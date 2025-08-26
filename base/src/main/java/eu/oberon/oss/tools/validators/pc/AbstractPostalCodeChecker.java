package eu.oberon.oss.tools.validators.pc;

import eu.oberon.oss.tools.cc.CountryCodeTable;
import eu.oberon.oss.tools.cc.CountryCodeTableEntry;
import eu.oberon.oss.tools.cc.CountryCodeTableLookupKeys;
import javassist.Modifier;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

@Log4j2
@Slf4j
public abstract class AbstractPostalCodeChecker implements PostalCodeChecker {
    private static final CountryCodeTable COUNTRY_CODE_TABLE;
    private static final Map<CountryCodeTableEntry, PostalCodeChecker> SUPPORTED_COUNTRY_CODE_TABLE;

    private final CountryCodeTableEntry entry;

    public AbstractPostalCodeChecker(String code, CountryCodeTableLookupKeys key) {
        entry = getCountryCodeTable(code, key);
        if (entry == null) {
            throw new IllegalArgumentException("Country code '" + code + "' is not supported/recognized for " + key);
        }
    }

    public AbstractPostalCodeChecker(String code) {
        entry = findCountryCodeTable(code);
        if (entry == null) {
            throw new IllegalArgumentException("Country code '" + code + "' is not supported/recognized");
        }
    }

    public static @Nullable PostalCodeChecker getPostalCodeChecker(String countryCode) {
        var tableEntry = findCountryCodeTable(countryCode);

        if (tableEntry == null) {
            return null;
        }

        return getSupportedCountryCodeTable().get(tableEntry);
    }

    public static @Nullable CountryCodeTableEntry findCountryCodeTable(String countryCode) {
        CountryCodeTableEntry tableEntry = null;
        for (CountryCodeTableLookupKeys key : CountryCodeTableLookupKeys.values()) {
            tableEntry = getCountryCodeTable(countryCode, key);
            if (tableEntry != null) {
                return tableEntry;
            }
        }
        return null;
    }

    public static @Nullable CountryCodeTableEntry getCountryCodeTable(String countryCode, CountryCodeTableLookupKeys key) {
        return getCountryCodeTable().findEntry(countryCode, key);
    }


    @Override
    public CountryCodeTableEntry getCodeTableEntry() {
        return entry;
    }

    static {
        try {
            COUNTRY_CODE_TABLE = CountryCodeTable.getDefaultInstance();
            SUPPORTED_COUNTRY_CODE_TABLE = loadPostalCodeCheckers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CountryCodeTable getCountryCodeTable() {
        return COUNTRY_CODE_TABLE;
    }

    private static Map<CountryCodeTableEntry, PostalCodeChecker> getSupportedCountryCodeTable() {
        return SUPPORTED_COUNTRY_CODE_TABLE;
    }

    private static Map<CountryCodeTableEntry, PostalCodeChecker> loadPostalCodeCheckers() {
        Map<CountryCodeTableEntry, PostalCodeChecker> map = new HashMap<>();
        Reflections reflections = new Reflections("eu.oberon.oss.tools");
        Set<Class<?>> subTypes = reflections.get(SubTypes.of(PostalCodeChecker.class).asClass());
        for (Class<?> theClass : subTypes) {
            LOGGER.info("CLass: {}", theClass);
            if (!Modifier.isAbstract(theClass.getModifiers())) {
                try {
                    PostalCodeChecker checker = (PostalCodeChecker) theClass.getConstructor(null).newInstance();
                    map.putIfAbsent(checker.getCodeTableEntry(), checker);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                         InstantiationException e) {
                    // TODO: add to loggin
                }
            }
        }
        return Map.copyOf(map);
    }

}
