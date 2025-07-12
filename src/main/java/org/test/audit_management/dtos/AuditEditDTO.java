package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * This DTO is used during to edit an existing audit. The information of this DTO is used to overwrite the current information
 * in an audit object and then persisted in the database.
 *
 * @param auditBegin
 * @param auditEnd
 * @param leadAuditor
 * @param coAuditors
 * @param auditedCompany
 * @param auditedDepartment
 * @param auditStatus
 */

public record AuditEditDTO(@NotNull long auditBegin,
                           @NotNull long auditEnd,
                           @NotNull String leadAuditor,
                           @NotNull String coAuditors,
                           @NotNull String auditedCompany,
                           @NotNull String auditedDepartment,
                           @NotNull String auditStatus) {
}
