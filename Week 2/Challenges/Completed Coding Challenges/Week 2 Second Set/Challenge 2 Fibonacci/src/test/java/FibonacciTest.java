import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FibonacciTest {

    /*
    The fibonacci Test was created first, based on the formula in Challenge Thoughts.txt
    The tests were created in such a way to try to fail and different points.

    For example, the fibonacciLengthLinkedListTest was started out at the Assertion.assertEqual
    stage to look for what I wanted before any other code was created. Then working backwards,
    I created the fibonacci generator class and called a method for a LinkedList and its size.

    The intention was to test to by trying to fail, I first made it return null to test for a
    null pointer exception, then a test with the wrong size, then finally I created a success based
    on the minimum requirements. After that, the test was refactored to be easier to build upon

     */

    public FibonacciGenerator fibonacciSequenceBuilder(int integerN) {
        FibonacciGenerator fibonacciGenerator = new FibonacciGenerator();
        fibonacciGenerator.setIntegerN(integerN);
        return fibonacciGenerator;
    }

    public int fibonacciSequenceValueAtEnd(FibonacciGenerator fibonacciSequence){
        return fibonacciSequence.getFibonacciLinkedList().get(fibonacciSequence.getFibonacciLinkedList().size() - 1);
    }

    @Test
    public void fibonacciLengthLinkedListSizeTest(){
        Assertions.assertEquals(5,
                fibonacciSequenceBuilder(5).getFibonacciLinkedList().size());
    }

    @Test
    public void fibonacciEquationN1Test() {
        int fibonacciSequenceN1Value = 1;
        Assertions.assertEquals(fibonacciSequenceN1Value,
                fibonacciSequenceValueAtEnd(fibonacciSequenceBuilder(1)));
    }

    @Test
    public void fibonacciEquationN2Test() {
        int fibonacciSequenceN2Value = 1;
        Assertions.assertEquals(fibonacciSequenceN2Value,
                fibonacciSequenceValueAtEnd(fibonacciSequenceBuilder(2)));
    }

    @Test
    public void fibonacciEquationN3Test() {
        int fibonacciSequenceN3Value = 2;
        Assertions.assertEquals(fibonacciSequenceN3Value,
                fibonacciSequenceValueAtEnd(fibonacciSequenceBuilder(3)));
    }

    @Test
    public void fibonacciEquationN5Test() {
        int fibonacciSequenceN5Value = 5;
        Assertions.assertEquals(fibonacciSequenceN5Value,
                fibonacciSequenceBuilder(5).getFibonacciLinkedList().get(4));

    }

}
