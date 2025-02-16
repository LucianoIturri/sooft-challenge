package org.domain.model.enterprise;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.domain.model.transfer.Transfer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
@Table(name = "enterprises")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull("CUIT cannot be null")
    private String cuit;
    @Column(name = "company_name")
    @NotNull("Company name cannot be null")
    private String companyName;
    @Column(name = "accession_date")
    private String accessionDate;
    @OneToMany(targetEntity = Transfer.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "enterprise")
    private List<Transfer> transfers;
}
