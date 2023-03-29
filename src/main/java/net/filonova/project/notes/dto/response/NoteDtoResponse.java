package net.filonova.project.notes.dto.response;

import lombok.Data;

@Data
public class NoteDtoResponse {
    private int id;
    private String subject;
    private String body;
    private int idSection;
    private int idUser;
    private String datetimeCreated;
    private int numberRevision;
}
