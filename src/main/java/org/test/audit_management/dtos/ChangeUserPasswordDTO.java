package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

public record ChangeUserPasswordDTO(@NotNull String currentPassword,
                                    @NotNull String NewPassword) {
}
