package net.filonova.project.notes.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDtoRequest {

    @NotNull(message = "not set")
    private String firstName;
    @NotNull(message = "not set")
    private String lastName;
    private String patronymic;
    @NotNull(message = "not set")
    private String login;
    @NotNull(message = "not set")
    private String password;

}
