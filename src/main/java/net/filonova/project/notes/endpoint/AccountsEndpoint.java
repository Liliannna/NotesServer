package net.filonova.project.notes.endpoint;

import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.dto.request.DeleteUserDtoRequest;
import net.filonova.project.notes.dto.request.SearchUsersDtoRequest;
import net.filonova.project.notes.dto.request.UserRegistrationDtoRequest;
import net.filonova.project.notes.dto.request.UserUpdateDtoRequest;
import net.filonova.project.notes.dto.response.ErrorDtoResponse;
import net.filonova.project.notes.model.User;
import net.filonova.project.notes.service.UserService;
import net.filonova.project.notes.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/api/accounts")
public class AccountsEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter converter;

    @Value("${cookie_name}")
    private String cookie_name;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrationUser(@RequestBody UserRegistrationDtoRequest dtoRequest, HttpServletResponse response) {
        try {
            User user = userService.registration(dtoRequest);
            Cookie cookie = new Cookie(cookie_name, String.valueOf(user.getIdSession()));
            response.addCookie(cookie);
            return ResponseEntity.ok(converter.userToUserDtoResponse(user));
        } catch (NotesException ex) {
            return ResponseEntity.ok(new ErrorDtoResponse(converter.notesExceptionToError(ex)));
        }
    }


    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestBody DeleteUserDtoRequest dtoRequest, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1 != null && cookie1.getName().equals(cookie_name)).findFirst().get();
        userService.deleteUser(dtoRequest, Integer.parseInt(cookie.getValue()));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateDtoRequest dtoRequest, HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1 != null && cookie1.getName().equals(cookie_name)).findFirst().get();
            User user = userService.updateUser(dtoRequest, Integer.parseInt(cookie.getValue()));
            return ResponseEntity.ok(converter.userToUserUpdateDtoResponse(user));
        } catch (NotesException ex) {
            return ResponseEntity.ok(new ErrorDtoResponse(converter.notesExceptionToError(ex)));
        }
    }

    @PutMapping(value = "{idUser}/super")
    public void addRole(@PathVariable int idUser, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1 != null && cookie1.getName().equals(cookie_name)).findFirst().get();
        userService.addRole(idUser, Integer.parseInt(cookie.getValue()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUsersByRequest(@RequestBody SearchUsersDtoRequest request) {
        try {
            return null;
        } catch (NotesException ex) {
            return ResponseEntity.ok(new ErrorDtoResponse(converter.notesExceptionToError(ex)));
        }
    }

}
