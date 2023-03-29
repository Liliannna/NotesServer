package net.filonova.project.notes.endpoint;

import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.dto.response.ErrorDtoResponse;
import net.filonova.project.notes.service.UserService;
import net.filonova.project.notes.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping("/api/account")
public class UserEndpoint {
    @Autowired
    private UserService userService;

    @Autowired
    private Converter converter;

    @Value("${cookie_name}")
    private String cookie_name;

    @GetMapping
    public ResponseEntity<Object> getUserInfo(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1 != null && cookie1.getName().equals(cookie_name)).findFirst().get();
            return ResponseEntity.ok(converter.userToUserDtoResponse(userService.getUserByIdSession(Integer.parseInt(cookie.getValue()))));
        } catch (NotesException ex) {
            return ResponseEntity.ok(new ErrorDtoResponse(converter.notesExceptionToError(ex)));
        }
    }
}
