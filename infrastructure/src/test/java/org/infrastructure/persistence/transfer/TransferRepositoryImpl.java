package org.infrastructure.persistence.transfer;

import jakarta.persistence.EntityManager;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TransferRepositoryImpl {
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private TransferRepositoryImpl repository;
}
