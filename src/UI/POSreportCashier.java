package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Cashier;
import PD.Session;
import PD.Store;
import PD.UPC;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.CalendarPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POSreportCashier
 * This UI allows the user to look at cashier reports for the day
 */
public class POSreportCashier extends JPanel {

	private static final long serialVersionUID = 1L;
	private int totalSales = 0;
	private BigDecimal totalAmount = new BigDecimal(0);
	private BigDecimal totalDiff = new BigDecimal(0);

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 */
	public POSreportCashier(JFrame currentFrame, Store store) {
		setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(48, 99, 347, 126);
		add(textArea);

		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(147, 65, 170, 19);
		add(datePicker);

		JLabel lblNewLabel = new JLabel("Cashier Report");
		lblNewLabel.setBounds(168, 22, 127, 35);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Date:");
		lblNewLabel_1.setBounds(80, 67, 70, 13);
		add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("Cashier report for: " + datePicker.getDate().toString() + "\n\n");
				textArea.append("Number     Name     Count     Amount    Diff\n");
				for (Cashier cashier : store.getCashiers().values()) {
					Session session = new Session();
					session = store.getSessionForDateAndCashier(datePicker.getDate(), cashier);
					textArea.append(cashier.getNumber());
					textArea.append("                " + cashier.getName());
					textArea.append("          " + session.getTotalSales() + "                ");
					textArea.append(session.calcTotal().toString() + "             ");
					textArea.append(session.getDiff().toString());
					textArea.append("\n");
					totalSales += session.getTotalSales();
					totalAmount = totalAmount.add(session.calcTotal());
					totalDiff = totalDiff.add(session.getDiff());
				}
				textArea.append("Total : ");
				textArea.append("                       ");
				textArea.append(Integer.toString(totalSales));
				textArea.append("               " + totalAmount.toString());
				textArea.append("               " + totalDiff.toString());				
				
			}
		});
		btnNewButton.setBounds(80, 254, 108, 21);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(220, 254, 108, 21);
		add(btnNewButton_1);

	}
}
