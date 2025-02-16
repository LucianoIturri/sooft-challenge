package org.application.enterprise.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.transfer.mapper.TransferDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnterpriseDTO {
    @JsonProperty("cuit")
    @NotNull("CUIT name cannot be null")
    private String cuit;
    @JsonProperty("name")
    @NotNull("Company name cannot be null")
    private String companyName;
    private String accessionDate;
    private List<TransferDTO> transfers;
}
