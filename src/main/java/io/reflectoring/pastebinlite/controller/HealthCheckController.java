package io.reflectoring.pastebinlite.controller;

import io.reflectoring.pastebinlite.repository.PasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @Autowired
    private PasteRepository repo;

    @GetMapping("/healthz")
    public ResponseEntity<Map<String, Boolean>> health() {
        repo.count(); // ensures DB access
        return ResponseEntity.ok(Map.of("ok", true));
    }
}

