package kshopov.web.eprescription.validation;

import kshopov.web.eprescription.model.IdentifierType;
import kshopov.web.eprescription.services.IdentifierService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentifierExistsValidator implements ConstraintValidator<IdentifierConstraint, IdentifierType> {

    private final IdentifierService identifierService;

    public IdentifierExistsValidator(IdentifierService identifierService) {
        this.identifierService = identifierService;
    }

    @Override
    public boolean isValid(IdentifierType value, ConstraintValidatorContext context) {
        return identifierService.getAll().stream().anyMatch(p -> p.getId() == value.getId());
    }

    @Override
    public void initialize(IdentifierConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
