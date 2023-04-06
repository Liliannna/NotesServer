package net.filonova.project.notes.endpoint;

import net.filonova.project.notes.dto.response.UserDtoResponse;
import net.filonova.project.notes.dto.request.UserRegistrationDtoRequest;
import net.filonova.project.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class AccountsEndpoint {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDtoResponse registrationUser(@RequestBody UserRegistrationDtoRequest dtoRequest, HttpServletResponse response) {
        return userService.registration(dtoRequest, response);
    }


   /* @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
    }*/

}
