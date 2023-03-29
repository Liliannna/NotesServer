package net.filonova.project.notes.mappers;

import net.filonova.project.notes.model.Comment;
import net.filonova.project.notes.model.NoteRevision;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comment (idUser, idNoteRevision, comment, datetimeCreated) VALUE (#{user.id}, #{noteRevision.id}, #{comment.comment}, #{comment.datetimeCreated})")
    @Options(useGeneratedKeys = true, keyProperty = "comment.id", keyColumn = "id")
    Integer insert(@Param("user") User user, @Param("noteRevision") NoteRevision noteRevision, @Param("comment") Comment comment);

    @Update("UPDATE comment SET comment = #{comment.comment} WHERE id = #{comment.id} AND idUser = #{user.id}")
    void update(@Param("comment") Comment comment, @Param("user") User user);

    @Select("SELECT * FROM comment WHERE id = #{idComment}")
    Comment getById(int idComment);

    @Select("SELECT * FROM comment WHERE idUser = #{idUser}")
    List<Comment> getCommentsByIdUser(int idUser);

    @Select("SELECT * FROM comment WHERE idNoteRevision = #{idNoteRevision}")
    List<Comment> getAllCommentsByIdNoteRevision(int idNoteRevision);

    @Delete("DELETE FROM comment WHERE id = #{comment.id} AND idUser = #{user.id}")
    void deleteById(@Param("comment") Comment comment, @Param("user") User user);

    @Delete("DELETE FROM comment WHERE idNoteRevision = #{idNoteRevision}")
    void deleteAllCommentsByNoteRevision(int idNoteRevision);

    @Delete("DELETE FROM comment WHERE idUser = #{idUser}")
    void deleteAllCommentsByUser(int idUser);

    @Delete("DELETE FROM comment")
    void deleteAll();
}
