package org.test.audit_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.audit_management.models.Audit;
import org.test.audit_management.models.Finding;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Finding entities in the database.
 *
 * This interface extends JpaRepository, which provides standard CRUD methods.
 * It also defines a custom query method to retrieve findings associated with a specific audit.
 */
@Repository
public interface FindingsRepository extends JpaRepository<Finding, Long> {
    /**
     * Finds all findings related to a given audit.
     *
     * @param audit_id The Audit entity to search findings for.
     * @return A list of Finding objects associated with the given audit.
     */
    List<Finding> findByAudit(Audit audit_id);
}
