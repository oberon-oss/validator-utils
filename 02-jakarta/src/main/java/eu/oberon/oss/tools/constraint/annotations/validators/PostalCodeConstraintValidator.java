package eu.oberon.oss.tools.constraint.annotations.validators;

import eu.oberon.oss.tools.constraint.annotations.PostalCode;
import eu.oberon.oss.tools.validators.pc.PostalCodeChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import java.util.Map;

import static eu.oberon.oss.tools.validators.pc.AbstractPostalCodeChecker.getPostalCodeChecker;

/**
 * Validation class for the {@link PostalCode} annotation interface.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class PostalCodeConstraintValidator implements ConstraintValidator<PostalCode, String> {

    /**
     * Default constructor
     */
    public PostalCodeConstraintValidator() {
        // Default constructor
    }

    /**
     * Validates a postal code.
     * <p>
     * The validation will use the value of the {@link PostalCode#countryCode() countryCode} attribute that was
     * specified on the
     * annotation.
     *
     * @param value   The postal code data to validate
     * @param context The context for this call. It provides access to the {@link PostalCode#countryCode()}, which is
     *                required to determine how to validate the value provided.
     *
     * @return <b>True</b> if the postal code is valid for the specified country, or <b>false</b> otherwise
     *
     * @since 1.0.0
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Map<String, Object> attributes = ((ConstraintValidatorContextImpl) context).getConstraintDescriptor().getAttributes();
        String countryCode = (String) attributes.get("countryCode");
        PostalCodeChecker postalCodeChecker = getPostalCodeChecker(countryCode);
        return postalCodeChecker != null && postalCodeChecker.isValid(value);
    }

}
