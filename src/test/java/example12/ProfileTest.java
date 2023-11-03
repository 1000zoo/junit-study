package example12;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    private static Profile profile;
    private static BooleanQuestion questionIsThereRelocation;
    private static Answer answerThereIsRelocation;
    private static Answer answerThereIsNotRelocation;

    @BeforeAll
    private static void createProfile() {
        profile = new Profile();
    }

    @BeforeAll
    private static void createQuestionAndAnswer() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);
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

    @Test
    void doesNotMatchWhenNoMatchingAnswer() {
        profile.add(answerThereIsNotRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void matchesWhenContainsMultipleAnswers() {
        profile.add(answerThereIsRelocation);
        profile.add(answerThereIsNotRelocation);
        Criterion criterion = new Criterion(answerThereIsNotRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void matchAgainstNullAnswerReturnsFalse() {
        assertThat(new Answer(new BooleanQuestion(0, ""), Bool.TRUE).match(null)).isEqualTo(false);
    }
}
