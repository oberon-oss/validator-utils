package eu.oberon.oss.tools.proofs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry that manages the storage and retrieval of {@link BaseValidator} instances.
 *
 * @param <I> The class type of the ID associated with a definition instance
 * @param <S> The type of object that contains the data to be validated
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class BaseValidatorRegistry<I, S> {
    private final Map<I, BaseValidator<S>> validators = new ConcurrentHashMap<>();

    private BaseValidatorRegistry() {
    }

    /**
     * Registers a new BaseValidator.
     *
     * @param id        ID of the validator to be added.
     * @param validator The actual validator to add.
     *
     * @throws IllegalArgumentException if the ID provided already exists in the registry
     * @since 1.0.0
     */
    public void registerBaseValidator(@NotNull I id, @NotNull BaseValidator<S> validator) {
        if (validators.containsKey(id)) {
            throw new IllegalArgumentException("An entry with ID '" + id + "' is already registered");
        }
        validators.put(id, validator);
    }

    /**
     * Retrieves the validator stored with the specified ID
     *
     * @param id ID of the validator to be retrieved.
     *
     * @return The validator stored for the specified 'id'. If no entry exists, a value of {@literal  <null>} will be
     * returned.
     *
     * @since 1.0.
     */
    public @Nullable BaseValidator<S> getBaseValidator(I id) {
        return validators.get(id);
    }

    /**
     * Creates and returns a registry instance.
     *
     * @param <I> The class type of the ID associated with a definition instance
     * @param <S> The type of object that contains the data to be validated
     *
     * @return the created registry instance.
     *
     * @since 1.0.0
     */
    public static <I, S> BaseValidatorRegistry<I, S> getInstance() {
        return new BaseValidatorRegistry<>();
    }

}
