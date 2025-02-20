package org.application.enterprise.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.transfer.mapper.TransferDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnterpriseDTO {
    @Hidden
    private Integer id;
    @JsonProperty("cuit")
    @NotNull(message = "CUIT cannot be null")
    @NotBlank(message = "CUIT cannot be empty")
    @Schema(
            description = "Company CUIT",
            example = "30014802014",
            required = true
    )
    private String cuit;
    @JsonProperty("name")
    @NotNull(message = "Company name cannot be null")
    @NotBlank(message = "Company name cannot be empty")
    @Schema(
            description = "Company name",
            example = "AWESOME COMPANY I",
            required = true
    )
    private String companyName;
    @Hidden
    private String accessionDate;
    @Hidden
    private List<TransferDTO> transfers;
}
