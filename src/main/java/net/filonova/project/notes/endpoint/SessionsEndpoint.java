package net.filonova.project.notes.endpoint;

import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.dto.request.LoginDtoRequest;
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
@RequestMapping("/api/sessions")
public class SessionsEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter converter;

    @Value("${cookie_name}")
    private String cookie_name;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody LoginDtoRequest dtoRequest, HttpServletResponse response) {
        try {
            User user = userService.login(dtoRequest);
            Cookie cookie = new Cookie(cookie_name, String.valueOf(user.getIdSession()));
            response.addCookie(cookie);
            return ResponseEntity.ok("");
        } catch (NotesException ex) {
            return ResponseEntity.ok(new ErrorDtoResponse(converter.notesExceptionToError(ex)));
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1 != null && cookie1.getName().equals(cookie_name)).findFirst().get();
            userService.logout(Integer.parseInt(cookie.getValue()));
            return ResponseEntity.ok("");
        } catch (NotesException ex) {
            return ResponseEntity.ok(new ErrorDtoResponse(converter.notesExceptionToError(ex)));
        }
    }
}
