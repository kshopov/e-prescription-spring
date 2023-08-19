package kshopov.web.eprescription.validation;

import kshopov.web.eprescription.model.IdentifierType;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentifierTypeValidator implements ConstraintValidator<IdentifierTypeConstraint, Object> {

    private String identifierType;
    private String identifier;

    @Override
    public void initialize(IdentifierTypeConstraint constraintAnnotation) {
        this.identifierType = constraintAnnotation.identifierType();
        this.identifier = constraintAnnotation.identifier();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        IdentifierType identifierTypeValue = (IdentifierType) new BeanWrapperImpl(value).getPropertyValue(identifierType);
        Object identifierValue = new BeanWrapperImpl(value).getPropertyValue(identifier);

        return true;
    }
}
