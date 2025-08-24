package eu.oberon.oss.tools.proofs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eu.oberon.oss.tools.proofs.StandardElevenProofDefinition.DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR;
import static org.junit.jupiter.api.Assertions.*;

class StandardElevenProofDefinitionTest {
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
    void testStandardElevenProofDefinition() {
        ElevenProofComplianceValidator<String> validator = DEFAULT_ELEVEN_PROOF_COMPLIANCE_VALIDATOR;

        List<Integer> listOf1 = List.of(1);
        assertThrows(NullPointerException.class,
                () -> validator.isElevenProofCompliant(null, listOf1, 11)
        );

        assertThrows(NullPointerException.class,
                () -> validator.isElevenProofCompliant("058688833", null, 11)
        );

        assertThrows(IllegalArgumentException.class,
                () -> validator.isElevenProofCompliant("0", listOf1, 0)
        );

        assertThrows(IllegalArgumentException.class,
                () -> validator.isElevenProofCompliant("058688833", listOf1, 11)
        );

        assertTrue(validator.isElevenProofCompliant("058688833", List.of(9, 8, 7, 6, 5, 4, 3, 2, -1), 11));
        assertFalse(validator.isElevenProofCompliant("058688833", List.of(1, 7, 4, 9, 5, 3, 9, 5, 6), 11));
        assertFalse(validator.isElevenProofCompliant("058688833", List.of(9, 8, 7, 6, 5, 4, 3, 2, -1), 13));
    }
}