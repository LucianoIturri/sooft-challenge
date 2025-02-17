package org.application.enterprise;

import lombok.extern.slf4j.Slf4j;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.application.transfer.mapper.TransferDTO;
import org.domain.model.enterprise.Enterprise;
import org.domain.port.enterprise.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    @Transactional(readOnly = true)
    public List<EnterpriseDTO> newerEnterprises() {
        String date = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Enterprise> newerEnterprises = repository.newerEnterprises(date);
        List<EnterpriseDTO> parsedNewerEnterprises = dtoParser(newerEnterprises);

        return parsedNewerEnterprises;
    }

    @Transactional(readOnly = true)
    public List<EnterpriseDTO> lastEnterprisesTransfers(){
        String dateFilter = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Enterprise> enterprises = repository.enterprisesTransfers(dateFilter);
        List<EnterpriseDTO> parsedEnterprises = dtoParser(enterprises);
        return parsedEnterprises;
    }

    private List<EnterpriseDTO> dtoParser(List<Enterprise> enterprises){
        return enterprises.stream()
                .map(enterprise -> {
                    List<TransferDTO> dtoTransfers = new ArrayList<TransferDTO>();

                    if (!enterprise.getTransfers().isEmpty()) {
                        dtoTransfers = Optional.ofNullable(enterprise.getTransfers())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(transfer -> {
                                    return TransferDTO.builder()
                                            .id(transfer.getId())
                                            .enterpriseId(enterprise.getId())
                                            .amount(transfer.getAmount())
                                            .debitAccount(transfer.getDebitAccount())
                                            .creditAccount(transfer.getDebitAccount())
                                            .date(transfer.getDate())
                                            .build();
                                })
                                .collect(Collectors.toList());
                    }
                    return EnterpriseDTO.builder()
                            .id(enterprise.getId())
                            .accessionDate(enterprise.getAccessionDate())
                            .cuit(enterprise.getCuit())
                            .companyName(enterprise.getCompanyName())
                            .transfers(dtoTransfers)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
