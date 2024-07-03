package org.test.audit_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.util.UUID;

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

}
