package net.filonova.project.notes.dto.response;

import lombok.Data;

@Data
public class CommentDtoResponse {
    private int id;
    private String body;
    private int idUser;
    private int idNote;
    private int numberRevision;
    private String datetimeCreated;
}
