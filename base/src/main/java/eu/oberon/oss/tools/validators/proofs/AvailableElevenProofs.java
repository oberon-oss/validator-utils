package eu.oberon.oss.tools.validators.proofs;

import eu.oberon.oss.tools.validators.BaseValidator;

import java.util.List;

/**
 * Provides pre-defined validations.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public enum AvailableElevenProofs implements BaseValidator<String> {
    /**
     * Provides validation of Burger Service Nummer (BSN, Dutch equivalent of the US Social Security Number) type
     * fields.
     *
     * @since 1.0.0
     */
    BSN(List.of(9, 8, 7, 6, 5, 4, 3, 2, -1));

    private final StandardElevenProofDefinition<AvailableElevenProofs> definition;

    AvailableElevenProofs(List<Integer> weightTable) {
        definition = StandardElevenProofDefinition.getInstance(this, weightTable);
    }

    @Override
    public boolean isValid(String source) {
        return definition.getValidator().isValid(source);
    }
}
