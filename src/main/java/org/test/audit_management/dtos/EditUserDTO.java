package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

public record EditUserDTO(@NotNull long userId,
                          @NotNull String firstName,
                          @NotNull String lastName) {
}
