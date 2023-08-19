package kshopov.web.eprescription.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = IdentifierExistsValidator.class)
@Target({FIELD})
@Documented
@Retention(RUNTIME)
public @interface IdentifierConstraint {

    public String message() default "Грешен тип идентификатор!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
