package net.filonova.project.notes.dto.request;

import lombok.Value;

@Value
public class SearchUsersDtoRequest {
    String sortByRating;
    String type;
    int from;
    int count;
}
