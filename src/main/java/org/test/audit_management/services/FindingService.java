package org.test.audit_management.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.test.audit_management.dtos.FindingEditDTO;
import org.test.audit_management.dtos.NewFindingDTO;
import org.test.audit_management.models.Audit;
import org.test.audit_management.models.Finding;
import org.test.audit_management.repositories.AuditRepository;
import org.test.audit_management.repositories.FindingsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindingService {

    @Autowired
    AuditRepository auditRepository;

    @Autowired
    FindingsRepository findingsRepository;

    public boolean createFinding(@NotNull NewFindingDTO newFindingDTO, @NotNull UUID auditId) {

        Optional<Audit> auditOptional = auditRepository.findById(auditId);

        if (auditOptional.isEmpty()) {
            return false;
        }

        Finding finding = new Finding(newFindingDTO, auditOptional.get());
        findingsRepository.save(finding);

        return true;
    }

    public boolean editFinding(@NotNull FindingEditDTO findingEditDTO, long findingId) {

        Optional<Finding> findingOptional = findingsRepository.findById(findingId);

        if (findingOptional.isEmpty()) {
            return false;
        } else {
            Finding finding = findingOptional.get();

            finding.updateFields(findingEditDTO);

            findingsRepository.save(finding);
            return true;
        }
    }

    public ResponseEntity<?> deleteFinding(long findingId) {
        Optional<Finding> findingOptional = findingsRepository.findById(findingId);

        if (findingOptional.isPresent()) {
            Finding finding = findingOptional.get();
            findingsRepository.delete(finding);
            return ResponseEntity.ok("Finding deleted");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Finding not found");
    }

    public List<Finding> listAuditFindings(UUID auditID) {

        Optional<Audit> audit = auditRepository.findById(auditID);

        List<Finding> listOfFindings = findingsRepository.findByAudit(audit.get());
        return listOfFindings;
    }

    public Optional<Finding> getFinding(long findingId) {
        return findingsRepository.findById(findingId);
    }
}
