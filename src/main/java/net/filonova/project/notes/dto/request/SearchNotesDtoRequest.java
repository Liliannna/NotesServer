package net.filonova.project.notes.dto.request;

import lombok.Value;

@Value
public class SearchNotesDtoRequest {

    int idSection;
    String sortByRating;
    String tags;
    boolean allTags;
    String timeFrom;
    String timeTo;
    int idUser;
    String include;
    boolean comments;
    boolean allVersion;
    boolean commentVersion;
    int from;
    int count;
}
