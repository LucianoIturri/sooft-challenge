package org.transport.v1.transfer;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.application.transfer.TransferService;
import org.application.transfer.mapper.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/transfer")
public class TransferController {
    private final TransferService service;

    @Autowired
    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping(path = "/create")
    @Operation(summary = "New transfer", description = "It creates a new enterprise transfer", tags = "Transfers")

    public ResponseEntity<TransferDTO> create(@Valid @RequestBody TransferDTO transferDTO) throws Exception {
        TransferDTO transfer = service.createTransfer(transferDTO);
        return ResponseEntity.ok(transfer);
    }
}
