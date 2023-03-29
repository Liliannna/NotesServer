package net.filonova.project.notes.mappers;

import net.filonova.project.notes.model.Note;
import net.filonova.project.notes.model.NoteRevision;
import net.filonova.project.notes.model.Section;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO note (idUser, idSection, subject, datetimeCreated) VALUE (#{user.id}, #{section.id}, #{note.subject}, #{note.datetimeCreated})")
    @Options(useGeneratedKeys = true, keyProperty = "note.id", keyColumn = "id")
    Integer insert(@Param("user") User user, @Param("section") Section section, @Param("note") Note note);

    @Insert("INSERT INTO note_revision (idNote, body, revision) VALUE (#{note.id}, #{noteRevision.body}, #{noteRevision.revision})")
    @Options(useGeneratedKeys = true, keyProperty = "noteRevision.id", keyColumn = "id")
    Integer insertNoteRevision(@Param("note") Note note, @Param("noteRevision") NoteRevision noteRevision);

    @Update("UPDATE note SET idSection = #{section.id} WHERE id = #{note.id} AND idUser = #{user.id}")
    void update(@Param("user") User user, @Param("section") Section section, @Param("note") Note note);

    @Select("SELECT * FROM note WHERE id = #{idNote}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "noteRevisions", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNoteRevisionByIdNote", fetchType = FetchType.LAZY))
    })
    Note getById(int idNote);

    @Select("SELECT * FROM note")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "noteRevisions", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNoteRevisionByIdNote", fetchType = FetchType.LAZY))
    })
    List<Note> getAll();

    @Select("SELECT * FROM note WHERE idSection = #{idSection}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "noteRevisions", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNoteRevisionByIdNote", fetchType = FetchType.LAZY))
    })
    List<Note> getNotesByIdSection(int idSection);

    @Select("SELECT * FROM note WHERE idUser = #{idUser}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "noteRevisions", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNoteRevisionByIdNote", fetchType = FetchType.LAZY))
    })
    List<Note> getNotesByIdUser(int idUser);

    @Select("SELECT * FROM note WHERE rating = #{rating}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "noteRevisions", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNoteRevisionByIdNote", fetchType = FetchType.LAZY))
    })
    List<Note> getNotesByRating(double rating);

    @Insert("INSERT INTO note_rating (idNote, idUser, rating) VALUE (#{note.id}, (SELECT idUser FROM note WHERE id = #{note.id}), #{rating})")
    void addRating(@Param("note") Note note, double rating);

    @Update("UPDATE note SET rating = (SELECT (SELECT SUM(rating) FROM note_rating WHERE idNote = #{id}) / (SELECT COUNT(*) FROM note_rating WHERE idNote = #{id})) WHERE id = #{id}")
    void ratingNote(Note note);

    @Select("SELECT * FROM user WHERE id = (SELECT idUser FROM note WHERE id = #{id})")
    User getIdUserByIdNote(Note note);

    @Select("SELECT * FROM note_revision WHERE idNote = #{idNote} ORDER BY id DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getAllCommentsByIdNoteRevision", fetchType = FetchType.LAZY))
    })
    List<NoteRevision> getNoteRevisionByIdNote(int idNote);

    @Delete("DELETE FROM note WHERE id = #{note.id} AND idUser = #{user.id}")
    void deleteNote(@Param("note") Note note, @Param("user") User user);

    @Delete("DELETE FROM note WHERE idUser = #{idUser}")
    void deleteAllNoteByUser(int idUser);

    @Delete("DELETE FROM note WHERE idSection = #{idSection}")
    void deleteAllNoteBySection(int idSection);

    @Delete("DELETE FROM note")
    void deleteAll();
}
