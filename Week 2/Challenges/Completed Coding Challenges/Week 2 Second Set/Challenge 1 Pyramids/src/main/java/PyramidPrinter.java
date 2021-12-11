import static java.lang.Thread.currentThread;

public class PyramidPrinter {

    public void printLeftPyramid(int numberOfRows, char characterToPrint) {
        for(int i = 1; i < numberOfRows + 1; i++) System.out.print(generateRow(i, characterToPrint));
        //for(int i = numberOfRows - 1; i >= 0; i--) System.out.print(generateRow(i, characterToPrint));
    }

    public void printRisingAndFallingPyramid(int numberOfRows, char characterToPrint) {
        for(int i = 1; i < numberOfRows + 1; i++) System.out.print(generateRow(i, characterToPrint));
        for(int i = numberOfRows - 1; i >= 0; i--) System.out.print(generateRow(i, characterToPrint));
    }

    public void printRightPyramid(int numberOfRows, char characterToPrint) {
        for(int i=numberOfRows;i>=0;i--) {
            System.out.print(generateRowWithoutEndCharacter(i, ' '));
            System.out.print(generateRow(numberOfRows - i, characterToPrint));
        }
    }

    public void printDiamond(int numberOfRows, char characterToPrint) {
        int row = numberOfRows/2;
        for(int i=row;i>=0;i--){
            System.out.print(generateRowWithoutEndCharacter(i, ' '));
            System.out.print(generateRowWithoutEndCharacter(row - i, characterToPrint));
            System.out.print(generateRowWithoutEndCharacter(row - i, characterToPrint));
            System.out.print(generateRow(i, ' '));
        }
        for(int i=1;i<row + 1;i++){
            System.out.print(generateRowWithoutEndCharacter(i, ' '));
            System.out.print(generateRowWithoutEndCharacter(row - i, characterToPrint));
            System.out.print(generateRowWithoutEndCharacter(row - i, characterToPrint));
            System.out.print(generateRow(i, ' '));
        }
    }

    public void printTruePyramid(int numberOfRows) {
        for(int i=numberOfRows;i>=0;i--){
            System.out.print(generateRowWithoutEndCharacter(i, ' '));
            System.out.print(generateRowWithoutEndCharacter(numberOfRows - i, '*'));
            System.out.print(generateRowWithoutEndCharacter(numberOfRows - i, '#'));
            System.out.print(generateRow(i, ' '));
        }
    }

    public StringBuilder generateRowWithoutEndCharacter(int lengthOfRow, char characterToPrint) {
        StringBuilder row = new StringBuilder();
        for(int i = 0; i < lengthOfRow; i++) row.append(characterToPrint);
        return row;
    }

    public StringBuilder generateRow(int lengthOfRow, char characterToPrint) {
        StringBuilder row = new StringBuilder();
        for(int i = 0; i < lengthOfRow; i++) row.append(characterToPrint);
        row.append('\n');
        return row;
    }

    public static void main(String... args) throws InterruptedException {
        PyramidPrinter pyramidPrinter = new PyramidPrinter();
        pyramidPrinter.printLeftPyramid(7, '*');
        pyramidPrinter.printRisingAndFallingPyramid(7, '+');
        pyramidPrinter.printRightPyramid(10, 'a');
        pyramidPrinter.printDiamond(10, '$');
        pyramidPrinter.printTruePyramid(10);
        pyramidPrinter.pyramadness();
    }

    private void pyramadness() {
        int numberOfRows = 5;
        int numberOfPyramids = 5;
        for (int j = 0; j < numberOfRows; j++) {
            for (int i = numberOfRows; i >= 0; i--) {
                System.out.print('?');
                for(int k = 0; k < numberOfPyramids; k++){
                    System.out.print(generateRowWithoutEndCharacter(i, '?'));
                    System.out.print(generateRowWithoutEndCharacter(numberOfRows - i, 'u'));
                    System.out.print(generateRowWithoutEndCharacter(numberOfRows - i, 'p'));
                    System.out.print(generateRowWithoutEndCharacter(i, '?'));
                }
                System.out.print(generateRow(i, ' '));
            }
        }
    }
}
