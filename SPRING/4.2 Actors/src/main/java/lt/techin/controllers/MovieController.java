package lt.techin.controllers;

import lt.techin.model.Movie;
import lt.techin.services.MovieService;
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
    public MovieController(MovieService movieService){this.movieService = movieService;}

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies(){return ResponseEntity.ok(movieService.findAllMovies());}

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id) {
        Optional<Movie> foundMovie = movieService.findMovieById(id);
        if(foundMovie.isEmpty()) {return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(foundMovie.get());
    }

    @PostMapping("/movies")
    public ResponseEntity<?> postMovie(@RequestBody Movie movie) {
        if (movie.getTitle().isEmpty() || movie.getDirector().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title or director is empty");
        }

        if (movieService.existsMovieByTitle(movie.getTitle()) && movieService.existsMovieByDirector(movie.getDirector())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A movie with this title and director already exists!");
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
        for (Movie movie : movieService.findAllMovies()) {
            if (!movieService.existsMovieByTitle(title)) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(movieService.findMovieByTitle(title));
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<?> putMovies(@PathVariable long id, @RequestBody Movie movie) {
        if (movie.getTitle().isEmpty() || movie.getDirector().isEmpty()) {
            return ResponseEntity.badRequest().body("Your title or director is empty!");
        }

        if (movieService.existsMovieById(id)) {
            Movie movieFromDb = movieService.findMovieById(id).get();

            movieFromDb.setTitle(movie.getTitle());
            movieFromDb.setDirector(movie.getDirector());
            movieFromDb.setScreenings(movie.getScreenings());
            movieFromDb.setActors(movie.getActors());

            return ResponseEntity.ok(movieService.saveMovie(movieFromDb));
        }

        if (movieService.existsMovieByTitle(movie.getTitle()) && movieService.existsMovieByTitle(movie.getTitle())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A movie with this title and director already exists!");
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
