package eu.oberon.oss.tools.proofs;

import eu.oberon.oss.tools.validators.BaseValidator;
import eu.oberon.oss.tools.validators.BaseValidatorRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseValidatorRegistryTest {
    private static final String TEST_STRING = "TestBaseValidator";
    private static final String TEST_ID = "ID1";

    private BaseValidatorRegistry<String, String> baseValidatorRegistry;
    private BaseValidator<String> baseValidator;


    @BeforeEach
    void setUp() {
        baseValidatorRegistry = BaseValidatorRegistry.getInstance();
        baseValidator = TEST_STRING::contentEquals;
    }

    @Test
    void testAddingValidator() {
        assertDoesNotThrow(() -> baseValidatorRegistry.registerBaseValidator(TEST_ID, baseValidator));
        BaseValidator<String> validator = baseValidatorRegistry.getBaseValidator(TEST_ID);
        assertNotNull(validator);
        assertTrue(validator.isValid(TEST_STRING));
    }

    @Test
    void testAddingDuplicateValidators() {
        assertDoesNotThrow(() -> baseValidatorRegistry.registerBaseValidator(TEST_ID, baseValidator));
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> baseValidatorRegistry.registerBaseValidator(TEST_ID, baseValidator)
        );
        assertEquals("An entry with ID '" + TEST_ID + "' is already registered", exception.getMessage());
    }
}