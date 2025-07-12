package org.test.audit_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.audit_management.models.Audit;

import java.util.UUID;

/**
 * Repository interface for performing CRUD operations on Audit entities.
 *
 * This interface extends JpaRepository, which provides methods for standard database
 * interactions such as saving, finding, deleting, and updating entities.
 *
 * The entity managed here is the Audit, and its primary key type is UUID.
 */
@Repository
public interface AuditRepository extends JpaRepository<Audit, UUID> {
}
