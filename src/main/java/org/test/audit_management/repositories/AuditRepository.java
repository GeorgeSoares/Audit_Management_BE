package org.test.audit_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.audit_management.models.Audit;

import java.util.UUID;

@Repository
public interface AuditRepository extends JpaRepository<Audit, UUID> {
}
