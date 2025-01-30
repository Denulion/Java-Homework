package lt.techin.controller;

import lt.techin.models.Movie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    List<Movie> movieList = new ArrayList<>(List.of(new Movie(1, "Brat", "Steve"),
            new Movie(2, "Brat 2", "Steve from the Kingdom")));

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/movies/{index}")
    public ResponseEntity<Movie> getMovie(@PathVariable int index) {
        if (index > movieList.size() - 1) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movieList.get(index));
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> postMovie(@RequestBody Movie movie) {
        if (movie.getId() < 0 || movie.getTitle().isEmpty() || movie.getDirector().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        movieList.add(movie);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{index}")
                                .buildAndExpand(movieList.size() - 1)
                                .toUri())
                .body(movie);
    }

    @GetMapping("/movies/search")
    public ResponseEntity<Movie> findMovie(@RequestParam String title) {

        Optional<Movie> foundMovie = movieList.stream()
                .filter(i -> i.getTitle().contains(title))
                .findFirst();

//        return foundMovie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        if (foundMovie.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(foundMovie.get());
    }

    @PutMapping("/movies/{index}")
    public ResponseEntity<?> putMovies(@PathVariable int index, @RequestBody Movie movie) {
        if (movie.getTitle().isEmpty() || movie.getDirector().isEmpty()) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Error-Message", "Your title or director is empty!");
//            return ResponseEntity.badRequest().headers(headers).build();
            return ResponseEntity.badRequest().body("Your title or director is empty!");
        }

        if (index <= movieList.size() - 1) {
            Movie movieFromDb = movieList.get(index);

            movieFromDb.setTitle(movie.getTitle());
            movieFromDb.setDirector(movie.getDirector());

            return ResponseEntity.ok(movieFromDb);
        }

        movieList.add(movie);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .replacePath("/api/movies/{index}")
                                .buildAndExpand(movieList.size() - 1)
                                .toUri())
                .body(movie);
    }

    @DeleteMapping("/movies/{index}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int index) {
        if (index > movieList.size() - 1) {
            return ResponseEntity.notFound().build();
        }

        movieList.remove(index);
        return ResponseEntity.noContent().build();
    }
}
