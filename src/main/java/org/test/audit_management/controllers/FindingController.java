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

/**
 * Controller responsible for handling HTTP requests related to Findings.
 * It supports operations such as creating, editing, retrieving, listing, and deleting findings associated with audits
 */
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

    /**
     * Creates a new finding and associates it with an audit.
     *
     * @param auditId UUID of the audit the finding belongs to.
     * @param newFindingDTO DTO containing the data for the new finding.
     * @return HTTP Response: 200 if created, 400 if creation fails.
     */
    @PostMapping("/audits/{auditId}/newFinding")
    public ResponseEntity<?> createNewFinding(@PathVariable UUID auditId, @RequestBody NewFindingDTO newFindingDTO) {
        if (findingService.createFinding(newFindingDTO, auditId)) {
            return ResponseEntity.ok("Finding successfully created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Finding not created");
        }
    }

    /**
     * Updates an existing finding based on its ID.
     *
     * @param findingId ID of the finding to update.
     * @param findingEditDTO DTO containing the updated data.
     * @return HTTP Response: 200 if updated, 400 if not found or update fails.
     */
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

    /**
     * Deletes a finding based on its ID.
     *
     * @param findingId ID of the finding to be deleted.
     * @return ResponseEntity with appropriate HTTP status and message.
     */
    @DeleteMapping("/audits/{auditId}/findings/{findingId}")
    public ResponseEntity<?> deleteFinding(@PathVariable Long findingId) {

        return findingService.deleteFinding(findingId);
    }

    /**
     * Retrieves a specific finding by its ID.
     *
     * @param findingId ID of the finding.
     * @return Optional containing the finding if found, or empty if not.
     */
    @GetMapping("/audits/{auditId}/findings/{findingId}")
    public Optional<Finding> getFinding(@PathVariable long findingId) {
        return findingService.getFinding(findingId);
    }

    /**
     * Lists all findings in the system.
     *
     * @return List of all findings.
     */
    @GetMapping("/audits/findings")
    public List<Finding> listFindings() {
        return findingsRepository.findAll();
    }

    /**
     * Lists all findings associated with a specific audit.
     *
     * @param auditId UUID of the audit.
     * @return List of findings related to the audit.
     */
    @GetMapping("/audits/{auditId}/findings")
    public List<Finding> listAuditFindings(@PathVariable UUID auditId) {
        return findingService.listAuditFindings(auditId);
    }
}
