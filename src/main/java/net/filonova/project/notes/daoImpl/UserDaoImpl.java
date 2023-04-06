package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.exception.NotesErrorCode;
import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.dao.UserDao;
import net.filonova.project.notes.model.Session;
import net.filonova.project.notes.model.Status;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends DaoImplBase implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);


    @Override
    public User insert(User user) {
        LOGGER.debug("DAO insert User {}", user);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t insert User {}, {}", user, ex);
                sqlSession.rollback();
                System.out.println(ex);
                throw new NotesException(NotesErrorCode.LOGIN_ALREADY_EXISTS, "Login", "User " + user.getLogin() + " already exists");
            }
            sqlSession.commit();
        }
        return user;
    }

    @Override
    public void insertSession(Session session) {
        LOGGER.debug("DAO insertSession user {}", session.getUser());
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).login(session);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t insert session {}, {}", session.getUser(), ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public User update(User user, String actualPassword, int idUserSession) {
        LOGGER.debug("DAO update User {}", user);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(user, actualPassword, idUserSession);
                user = getUserMapper(sqlSession).getUserByIdSession(idUserSession);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t update User {}, {}", user, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.WRONG_PASSWORD, "Password", "That password is not valid");
            }
            sqlSession.commit();
        }
        return user;
    }

    @Override
    public void deleteUser(int idUserSession, String password) {
        LOGGER.debug("DAO delete User {}", idUserSession);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteUser(idUserSession, password);
                getUserMapper(sqlSession).logout(idUserSession);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete User {}, {}", idUserSession, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.WRONG_PASSWORD, "Password", "That password is not valid");
            }
            sqlSession.commit();
        }
    }

    @Override
    public User login(User user) {
        LOGGER.debug("DAO login User {}", user);
        /*try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).login(user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t login User {}, {}", user, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.WRONG_LOGIN_OR_PASSWORD, "Login or password", "That login/password is not valid");
            }
            sqlSession.commit();
        }*/
        return user;
    }

    @Override
    public void logout(int idUserSession) {
        LOGGER.debug("DAO logout User {}", idUserSession);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).logout(idUserSession);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t logout User {}, {}", idUserSession, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public User getById(int idUser) {
        LOGGER.debug("DAO get User by id {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getById(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get User by id {}, {}", idUser, ex);
                throw new NotesException(NotesErrorCode.NOT_FOUND_USER, "User", "User is not found");
            }
        }
    }

    @Override
    public User getByLogin(String login) {
        LOGGER.debug("DAO get User by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getByLogin(login);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get User by login {}, {}", login, ex);
                throw new NotesException(NotesErrorCode.NOT_FOUND_USER, "Login", "User " + login + " is not found");
            }
        }
    }

    @Override
    public User getByIdSession(int idUserSession) {
        LOGGER.debug("DAO get User by idSession {}", idUserSession);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getUserByIdSession(idUserSession);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get User by idSession {}, {}", idUserSession, ex);
                throw new NotesException(NotesErrorCode.NOT_FOUND_USER, "id", "User is not found");
            }
        }
    }

    @Override
    public List<User> getAll() {
        LOGGER.debug("DAO get all Users");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get all Users, {}", ex);
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
        }
    }

    @Override
    public List<User> getAllSession() {
        LOGGER.debug("DAO get all session");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getAllSession();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get all session, {}", ex);
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
        }
    }

    @Override
    public List<User> getByStatus(Status status) {
        LOGGER.debug("DAO get Users by status {}", status);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getByStatus(status);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Users by status {}, {}", status, ex);
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
        }
    }

    @Override
    public List<User> getByStatusCount(int from, int count) {
        return null;
    }

    @Override
    public List<User> getHighRating() {
        LOGGER.debug("DAO get Users high rating");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getHighRating();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Users high rating, {}", ex);
                throw new NotesException(NotesErrorCode.NOT_FOUND, "High rating", "Not found");
            }
        }
    }

    @Override
    public List<User> getLowRating() {
        return null;
    }

    @Override
    public List<User> getFollowings(int idUser) {
        LOGGER.debug("DAO get followings User by idUser {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getFollowings(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get followings User by idUser {}, {}", idUser, ex);
                throw new NotesException(NotesErrorCode.NOT_FOUND, "Following", "Not found");
            }
        }
    }

    @Override
    public List<User> getFollowers(int idUser) {
        LOGGER.debug("DAO get followers User by idUser {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getFollowers(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get followers User by idUser {}, {}", idUser, ex);
                throw new NotesException(NotesErrorCode.NOT_FOUND, "Followers", "Not found");
            }
        }
    }

    @Override
    public List<User> getIgnore(int idUser) {
        LOGGER.debug("DAO get ignore list User by idUser {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getIgnore(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get ignore list User by idUser {}, {}", idUser, ex);
                throw new NotesException(NotesErrorCode.NOT_FOUND, "Ignore", "Not found");
            }
        }
    }

    @Override
    public List<User> getIgnoredBy(int idUser) {
        LOGGER.debug("DAO get ignored by User by idUser {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getIgnoredBy(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get ignored by User by idUser {}, {}", idUser, ex);
                throw new NotesException(NotesErrorCode.NOT_FOUND, "IgnoredBy", "Not found");
            }
        }
    }

    @Override
    public void addFollowing(int idUser, int idFollowing) {
        LOGGER.debug("DAO add following {} User by idUser {}", idFollowing, idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteIgnoredUser(idUser, idFollowing);
                getUserMapper(sqlSession).addFollowing(idUser, idFollowing);
                getUserMapper(sqlSession).addFollower(idFollowing, idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t add following {} User by idUser {}, {}", idFollowing, idUser, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public void addIgnore(int idUser, int idIgnoredUser) {
        LOGGER.debug("DAO add ignored user {} User by idUser {}", idIgnoredUser, idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteFollowing(idUser, idIgnoredUser);
                getUserMapper(sqlSession).addIgnore(idUser, idIgnoredUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t add ignored user {} User by idUser {}, {}", idIgnoredUser, idUser, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteFollowing(int idUser, int idFollowing) {
        LOGGER.debug("DAO delete following {} User by idUser {}", idFollowing, idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteFollowing(idUser, idFollowing);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete following {} User by idUser {}, {}", idFollowing, idUser, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAllFollowingByUser(int idUser) {
        LOGGER.debug("DAO delete all followings User by idUser {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteAllFollowingByUser(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete all followings User by idUser {}, {}", idUser, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteIgnoredUser(int idUser, int idIgnoredUser) {
        LOGGER.debug("DAO delete all ignored User {} by idUser {}", idIgnoredUser, idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteIgnoredUser(idUser, idIgnoredUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete all ignored User {} by idUser {}, {}", idIgnoredUser, idUser, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAllIgnoredUsersByUser(int idUser) {
        LOGGER.debug("DAO delete all ignored Users by idUser {}", idUser);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteAllIgnoredUsersByUser(idUser);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete all ignored Users by idUser {}, {}", idUser, ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all users");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteAll();
                getUserMapper(sqlSession).deleteAllSession();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete all users, {}", ex);
                sqlSession.rollback();
                throw new NotesException(NotesErrorCode.UNEXPECTED_ERROR, "Error", "Unexpected error");
            }
            sqlSession.commit();
        }
    }
}
