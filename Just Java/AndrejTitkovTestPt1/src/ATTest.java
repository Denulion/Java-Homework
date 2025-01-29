import lt.itakademija.exam.Exercises;
import lt.itakademija.exam.test.BaseTest;

public class ATTest extends BaseTest {
    @Override
    protected Exercises createExercises() {
        return new ExercisesImpl();
    }
}
