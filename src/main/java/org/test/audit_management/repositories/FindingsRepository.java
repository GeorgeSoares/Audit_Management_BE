package org.test.audit_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.audit_management.models.Audit;
import org.test.audit_management.models.Finding;

import java.util.List;
import java.util.UUID;

@Repository
public interface FindingsRepository extends JpaRepository<Finding, Long> {
    List<Finding> findByAudit(Audit audit_id);
}
