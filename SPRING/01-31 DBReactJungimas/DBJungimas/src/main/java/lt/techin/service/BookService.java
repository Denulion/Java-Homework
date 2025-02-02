package lt.techin.service;

import lt.techin.model.Book;
import lt.techin.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public boolean existsBookById(long id) {
        return bookRepository.existsById(id);
    }

    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    public boolean existsBookByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public boolean existsBookByAuthor(String author) {
        return bookRepository.existsByAuthor(author);
    }

    public Book findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public boolean existsBookByCategory(String category) {
        return bookRepository.existsByCategory(category);
    }

    public Book findBookByCategory(String category) {
        return bookRepository.findByCategory(category);
    }
}
