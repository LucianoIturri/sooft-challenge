package org.domain.port.enterprise;

import org.domain.model.enterprise.Enterprise;
import org.springframework.stereotype.Component;

import java.util.List;
public interface EnterpriseRepository {
    public void accession(Enterprise enterprise);
    public List<Enterprise> enterprisesTransfers();
    public List<Enterprise> newerEnterprises(String dateFilter);
}
