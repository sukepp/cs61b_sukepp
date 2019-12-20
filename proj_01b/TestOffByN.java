import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testEqualChars() {
        OffByN offBy3 = new OffByN(3);
        assertTrue(offBy3.equalChars('a', 'd'));
        assertFalse(offBy3.equalChars('a', 'b'));
        OffByN offBy4 = new OffByN(2);
        assertTrue(offBy4.equalChars('c', 'a'));
        assertFalse(offBy4.equalChars('e', 'e'));
    }
}
