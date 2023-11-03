package example12;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    private static Profile profile;
    private static BooleanQuestion questionIsThereRelocation;
    private static Answer answerThereIsRelocation;
    private static Answer answerThereIsNotRelocation;
    private static Answer answerReimburseTuition;
    private static Answer answerDoesNotReimburseTuition;
    private static Criteria criteria;

    @BeforeAll
    private static void createProfile() {
        profile = new Profile();
    }

    @BeforeAll
    private static void createQuestionAndAnswer() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);
        answerReimburseTuition = new Answer(new BooleanQuestion(1, ""), Bool.FALSE);
        answerDoesNotReimburseTuition = new Answer(new BooleanQuestion(1, ""), Bool.TRUE);
    }

    @BeforeAll
    private static void createCriteria() {
        criteria = new Criteria();
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

    @Test
    void doesNotMatchWhenNoneOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerDoesNotReimburseTuition, Weight.Important));

        boolean result = profile.matches(criteria);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void matchesWhenAnyOfMultipleCriteriaMatch() {
        profile.add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerDoesNotReimburseTuition, Weight.Important));

        assertThat(profile.matches(criteria)).isEqualTo(true);
    }

    @Test
    void matchesWhenCriterionIsDontCare() {
        profile.add(answerDoesNotReimburseTuition);
        Criterion criterion = new Criterion(answerReimburseTuition, Weight.DontCare);

        assertThat(profile.matches(criterion)).isEqualTo(true);
    }
}
