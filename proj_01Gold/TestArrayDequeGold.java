import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> dqStu = new StudentArrayDeque<>();
        ArrayDeque<Integer> dq = new ArrayDeque<>()

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform(3);

            if (numberBetweenZeroAndOne == 0) {
                dqStu.addLast(i);
                dq.addLast(i);
                ass
            } else {
                dqStu.addFirst(i);
                dq.addFirst(i);
            }
        }
    }
}
