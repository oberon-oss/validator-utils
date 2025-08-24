package eu.oberon.oss.tools;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static eu.oberon.oss.tools.ValidatorEnum.BSN;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorEnumTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "227141775", "217304035", "489570689", "661769124", "120671220",
            "514763784", "226435623", "646434147", "342115868", "126360248",
            "410237486", "458383338", "393824597", "425666980", "014898561",
            "058688833", "772517137", "588490155", "532537737", "410626363",
            "274958168", "011006857", "364529209", "027662391", "171024369",
            "204822397", "405102148", "761860174", "697970334", "622948519",
            "170833409", "132765433", "373444424", "358041508", "076828074",
            "186308590", "327182647", "207864469", "396521356", "306081337",
            "296357169", "739745025", "574566867", "408146448", "174939048",
            "087755798", "492315668", "247296016", "315011567", "392331913"
    })
    void testValidBSNNumbers(String bsnString) {
        assertTrue(BSN.isValid(bsnString));
    }
}