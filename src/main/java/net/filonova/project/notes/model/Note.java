package net.filonova.project.notes.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Note {
    private int id;
    private String subject;
    private LocalDateTime datetimeCreated;
    private List<NoteRevision> noteRevisions;
    private double rating;

    public Note() {
    }

    public Note(String subject, LocalDateTime datetimeCreated, List<NoteRevision> noteRevisions) {
        this(0, subject, datetimeCreated, noteRevisions, 0.0);
    }

    public Note(int id, String subject, LocalDateTime datetimeCreated, List<NoteRevision> noteRevisions, double rating) {
        this.id = id;
        this.subject = subject;
        this.datetimeCreated = datetimeCreated;
        this.noteRevisions = noteRevisions;
        this.rating = rating;
    }
}
