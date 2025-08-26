package eu.oberon.oss.tools.constraint.annotations.validators;

import eu.oberon.oss.tools.constraint.annotations.ConstraintValidatorTest;

import eu.oberon.oss.tools.constraint.annotations.PostalCode;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostalCodeConstraintValidatorTest extends ConstraintValidatorTest {
    private static final String VALID_VALUE = "1000AA";
    private static final String INVALID_VALUE = "1000SS";

    @Test
    void testWithInvalidPostalCode() {
        PostalCodeTestClass testField = new PostalCodeTestClass(INVALID_VALUE);
        Set<ConstraintViolation<PostalCodeTestClass>> violations = validator.validate(testField);
        assertEquals(1, violations.size());
    }

    @Test
    void testWithValidPostalCode() {
        PostalCodeTestClass testClass = new PostalCodeTestClass(VALID_VALUE);
        Set<ConstraintViolation<PostalCodeTestClass>> violations = validator.validate(testClass);
        assertEquals(0, violations.size());
    }

    private record PostalCodeTestClass(@PostalCode(countryCode = "NL") String testPostalCodeField) {

    }
}