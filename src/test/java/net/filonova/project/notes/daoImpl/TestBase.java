package net.filonova.project.notes.daoImpl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TestBase {
  /* protected UserDao userDao = new UserDaoImpl();
    protected SectionDao sectionDao = new SectionDaoImpl();
    protected NoteDao noteDao = new NoteDaoImpl();
    protected CommentDao commentDao = new CommentDaoImpl();
    protected CommonDao commonDao = new CommonDaoImpl();

    private static boolean setUpIsDone = false;

    @BeforeAll
    public static void setUp() {
        if (!setUpIsDone) {
            boolean initSqlSessionFactory = MyBatisUtils.initSqlSessionFactory();
            if (!initSqlSessionFactory) {
                throw new RuntimeException("Can't create connection, stop");
            }
            setUpIsDone = true;
        }
    }

    @BeforeEach
    public void clearDatabase() {
        commonDao.clear();
    }

    protected User insertUser(String login, String password, String firstName, String lastName, String patronymic, LocalDateTime datetimeRegistration) {
        User user = new User(login, password, firstName, lastName, patronymic, datetimeRegistration);
        userDao.insert(user);
        assertNotEquals(0, user.getId());
        return user;
    }

    protected void updateUser(User user, String actualPassword, int idUserSession) {
        userDao.update(user, actualPassword, idUserSession);
    }

    protected void deleteUser(int idUserSession, String password) {
        userDao.deleteUser(idUserSession, password);
    }

    protected void login(User user) {
        userDao.login(user);
    }

    protected void logout(int idUserSession) {
        userDao.logout(idUserSession);
    }

    protected List<User> getAllSession() {
        return userDao.getAllSession();
    }

    protected User getUserById(int idUser) {
        return userDao.getById(idUser);
    }

    protected List<User> getAllUsers() {
        return userDao.getAll();
    }

    protected List<User> getByStatus(Status status) {
        return userDao.getByStatus(status);
    }

    protected List<User> getFollowings(int idUserSession) {
        return userDao.getFollowings(idUserSession);
    }

    protected List<User> getFollowers(int idUserSession) {
        return userDao.getFollowers(idUserSession);
    }

    protected List<User> getIgnore(int idUserSession) {
        return userDao.getIgnore(idUserSession);
    }

    protected List<User> getIgnoredBy(int idUserSession) {
        return userDao.getIgnoredBy(idUserSession);
    }

    protected void addFollowing(int idUserSession, int idFollowing) {
        userDao.addFollowing(idUserSession, idFollowing);
    }

    protected void addIgnore(int idUserSession, int idIgnoredUser) {
        userDao.addIgnore(idUserSession, idIgnoredUser);
    }

    protected void deleteFollowing(int idUserSession, int idFollowing) {
        userDao.deleteFollowing(idUserSession, idFollowing);
    }

    protected void deleteIgnoredUser(int idUserSession, int idIgnoredUser) {
        userDao.deleteIgnoredUser(idUserSession, idIgnoredUser);
    }

    protected Section insertSection(User user, String name) {
        Section section = new Section(name);
        return sectionDao.insert(user, section);
    }

    protected void updateSection(Section section, User user) {
        sectionDao.update(section, user);
    }

    protected Section getSectionById(int idSection) {
        return sectionDao.getById(idSection);
    }

    protected List<Section> getAllSections() {
        return sectionDao.getAll();
    }

    protected void deleteSection(Section section, User user) {
        sectionDao.deleteSection(section, user);
    }

    protected Note insertNote(User user, Section section, Note note) {
        return noteDao.insert(user, section, note);
    }

    protected void insertNoteRevision(Note note) {
        noteDao.insertNoteRevision(note);
    }

    protected void updateNote(Note note, Section section, User user) {
        noteDao.update(note, section, user);
    }

    protected Note getByIdNote(int idNote) {
        return noteDao.getById(idNote);
    }

    protected List<Note> getAllNote() {
        return noteDao.getAll();
    }

    protected List<Note> getAllNotesByIdUser(int idUser) {
        return noteDao.getAllNotesByIdUser(idUser);
    }

    protected List<Note> getAllNotesByIdSection(int idSection) {
        return noteDao.getNotesByIdSection(idSection);
    }

    protected List<Note> getAllNotesByRating(double rating) {
        return noteDao.getAllNotesByRating(rating);
    }

    protected void addRating(Note note, double rating) {
        noteDao.addRating(note, rating);
    }

    protected void deleteNote(Note note, User user) {
        noteDao.deleteNote(note, user);
    }

    protected void deleteAllNoteByUser(int idUser) {
        noteDao.deleteAllNoteByUser(idUser);
    }

    protected void deleteNotesBySection(int idSection) {
        noteDao.deleteNotesBySection(idSection);
    }

    protected Comment insertComment(User user, Note note, Comment comment) {
        return commentDao.insert(user, note, comment);
    }

    protected void updateComment(Comment comment, User user) {
        commentDao.update(comment, user);
    }

    protected Comment getByIdComment(int idComment) {
        return commentDao.getById(idComment);
    }

    protected List<Comment> getAllCommentsByIdUser(int idUser) {
        return commentDao.getAllCommentsByIdUser(idUser);
    }

    protected List<Comment> getAllCommentsByIdNoteRevision(int idNoteRevision) {
        return commentDao.getAllCommentsByIdNoteRevision(idNoteRevision);
    }

    protected void deleteCommentById(Comment comment, User user) {
        commentDao.deleteById(comment, user);
    }

    protected void deleteAllCommentsByNoteRevision(int idNoteRevision) {
        commentDao. deleteAllCommentsByNoteRevision(idNoteRevision);
    }

    protected void deleteAllCommentsByUser(int idUser) {
        commentDao.deleteAllCommentsByUser(idUser);
    }*/
}
