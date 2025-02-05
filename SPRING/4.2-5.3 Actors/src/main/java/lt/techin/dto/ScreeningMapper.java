package lt.techin.dto;

import lt.techin.model.Movie;
import lt.techin.model.Screening;

import java.util.ArrayList;
import java.util.List;

public class ScreeningMapper {

    public static ScreeningDTO toScreeningDTO(Screening screening) {
        return new ScreeningDTO(
                //screening.getId(),
                screening.getScreeningTheatre(),
                screening.getTime(),
                screening.getDate()
        );
    }

    public static List<ScreeningDTO> toScreeningDTOList(Movie movie) {
        return movie.getScreenings().stream()
                .map(ScreeningMapper::toScreeningDTO)
                .toList();
    }

    public static Screening toScreening(ScreeningDTO screeningDTO) {
        Screening screening = new Screening();

        screening.setScreeningTheatre(screeningDTO.screeningTheatre());
        screening.setTime(screeningDTO.time());
        screening.setDate(screeningDTO.date());

        return screening;
    }

    public static List<Screening> toScreeningListFromDTO(List<ScreeningDTO> dtoList) {
        return dtoList.stream().map(ScreeningMapper::toScreening).toList();
    }
}
