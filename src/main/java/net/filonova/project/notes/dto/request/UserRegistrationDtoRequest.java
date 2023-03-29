package net.filonova.project.notes.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDtoRequest {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String password;

    public UserRegistrationDtoRequest() {
    }

    public UserRegistrationDtoRequest(String firstName, String lastName, String patronymic, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
    }

    public UserRegistrationDtoRequest(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = null;
        this.login = login;
        this.password = password;
    }

}
