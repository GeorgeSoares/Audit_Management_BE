package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

/**
 * This DTO is used to edit the information of an existing finding. This information of this DTO overwrites the
 * information of an existing Finding object which will be persisted in the database by the finding service.
 *
 * @param assessment
 * @param findingTitle
 * @param status
 * @param findingDescription
 * @param rootCause
 * @param responsiblePerson
 * @param lastEditor
 * @param actions
 * @param closedAt
 */

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
