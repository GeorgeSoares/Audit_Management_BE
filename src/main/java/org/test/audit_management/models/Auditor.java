package org.test.audit_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Table(name = "auditors")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Auditor {

    @Id
    @GeneratedValue
    private UUID auditorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
