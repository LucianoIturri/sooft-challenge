package org.application.enterprise;

import org.application.ApplicationLayerTests;
import org.domain.model.enterprise.Enterprise;
import org.domain.port.enterprise.EnterpriseRepository;
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
        Enterprise enterprise = Enterprise.builder()
                .cuit("123345")
                .companyName("TEST ENTERPRISE")
                .accessionDate(date)
                .build();
        Enterprise createdEnterprise = service.createEnterprise(enterprise);
    }
}
