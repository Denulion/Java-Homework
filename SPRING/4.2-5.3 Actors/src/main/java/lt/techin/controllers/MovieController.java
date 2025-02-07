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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return ResponseEntity.ok(MovieMapper.toMovieDTOList(movieService.findAllMovies()));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable long id) {
        Optional<Movie> foundMovie = movieService.findMovieById(id);
        if (foundMovie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MovieMapper.toMovieDTO(foundMovie.get()));
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/movies/search")
    public ResponseEntity<MovieDTO> findMovieByTitle(@RequestParam String title) {
        for (Movie movie : movieService.findAllMovies()) {
            if (!movieService.existsMovieByTitle(title)) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(MovieMapper.toMovieDTO(movieService.findMovieByTitle(title)));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/movies/search/by-title")
    public ResponseEntity<List<MovieDTO>> getMoviesByTitleContaining(@RequestParam String title) {
        return ResponseEntity.ok(MovieMapper.toMovieDTOList(movieService.findAllMoviesByTitleContaining(title)));
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        if (!movieService.existsMovieById(id)) {
            return ResponseEntity.notFound().build();
        }

        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }

    //Pagination
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/movies/pagination")
    public ResponseEntity<Page<MovieDTO>> getMoviePage(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam(required = false) String sort) {
        Page<Movie> movies = movieService.findAllMoviePage(page, size, sort);
        Page<MovieDTO> dtoMovies = movies.map(MovieMapper::toMovieDTO);

        return ResponseEntity.ok(dtoMovies);
    }
}
