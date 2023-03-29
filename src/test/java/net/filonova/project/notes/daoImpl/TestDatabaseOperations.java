package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDatabaseOperations extends TestBase {

  /* @Test
    public void testDatabase() {
        //зарегистрируем юзеров
        insertUsers();
        login(new User("Admin", "admin123"));
        List<User> users = getAllSession();
        assertEquals(users.size(), 13);

        //добавим секции
        Section section1 = insertSection(users.get(0), "first Section");
        Section section2 = insertSection(users.get(1), "second Section");
        Section section3 = insertSection(users.get(2), "third Section");
        List<Section> sections = getAllSections();
        assertEquals(sections.size(), 3);

        //добавим заметки
        NoteRevision noteRevision1 = new NoteRevision("This is Note-1 for First Section", 1);
        List<NoteRevision> listRevisions1 = new ArrayList<>();
        listRevisions1.add(noteRevision1);
        Note note1 = insertNote(users.get(0), section1, new Note("Note 1", getDateTime(), listRevisions1));

        NoteRevision noteRevision2 = new NoteRevision("This is Note-2 for First Section", 1);
        List<NoteRevision> listRevisions2 = new ArrayList<>();
        listRevisions2.add(noteRevision2);
        Note note2 = insertNote(users.get(1), section1, new Note("Note 2", getDateTime(), listRevisions2));

        List<Note> notesBySection1 = getAllNotesByIdSection(section1.getId());
        assertEquals(notesBySection1.size(), 2);

        NoteRevision noteRevision3 = new NoteRevision("This is Note-1 for Second Section", 1);
        List<NoteRevision> listRevisions3 = new ArrayList<>();
        listRevisions3.add(noteRevision3);
        Note note3 = insertNote(users.get(1), section2, new Note("Note 1", getDateTime(), listRevisions3));

        NoteRevision noteRevision4 = new NoteRevision("This is Note-2 for Second Section", 1);
        List<NoteRevision> listRevisions4 = new ArrayList<>();
        listRevisions4.add(noteRevision4);
        Note note4 = insertNote(users.get(5), section2, new Note("Note 2", getDateTime(), listRevisions4));

        List<Note> notesBySection2 = getAllNotesByIdSection(section2.getId());
        assertEquals(notesBySection2.size(), 2);

        NoteRevision noteRevision5 = new NoteRevision("This is Note-1 for Third Section", 1);
        List<NoteRevision> listRevisions5 = new ArrayList<>();
        listRevisions5.add(noteRevision5);
        Note note5 = insertNote(users.get(2), section3, new Note("Note 1", getDateTime(), listRevisions5));

        List<Note> notesBySection3 = getAllNotesByIdSection(section3.getId());
        assertEquals(notesBySection3.size(), 1);

        //добавим комментарии
        Comment comment1 = insertComment(users.get(10), note1, new Comment("first Comment", getDateTime()));
        Comment comment2 = insertComment(users.get(6), note1, new Comment("second Comment", getDateTime()));
        Comment comment3 = insertComment(users.get(8), note2, new Comment("first Comment", getDateTime()));
        Comment comment4 = insertComment(users.get(9), note3, new Comment("first Comment", getDateTime()));
        Comment comment5 = insertComment(users.get(5), note3, new Comment("second Comment", getDateTime()));
        Comment comment6 = insertComment(users.get(6), note5, new Comment("second Comment", getDateTime()));

        List<Comment> commentsByNote1 = getAllCommentsByIdNoteRevision(note1.getNoteRevisions().get(0).getId());
        assertEquals(commentsByNote1.size(), 2);
        List<Comment> commentsByNote2 = getAllCommentsByIdNoteRevision(note2.getNoteRevisions().get(0).getId());
        assertEquals(commentsByNote2.size(), 1);
        List<Comment> commentsByNote3 = getAllCommentsByIdNoteRevision(note3.getNoteRevisions().get(0).getId());
        assertEquals(commentsByNote3.size(), 2);
        List<Comment> commentsByNote4 = getAllCommentsByIdNoteRevision(note4.getNoteRevisions().get(0).getId());
        assertEquals(commentsByNote4.size(), 0);
        List<Comment> commentsByNote5 = getAllCommentsByIdNoteRevision(note5.getNoteRevisions().get(0).getId());
        assertEquals(commentsByNote5.size(), 1);

        //поставим рейтинг
        addRating(note1, 4.0);
        addRating(note1, 5.0);
        addRating(note1, 5.0);
        addRating(note2, 3.0);
        addRating(note2, 3.0);
        addRating(note2, 4.0);
        addRating(note3, 5.0);
        addRating(note3, 5.0);
        addRating(note4, 5.0);
        addRating(note4, 4.0);
        addRating(note4, 5.0);
        addRating(note4, 5.0);
        addRating(note4, 4.0);

        User user1 = getUserById(users.get(0).getId());
        User user2 = getUserById(users.get(1).getId());
        User user3 = getUserById(users.get(2).getId());
        User user4 = getUserById(users.get(5).getId());

        assertEquals(user1.getRating(), 4.7);
        assertEquals(user2.getRating(), 4.0);
        assertEquals(user3.getRating(), 0.0);
        assertEquals(user4.getRating(), 4.6);

        Note note1ForDB = getByIdNote(note1.getId());
        Note note2ForDB = getByIdNote(note2.getId());
        Note note3ForDB = getByIdNote(note3.getId());
        Note note4ForDB = getByIdNote(note4.getId());
        Note note5ForDB = getByIdNote(note5.getId());

        assertEquals(note1ForDB.getRating(), 4.7);
        assertEquals(note2ForDB.getRating(), 3.3);
        assertEquals(note3ForDB.getRating(), 5.0);
        assertEquals(note4ForDB.getRating(), 4.6);
        assertEquals(note5ForDB.getRating(), 0.0);
    }

    private void insertUsers() {
        User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
        User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
        User userPeter = insertUser("Peter123", "q1w2e3", "Peter", "Smite", null, getDateTime());
        User userIvan = insertUser("Ivan9020010", "u86hfd", "Ivan", "Ivanov", null, getDateTime());
        User userOlga = insertUser("Olga90", "q1w2e3", "Olga", "Shishkina", null, getDateTime());
        User userValeria = insertUser("ValeriaL", "i8u6t7", "Valeria", "Voropaeva", null, getDateTime());
        User userOleg = insertUser("SittyPet", "q1w2e3", "Peter", "Smite", null, getDateTime());
        User userRoman = insertUser("RomanFrost", "u86hfd", "Roman", "Ivanov", null, getDateTime());
        User userVladimir = insertUser("VNefedov", "q1w2e3", "Vladimir", "Nefedov", null, getDateTime());
        User userIgor = insertUser("Igorelij", "bassOMG", "Igor", "Gorelij", null, getDateTime());
        User userMihail = insertUser("MihaMishustin", "q1w2e3", "Mihail", "Mishustin", null, getDateTime());
        User userNikol = insertUser("NiKidman", "u86hfd", "Nikol", "Kidman", null, getDateTime());
    }
    private LocalDateTime getDateTime() {
        return LocalDateTime.now().withNano(0);
    }*/
}
