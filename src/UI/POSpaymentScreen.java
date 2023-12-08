package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.CashDrawer;
import PD.Cashier;
import PD.Register;
import PD.Sale;
import PD.Session;
import PD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POSpaymentScreen
 * This UI allows the cashier to choose what the user is paying with
 * Displays how much the user owes
 */
public class POSpaymentScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 * @param cashDrawer
	 * @param cashier
	 * @param register
	 * @param sale
	 * @param session
	 * @param isCash
	 */
	public POSpaymentScreen(JFrame currentFrame, Store store, CashDrawer cashDrawer, Cashier cashier, Register register, Sale sale, Session session, Boolean isCash) {
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
		
		textField_1 = new JTextField(sale.calcAmtTendered().toString());
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
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POScreditPayment(currentFrame, store, cashDrawer, cashier, register, sale, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(31, 246, 85, 21);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Payment Complete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isCash) {
					cashDrawer.addCash(BigDecimal.valueOf(Double.valueOf(textField_1.getText())));
				}
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSsaleScreen(currentFrame, store, cashDrawer, cashier, register, sale, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(217, 229, 143, 21);
		add(btnNewButton_3);
		btnNewButton_3.setEnabled(false);
		
		if(sale.isPaymentEnough())
		{
			btnNewButton_3.setEnabled(true);
		}
		
	}

}
