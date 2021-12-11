public class PyramidPrinter {

    private char characterToPrint;
    private int numberOfRows;
    private StringBuffer pyramidBuffer = new StringBuffer();

    public char getPrintCharacter() {
        return characterToPrint;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }

    public StringBuffer generatePyramidString(int numberOfRows, char characterToPrint) {
        this.numberOfRows = numberOfRows;
        this.characterToPrint = characterToPrint;
        for(int i = 1; i < numberOfRows + 1; i++) pyramidBuffer.append(generateRow(i));
        return pyramidBuffer;
    }

    public StringBuffer generateRow(int lengthOfRow) {
        StringBuffer row = new StringBuffer();
        for(int i = 0; i < lengthOfRow; i++) row.append(characterToPrint);
        row.append('\n');
        return row;
    }

    public static void main(String... args){
        PyramidPrinter pyramidPrinter = new PyramidPrinter();
        System.out.println(pyramidPrinter.generatePyramidString(90, '*'));
    }


}
