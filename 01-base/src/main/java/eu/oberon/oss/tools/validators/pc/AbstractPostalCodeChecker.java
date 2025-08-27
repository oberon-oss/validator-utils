package eu.oberon.oss.tools.validators.pc;

import eu.oberon.oss.tools.cc.CountryCodeTable;
import eu.oberon.oss.tools.cc.CountryCodeTableEntry;
import eu.oberon.oss.tools.cc.CountryCodeTableLookupKeys;
import javassist.Modifier;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

/**
 * Contains shared code for {@link PostalCodeChecker} implementations.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
@Log4j2
public abstract class AbstractPostalCodeChecker implements PostalCodeChecker {
    private static final CountryCodeTable COUNTRY_CODE_TABLE;
    private static final Map<CountryCodeTableEntry, PostalCodeChecker> AVAILABLE_POSTAL_CODE_CHECKERS;

    private final CountryCodeTableEntry entry;

    /**
     * Initializes the PostalCodeChecker using the code and lookup key.
     *
     * @param code Country specific code.
     * @param key  The lookup key specifying what attribute should be searched on.
     *
     * @since 1.0.0
     */
    protected AbstractPostalCodeChecker(@NotNull String code, @NotNull CountryCodeTableLookupKeys key) {
        entry = getCountryCodeTable(code, key);
        if (entry == null) {
            throw new IllegalArgumentException("Country code '" + code + "' is not supported/recognized for " + key);
        }
    }

    /**
     * Initializes the PostalCodeChecker using the specified code.
     *
     * @param code The code to lookup
     *
     * @since 1.0.0
     */
    protected AbstractPostalCodeChecker(@NotNull String code) {
        entry = findCountryCodeTable(code);
        if (entry == null) {
            throw new IllegalArgumentException("Country code '" + code + "' is not supported/recognized");
        }
    }

    /**
     * Performs a lookup for a PostalCodeChecker instance for a CountryCodeTableEntry that has an attribute that matches
     * the specified country code.
     *
     * @param countryCode The country code for which to find a postal checker.
     *
     * @return The postal code checker that was found, or {@literal <null>} if none was found.
     *
     * @since 1.0.0
     */
    public static @Nullable PostalCodeChecker getPostalCodeChecker(@NotNull String countryCode) {
        var tableEntry = findCountryCodeTable(countryCode);

        if (tableEntry == null) {
            return null;
        }
        return AVAILABLE_POSTAL_CODE_CHECKERS.get(tableEntry);
    }


    /**
     * Queries the country code table for an entry with an attribute that matches the specified code.
     *
     * @param code the code to lookup.
     *
     * @return The matching CountryCodeTableEntry, or {@literal <null>} if no entry in the table has an attribute
     * that matches the given code.
     *
     * @since 1.0.0
     */
    public static @Nullable CountryCodeTableEntry findCountryCodeTable(String code) {
        CountryCodeTableEntry tableEntry;
        for (CountryCodeTableLookupKeys key : CountryCodeTableLookupKeys.values()) {
            tableEntry = getCountryCodeTable(code, key);
            if (tableEntry != null) {
                return tableEntry;
            }
        }
        return null;
    }

    /**
     * Queries the country code table for an entry matching the key to the specified code.
     *
     * @param code The code to lookup.
     * @param key  The lookup key specifying what attribute should be searched on.
     *
     * @return An entry for the given code and key, or {@literal <null>} if none was found.
     *
     * @since 1.0.0
     */
    public static @Nullable CountryCodeTableEntry getCountryCodeTable(@NotNull String code,
                                                                      @NotNull CountryCodeTableLookupKeys key) {
        return COUNTRY_CODE_TABLE.findEntry(code, key);
    }


    @Override
    public @NotNull CountryCodeTableEntry getCodeTableEntry() {
        return entry;
    }

    static {
        try {
            COUNTRY_CODE_TABLE = CountryCodeTable.getDefaultInstance();
            AVAILABLE_POSTAL_CODE_CHECKERS = loadPostalCodeCheckers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<CountryCodeTableEntry, PostalCodeChecker> loadPostalCodeCheckers() {
        Map<CountryCodeTableEntry, PostalCodeChecker> map = new HashMap<>();
        Reflections reflections = new Reflections("eu.oberon.oss.tools");
        Set<Class<?>> subTypes = reflections.get(SubTypes.of(PostalCodeChecker.class).asClass());
        for (Class<?> theClass : subTypes) {
            if (!Modifier.isAbstract(theClass.getModifiers())) {
                try {
                    PostalCodeChecker checker = (PostalCodeChecker) theClass.getConstructor((Class<?>[]) null).newInstance();
                    map.putIfAbsent(checker.getCodeTableEntry(), checker);
                    LOGGER.debug("Added '{}' for Country code '{}'",
                            theClass.getSimpleName(), checker.getCodeTableEntry().iso3166Alpha2Code()
                    );
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                         InstantiationException e) {
                    // TODO: add to loggin
                }
            }
        }
        return Map.copyOf(map);
    }

}
