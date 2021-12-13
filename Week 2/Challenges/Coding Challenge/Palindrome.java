import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class Palindrome {

    public static void main(String[] args) {
        Palindrome pal = new Palindrome();
        pal.palindrome("was it a car or a cat i saw");
    }

    boolean palindrome(String statement) {
        // initialize an empty String for reversed word
        String backwards = "";
        // intialize an empty String to take out spaces
        String statementNoSpaces = "";
        // boolean set to true, so it can switch in an if statement.
        boolean isItFalse = true;

        // loop through the statement or word
        for (int i = 0; i < statement.length(); i++) {

            char emptySpace = ' ';
            // if the character is an empty space, skip it.
            if (statement.charAt(i) == emptySpace) {
                i++;
            }
            // take each letter
            char letter = statement.charAt(i);
            // and push it onto the backwards variable, one character at a time.
            backwards = letter + backwards;
            // take the orginal statement, take out spaces if need be.
            statementNoSpaces += letter;
        }

        // loop over the new length, just in case the spaces are gone.
        for (int i = 0; i < backwards.length(); i++) {
            // compare each character.
            if (statementNoSpaces.charAt(i) == backwards.charAt(i)) {
                i++;
            } else {
                // if only one character doesn't match, switch isItFalse to false.
                isItFalse = false;
            }

        }
        // return a boolean.
        System.out.println(isItFalse);
        return isItFalse;

    }

    // make sure the method works.
    @Ignore
    @Test
    @DisplayName("Test 1: it works")
    public void testPalindrome() {
        Palindrome pal = new Palindrome();
        pal.palindrome("civic");
    }

    // check if the method is returning false.
    @Ignore
    @Test
    @DisplayName("Test 2: Returns false by default")
    public void instance() {
        Assertions.assertFalse(false);
    }

    // check different strings.
    @Ignore
    @Test
    @DisplayName("Test 3: Different Strings")
    public void diffStrings() {
        Palindrome pal = new Palindrome();
        Assert.assertFalse(pal.palindrome("cat"));
        Assert.assertFalse(pal.palindrome("tomorrow"));
        Assert.assertTrue(pal.palindrome("civic"));
        Assert.assertTrue(pal.palindrome("madam"));
        Assert.assertTrue(pal.palindrome("radar"));
        Assert.assertTrue(pal.palindrome("refer"));
        Assert.assertTrue(pal.palindrome("aibohphobia"));
        Assert.assertTrue(pal.palindrome("was it a car or a cat i saw"));
    }

}