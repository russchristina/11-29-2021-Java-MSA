public class PyramidPrinter {

    private char characterToPrint;
    private int numberOfRows;
    private StringBuffer pyramidBuffer = new StringBuffer();

    public PyramidPrinter(UserInput userInput) {
        this.characterToPrint = userInput.getCharacterToPrint();;
        this.numberOfRows = userInput.getNumberOfRows();
    }

    public char getPrintCharacter() {
        return characterToPrint;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public StringBuffer generatePyramidString() {
        for(int i = 1; i < numberOfRows + 1; i++) pyramidBuffer.append(generateRow(i));
        return pyramidBuffer;
    }

    public StringBuffer generateRow(int lengthOfRow) {
        StringBuffer row = new StringBuffer();
        for(int i = 0; i < lengthOfRow; i++) row.append(characterToPrint);
        row.append('\n');
        return row;
    }


}
