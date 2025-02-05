package lt.techin.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lt.techin.dto.MovieDTO;
import lt.techin.dto.MovieMapper;
import lt.techin.model.Movie;
import lt.techin.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return ResponseEntity.ok(MovieMapper.toMovieDTOList(movieService.findAllMovies()));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable long id) {
        Optional<Movie> foundMovie = movieService.findMovieById(id);
        if (foundMovie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MovieMapper.toMovieDTO(foundMovie.get()));
    }

    @PostMapping("/movies")
    public ResponseEntity<?> postMovie(@Valid @RequestBody MovieDTO movieDTO) {
        if (movieService.existsMovieByTitle(movieDTO.title()) && movieService.existsMovieByDirector(movieDTO.director())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A movie with this title and director already exists!");
        }

        Movie savedMovie = movieService.saveMovie(MovieMapper.toMovie(movieDTO));

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedMovie.getId())
                                .toUri())
                .body(MovieMapper.toMovieDTO(savedMovie));
    }

    @GetMapping("/movies/search")
    public ResponseEntity<MovieDTO> findMovieByTitle(@RequestParam String title) {
        for (Movie movie : movieService.findAllMovies()) {
            if (!movieService.existsMovieByTitle(title)) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(MovieMapper.toMovieDTO(movieService.findMovieByTitle(title)));
    }

    @GetMapping("/movies/search/by-title")
    public ResponseEntity<List<MovieDTO>> getMoviesByTitleContaining(@RequestParam String title) {
        return ResponseEntity.ok(MovieMapper.toMovieDTOList(movieService.findAllMoviesByTitleContaining(title)));
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<?> putMovies(@PathVariable long id, @Valid @RequestBody MovieDTO movieDTO) {
        if (movieService.existsMovieById(id)) {
            Movie movieFromDb = movieService.findMovieById(id).get();

            MovieMapper.updateMovieFromDTO(movieFromDb, movieDTO);

            movieService.saveMovie(movieFromDb);

            return ResponseEntity.ok(movieDTO);
        }

        if (movieService.existsMovieByTitle(movieDTO.title()) && movieService.existsMovieByDirector(movieDTO.director())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A movie with this title and director already exists!");
        }

        Movie savedMovie = movieService.saveMovie(MovieMapper.toMovie(movieDTO));

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .replacePath("/api/movies/{id}")
                                .buildAndExpand(savedMovie.getId())
                                .toUri())
                .body(MovieMapper.toMovieDTO(savedMovie));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        if (!movieService.existsMovieById(id)) {
            return ResponseEntity.notFound().build();
        }

        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }

    //Pagination
    @GetMapping("/movies/pagination")
    public ResponseEntity<Page<Movie>> getMoviePage(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam(required = false) String sort) {
        return ResponseEntity.ok(movieService.findAllMoviePage(page, size, sort));
    }
}
