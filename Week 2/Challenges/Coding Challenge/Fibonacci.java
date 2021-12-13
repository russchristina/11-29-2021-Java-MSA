import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci seq = new Fibonacci();
        seq.fibSequencer(7);
    }

    void fibSequencer(int n) {
        // initialize the first two numbers in the sequence.
        int firstNum = 0;
        int secondNum = 1;

        // if n is less than 0 don't run.
        if (n < 1)
            return;
        // for loop for looping through n amount of times from beginning.
        for (int i = 0; i < n; i++) {
            // print the old number to the console.
            System.out.print(secondNum + " ");
            // the next number is the firstNum + secondNum
            int next = firstNum + secondNum;
            // pass the firstNum value to the secondNum value
            firstNum = secondNum;
            // set the secondNum value to the value of the next integer.
            secondNum = next;
        }
    }

    // make sure getting output.
    @Ignore
    @Test
    @DisplayName("Test 1: Test method")
    public void doesItWork() {
        Fibonacci seq = new Fibonacci();
        seq.fibSequencer(7);
    }

    @Ignore
    @Test
    @DisplayName("Test 2: Test negative number")
    public void diffIntegers() {
        Fibonacci seq = new Fibonacci();
        seq.fibSequencer(-3);
    }

    @Ignore
    @Test
    @DisplayName("Test 3: Pass more numbers ")
    public void moreNumbers() {
        Fibonacci seq = new Fibonacci();
        seq.fibSequencer(7);
        seq.fibSequencer(9);
        seq.fibSequencer(19);
    }

}
