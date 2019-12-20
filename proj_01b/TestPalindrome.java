import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        /* Test isPalindrome with a String. */
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("Ata"));
        assertTrue(palindrome.isPalindrome("ata"));
        assertTrue(palindrome.isPalindrome("noon"));

        /* Test isPalindrome with a String and CharacterComparator. */
        CharacterComparator comparator = new OffByOne();
        assertTrue(palindrome.isPalindrome("", comparator));
        assertTrue(palindrome.isPalindrome("a", comparator));
        assertFalse(palindrome.isPalindrome("aa", comparator));
        assertFalse(palindrome.isPalindrome("ata", comparator));
        assertTrue(palindrome.isPalindrome("%d$", comparator));
        assertTrue(palindrome.isPalindrome("l%$m", comparator));
        CharacterComparator comparatorN = new OffByN(2);
        assertTrue(palindrome.isPalindrome("", comparatorN));
        assertTrue(palindrome.isPalindrome("a", comparatorN));
        assertFalse(palindrome.isPalindrome("aa", comparatorN));
        assertFalse(palindrome.isPalindrome("atb", comparatorN));
        assertTrue(palindrome.isPalindrome("drf", comparatorN));
        assertTrue(palindrome.isPalindrome("lcan", comparatorN));
    }
}
