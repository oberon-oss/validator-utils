package eu.oberon.oss.tools.validators.pc;

import eu.oberon.oss.tools.cc.CountryCodeTableEntry;
import eu.oberon.oss.tools.validators.BaseValidator;
import org.jetbrains.annotations.NotNull;


/**
 * Defines the contract for implementing postal code checkers.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public interface PostalCodeChecker extends BaseValidator<String> {
    /**
     * Returns the country code table entry of the country for which this postal checker is applicable.
     *
     * @return The CountryCodeTableEntry for the PostalCodeChecker implementation
     *
     * @since 1.0.0
     */
    @NotNull CountryCodeTableEntry getCodeTableEntry();
}
