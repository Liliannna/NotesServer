package net.filonova.project.notes.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUsersDtoResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String datetimeRegistration;
    private boolean online;
    private boolean deleted;
    private boolean admin;
    private double rating;

    public SearchUsersDtoResponse(int id, String firstName, String lastName,
                                  String patronymic, String login, String datetimeRegistration,
                                  boolean online, boolean deleted, boolean admin, double rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.datetimeRegistration = datetimeRegistration;
        this.online = online;
        this.deleted = deleted;
        this.admin = admin;
        this.rating = rating;
    }

    public SearchUsersDtoResponse(int id, String firstName, String lastName, String patronymic, String login,
                                  String datetimeRegistration, boolean online, boolean deleted, double rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.datetimeRegistration = datetimeRegistration;
        this.online = online;
        this.deleted = deleted;
        this.rating = rating;
    }
}
