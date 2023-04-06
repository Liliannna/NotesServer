package net.filonova.project.notes.dto.converter;

import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.model.User;
import net.filonova.project.notes.dto.request.LoginDtoRequest;
import net.filonova.project.notes.dto.request.UserRegistrationDtoRequest;
import net.filonova.project.notes.dto.request.UserUpdateDtoRequest;
import net.filonova.project.notes.dto.response.UserDtoResponse;
import net.filonova.project.notes.dto.response.UserUpdateDtoResponse;
import net.filonova.project.notes.exception.Error;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Converter {

    public User userDtoRequestToUser(UserRegistrationDtoRequest request) {
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setRegistration(getDateTime());
        return user;
    }

    public UserDtoResponse userToUserDtoResponse(User user) {
        UserDtoResponse response = new UserDtoResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPatronymic(user.getPatronymic());
        response.setLogin(user.getLogin());
        return response;
    }

    public User userUpdateDtoRequestToUser(UserUpdateDtoRequest request) {
        User user = new User();
        user.setPassword(request.getNewPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setRegistration(getDateTime());
        return user;
    }

    public UserUpdateDtoResponse userToUserUpdateDtoResponse(User user) {
        UserUpdateDtoResponse response = new UserUpdateDtoResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPatronymic(user.getPatronymic());
        response.setLogin(user.getLogin());
        return response;
    }

    public User userLoginDtoRequestToUser(LoginDtoRequest request) {
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        return user;
    }

    public Error notesExceptionToError(NotesException exception) {
        Error error = new Error();
        error.setErrorCode(exception.getErrorCode());
        error.setField(exception.getField());
        error.setMessage(exception.getMessage());
        return  error;
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.now().withNano(0);
    }
}
