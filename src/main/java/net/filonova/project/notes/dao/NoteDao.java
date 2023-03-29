package net.filonova.project.notes.dao;

import net.filonova.project.notes.model.Note;
import net.filonova.project.notes.model.Section;
import net.filonova.project.notes.model.User;

import java.util.List;

public interface NoteDao {
    Note insert(User user, Section section, Note note);
    Note insertNoteRevision(Note note);
    Note update(Note note, Section section, User user);
    Note getById(int idNote);
    List<Note> getAll();
    List<Note> getAllNotesByIdUser(int idUser);
    List<Note> getNotesByIdSection(int idSection);
    List<Note> getAllNotesByRating(double rating);
    void addRating(Note note, double rating);
    void deleteNote(Note note, User user);
    void deleteAllNoteByUser(int idUser);
    void deleteNotesBySection(int idSection);
    void deleteAll();
}
