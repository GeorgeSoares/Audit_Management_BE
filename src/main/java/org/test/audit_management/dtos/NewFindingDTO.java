package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewFindingDTO(@NotNull String assessment,
                            @NotNull String findingTitle,
                            @NotNull String status,
                            @NotNull String findingDescription,
                            String rootCause,
                            @NotNull String responsiblePerson,
                            @NotNull String lastEditor,
                            String actions) {
}
