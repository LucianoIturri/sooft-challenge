package org.transport.healthCheck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping(path = "/liveness")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok().body("Service running");
    }
}
