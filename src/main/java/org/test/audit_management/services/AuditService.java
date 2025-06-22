package org.test.audit_management.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.audit_management.dtos.AuditEditDTO;
import org.test.audit_management.dtos.AuditRequestDTO;
import org.test.audit_management.models.Audit;
import org.test.audit_management.repositories.AuditRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This Service here is responsible for the implementation of the business logic related to the Audits, it covers the
 * creation, update, retrieval e deletion of audits.
 * For the purpose of the interaction with the db, the bean of the repository is injected.
 */


@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    /**
     * This method creates an audit based on the corresponding DTO. It sets the attributes according to the data given as input
     * on the Audit form and persists the audit on the database.
     *
     * @param auditRequestDTO DTO with the information of the audit.
     * @return true if the audit was successfully persisted in the database.
     */
    public boolean createNewAudit(AuditRequestDTO auditRequestDTO) {
        Audit newAudit = new Audit();

        BeanUtils.copyProperties(auditRequestDTO, newAudit);

        newAudit.setAuditBegin(Audit.convertMllsTimeStamp(auditRequestDTO.auditBegin()));
        newAudit.setAuditEnd(Audit.convertMllsTimeStamp(auditRequestDTO.auditEnd()));
        newAudit.setAuditStatus("In planning");
        newAudit.setAuditCreatedAt(LocalDateTime.now());
        newAudit.setLastUpdate(LocalDateTime.now());

        try {
            auditRepository.save(newAudit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method returns a list of all audits in the database.
     * @return list of audits present in the database.
     */
    public List<Audit> listAudits() {
        return auditRepository.findAll();
    }


    /**
     * This method returns a specific audit.
     * @param auditId GUID of the audited to be consulted.
     * @return audit if the GUID is valid.
     */
    public Optional<Audit> getAudit(UUID auditId) {
        Optional<Audit> audit = auditRepository.findById(auditId);

        return audit;
    }

    /**
     * This method updates an existing audit with the data provided by the corresponding DTO.
     *
     * @param auditInput Object of the audit to be updated.
     * @param auditEditDTO DTO containing the updated information of the audit.
     *
     * @return true if the updated audit object was persisted successfully.
     */
    public boolean updateExistingAudit(Audit auditInput, AuditEditDTO auditEditDTO) {
        Optional<Audit> auditOptional = auditRepository.findById(auditInput.getAuditId());

        if (auditOptional.isEmpty()) return false;

        Audit audit = auditOptional.get();

        audit.setAuditBegin(Audit.convertMllsTimeStamp(auditEditDTO.auditBegin()));
        audit.setAuditEnd(Audit.convertMllsTimeStamp(auditEditDTO.auditEnd()));
        audit.setLeadAuditor(auditEditDTO.leadAuditor());
        audit.setCoAuditors(auditEditDTO.coAuditors());
        audit.setAuditedCompany(auditEditDTO.auditedCompany());
        audit.setAuditedDepartment(auditEditDTO.auditedDepartment());
        audit.setAuditStatus(auditEditDTO.auditStatus());
        audit.setLastUpdate(LocalDateTime.now());

        try {
            auditRepository.save(audit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Deletes an audit from the database.
     *
     * @param auditId GUID of the audit to be deleted.
     * @return true is the audit was successfully deleted.
     */
    public boolean deleteAudit(UUID auditId) {
        Optional<Audit> auditOptional = auditRepository.findById(auditId);

        if (auditOptional.isEmpty()) {
            return false;
        } else {
            auditRepository.delete(auditOptional.get());
            return true;
        }
    }
}
