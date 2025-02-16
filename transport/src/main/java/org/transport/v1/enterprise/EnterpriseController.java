package org.transport.v1.enterprise;

import io.swagger.v3.oas.annotations.Operation;
import org.application.enterprise.EnterpriseService;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Operation(summary = "New enterprise accession")
    public ResponseEntity<EnterpriseDTO> create(@RequestBody EnterpriseDTO enterpriseDTO) throws Exception {
        EnterpriseDTO enterpriseAccessed = service.accession(enterpriseDTO);
        return ResponseEntity.ok(enterpriseAccessed);
    }

    @GetMapping(path = "/newer")
    @Operation(summary = "Filter enterprises by date range", description = "Newer enterprises will be displayed")
    public ResponseEntity<List<EnterpriseDTO>> findNewerEnterprises() {
        List<EnterpriseDTO> enterprises = service.findPostsByDateRange();
        return ResponseEntity.ok().body(enterprises);
    }
}
