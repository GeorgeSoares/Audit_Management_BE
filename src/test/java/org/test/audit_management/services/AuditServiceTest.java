package org.test.audit_management.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.test.audit_management.repositories.AuditRepository;
import org.test.audit_management.repositories.AuditorRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AuditServiceTest {

    @Mock
    AuditRepository auditRepository;
    @Mock
    private AuditorRepository auditorRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createNewAudit() {
    }

    @Test
    void listAudits() {
    }

    @Test
    void getAuditSuccessCase() {
    }

    @Test
    void getAuditFailCase() {
    }

    @Test
    void updateExistingAuditSuccessCase() {
    }

    @Test
    void updateExistingAuditFailCase() {
    }

    @Test
    void deleteAuditSuccessCase() {
    }

    @Test
    void deleteAuditFailCase() {
    }

}