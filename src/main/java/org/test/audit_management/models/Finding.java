package org.test.audit_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.test.audit_management.dtos.FindingEditDTO;
import org.test.audit_management.dtos.NewFindingDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * This class represents the Findings entity of the application. Findings are closely related to audits
 * as they are the core result of Audits.
 */
@Table(name = "finding")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Finding {

    @Id
    @GeneratedValue
    private long findingNumber;

    @ManyToOne
    @JoinColumn(name = "audit_id", referencedColumnName = "auditId")
    @JsonIgnore
    private Audit audit;

    @Column(name = "assessment")
    private String assessment;

    @Column(name = "title")
    private String findingTitle;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String findingDescription;

    @Column(name = "root_cause")
    private String rootCause;

    @Column(name = "responsible person")
    private String responsiblePerson;

    @Column(name = "last editor")
    private String lastEditor;

    @Column(name = "actions")
    private String actions;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    /**
     * This is a constructor that is used during the creation of a finding, it is called on the Finding Service
     * and associates the finding to the audit.
     *
     * @param newFindingDTO New finding dto
     * @param audit Audit to which the finding will be related
     */
    public Finding(NewFindingDTO newFindingDTO, Audit audit) {
        this.audit = audit;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (newFindingDTO.assessment() != null) { this.assessment = newFindingDTO.assessment(); }
        if (newFindingDTO.status() != null) { this.status = newFindingDTO.status(); }
        if (newFindingDTO.findingTitle() != null) { this.findingTitle = newFindingDTO.findingTitle(); }
        if (newFindingDTO.findingDescription() != null) { this.findingDescription = newFindingDTO.findingDescription(); }
        if (newFindingDTO.responsiblePerson() != null) { this.responsiblePerson = newFindingDTO.responsiblePerson(); }
        if (newFindingDTO.rootCause() != null) { this.rootCause = newFindingDTO.rootCause(); }
        if (newFindingDTO.actions() != null) { this.actions = newFindingDTO.actions(); }
    }

    /**
     * This method converts an input in milliseconds (timestamp) in a localDateTime object.
     *
     * @param stamp Timestamp in milliseconds.
     * @return localDateTime object.
     */
    public static LocalDateTime convertMllsTimeStamp(long stamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(stamp),
                ZoneId.systemDefault()
        );
        return localDateTime;
    }


    /**
     * This method is used during the update of an existing finding.
     *
     * @param findingEditDTO DTO with the new information of the audit.
     */
    public void updateFields(FindingEditDTO findingEditDTO) {
        if (findingEditDTO.assessment() != null) { this.assessment = findingEditDTO.assessment(); }
        if (findingEditDTO.status() != null) { this.status = findingEditDTO.status(); }
        if (findingEditDTO.findingTitle() != null) { this.findingTitle = findingEditDTO.findingTitle(); }
        if (findingEditDTO.findingDescription() != null) { this.findingDescription = findingEditDTO.findingDescription(); }
        if (findingEditDTO.responsiblePerson() != null) { this.responsiblePerson = findingEditDTO.responsiblePerson(); }
        if (findingEditDTO.actions() != null) { this.actions = findingEditDTO.actions(); }
        if (findingEditDTO.rootCause() != null) { this.rootCause = findingEditDTO.rootCause(); }

        this.updatedAt = LocalDateTime.now();

        if (findingEditDTO.status().equals("Closed")) {
            this.closedAt = LocalDateTime.now();
        }
    }
}
