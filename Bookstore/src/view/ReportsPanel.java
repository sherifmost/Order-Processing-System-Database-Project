package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportsPanel extends JPanel {

	private static final long serialVersionUID = -7684997863828392008L;
	private JButton salesBtn, customerBtn, booksBtn;
	
	public ReportsPanel() {
		salesBtn = new JButton("Sales report");
		customerBtn = new JButton("Top customers");
		booksBtn = new JButton("Top selling books");
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.ipadx = 50;
		gc.ipady = 50;
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 0;
		add(salesBtn, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		add(customerBtn, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		add(booksBtn, gc);
		
		salesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JasperDesign jdesign = JRXmlLoader.load("src/reports/SalesReport.jrxml");
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jprint = JasperFillManager.fillReport(jreport, null, Controller.getConnection());
					JasperViewer.viewReport(jprint);
				} catch (JRException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		customerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JasperDesign jdesign = JRXmlLoader.load("src/reports/CustomersReport.jrxml");
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jprint = JasperFillManager.fillReport(jreport, null, Controller.getConnection());
					JasperViewer.viewReport(jprint);
				} catch (JRException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		booksBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JasperDesign jdesign = JRXmlLoader.load("src/reports/BooksReport.jrxml");
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jprint = JasperFillManager.fillReport(jreport, null, Controller.getConnection());
					JasperViewer.viewReport(jprint);
				} catch (JRException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
