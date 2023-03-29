package net.filonova.project.notes.endpoint;

import net.filonova.project.notes.dto.request.AddNoteDtoRequest;
import net.filonova.project.notes.dto.request.AddRatingForNoteDtoRequest;
import net.filonova.project.notes.dto.request.NoteUpdateDtoRequest;
import net.filonova.project.notes.dto.request.SearchNotesDtoRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NotesEndpoint {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addNote(@RequestBody AddNoteDtoRequest request) {
        return null;
    }

    @GetMapping(value = "{idNote}")
    public String getNoteInfo(@PathVariable int idNote) {
        return null;
    }

    @PutMapping(value = "{idNote}")
    public String updateNote(@PathVariable int idNote, @RequestBody NoteUpdateDtoRequest request) {
        return null;
    }

    @DeleteMapping(value = "{idNote}")
    public void deleteNote(@PathVariable int idNote) {

    }

    @GetMapping(value = "{idNote}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCommentsByNote(@PathVariable int idNote) {
        return null;
    }

    @DeleteMapping(value = "{idNote}/comments")
    public void deleteAllCommentsForNote(@PathVariable int idNote) {

    }

    @PostMapping(value = "{idNote}/rating", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addRatingForNote(@PathVariable int idNote, @RequestBody AddRatingForNoteDtoRequest request) {

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getNotesByRequest(@RequestBody SearchNotesDtoRequest request) {
        return null;
    }
}
