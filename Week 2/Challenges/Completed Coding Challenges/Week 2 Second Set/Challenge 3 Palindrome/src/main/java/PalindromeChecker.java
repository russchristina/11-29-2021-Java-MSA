public class PalindromeChecker {

    public String removeSpacesLowerCase(String userInput) {
        char[] inputChars = userInput.toLowerCase().toCharArray();
        StringBuilder userInputNoSpaceLowerCase = new StringBuilder();
        for(char c: inputChars) if(c != ' ') userInputNoSpaceLowerCase.append(c);
        return userInputNoSpaceLowerCase.toString();
    }

    public StringBuffer reverseStringBuffer(String userInput) {
        StringBuffer reverseInput = new StringBuffer(removeSpacesLowerCase(userInput));
        reverseInput.reverse();
        return reverseInput;
    }

    public void doesItPalindrome(String userInput){
        System.out.printf("Is \"%s\" a palindrome? ", userInput);
        System.out.println(removeSpacesLowerCase(userInput).contentEquals(reverseStringBuffer(userInput)));
    }

    public static void main(String[] args) {
        PalindromeChecker palindromeChecker = new PalindromeChecker();
        palindromeChecker.doesItPalindrome("r ac ec           ar");
        palindromeChecker.doesItPalindrome("Hasda ajksd");
        palindromeChecker.doesItPalindrome("a iBo hpHO bIa");

    }

}
