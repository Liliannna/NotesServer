package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.dao.CommentDao;
import net.filonova.project.notes.model.Comment;
import net.filonova.project.notes.model.Note;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CommentDaoImpl extends DaoImplBase implements CommentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentDaoImpl.class);

    @Override
    public Comment insert(User user, Note note, Comment comment) {
        LOGGER.debug("DAO insert Comment {}", comment);
        try (SqlSession sqlSession = getSession()) {
            try {
                getCommentMapper(sqlSession).insert(user, note.getNoteRevisions().get(0), comment);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t insert Comment {}, {}", comment, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return comment;
    }

    @Override
    public Comment update(Comment comment, User user) {
        LOGGER.debug("DAO update Comment {}", comment);
        try (SqlSession sqlSession = getSession()) {
            try {
                getCommentMapper(sqlSession).update(comment, user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t update Comment {}, {}", comment, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return comment;
    }

    @Override
    public Comment getById(int idComment) {
        LOGGER.debug("DAO get Comment by id {}", idComment);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getCommentMapper(sqlSession).getById(idComment);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Comment by id {}, {}", idComment, ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Comment> getAllCommentsByIdUser(int idUser) {
        LOGGER.debug("DAO get Comment by id User {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getCommentMapper(sqlSession).getCommentsByIdUser(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Comment by id User {}, {}", idUser, ex);
                throw ex;
            }
        }
    }

    @Override
    public List<Comment> getAllCommentsByIdNoteRevision(int idNoteRevision) {
        LOGGER.debug("DAO get Comment by id NoteRevision {}", idNoteRevision);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getCommentMapper(sqlSession).getAllCommentsByIdNoteRevision(idNoteRevision);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Comment by id NoteRevision {}, {}", idNoteRevision, ex);
                throw ex;
            }
        }
    }

    @Override
    public void deleteById(Comment comment, User user) {
        LOGGER.debug("DAO delete Comment by id {}", comment.getId());
        try (SqlSession sqlSession = getSession()) {
            try {
                getCommentMapper(sqlSession).deleteById(comment, user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete Comment by id {}, {}", comment.getId(), ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAllCommentsByNoteRevision(int idNoteRevision) {
        LOGGER.debug("DAO delete Comments by idRevision {}", idNoteRevision);
        try (SqlSession sqlSession = getSession()) {
            try {
                getCommentMapper(sqlSession).deleteAllCommentsByNoteRevision(idNoteRevision);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete Comments by idRevision, {}", idNoteRevision, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAllCommentsByUser(int idUser) {
        LOGGER.debug("DAO delete Comments by idUser {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getCommentMapper(sqlSession).deleteAllCommentsByUser(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete Comments by idUser, {}", idUser, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Comments");
        try (SqlSession sqlSession = getSession()) {
            try {
                getCommentMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete all Comments, {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
