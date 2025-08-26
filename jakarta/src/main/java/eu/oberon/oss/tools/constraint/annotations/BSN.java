package eu.oberon.oss.tools.constraint.annotations;

import eu.oberon.oss.tools.validators.proofs.AvailableElevenProofs;
import eu.oberon.oss.tools.constraint.annotations.validators.BSNConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation that can be used for fields to validate fields expected to contain valid BSN numbers.
 *
 * @see AvailableElevenProofs#BSN
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {BSNConstraintValidator.class})
public @interface BSN {
    /**
     * Default message id of the message to send in case the validation failed.
     *
     * @return The message key.
     *
     * @since 1.0.0
     */
    String message() default "Invalid BSN";

    /**
     * for user to customize the targeted groups
     *
     * @return an array of classes. Default is to return an empty array.
     *
     * @since 1.0.0
     */
    Class<?>[] groups() default {};

    /**
     * For extensibility purposes
     *
     * @return an array of classes. Default is to return an empty array.
     *
     * @since 1.0.0
     */
    Class<? extends Payload>[] payload() default {};
}
