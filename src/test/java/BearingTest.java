import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import example07.Bearing;
import example07.BearingOutOfRangeException;
import org.junit.jupiter.api.Test;

public class BearingTest {

    @Test
    void throwsOnNegativeNumber() {
        assertThrows(
                BearingOutOfRangeException.class,
                () -> new Bearing(-1)
        );
    }

    @Test
    void throwsWhenBearingTooLarge() {
        assertThrows(
                BearingOutOfRangeException.class,
                () -> new Bearing(360)
        );
    }

    @Test
    void answersValidBearing() {
        int origin = 10;
        Bearing bearing = new Bearing(origin);
        int bearingValue = bearing.value();

        assertThat(bearingValue).isEqualTo(origin);
    }

    @Test
    void answerAngleBetweenItAndAnotherBearing() {
        Bearing bearing1 = new Bearing(10);
        Bearing bearing2 = new Bearing(15);

        assertThat(bearing2.angleBetween(bearing1)).isEqualTo(5);
    }
}
