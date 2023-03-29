package net.filonova.project.notes.exception;

import lombok.Data;
import net.filonova.project.notes.exception.NotesErrorCode;

@Data
public class Error {
    NotesErrorCode errorCode;
    String field;
    String message;
}
