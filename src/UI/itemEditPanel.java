package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import PD.Item;
import PD.Price;
import PD.Store;
import PD.TaxCategory;
import PD.UPC;

import java.time.LocalDateTime;

import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Scrollbar;
import java.awt.List;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JComboBox;

/**
 * @Author Hank Heiselbetz
 * itemEditPanel
 * This UI allows items to be edited
 */
public class itemEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	JButton btnNewButton_2 = new JButton();
	JButton btnNewButton_4 = new JButton();
	JButton btnNewButton_5 = new JButton();
	JButton btnNewButton_7 = new JButton();
	Item item1 = new Item();

	/**
	 * @param currentFrame
	 * @param store
	 * @param item
	 * @param isAdd
	 */
	public itemEditPanel(JFrame currentFrame, Store store, Item item, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit item");
		lblNewLabel.setBounds(147, 35, 61, 18);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item number: ");
		lblNewLabel_1.setBounds(22, 85, 89, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description: ");
		lblNewLabel_2.setBounds(22, 108, 89, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tax Category: ");
		lblNewLabel_3.setBounds(22, 135, 89, 13);
		add(lblNewLabel_3);
		
		DefaultComboBoxModel<TaxCategory> listModel2 = new DefaultComboBoxModel<TaxCategory>();
		for(TaxCategory i : store.getTaxCategories().values())
		{
			listModel2.addElement(i);
		}
		JComboBox<TaxCategory> comboBox = new JComboBox<TaxCategory>(listModel2);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setBounds(107, 131, 86, 21);
		add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(117, 220, 85, 21);
		add(btnNewButton_1);
		
		
		DefaultListModel<UPC> listModel = new DefaultListModel<UPC>();
		for(UPC i : item.getUpc().values())
		{
			listModel.addElement(i);
		}	
		JList<UPC> list = new JList<UPC>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedValue() == null) {
					btnNewButton_2.setEnabled(false);
				}
				else {
					btnNewButton_2.setEnabled(true);
				}
				if(list.getSelectedValue() == null) {
					btnNewButton_4.setEnabled(false);
				}
				else
				{
					btnNewButton_4.setEnabled(true);
				}
			}
		});
		list.setBounds(258, 37, 112, 78);
		add(list);
		
		DefaultListModel<Price> listModel1 = new DefaultListModel<Price>();
		for(Price i : item.getPrices())
		{
			listModel1.addElement(i);
		}	
		JList<Price> list1 = new JList<Price>(listModel1);
		list1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list1.getSelectedValue() == null) {
					btnNewButton_5.setEnabled(false);
				}
				else {
					btnNewButton_5.setEnabled(true);
				}
				if(list1.getSelectedValue() == null) {
					btnNewButton_7.setEnabled(false);
				}
				else
				{
					btnNewButton_7.setEnabled(true);
				}
			}
		});
		list1.setBounds(258, 158, 112, 57);
		add(list1);
		
		btnNewButton_2.setText("Edit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UPC upc = (UPC) list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpcEditPanel(currentFrame, store, upc, item, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(211, 127, 61, 21);
		add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		
		JButton btnNewButton_3 = new JButton("Add");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpcEditPanel(currentFrame, store, new UPC(), item, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(275, 127, 61, 21);
		add(btnNewButton_3);
		
		btnNewButton_4.setText("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.removeUPC(list.getSelectedValue());
				itemListPanel itemList = new itemListPanel(store, currentFrame);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemList);
				currentFrame.revalidate();
			}
		});
		btnNewButton_4.setBounds(336, 127, 71, 21);
		add(btnNewButton_4);
		btnNewButton_4.setEnabled(false);
		
		btnNewButton_5.setText("Edit");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Price price = (Price) list1.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new priceEditPanel(currentFrame, store, price, item, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_5.setBounds(212, 220, 61, 21);
		add(btnNewButton_5);
		btnNewButton_5.setEnabled(false);
		
		JButton btnNewButton_6 = new JButton("Add");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new priceEditPanel(currentFrame, store, new Price(), item, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_6.setBounds(275, 220, 61, 21);
		add(btnNewButton_6);
		
		btnNewButton_7.setText("Delete");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.removePrice(list1.getSelectedValue());
				itemListPanel itemList = new itemListPanel(store, currentFrame);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemList);
				currentFrame.revalidate();
			}
		});
		btnNewButton_7.setBounds(336, 220, 71, 21);
		add(btnNewButton_7);
		btnNewButton_7.setEnabled(false);
		
		textField = new JTextField(item.getNumber());
		textField.setBounds(97, 82, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(item.getDescription());
		textField_1.setBounds(97, 108, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isAdd) {store.removeItem(item);};
				item1.setDescription(textField_1.getText());
				item1.setNumber(textField.getText());
				item1.setTaxCategory((TaxCategory) comboBox.getSelectedItem());
				for(Price p : item.getPrices()) {
					item1.addPrice(p);
				}
				for(UPC upc : item.getUpc().values()) {
					item1.addUPC(upc);
				}
				store.addItem(item1);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new itemListPanel(store, currentFrame));
				currentFrame.revalidate();
				
			}
		});
		btnNewButton.setBounds(22, 220, 85, 21);
		add(btnNewButton);
		
	}
}
