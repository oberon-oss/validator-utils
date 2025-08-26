package eu.oberon.oss.tools.constraint.annotations.validators;

import eu.oberon.oss.tools.validators.proofs.AvailableElevenProofs;
import eu.oberon.oss.tools.constraint.annotations.BSN;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

/**
 * Class that performs the validation on behalf of the {@link BSN} annotation.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
@Log4j2
public class BSNConstraintValidator implements ConstraintValidator<BSN, String> {
    private static final AvailableElevenProofs BSN_VALIDATOR = AvailableElevenProofs.BSN;

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
        try {
            return BSN_VALIDATOR.isValid(source);
        } catch (IllegalArgumentException|NullPointerException e) {
            LOGGER.warn("Invalid BSN number {}, error={}",source,e.getMessage());
            return false;
        }
    }
}
