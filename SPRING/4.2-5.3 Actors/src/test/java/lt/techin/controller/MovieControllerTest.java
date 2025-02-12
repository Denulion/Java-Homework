package lt.techin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lt.techin.controllers.MovieController;
import lt.techin.model.Actor;
import lt.techin.model.Movie;
import lt.techin.model.Screening;
import lt.techin.security.SecurityConfig;
import lt.techin.services.MovieService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MovieController.class)
@Import(SecurityConfig.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    //happy path
    @Test
    @WithMockUser
    void getMovies_withFindAll_thenReturnAllAnd200() throws Exception {
        //given
        Movie movie1 = new Movie("First Title", "Direktor Direktoraitis",
                List.of(),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        Movie movie2 = new Movie("Second Title", "Direktoravicius",
                List.of(new Screening("Theatre", LocalTime.of(22, 30), LocalDate.of(2025, 5, 10))),
                List.of(new Actor("Actoraitis"), new Actor("Deimantaitis")));

        List<Movie> movies = List.of(movie1, movie2);

        given(movieService.findAllMovies()).willReturn(movies);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists())
                .andExpect(jsonPath("[0].title").value("First Title"))
                .andExpect(jsonPath("[0].director").value("Direktor Direktoraitis"))
                .andExpect(jsonPath("[0].screenings").isArray())
                .andExpect(jsonPath("[0].screenings", Matchers.hasSize(0)))
                .andExpect(jsonPath("[0].actors").isArray())
                .andExpect(jsonPath("[0].actors", Matchers.hasSize(2)))
                .andExpect(jsonPath("[0].actors.[0].id").exists())
                .andExpect(jsonPath("[0].actors.[0].name").value("Actoraitis"))
                .andExpect(jsonPath("[0].actors.[1].id").exists())
                .andExpect(jsonPath("[0].actors.[1].name").value("Aktoravicius"))

                .andExpect(jsonPath("[1].id").exists())
                .andExpect(jsonPath("[1].title").value("Second Title"))
                .andExpect(jsonPath("[1].director").value("Direktoravicius"))
                .andExpect(jsonPath("[1].screenings").isArray())
                .andExpect(jsonPath("[1].screenings", Matchers.hasSize(1)))
                .andExpect(jsonPath("[1].screenings.[0].screeningTheatre").value("Theatre"))
                .andExpect(jsonPath("[1].screenings.[0].time").value("22:30:00"))
                .andExpect(jsonPath("[1].screenings.[0].date").value("2025-05-10"))
                .andExpect(jsonPath("[1].actors").isArray())
                .andExpect(jsonPath("[1].actors", Matchers.hasSize(2)))
                .andExpect(jsonPath("[1].actors.[0].id").exists())
                .andExpect(jsonPath("[1].actors.[0].name").value("Actoraitis"))
                .andExpect(jsonPath("[1].actors.[1].id").exists())
                .andExpect(jsonPath("[1].actors.[1].name").value("Deimantaitis"));

        Mockito.verify(movieService, times(1)).findAllMovies();
    }

    //unhappy path
    @Test
    void getMovies_whenFindAllUnauthenticated_thenRespond401() throws Exception {
        //given
        Movie movie1 = new Movie("First Title", "Direktor Direktoraitis",
                List.of(),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        Movie movie2 = new Movie("Second Title", "Direktoravicius",
                List.of(new Screening("Theatre", LocalTime.of(22, 30), LocalDate.of(2025, 5, 10))),
                List.of(new Actor("Actoraitis"), new Actor("Deimantaitis")));

        List<Movie> movies = List.of(movie1, movie2);

        given(movieService.findAllMovies()).willReturn(movies);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                //then
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).findAllMovies();
    }

    // unhappy path
    @Test
    @WithMockUser
    void getMovies_whenFindAllEmpty_thenReturnEmptyListAnd200() throws Exception {
        // given
        given(movieService.findAllMovies()).willReturn(List.of());

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));

        Mockito.verify(movieService, times(1)).findAllMovies();
    }

    //happy path
    @Test
    @WithMockUser(authorities = "ROLE_USER")
    void getMovie_whenFindOne_thenReturnOneAnd200() throws Exception {
        //given
        long movieId = 1L;

        Movie movie1 = new Movie("First Title", "Direktor Direktoraitis",
                List.of(),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        given(movieService.findMovieById(movieId)).willReturn(Optional.of(movie1));

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/{id}", movieId))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("First Title"))
                .andExpect(jsonPath("director").value("Direktor Direktoraitis"))
                .andExpect(jsonPath("screenings").isArray())
                .andExpect(jsonPath("screenings", Matchers.hasSize(0)))
                .andExpect(jsonPath("actors").isArray())
                .andExpect(jsonPath("actors", Matchers.hasSize(2)))
                .andExpect(jsonPath("actors[0].id").exists())
                .andExpect(jsonPath("actors[0].name").value("Actoraitis"))
                .andExpect(jsonPath("actors[1].id").exists())
                .andExpect(jsonPath("actors[1].name").value("Aktoravicius"));

        Mockito.verify(movieService, times(1)).findMovieById(movieId);
    }

    //unhappy path
    @Test
    @WithMockUser(authorities = "ROLE_USER")
    void getMovie_whenFindNone_thenRespond404() throws Exception {
        //given
        long movieId = 100L;

        given(movieService.findMovieById(movieId)).willReturn(Optional.empty());

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/{id}", movieId))
                //then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(1)).findMovieById(movieId);
    }

    //unhappy path
    @Test
    void getMovie_whenFindOneUnauthenticated_thenRespond401() throws Exception {
        //given
        long movieId = 1L;

        Movie movie1 = new Movie("First Title", "Direktor Direktoraitis",
                List.of(),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        given(movieService.findMovieById(movieId)).willReturn(Optional.of(movie1));

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/{id}", movieId))
                //then
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).findMovieById(movieId);
    }

    // happy path
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void addMovie_whenAdminAddMovie_thenReturnAnd201() throws Exception {
        //given
        Movie movie = new Movie("First Title", "Direktor Direktoraitis",
                List.of(new Screening("Theatre", LocalTime.of(22, 30), LocalDate.of(2025, 5, 10))),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        given(movieService.saveMovie(ArgumentMatchers.any(Movie.class))).willReturn(movie);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").value("First Title"))
                .andExpect(jsonPath("director").value("Direktor Direktoraitis"))
                .andExpect(jsonPath("screenings").isArray())
                .andExpect(jsonPath("screenings", Matchers.hasSize(1)))
                .andExpect(jsonPath("screenings[0].screeningTheatre").value("Theatre"))
                .andExpect(jsonPath("screenings[0].time").value("22:30:00"))
                .andExpect(jsonPath("screenings[0].date").value("2025-05-10"))
                .andExpect(jsonPath("actors").isArray())
                .andExpect(jsonPath("actors", Matchers.hasSize(2)))
                .andExpect(jsonPath("actors[0].id").exists())
                .andExpect(jsonPath("actors[0].name").value("Actoraitis"))
                .andExpect(jsonPath("actors[1].id").exists())
                .andExpect(jsonPath("actors[1].name").value("Aktoravicius"));

        Mockito.verify(movieService, times(1)).saveMovie(ArgumentMatchers.any(Movie.class));
    }

    // unhappy path
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void addMovie_whenAdminFailsValidation_thenReturnAnd400() throws Exception {
        //given
        Movie movie = new Movie("Invalid Movie", "Invalid Director",
                List.of(),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        given(movieService.saveMovie(ArgumentMatchers.any(Movie.class))).willReturn(movie);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.screenings").value("List of screenings is empty!"));

        Mockito.verify(movieService, times(0)).saveMovie(ArgumentMatchers.any(Movie.class));
    }

    // unhappy path
    @Test
    @WithMockUser(authorities = "ROLE_USER")
    void addMovie_whenUserTriesToSave_thenReturnAnd403() throws Exception {
        //given
        Movie movie = new Movie("First Title", "Direktor Direktoraitis",
                List.of(new Screening("Theatre", LocalTime.of(22, 30), LocalDate.of(2025, 5, 10))),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        given(movieService.saveMovie(ArgumentMatchers.any(Movie.class))).willReturn(movie);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                //then
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).saveMovie(ArgumentMatchers.any(Movie.class));
    }

    // unhappy path
    @Test
    void addMovie_whenUnauthenticated_thenReturnAnd401() throws Exception {
        //given
        Movie movie = new Movie("First Title", "Direktor Direktoraitis",
                List.of(new Screening("Theatre", LocalTime.of(22, 30), LocalDate.of(2025, 5, 10))),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        given(movieService.saveMovie(ArgumentMatchers.any(Movie.class))).willReturn(movie);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                //then
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).saveMovie(ArgumentMatchers.any(Movie.class));
    }

    // happy path
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void updateMovie_whenAdminUpdatesMovie_thenReturnAnd200() throws Exception {
        //given
        long movieId = 1L;

        Movie existingMovie = new Movie("Old Title", "Old Director",
                List.of(new Screening("Old Theatre", LocalTime.of(18, 00), LocalDate.of(2025, 5, 5))),
                List.of(new Actor("Old Actor")));

        Movie updatedMovie = new Movie("New Title", "New Director",
                List.of(new Screening("New Theatre", LocalTime.of(20, 00), LocalDate.of(2025, 6, 10))),
                List.of(new Actor("New Actor")));

        given(movieService.existsMovieById(movieId)).willReturn(true);
        given(movieService.findMovieById(movieId)).willReturn(Optional.of(existingMovie));
        given(movieService.saveMovie(ArgumentMatchers.any(Movie.class))).willReturn(updatedMovie);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/movies/{id}", movieId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMovie)))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("New Title"))
                .andExpect(jsonPath("director").value("New Director"))
                .andExpect(jsonPath("screenings").isArray())
                .andExpect(jsonPath("screenings", Matchers.hasSize(1)))
                .andExpect(jsonPath("screenings[0].screeningTheatre").value("New Theatre"))
                .andExpect(jsonPath("screenings[0].time").value("20:00:00"))
                .andExpect(jsonPath("screenings[0].date").value("2025-06-10"))
                .andExpect(jsonPath("actors").isArray())
                .andExpect(jsonPath("actors", Matchers.hasSize(1)))
                .andExpect(jsonPath("actors[0].id").exists())
                .andExpect(jsonPath("actors[0].name").value("New Actor"));

        Mockito.verify(movieService, times(1)).saveMovie(ArgumentMatchers.any(Movie.class));
    }

    // unhappy path
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void updateMovie_whenAdminUpdatesNonExistentMovie_thenReturnAnd404() throws Exception {
        //given
        long movieId = 100L;
        Movie updatedMovie = new Movie("Updated Title", "Updated Director",
                List.of(new Screening("Theatre", LocalTime.of(21, 30), LocalDate.of(2025, 7, 15))),
                List.of(new Actor("Updated Actor")));

        given(movieService.existsMovieById(movieId)).willReturn(false);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/movies/{id}", movieId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMovie)))
                //then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).saveMovie(ArgumentMatchers.any(Movie.class));
    }

    // unhappy path
    @Test
    @WithMockUser(authorities = "ROLE_USER")
    void updateMovie_whenUserTriesToUpdateMovie_thenReturnAnd403() throws Exception {
        //given
        long movieId = 1L;
        Movie updatedMovie = new Movie("Updated Title", "Updated Director",
                List.of(new Screening("Theatre", LocalTime.of(21, 30), LocalDate.of(2025, 7, 15))),
                List.of(new Actor("Updated Actor")));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/movies/{id}", movieId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMovie)))
                //then
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).saveMovie(ArgumentMatchers.any(Movie.class));
    }

    // unhappy path
    @Test
    void updateMovie_whenUnauthenticatedUserTriesToUpdateMovie_thenReturnAnd401() throws Exception {
        //given
        long movieId = 1L;
        Movie updatedMovie = new Movie("Updated Title", "Updated Director",
                List.of(new Screening("Theatre", LocalTime.of(21, 30), LocalDate.of(2025, 7, 15))),
                List.of(new Actor("Updated Actor")));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/movies/{id}", movieId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMovie)))
                //then
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).saveMovie(ArgumentMatchers.any(Movie.class));
    }

    // happy path
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deleteMovie_whenAdminDeletesMovie_thenReturnAnd204() throws Exception {
        //given
        long movieId = 1L;

        given(movieService.existsMovieById(movieId)).willReturn(true);
        Mockito.doNothing().when(movieService).deleteMovieById(movieId);

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/{id}", movieId))
                //then
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(1)).deleteMovieById(movieId);
    }

    // unhappy path
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deleteMovie_whenAdminDeletesNonExistentMovie_thenReturnAnd404() throws Exception {
        //given
        long movieId = 100L;

        given(movieService.existsMovieById(movieId)).willReturn(false);

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/{id}", movieId))
                //then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).deleteMovieById(movieId);
    }

    // unhappy path
    @Test
    @WithMockUser(authorities = "ROLE_USER")
    void deleteMovie_whenUserTriesToDeleteMovie_thenReturnAnd403() throws Exception {
        //given
        long movieId = 1L;

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/{id}", movieId))
                //then
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).deleteMovieById(movieId);
    }

    // unhappy path
    @Test
    void deleteMovie_whenUnauthenticatedUserTriesToDeleteMovie_thenReturnAnd401() throws Exception {
        //given
        long movieId = 1L;

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/{id}", movieId))
                //then
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).deleteMovieById(movieId);
    }

    //happy path
    @Test
    @WithMockUser(authorities = "ROLE_USER")
    void getMoviePage_whenValidParams_thenReturnAnd200() throws Exception {
        // given
        int page = 0;
        int size = 2;
        String sort = null;

        Movie movie1 = new Movie("First Title", "Direktor Direktoraitis",
                List.of(),
                List.of(new Actor("Actoraitis"), new Actor("Aktoravicius")));

        Movie movie2 = new Movie("Second Title", "Direktoravicius",
                List.of(new Screening("Theatre", LocalTime.of(22, 30), LocalDate.of(2025, 5, 10))),
                List.of(new Actor("Actoraitis"), new Actor("Deimantaitis")));

        Page<Movie> moviePage = new PageImpl<>(List.of(movie1, movie2));
        given(movieService.findAllMoviePage(page, size, sort)).willReturn(moviePage);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/pagination")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("content").isArray())
                .andExpect(jsonPath("content", Matchers.hasSize(2)))

                .andExpect(jsonPath("content[0].title").value("First Title"))
                .andExpect(jsonPath("content[0].director").value("Direktor Direktoraitis"))
                .andExpect(jsonPath("content[0].screenings").isArray())
                .andExpect(jsonPath("content[0].screenings", Matchers.hasSize(0)))
                .andExpect(jsonPath("content[0].actors").isArray())
                .andExpect(jsonPath("content[0].actors", Matchers.hasSize(2)))
                .andExpect(jsonPath("content[0].actors[0].id").exists())
                .andExpect(jsonPath("content[0].actors[0].name").value("Actoraitis"))
                .andExpect(jsonPath("content[0].actors[1].id").exists())
                .andExpect(jsonPath("content[0].actors[1].name").value("Aktoravicius"))

                .andExpect(jsonPath("content[1].title").value("Second Title"))
                .andExpect(jsonPath("content[1].director").value("Direktoravicius"))
                .andExpect(jsonPath("content[1].screenings").isArray())
                .andExpect(jsonPath("content[1].screenings", Matchers.hasSize(1)))
                .andExpect(jsonPath("content[1].screenings[0].screeningTheatre").value("Theatre"))
                .andExpect(jsonPath("content[1].screenings[0].time").value("22:30:00"))
                .andExpect(jsonPath("content[1].screenings[0].date").value("2025-05-10"))
                .andExpect(jsonPath("content[1].actors").isArray())
                .andExpect(jsonPath("content[1].actors", Matchers.hasSize(2)))
                .andExpect(jsonPath("content[1].actors[0].id").exists())
                .andExpect(jsonPath("content[1].actors[0].name").value("Actoraitis"))
                .andExpect(jsonPath("content[1].actors[1].id").exists())
                .andExpect(jsonPath("content[1].actors[1].name").value("Deimantaitis"));

        Mockito.verify(movieService, times(1)).findAllMoviePage(page, size, sort);
    }

    // happy path sorting
    @Test
    @WithMockUser(authorities = "ROLE_USER")
    void getMoviePage_whenSorted_thenReturnSortedAnd200() throws Exception {
        // given
        int page = 0;
        int size = 2;
        String sort = "title";

        Movie movie1 = new Movie("A Title", "Director A", List.of(), List.of(new Actor("Actor A")));
        Movie movie2 = new Movie("B Title", "Director B", List.of(), List.of(new Actor("Actor B")));

        Page<Movie> moviePage = new PageImpl<>(List.of(movie1, movie2));
        given(movieService.findAllMoviePage(page, size, sort)).willReturn(moviePage);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/pagination")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("content").isArray())
                .andExpect(jsonPath("content", Matchers.hasSize(2)))

                .andExpect(jsonPath("content[0].title").value("A Title"))
                .andExpect(jsonPath("content[0].director").value("Director A"))
                .andExpect(jsonPath("content[0].screenings").isArray())
                .andExpect(jsonPath("content[0].screenings").exists())

                .andExpect(jsonPath("content[0].actors").isArray())
                .andExpect(jsonPath("content[0].actors", Matchers.hasSize(1)))
                .andExpect(jsonPath("content[0].actors[0].id").exists())
                .andExpect(jsonPath("content[0].actors[0].name").value("Actor A"))

                .andExpect(jsonPath("content[1].title").value("B Title"))
                .andExpect(jsonPath("content[1].director").value("Director B"))
                .andExpect(jsonPath("content[1].screenings").isArray())
                .andExpect(jsonPath("content[1].screenings").exists())

                .andExpect(jsonPath("content[1].actors").isArray())
                .andExpect(jsonPath("content[1].actors", Matchers.hasSize(1)))
                .andExpect(jsonPath("content[1].actors[0].id").exists())
                .andExpect(jsonPath("content[1].actors[0].name").value("Actor B"));

        Mockito.verify(movieService, times(1)).findAllMoviePage(page, size, sort);
    }

    // unhappy path
    @Test
    @WithMockUser(authorities = "ROLE_USER")
    void getMoviePage_whenInvalidPageSize_thenReturnBadRequest() throws Exception {
        // given
        int page = 0;
        int size = -1;
        String sort = null;

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/pagination")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).findAllMoviePage(page, size, sort);
    }

    // unhappy path
    @Test
    void getMoviePage_whenUserUnauthorized_thenReturnForbidden() throws Exception {
        // given
        int page = 0;
        int size = 2;
        String sort = null;

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/pagination")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                // then
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());

        Mockito.verify(movieService, times(0)).findAllMoviePage(page, size, sort);
    }
}
