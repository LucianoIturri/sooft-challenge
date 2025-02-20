package org.infrastructure.persistence.transfer;

import jakarta.persistence.EntityManager;
import org.domain.model.enterprise.Enterprise;
import org.domain.model.transfer.Transfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransferRepositoryImplTests {
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private TransferRepositoryImpl repository;

    @Test
    public void save_Success() {
        Enterprise enterprise = Enterprise.builder()
                .id(1)
                .cuit("12345")
                .companyName("AWESOME COMPANY")
                .accessionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        Transfer transfer = Transfer.builder()
                .id(1)
                .date(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .amount(Float.parseFloat("1000"))
                .debitAccount("0800")
                .creditAccount("0900")
                .enterprise(enterprise)
                .build();

        assertDoesNotThrow(() -> repository.save(transfer));
        verify(entityManager, times(1)).persist(transfer);
    }

    @Test
    public void save_Failure_WhenParamIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> repository.save(null));
    }
}
