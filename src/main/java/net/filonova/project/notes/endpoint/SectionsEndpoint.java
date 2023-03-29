package net.filonova.project.notes.endpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sections")
public class SectionsEndpoint {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addSection(@RequestBody String nameSection) {
        return null;
    }

    @PutMapping(value = "{idSection}")
    public String updateSection(@PathVariable int idSection) {
        return null;
    }

    @DeleteMapping(value = "{idSection}")
    public void deleteSection(@PathVariable int idSection) {

    }

    @GetMapping(value = "{idSection}")
    public String getSectionInfo(@PathVariable int idSection) {
        return null;
    }

    @GetMapping
    public String getAllSection() {
        return null;
    }

}
