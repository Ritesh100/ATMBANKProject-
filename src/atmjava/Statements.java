package atmjava;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Statements {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	static void statementPanel(String atmNumber, JDBCConnection jdbc) {
		try {
			JFrame frame = new JFrame();
			int count = jdbc.getDataInt("SELECT COUNT(*) FROM transactions WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
			JPanel pane = new JPanel();
			pane.setLayout(null);
					
			JLabel empty;
			if (count == 0) {
				empty = new JLabel("No Transaction History Available", SwingConstants.CENTER);
				empty.setForeground(Color.red);
				empty.setBounds(150,200,300,40);
				pane.add(empty);
			} else {
				
				JButton downloadPdf = new JButton("Download PDF");
				JButton exportExcel = new JButton("Export Excel");
				JButton exit = new JButton("Exit");
				
				exportExcel.setBackground(Color.green);
				exportExcel.setForeground(Color.white);
				exportExcel.setBounds(40, 0, 200, 30);
				int u_id = jdbc.getDataInt("SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"'");
				downloadPdf.setForeground(Color.white);
				downloadPdf.setBackground(Color.green);
				downloadPdf.setBounds(360, 0, 200, 30);
				
				exit.setForeground(Color.white);
				exit.setBackground(Color.red);
				exit.setBounds(460, 420, 100, 30);
								
				String data[][] = jdbc.dataSet(count, u_id);
				String columns[] = { "Transaction ID", "Amount", "Date Performed", "Remarks"};
				
				DefaultTableModel model = new DefaultTableModel(data, columns);
				JTable table = new JTable(model);
				table.setShowGrid(true);
				table.setShowVerticalLines(true);

				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setBounds(0,50,600,350);
				
				exportExcel.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String name = jdbc.getData("SELECT first_name FROM user WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
						boolean status = ExcelFileMaker.ExcelMaker(data, count, name);
						if(status) {
							JOptionPane.showMessageDialog(frame,
								    "Successfully exported document to\nC:\\ATMProject",
								    "Export Successful",
								    JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(frame,
								    "Error Occured while exporting to excel",
								    "Error",
								    JOptionPane.WARNING_MESSAGE);
						}
					}});
				
				downloadPdf.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = jdbc.getData("SELECT first_name FROM user WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
						boolean status = PDFFileMaker.PDFGenerate(data, count, name);
						if(status) {
							JOptionPane.showMessageDialog(frame,
								    "Successfully exported document to\nC:\\ATMProject",
								    "Export Successful",
								    JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(frame,
								    "Error Occured while exporting to pdf",
								    "Error",
								    JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				
				pane.add(downloadPdf);
				pane.add(exportExcel);
				pane.add(exit);
				pane.add(scrollPane);
			}
			
			frame.getContentPane().add(pane);
			frame.setSize(635, 488);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		} catch (Exception ee) {
			
		}
	}
}
