package eu.oberon.oss.tools.constraint.annotations.validators;

import eu.oberon.oss.tools.constraint.annotations.BSN;
import eu.oberon.oss.tools.constraint.annotations.ConstraintValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BSNConstraintValidatorTest extends ConstraintValidatorTest {
    private static final String VALID_VALUE = "132827955";
    private static final String INVALID_VALUE = "";

    @Test
    void testWithInvalidBSN() {
        BSNTestClass testField = new BSNTestClass(INVALID_VALUE);
        Set<ConstraintViolation<BSNTestClass>> violations = validator.validate(testField);
        assertEquals(1, violations.size());
    }

    @Test
    void testWithValidBSN() {
        BSNTestClass testClass = new BSNTestClass(VALID_VALUE);
        Set<ConstraintViolation<BSNTestClass>> violations = validator.validate(testClass);
        assertEquals(0, violations.size());
    }

    private record BSNTestClass(@BSN String testBSNField) {

    }
}