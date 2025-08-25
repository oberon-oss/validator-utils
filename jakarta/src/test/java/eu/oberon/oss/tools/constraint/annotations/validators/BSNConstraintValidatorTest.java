package eu.oberon.oss.tools.constraint.annotations.validators;

import eu.oberon.oss.tools.constraint.annotations.validators.cls.BSNFieldTestClass;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Log4j2
class BSNConstraintValidatorTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void isValid()  {
        BSNFieldTestClass bsnTestField = new BSNFieldTestClass("bsnTestField");
        assertNotNull(bsnTestField);
        BSNFieldTestClass invalidBSNNumber = new BSNFieldTestClass("ABCDEFGHI");
        Set<ConstraintViolation<BSNFieldTestClass>> violations = validator.validate(invalidBSNNumber);

    }

}