package SandBox;
import java.awt.*; 
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.List;
public class PizzaPlace implements ActionListener {
JTextField myTextField;
ButtonGroup pizzaSize;
JRadioButton small;
JRadioButton medium;
JRadioButton large;
JList toppingList;
JComboBox myCombo;
JCheckBox breadsticks = new JCheckBox("Breadsticks");
JCheckBox salad = new JCheckBox("Salad");
JCheckBox soda = new JCheckBox("Soda");
JButton placeOrder;
JButton resetValue;
JTextArea commentArea;

public static void main(String[] args) {
		new PizzaPlace();
	}


	public PizzaPlace()
	{
		//Main
		JFrame myFrame = new JFrame("Pizza Place");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = (JPanel)myFrame.getContentPane();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel customerPanel = new JPanel();
		JLabel myLabel = new JLabel("Customer Name:");
		myTextField = new JTextField(20);
		customerPanel.add(myLabel);
		customerPanel.add(myTextField);
		mainPanel.add(customerPanel);
	
		//Pizza Size
		JPanel radioPanel = new JPanel();
		JLabel radioLabel = new JLabel("Pizza Size:");
		small = new JRadioButton("Small");
		medium = new JRadioButton("Medium");
		large = new JRadioButton("Large");
		small.setSelected(true);

		pizzaSize = new ButtonGroup();
		pizzaSize.add(small);
		small.addActionListener(this);
		pizzaSize.add(medium);
		medium.addActionListener(this);
		pizzaSize.add(large);
		large.addActionListener(this);
		
		radioPanel.add(radioLabel);
		radioPanel.add(small);
		radioPanel.add(medium);
		radioPanel.add(large);
		mainPanel.add(radioPanel);
		
		//Crust Type
		JPanel comboPanel = new JPanel();
		JLabel comboLabel = new JLabel("Crust Type");
		String[] crustType = {"Thin", "Thick","Deep Dish"};
		myCombo = new JComboBox(crustType);
		myCombo.addActionListener(this);
		comboPanel.add(comboLabel);
		comboPanel.add(myCombo);
		mainPanel.add(comboPanel);
		
		
		//Toppings
		JPanel toppingsPanel = new JPanel();
		JLabel pizzaLabel = new JLabel("Toppings");
		String[] toppingChoice = {"Pepperoni", "Sausage", "Green Peppers", "Onions", "Tomatoes", 
				"Anchovies", "Bacon", "Chicken", "Beef", "Olives","Mushrooms"};
		toppingList = new JList(toppingChoice);
		toppingList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane myScrollPane = new JScrollPane(toppingList);
		toppingsPanel.add(pizzaLabel);
		toppingsPanel.add(myScrollPane);
		mainPanel.add(toppingsPanel);
		
		//Extras
		JPanel checkPanel = new JPanel();
		JLabel checkLabel = new JLabel("Extras:");
		checkPanel.add(checkLabel);
		checkPanel.add(breadsticks);
		checkPanel.add(salad);
		checkPanel.add(soda);
		breadsticks.addActionListener(this);
		salad.addActionListener(this);
		soda.addActionListener(this);
		mainPanel.add(checkPanel);
		
		//Comments
		JPanel commentPanel = new JPanel();
		JLabel commentLabel = new JLabel("Order Comments");
		commentArea = new JTextArea(5,20);
		JScrollPane commentPane = new JScrollPane(commentArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		commentArea.setLineWrap(true);
		commentPanel.add(commentLabel);
		commentPanel.add(commentPane);
		mainPanel.add(commentPanel);
		
		//Buttons
		JPanel buttonPanel = new JPanel();
		placeOrder = new JButton("Place Order");
		placeOrder.addActionListener(this);
		resetValue = new JButton("Reset Values");
		resetValue.addActionListener(this);
		buttonPanel.add(placeOrder);
		buttonPanel.add(resetValue);
		mainPanel.add(buttonPanel);

		myFrame.pack();
		myFrame.setVisible(true);
	}


	
	public void actionPerformed(ActionEvent e) {
		
		Object control = e.getSource();
		String summary =  "";
		String textField = myTextField.getText();
		String smallSize = small.getText();
		String mediumSize = medium.getText();
		String largeSize = large.getText();
		String comboCrust = (String)myCombo.getSelectedItem();
		List toppingChoices = toppingList.getSelectedValuesList();
		String breadString = breadsticks.getText();
		String sodaString = soda.getText();
		String saladString = salad.getText();
		String comments = commentArea.getText();



		if(control == placeOrder)
		{
			
			summary += "PIZZA ORDER FOR: " + textField; 
			if(small.isSelected()==true)
			{
				
			summary+= "\nSIZE: " + smallSize;
			}
			
			
			else if(medium.isSelected()==true)
			{
				
			summary+= "\nSIZE: " + mediumSize;
			}
				

			else if(large.isSelected()==true)
			{
				
			summary+= "\nSIZE: " + largeSize;
			}
			
			
			summary += "\nCRUST TYPE : " + comboCrust;
			summary += "\nTOPPINGS:\n ";
			for(int i = 0; i < toppingChoices.size(); i++)
			{	
			summary +=  (String) toppingChoices.get(i);
			//why does this statement work?
			if (i < toppingChoices.size() - 1)
		      {
		    	 summary += "\n ";
		      }
			}
			summary += "\nEXTRAS:\n";
			if(breadsticks.isSelected()==true)
			{
			summary += breadString + "\n";
			}
			if(soda.isSelected()==true)
			{
			summary += soda.getText() + "\n";
			}
			if(salad.isSelected()==true)
			{
			summary += saladString + "\n";
			}
			summary+= "COMMENTS:\n" + comments;
			JOptionPane.showMessageDialog(null, summary);
			
		}
		if(control == resetValue)
		{
			myTextField.setText(" ");
			small.setSelected(true);
			myCombo.setSelectedIndex(0);
			toppingList.clearSelection();
			breadsticks.setSelected(false);
			soda.setSelected(false);
			salad.setSelected(false);
			commentArea.setText("");
			
		}
	}
}