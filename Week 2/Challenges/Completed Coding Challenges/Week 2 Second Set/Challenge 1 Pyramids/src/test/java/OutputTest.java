import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutputTest {

    /*
    The main focus for the output tests were to ensure that the UserInput object was being read correctly
    in order to generate the Pyramid Printer object.
    The classes correctCharacterFromUserTest and correctNumberOfRowsFromUserTest are a bit redundant, but I
    decided to make those values private so they needed getters and setters.
    The focus was ensuring correct transfer between UserInput obj to PyramidPrinter obj.

    I used a StringBuffer instead of a String due to the constant need for updating and appending character to
    the pyramid string. By using a Buffered String, the speed at which the pyramid would be outputted is
    considerably faster than if it were done with a String object.

    The final tests were to ensure a printable row was created, by having the first few tests correct, I could
    be assured that I could use them for the rest of the PyramidPrinter without issue.

     */

    public UserInput userInputBuilder(int numberOfRows, char characterToPrint){
        return new UserInput(numberOfRows, characterToPrint);
    }

    public PyramidPrinter pyramidPrinterBuilder(int numberOfRows, char characterToPrint) {
        UserInput userInput = userInputBuilder(numberOfRows, characterToPrint);
        return new PyramidPrinter(userInput);
    }

    @Test
    public void correctCharacterFromUserInputTest() {
        PyramidPrinter pyramidPrinter = pyramidPrinterBuilder(0, 'd');
        Assertions.assertEquals('d', pyramidPrinter.getPrintCharacter());
    }

    @Test
    public void correctNumberOfRowsFromUserInputTest() {
        PyramidPrinter pyramidPrinter = pyramidPrinterBuilder(5, '*');
        Assertions.assertEquals(5, pyramidPrinter.getNumberOfRows());
    }

    @Test
    public void returnsStringBufferToPrintTest() {
        PyramidPrinter pyramidPrinter = pyramidPrinterBuilder(3, '*');
        Assertions.assertTrue(pyramidPrinter.generatePyramidString() instanceof StringBuffer);
    }

    @Test
    public void endLineCharacterTest() {
        PyramidPrinter pyramidPrinter = pyramidPrinterBuilder(1, '*');
        Assertions.assertTrue(pyramidPrinter.generatePyramidString().charAt(1) == '\n');
    }


}
