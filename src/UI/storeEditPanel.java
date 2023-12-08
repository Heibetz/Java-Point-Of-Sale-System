package UI;

import javax.swing.JPanel;

import PD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * storeEditPanel
 * This UI allows the user to edit the name of the store
 */
public class storeEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * 
	 * @param store
	 * @param currentFrame
	 */
	public storeEditPanel(Store store, JFrame currentFrame) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Store");
		lblNewLabel.setBounds(139, 63, 85, 27);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(69, 112, 52, 33);
		add(lblNewLabel_1);
		
		textField = new JTextField(store.getName());
		textField.setBounds(131, 119, 111, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setName(textField.getText());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(80, 202, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(213, 202, 85, 21);
		add(btnNewButton_1);
		
		
	}
}
