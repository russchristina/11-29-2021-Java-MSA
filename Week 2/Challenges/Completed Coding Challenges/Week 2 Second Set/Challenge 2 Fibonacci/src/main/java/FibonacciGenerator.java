import java.util.LinkedList;
import java.util.List;

public class FibonacciGenerator {

    private int integerN;
    private List<Integer> fibonacciSequence = new LinkedList<>();

    public FibonacciGenerator(){}

    public FibonacciGenerator(int integerN) {
        this.integerN = integerN;
    }

    public List<Integer> getFibonacciLinkedList() {
        for(int i = 0; i < integerN; i++) fibonacciSequence.add(fibonacciRowSolver(i));
        return fibonacciSequence;
    }

    public int fibonacciRowSolver(int rowNumber) {
        if(rowNumber == 0 || rowNumber == 1) return 1;
        int currentSequence = fibonacciSequence.get(rowNumber - 1);
        int previousSequence = fibonacciSequence.get(rowNumber - 2);
        return ( currentSequence+previousSequence);
    }

    //Method call that takes in an inputN and prints out using stream and forEach with
    //method referencing of each value in the linkedList

    public void fibonacciSequencePrinter(int inputN){
        FibonacciGenerator fibonacciGenerator = new FibonacciGenerator(inputN);
        fibonacciGenerator.getFibonacciLinkedList().stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        FibonacciGenerator fibonacciGenerator = new FibonacciGenerator();
        fibonacciGenerator.fibonacciSequencePrinter(10);
    }
}
