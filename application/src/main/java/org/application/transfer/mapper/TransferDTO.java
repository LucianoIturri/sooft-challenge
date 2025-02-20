package org.application.transfer.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {
    @Hidden
    private Integer id;
    @JsonProperty("amount")
    @NotNull(message = "Amount cannot be null")
    @Schema(
            description = "Company CUIT",
            example = "1.2",
            required = true
    )
    private Float amount;
    @JsonProperty("credit_account")
    @NotNull(message ="Credit account cannot be null")
    @NotBlank(message ="Credit account cannot be empty")
    @Schema(
            description = "Receiver account",
            example = "0800",
            required = true
    )
    private String creditAccount;
    @JsonProperty("debit_account")
    @NotNull(message ="Debit account account cannot be null")
    @NotBlank(message ="Debit account account cannot be empty")
    @Schema(
            description = "Sender account",
            example = "0900",
            required = true
    )
    private String debitAccount;
    @Hidden
    private String date;
    @JsonProperty("enterprise_id")
    @NotNull(message ="Enterprise id cannot be null")
    @Schema(
            description = "Company owner",
            example = "1",
            required = true
    )
    private Integer enterpriseId;
}
