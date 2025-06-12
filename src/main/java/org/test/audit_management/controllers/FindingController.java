package org.test.audit_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.audit_management.dtos.FindingEditDTO;
import org.test.audit_management.dtos.NewFindingDTO;
import org.test.audit_management.models.Finding;
import org.test.audit_management.repositories.AuditRepository;
import org.test.audit_management.repositories.FindingsRepository;
import org.test.audit_management.services.AuditService;
import org.test.audit_management.services.FindingService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class FindingController {

    @Autowired
    FindingsRepository findingsRepository;

    @Autowired
    FindingService findingService;

    @Autowired
    AuditRepository auditRepository;

    @Autowired
    AuditService auditService;

    @PostMapping("/audits/{auditId}/newFinding")
    public ResponseEntity<?> createNewFinding(@PathVariable UUID auditId, @RequestBody NewFindingDTO newFindingDTO) {
        if (findingService.createFinding(newFindingDTO, auditId)) {
            return ResponseEntity.ok("Finding successfully created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Finding not created");
        }
    }

    @PutMapping("/audits/{auditId}/findings/{findingId}")
    public ResponseEntity<?> editFinding(@PathVariable long findingId, @RequestBody FindingEditDTO findingEditDTO) {

        Optional<Finding> findingOptional = findingsRepository.findById(findingId);

        if (findingOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Finding not found");
        } else {
            if (findingService.editFinding(findingEditDTO, findingId)) {
                return ResponseEntity.ok("Finding updated");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Finding not updated");
        }
    }

    @DeleteMapping("/audits/{auditId}/findings/{findingId}")
    public ResponseEntity<?> deleteFinding(@PathVariable Long findingId) {

        return findingService.deleteFinding(findingId);
    }


    @GetMapping("/audits/{auditId}/findings/{findingId}")
    public Optional<Finding> getFinding(@PathVariable long findingId) {
        return findingService.getFinding(findingId);
    }

    @GetMapping("/audits/findings")
    public List<Finding> listFindings() {
        return findingsRepository.findAll();
    }

    @GetMapping("/audits/{auditId}/findings")
    public List<Finding> listAuditFindings(@PathVariable UUID auditId) {
        return findingService.listAuditFindings(auditId);
    }
}
