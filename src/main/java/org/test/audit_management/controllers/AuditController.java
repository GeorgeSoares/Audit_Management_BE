package org.test.audit_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.audit_management.dtos.AuditRequestDTO;
import org.test.audit_management.models.Audit;
import org.test.audit_management.repositories.AuditRepository;
import org.test.audit_management.services.AuditService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AuditController {

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private AuditService auditService;

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping(value = "/createAudit")
    public ResponseEntity<?> createNewAudit(@RequestBody AuditRequestDTO auditRequest) {
        if (auditService.createNewAudit(auditRequest)) {
            return ResponseEntity.status(HttpStatus.OK).body("Audit successfully created");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Fail to create the audit");
    }

    @GetMapping(value = "/audits")
    public List<Audit> listAllAudits() {
        return auditService.listAudits();
    }

    @GetMapping(value = "/audits/{auditId}")
    public Optional<Audit> getAudit(@PathVariable UUID auditId) {
        return auditService.getAudit(auditId);
    }

}
