package UI;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import PD.CashDrawer;
import PD.Cashier;
import PD.Check;
import PD.Credit;
import PD.Register;
import PD.Sale;
import PD.Session;
import PD.Store;
import PD.TaxCategory;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POScashScreen
 * This UI allows the cashier to input a credit amount, and information
 * Updates the total money left to pay, or the change to give, and updates the cashDrawer
 */
public class POScreditPayment extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	/**
	 * 
	 * @param currentFrame
	 * @param store
	 * @param cashDrawer
	 * @param cashier
	 * @param register
	 * @param sale
	 * @param session
	 */
	public POScreditPayment(JFrame currentFrame, Store store, CashDrawer cashDrawer, Cashier cashier, Register register, Sale sale, Session session) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setBounds(157, 25, 120, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Payment due:");
		lblNewLabel_1.setBounds(20, 55, 96, 13);
		add(lblNewLabel_1);
		
		BigDecimal s = sale.calcTotal().subtract(sale.getTotalPayments());
		if(s.compareTo(new BigDecimal(0)) < 0)
		{
			s = new BigDecimal(0);
		}
		
		textField = new JTextField(s.toString());
		textField.setBounds(20, 78, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Amount Tendered:");
		lblNewLabel_2.setBounds(20, 116, 96, 13);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(20, 142, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Cash");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POScashScreen(currentFrame, store, cashDrawer, cashier, register, sale, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(31, 181, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Check");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POScheckPanel(currentFrame, store, cashDrawer, cashier, register, sale, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(31, 214, 85, 21);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Credit");
		btnNewButton_2.setBounds(31, 246, 85, 21);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Payment Complete");
		btnNewButton_3.setBounds(206, 246, 143, 21);
		add(btnNewButton_3);
		btnNewButton_3.setEnabled(false);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Credit Payment");
		lblNewLabel_3.setBounds(189, 55, 143, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount:");
		lblNewLabel_4.setBounds(169, 81, 63, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Card Type:");
		lblNewLabel_5.setBounds(169, 116, 63, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Account num:");
		lblNewLabel_6.setBounds(169, 145, 129, 13);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Expire Date:");
		lblNewLabel_7.setBounds(169, 168, 63, 20);
		add(lblNewLabel_7);
		
		DefaultComboBoxModel<String> listModel = new DefaultComboBoxModel<String>();
		listModel.addElement("Visa");
		listModel.addElement("MasterCard");
		listModel.addElement("Discover");
		listModel.addElement("American Express");
		JComboBox<String> comboBox = new JComboBox<String>(listModel);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setBounds(242, 112, 96, 21);
		add(comboBox);
		
		JButton btnNewButton_4 = new JButton("Save");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credit credit = new Credit();
				
				credit.setAmtTendered(BigDecimal.valueOf(Double.valueOf(textField_2.getText())));
				
				credit.setCardType((String) comboBox.getSelectedItem());
				
				credit.setAcctNumber(textField_3.getText());
				credit.setExpireDate(textField_4.getText());
				
				credit.setCreditTotal(sale.calcTotal());
				sale.addPayment(credit);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSpaymentScreen(currentFrame, store, cashDrawer, cashier, register, sale, session, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_4.setBounds(169, 204, 85, 21);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cancel");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(282, 204, 85, 21);
		add(btnNewButton_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(242, 78, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(242, 142, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(242, 169, 96, 19);
		add(textField_4);
		textField_4.setColumns(10);
	}
}
