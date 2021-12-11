import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    /*
    Reformatted the code to have less useless tests, and ones that are more meaningful. Removed the userInput class
    I had originally and just initialized those with the PyramidPrinter Class.
     */

    public PyramidPrinter pyramidPrinterBuilder() {
        return new PyramidPrinter();
    }

    @Test
    public void returnsStringBufferToPrintTest() {
        PyramidPrinter pyramidPrinter = pyramidPrinterBuilder();
        Assertions.assertTrue(pyramidPrinter.generatePyramidString(3, '*')
                instanceof StringBuffer);
    }

    @Test
    public void endLineCharacterTest() {
        PyramidPrinter pyramidPrinter = pyramidPrinterBuilder();
        Assertions.assertTrue(pyramidPrinter.generatePyramidString(1, 'H').charAt(1) == '\n');
    }

    @Test
    public void lengthOfRowIncludingEndRowCharacterTest() {
        PyramidPrinter pyramidPrinter = pyramidPrinterBuilder();
        Assertions.assertEquals(5, pyramidPrinter.generateRow(4).length());
    }


}
