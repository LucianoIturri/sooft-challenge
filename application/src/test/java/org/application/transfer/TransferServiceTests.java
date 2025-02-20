package org.application.transfer;

import jakarta.persistence.EntityManager;
import org.application.transfer.mapper.TransferDTO;
import org.domain.model.transfer.Transfer;
import org.domain.port.transfer.TransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTests {
    @Mock
    private EntityManager entityManager;
    @Mock
    private TransferRepository repository;
    @InjectMocks
    private TransferService service;

    @Test
    void testCreateTransfer_Success() {
        TransferDTO transferDTO = new TransferDTO(1, Float.valueOf(1000), "ACC123", "ACC456", LocalDate.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ), 0);
        TransferDTO result = service.createTransfer(transferDTO);
        Assertions.assertNotNull(result.getDate());
    }

    @Test
    void testCreateTransfer_Failure_NullTransfer() {
        Assertions.assertThrows(NullPointerException.class, () -> service.createTransfer(null));
    }

    @Test
    void testCreateTransfer_BorderCase_ZeroAmount() {
        TransferDTO transferDTO = new TransferDTO(1, Float.valueOf(0), "ACC123", "ACC456", LocalDate.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ), 0);
        TransferDTO result = service.createTransfer(transferDTO);
        Assertions.assertEquals(Float.valueOf(0), result.getAmount());
    }

    @Test
    void testMapper_Success() {
        TransferDTO transferDTO = new TransferDTO(3, Float.valueOf(500), "ACC555", "ACC666", LocalDate.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ), 0);
        Transfer result = service.mapper(transferDTO);
        Assertions.assertEquals(transferDTO.getId(), result.getId());
        Assertions.assertEquals(transferDTO.getAmount(), result.getAmount());
    }

    @Test
    void testMapper_Failure_NullTransferDTO() {
        Assertions.assertThrows(NullPointerException.class, () -> service.mapper(null));
    }

    @Test
    void testMapper_BorderCase_EmptyFields() {
        TransferDTO transferDTO = new TransferDTO();
        // null param
        Assertions.assertThrows(NullPointerException.class, () -> service.mapper(null));
        // empty param
        Assertions.assertThrows(IllegalStateException.class, () -> service.mapper(transferDTO));
    }
}
