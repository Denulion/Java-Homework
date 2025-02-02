package lt.techin.controller;

import lt.techin.model.Book;
import lt.techin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Optional<Book> foundBook = bookService.findBookById(id);
        if (foundBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(foundBook.get());
    }

    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title or author cannot be empty");
        }

        Book savedBook = bookService.saveBook(book);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedBook.getId())
                                .toUri())
                .body(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book book) {
        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title or author cannot be empty");
        }

        if (bookService.existsBookById(id)) {
            Book bookFromDb = bookService.findBookById(id).get();

            bookFromDb.setTitle(book.getTitle());
            bookFromDb.setAuthor(book.getAuthor());

            return ResponseEntity.ok(bookService.saveBook(bookFromDb));
        }

        Book savedBook = bookService.saveBook(book);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .replacePath("/api/books/{id}")
                                .buildAndExpand(savedBook.getId())
                                .toUri())
                .body(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        if (!bookService.existsBookById(id)) {
            return ResponseEntity.notFound().build();
        }

        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/books/by-title/")
    public ResponseEntity<Book> getBookByTitle(@RequestParam String title) {
        for (Book book : bookService.findAllBooks()) {
            if (!bookService.existsBookByTitle(title)) {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(bookService.findBookByTitle(title));
    }

    @GetMapping("/books/by-author/")
    public ResponseEntity<Book> getBookByAuthor(@RequestParam String title) {
        for (Book book : bookService.findAllBooks()) {
            if (!bookService.existsBookByAuthor(title)) {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(bookService.findBookByAuthor(title));
    }

    @GetMapping("/books/by-category/")
    public ResponseEntity<Book> getBookByCategory(@RequestParam String title) {
        for (Book book : bookService.findAllBooks()) {
            if (!bookService.existsBookByCategory(title)) {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(bookService.findBookByCategory(title));
    }
}
