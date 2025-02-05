package lt.techin.services;

import lt.techin.dto.MovieDTO;
import lt.techin.model.Movie;
import lt.techin.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    ;

    public boolean existsMovieById(long id) {
        return movieRepository.existsById(id);
    }

    public Optional<Movie> findMovieById(long id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovieById(long id) {
        movieRepository.deleteById(id);
    }

    public boolean existsMovieByTitle(String title) {
        return movieRepository.existsByTitle(title);
    }

    public Movie findMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> findAllMoviesByTitleContaining(String title) {
        return movieRepository.findAllByTitleContaining(title);
    }

    public boolean existsMovieByDirector(String director) {
        return movieRepository.existsByDirector(director);
    }

    public Movie findMovieByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    public Page<Movie> findAllMoviePage(int page, int size, String sort) {
        if (sort == null) {
            Pageable pageable = PageRequest.of(page, size);

            return movieRepository.findAll(pageable);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return movieRepository.findAll(pageable);
    }
}
