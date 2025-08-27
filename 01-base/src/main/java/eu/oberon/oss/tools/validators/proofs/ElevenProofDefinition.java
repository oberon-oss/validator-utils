package eu.oberon.oss.tools.validators.proofs;

import eu.oberon.oss.tools.validators.BaseValidator;

import java.util.List;

/**
 * Defines the operational attributes for any eleven test based validation
 *
 * @param <S> The type of object that contains the data to be validated
 * @param <I> The class type of the ID associated with a definition instance
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public interface ElevenProofDefinition<S, I> {
    /**
     * Returns the ID for an eleven proof definition
     *
     * @return the ID for the instance.
     *
     * @since 1.0.0
     */
    I getDefinitionID();

    /**
     * Returns the weight table used by the eleven proof definition.
     *
     * @return The weight table, as an array of integers
     *
     * @since 1.0.0
     */
    List<Integer> getWeightTable();

    /**
     * returns the maximum length of the data object that can be analyzed.
     *
     * @return The maximum data length allowed.
     *
     * @since 1.0.0
     */
    int getMaximumLength();

    /**
     * Returns a validator that can be used to validate data to be valid according to the implemented eleven proof.
     *
     * @return An instance of the {@link BaseValidator} interface to be used for validation.
     *
     * @since 1.0.0
     */
    BaseValidator<S> getValidator();
}
