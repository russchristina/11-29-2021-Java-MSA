public class PalindromeChecker {

    private String userInput;

    public PalindromeChecker(String userInput) {
        this.userInput = removeSpacesLowerCase(userInput);
    }

    public String removeSpacesLowerCase(String input) {
        char[] inputChars = input.toLowerCase().toCharArray();
        StringBuffer userInputNoSpaceLowerCase = new StringBuffer();
        for(char c: inputChars) if(c != ' ') userInputNoSpaceLowerCase.append(c);
        return userInputNoSpaceLowerCase.toString();
    }

    public StringBuffer reverseStringBuffer() {
        StringBuffer reverseInput = new StringBuffer(userInput);
        reverseInput.reverse();
        return reverseInput;
    }

    public boolean doesItPalindrome(){
        return userInput.contentEquals(reverseStringBuffer());
    }

    public static void main(String[] args) {
        String userInputString = "r ac ec           ar";
        PalindromeChecker palindromeChecker = new PalindromeChecker(userInputString);
        System.out.println(palindromeChecker.doesItPalindrome());
    }

    public String getUserInput() {
        return userInput;
    }
}
