package net.filonova.project.notes.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private int id;
    private String comment;
    private LocalDateTime datetimeCreated;

    public Comment() {
    }

    public Comment(String comment, LocalDateTime datetimeCreated) {
        this(0, comment, datetimeCreated);
    }

    public Comment(int id, String comment, LocalDateTime datetimeCreated) {
        this.id = id;
        this.comment = comment;
        this.datetimeCreated = datetimeCreated;
    }
}
