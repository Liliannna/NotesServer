package net.filonova.project.notes.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Role role;
    private Status status;
    private double rating;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LocalDateTime registration;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Note> notes;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Comment> comments;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> following;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> followers;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> ignore;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> ignoredBy;

    public User(String login, String password, String firstName, String lastName, String patronymic, Role role, Status status, double rating, LocalDateTime registration) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.role = role;
        this.status = status;
        this.rating = rating;
        this.registration = registration;
        this.notes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.ignore = new ArrayList<>();
        this.ignoredBy = new ArrayList<>();
    }

    public User(String login, String password, String firstName, String lastName, String patronymic, LocalDateTime registration) {
        this(login, password, firstName, lastName, patronymic, Role.REGULAR, Status.ACTIVE, 0.0, registration);
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
