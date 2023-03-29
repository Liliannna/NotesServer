package net.filonova.project.notes.dao;

import net.filonova.project.notes.model.Status;
import net.filonova.project.notes.model.User;

import java.util.List;

public interface UserDao {
    User insert(User user);
    User update(User user, String actualPassword, int idUserSession);
    void deleteUser(int idUserSession, String password);
    User login(User user);
    void logout(int idUserSession);
    User getById(int idUser);
    User getByLogin(String login);
    User getByIdSession(int idUserSession);
    List<User> getAll();
    List<User> getAllSession();
    List<User> getByStatus(Status status);
    List<User> getByStatusCount(int from, int count);
    List<User> getHighRating();
    List<User> getLowRating();
    List<User> getFollowings(int idUser);
    List<User> getFollowers(int idUser);
    List<User> getIgnore(int idUser);
    List<User> getIgnoredBy(int idUser);
    void addFollowing(int idUser, int idFollowing);
    void addIgnore(int idUser, int idIgnoredUser);
    void deleteFollowing(int idUser, int idFollowing);
    void deleteAllFollowingByUser(int idUser);
    void deleteIgnoredUser(int idUser, int idIgnoredUser);
    void deleteAllIgnoredUsersByUser(int idUser);
    void deleteAll();
}
