package eu.oberon.oss.tools.constraint.annotations.validators;

import eu.oberon.oss.tools.ValidatorEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Class that performs the validation on behalf of the {@link BSN} annotation.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class BSNConstraintValidator implements ConstraintValidator<BSN, String> {
    private static final ValidatorEnum BSN_VALIDATOR = ValidatorEnum.BSN;

    /**
     * Default constructor
     *
     * @since 1.0.0
     */
    public BSNConstraintValidator() {
        // Keep JavaDoc processor from complaining
    }

    @Override
    public void initialize(BSN constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String source, ConstraintValidatorContext constraintValidatorContext) {
        return BSN_VALIDATOR.isValid(source);
    }
}
