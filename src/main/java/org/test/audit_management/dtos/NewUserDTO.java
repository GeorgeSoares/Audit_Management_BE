package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

public record NewUserDTO(@NotNull String firstName,
                         @NotNull String lastName,
                         @NotNull String email,
                         @NotNull String password,
                         @NotNull String role) {
}
