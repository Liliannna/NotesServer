package net.filonova.project.notes.dto.response;

import lombok.Value;
import net.filonova.project.notes.exception.Error;

@Value
public class ErrorDtoResponse {
    Error errors;
}
