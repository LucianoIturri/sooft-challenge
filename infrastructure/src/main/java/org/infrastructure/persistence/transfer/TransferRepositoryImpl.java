package org.infrastructure.persistence.transfer;

import jakarta.persistence.EntityManager;
import org.domain.model.transfer.Transfer;
import org.domain.port.transfer.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRepositoryImpl implements TransferRepository {
    private final EntityManager entityManager;

    @Autowired
    public TransferRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Transfer transfer) {
        entityManager.persist(transfer);
    }
}
