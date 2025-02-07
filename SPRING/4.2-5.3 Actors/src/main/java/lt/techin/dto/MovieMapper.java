package lt.techin.dto;

import lt.techin.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static List<MovieDTO> toMovieDTOList(List<Movie> movieList) {

        return movieList.stream()
                .map(movie -> new MovieDTO(movie.getId(), movie.getTitle(), movie.getDirector(),
                        ScreeningMapper.toScreeningDTOList(movie), ActorMapper.toActorDTOList(movie)))
                .collect(Collectors.toList());
    }

    public static MovieDTO toMovieDTO(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getDirector(), ScreeningMapper.toScreeningDTOList(movie), ActorMapper.toActorDTOList(movie));
    }

    public static Movie toMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();

        updateMovieFromDTO(movie, movieDTO);

        return movie;
    }

    public static void updateMovieFromDTO(Movie movie, MovieDTO movieDTO) {
        movie.setTitle(movieDTO.title());
        movie.setDirector(movieDTO.director());
        movie.setScreenings(ScreeningMapper.toScreeningListFromDTO(movieDTO.screenings()));
        movie.setActors(ActorMapper.toActorListFromDTO(movieDTO.actors()));
    }
}
