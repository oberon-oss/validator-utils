package eu.oberon.oss.tools.proofs;

/**
 * Most basic definition of a validator.
 *
 * @param <S> The type of object that contains the data to be validated
 *
 * @author TigerLilly64
 * @since 0.0.1
 */
@FunctionalInterface
public interface BaseValidator<S> {
    /**
     * Validates the provided 'source' and returns a boolean indicating if it was valid or not.
     *
     * @param source The source data to be validated
     *
     * @return <b>True</b> if the data is valid, or <b>false</b> if not.
     *
     * @since 1.0.0
     */
    boolean isValid(S source);
}
