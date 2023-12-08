package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Cashier;
import PD.Item;
import PD.Store;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * @Author Hank Heiselbetz
 * itemListPanel
 * This UI allows an item to be added, or selected and updated, or deleted
 */
public class itemListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton btnNewButton = new JButton();
	JButton btnNewButton_2 = new JButton();

	/**
	 * 
	 * @param store
	 * @param currentFrame
	 */
	public itemListPanel(Store store, JFrame currentFrame) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Item");
		lblNewLabel.setBounds(159, 40, 85, 13);
		add(lblNewLabel);
		
		DefaultListModel<Item> listModel = new DefaultListModel<Item>();
		for(Item i : store.getItems().values())
		{
			listModel.addElement(i);
		}	
		JList<Item> list = new JList<Item>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedValue() == null) {
					btnNewButton.setEnabled(false);
				}
				else {
					btnNewButton.setEnabled(true);
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
		list.setBounds(78, 63, 251, 130);
		add(list);
		
		btnNewButton.setText("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new itemEditPanel(currentFrame, store, item, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(64, 203, 85, 21);
		add(btnNewButton);
		btnNewButton.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new itemEditPanel(currentFrame, store, new Item(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(159, 203, 85, 21);
		add(btnNewButton_1);
		
		btnNewButton_2.setText("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeItem(list.getSelectedValue());
				itemListPanel itemList = new itemListPanel(store, currentFrame);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemList);
				currentFrame.revalidate();
			}
		});
		btnNewButton_2.setBounds(254, 203, 85, 21);
		add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		
	}
}
