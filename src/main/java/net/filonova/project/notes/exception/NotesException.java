package net.filonova.project.notes.exception;

import lombok.Value;

@Value
public class NotesException extends RuntimeException {
    NotesErrorCode errorCode;
    String field;
    String message;
}
