package org.application.enterprise;

import jakarta.persistence.EntityManager;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.domain.model.enterprise.Enterprise;
import org.domain.model.transfer.Transfer;
import org.domain.port.enterprise.EnterpriseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EnterpriseServiceTests {
    @Mock
    private EntityManager entityManager;
    @Mock
    private EnterpriseRepository repository;
    @InjectMocks
    private EnterpriseService service;

    @Test
    public void save(){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        EnterpriseDTO enterprise = EnterpriseDTO.builder()
                .cuit("123345")
                .companyName("TEST ENTERPRISE")
                .accessionDate(date)
                .build();
        EnterpriseDTO createdEnterprise = service.accession(enterprise);
        Assertions.assertEquals(enterprise, createdEnterprise);
    }

    @Test
    void testAccession_Failure_NullEnterprise() {
        Assertions.assertThrows(NullPointerException.class, () -> service.accession(null));
    }

    @Test
    void testAccession_BorderCase_EmptyEnterpriseDTO() {
        EnterpriseDTO emptyEnterprise = new EnterpriseDTO();
        EnterpriseDTO result = service.accession(emptyEnterprise);
        Assertions.assertNotNull(result.getAccessionDate());
    }
    @Test
    void testDtoParser_Success() {
        List<Transfer> transfers = Arrays.asList(new Transfer(10, Float.valueOf(1000), "ACC123", "ACC456", "2024-02-17", null));
        Enterprise enterprise = new Enterprise(1, "123456789","Tech Corp", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),transfers);
        List<EnterpriseDTO> result = service.dtoParser(List.of(enterprise));
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("123456789", result.get(0).getCuit());
        Assertions.assertEquals(1, result.get(0).getTransfers().size());
        Assertions.assertEquals(Float.valueOf(1000), result.get(0).getTransfers().get(0).getAmount());
    }

    @Test
    void testDtoParser_Failure() {
        Enterprise enterprise = new Enterprise(1, "123456789","Tech Corp", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Arrays.asList());

        List<EnterpriseDTO> result = service.dtoParser(List.of(enterprise));
        Assertions.assertNotEquals("111111111", result.get(0).getCuit());
        Assertions.assertEquals(0, result.get(0).getTransfers().size());
    }

    @Test
    void testDtoParser_BorderCase_EmptyTransfers() {
        Enterprise enterprise = new Enterprise(3, "111222333", "Border Corp", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), Collections.emptyList());

        List<EnterpriseDTO> result = service.dtoParser(List.of(enterprise));
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("111222333", result.get(0).getCuit());
        Assertions.assertTrue(result.get(0).getTransfers().isEmpty());
    }
}
