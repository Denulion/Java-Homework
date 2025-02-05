package lt.techin.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lt.techin.model.Actor;
import lt.techin.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class ActorMapper {


    public static Actor toActor(@Valid ActorDTO actorDTO) {
        Actor actor = new Actor();

        actor.setName(actorDTO.name());

        return actor;
    }

    public static ActorDTO toActorDTO(Actor actor) {
        return new ActorDTO(actor.getId(), actor.getName());
    }

    public static List<ActorDTO> toActorDTOList(Movie movie) {

        return movie.getActors().stream()
                .map(ActorMapper::toActorDTO)
                .toList();
    }

    public static List<Actor> toActorListFromDTO(List<ActorDTO> actorsDTO) {
        return actorsDTO.stream().map(ActorMapper::toActor).toList();
    }
}
