package eu.oberon.oss.tools.proofs;

import java.util.List;


/**
 * Generic builder to create instances of {@link StandardElevenProofDefinition} objects.
 *
 * @param <I> The type class used as the identifier for the {@link StandardElevenProofDefinition}
 * @param <S> The type of object that contains the data to be validated
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public abstract class StandardElevenProofBuilder<I, S> {
    private I definitionID;
    private List<Integer> weightTable;
    private int remainder;
    private ElevenProofComplianceValidator<S> complianceValidator;

    /**
     * Allows extension by subclasses.
     *
     * @since 1.0.0
     */
    protected StandardElevenProofBuilder() {
        // added for clarity of purpose
    }

    /**
     * Constructs the target {@link StandardElevenProofDefinition} object.
     *
     * @return The StandardElevenProofDefinition build.
     *
     * @since 1.0.0
     */
    public abstract StandardElevenProofDefinition<I> build();

    /**
     * Sets the definitionID for the ElevenProofComplianceValidator object to be created.
     *
     * @param definitionID The definition ID
     *
     * @return The current builder object
     *
     * @since 1.0.0
     */
    public StandardElevenProofBuilder<I, S> setDefinitionID(I definitionID) {
        this.definitionID = definitionID;
        return this;
    }

    /**
     * Sets the weight table for the ElevenProofComplianceValidator object to be created.
     *
     * @param weightTable The weight table to set.
     *
     * @return The current builder object
     *
     * @since 1.0.0
     */
    public StandardElevenProofBuilder<I, S> setWeightTable(List<Integer> weightTable) {
        this.weightTable = weightTable;
        return this;
    }

    /**
     * Sets the remainder for the ElevenProofComplianceValidator object to be created.
     *
     * @param remainder the remainder to set
     *
     * @return The current builder object
     *
     * @since 1.0.0
     */
    public StandardElevenProofBuilder<I, S> setRemainder(int remainder) {
        this.remainder = remainder;
        return this;
    }

    /**
     * Sets the definitionID for the ElevenProofComplianceValidator object to be created.
     *
     * @param complianceValidator the compliance validator to set.
     *
     * @return The current builder object
     *
     * @since 1.0.0
     */
    public StandardElevenProofBuilder<I, S> setComplianceValidator(ElevenProofComplianceValidator<S> complianceValidator) {
        this.complianceValidator = complianceValidator;
        return this;
    }

    /**
     * Returns the current definition ID
     *
     * @return the definition ID
     *
     * @since 1.0.0
     */
    protected I getDefinitionID() {
        return definitionID;
    }

    /**
     * Returns the current weight table.
     *
     * @return the weight table.
     *
     * @since 1.0.0
     */
    protected List<Integer> getWeightTable() {
        return weightTable;
    }

    /**
     * Returns the current remainder.
     *
     * @return the remainder
     *
     * @since 1.0.0
     */
    protected int getRemainder() {
        return remainder;
    }

    /**
     * Returns the current eleven proof compliance validator.
     *
     * @return The validator
     *
     * @since 1.0.0
     */
    protected ElevenProofComplianceValidator<S> getComplianceValidator() {
        return complianceValidator;
    }
}
