package eu.oberon.oss.tools.constraint.annotations.validators;

import eu.oberon.oss.tools.constraint.annotations.PostalCode;
import eu.oberon.oss.tools.validators.pc.PostalCodeChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import java.util.Map;

import static eu.oberon.oss.tools.validators.pc.AbstractPostalCodeChecker.getPostalCodeChecker;

public class PostalCodeConstraintValidator implements ConstraintValidator<PostalCode, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Map<String, Object> attributes = ((ConstraintValidatorContextImpl) context).getConstraintDescriptor().getAttributes();
        String countryCode = (String) attributes.get("countryCode");
        PostalCodeChecker postalCodeChecker = getPostalCodeChecker(countryCode);
        return postalCodeChecker != null && postalCodeChecker.isValid(value);
    }

}
