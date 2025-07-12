package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

/**
 * This DTO is used to create a new audit. The information of the DTO is copied to an audit object. The id of the audit
 * is created automatically when a new instance of the Audit class is created.
 *
 * @param auditBegin
 * @param auditEnd
 * @param leadAuditor
 * @param coAuditors
 * @param auditedCompany
 * @param auditedDepartment
 */

public record AuditRequestDTO(@NotNull Long auditBegin,
                              @NotNull Long auditEnd,
                              @NotNull String leadAuditor,
                              @NotNull String coAuditors,
                              @NotNull String auditedCompany,
                              @NotNull String auditedDepartment) {
}
