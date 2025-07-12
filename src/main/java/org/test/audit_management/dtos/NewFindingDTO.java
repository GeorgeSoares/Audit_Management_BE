package org.test.audit_management.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * This DTO is used to create a new finding. Once a new request is sent by the front end, JSON received wraps the information
 * in a DTO and transfers it to the Finding Service.
 *
 * @param assessment
 * @param findingTitle
 * @param status
 * @param findingDescription
 * @param rootCause
 * @param responsiblePerson
 * @param lastEditor
 * @param actions
 */

public record NewFindingDTO(@NotNull String assessment,
                            @NotNull String findingTitle,
                            @NotNull String status,
                            @NotNull String findingDescription,
                            String rootCause,
                            @NotNull String responsiblePerson,
                            @NotNull String lastEditor,
                            String actions) {
}
