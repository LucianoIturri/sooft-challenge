package org.transport.healthCheck;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping(path = "/liveness")
    @Operation(summary = "Health check", tags = "Health check")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok().body("Service running");
    }
}
