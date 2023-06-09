package net.filonova.project.notes.service;

import net.filonova.project.notes.dao.AdminDao;
import net.filonova.project.notes.dao.UserDao;
import net.filonova.project.notes.daoImpl.AdminDaoImpl;
import net.filonova.project.notes.daoImpl.UserDaoImpl;
import net.filonova.project.notes.dto.converter.UserDtoMapper;
import net.filonova.project.notes.dto.request.*;
import net.filonova.project.notes.dto.response.UserDtoResponse;
import net.filonova.project.notes.exception.NotesException;
import net.filonova.project.notes.model.Role;
import net.filonova.project.notes.model.Session;
import net.filonova.project.notes.model.User;
import net.filonova.project.notes.dto.converter.Converter;
import net.filonova.project.notes.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private final UserDao userDao = new UserDaoImpl();

    @Autowired
    private final AdminDao adminDao = new AdminDaoImpl();

    @Autowired
    private final Converter converter = new Converter();

    @Autowired
    private final Validator validator = new Validator();

    @Value("${cookie_name}")
    private String cookie_name;

    public UserService() {
    }

    public UserDtoResponse registration(UserRegistrationDtoRequest request, HttpServletResponse response) throws NotesException {
        User user = UserDtoMapper.INSTANCE.userDtoRequestToUser(request);
        user.setRole(Role.REGULAR);
        userDao.insert(user);
        addCookie(user, response);
        return UserDtoMapper.INSTANCE.userToUserDtoResponse(user);
    }

    public void deleteUser(DeleteUserDtoRequest password, int idSession) {
        validator.validatePassword(password.getPassword());
        userDao.deleteUser(idSession, password.getPassword());
    }

    public User updateUser(UserUpdateDtoRequest dtoRequest, int idSession) {
        validator.updateUser(dtoRequest);
        User user = converter.userUpdateDtoRequestToUser(dtoRequest);
        return userDao.update(user, dtoRequest.getOldPassword(), idSession);
    }

    public void addRole(int idUser, int idSessionForAdmin) {
        User user = adminDao.getAdminByIdSession(idSessionForAdmin);
        if (user.getRole() == Role.SUPER) {
            adminDao.addRole(idUser);
        }
    }

    public User login(LoginDtoRequest request) {
        validator.validateName(request.getLogin());
        validator.validatePassword(request.getPassword());
        return userDao.login(converter.userLoginDtoRequestToUser(request));
    }

    public void logout(int idSession) {
        userDao.logout(idSession);
    }

    public User getUserByIdSession(int idSession) {
        return userDao.getByIdSession(idSession);
    }

    public void addFollowing(AddIgnoreOrFollowingDtoRequest dtoRequest, int idSession) {
        User user = userDao.getByIdSession(idSession);
        User following = userDao.getByLogin(dtoRequest.getLogin());
        userDao.addFollowing(user.getId(), following.getId());
    }

    public void deleteFollowing(String loginUser, int idSession) {
        User user = userDao.getByIdSession(idSession);
        User following = userDao.getByLogin(loginUser);
        userDao.deleteFollowing(user.getId(), following.getId());
    }

    public void addIgnore(AddIgnoreOrFollowingDtoRequest dtoRequest, int idSession) {
        User user = userDao.getByIdSession(idSession);
        User following = userDao.getByLogin(dtoRequest.getLogin());
        userDao.addIgnore(user.getId(), following.getId());
    }

    public void deleteIgnore(String loginUser, int idSession) {
        User user = userDao.getByIdSession(idSession);
        User following = userDao.getByLogin(loginUser);
        userDao.deleteIgnoredUser(user.getId(), following.getId());
    }

    private void addCookie(User user, HttpServletResponse httpServletResponse) {
        Cookie cookie = createCookie();

        insertSession(user, cookie);

        httpServletResponse.addCookie(cookie);
    }

    private Cookie createCookie() {
        return new Cookie(cookie_name, UUID.randomUUID().toString());
    }

    private void insertSession(User user, Cookie cookie) {
        Session session = new Session(user, cookie.getValue(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        userDao.insertSession(session);
    }

  /* public List<User> getUsersByRequest(SearchUsersDtoRequest dtoRequest, int idSession) {
        User user = userDao.getByIdSession(idSession);
        List<User> users;
        switch (dtoRequest.getType()) {
            case "highRating":
                if (dtoRequest.getFrom() == 0 && dtoRequest.getCount() == 0) {
                    users = userDao.getHighRating();
                } else {
                    users = userDao.getHighRatingCount(dtoRequest.getFrom(), dtoRequest.getCount());
                }
            case "lowRating":
                if (dtoRequest.getFrom() == 0 && dtoRequest.getCount() == 0) {
                    users = userDao.getLowRating();
                } else {
                    users = userDao.getLowRatingCount(dtoRequest.getFrom(), dtoRequest.getCount());
                }
            case "following":
                if (dtoRequest.getFrom() == 0 && dtoRequest.getCount() == 0) {
                    users = user.getFollowing();
                } else {
                    users = user.getFollowing().subList(dtoRequest.getFrom(), dtoRequest.getCount());
                }

            case "followers":
                if (dtoRequest.getFrom() == 0 && dtoRequest.getCount() == 0) {
                    users = user.getFollowers();
                } else {
                    users = user.getFollowers().subList(dtoRequest.getFrom(), dtoRequest.getCount());
                }

            case "ignore":
                if (dtoRequest.getFrom() == 0 && dtoRequest.getCount() == 0) {
                    users = user.getIgnore();
                } else {
                    users = user.getIgnore().subList(dtoRequest.getFrom(), dtoRequest.getCount());
                }
            case "ignoredBy":
                if (dtoRequest.getFrom() == 0 && dtoRequest.getCount() == 0) {
                    users = user.getIgnoredBy();
                } else {
                    users = user.getIgnoredBy().subList(dtoRequest.getFrom(), dtoRequest.getCount());
                }
            case "deleted":
                if (dtoRequest.getFrom() == 0 && dtoRequest.getCount() == 0) {
                    users = userDao.getByStatus(Status.DELETED);
                } else {
                    users = userDao.getByStatusCount(dtoRequest.getFrom(), dtoRequest.getCount());
                }
            case "active":
                if (dtoRequest.getFrom() == 0 && dtoRequest.getCount() == 0) {
                    users = userDao.getByStatus(Status.ACTIVE);
                } else {
                    users = userDao.getByStatusCount(dtoRequest.getFrom(), dtoRequest.getCount());
                }
            case "super":
               if (user.getRole().equals(Role.SUPER)) {
                    users = adminDao.getUsersByRole(Role.SUPER);
                } else {
                throw new NotesErrorCode("type", "Wrong type dtoRequest");
                }
            default:
                throw new NotesErrorCode("type", "Wrong type dtoRequest");
        }
        if(dtoRequest.getSortByRating() != null){
            switch (dtoRequest.getSortByRating()) {
                case "asc":
                    users.sort(((o1, o2) -> o1.getRating().compareTo(o2.getRating())));
                case "desc":
                    users.sort(((o1, o2) -> o2.getRating().compareTo(o1.getRating())));
            }
        }
        return users;
    }*/
}
