public class PyramidPrinter {

    private StringBuilder pyramidBuilder;

    public StringBuilder generatePyramidString(int numberOfRows, char characterToPrint) {
        this.pyramidBuilder = new StringBuilder();
        for(int i = 1; i < numberOfRows + 1; i++) pyramidBuilder.append(generateRow(i, characterToPrint));
        return pyramidBuilder;
    }

    public StringBuilder generateRow(int lengthOfRow, char characterToPrint) {
        StringBuilder row = new StringBuilder();
        for(int i = 0; i < lengthOfRow; i++) row.append(characterToPrint);
        row.append('\n');
        return row;
    }

    public static void main(String... args){
        PyramidPrinter pyramidPrinter = new PyramidPrinter();
        System.out.println(pyramidPrinter.generatePyramidString(4, '*'));
        System.out.println(pyramidPrinter.generatePyramidString(4, '+'));
        System.out.println(pyramidPrinter.generatePyramidString(200, '|'));
    }


}
