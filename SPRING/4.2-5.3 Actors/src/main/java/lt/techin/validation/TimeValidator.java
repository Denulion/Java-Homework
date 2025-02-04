package lt.techin.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class TimeValidator implements ConstraintValidator<ValidTime, LocalTime> {

    @Override
    public boolean isValid(LocalTime time, ConstraintValidatorContext context) {
        if (time == null) {
            return false;
        }

        return time.isAfter(LocalTime.now());
    }
}
