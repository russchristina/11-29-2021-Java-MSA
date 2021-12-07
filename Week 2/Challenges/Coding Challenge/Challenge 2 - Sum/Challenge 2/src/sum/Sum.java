package sum;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Sum extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Fields
	private ArrayList<Integer> numbers;
	private JPanel panel;
	private JPanel bPanel;
	private JTextField text;
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton four;
	private JButton five;
	private JButton six;
	private JButton seven;
	private JButton eight;
	private JButton nine;
	private JButton zero;
	private JButton plus;
	private JButton equals;
	private JButton clear;
	private JButton clearAll;
	private boolean justEqualed;
	
	// Constructor
	public Sum() {
		
		// Initialize
		numbers = new ArrayList<>();
		panel = new JPanel(new GridLayout(2, 6));
		bPanel = new JPanel(new GridLayout(1, 2));
		text = new JTextField(20);
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		zero = new JButton("0");
		plus = new JButton("+");
		equals = new JButton("=");
		clear = new JButton("Clear");
		clearAll = new JButton("ClearAll");
		justEqualed = false;
		
		// Set window options
		setTitle ("Add Integers");
        setSize (350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible (true);
		
		// Add buttons to panel
		panel.add(one);
		panel.add(two);
		panel.add(three);
		panel.add(four);
		panel.add(five);
		panel.add(plus);
		panel.add(six);
		panel.add(seven);
		panel.add(eight);
		panel.add(nine);
		panel.add(zero);
		panel.add(equals);
		
		bPanel.add(clear);
		bPanel.add(clearAll);
		
		// Prevent text field editing
		text.setEditable(false);
		text.setBackground(Color.YELLOW);
		
		// Add to frame
		add(text, BorderLayout.NORTH);
		add(panel);
		add(bPanel, BorderLayout.SOUTH);
		
		one.addActionListener ((ActionEvent e) -> {
			numberButtons(1);}); // End listener    
		two.addActionListener ((ActionEvent e) -> {
			numberButtons(2);}); // End listener        
		three.addActionListener ((ActionEvent e) -> {
			numberButtons(3);}); // End listener        
		four.addActionListener ((ActionEvent e) -> {
			numberButtons(4);}); // End listener        
		five.addActionListener ((ActionEvent e) -> {
			numberButtons(5);}); // End listener        
		six.addActionListener ((ActionEvent e) -> {
			numberButtons(6);}); // End listener       
		seven.addActionListener ((ActionEvent e) -> {
			numberButtons(7);}); // End listener       
		eight.addActionListener ((ActionEvent e) -> {
			numberButtons(8);}); // End listener        
		nine.addActionListener ((ActionEvent e) -> {
			numberButtons(9);}); // End listener        
		zero.addActionListener ((ActionEvent e) -> {
			numberButtons(0);}); // End listener  
		clear.addActionListener ((ActionEvent e) -> {
			text.setText("");}); // End listener
		clear.addActionListener ((ActionEvent e) -> {
			text.setText("");}); // End listener
		clearAll.addActionListener ((ActionEvent e) -> {
			text.setText("");
			numbers.clear();
		}); // End listener
		plus.addActionListener ((ActionEvent e) -> {
            numbers.add(Integer.parseInt(text.getText()));
            text.setText(""); 
        }); // End listener
		equals.addActionListener ((ActionEvent e) -> {
			numbers.add(Integer.parseInt(text.getText()));
			text.setText(String.valueOf(addInts(numbers)));
			numbers.clear();
			justEqualed= true;
        }); // End listener	
	} // End constructor
	
	// Method to prevent duplicate code for number button handling
	public void numberButtons(int n) {
		if (justEqualed) {
			text.setText("");
			justEqualed = false;
		}
		text.setText(text.getText() + n);
	} // End method
	
	// Method to handle and return addition calculations
	public int addInts(ArrayList<Integer> numbers) {
		int sum = 0;
		for(int num: numbers){
			sum += num;
		} // End for loop
		return sum;
	} // End method
	
	// Main
	public static void main(String[] args) {
		Sum s  = new Sum();
	} // End main
} // End class
