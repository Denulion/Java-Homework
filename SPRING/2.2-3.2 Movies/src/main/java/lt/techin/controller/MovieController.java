package lt.techin.controller;

import lt.techin.models.Movie;
import lt.techin.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id) {
//        if (!movieService.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This movie doesn't exist!");
//        }

        Optional<Movie> foundMovie = movieService.findMovieById(id);

        if (foundMovie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundMovie.get());
    }

    @PostMapping("/movies")
    public ResponseEntity<?> postMovie(@RequestBody Movie movie) {
        if (movie.getTitle().isEmpty() || movie.getAuthor().isEmpty()) {
//            return ResponseEntity.badRequest().build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title or author is empty");
        }

        Movie savedMovie = movieService.saveMovie(movie);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedMovie.getId())
                                .toUri())
                .body(movie);
    }

    @GetMapping("/movies/search")
    public ResponseEntity<Movie> findMovieByTitle(@RequestParam String title) {

//        Optional<Movie> foundMovie = movieService.findAllMovies().stream()
//                .filter(i -> movieService.existsMovieByTitle(title))
//                .findFirst();

        for (Movie movie : movieService.findAllMovies()) {
            if (!movieService.existsMovieByTitle(title)) {
                return ResponseEntity.notFound().build();
            }
        }

//        return foundMovie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        return ResponseEntity.ok(movieService.findMovieByTitle(title));
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<?> putMovies(@PathVariable long id, @RequestBody Movie movie) {
        if (movie.getTitle().isEmpty() || movie.getAuthor().isEmpty()) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Error-Message", "Your title or director is empty!");
//            return ResponseEntity.badRequest().headers(headers).build();
            return ResponseEntity.badRequest().body("Your title or director is empty!");
        }

        if (movieService.existsMovieById(id)) {
            Movie movieFromDb = movieService.findMovieById(id).get();

            movieFromDb.setTitle(movie.getTitle());
            movieFromDb.setAuthor(movie.getAuthor());

            return ResponseEntity.ok(movieService.saveMovie(movieFromDb));
        }

        Movie savedMovie = movieService.saveMovie(movie);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .replacePath("/api/movies/{id}")
                                .buildAndExpand(savedMovie.getId())
                                .toUri())
                .body(movie);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        if (!movieService.existsMovieById(id)) {
            return ResponseEntity.notFound().build();
        }

        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }
}
