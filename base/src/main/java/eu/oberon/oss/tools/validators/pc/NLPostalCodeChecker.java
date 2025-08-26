package eu.oberon.oss.tools.validators.pc;

import java.util.regex.Pattern;

import static eu.oberon.oss.tools.cc.CountryCodeTableLookupKeys.ISO3166_ALPHA_2;

public class NLPostalCodeChecker extends AbstractPostalCodeChecker {
    private static final Pattern POST_CODE_PATTERN = Pattern.compile(
            "([1-9]\\d{3})\\s*([A-RT-Z][A-Z]|S[B-CE-RT-Z])"
    );

    public NLPostalCodeChecker() {
        super("NL", ISO3166_ALPHA_2);
    }

    @Override
    public boolean isValid(String postalCode) {
        return POST_CODE_PATTERN.matcher(postalCode).matches();
    }
}
