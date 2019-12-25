package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(10);
        arb.enqueue(33.1); // 33.1 null null  null
        arb.enqueue(44.8); // 33.1 44.8 null  null
        arb.enqueue(62.3); // 33.1 44.8 62.3  null
        arb.enqueue(-3.4); // 33.1 44.8 62.3 -3.4
        double res = arb.dequeue();     // 44.8 62.3 -3.4  null (returns 33.1)
        double exp = 33.1;
        assertEquals(exp, res, 0.01);

        Iterator<Double> it = arb.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
