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


/**
 *  This Service here is responsible for the implementation of the business logic related to the Audit Findings, it covers the
 *  creation, update, retrieval e deletion of findings.
 *  For the purpose of the interaction with the database, the bean of the findings repository is injected. A bean of the audit repository
 *  is also injected.
 */
@Service
public class FindingService {

    @Autowired
    AuditRepository auditRepository;

    @Autowired
    FindingsRepository findingsRepository;

    /**
     * This method creates a finding, it checks if the audit_id passed as argument is valid, if not, it returns an error.
     * All findings must be linked to an audit!
     *
     * @param newFindingDTO Object with the information of the finding sent by the template.
     * @param auditId GUID of the audit to which the finding is linked to.
     * @return true if the audit was successfully created.
     */
    public boolean createFinding(@NotNull NewFindingDTO newFindingDTO, @NotNull UUID auditId) {

        Optional<Audit> auditOptional = auditRepository.findById(auditId);

        if (auditOptional.isEmpty()) {
            return false;
        }

        Finding finding = new Finding(newFindingDTO, auditOptional.get());
        findingsRepository.save(finding);

        return true;
    }

    /**
     * This method is used to update the information of an existing finding based on the information sent by the
     * template on the front end. It checks if the Finding id is valid, if so, it will be updated according to the
     * information passed.
     *
     * @param findingEditDTO DTO with the updated information of the finding.
     * @param findingId ID of the finding to be changed.
     * @return true if the updated object of the finding was successfully persisted in the database.
     */
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

    /**
     * This method deletes a finding from the database. It takes the finding id as argument and proceed with the deletion
     * if the provided id is valid.
     *
     * @param findingId ID of the finding to be deleted.
     * @return Response entity with the result of the process.
     */
    public ResponseEntity<?> deleteFinding(long findingId) {
        Optional<Finding> findingOptional = findingsRepository.findById(findingId);

        if (findingOptional.isPresent()) {
            Finding finding = findingOptional.get();
            findingsRepository.delete(finding);
            return ResponseEntity.ok("Finding deleted");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Finding not found");
    }

    /**
     * This method lists all findings from an audit based on the audit id.
     *
     * @param auditID ID of the audit to be consulted.
     * @return List of the Findings of the audit.
     */
    public List<Finding> listAuditFindings(UUID auditID) {

        Optional<Audit> audit = auditRepository.findById(auditID);

        List<Finding> listOfFindings = findingsRepository.findByAudit(audit.get());
        return listOfFindings;
    }

    public Optional<Finding> getFinding(long findingId) {
        return findingsRepository.findById(findingId);
    }
}
