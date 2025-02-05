package lt.techin.dto;

import lt.techin.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static List<MovieDTO> toMovieDTOList(List<Movie> movieList) {

        return movieList.stream()
                .map(movie -> new MovieDTO(movie.getId(), movie.getTitle(), movie.getDirector(),
                        movie.getScreenings(), movie.getActors()))
                .collect(Collectors.toList());
    }

    public static MovieDTO toMovieDTO(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getScreenings(), movie.getActors());
    }

    public static Movie toMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();

        updateMovieFromDTO(movie, movieDTO);

        return movie;
    }

    public static void updateMovieFromDTO(Movie movie, MovieDTO movieDTO) {
        movie.setTitle(movieDTO.title());
        movie.setDirector(movieDTO.director());
        movie.setScreenings(movieDTO.screenings());
        movie.setActors(movieDTO.actors());
    }
}
