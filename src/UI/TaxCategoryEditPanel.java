package UI;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Cashier;
import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * @Author Hank Heiselbetz
 * TaxCategoryEditPanel
 * This UI allows the user to edit the taxCategory 
 */
public class TaxCategoryEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	JButton btnNewButton_1 = new JButton();
	JButton btnNewButton_2 = new JButton();
	
	/**
	 *
	 * @param store
	 * @param currentFrame
	 * @param taxCategory
	 * @param isAdd
	 */
	public TaxCategoryEditPanel(Store store, JFrame currentFrame, TaxCategory taxCategory, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Category");
		lblNewLabel.setBounds(45, 93, 65, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Edit Tax Category");
		lblNewLabel_1.setBounds(112, 34, 103, 22);
		add(lblNewLabel_1);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setBounds(98, 90, 96, 19);
		add(textField);
		textField.setColumns(10);
	
	
		DefaultListModel<TaxRate> listModel = new DefaultListModel<TaxRate>();
		
		for(TaxRate tr : taxCategory.getTaxRates()) {
			listModel.addElement(tr);
		}
		JList<TaxRate> list = new JList<TaxRate>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedValue() == null) {
					btnNewButton_2.setEnabled(false);
				}
				else {
					btnNewButton_2.setEnabled(true);
				}
				if(list.getSelectedValue() == null) {
					btnNewButton_1.setEnabled(false);
				}
				else
				{
					btnNewButton_1.setEnabled(true);
				}
			}
		});
		list.setBounds(260, 54, 125, 110);
		add(list);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, store, taxCategory, true, new TaxRate()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(217, 174, 58, 21);
		add(btnNewButton);
		
		btnNewButton_1.setText("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxRate tr = (TaxRate) list.getSelectedValue();
				taxCategory.addTaxRate(tr);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, store, taxCategory, false, tr));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(287, 174, 58, 21);
		add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		
		btnNewButton_2.setText("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.removeTaxRate(list.getSelectedValue());
				TaxCategoryListPanel taxList = new TaxCategoryListPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(taxList);
				currentFrame.revalidate();
			}
		});
		btnNewButton_2.setBounds(355, 174, 65, 21);
		add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		
		JButton btnNewButton_3 = new JButton("Save");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isAdd) {store.removeTaxCategory(taxCategory);};
				TaxCategory tx = new TaxCategory();
				tx.setCategory(textField.getText());
				
				
				for(TaxRate tr : taxCategory.getTaxRates()) 
				{ 
					tx.addTaxRate(tr); 
				}
				
				store.addTaxCategory(tx);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(77, 219, 85, 21);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Cancel");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_4.setBounds(187, 219, 85, 21);
		add(btnNewButton_4);
		
	}
}
