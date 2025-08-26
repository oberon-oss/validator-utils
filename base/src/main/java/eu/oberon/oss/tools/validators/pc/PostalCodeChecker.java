package eu.oberon.oss.tools.validators.pc;

import eu.oberon.oss.tools.cc.CountryCodeTableEntry;
import eu.oberon.oss.tools.validators.BaseValidator;

public interface PostalCodeChecker extends BaseValidator<String> {
    CountryCodeTableEntry getCodeTableEntry();
}
