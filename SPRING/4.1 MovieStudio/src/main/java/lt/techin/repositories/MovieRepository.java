package lt.techin.repositories;

import lt.techin.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);

    Movie findByTitle(String title);

    boolean existsByDirector(String director);

    Movie findByDirector(String director);
}

