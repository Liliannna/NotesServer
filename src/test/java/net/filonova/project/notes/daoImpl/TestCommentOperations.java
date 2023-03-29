package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCommentOperations extends TestBase {
   /* @Test
    public void testInsertComment() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section, new Note("New Note", getDateTime(), listRevisions));

            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            Comment comment = insertComment(userDavid,  note, new Comment("new Comment", getDateTime()));
            Comment commentForDB = getByIdComment(comment.getId());
            assertEquals(comment, commentForDB);
            // REVU лишнее, и try вообще не нужен
            // если случится необработанное тестом исключение, он и так упадет
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testUpdateComment() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section, new Note("New Note", getDateTime(), listRevisions));

            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            Comment comment = insertComment(userDavid,  note, new Comment("new Comment", getDateTime()));

            comment.setComment("This is update comment");
            updateComment(comment, userDavid);
            Comment commentForDB = getByIdComment(comment.getId());
            assertEquals(comment, commentForDB);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertNullComment() {
        User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
        Section section = insertSection(userLiliana, "new Section");
        NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
        List<NoteRevision> listRevisions = new ArrayList<>();
        listRevisions.add(noteRevision);
        Note note = insertNote(userLiliana, section, new Note("New Note", getDateTime(), listRevisions));

        User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
        assertThrows(RuntimeException.class, () -> {
            insertComment(userDavid, note, new Comment(null, getDateTime()));
        });
    }

    @Test
    public void testGetCommentsByIdUser() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section, new Note("New Note", getDateTime(), listRevisions));

            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            Comment comment1 = insertComment(userDavid, note, new Comment("first Comment", getDateTime()));
            Comment comment2 = insertComment(userDavid, note, new Comment("second Comment", getDateTime()));

            List<Comment> comments = getAllCommentsByIdUser(userDavid.getId());
            assertEquals(comments.size(), 2);
            assertTrue(comments.contains(comment1));
            assertTrue(comments.contains(comment2));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetCommentByIdNoteRevision() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section, new Note("New Note", getDateTime(), listRevisions));

            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null,getDateTime());
            Comment comment1 = insertComment(userDavid, note, new Comment("first Comment", getDateTime()));
            Comment comment2 = insertComment(userDavid, note, new Comment("second Comment", getDateTime()));

            List<Comment> comments = getAllCommentsByIdNoteRevision(noteRevision.getId());
            assertEquals(comments.size(), 2);
            assertTrue(comments.contains(comment1));
            assertTrue(comments.contains(comment2));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteCommentById() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section, new Note("New Note", getDateTime(), listRevisions));

            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null,getDateTime());
            Comment comment1 = insertComment(userDavid, note, new Comment("first Comment", getDateTime()));
            Comment comment2 = insertComment(userDavid, note, new Comment("second Comment", getDateTime()));

            List<Comment> comments = getAllCommentsByIdUser(userDavid.getId());
            assertEquals(comments.size(), 2);
            assertTrue(comments.contains(comment1));
            assertTrue(comments.contains(comment2));

            deleteCommentById(comment1, userDavid);
            comments = getAllCommentsByIdUser(userDavid.getId());
            assertEquals(comments.size(), 1);
            assertFalse(comments.contains(comment1));
            assertTrue(comments.contains(comment2));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteCommentsByIdNoteRevision() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section, new Note("New Note", getDateTime(), listRevisions));
            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null,getDateTime());
            Comment comment1 = insertComment(userDavid, note, new Comment("first Comment", getDateTime()));
            Comment comment2 = insertComment(userDavid, note, new Comment("second Comment", getDateTime()));

            List<Comment> comments = getAllCommentsByIdNoteRevision(noteRevision.getId());
            assertEquals(comments.size(), 2);
            assertTrue(comments.contains(comment1));
            assertTrue(comments.contains(comment2));

            deleteAllCommentsByNoteRevision(noteRevision.getId());
            comments = getAllCommentsByIdNoteRevision(noteRevision.getId());
            assertEquals(comments.size(), 0);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteCommentsByIdUser() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section, new Note("New Note", getDateTime(), listRevisions));

            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            Comment comment1 = insertComment(userDavid, note, new Comment("first Comment", getDateTime()));
            Comment comment2 = insertComment(userDavid, note, new Comment("second Comment", getDateTime()));

            List<Comment> comments = getAllCommentsByIdUser(userDavid.getId());
            assertEquals(comments.size(), 2);
            assertTrue(comments.contains(comment1));
            assertTrue(comments.contains(comment2));

            deleteAllCommentsByUser(userDavid.getId());
            comments = getAllCommentsByIdUser(userDavid.getId());
            assertEquals(comments.size(), 0);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.now().withNano(0);
    }*/
}
