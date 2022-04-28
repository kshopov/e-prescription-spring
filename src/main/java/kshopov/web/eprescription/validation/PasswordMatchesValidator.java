package kshopov.web.eprescription.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import kshopov.web.eprescription.model.Doctor;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final Doctor doctor = (Doctor) obj;
        return doctor.getPassword()
            .equals(doctor.getPasswordConfirmation());
    }

}
