package eu.oberon.oss.tools.constraint.annotations;

import eu.oberon.oss.tools.cc.CountryCodeTable;
import eu.oberon.oss.tools.cc.CountryCodeTableLookupKeys;
import eu.oberon.oss.tools.constraint.annotations.validators.PostalCodeConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Validation constraint for Postal codes.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {PostalCodeConstraintValidator.class})
public @interface PostalCode {

    /**
     * Instructs the validator for which country the postal code validation should be performed.
     * The entry can be identified by the
     * <ul>
     * <li>2 letter {@link CountryCodeTableLookupKeys#ISO3166_ALPHA_2 ISO3166_ALPHA_2}</li>
     * <li>3 letter {@link CountryCodeTableLookupKeys#ISO3166_ALPHA_3 ISO3166_ALPHA_3}</li>
     * <li>The numeral UNM code {@link CountryCodeTableLookupKeys#UNM49 UNM49}</li>
     * <li>The exact country name {@link CountryCodeTableLookupKeys#NAME NAME}</li>
     * </ul>
     *
     * @return The country code string specified by the user.
     * @see CountryCodeTable
     * @see CountryCodeTableLookupKeys
     * @since 1.0.0
     */
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