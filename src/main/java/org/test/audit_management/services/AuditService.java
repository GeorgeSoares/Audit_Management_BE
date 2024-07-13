package org.test.audit_management.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.audit_management.dtos.AuditEditDTO;
import org.test.audit_management.dtos.AuditRequestDTO;
import org.test.audit_management.models.Audit;
import org.test.audit_management.repositories.AuditRepository;
import org.test.audit_management.repositories.AuditorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

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

    public List<Audit> listAudits() {
        return auditRepository.findAll();
    }

    public Optional<Audit> getAudit(UUID auditId) {
        Optional<Audit> audit = auditRepository.findById(auditId);

        return audit;
    }

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
