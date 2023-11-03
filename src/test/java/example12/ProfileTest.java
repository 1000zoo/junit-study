package example12;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    private static Profile profile;
    private static BooleanQuestion questionIsThereRelocation;
    private static Answer answerThereIsRelocation;

    @BeforeAll
    private static void createProfile() {
        profile = new Profile();
    }

    @BeforeAll
    private static void createQuestionAndAnswer() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
    }

    @Test
    void matchesNothingWhenProfileEmpty() {
        Criterion criterion = new Criterion(new Answer(questionIsThereRelocation, Bool.TRUE), Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answerThereIsRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertThat(result).isEqualTo(true);
    }
}
