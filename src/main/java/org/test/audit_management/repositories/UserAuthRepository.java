package org.test.audit_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.audit_management.models.UserAuth;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository <UserAuth, Long> {
    Optional<UserAuth> findByEmail(String email);
}
