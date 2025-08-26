package eu.oberon.oss.tools.constraint.annotations;
import eu.oberon.oss.tools.constraint.annotations.validators.PostalCodeConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {PostalCodeConstraintValidator.class})
public @interface PostalCode {
    String countryCode() default "??";

    /**
     * Default message id of the message to send in case the validation failed.
     *
     * @return The message key.
     *
     * @since 1.0.0
     */
    String message() default "Invalid code";

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