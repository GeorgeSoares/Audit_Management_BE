package org.test.audit_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.audit_management.models.Auditor;

@Repository
public interface AuditorRepository extends JpaRepository<Auditor, Long> {
}
