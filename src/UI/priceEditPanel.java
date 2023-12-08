package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Item;
import PD.Price;
import PD.PromoPrice;
import PD.Store;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import java.time.LocalDate;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * @Author Hank Heiselbetz
 * priceEditPanel
 * This UI allows for maintenance of the price of an item
 */
public class priceEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private Boolean isChecked = false;
	JLabel lblNewLabel_4 = new JLabel("End Date:");
	private JTextField textField_3;
	
	/**
	 * 
	 * @param currentFrame
	 * @param store
	 * @param price
	 * @param item
	 * @param isAdd
	 */
	public priceEditPanel(JFrame currentFrame, Store store, Price price, Item item, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Price");
		lblNewLabel.setBounds(162, 27, 82, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price:");
		lblNewLabel_1.setBounds(45, 103, 67, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Effective Date:");
		lblNewLabel_2.setBounds(45, 136, 96, 13);
		add(lblNewLabel_2);
		
		lblNewLabel_4.setText("End Date:");
		lblNewLabel_4.setBounds(45, 167, 67, 13);
		add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Promo Price");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(chckbxNewCheckBox.isSelected()) {

					isChecked = true;
					lblNewLabel_4.setVisible(true);
					
					textField_3 = new JTextField();
					textField_3.setBounds(162, 164, 96, 19);
					add(textField_3);
					textField_3.setColumns(10);
					textField_3.setEnabled(true);
					textField_3.setVisible(true);
				}
				else
				{
					isChecked = false;
					lblNewLabel_4.setText("End Date:");
					lblNewLabel_4.setBounds(45, 167, 67, 13);
					add(lblNewLabel_4);
					lblNewLabel_4.setVisible(false);
					
					textField_3 = new JTextField();
					textField_3.setBounds(162, 164, 96, 19);
					add(textField_3);
					textField_3.setColumns(10);
					textField_3.setEnabled(false);
					textField_3.setVisible(false);
				}
				
			}
		});
		chckbxNewCheckBox.setBounds(131, 61, 93, 21);
		add(chckbxNewCheckBox);
		
		textField = new JTextField(price.getPrice().toString());
		textField.setBounds(162, 100, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(price.getEffectiveDate().toString());
		textField_1.setBounds(162, 133, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isAdd) {item.removePrice(price);};
				if(isChecked) {
					PromoPrice pp = new PromoPrice(BigDecimal.valueOf(Double.valueOf(textField.getText())),
							LocalDate.parse(textField_1.getText()), LocalDate.parse(textField_3.getText()));
					item.addPrice(pp);
					
				}
				else 
				{
					Price price1 = new Price();
					price1.setPrice(BigDecimal.valueOf(Double.valueOf(textField.getText())));
					price1.setEffectiveDate(LocalDate.parse(textField_1.getText()));
					item.addPrice(price1);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new itemEditPanel(currentFrame, store, item, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(75, 212, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new itemEditPanel(currentFrame, store, item, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(178, 212, 85, 21);
		add(btnNewButton_1);
	
	}
}
