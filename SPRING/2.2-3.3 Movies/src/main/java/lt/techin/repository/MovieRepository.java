package lt.techin.repository;

import lt.techin.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByTitle(String title);

    Movie findByTitle(String title);

    boolean existsByAuthor(String author);

    Movie findByAuthor(String author);
}
