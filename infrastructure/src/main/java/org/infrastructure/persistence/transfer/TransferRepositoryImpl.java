package org.infrastructure.persistence.transfer;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.domain.model.transfer.Transfer;
import org.domain.port.transfer.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
@Slf4j
public class TransferRepositoryImpl implements TransferRepository {
    private final EntityManager entityManager;

    @Autowired
    public TransferRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Transfer transfer) {
        try {
            entityManager.persist(transfer);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
