package net.filonova.project.notes.endpoint;

import net.filonova.project.notes.dto.request.AddCommentDtoRequest;
import net.filonova.project.notes.dto.request.CommentUpdateDtoRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentsEndpoint {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addComment(@RequestBody AddCommentDtoRequest request) {
        return null;
    }

    @PutMapping(value = "{idComment}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateComment(@PathVariable int idComment, @RequestBody CommentUpdateDtoRequest request) {
        return null;
    }

    @DeleteMapping(value = "{idComment}")
    public void deleteComment(@PathVariable int idComment) {

    }
}
