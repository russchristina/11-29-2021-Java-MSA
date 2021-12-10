public class UserInput {

    private int numberOfRows;
    private char characterToPrint;

    public UserInput(int numberOfRows, char characterToPrint){
        this.numberOfRows = numberOfRows;
        this.characterToPrint = characterToPrint;
    }


    public int getNumberOfRows() {

        return numberOfRows;
    }

    public char getCharacterToPrint() {

        return characterToPrint;
    }

    public static void main(String... args){

        int numberOfRows = 90;
        char characterToPrint = '8';
        UserInput userInput = new UserInput(numberOfRows, characterToPrint);
        PyramidPrinter pyramidPrinter = new PyramidPrinter(userInput);
        System.out.println(pyramidPrinter.generatePyramidString());
    }

}
