package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

public record AuditRequestDTO(@NotNull Long auditBegin,
                              @NotNull Long auditEnd,
                              @NotNull String leadAuditor,
                              @NotNull String coAuditors,
                              @NotNull String auditedCompany,
                              @NotNull String auditedDepartment) {
}
