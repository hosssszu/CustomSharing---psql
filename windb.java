package db;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.Font;

public class windb {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static DefaultListModel<String> listM = new DefaultListModel<>();
	private JPasswordField passwordField;
	private JTextField textField_4;
	
	   static Connection c = null;
	   ResultSet rs = null;

	public static void main(String[] args) throws Exception {
		

		  try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:port/<dbname>",
		            "user", "password");
		         c.setAutoCommit(true);
		         JOptionPane.showMessageDialog(null, "Connected successfully!");
		      } catch (Exception e) {
		         e.printStackTrace();
		         System.err.println(e.getClass().getName()+": "+e.getMessage());
		         JOptionPane.showMessageDialog(null, "Connection failed!");
		         System.exit(0);
		      }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windb window = new windb();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public windb() {
		initialize();
		
	}

	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 450);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("CustomShareing - psql");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic(KeyEvent.VK_X);
        mntmExit.setToolTipText("Exit application");
		mntmExit.addActionListener((event) -> System.exit(0));
		mnFile.add(mntmExit);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		   menuBar.add(Box.createHorizontalGlue());
		   JMenu menuhelp = new JMenu("Help");
		   menuhelp.setMnemonic(KeyEvent.VK_H);
		   menuBar.add(menuhelp);
		   
		   JMenuItem about = new JMenuItem("About");
		   about.setMnemonic(KeyEvent.VK_A);
		   about.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		JOptionPane.showMessageDialog(null, String.format("Created by Szegedi Loránd!\nVersion: 1.00",e.getActionCommand()));
		   	}
		   });
		   menuhelp.add(about);
		   
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 826, 375);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Insert data", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField(); //customer
		textField.setBounds(12, 39, 109, 19);
		textField.setColumns(10);
		panel.add(textField);
		
		textField_1 = new JTextField(); //product
		textField_1.setColumns(10);
		textField_1.setBounds(133, 39, 167, 19);
		panel.add(textField_1);
		
		textField_2 = new JTextField(); //amount
		textField_2.setColumns(10);
		textField_2.setBounds(312, 39, 56, 19);
		panel.add(textField_2);
		
		textField_3 = new JTextField(); //dt
		textField_3.setColumns(10);
		textField_3.setBounds(394, 39, 107, 19);
		panel.add(textField_3);
		
		textField_4 = new JTextField(); //et
		textField_4.setToolTipText("[in hours]");
		textField_4.setBounds(632, 39, 69, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
		String date = spf.format(new Date());
	
		JLabel lblFirma = new JLabel("Customer");
		lblFirma.setBounds(45, 12, 66, 15);
		panel.add(lblFirma);
		
		JLabel lblProdus = new JLabel("Product");
		lblProdus.setBounds(194, 12, 66, 15);
		panel.add(lblProdus);
		
		JLabel lblCantitate = new JLabel("Amount");
		lblCantitate.setBounds(317, 12, 47, 15);
		panel.add(lblCantitate);
		
		JLabel lblTermenDeLivrare = new JLabel("Delivery time");
		lblTermenDeLivrare.setBounds(412, 12, 75, 15);
		panel.add(lblTermenDeLivrare);
		
		JLabel lblResponsabil = new JLabel("Responsible");
		lblResponsabil.setBounds(527, 12, 78, 15);
		panel.add(lblResponsabil);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 70, 797, 230);
		panel.add(scrollPane);
		
		String[] filename= {"A","B","C","D","E"};
		JComboBox comboBox = new JComboBox(filename); //responsible
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(514, 38, 94, 19);
		panel.add(comboBox);
		
		String[] filename2 = {"Machine 1","Machine 2","Machine 3","Machine 4"};
		JComboBox comboBox_1 = new JComboBox(filename2); //machine
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(727, 38, 82, 19);
		panel.add(comboBox_1);
		
		JList list = new JList(listM);
		list.setFont(new Font("Times New Roman", Font.BOLD, 12));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		list.setPrototypeCellValue(list);
		
		JButton btnAdaugare = new JButton("Add");
		btnAdaugare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
                    Statement stat = c.createStatement();
                    String sql = "INSERT INTO <TABLE NAME>" +
                    "(Date, Customer, Product, Amount, Delivery time," +
                    " Responsible, Estimated Time, Machine) VALUES('"+date+"', '"+
                    textField.getText()+"',"+textField_1.getText()+",'"+textField_2.getText()+
                    "','"+textField_3.getText()+"','"+comboBox.getSelectedItem()+"','"+textField_4.getText()+"','"
                    +comboBox_1.getSelectedItem()+"')";
                    
                    stat.executeUpdate(sql);

                    listM.addElement("");
                 }
                 catch(Exception ex) {
                     ex.printStackTrace();
                     System.err.println(ex.getMessage());
                 }
             } 
		});
		btnAdaugare.setBounds(575, 311, 114, 25);
		panel.add(btnAdaugare);
		
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultListModel listM = new DefaultListModel();
				try {
					String sqlQuery = "select * from <table name>; " ;
					Statement stat = c.createStatement();
					rs = stat.executeQuery(sqlQuery);

					while(rs.next()) {
						
						String categorynameDba = rs.getString("ID");
						String categorynameDb0 = rs.getString("Date");
						String categorynameDb = rs.getString("Customer");
						String categorynameDb2 = rs.getString("Product");
						String categorynameDb3 = rs.getString("Amount");
						String categorynameDb4 = rs.getString("Delivery Time");
						String categorynameDb5 = rs.getString("Responsible");
						String categorynameDb6 = rs.getString("Estimated time");
						String categorynameDb7 = rs.getString("Machine");
						
						StringBuilder builder = new StringBuilder();

						builder.append("<html><pre>");
						builder.append(String.format("%s \t %s \t %s \t %s \t %s \t %s \t %s \t %s \t %s", categorynameDba, categorynameDb0, categorynameDb, categorynameDb2, categorynameDb3, categorynameDb4, categorynameDb5, categorynameDb6, categorynameDb7));
						builder.append("</pre></html>");
						
						listM.addElement(builder.toString()); 

					}
					
					list.setModel(listM);

				} catch (Exception ex) {

				}

			}
		});
		btnNewButton_1.setBounds(454, 311, 114, 25);
		panel.add(btnNewButton_1);
		
		String password = "pass";
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter password");
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPasswordField input = (JPasswordField) e.getSource();
				char[] passy = input.getPassword();
				String p = new String(passy);
				
				if (p.equals(password)) {

					try {
	                    Statement stat = c.createStatement();
					String sql = "DELETE FROM <TABLE NAME> WHERE ID = '"+textField.getText()+"'";
					   stat.executeUpdate(sql);
	                }
	                catch(Exception ex) {
	                    ex.printStackTrace();
	                    System.err.println(ex.getMessage());
	                }
					JOptionPane.showMessageDialog(null, "Deleted successfully!");
				}else
					JOptionPane.showMessageDialog(null, "Reserved to moderators!");
			}
		});
		passwordField.setBounds(12, 312, 101, 24);
		panel.add(passwordField);
		
		JButton btnIesire = new JButton("Exit");
		btnIesire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnIesire.setBounds(695, 311, 114, 25);
		panel.add(btnIesire);
		
		JLabel lblTimpEstimatin = new JLabel("Estimated time");
		lblTimpEstimatin.setToolTipText("[in hours]");
		lblTimpEstimatin.setBounds(627, 12, 96, 15);
		panel.add(lblTimpEstimatin);
		
		JLabel lblExecutie = new JLabel("Machine");
		lblExecutie.setBounds(743, 12, 66, 15);
		panel.add(lblExecutie);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(288, 316, 66, 15);
		panel.add(lblNewLabel);
		
	}	
}