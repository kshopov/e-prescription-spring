package kshopov.web.eprescription.validation;

import kshopov.web.eprescription.model.IdentifierType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Constraint(validatedBy = IdentifierTypeValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface IdentifierTypeConstraint {

    String message() default "Идентификаторът не е валиден спрямо типа, който сте избрали!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String identifierType();

    String identifier();

    @Target({TYPE})
    @Retention(RUNTIME)
    @interface List {
        IdentifierTypeConstraint[] value();
    }
}
