package net.filonova.project.notes.dto.converter;

import javax.annotation.processing.Generated;
import net.filonova.project.notes.dto.request.UserRegistrationDtoRequest;
import net.filonova.project.notes.dto.response.UserDtoResponse;
import net.filonova.project.notes.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-06T17:14:20+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl extends UserDtoMapper {

    @Override
    public User userDtoRequestToUser(UserRegistrationDtoRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setLogin( request.getLogin() );
        user.setPassword( request.getPassword() );
        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setPatronymic( request.getPatronymic() );

        user.setRegistration( java.time.LocalDateTime.now() );

        return user;
    }

    @Override
    public UserDtoResponse userToUserDtoResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoResponse userDtoResponse = new UserDtoResponse();

        userDtoResponse.setFirstName( user.getFirstName() );
        userDtoResponse.setLastName( user.getLastName() );
        userDtoResponse.setPatronymic( user.getPatronymic() );
        userDtoResponse.setLogin( user.getLogin() );

        return userDtoResponse;
    }
}
