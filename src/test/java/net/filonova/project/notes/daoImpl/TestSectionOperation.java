package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.model.Section;
import net.filonova.project.notes.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSectionOperation extends TestBase {
/*
    @Test
    public void testInsertSection() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            Section sectionForDB = getSectionById(section.getId());
            assertEquals(section, sectionForDB);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testUpdateSectionFirst() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section = insertSection(userLiliana, "new Section");
            section.setName("new name Section");
            updateSection(section, userLiliana);
            Section sectionForDB = getSectionById(section.getId());
            assertEquals(section, sectionForDB);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllSections() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section1 = insertSection(userLiliana, "Section 1");
            Section section2 = insertSection(userLiliana, "Section 2");
            Section section3 = insertSection(userLiliana, "Section 3");

            List<Section> sections = getAllSections();
            assertEquals(sections.size(), 3);
            assertTrue(sections.contains(section1));
            assertTrue(sections.contains(section2));
            assertTrue(sections.contains(section3));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteSection() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            Section section1 = insertSection(userLiliana, "Section 1");
            Section section2 = insertSection(userLiliana, "Section 2");

            List<Section> sections = getAllSections();
            assertEquals(sections.size(), 2);

            deleteSection(section1, userLiliana);
            sections = getAllSections();
            assertEquals(sections.size(), 1);
            assertFalse(sections.contains(section1));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertSectionWithNullName() {
        User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
        assertThrows(RuntimeException.class, () -> {
            insertSection(userLiliana, null);
        });
    }

    @Test
    public void testUpdateSectionSecond() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userPeter = insertUser("Peter123", "q1w2e3", "Peter", "Smite", null, getDateTime());
            Section section1 = insertSection(userLiliana, "Section 1");
            deleteSection(section1, userPeter);
            List<Section> sections = getAllSections();
            assertTrue(sections.contains(section1));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.now().withNano(0);
    }*/
}
