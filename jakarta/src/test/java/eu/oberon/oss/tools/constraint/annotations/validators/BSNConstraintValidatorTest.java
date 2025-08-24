package eu.oberon.oss.tools.constraint.annotations.validators;

import eu.oberon.oss.tools.constraint.annotations.validators.cls.BSNFieldTestClass;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
class BSNConstraintValidatorTest {

    @Test
    void isValid()  {
        BSNFieldTestClass bsnTestField = new BSNFieldTestClass("bsnTestField");
        assertNotNull(bsnTestField);
    }

}