package net.filonova.project.notes.dto.response;

import lombok.Data;

@Data
public class UserDtoResponse {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
}
