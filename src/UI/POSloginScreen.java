package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.CashDrawer;
import PD.Cashier;
import PD.Price;
import PD.Register;
import PD.Sale;
import PD.Session;
import PD.Store;
import PD.TaxCategory;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * @Author Hank Heiselbetz
 * POSloginScreen
 * This UI allows the user to login to start a session
 */
public class POSloginScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	JLabel lblNewLabel_5 = new JLabel();
	

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 */
	public POSloginScreen(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(176, 48, 85, 31);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cashier Number:");
		lblNewLabel_1.setBounds(69, 93, 108, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Register Number:");
		lblNewLabel_2.setBounds(69, 116, 108, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Starting Cash:");
		lblNewLabel_3.setBounds(69, 139, 108, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password:");
		lblNewLabel_4.setBounds(69, 162, 85, 13);
		add(lblNewLabel_4);
		
		DefaultComboBoxModel<Cashier> listModel = new DefaultComboBoxModel<Cashier>();
		for(Cashier i : store.getCashiers().values())
		{
			listModel.addElement(i);
		}
		JComboBox<Cashier> comboBox = new JComboBox<Cashier>(listModel);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setBounds(176, 89, 67, 21);
		add(comboBox);
		
		DefaultComboBoxModel<Register> listModel1 = new DefaultComboBoxModel<Register>();
		for(Register s : store.getRegisters().values())
		{
			listModel1.addElement(s);
		}
		JComboBox<Register> comboBox_1 = new JComboBox<Register>(listModel1);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox_1.setBounds(176, 115, 71, 21);
		add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(176, 139, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		passwordField.setBounds(176, 159, 85, 19);
		add(passwordField);
		
		lblNewLabel_5 = new JLabel("Wrong password");
		lblNewLabel_5.setBounds(176, 180, 143, 13);
		add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier cashier = (Cashier) comboBox.getSelectedItem();
				Register register = (Register) comboBox_1.getSelectedItem();
			
				if(cashier.getPassword().equals(passwordField.getText())){
					CashDrawer cashDrawer = new CashDrawer(BigDecimal.valueOf(Double.valueOf(textField.getText())), Integer.parseInt(register.getNumber()));
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSsaleScreen(currentFrame, store, cashDrawer, cashier, register, new Sale(), new Session()));
					currentFrame.getContentPane().revalidate();
				}
				else {
					lblNewLabel_5.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(69, 203, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(191, 203, 85, 21);
		add(btnNewButton_1);
		

	}
}
