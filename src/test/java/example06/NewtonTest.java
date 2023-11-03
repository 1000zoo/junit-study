package example06;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

public class NewtonTest {
    static class Newton {
        private static final double TOLERANCE = 1E-16;

        public static double squareRoot(double n) {
            double approx = n;
            while (Math.abs(approx - n / approx) > TOLERANCE * approx) {
                approx = (n / approx + approx) / 2.0;
            }
            return approx;
        }
    }

    @Test
    public void squareRootTest() {
        final double PERCENTAGE = 99.999999;
        final double origin = 250.0;
        double result = Newton.squareRoot(origin);
        Assertions.assertThat(result).isCloseTo(origin, Percentage.withPercentage(PERCENTAGE));
    }

    @Test
    public void squareRootCrossTest() {
        final double PERCENTAGE = 99.999999;
        final double origin = 250.0;
        double result = Newton.squareRoot(origin);
        Assertions.assertThat(result).isCloseTo(Math.sqrt(origin), Percentage.withPercentage(PERCENTAGE));
    }
}
