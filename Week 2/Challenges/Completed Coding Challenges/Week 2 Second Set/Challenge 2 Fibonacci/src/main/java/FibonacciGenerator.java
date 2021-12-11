import java.util.LinkedList;

public class FibonacciGenerator {
    
    //Changed from List<> to LinkedList<> to take advantage of getLast() of LinkedList
    //Only occurence of Object referencing.
    private LinkedList<Integer> fibonacciSequence;

    public LinkedList<Integer> generateFibonacciLinkedList(int integerN) {
        fibonacciSequence = new LinkedList<>();
        for(int i = 0; i < integerN; i++) fibonacciSequence.add(fibonacciRowSolver(i));
        return fibonacciSequence;
    }

    public int fibonacciRowSolver(int rowNumber) {
        if(rowNumber == 0 || rowNumber == 1) return 1;
        int previousSequence = fibonacciSequence.get(rowNumber-2);
        return (fibonacciSequence.getLast()+previousSequence);
    }

    //Method call that takes in an inputN and prints out using forEach with
    //method referencing of each value in the linkedList to just print out the sequence
    //from the linkedList

    public void fibonacciSequencePrinter(int integerN){
        generateFibonacciLinkedList(integerN).forEach(System.out::println);
    }

    public static void main(String[] args) {
        FibonacciGenerator fibonacciGenerator = new FibonacciGenerator();
        fibonacciGenerator.fibonacciSequencePrinter(20);
    }
}
