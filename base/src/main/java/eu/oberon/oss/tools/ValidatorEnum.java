package eu.oberon.oss.tools;

import eu.oberon.oss.tools.proofs.BaseValidator;
import eu.oberon.oss.tools.proofs.StandardElevenProofDefinition;

import java.util.List;

/**
 * Provides pre-defined validations.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public enum ValidatorEnum implements BaseValidator<String> {
    /**
     * Provides validation of Burger Service Nummer (BSN, Dutch equivalent of the US Social Security Number) type
     * fields.
     *
     * @since 1.0.0
     */
    BSN(List.of(9, 8, 7, 6, 5, 4, 3, 2, -1));

    private final StandardElevenProofDefinition<ValidatorEnum> definition;

    ValidatorEnum(List<Integer> weightTable) {
        definition = StandardElevenProofDefinition.getInstance(this, weightTable);
    }

    @Override
    public boolean isValid(String source) {
        return definition.getValidator().isValid(source);
    }
}
