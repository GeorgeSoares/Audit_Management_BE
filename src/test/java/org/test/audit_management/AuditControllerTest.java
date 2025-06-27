package org.test.audit_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.test.audit_management.controllers.AuditController;
import org.test.audit_management.dtos.AuditEditDTO;
import org.test.audit_management.dtos.AuditRequestDTO;
import org.test.audit_management.models.Audit;
import org.test.audit_management.services.AuditService;
import org.test.audit_management.repositories.AuditRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuditController.class)
class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService auditService;

    @MockBean
    private AuditRepository auditRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnHello() throws Exception {
        mockMvc.perform(get("/api/v1/audits/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    void shouldReturnAuditIfExists() throws Exception {
        UUID id = UUID.randomUUID();
        Audit audit = new Audit();
        audit.setAuditId(id);
        audit.setAuditedCompany("ACME Inc.");

        Mockito.when(auditService.getAudit(id)).thenReturn(Optional.of(audit));

        mockMvc.perform(get("/api/v1/audits/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auditedCompany").value("ACME Inc."));
    }

    @Test
    void shouldReturnEmptyIfAuditNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(auditService.getAudit(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/audits/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Error: Audit not found."));
    }

    @Test
    void shouldCreateAuditSuccessfully() throws Exception {
        AuditRequestDTO dto = new AuditRequestDTO(
                1700000000000L,
                1700000000000L,
                "Lead Auditor",
                "Co Auditor",
                "Company",
                "Department"
        );

        Mockito.when(auditService.createNewAudit(Mockito.any())).thenReturn(true);

        mockMvc.perform(post("/api/v1/audits/createAudit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Audit successfully created"));
    }

    @Test
    void shouldDeleteAuditSuccessfully() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(auditService.deleteAudit(id)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/audits/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("Audit deleted"));
    }

    @Test
    void shouldFailToDeleteAudit() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(auditService.deleteAudit(id)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/audits/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Audit not deleted"));
    }
}

