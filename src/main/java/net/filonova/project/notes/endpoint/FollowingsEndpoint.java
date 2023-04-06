package net.filonova.project.notes.endpoint;

import net.filonova.project.notes.dto.request.AddIgnoreOrFollowingDtoRequest;
import net.filonova.project.notes.dto.response.ErrorDtoResponse;
import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.service.UserService;
import net.filonova.project.notes.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping("/api/followings")
public class FollowingsEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter converter;

    @Value("${cookie_name}")
    private String cookie_name;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addFollowing(@RequestBody AddIgnoreOrFollowingDtoRequest dtoRequest, HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1 != null && cookie1.getName().equals(cookie_name)).findFirst().get();
            userService.addFollowing(dtoRequest, Integer.parseInt(cookie.getValue()));
            return ResponseEntity.ok("");
        } catch (NotesException ex) {
            return ResponseEntity.ok(new ErrorDtoResponse(converter.notesExceptionToError(ex)));
        }
    }

    @DeleteMapping(value = "{loginUser}")
    public ResponseEntity<Object> deleteFollowing(@PathVariable String loginUser, HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1 != null && cookie1.getName().equals(cookie_name)).findFirst().get();
            userService.deleteFollowing(loginUser, Integer.parseInt(cookie.getValue()));
            return ResponseEntity.ok("");
        } catch (NotesException ex) {
            return ResponseEntity.ok(new ErrorDtoResponse(converter.notesExceptionToError(ex)));
        }
    }
}
