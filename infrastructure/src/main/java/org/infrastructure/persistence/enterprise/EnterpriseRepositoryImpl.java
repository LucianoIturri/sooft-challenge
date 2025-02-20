package org.infrastructure.persistence.enterprise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.QueryTimeoutException;
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
            log.error("Enterprise accession: TransactionRequiredException occurred while processing request:", e);
            throw new TransactionRequiredException("Enterprise accession: A transaction is required for this operation but is missing", e);
        }
    }

    @Override
    public List<Enterprise> enterprisesTransfers(String dateFilter) {
        try {
            Objects.requireNonNull(dateFilter);
            String jpql = "SELECT e FROM Enterprise e JOIN e.transfers t WHERE t.date >= :dateFilter";
            TypedQuery<Enterprise>  query = entityManager.createQuery(jpql, Enterprise.class);
            query.setParameter("dateFilter", dateFilter);
            return query.getResultList();
        } catch (QueryTimeoutException e) {
            log.error("Enterprise transfers: Could not finishing operation due a time out exception", e);
            throw new QueryTimeoutException("Enterprise transfers: Query execution timed out: ", e);
        }
    }


    @Override
    public List<Enterprise> newerEnterprises(String dateFilter) {
        try {
            Objects.requireNonNull(dateFilter);
            String jpql = "SELECT e FROM Enterprise e WHERE e.accessionDate >= :dateFilter";
            TypedQuery<Enterprise>  query = entityManager.createQuery(jpql, Enterprise.class);
            query.setParameter("dateFilter", dateFilter);
            return query.getResultList();
        } catch (QueryTimeoutException e) {
            log.error("Newer enterprises: Could not finishing operation due a time out exception", e);
            throw new QueryTimeoutException("Newer enterprises: Query execution timed out: ", e);
        }
    }
}
