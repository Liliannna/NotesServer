package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.dao.NoteDao;
import net.filonova.project.notes.model.Note;
import net.filonova.project.notes.model.Section;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class NoteDaoImpl extends DaoImplBase implements NoteDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteDaoImpl.class);

    @Override
    public Note insert(User user, Section section, Note note) {
        LOGGER.debug("DAO insert Note {}", note);
        try (SqlSession sqlSession = getSession()) {
            try {
                getNoteMapper(sqlSession).insert(user, section, note);
                getNoteMapper(sqlSession).insertNoteRevision(note, note.getNoteRevisions().get(0));
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t insert Note {}, {}", note, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return note;
    }

    @Override
    public Note insertNoteRevision(Note note) {
        LOGGER.debug("DAO insert NoteRevision {}", note.getNoteRevisions().get(0));
        try (SqlSession sqlSession = getSession()) {
            try {
                getNoteMapper(sqlSession).insertNoteRevision(note, note.getNoteRevisions().get(0));
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t insert NoteRevision {}, {}", note, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return note;
    }

    @Override
    public Note update(Note note, Section section, User user) {
        LOGGER.debug("DAO update Note {}", note);
        try (SqlSession sqlSession = getSession()) {
            try {
                getNoteMapper(sqlSession).update(user, section, note);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t update Note {}, {}", note, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return note;
    }

    @Override
    public Note getById(int idNote) {
        LOGGER.debug("DAO get Note by id {}", idNote);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getNoteMapper(sqlSession).getById(idNote);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Note by id {}, {}", idNote, ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Note> getAll() {
        LOGGER.debug("DAO get all Note");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getNoteMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get all Note, {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Note> getAllNotesByIdUser(int idUser) {
        LOGGER.debug("DAO get all Notes by id User {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getNoteMapper(sqlSession).getNotesByIdUser(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get all Notes by id User {}, {}", idUser, ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Note> getAllNotesByRating(double rating) {
        LOGGER.debug("DAO get Notes by rating {}", rating);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getNoteMapper(sqlSession).getNotesByRating(rating);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Notes by rating {}, {}", rating, ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Note> getNotesByIdSection(int idSection) {
        LOGGER.debug("DAO get Notes by id Section {}", idSection);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getNoteMapper(sqlSession).getNotesByIdSection(idSection);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Notes by id Section {}, {}", idSection, ex);
                throw ex;
            }
        }
    }

    @Override
    public void addRating(Note note, double rating) {
        LOGGER.debug("DAO add Rating {} for Note {}", rating, note);
        try (SqlSession sqlSession = getSession()) {
            try {
                getNoteMapper(sqlSession).addRating(note, rating);
                getNoteMapper(sqlSession).ratingNote(note);
                User user = getNoteMapper(sqlSession).getIdUserByIdNote(note);
                getUserMapper(sqlSession).ratingUser(user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t add Rating {} for Note {}, {}", rating, note, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteNote(Note note, User user) {
        LOGGER.debug("DAO delete Note by id {}", note.getId());
        try (SqlSession sqlSession = getSession()) {
            try {
                getNoteMapper(sqlSession).deleteNote(note, user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete Note by id {}, {}", note.getId(), ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAllNoteByUser(int idUser) {
        LOGGER.debug("DAO delete Note by id User {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getNoteMapper(sqlSession).deleteAllNoteByUser(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete Note by id User {}, {}", idUser, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteNotesBySection(int idSection) {
        LOGGER.debug("DAO delete Note by id Section {}", idSection);
        try (SqlSession sqlSession = getSession()) {
            try {
                getNoteMapper(sqlSession).deleteAllNoteBySection(idSection);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete Note by id Section {}, {}", idSection, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Notes");
        try (SqlSession sqlSession = getSession()) {
            try {
                getNoteMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete all Notes, {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
