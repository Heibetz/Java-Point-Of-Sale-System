package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Cashier;
import PD.Register;
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
 * registerListPanel
 * This UI allows a register to be added, updated or deleted
 */
public class registerListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton btnNewButton = new JButton();
	JButton btnNewButton_2 = new JButton();
	
	/**
	 * 
	 * @param currentFrame
	 * @param store
	 */
	public registerListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Register");
		lblNewLabel.setBounds(152, 38, 99, 13);
		add(lblNewLabel);
		
		DefaultListModel<Register> listModel = new DefaultListModel<Register>();
		for(Register r : store.getRegisters().values()) {
			listModel.addElement(r);
		}
		JList<Register> list = new JList<Register>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedValue() == null) {
					btnNewButton.setEnabled(false);
				}
				else {
					btnNewButton.setEnabled(true);
				}
				if(list.getSelectedValue().canDelete()) {
					btnNewButton_2.setEnabled(true);
				}
				else
				{
					btnNewButton_2.setEnabled(false);
				}
			}
		});
		list.setBounds(113, 61, 138, 123);
		add(list);
		
		btnNewButton.setText("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = (Register) list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new registerEditPanel(store, currentFrame, register, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(53, 194, 85, 21);
		add(btnNewButton);
		btnNewButton.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new registerEditPanel(store, currentFrame, new Register(), true));
				currentFrame.getContentPane().revalidate();
				
			}
		});
		btnNewButton_1.setBounds(152, 194, 85, 21);
		add(btnNewButton_1);
		
		btnNewButton_2.setText("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = (Register) list.getSelectedValue();
				store.removeRegister(register);
				registerListPanel registerPanel = new registerListPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerPanel);
				currentFrame.getContentPane().revalidate();
				
			}
		});
		btnNewButton_2.setBounds(247, 194, 85, 21);
		add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		
	}
}
