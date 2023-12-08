package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * @Author Hank Heiselbetz
 * TaxCategoryListPanel
 * This UI allows the user to add, edit or delete a tax category
 */
public class TaxCategoryListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton btnNewButton_1 = new JButton();
	JButton btnNewButton_2 = new JButton();

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 */
	public TaxCategoryListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		DefaultListModel<TaxCategory> listModel = new DefaultListModel<TaxCategory>();
		
		for(TaxCategory taxCategory : store.getTaxCategories().values()) {
			listModel.addElement(taxCategory); 
		}
		JList<TaxCategory> list = new JList<TaxCategory>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedValue() == null) {
					btnNewButton_1.setEnabled(false);
				}
				else {
					btnNewButton_1.setEnabled(true);
				}
				if(list.getSelectedValue() == null) {
					btnNewButton_2.setEnabled(false);
				}
				else
				{
					btnNewButton_2.setEnabled(true);
				}
			}
		});
		list.setBounds(97, 58, 206, 124);
		add(list);
		 
		
		JLabel lblNewLabel = new JLabel("Tax Category List");
		lblNewLabel.setBounds(139, 10, 164, 58);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(store, currentFrame, new TaxCategory(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(161, 204, 103, 21);
		add(btnNewButton);
		
		btnNewButton_1.setText("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategory tr = (TaxCategory) list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(store, currentFrame, tr, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(31, 204, 108, 21);
		add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		
		btnNewButton_2.setText("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategory tr = (TaxCategory) list.getSelectedValue();
				store.removeTaxCategory(tr);
				TaxCategoryListPanel taxCategoryListPanel = new TaxCategoryListPanel(currentFrame,store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(taxCategoryListPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(274, 204, 97, 21);
		add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
	}
}
