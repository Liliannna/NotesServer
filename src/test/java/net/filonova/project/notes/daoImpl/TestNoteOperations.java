package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.model.Note;
import net.filonova.project.notes.model.NoteRevision;
import net.filonova.project.notes.model.Section;
import net.filonova.project.notes.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestNoteOperations extends TestBase {
/*

    @Test
    public void testInsertNote() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section, new Note("new Note", getDateTime(), listRevisions));

            Note noteForDB = getByIdNote(note.getId());
            assertEquals(noteForDB, note);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testUpdateNote() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section1 = insertSection(userLiliana, "First Section");
            Section section2 = insertSection(userLiliana, "Second Section");
            NoteRevision noteRevision = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision);
            Note note = insertNote(userLiliana, section1, new Note("new Note", getDateTime(), listRevisions));

            Section section1ForDB = getSectionById(section1.getId());
            assertTrue(section1ForDB.getNotes().contains(note));

            updateNote(note, section2, userLiliana);
            Section section2ForDB = getSectionById(section2.getId());
            assertTrue(section2ForDB.getNotes().contains(note));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testAddNoteRevision() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section1 = insertSection(userLiliana, "First Section");
            NoteRevision noteRevision1 = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision1);
            Note note = insertNote(userLiliana, section1, new Note("new Note", getDateTime(), listRevisions));

            NoteRevision noteRevision2 = new NoteRevision("This is update new Note", 2);
            note.getNoteRevisions().add(0, noteRevision2);
            insertNoteRevision(note);

            Note noteForDB = getByIdNote(note.getId());
            assertTrue(noteForDB.getNoteRevisions().contains(noteRevision2));
            assertTrue(noteForDB.getNoteRevisions().contains(noteRevision1));
            assertEquals(noteForDB.getNoteRevisions().get(0), noteRevision2);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllNotes() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            Section section1 = insertSection(userLiliana, "First Section");

            NoteRevision noteRevision1 = new NoteRevision("This is new Note 1 for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision1);
            Note note1 = insertNote(userLiliana, section1, new Note("Note 1", getDateTime(), listRevisions));

            NoteRevision noteRevision2 = new NoteRevision("This is new Note 2 for new Section", 1);
            List<NoteRevision> listRevisions2 = new ArrayList<>();
            listRevisions2.add(noteRevision2);
            Note note2 = insertNote(userLiliana, section1, new Note("Note 2", getDateTime(), listRevisions2));

            NoteRevision noteRevision3 = new NoteRevision("This is new Note 3 for new Section", 1);
            List<NoteRevision> listRevisions3 = new ArrayList<>();
            listRevisions3.add(noteRevision3);
            Note note3 = insertNote(userLiliana, section1, new Note("Note 3", getDateTime(), listRevisions3));

            NoteRevision noteRevision4 = new NoteRevision("This is new Note 4 for new Section", 1);
            List<NoteRevision> listRevisions4 = new ArrayList<>();
            listRevisions4.add(noteRevision4);
            Note note4 = insertNote(userDavid, section1, new Note("Note 4", getDateTime(), listRevisions4));

            List<Note> notes = getAllNote();
            assertEquals(notes.size(), 4);
            assertTrue(notes.contains(note4));
            assertTrue(notes.contains(note3));
            assertTrue(notes.contains(note2));
            assertTrue(notes.contains(note1));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllNotesByUser() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            Section section1 = insertSection(userLiliana, "First Section");

            NoteRevision noteRevision1 = new NoteRevision("This is new Note 1 for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision1);
            Note note1 = insertNote(userLiliana, section1, new Note("Note 1", getDateTime(), listRevisions));

            NoteRevision noteRevision2 = new NoteRevision("This is new Note 2 for new Section", 1);
            List<NoteRevision> listRevisions2 = new ArrayList<>();
            listRevisions2.add(noteRevision2);
            Note note2 = insertNote(userLiliana, section1, new Note("Note 2", getDateTime(), listRevisions2));

            NoteRevision noteRevision3 = new NoteRevision("This is new Note 3 for new Section", 1);
            List<NoteRevision> listRevisions3 = new ArrayList<>();
            listRevisions3.add(noteRevision3);
            Note note3 = insertNote(userDavid, section1, new Note("Note 3", getDateTime(), listRevisions3));

            List<Note> notes1 = getAllNotesByIdUser(userLiliana.getId());
            List<Note> notes2 = getAllNotesByIdUser(userDavid.getId());
            assertEquals(notes1.size(), 2);
            assertEquals(notes2.size(), 1);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllNotesBySection() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            Section section1 = insertSection(userLiliana, "First Section");

            NoteRevision noteRevision1 = new NoteRevision("This is new Note 1 for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision1);
            Note note1 = insertNote(userLiliana, section1, new Note("Note 1", getDateTime(), listRevisions));

            NoteRevision noteRevision2 = new NoteRevision("This is new Note 2 for new Section", 1);
            List<NoteRevision> listRevisions2 = new ArrayList<>();
            listRevisions2.add(noteRevision2);
            Note note2 = insertNote(userLiliana, section1, new Note("Note 2", getDateTime(), listRevisions2));

            NoteRevision noteRevision3 = new NoteRevision("This is new Note 3 for new Section", 1);
            List<NoteRevision> listRevisions3 = new ArrayList<>();
            listRevisions3.add(noteRevision3);
            Note note3 = insertNote(userDavid, section1, new Note("Note 3", getDateTime(), listRevisions3));

            List<Note> notes1 = getAllNotesByIdSection(section1.getId());
            assertEquals(notes1.size(), 3);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteNoteByIdNote() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section1 = insertSection(userLiliana, "First Section");
            NoteRevision noteRevision1 = new NoteRevision("This is new Note for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision1);
            Note note = insertNote(userLiliana, section1, new Note("new Note", getDateTime(), listRevisions));

            List<Note> notes1 = getAllNotesByIdUser(userLiliana.getId());
            assertEquals(notes1.size(), 1);

            deleteNote(note, userLiliana);
            notes1 = getAllNotesByIdUser(userLiliana.getId());
            assertEquals(notes1.size(), 0);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteNotesByUser() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section1 = insertSection(userLiliana, "First Section");

            NoteRevision noteRevision1 = new NoteRevision("This is new Note 1 for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision1);
            Note note1 = insertNote(userLiliana, section1, new Note("Note 1", getDateTime(), listRevisions));

            NoteRevision noteRevision2 = new NoteRevision("This is new Note 2 for new Section", 1);
            List<NoteRevision> listRevisions2 = new ArrayList<>();
            listRevisions2.add(noteRevision2);
            Note note2 = insertNote(userLiliana, section1, new Note("Note 2", getDateTime(), listRevisions2));

            NoteRevision noteRevision3 = new NoteRevision("This is new Note 3 for new Section", 1);
            List<NoteRevision> listRevisions3 = new ArrayList<>();
            listRevisions3.add(noteRevision3);
            Note note3 = insertNote(userLiliana, section1, new Note("Note 3", getDateTime(), listRevisions3));

            List<Note> notes1 = getAllNotesByIdUser(userLiliana.getId());
            assertEquals(notes1.size(), 3);

            deleteAllNoteByUser(userLiliana.getId());
            notes1 = getAllNotesByIdUser(userLiliana.getId());
            assertEquals(notes1.size(), 0);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteNotesBySection() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section1 = insertSection(userLiliana, "First Section");

            NoteRevision noteRevision1 = new NoteRevision("This is new Note 1 for new Section", 1);
            List<NoteRevision> listRevisions = new ArrayList<>();
            listRevisions.add(noteRevision1);
            Note note1 = insertNote(userLiliana, section1, new Note("Note 1", getDateTime(), listRevisions));

            NoteRevision noteRevision2 = new NoteRevision("This is new Note 2 for new Section", 1);
            List<NoteRevision> listRevisions2 = new ArrayList<>();
            listRevisions2.add(noteRevision2);
            Note note2 = insertNote(userLiliana, section1, new Note("Note 2", getDateTime(), listRevisions2));

            NoteRevision noteRevision3 = new NoteRevision("This is new Note 3 for new Section", 1);
            List<NoteRevision> listRevisions3 = new ArrayList<>();
            listRevisions3.add(noteRevision3);
            Note note3 = insertNote(userLiliana, section1, new Note("Note 3", getDateTime(), listRevisions3));

            List<Note> notes1 = getAllNotesByIdSection(section1.getId());
            assertEquals(notes1.size(), 3);

            deleteNotesBySection(section1.getId());
            notes1 = getAllNotesByIdSection(section1.getId());
            assertEquals(notes1.size(), 0);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertNoteNullSection() {
        User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());

        NoteRevision noteRevision1 = new NoteRevision("This is new Note 1 for new Section", 1);
        List<NoteRevision> listRevisions = new ArrayList<>();
        listRevisions.add(noteRevision1);
        assertThrows(RuntimeException.class, () -> {
            insertNote(userLiliana, null, new Note("Note 1", getDateTime(), listRevisions));
        });
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.now().withNano(0);
    }
*/

}
