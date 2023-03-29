package net.filonova.project.notes.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private int id;
    private int idSession;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDateTime datetimeRegistration;
    private Role role;
    private Status status;
    private double rating;
    private List<Section> sections;
    private List<Note> notes;
    private List<Comment> comments;
    private List<User> following;
    private List<User> followers;
    private List<User> ignore;
    private List<User> ignoredBy;

    public User() {
    }

    public User(int id, int idSession, String login, String password, String firstName, String lastName, String patronymic,
                LocalDateTime datetimeRegistration, Role role, Status status, double rating) {
        this.id = id;
        this.idSession = idSession;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.datetimeRegistration = datetimeRegistration;
        this.role = role;
        this.status = status;
        this.rating = rating;
        this.sections = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.ignore = new ArrayList<>();
        this.ignoredBy = new ArrayList<>();
    }

    public User(String login, String password, String firstName, String lastName, String patronymic, LocalDateTime datetimeRegistration) {
        this(0, 0, login, password, firstName, lastName, patronymic, datetimeRegistration, Role.REGULAR, Status.ACTIVE, 0.0);
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

     public User(String password, String firstName, String lastName, String patronymic) {
         this.password = password;
         this.firstName = firstName;
         this.lastName = lastName;
         this.patronymic = patronymic;
     }
}
