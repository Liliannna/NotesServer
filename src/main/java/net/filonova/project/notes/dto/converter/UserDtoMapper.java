package net.filonova.project.notes.dto.converter;

import net.filonova.project.notes.dto.request.UserRegistrationDtoRequest;
import net.filonova.project.notes.dto.response.UserDtoResponse;
import net.filonova.project.notes.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class UserDtoMapper {
    public static final UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    @Mapping(target = "registration", expression = "java(java.time.LocalDateTime.now())")
    public abstract User userDtoRequestToUser(UserRegistrationDtoRequest request);

    public abstract UserDtoResponse userToUserDtoResponse(User user);
}
