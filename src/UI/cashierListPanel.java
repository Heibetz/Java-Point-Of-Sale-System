package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Cashier;
import PD.Store;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * @Author Hank Heiselbetz
 * cashierListPanel
 * This UI allows a cashier to be added, or selected to update or delete
 */
public class cashierListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton btnNewButton_1 = new JButton();
	JButton btnNewButton_2 = new JButton();

	/**
	 * @param currentFrame
	 * @param store
	 */
	public cashierListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cashier List");
		lblNewLabel.setBounds(171, 32, 95, 29);
		add(lblNewLabel);
		
		DefaultListModel<Cashier> listModel = new DefaultListModel<Cashier>();
		for(Cashier moo : store.getCashiers().values())
		{
			listModel.addElement(moo);
		}
		JList<Cashier> list = new JList<Cashier>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedValue() == null) {
					btnNewButton_1.setEnabled(false);
				}
				else {
					btnNewButton_1.setEnabled(true);
				}
				if(list.getSelectedValue().canDeleteCashier()) {
					btnNewButton_2.setEnabled(true);
				}
				else
				{
					btnNewButton_2.setEnabled(false);
				}
			}
		});
		list.setBounds(105, 71, 205, 90);
		add(list);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new cashierEditPanel(store, currentFrame, true, null));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(61, 196, 85, 21);
		add(btnNewButton);
		
		btnNewButton_1.setText("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier cashier = (Cashier) list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new cashierEditPanel(store, currentFrame, false, cashier));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton_1.setBounds(171, 196, 95, 21);
		add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		
		btnNewButton_2.setText("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeCashier(list.getSelectedValue());
				cashierListPanel cashierList = new cashierListPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cashierList);
				currentFrame.revalidate();
			}
		});
		btnNewButton_2.setBounds(286, 196, 85, 21);
		add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		
		
			
	}
}
