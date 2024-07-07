package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

public record FindingEditDTO(@NotNull String assessment,
                             @NotNull String findingTitle,
                             @NotNull String status,
                             @NotNull String findingDescription,
                             String rootCause,
                             @NotNull String responsiblePerson,
                             @NotNull String lastEditor,
                             String actions,
                             long closedAt) {
}
