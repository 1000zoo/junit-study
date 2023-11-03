package example06;

import example02.Answer;
import example02.Bool;
import example02.BooleanQuestion;
import example02.PercentileQuestion;
import example02.Profile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    private static Profile profile;

    @BeforeAll
    public static void beforeAll() {
        profile = new Profile("Bull Hockey, Inc.");
    }

    int[] ids(Collection<Answer> answerCollection) {
        return answerCollection.stream().mapToInt(a -> a.getQuestion().getId()).toArray();
    }

    @Test
    public void findAnswerBasedOnPredicate() {
        profile.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
        profile.add(new Answer(new PercentileQuestion(2, "2", new String[]{}), 0));
        profile.add(new Answer(new PercentileQuestion(3, "3", new String[]{}), 0));

        List<Answer> booleanAnswers = profile.find(a -> a.getQuestion().getClass() == BooleanQuestion.class);
        List<Answer> percentileAnswers = profile.find(a -> a.getQuestion().getClass() == PercentileQuestion.class);
        List<Answer> allAnswer = new ArrayList<>();

        allAnswer.addAll(booleanAnswers);
        allAnswer.addAll(percentileAnswers);

        int[] result = ids(allAnswer);

        Assertions.assertThat(result).isEqualTo(new int[]{1, 2, 3});
    }
}
