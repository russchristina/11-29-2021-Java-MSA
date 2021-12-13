import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Pyramid {

    // function with arg n
    void pyramid(int n) {
        // A string that reads \*
        String backslash = "\\*";
        // A blank string that will be added onto
        String start = "";

        // For loop, to loop throug the pyramid
        for (int i = 0; i < n; i++) {
            // With every loop, add a \*
            start += backslash;
            // print the new start variable onto a new line.
            System.out.println(start);
        }
    }

    @Ignore
    @Test
    @DisplayName("Test 1: Working")
    public void working() {
        Pyramid buildBlock = new Pyramid();
        buildBlock.pyramid(7);
    }

    @Ignore
    @Test
    @DisplayName("Test 2: Make sure JUnit is working ")
    public void f() {
    }

    @Ignore
    @Test
    @DisplayName("Test 3: Pass numbers through")
    public void numbers() {
        Pyramid buildBlock = new Pyramid();
        buildBlock.pyramid(7);
        buildBlock.pyramid(1);
        buildBlock.pyramid(3);
        buildBlock.pyramid(6);
        buildBlock.pyramid(2);

    }
}