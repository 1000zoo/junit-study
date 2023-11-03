package example06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BoundaryConditionTest {

    private static ScoreCollection collection;

    @BeforeAll
    private static void beforeAll() {
        collection = new ScoreCollection();
    }

    @Test
    void throwsExceptionWhenAddingNull() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> collection.add(null)
        );
    }

    @Test
    void throwsExceptionWhenDividedByZero() {
        Assertions.assertThrows(
                ArithmeticException.class,
                () -> collection.arithmeticMean()
        );
    }
}
