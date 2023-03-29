package net.filonova.project.notes.dto.request;

import lombok.Value;

@Value
public class AddCommentDtoRequest {
    String body;
    int idNote;
}
