package org.test.audit_management;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.test.audit_management.controllers.FindingController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.test.audit_management.dtos.FindingEditDTO;
import org.test.audit_management.dtos.NewFindingDTO;
import org.test.audit_management.models.Finding;
import org.test.audit_management.repositories.AuditRepository;
import org.test.audit_management.repositories.FindingsRepository;
import org.test.audit_management.services.AuditService;
import org.test.audit_management.services.FindingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FindingController.class)
public class FindingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindingService findingService;

    @MockBean
    private FindingsRepository findingsRepository;

    @MockBean
    private AuditRepository auditRepository;

    @MockBean
    private AuditService auditService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateNewFindingSuccessfully() throws Exception {
        UUID auditId = UUID.randomUUID();

        NewFindingDTO dto = new NewFindingDTO(
                "Observation", "Missing checklist", "Checklists not used in last audit", "John", "Open", "Analysis missing", "Alice", "Add training"
        );

        Mockito.when(findingService.createFinding(Mockito.any(), Mockito.eq(auditId))).thenReturn(true);

        mockMvc.perform(post("/api/v1/audits/" + auditId + "/newFinding")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Finding successfully created"));
    }

    @Test
    void shouldReturnBadRequestWhenCreateFindingFails() throws Exception {
        UUID auditId = UUID.randomUUID();

        NewFindingDTO dto = new NewFindingDTO("X", "Y", "Z", "W", "Open", "", "", "");

        Mockito.when(findingService.createFinding(Mockito.any(), Mockito.eq(auditId))).thenReturn(false);

        mockMvc.perform(post("/api/v1/audits/" + auditId + "/newFinding")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Finding not created"));
    }

    @Test
    void shouldEditFindingSuccessfully() throws Exception {
        long findingId = 1L;
        FindingEditDTO dto = new FindingEditDTO("Observation", "Title", "Open", "Desc", "Root cause", "GS", "GS", "Actions", 1700000000000L);

        Finding finding = new Finding();
        Mockito.when(findingsRepository.findById(findingId)).thenReturn(Optional.of(finding));
        Mockito.when(findingService.editFinding(dto, findingId)).thenReturn(true);

        mockMvc.perform(put("/api/v1/audits/xyz/findings/" + findingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Finding updated"));
    }

    @Test
    void shouldReturnBadRequestWhenEditingNonexistentFinding() throws Exception {
        long findingId = 1L;
        FindingEditDTO dto = new FindingEditDTO("Observation", "Title", "Open", "Desc", "Root cause", "GS", "GS", "Actions", 1700000000000L);

        Mockito.when(findingsRepository.findById(findingId)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/audits/xyz/findings/" + findingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Finding not found"));
    }

    @Test
    void shouldDeleteFinding() throws Exception {
        long findingId = 123L;

        Mockito.when(findingService.deleteFinding(findingId))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body(null));

        mockMvc.perform(delete("/api/v1/audits/xyz/findings/" + findingId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetSingleFinding() throws Exception {
        long findingId = 456L;
        Finding f = new Finding();
        f.setFindingNumber(findingId);
        f.setFindingTitle("Checklists");

        Mockito.when(findingService.getFinding(findingId)).thenReturn(Optional.of(f));

        mockMvc.perform(get("/api/v1/audits/xyz/findings/" + findingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.findingTitle").value("Checklists"));
    }

    @Test
    void shouldListAllFindings() throws Exception {
        List<Finding> list = Arrays.asList(new Finding(), new Finding());
        Mockito.when(findingsRepository.findAll()).thenReturn(list);

        mockMvc.perform(get("/api/v1/audits/findings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldListFindingsByAudit() throws Exception {
        UUID auditId = UUID.randomUUID();
        List<Finding> list = Arrays.asList(new Finding(), new Finding());

        Mockito.when(findingService.listAuditFindings(auditId)).thenReturn(list);

        mockMvc.perform(get("/api/v1/audits/" + auditId + "/findings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
