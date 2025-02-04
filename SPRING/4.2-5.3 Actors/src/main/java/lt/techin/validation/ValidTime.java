package lt.techin.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeValidator.class)
public @interface ValidTime {

    String message() default "Time must be in the future!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
