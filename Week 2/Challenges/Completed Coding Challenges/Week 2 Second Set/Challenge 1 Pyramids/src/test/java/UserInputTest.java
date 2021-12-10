import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserInputTest {

    /*
     I created these tests to be used to between user input and pyramid generation.
     The main focus on these was to ensure the correct data was being transferred
     between the objects, and that they would maintain their data.
     The input variables need to be either an integer for the row number, or char
     for the type of character to build the pyramid with.
     */

    @Test
    public void inputNumberTest() {
        int numberOfRowsFromUser = 5;
        char characterToPrint = '*';
        UserInput userInput = new UserInput(numberOfRowsFromUser, characterToPrint);
        Assertions.assertEquals(5, userInput.getNumberOfRows());
    }

    @Test
    public void characterInputTest() {
        int numberOfRowsFromUser = 5;
        char characterToPrint = 'd';
        UserInput userInput = new UserInput(numberOfRowsFromUser, characterToPrint);
        Assertions.assertEquals('d', userInput.getCharacterToPrint());
    }


}
