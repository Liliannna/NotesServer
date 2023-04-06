package net.filonova.project.notes.mappers;

import net.filonova.project.notes.model.Session;
import net.filonova.project.notes.model.Status;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    @Insert("INSERT INTO user (login, password, firstName, lastName, patronymic, datetimeRegistration) " +
            "VALUES (#{user.login}, #{user.password}, #{user.firstName}, #{user.lastName}, #{user.patronymic}, #{user.registration})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    Integer insert(@Param("user") User user);

    @Update("UPDATE user SET password = #{user.password}, firstName = #{user.firstName}, lastName = #{user.lastName}, patronymic = #{user.patronymic} " +
            "WHERE id IN (SELECT idUser FROM session WHERE id = #{idSession}) AND password = #{actualPassword}")
    void update(@Param("user") User user, @Param("actualPassword") String actualPassword, int idSession);

    @Update("UPDATE user SET status = 'DELETED' WHERE id IN (SELECT idUser FROM session WHERE id = #{idSession}) AND password = #{password}")
    void deleteUser(int idSession, String password);

    @Insert("INSERT INTO session (idUser, token, lastAction) VALUES (#{user.id}, #{token}, #{lastAction}) " +
            "ON DUPLICATE KEY UPDATE token=#{token}, lastAction=#{lastAction}")
    Integer login(Session session);

    @Delete("DELETE FROM session WHERE id = #{idUserSession}")
    void logout(int idUserSession);

    @Select("SELECT * FROM user WHERE id IN (SELECT idUser FROM session WHERE id = #{idSession})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    User getUserByIdSession(int idSession);

    @Select("SELECT * FROM user WHERE id = #{idUser}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    User getById(int idUser);

    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    List<User> getAll();

    @Select("SELECT * FROM user JOIN session ON session.idUser = user.id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    List<User> getAllSession();

    @Select("SELECT * FROM user WHERE status = #{status}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    List<User> getByStatus(Status status);

    @Select("SELECT * FROM user WHERE rating = #{rating}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    List<User> getByRating(int rating);

    @Select("SELECT * FROM user WHERE id IN (SELECT idFollowing FROM following WHERE idUser = #{idUser})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    List<User> getFollowings(int idUser);

    @Select("SELECT * FROM user WHERE id IN (SELECT idFollower FROM follower WHERE idUser = #{idUser})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    List<User> getFollowers(int idUser);

    @Select("SELECT * FROM user WHERE id IN (SELECT idIgnoredUser FROM `ignore` WHERE idUser = #{idUser})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    List<User> getIgnore(int idUser);

    @Select("SELECT * FROM user WHERE id IN (SELECT idUser FROM `ignore` WHERE idIgnoredUser = #{idUser})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sections", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.SectionMapper.getSectionByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.CommentMapper.getCommentsByIdUser", fetchType = FetchType.LAZY)),
            @Result(property = "following", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowings", fetchType = FetchType.LAZY)),
            @Result(property = "followers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getFollowers", fetchType = FetchType.LAZY)),
            @Result(property = "ignore", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnore", fetchType = FetchType.LAZY)),
            @Result(property = "ignoredBy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.UserMapper.getIgnoredBy", fetchType = FetchType.LAZY)),
    })
    List<User> getIgnoredBy(int idUser);

    @Update("UPDATE user SET rating = (SELECT (SELECT SUM(rating) FROM note_rating WHERE idUser = #{id}) / (SELECT COUNT(*) FROM note_rating WHERE idUser = #{id})) WHERE id = #{id}")
    void ratingUser(User user);

    @Insert("INSERT INTO following (idUser, idFollowing) VALUES (#{idUser}, #{idFollowing})")
    void addFollowing(@Param("idUser") int idUser, @Param("idFollowing") int idFollowing);

    @Insert("INSERT INTO follower (idUser, idFollower) VALUES (#{idUser}, #{idFollower})")
    void addFollower(@Param("idUser") int idUser, @Param("idFollower") int idFollower);

    @Insert("INSERT INTO `ignore` (idUser, idIgnoredUser) VALUES (#{idUser}, #{idIgnoredUser})")
    void addIgnore(@Param("idUser") int idUser, @Param("idIgnoredUser") int idIgnoredUser);

    @Delete("DELETE FROM following WHERE idUser = #{idUser} AND idFollowing = #{idFollowing}")
    void deleteFollowing(@Param("idUser") int idUser, @Param("idFollowing") int idFollowing);

    @Delete("DELETE FROM following WHERE idUser = #{idUser}")
    void deleteAllFollowingByUser(int idUser);

    @Delete("DELETE FROM `ignore` WHERE idUser = #{idUser} AND idIgnoredUser = #{idIgnoredUser}")
    void deleteIgnoredUser(@Param("idUser") int idUser, @Param("idIgnoredUser") int idIgnoredUser);

    @Delete("DELETE FROM `ignore` WHERE idUser = #{idUser}")
    void deleteAllIgnoredUsersByUser(int idUser);

    @Delete("DELETE FROM user WHERE id != 1")
    void deleteAll();

    @Delete("DELETE FROM session")
    void deleteAllSession();

    @Select("SELECT * FROM user WHERE login = #{login}")
    User getByLogin(String login);

    List<User> getHighRating();
}
