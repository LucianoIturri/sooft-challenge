package org.domain.model.transfer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.domain.model.enterprise.Enterprise;

@Entity
@Table(name = "transfers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Access(AccessType.FIELD)
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float amount;
    @Column(name = "credit_account")
    private String creditAccount;
    @Column(name = "debit_account")
    private String debitAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;
}
