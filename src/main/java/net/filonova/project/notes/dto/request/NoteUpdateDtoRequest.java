package net.filonova.project.notes.dto.request;

import lombok.Value;

@Value
public class NoteUpdateDtoRequest {
    String body;
    int idSection;
}
