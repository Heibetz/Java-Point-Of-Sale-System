package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import com.github.lgooddatepicker.components.DatePicker;

import PD.Cashier;
import PD.Payment;
import PD.Sale;
import PD.Session;
import PD.Store;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.awt.Label;
import java.awt.event.ActionEvent;

/**
 * @Author Hank Heiselbetz
 * POSsaleReports
 * This UI allows the user to view sale reports for the day
 */
public class POSsaleReports extends JPanel {

	private static final long serialVersionUID = 1L;
	private BigDecimal getCash = new BigDecimal(0);
	private BigDecimal getCredit = new BigDecimal(0);
	private BigDecimal getCheck = new BigDecimal(0);
	private BigDecimal getTotal = new BigDecimal(0);

	/**
	 * 
	 * @param currentFrame
	 * @param store
	 */
	public POSsaleReports(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sales Report");
		lblNewLabel.setBounds(170, 29, 98, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date:");
		lblNewLabel_1.setBounds(98, 71, 70, 13);
		add(lblNewLabel_1);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(162, 69, 172, 19);
		add(datePicker);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(28, 94, 385, 135);
		add(textArea);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("Sales Report for:" + datePicker.getDate().toString());
				textArea.append("\n\nTime     Cash     Check     Credit     Total");
				for(Cashier cashier : store.getCashiers().values()) {
					
					Session session = new Session();
					session = store.getSessionForDateAndCashier(datePicker.getDate(), cashier);
					if(session.getSales().isEmpty()) {
						break;
					}
					else {
					textArea.append("\n");
					for(Sale s : session.getSales()) {
						for(Payment p : s.getPayments()) {
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
							textArea.append(session.getStartDateAndTime().toLocalTime().format(dtf).toString());
							textArea.append("     " + p.getCashTotal().toString());
							textArea.append("           " + p.getCheckTotal().toString());
							textArea.append("           " + p.getCreditTotal().toString());
							textArea.append("           " + s.calcTotal().toString());
							textArea.append("\n");
							getCredit = getCredit.add(p.getCreditTotal());
							getCash = getCash.add(p.getCashTotal());
							getCheck = getCheck.add(p.getCreditTotal());
						}
						getTotal = getTotal.add(s.calcTotal());
					}
					}
				}
				textArea.append("\n\nTotal:");
				textArea.append("        " + getCash.toString());
				textArea.append("             " + getCheck.toString());
				textArea.append("             " + getCredit.toString());
				textArea.append("             " + getTotal.toString());

			}
		});
		btnNewButton.setBounds(83, 244, 117, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(228, 244, 106, 21);
		add(btnNewButton_1);

	}
}
