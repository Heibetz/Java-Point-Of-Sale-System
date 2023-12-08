package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import PD.Cashier;
import PD.Person;
import PD.Store;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * cashierEditPanel
 * This UI allows cashiers to be edited
 */
public class cashierEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * 
	 * @param store
	 * @param currentFrame
	 * @param isAdd
	 * @param cashier
	 */
	public cashierEditPanel(Store store, JFrame currentFrame, Boolean isAdd, Cashier cashier) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("edit cashier");
		lblNewLabel.setBounds(134, 40, 75, 39);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(76, 94, 53, 17);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(76, 115, 96, 17);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(76, 136, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(168, 249, 85, 21);
		add(btnNewButton_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(76, 159, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(76, 182, 96, 19);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(76, 208, 66, 19);
		add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(223, 93, 96, 19);
		add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(223, 159, 34, 19);
		add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(311, 159, 53, 19);
		add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setBounds(21, 96, 45, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(21, 117, 45, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address:");
		lblNewLabel_3.setBounds(21, 139, 45, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("City:");
		lblNewLabel_4.setBounds(21, 162, 45, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Phone:");
		lblNewLabel_5.setBounds(21, 185, 45, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Password:");
		lblNewLabel_6.setBounds(21, 211, 75, 13);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("SSN");
		lblNewLabel_7.setBounds(181, 96, 45, 13);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("State:");
		lblNewLabel_8.setBounds(181, 162, 45, 13);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Zip:");
		lblNewLabel_9.setBounds(281, 162, 45, 13);
		add(lblNewLabel_9);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!isAdd) {store.removeCashier(cashier);};

				Person person = new Person(textField_1.getText(), textField_2.getText(), textField_3.getText(), 
						textField_7.getText(), textField_8.getText(), 
						textField_4.getText(), textField_6.getText());
				Cashier cashier1 = new Cashier();
				cashier1.addPerson(person);
				cashier1.setNumber(textField.getText());
				cashier1.setPassword(textField_5.getText());
				
				store.addCashier(cashier1);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new cashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(76, 250, 82, 19);
		add(btnNewButton);

	}
}
