package lt.techin.repositories;

import lt.techin.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    boolean existsByName(String name);

    Actor findActorByName(String name);
}
