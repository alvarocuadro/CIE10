
import java.sql.ResultSet;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;


public class GUI extends javax.swing.JFrame {
	
	private JFrame frmBuscadorDeDiagnsticos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmBuscadorDeDiagnsticos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection cn = null;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JTextField textField;
	/**
	 * Create the application.
	 */
	public GUI() {
		cn =SQL.getConnection();  //conecta a la DB configurada en SQL.java
		frmBuscadorDeDiagnsticos = new JFrame();
		frmBuscadorDeDiagnsticos.getContentPane().setBackground(Color.GRAY);
		frmBuscadorDeDiagnsticos.setBackground(Color.GRAY);
		frmBuscadorDeDiagnsticos.setTitle("Buscador de Diagn\u00F3sticos CIE-10");
		frmBuscadorDeDiagnsticos.setBounds(100, 100, 757, 320);
		frmBuscadorDeDiagnsticos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBuscadorDeDiagnsticos.getContentPane().setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 78, 727, 192);
		frmBuscadorDeDiagnsticos.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				DefaultTableModel dtm = new DefaultTableModel();
				dtm.addColumn("Código");
				dtm.addColumn("Diagnóstico");
				String diagnostico = textField.getText();
				int letras = diagnostico.length();
		        if(letras >=3) { //realiza la acción solo al tener al menos 3 caracteres escritos
				
				Separar separa = new Separar();
				String query = separa.separar(diagnostico); //envía el string escrito por el usuario a al método separar de la clase Separar para traer la query de SQL necesaria.	
				try {
					PreparedStatement pst=cn.prepareStatement(query);
					
					ResultSet rs=pst.executeQuery();
					while (rs.next()) {
						dtm.addRow(new Object[]{
							rs.getString("icd_cod"),
							rs.getString("icd_nom")
						});
					}
										
					table_1.setModel(dtm);
					table_1.setAutoResizeMode(0);
					table_1.getColumnModel().getColumn(0).setPreferredWidth(60);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(1000);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}}
			});
		textField.setBounds(10, 36, 430, 20);
		frmBuscadorDeDiagnsticos.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Diagn\u00F3stico (3 caracteres m\u00EDnimos)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 311, 20);
		frmBuscadorDeDiagnsticos.getContentPane().add(lblNewLabel);
		
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logoSF.png")).getImage();
		logo.setIcon(new ImageIcon(img));
		logo.setBounds(491, 0, 250, 66);
		frmBuscadorDeDiagnsticos.getContentPane().add(logo);
	}
}
