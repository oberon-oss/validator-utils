package eu.oberon.oss.tools.proofs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.oberon.oss.tools.proofs.StandardElevenProofDefinition.DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR;
import static org.junit.jupiter.api.Assertions.*;

class StandardElevenProofDefinitionTest {
    private static final List<Integer> WEIGHT_TABLE = List.of(9, 8, 7, 6, 5, 4, 3, 2, -1);

    private StandardElevenProofBuilder<String, String> builder;

    @BeforeEach
    void setUp() {
        builder = StandardElevenProofDefinition.builder();
    }

    @Test
    void testValidElevenProofDefinition() {
        StandardElevenProofDefinition<String> definition =
                assertDoesNotThrow(() -> builder
                        .setWeightTable(List.of(9, 8, 7, 6, 5))
                        .setDefinitionID("ID1")
                        .build()
                );

        assertEquals("ID1", definition.getDefinitionID());
        assertEquals(5, definition.getWeightTable().size());
        assertEquals(definition.getMaximumLength(), definition.getWeightTable().size());
        assertEquals(11, definition.getRemainder());
    }

    @Test
    void testThrowsNullPointerExceptionWithSourceIsNull() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR.isElevenProofCompliant(null, List.of(1), 11));
        assertEquals("Parameter source: cannot be null", exception.getMessage());
    }

    @Test
    void testThrowsNullPointerExceptionWithWeightTableIsNull() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR.isElevenProofCompliant("058688833", null, 11)
        );
        assertEquals("Parameter applicableWeightTable: cannot be null", exception.getMessage());
    }

    @Test
    void testThrowsIllegalArgumentExceptionWhenSourceLengthNotEqualWeightTableSize() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR.isElevenProofCompliant("058688833", List.of(1), 11)
        );
        assertEquals("Parameter source: length 9 is invalid, expected length=1", exception.getMessage());
    }

    @Test
    void testThrowsIllegalArgumentExceptionWhenRemainderIsZero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR.isElevenProofCompliant("0", List.of(1), 0)
        );
        assertEquals("Parameter applicableRemainder: cannot be 0 (Zero)", exception.getMessage());
    }

    @Test
    void testStandardElevenProofDefinition() {
        assertTrue(DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR.isElevenProofCompliant("058688833", WEIGHT_TABLE, 11));
        assertFalse(DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR.isElevenProofCompliant("058688833", List.of(1, 7, 4, 9, 5, 3, 9, 5, 6), 11));
        assertFalse(DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR.isElevenProofCompliant("058688833", WEIGHT_TABLE, 13));
    }
}