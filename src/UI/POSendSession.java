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

import java.math.BigDecimal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POSendSession
 * This UI allows the cashier to end a session
 * Saves the cashier session data and asks for input of total cash left in drawer
 */
public class POSendSession extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 * @param cashDrawer
	 * @param cashier
	 * @param register
	 * @param session
	 */
	public POSendSession(JFrame currentFrame, Store store, CashDrawer cashDrawer, Cashier cashier, Register register, Session session) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Session Summary");
		lblNewLabel.setBounds(150, 23, 117, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cashier:");
		lblNewLabel_1.setBounds(24, 61, 70, 13);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Register:");
		lblNewLabel_2.setBounds(24, 94, 70, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Number Sales:");
		lblNewLabel_3.setBounds(24, 147, 96, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Total Sales:");
		lblNewLabel_4.setBounds(24, 170, 70, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Enter Cash:");
		lblNewLabel_5.setBounds(24, 194, 85, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Cash Count diff:");
		lblNewLabel_6.setBounds(24, 224, 96, 13);
		add(lblNewLabel_6);
		
		textField_2 = new JTextField(Integer.toString(session.getTotalSales()));
		textField_2.setBounds(124, 143, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(session.calcTotal().toString());
		textField_3.setBounds(124, 166, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal cd = cashDrawer.getCashAmount().subtract(BigDecimal.valueOf(Double.valueOf(textField_4.getText())));
				textField_5 = new JTextField(cd.toString());
				textField_5.setBounds(124, 220, 96, 19);
				add(textField_5);
				textField_5.setColumns(10);
				session.setDiff(cd);
			}
		});
		textField_4.setBounds(124, 190, 96, 19);
		add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("End Session");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.setCashier(cashier);
				store.addSession(session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(266, 246, 117, 21);
		add(btnNewButton);
		
		JLabel lblNewLabel_7 = new JLabel(cashier.toString());
		lblNewLabel_7.setBounds(84, 60, 85, 14);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(register.getNumber());
		lblNewLabel_8.setBounds(84, 93, 85, 14);
		add(lblNewLabel_8);
		
	}
}
