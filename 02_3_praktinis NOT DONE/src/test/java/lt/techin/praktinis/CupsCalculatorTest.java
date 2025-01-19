package lt.techin.praktinis;

import base.BaseIOTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CupsCalculatorTest extends BaseIOTest {

    @Test
    void calculateCups10() {
        CupsCalculator calc = new CupsCalculator();

        calc.calculateCups(10);

        assertEquals("Dėžių: 3\nLiko puodelių: 1\n", updateLineSpliterators(outContent.toString()));
    }

    @Test
    void calculateCups17() {
        CupsCalculator calc = new CupsCalculator();

        calc.calculateCups(17);

        assertEquals("Dėžių: 5\nLiko puodelių: 2\n", updateLineSpliterators(outContent.toString()));
    }

    @Test
    void calculateCups12() {
        CupsCalculator calc = new CupsCalculator();

        calc.calculateCups(12);

        assertEquals("Dėžių: 4\nLiko puodelių: 0\n", updateLineSpliterators(outContent.toString()));
    }


}
