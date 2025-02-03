package lt.techin.controllers;

import lt.techin.model.Actor;
import lt.techin.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/actors")
    public ResponseEntity<?> addActor(@RequestBody Actor actor) {
        if(actor.getName().isEmpty()) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is empty!");}

        Actor savedActor = actorService.saveActor(actor);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedActor.getId())
                        .toUri())
                .body(actor);
    }

}
