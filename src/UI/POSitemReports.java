package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Cashier;
import PD.Item;
import PD.Session;
import PD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POSitemReports
 * This UI allows the user to look at item reports for the past days
 */
public class POSitemReports extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 */
	public POSitemReports(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(38, 101, 364, 125);
		add(textArea);
		
		JLabel lblNewLabel = new JLabel("Item Report");
		lblNewLabel.setBounds(152, 31, 114, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date:");
		lblNewLabel_1.setBounds(85, 67, 78, 13);
		add(lblNewLabel_1);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(140, 65, 164, 19);
		add(datePicker);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("Item report for: \n");
				for(Item item : store.getItems().values()) {
					textArea.append(item.getNumber() + " " + item.getDescription());
					textArea.append(" ");
					textArea.append(Integer.toString(item.getTimesSold()) + "\n");
				};
			}
		});
		btnNewButton.setBounds(85, 242, 114, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(229, 242, 107, 21);
		add(btnNewButton_1);
		
	}
}
