package net.filonova.project.notes.utils;

import net.filonova.project.notes.exception.NotesErrorCode;
import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.dto.request.UserRegistrationDtoRequest;
import net.filonova.project.notes.dto.request.UserUpdateDtoRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {

    @Value("${max_name_length}")
    private int max_name_length;

    @Value("${min_password_length}")
    private int min_password_length;

    private static final String LOGIN_PATTERN = "^[A-Za-zА-Яа-я0-9]*$";
    private final Pattern patternLogin = Pattern.compile(LOGIN_PATTERN);
    private static final String NAME_PATTERN = "^[-A-Za-zА-Яа-я0-9\\s]*$";
    private final Pattern patternName = Pattern.compile(NAME_PATTERN);
    private Matcher matcher;

    public Validator() {
    }

    public void registrationUser(UserRegistrationDtoRequest request) {
        validateName(request.getFirstName());
        validateName(request.getLastName());
        if(request.getPatronymic() != null){
            validateName(request.getPatronymic());
        }
        validateLogin(request.getLogin());
        validatePassword(request.getPassword());
    }

    public void updateUser(UserUpdateDtoRequest request) {
        validateName(request.getFirstName());
        validateName(request.getLastName());
        validateName(request.getPatronymic());
        validatePassword(request.getNewPassword());
        validatePassword(request.getOldPassword());
    }

    public void validatePassword(String password) {
        if (password.length() < min_password_length ) {
            throw new NotesException(NotesErrorCode.WRONG_PASSWORD, "Password", "Min password length: " + min_password_length);
        }
        if(password.length() > max_name_length) {
            throw new NotesException(NotesErrorCode.WRONG_PASSWORD, "Password", "Max password length: " + max_name_length);
        }
        if(password.isEmpty()) {
            throw new NotesException(NotesErrorCode.EMPTY_FIELD, "Password", "Enter password");
        }

    }

    public void validateName(String name) {
        matcher = patternName.matcher(name);
        if (!matcher.matches()) {
            throw new NotesException(NotesErrorCode.WRONG, "Field", "Incorrect: " + name);
        }
        if(name.isEmpty()) {
            throw new NotesException(NotesErrorCode.EMPTY_FIELD, "Field", "Cannot be empty");
        }
        if(name.length() > max_name_length) {
            throw new NotesException(NotesErrorCode.WRONG, "Field", "Max length: " + max_name_length);
        }
    }

    public void validateLogin(String login) {
        matcher = patternLogin.matcher(login);
        if (!matcher.matches()) {
            throw new NotesException(NotesErrorCode.WRONG, "Field", "Incorrect: " + login);
        }
        if(login.isEmpty()) {
            throw new NotesException(NotesErrorCode.EMPTY_FIELD, "Field", "Cannot be empty");
        }
        if(login.length() > max_name_length) {
            throw new NotesException(NotesErrorCode.WRONG, "Field", "Max length: " + max_name_length);
        }
    }
}
