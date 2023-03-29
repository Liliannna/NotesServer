package net.filonova.project.notes.dto.request;

import lombok.Value;

@Value
public class UserUpdateDtoRequest {

    String firstName;
    String lastName;
    String patronymic;
    String oldPassword;
    String newPassword;
}
