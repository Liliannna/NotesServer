package net.filonova.project.notes.dto.request;

import lombok.Value;

@Value
public class LoginDtoRequest {
    String login;
    String password;
}
