package eu.oberon.oss.tools.validators.pc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NLPostalCodeCheckerTest {
    private static NLPostalCodeChecker checker;

    @BeforeAll
    static void setUp() {
        checker = new NLPostalCodeChecker();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1000AA", "9999ZZ", "1234 Az", "5678 ff", "8495 VJ", "3432      Ax", "8394XX"
    })
    void testValidPostalCode(String postCode) {
        assertTrue(checker.isValid(postCode));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2A", "0999AA", "12345AA","1234SA", "1234SD", "1234SS","1234ABC"
    })
    void testInvalidPostCodes(String postCode) {
        assertFalse(checker.isValid(postCode));
    }
}