package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.model.Status;
import net.filonova.project.notes.model.User;
import net.filonova.project.notes.utils.Converter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserOperations extends TestBase {

 /*   private final Converter converter = new Converter();

   @Test
    public void testInsertUser() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userLilianaForDB = getUserById(userLiliana.getId());
            assertEquals(converter.userToUserDtoResponse(userLiliana), converter.userToUserDtoResponse(userLilianaForDB));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testInsertUserNullFirstName() {
        assertThrows(RuntimeException.class, () -> {
            insertUser("Liliana", "q1w2e3", null, "Filonova", null, getDateTime());
        });
    }

    @Test
    public void testUpdateUser() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userLilianaForDB = getUserById(userLiliana.getId());
            assertEquals(converter.userToUserDtoResponse(userLiliana), converter.userToUserDtoResponse(userLilianaForDB));
            userLiliana.setFirstName("Filipa");
            updateUser(userLiliana, userLiliana.getPassword(), userLiliana.getIdSession());
            userLilianaForDB = getUserById(userLiliana.getId());
            assertEquals(converter.userToUserDtoResponse(userLiliana), converter.userToUserDtoResponse(userLilianaForDB));
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testDeleteUser() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userLilianaForDB = getUserById(userLiliana.getId());
            assertEquals(userLilianaForDB.getStatus(), Status.ACTIVE);
            deleteUser(userLiliana.getIdSession(), userLiliana.getPassword());
            userLilianaForDB = getUserById(userLiliana.getId());
            assertEquals(userLilianaForDB.getStatus(), Status.DELETED);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testSession() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            List<User> session = getAllSession();
            assertEquals(session.size(), 1);
            login(new User("Admin", "admin123"));
            session = getAllSession();
            assertEquals(session.size(), 2);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllUsers() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            List<User> users = getAllUsers();
            assertEquals(users.size(), 3);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testGetByStatus() {
        try {
            User userLiliana = insertUser("Liliana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userDavid = insertUser("David504", "bassOMG", "David", "Bass", null, getDateTime());
            User userPeter = insertUser("Peter123", "q1w2e3", "Peter", "Smite", null, getDateTime());
            User userIvan = insertUser("Ivan9020010", "u86hfd", "Ivan", "Ivanov", null, getDateTime());

            List<User> users1 = getByStatus(Status.ACTIVE);
            assertEquals(users1.size(), 5);

            deleteUser(userPeter.getIdSession(), userPeter.getPassword());
            List<User> users2 = getByStatus(Status.DELETED);
            assertEquals(users2.size(), 1);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testAddAndDeleteFollowing() {
        try {
            User userLiliana = insertUser("Filiana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userPeter = insertUser("Peter123", "q1w2e3", "Peter", "Smite", null, getDateTime());
            User userIvan = insertUser("Ivan9020010", "u86hfd", "Ivan", "Ivanov", null, getDateTime());
            List<User> followings = getFollowings(userLiliana.getId());
            assertEquals(followings.size(), 0);

            addFollowing(userLiliana.getId(), userPeter.getId());
            addFollowing(userLiliana.getId(), userIvan.getId());
            followings = getFollowings(userLiliana.getId());
            assertEquals(followings.size(), 2);

            List<User> followersPeter = getFollowers(userPeter.getId());
            assertEquals(followersPeter.size(), 1);

            List<User> followersIvan = getFollowers(userIvan.getId());
            assertEquals(followersIvan.size(), 1);

            deleteFollowing(userLiliana.getId(), userPeter.getId());
            followings = getFollowings(userLiliana.getId());
            assertEquals(followings.size(), 1);

            followersPeter = getFollowers(userPeter.getId());
            assertEquals(followersPeter.size(), 0);
        } catch (RuntimeException ex) {
            fail();
        }
    }

    @Test
    public void testAddAndDeleteIgnoredUser() {
        try {
            User userLiliana = insertUser("Filiana", "q1w2e3", "Tatiana", "Filonova", null, getDateTime());
            User userPeter = insertUser("Peter123", "q1w2e3", "Peter", "Smite", null, getDateTime());
            User userIvan = insertUser("Ivan9020010", "u86hfd", "Ivan", "Ivanov", null, getDateTime());

            List<User> ignore = getIgnore(userLiliana.getId());
            assertEquals(ignore.size(), 0);

            addIgnore(userLiliana.getId(), userPeter.getId());
            ignore = getIgnore(userLiliana.getId());
            assertEquals(ignore.size(), 1);

            User userPeterFromDB = getUserById(userPeter.getId());
            List<User> ignoredBy = userPeterFromDB.getIgnoredBy();
            assertEquals(ignoredBy.size(), 1);

            List<User> followingsLiliana = getFollowings(userLiliana.getId());
            assertEquals(followingsLiliana.size(), 0);

            addFollowing(userLiliana.getId(), userIvan.getId());
            followingsLiliana = getFollowings(userLiliana.getId());
            assertEquals(followingsLiliana.size(), 1);

            addIgnore(userLiliana.getId(), userIvan.getId());
            ignore = getIgnore(userLiliana.getId());
            assertEquals(ignore.size(), 2);

            followingsLiliana = getFollowings(userLiliana.getId());
            assertEquals(followingsLiliana.size(), 0);

            deleteIgnoredUser(userLiliana.getId(), userPeter.getId());
            ignore = getIgnore(userLiliana.getId());
            assertEquals(ignore.size(), 1);
        } catch (RuntimeException ex) {
            fail();
        }
    }
    private LocalDateTime getDateTime() {
        return LocalDateTime.now().withNano(0);
    }*/
}
