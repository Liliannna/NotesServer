package net.filonova.project.notes.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Section {
    private int id;
    private String name;
    private List<Note> notes;

    public Section() {
    }

    public Section(int id, String name, List<Note> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public Section(String name) {
        this(0, name, new ArrayList<>());
    }

    public Section(int id, String name) {
        this(id, name, new ArrayList<>());
    }
}
