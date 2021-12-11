import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromeCheckTest {
    /*
    Started with the Test class again, this time working with a UserInput class and a Palindrome Check class
    Used a mix of String and String buffer at the beginning
    The first attempt was to make the simple palindrome checker, the StringBuffer class comes with a reverse
    utility tool attached so it was easy enough to do that process.
    If I didn't have access to it then it would be a case of putting it into a char array and iterating
    backwards through the array and outputting it to another array of the same length
    Something like:
    char[] userInputCharArray = userInputString.toCharArray();
    List<Character> reverseInputCharArray = new ArrayList<>();
    for(int i = userInputString.length; i >= 0; i--) reverseInputCharArray.add(userInputString.length - i, userInputString[i]);

    The tests progressed, but with the Assertion first, that way it minimized any extra programming and kept it very
    efficient and very tight to the scope.
    There were quite a few compilation errors and the tests proved quite helpful in debugging.
    As new tests were added on, it was easier to refactor the tests to minimize repeated lines.

    Reversing and checking the reverse wasn't an issue.
    In order to check for palindromes that have reversed letter, I converted the input to a char array and put a
    toLowerCase() method on it.
    This was then passed to a remove space method, this was tested first without implementation in the Palindrome
    checker to make sure it would remove spaces.
    The user input is converted to a string with no spaces or capital letters, to quickly find palindromes no matter
    the number of spaces.
    This was done using an enhanced ForLoop that checks for a space character.
    I attempted to use a RegEx pattern finder and matcher, to find a way to generate the new string in another fashion.
    this was not within my ability, so I reverted to an easier solution for myself.
     */

    public PalindromeChecker palindromeCheckerBuilder(){
        return new PalindromeChecker();
    }

    @Test
    public void returnStringTest(){
        Assertions.assertTrue(palindromeCheckerBuilder().reverseStringBuffer("luffy") instanceof StringBuffer);
    }

    @Test
    public void reverseStringBufferTest(){
        Assertions.assertTrue(palindromeCheckerBuilder().reverseStringBuffer("abc").toString().
                contentEquals("cba"));
    }

    @Test
    public void removeSpacesTest(){
        Assertions.assertEquals("abc",
                palindromeCheckerBuilder().removeSpacesLowerCase("a b c"));
    }

    @Test
    public void reverseStringWithSpaceTest(){
        Assertions.assertTrue(palindromeCheckerBuilder().removeSpacesLowerCase("a b   c").
                contentEquals("abc"));
    }




}
