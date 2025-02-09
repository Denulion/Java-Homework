package lt.techin.controllers;

import jakarta.validation.Valid;
import lt.techin.dto.ActorDTO;
import lt.techin.dto.ActorMapper;
import lt.techin.model.Actor;
import lt.techin.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actors")
    public ResponseEntity<?> addActor(@Valid @RequestBody ActorDTO actorDTO) {
        Actor savedActor = actorService.saveActor(ActorMapper.toActor(actorDTO));

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedActor.getId())
                                .toUri())
                .body(ActorMapper.toActorDTO(savedActor));
    }

}
