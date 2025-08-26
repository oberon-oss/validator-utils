package eu.oberon.oss.tools.validators.proofs;

import eu.oberon.oss.tools.validators.BaseValidator;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Generic implementation of the {@link ElevenProofDefinition}, using {@link String} as the class that contains the
 * data to be validated
 *
 * @param <I> The type class used as the identifier for the ElevenProofDefinition
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
@Log4j2
@Getter
public class StandardElevenProofDefinition<I> implements ElevenProofDefinition<String, I> {
    private final I definitionID;
    private final BaseValidator<String> validator;
    private final List<Integer> weightTable;
    private final int remainder;

    /**
     * The default remainder divider to apply when performing the validation calculation.
     *
     * @since 1.0.0
     */
    public static final int DEFAULT_REMAINDER = 11;

    /**
     * Default {@link ElevenProofComplianceValidator} that can be used to validate eleven proof type compliant data.
     *
     * @since 1.0.0
     */
    public static final ElevenProofComplianceValidator<String> DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR = (source, applicableWeightTable, applicableRemainder) -> {
        final Pattern bsnNumberPattern = Pattern.compile("\\d{9}");
        if (source == null) {
            throw new NullPointerException("Parameter source: cannot be null");
        }

        if (applicableWeightTable == null) {
            throw new NullPointerException("Parameter applicableWeightTable: cannot be null");
        }

        if (source.length() != applicableWeightTable.size()) {
            throw new IllegalArgumentException(
                    "Parameter source: length " + source.length() + " is invalid, expected length=" + applicableWeightTable.size());
        }

        if (applicableRemainder == 0) {
            throw new IllegalArgumentException("Parameter applicableRemainder: cannot be 0 (Zero)");
        }

        if (!bsnNumberPattern.matcher(source).matches()) {
            throw new IllegalArgumentException("Invalid bsn data: " + source + " for pattern '" + bsnNumberPattern + "'");
        }

        int sum = 0;
        for (int i = 0; i < source.length(); i++) {
            int digit = Integer.parseInt(source.substring(i, i + 1));
            Integer weight = applicableWeightTable.get(i);
            sum += (digit * weight);
            LOGGER.trace("({} of {}): digit={}, weight={}, sum={}", i, source.length(), digit, weight, sum);
        }
        return sum % applicableRemainder == 0;
    };


    @Override
    public int getMaximumLength() {
        return weightTable.size();
    }

    /**
     * Create a {@link StandardElevenProofDefinition} using the {@link #DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR}
     * and {@link #DEFAULT_REMAINDER}
     *
     * @param definitionID The definition ID to assign to the validator
     * @param weightTable  The weight table ot use when calculating the checksum
     * @param <I>          The type class used as the identifier for the {@link StandardElevenProofDefinition}
     *
     * @return The initialized StandardElevenProofDefinition object
     *
     * @since 1.0.0
     */
    public static <I> StandardElevenProofDefinition<I> getInstance(I definitionID, List<Integer> weightTable) {
        return (StandardElevenProofDefinition<I>) builder().setDefinitionID(definitionID).setWeightTable(weightTable).build();
    }

    /**
     * Obtain a builder to construct instances of the {@link StandardElevenProofDefinition} class.
     * The builder object will be initialized with the remainder field set to {@link #DEFAULT_REMAINDER}, and the default
     * {@link ElevenProofComplianceValidator} defined by {@link #DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR}
     *
     * @param <I> The type class used as the identifier for the {@link StandardElevenProofDefinition}
     *
     * @return A builder object
     *
     * @since 1.0.0
     */
    public static <I> StandardElevenProofBuilder<I, String> builder() {
        return new InternalElevenProofBuilder<I>().setRemainder(DEFAULT_REMAINDER).setComplianceValidator(DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR);
    }

    private StandardElevenProofDefinition(StandardElevenProofBuilder<I, String> builder) {
        this.definitionID = builder.getDefinitionID();
        this.weightTable = List.copyOf(builder.getWeightTable());
        this.remainder = builder.getRemainder();
        validator = source -> builder.getComplianceValidator().isElevenProofCompliant(source, this.weightTable, remainder);
    }


    private static class InternalElevenProofBuilder<I> extends StandardElevenProofBuilder<I, String> {
        @Override
        public StandardElevenProofDefinition<I> build() {
            return new StandardElevenProofDefinition<>(this);
        }
    }
}
