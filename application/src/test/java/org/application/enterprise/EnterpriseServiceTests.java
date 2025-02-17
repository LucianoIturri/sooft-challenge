package org.application.enterprise;

import org.application.ApplicationLayerTests;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.domain.port.enterprise.EnterpriseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApplicationLayerTests.class)
public class EnterpriseServiceTests {
    @Mock
    private EnterpriseRepository repository;
    @InjectMocks
    private EnterpriseService service;

    @Test
    public void save(){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-mm-dd"));
        EnterpriseDTO enterprise = EnterpriseDTO.builder()
                .cuit("123345")
                .companyName("TEST ENTERPRISE")
                .accessionDate(date)
                .build();
        EnterpriseDTO createdEnterprise = service.accession(enterprise);
        Assertions.assertEquals(enterprise, createdEnterprise);
    }
}
