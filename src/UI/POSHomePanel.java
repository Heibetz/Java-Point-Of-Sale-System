package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import PD.Store;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POSHomePanel
 * Home Panel that presents store name and allows user to make changes to the system
 */
public class POSHomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 */
	public POSHomePanel(JFrame currentFrame, Store store)
	{
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(store.getName());
		lblNewLabel.setBounds(118, 129, 227, 16);
		add(lblNewLabel);
	}

}