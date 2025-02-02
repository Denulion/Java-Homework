package lt.techin.repository;

import lt.techin.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitle(String title);

    Book findByTitle(String title);

    boolean existsByAuthor(String author);

    Book findByAuthor(String author);

    boolean existsByCategory(String category);

    Book findByCategory(String category);
}
