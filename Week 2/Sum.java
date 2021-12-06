public class Sum {

    public static void main(String[] args) {
        int a = 9;
        int b = 8;
        int c = 3;
        // must make a new instance if want to run method since not static.
        Sum addMethod = new Sum();
        // run method with any amount of integers
        System.out.println(addMethod.sumOfInt(a, b, c));
    }

    // make the method for the sum of two integers. return an int.
    public int sumOfInt(int... numbers) {
        // intialize sum at 0, add on top of it.
        int sum = 0;
        for (int i : numbers) {
            // add every integer onto the sum
            sum += i;
        }
        // return sum which is an integer.
        return sum;
    }
}
