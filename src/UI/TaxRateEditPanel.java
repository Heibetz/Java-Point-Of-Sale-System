package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * TaxRateEditPanel
 * This UI allows the user to edit a tax rate of a Tax Category
 */
public class TaxRateEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	TaxRate taxRate = new TaxRate();

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 * @param taxCategory
	 * @param isAdd
	 * @param tr
	 */
	public TaxRateEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd, TaxRate tr) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Tax Rates");
		lblNewLabel.setBounds(189, 23, 103, 13);
		add(lblNewLabel);
		
		textField = new JTextField(tr.getTaxRate().toString());
		textField.setBounds(127, 83, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Rate:");
		lblNewLabel_1.setBounds(44, 86, 45, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Effective Date:");
		lblNewLabel_2.setBounds(44, 132, 73, 13);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField(tr.getEffectiveDate().toString());
		textField_1.setBounds(127, 129, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isAdd) {taxCategory.removeTaxRate(tr);};
				taxRate.setTaxRate(BigDecimal.valueOf(Double.valueOf(textField.getText())));					 
				taxRate.setEffectiveDate(LocalDate.parse(textField_1.getText()));
				taxCategory.addTaxRate(taxRate);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(store, currentFrame, taxCategory, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(83, 222, 111, 19);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(store, currentFrame, taxCategory, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(224, 220, 85, 22);
		add(btnNewButton_1);
		
	}
}
