package net.filonova.project.notes.dto.request;

import lombok.Value;

@Value
public class AddNoteDtoRequest {
    String subject;
    String body;
    int idSection;
}
