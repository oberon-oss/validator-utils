package eu.oberon.oss.tools.validators.pc;

import eu.oberon.oss.tools.cc.CountryCodeTableLookupKeys;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static eu.oberon.oss.tools.cc.CountryCodeTableLookupKeys.*;
import static org.junit.jupiter.api.Assertions.*;

class AbstractPostalCodeCheckerTest {

    @ParameterizedTest
    @ValueSource(strings = {"NL", "NLD", "528", "Netherlands (the)"})
    void testGetPostalCodeChecker(String code) {
        assertNotNull(AbstractPostalCodeChecker.getPostalCodeChecker(code));
    }

    @ParameterizedTest
    @ValueSource(strings = {"NL", "NLD", "528", "Netherlands (the)"})
    void testFindCountryCodeTable(String code) {
        assertNotNull(AbstractPostalCodeChecker.findCountryCodeTable(code));
    }

    @Test
    void testGetCountryCodeTable() {
        assertNotNull(AbstractPostalCodeChecker.getCountryCodeTable("NL", ISO3166_ALPHA_2));
        assertNotNull(AbstractPostalCodeChecker.getCountryCodeTable("NLD", ISO3166_ALPHA_3));
        assertNotNull(AbstractPostalCodeChecker.getCountryCodeTable("528", UNM49));
        assertNotNull(AbstractPostalCodeChecker.getCountryCodeTable("Netherlands (the)", NAME));
    }

    @Test
    void testForNonExistingCountryCodes() {
        assertNull(AbstractPostalCodeChecker.getCountryCodeTable("XX", ISO3166_ALPHA_2));
        assertNull(AbstractPostalCodeChecker.getCountryCodeTable("XXX", ISO3166_ALPHA_3));
        assertNull(AbstractPostalCodeChecker.getCountryCodeTable("999", UNM49));
        assertNull(AbstractPostalCodeChecker.getCountryCodeTable("Does not exist", NAME));
    }

    @Test
    void testForNonExistingPostalCodeChecker() {
        assertNull(AbstractPostalCodeChecker.getPostalCodeChecker("???"));
    }

    @Test
    void testConstructorsForValidCodes() {
        assertDoesNotThrow(() -> new TestPostalCodeChecker("NLD"));
        assertDoesNotThrow(() -> new TestPostalCodeChecker("NL", ISO3166_ALPHA_2));
    }

    @Test
    void testConstructorsForInvalidCodes() {
        assertThrows(IllegalArgumentException.class, () -> new TestPostalCodeChecker("XXX"));
        assertThrows(IllegalArgumentException.class, () -> new TestPostalCodeChecker("XX", ISO3166_ALPHA_2));
    }

    private static class TestPostalCodeChecker extends AbstractPostalCodeChecker {

        public TestPostalCodeChecker(@NotNull String code, @NotNull CountryCodeTableLookupKeys key) {
            super(code, key);
        }

        public TestPostalCodeChecker(@NotNull String code) {
            super(code);
        }

        @Override
        public boolean isValid(String source) {
            return true;
        }
    }
}