package example06;

import example02.Answer;
import example02.Bool;
import example02.BooleanQuestion;
import example02.PercentileQuestion;
import example02.Profile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProfilePerformanceTest {

    private static Profile profile;

    @BeforeAll
    public static void beforeAll() {
        profile = new Profile("Bull Hockey, Inc.");
    }

    @Test
    void profilePerformanceTest() {
        int dataSize = 5000;
        for (int i = 0; i < dataSize; i++) {
            profile.add(new Answer(new BooleanQuestion(i, String.valueOf(i)), Bool.FALSE));
            profile.add(new Answer(new PercentileQuestion(i, String.valueOf(i), new String[]{}), 0));
        }

        int numberOfTimes = 1000;
        long elapsedMs = run(numberOfTimes,
                () -> profile.find(a -> a.getQuestion().getClass() == PercentileQuestion.class));
        Assertions.assertThat(elapsedMs < 1000).isEqualTo(true);
    }

    private long run(int numberOfTimes, Runnable function) {
        long start = System.nanoTime();
        for (int i = 0; i < numberOfTimes; i++) {
            function.run();
        }
        long stop = System.nanoTime();
        return (stop - start) / 1000000;
    }
}
