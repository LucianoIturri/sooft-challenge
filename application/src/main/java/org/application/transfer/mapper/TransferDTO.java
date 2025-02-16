package org.application.transfer.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {
    @JsonProperty("amount")
    private Float amount;
    @JsonProperty("credit_account")
    private String creditAccount;
    @JsonProperty("debit_account")
    private String debitAccount;
}
