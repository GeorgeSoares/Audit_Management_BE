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

/**
 * Controller responsible for handling HTTP requests related to audit operations.
 * It exposes endpoints to create, retrieve, update, and delete audits.
 */
@RestController
@RequestMapping("/api/v1/audits")
@CrossOrigin(origins = "*")
public class AuditController {

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private AuditService auditService;

    /**
     * Health check.
     * @return
     */
    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }

    /**
     * This method creates a new audit based on the provided DTO.
     *
     * @param auditRequest DTO containing audit creation data.
     * @return HTTP response.
     */
    @PostMapping(value = "/createAudit")
    public ResponseEntity<?> createNewAudit(@RequestBody AuditRequestDTO auditRequest) {
        if (auditService.createNewAudit(auditRequest)) {
            return ResponseEntity.status(HttpStatus.OK).body("Audit successfully created");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Fail to create the audit");
    }

    /**
     * Retrieves a list of all audits in the database.
     *
     * @return List of existing audits in the database.
     */
    @GetMapping(value = "/")
    public List<Audit> listAllAudits() {
        return auditService.listAudits();
    }

    /**
     * Retrieves a specific audit by its UUID.
     *
     * @param auditId UUID of the audit to retrieve.
     * @return If the Audit is found, it will be return in a body of an HTTP Response (Status 200). 404 if not found.
     */
    @GetMapping(value = "/{auditId}")
    public ResponseEntity<?> getAudit(@PathVariable UUID auditId) {
        Optional<Audit> audit = auditService.getAudit(auditId);

        if(audit.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(audit);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Audit not found.");
        }
    }

    /**
     * Updates an existing audit identified by its UUID.
     *
     * @param auditId UUID of the audit to update.
     * @param auditEditDTO DTO with updated audit information.
     * @return HTTP 200 if updated, 400 if audit not found or update fails.
     */
    @PutMapping(value = "/{auditId}")
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

    /**
     * Deletes an audit identified by its UUID.
     *
     * @param auditId UUID of the audit to delete.
     * @return HTTP 200 if deleted, or 400 if audit does not exist.
     */
    @DeleteMapping(value = "/{auditId}")
    public ResponseEntity<?> deleteAudit(@PathVariable UUID auditId) {

        if (auditService.deleteAudit(auditId)) {
            return ResponseEntity.ok("Audit deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Audit not deleted");
        }
    }
}
