package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.exception.NotesErrorCode;
import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.dao.AdminDao;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends DaoImplBase implements AdminDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);

    @Override
    public void addRole(int idUser) {
        LOGGER.debug("DAO add Role idUser {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getAdminMapper(sqlSession).addRole(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t add Role idUser {}, {}", idUser, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public User getAdminByIdSession(int idSessionForAdmin) {
        LOGGER.debug("DAO get User by idSession {}", idSessionForAdmin);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getAdminMapper(sqlSession).getAdminById(idSessionForAdmin);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get User by idSession {}, {}", idSessionForAdmin, ex);
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
        }
    }

    @Override
    public void deleteSection(int idSection) {

    }

    @Override
    public void deleteNote(int idNote) {

    }

    @Override
    public void deleteComment(int idComment) {

    }

    @Override
    public void changeNoteSection(int idNote, int idSection) {

    }
}
