package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AuditEditDTO(@NotNull long auditBegin,
                           @NotNull long auditEnd,
                           @NotNull String leadAuditor,
                           @NotNull String coAuditors,
                           @NotNull String auditedCompany,
                           @NotNull String auditedDepartment,
                           @NotNull String auditStatus) {
}
