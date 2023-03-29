package net.filonova.project.notes.dao;

import net.filonova.project.notes.model.User;

public interface AdminDao {
    void addRole(int idUser);
    User getAdminByIdSession(int idSessionForAdmin);
    void deleteSection(int idSection);
    void deleteNote(int idNote);
    void deleteComment(int idComment);
    void changeNoteSection(int idNote, int idSection);
}
