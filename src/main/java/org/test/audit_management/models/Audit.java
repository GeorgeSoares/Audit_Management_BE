package org.test.audit_management.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

/**
 * This class represents the Audit entity. It has an auditId as primary key and other attributes that define an audit.
 */

@Table(name = "audits")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Audit {

    @Id
    @GeneratedValue
    private UUID auditId;

    @Column(name = "audit_begin")
    private LocalDateTime auditBegin;

    @Column(name = "audit_end")
    private LocalDateTime auditEnd;

    @Column(name = "lead_auditor")
    private String leadAuditor;

    @Column(name = "co_auditor")
    private String coAuditors;

    @Column(name = "audited_company")
    private String auditedCompany;

    @Column(name = "audited_department")
    private String auditedDepartment;

    @Column(name = "audit_status")
    private String auditStatus;

    @OneToMany(mappedBy = "audit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Finding> findings;

    @Column(name = "audit_createdAt")
    private LocalDateTime auditCreatedAt;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


    /**
     * This method converts the time stamp in milliseconds sent by the front end to a LocalDateTime object. The value of this LocalDateTime
     * will be saved on the database.
     *
     * @param stamp
     * @return LocalDateTime object corresponding the time stamp in milliseconds.
     */
    public static LocalDateTime convertMllsTimeStamp(long stamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(stamp),
                ZoneId.systemDefault()
        );
        return localDateTime;
    }
}
