public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<>();
        if (word == null) {
            return dq;
        }
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindromeHelper(Deque<Character> q) {
        if (q.size() < 2) {
            return true;
        }
        return q.removeFirst().equals(q.removeLast()) && isPalindromeHelper(q);
    }

    public boolean isPalindromeHelper(Deque<Character> q, CharacterComparator comparator) {
        if (q.size() < 2) {
            return true;
        }
        return comparator.equalChars(q.removeFirst(), q.removeLast()) && isPalindromeHelper(q, comparator);
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> dq = wordToDeque(word);
        return isPalindromeHelper(dq);
    }

    public boolean isPalindrome(String word, CharacterComparator comparator) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> dq = wordToDeque(word);
        return isPalindromeHelper(dq, comparator);
    }
}
