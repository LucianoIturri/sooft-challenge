package org.application.enterprise;

import lombok.extern.slf4j.Slf4j;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.domain.model.enterprise.Enterprise;
import org.domain.port.enterprise.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EnterpriseService {
    private final EnterpriseRepository repository;

    @Autowired
    public EnterpriseService(EnterpriseRepository repository) {
        this.repository = repository;
    }

    public EnterpriseDTO accession(EnterpriseDTO enterpriseDTO) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        enterpriseDTO.setAccessionDate(date);
        Enterprise enterprise = mapper(enterpriseDTO);
        repository.accession(enterprise);
        return enterpriseDTO;
    }

    private Enterprise mapper(EnterpriseDTO enterpriseDTO) {
        return Enterprise.builder()
                .cuit(enterpriseDTO.getCuit())
                .accessionDate(enterpriseDTO.getAccessionDate())
                .companyName(enterpriseDTO.getCompanyName())
                .build();
    }

    public List<EnterpriseDTO> findPostsByDateRange() {
        String date = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Enterprise> newerEnterprises = repository.newerEnterprises(date);
        List<EnterpriseDTO> parsedNewerEnterprises = newerEnterprises.stream()
                .map(enterprise -> {
                    return EnterpriseDTO.builder()
                            .accessionDate(enterprise.getAccessionDate())
                            .cuit(enterprise.getCuit())
                            .companyName(enterprise.getCompanyName())
                            .build();
                })
                .collect(Collectors.toList());

        return parsedNewerEnterprises;
    }
}
