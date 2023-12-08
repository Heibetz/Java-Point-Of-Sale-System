package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Item;
import PD.Store;
import PD.UPC;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * UpcEditPanel
 * This UI allows the user to edit a UPC for an item
 */
public class UpcEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	UPC upc1 = new UPC();

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 * @param upc
	 * @param item
	 * @param isAdd
	 */
	public UpcEditPanel(JFrame currentFrame, Store store, UPC upc, Item item, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit UPC");
		lblNewLabel.setBounds(135, 47, 81, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UPC Code:");
		lblNewLabel_1.setBounds(68, 94, 81, 13);
		add(lblNewLabel_1);
		
		textField = new JTextField(upc.getUPC());
		textField.setBounds(159, 91, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isAdd) {item.removeUPC(upc);};
				upc1.setUPC(textField.getText());
				item.addUPC(upc1);
				
				if(isAdd) {item.addUPC(upc1);};
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new itemEditPanel(currentFrame, store, item, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(64, 200, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new itemEditPanel(currentFrame, store, item, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(179, 200, 85, 21);
		add(btnNewButton_1);
		
	}
}
