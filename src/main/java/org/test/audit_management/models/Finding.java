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

    public Finding(NewFindingDTO newFindingDTO, Audit audit) {
        this.audit = audit;
        this.assessment = newFindingDTO.assessment();
        this.findingTitle = newFindingDTO.findingTitle();
        this.findingDescription = newFindingDTO.findingDescription();
        this.responsiblePerson = newFindingDTO.responsiblePerson();
        this.createdAt = LocalDateTime.now();
    }

    public static LocalDateTime convertMllsTimeStamp(long stamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(stamp),
                ZoneId.systemDefault()
        );
        return localDateTime;
    }

    public void updateFields(FindingEditDTO findingEditDTO) {
        this.assessment = findingEditDTO.assessment();
        this.findingTitle = findingEditDTO.findingTitle();
        this.status = findingEditDTO.status();
        this.findingDescription = findingEditDTO.findingDescription();
        this.responsiblePerson = findingEditDTO.responsiblePerson();
        this.lastEditor = findingEditDTO.lastEditor();
        this.updatedAt = LocalDateTime.now();

        if (!findingEditDTO.rootCause().isEmpty()) {
            this.rootCause = findingEditDTO.rootCause();
        }

        if (!findingEditDTO.actions().isEmpty()) {
            this.actions = findingEditDTO.actions();
        }

        if (findingEditDTO.closedAt() != 0) {
            this.closedAt = Finding.convertMllsTimeStamp(findingEditDTO.closedAt());
        }
    }
}
