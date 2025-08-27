package eu.oberon.oss.tools.validators.pc;

import java.util.regex.Pattern;

import static eu.oberon.oss.tools.cc.CountryCodeTableLookupKeys.ISO3166_ALPHA_2;

/**
 * Provides postal code validation for Dutch 'Post code' values.
 * <p>
 * A valid post code consists of 4 digits, ranging from 1000-9999 (inclusive), one space and two upper case characters
 * in the range A-Z. There are three letter combinations that are not allowed, these are SS, SD and SS.
 * <p>
 * The {@link #isValid(String)} method is lenient with the number of spaces between the number and the characters (0
 * or more allowed), and will be case-insensitive.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class NLPostalCodeChecker extends AbstractPostalCodeChecker {
    private static final Pattern POST_CODE_PATTERN = Pattern.compile(
            "([1-9]\\d{3})\\s*([A-RT-Z][A-Z]|S[B-CE-RT-Z])", Pattern.CASE_INSENSITIVE
    );

    /**
     * Default constructor
     *
     * @since 1.0.0
     */
    public NLPostalCodeChecker() {
        super("NL", ISO3166_ALPHA_2);
    }

    /**
     * {@inheritDoc}
     *
     * @param postCode the postcode to validate
     *
     * @return <b>True</b> if valid, or <b>false</b> otherwise.
     *
     * @since 1.0.0
     */
    @Override
    public boolean isValid(String postCode) {
        return POST_CODE_PATTERN.matcher(postCode).matches();
    }
}
