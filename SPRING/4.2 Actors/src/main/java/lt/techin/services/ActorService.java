package lt.techin.services;

import lt.techin.model.Actor;
import lt.techin.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor saveActor(Actor actor){return actorRepository.save(actor);}

    public boolean existsActorByName(String name){return  actorRepository.existsByName(name);}

    public Actor findActorByName(String name){return actorRepository.findActorByName(name);}
}
