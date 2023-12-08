package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.CashDrawer;
import PD.Cashier;
import PD.Item;
import PD.Store;
import PD.Register;
import PD.Sale;
import PD.SaleLineItem;
import PD.Session;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POScashScreen
 * This UI allows the cashier to scan or enter a UPC, and input the quantity
 * The cashier can choose to end session, report no taxes for sale, add payment, 
 * view the items scanned, cancel sale, and complete sale
 */
public class POSsaleScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	JButton btnNewButton_3 = new JButton();
	JButton btnNewButton_2 = new JButton();
	Item item = new Item();
	JLabel lblNewLabel_12 = new JLabel();
	JLabel lblNewLabel_13 = new JLabel();

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
	public POSsaleScreen(JFrame currentFrame, Store store, CashDrawer cashDrawer, Cashier cashier, Register register, Sale sale, Session session) {
		setLayout(null);
		
		lblNewLabel_12 = new JLabel("Enter valid UPC");
		lblNewLabel_12.setBounds(39, 69, 96, 13);
		add(lblNewLabel_12);
		lblNewLabel_12.setVisible(false);
		
		lblNewLabel_13 = new JLabel("Enter valid quantity");
		lblNewLabel_13.setBounds(171, 69, 93, 13);
		add(lblNewLabel_13);
		lblNewLabel_13.setVisible(false);
		
		DefaultListModel<SaleLineItem> listModel = new DefaultListModel<SaleLineItem>();
		for(SaleLineItem i : sale.getSaleLineItems()) {
			listModel.addElement(i);
		}
		JList<SaleLineItem> list = new JList<SaleLineItem>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
			}
		});
		list.setBounds(20, 105, 207, 122);
		add(list);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Tax Free");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
		});
		chckbxNewCheckBox.setBounds(315, 46, 93, 21);
		add(chckbxNewCheckBox);
		
		JLabel lblNewLabel = new JLabel("Cashier: ");
		lblNewLabel.setBounds(10, 23, 70, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(cashier.toString());
		lblNewLabel_1.setBounds(73, 23, 81, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Register:");
		lblNewLabel_2.setBounds(10, 46, 70, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(register.getNumber());
		lblNewLabel_3.setBounds(73, 46, 81, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sale");
		lblNewLabel_4.setBounds(185, 33, 45, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Item:");
		lblNewLabel_5.setBounds(10, 82, 59, 13);
		add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(199, 79, 37, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(store.findItemForUPC(textField.getText()) != null){
					SaleLineItem sli = new SaleLineItem();
					item = new Item();
					item = (store.findItemForUPC(textField.getText()));
					
					sli.setItem(item);
					if(textField_1.getText() != "" && Integer.parseInt(textField_1.getText()) > 0) {
						sli.setQuantity(Integer.parseInt(textField_1.getText()));
						
						item.addTimesSold(Integer.parseInt(textField_1.getText()));
						
						sale.addSaleLineItem(sli);
						
						if(chckbxNewCheckBox.isSelected()) {
							sale.setTaxFree(true);
						}
						else sale.setTaxFree(false);
						
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(new POSsaleScreen(currentFrame, store, cashDrawer, cashier, register, sale, session));
						currentFrame.getContentPane().revalidate();
					}
					else
						lblNewLabel_13.setVisible(true);
				}
				else
					lblNewLabel_12.setVisible(true);
				
			}
		});
		textField.setBounds(39, 79, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Quantity:");
		lblNewLabel_6.setBounds(145, 82, 59, 13);
		add(lblNewLabel_6);
	
		JLabel lblNewLabel_7 = new JLabel("SubTotal:");
		lblNewLabel_7.setBounds(247, 106, 70, 13);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Tax:");
		lblNewLabel_8.setBounds(247, 135, 70, 13);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Total:");
		lblNewLabel_9.setBounds(247, 169, 70, 13);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Amt tendered:");
		lblNewLabel_10.setBounds(233, 237, 81, 13);
		add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Change:");
		lblNewLabel_11.setBounds(247, 272, 59, 13);
		add(lblNewLabel_11);
		
		textField_2 = new JTextField(sale.calcSubTotal().toString());
		textField_2.setBounds(312, 105, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(sale.calcTax().setScale(2, RoundingMode.HALF_UP).toString());
		textField_3.setBounds(312, 132, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField(sale.calcTotal().toString());
		textField_4.setBounds(312, 166, 96, 19);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField(sale.calcAmtTendered().toString());
		textField_5.setBounds(312, 237, 96, 19);
		add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField(sale.calcChange().toString());
		textField_6.setBounds(312, 269, 96, 19);
		add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("Payment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSpaymentScreen(currentFrame, store, cashDrawer, cashier, register, sale, session, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(0, 237, 106, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel Sale");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSsaleScreen(currentFrame, store, cashDrawer, cashier, register, new Sale(), session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(0, 268, 106, 21);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Complete Sale");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.addSales(sale);
				session.addSale(1);
				cashDrawer.removeCash(sale.calcChange());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSsaleScreen(currentFrame, store, cashDrawer, cashier, register, new Sale(), session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(116, 237, 111, 21);
		add(btnNewButton_2);
		
		if(sale.isPaymentEnough()) {
			btnNewButton_2.setEnabled(true);
		}
		else btnNewButton_2.setEnabled(false);
		
		btnNewButton_3 = new JButton("End Session");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.setEndDateTIme(LocalDateTime.now());
				store.addSession(session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSendSession(currentFrame, store, cashDrawer, cashier, register, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(116, 268, 111, 21);
		add(btnNewButton_3);
		
	}
}
