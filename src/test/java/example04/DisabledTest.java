package example04;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledTest {

    @Test
    public void runTest() {
        System.out.println("DisabledTest.runTest");
    }

    @Test
    @Disabled
    public void disabledTest() {
        System.out.println("DisabledTest.disabledTest");
    }
}
