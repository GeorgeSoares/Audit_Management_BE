package org.test.audit_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.audit_management.dtos.AuditEditDTO;
import org.test.audit_management.dtos.AuditRequestDTO;
import org.test.audit_management.models.Audit;
import org.test.audit_management.repositories.AuditRepository;
import org.test.audit_management.services.AuditService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
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

    @GetMapping(value = "/audits/{auditId}") // Add custom return to the function
    public Optional<Audit> getAudit(@PathVariable UUID auditId) {
        return auditService.getAudit(auditId);
    }

    @PostMapping(value = "/audits/{auditId}")
    public ResponseEntity<?> updateAudit(@PathVariable UUID auditId, @RequestBody AuditEditDTO auditEditDTO) {

        Optional<Audit> auditOptional = auditRepository.findById(auditId);

        if (auditOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Audit not found");
        }

        if (auditService.updateExistingAudit(auditOptional.get(), auditEditDTO)) {
            return ResponseEntity.ok("Audit successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It was not possible to update the audit.");
        }
    }

    @DeleteMapping(value = "/audits/{auditId}")
    public ResponseEntity<?> deleteAudit(@PathVariable UUID auditId) {

        if (auditService.deleteAudit(auditId)) {
            return ResponseEntity.ok("Audit deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Audit not deleted");
        }
    }
}
