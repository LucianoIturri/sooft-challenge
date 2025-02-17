package org.infrastructure.persistence.enterprise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.TypedQuery;
import org.domain.model.enterprise.Enterprise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnterpriseRepositoryImplTests {
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private EnterpriseRepositoryImpl repository;


    @Test
    void testAccession_Success() {
        Enterprise enterprise = new Enterprise(1, "123456789", "Tech Corp",
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Arrays.asList()
        );
        assertDoesNotThrow(() -> repository.accession(enterprise));
        verify(entityManager, times(1)).persist(enterprise);
    }

    @Test
    void testAccession_Failure_TransactionRequired() {
        Enterprise enterprise = new Enterprise(1, "123456789", "Fail Corp",
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Arrays.asList()
        );
        doThrow(new TransactionRequiredException("Transaction required"))
                .when(entityManager).persist(enterprise);
        Assertions.assertThrows(TransactionRequiredException.class, () -> repository.accession(enterprise));
    }

    @Test
    public void testEnterprisesTransfers_Success() {
        String dateFilter = "2024-01-01";
        List<Enterprise> expectedEnterprises = List.of(
                new Enterprise(1, "123456789", "Corp I",
                        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Arrays.asList()
                ),
                new Enterprise(1, "123456789", "Corp II",
                        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Arrays.asList()
                ));

        TypedQuery<Enterprise> queryMock = mock(TypedQuery.class);
        when(queryMock.getResultList()).thenReturn(expectedEnterprises);
        when(entityManager.createQuery(anyString(), eq(Enterprise.class))).thenReturn(queryMock);
        List<Enterprise> result = repository.enterprisesTransfers(dateFilter);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Corp I", result.get(0).getCompanyName());
    }

    @Test
    public void testEnterprisesTransfers_Fail() {
        String dateFilter = "2024-01-01";
        TypedQuery<Enterprise> queryMock = mock(TypedQuery.class);
        when(queryMock.getResultList()).thenThrow(new RuntimeException("Database error"));
        when(entityManager.createQuery(anyString(), eq(Enterprise.class))).thenReturn(queryMock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            repository.enterprisesTransfers(dateFilter);
        });
        Assertions.assertEquals("Error trying to get enterprises: Database error", exception.getMessage());
    }

    @Test
    public void testNewerEnterprises_Success() {
        String dateFilter = "2024-01-01";
        List<Enterprise> expectedEnterprises = List.of(
                new Enterprise(1, "123456789", "Corp I",
                        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Arrays.asList()
                ),
                new Enterprise(1, "123456789", "Corp II",
                        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Arrays.asList()
                ));

        TypedQuery<Enterprise> queryMock = mock(TypedQuery.class);
        when(queryMock.getResultList()).thenReturn(expectedEnterprises);
        when(entityManager.createQuery(anyString(), eq(Enterprise.class))).thenReturn(queryMock);
        List<Enterprise> result = repository.newerEnterprises(dateFilter);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Corp II", result.get(1).getCompanyName());
    }

    @Test
    public void testNewerEnterprises_Fail() {
        String dateFilter = "2024-01-01";
        TypedQuery<Enterprise> queryMock = mock(TypedQuery.class);
        when(queryMock.getResultList()).thenThrow(new RuntimeException("Database error"));
        when(entityManager.createQuery(anyString(), eq(Enterprise.class))).thenReturn(queryMock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            repository.newerEnterprises(dateFilter);
        });
        Assertions.assertEquals("Error trying to get enterprises: Database error", exception.getMessage());
    }
}
