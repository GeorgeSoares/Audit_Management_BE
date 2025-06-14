package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EditUserDTO(@NotNull UUID userId,
                          @NotNull String firstName,
                          @NotNull String lastName) {
}
