package org.domain.model.enterprise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    private String cuit;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "accession_date")
    private String accessionDate;
    @OneToMany(targetEntity = Transfer.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "enterprise")
    @ToString.Exclude
    private List<Transfer> transfers;
}
