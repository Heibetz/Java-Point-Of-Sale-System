package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Register;
import PD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POSregisterPanel
 * This UI allows maintenance of a register
 */
public class registerEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * 
	 * @param store
	 * @param currentFrame
	 * @param register
	 * @param isAdd
	 */
	public registerEditPanel(Store store, JFrame currentFrame, Register register, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Register");
		lblNewLabel.setBounds(145, 41, 87, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Register Number:");
		lblNewLabel_1.setBounds(29, 85, 120, 13);
		add(lblNewLabel_1);
		
		textField = new JTextField(register.getNumber());
		textField.setBounds(151, 82, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.setNumber(textField.getText());
				
				if(isAdd) {store.addRegister(register);};
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new registerListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(64, 195, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new registerListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(180, 195, 85, 21);
		add(btnNewButton_1);
		
	}

}
