package net.filonova.project.notes.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NoteRevision {
    private int id;
    private String body;
    private int revision;
    private List<Comment> comments;

    public NoteRevision() {
    }

    public NoteRevision(int id, String body, int revision, List<Comment> comments) {
        this.id = id;
        this.body = body;
        this.revision = revision;
        this.comments = comments;
    }

    public NoteRevision(String body, int revision) {
       this(0, body, revision, new ArrayList<>());
    }
}
