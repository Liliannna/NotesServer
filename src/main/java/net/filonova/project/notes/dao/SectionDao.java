package net.filonova.project.notes.dao;

import net.filonova.project.notes.model.Section;
import net.filonova.project.notes.model.User;

import java.util.List;

public interface SectionDao {
    Section insert(User user, Section section);
    Section update(Section section, User user);
    Section getById(int idSection);
    List<Section> getAll();
    void deleteSection(Section section, User user);
    void deleteAll();
}
