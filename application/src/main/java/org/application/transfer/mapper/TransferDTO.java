package org.application.transfer.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {
    private Integer id;
    @JsonProperty("amount")
    @NotNull("Amount cannot be null")
    private Float amount;
    @JsonProperty("credit_account")
    @NotNull("Credit account cannot be null")
    private String creditAccount;
    @JsonProperty("debit_account")
    @NotNull("Debit account account cannot be null")
    private String debitAccount;
    private String date;
    @JsonProperty("enterprise_id")
    @NotNull("Enterprise id cannot be null")
    @JsonIgnore
    private Integer enterpriseId;
}
