public class PyramidPrinter {

    private StringBuffer pyramidBuffer;

    public StringBuffer generatePyramidString(int numberOfRows, char characterToPrint) {
        this.pyramidBuffer = new StringBuffer();
        for(int i = 1; i < numberOfRows + 1; i++) pyramidBuffer.append(generateRow(i, characterToPrint));
        return pyramidBuffer;
    }

    public StringBuffer generateRow(int lengthOfRow, char characterToPrint) {
        StringBuffer row = new StringBuffer();
        for(int i = 0; i < lengthOfRow; i++) row.append(characterToPrint);
        row.append('\n');
        return row;
    }

    public static void main(String... args){
        PyramidPrinter pyramidPrinter = new PyramidPrinter();
        System.out.println(pyramidPrinter.generatePyramidString(4, '*'));
        System.out.println(pyramidPrinter.generatePyramidString(4, '+'));
        System.out.println(pyramidPrinter.generatePyramidString(7, '|'));
    }


}
