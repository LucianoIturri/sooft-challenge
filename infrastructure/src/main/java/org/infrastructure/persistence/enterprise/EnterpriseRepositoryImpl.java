package org.infrastructure.persistence.enterprise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.domain.model.enterprise.Enterprise;
import org.domain.port.enterprise.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class EnterpriseRepositoryImpl implements EnterpriseRepository {
    private final EntityManager entityManager;

    @Autowired
    public EnterpriseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void accession(Enterprise enterprise) {
        try {
            Objects.requireNonNull(enterprise);
            entityManager.persist(enterprise);
        } catch (TransactionRequiredException e) {
            log.error(e.getMessage());
            throw new TransactionRequiredException(e.getMessage(), e);
        }
    }

    @Override
    public List<Enterprise> enterprisesTransfers(String dateFilter) {
        try {
            String jpql = "SELECT e FROM Enterprise e JOIN e.transfers t WHERE t.date >= :dateFilter";
            TypedQuery<Enterprise>  query = entityManager.createQuery(jpql, Enterprise.class);
            query.setParameter("dateFilter", dateFilter);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error trying to get enterprises: " + e.getMessage(), e);
        }
    }


    @Override
    public List<Enterprise> newerEnterprises(String dateFilter) {
        try {
            String jpql = "SELECT e FROM Enterprise e WHERE e.accessionDate >= :dateFilter";
            TypedQuery<Enterprise>  query = entityManager.createQuery(jpql, Enterprise.class);
            query.setParameter("dateFilter", dateFilter);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error trying to get enterprises: " + e.getMessage(), e);
        }
    }
}
