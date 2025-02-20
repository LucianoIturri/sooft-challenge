package org.transport.v1.enterprise;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.application.enterprise.EnterpriseService;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/enterprise")
public class EnterpriseController {
    private final EnterpriseService service;

    @Autowired
    public EnterpriseController(EnterpriseService service) {
        this.service = service;
    }

    @PostMapping(path = "/create")
    @Operation(summary = "New enterprise accession", description = "Add a company as customer",tags = "Enterprise")
    public ResponseEntity<EnterpriseDTO> create(@Valid @RequestBody EnterpriseDTO enterpriseDTO) throws Exception {
        EnterpriseDTO enterpriseAccessed = service.accession(enterpriseDTO);
        return ResponseEntity.ok(enterpriseAccessed);
    }

    @GetMapping(path = "/newer")
    @Operation(summary = "Filter newer enterprises", description = "Newer enterprises will be displayed", tags = "Enterprise")
    @ApiResponse(responseCode = "200",
            description = "List of companies retrieved successfully",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = EnterpriseDTO.class))
            )
    )

    public ResponseEntity<List<EnterpriseDTO>> findNewerEnterprises() {
        List<EnterpriseDTO> enterprises = service.newerEnterprises();
        return ResponseEntity.ok().body(enterprises);
    }

    @GetMapping(path = "/last-transfers")
    @Operation(summary = "Last transfers", description = "Companies that had transferred money in the last month", tags = "Enterprise")
    @ApiResponse(responseCode = "200",
            description = "List of companies retrieved successfully",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = EnterpriseDTO.class))
            )
    )
    public ResponseEntity<List<EnterpriseDTO>> lastEnterprisesTransfers() {
        List<EnterpriseDTO> enterprises = service.lastEnterprisesTransfers();
        return ResponseEntity.ok().body(enterprises);
    }
}
