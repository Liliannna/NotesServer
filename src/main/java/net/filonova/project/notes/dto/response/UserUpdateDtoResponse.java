package net.filonova.project.notes.dto.response;

import lombok.Data;

@Data
public class UserUpdateDtoResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
}
