package net.filonova.project.notes.dao;

import net.filonova.project.notes.model.Comment;
import net.filonova.project.notes.model.Note;
import net.filonova.project.notes.model.User;

import java.util.List;

public interface CommentDao {
    Comment insert(User user, Note note, Comment comment);
    Comment update(Comment comment, User user);
    Comment getById(int idComment);
    List<Comment> getAllCommentsByIdUser(int idUser);
    List<Comment> getAllCommentsByIdNoteRevision(int idNoteRevision);
    void deleteById(Comment comment, User user);
    void deleteAllCommentsByNoteRevision(int idNoteRevision);
    void deleteAllCommentsByUser(int idUser);
    void deleteAll();
}
