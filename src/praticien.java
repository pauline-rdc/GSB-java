import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JTextArea;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import connexion.ConnexionBDD;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class praticien extends accueil{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private static JTextField nom;
	private static JTextField firstname;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private static JTextField adress;
	private JTextArea textArea_3;
	private static JTextField adress_cp;
	private static JTextField adress_v;
	private static JTextField coeff;
	private JTextArea textArea_4;
	private JTextArea textArea_5;
	private static String lieu;
	private JTextArea textArea_6;
	private JTextField pra_type;
	private JTextField lieuEx;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					praticien frame = new praticien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public praticien() {
		try{
			System.out.println(" | Numéro du praticien pour test détail " + numPraticien +" | ");
			ConnexionBDD conn = new ConnexionBDD();
			Statement stat = (Statement) conn.execBDD().createStatement();
			ResultSet resul = stat.executeQuery("SELECT * FROM praticien where PRA_NUM='"+ numPraticien + "'");
		    ResultSetMetaData resultMe = (ResultSetMetaData) resul.getMetaData();
		    while(resul.next()){
		    	 getNom().setText(resul.getString("PRA_NOM"));
		    	 getFirstname().setText(resul.getString("PRA_PRENOM"));
		    	 getAdress().setText(resul.getString("PRA_ADRESSE"));
		    	 getAdress_cp().setText(resul.getString("PRA_CP"));
		    	 getAdress_v().setText(resul.getString("PRA_VILLE"));
		    	 getCoeff().setText(resul.getString("PRA_COEFNOTORIETE"));
		    	 lieu = resul.getString("TYP_CODE");
		    }
		    ConnexionBDD conn1 = new ConnexionBDD();
	    	 Statement stat2 = (Statement) conn1.execBDD().createStatement();
	    	 ResultSet result = stat2.executeQuery("SELECT * FROM type_praticien where TYP_CODE='"+lieu +"'");
			 ResultSetMetaData resultMe1 = (ResultSetMetaData) result.getMetaData();
			 while(result.next()){
			    	getLieuEx().setText(result.getString("TYP_LIEU"));
			    	getPra_type().setText(result.getString("TYP_LIBELLE"));
			  }
			 
		   resul.close();
		   stat.close();					
			} catch(Exception e) {
				e.printStackTrace();
			}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 406, 364);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextField());
		contentPane.add(getTextArea());
		contentPane.add(getNom());
		contentPane.add(getFirstname());
		contentPane.add(getTextArea_1());
		contentPane.add(getTextArea_2());
		contentPane.add(getAdress());
		contentPane.add(getTextArea_3());
		contentPane.add(getAdress_cp());
		contentPane.add(getAdress_v());
		contentPane.add(getCoeff());
		contentPane.add(getTextArea_4());
		contentPane.add(getTextArea_5());
		contentPane.add(getTextArea_6());
		contentPane.add(getPra_type());
		contentPane.add(getLieuEx());
		contentPane.add(getButton());
	}
	private JTextComponent getPraLieu() {
		// TODO Auto-generated method stub
		return null;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText("Praticiens");
			textField.setForeground(Color.WHITE);
			textField.setFont(new Font("Tahoma", Font.BOLD, 16));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBackground(new Color(100, 149, 237));
			textField.setBounds(0, 0, 427, 58);
		}
		return textField;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setText("Nom");
			textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea.setEditable(false);
			textArea.setBounds(28, 71, 70, 22);
		}
		return textArea;
	}
	private static JTextField getNom() {
		if (nom == null) {
			nom = new JTextField();
			nom.setEditable(false);
			nom.setColumns(10);
			nom.setBounds(105, 71, 173, 22);
		}
		return nom;
	}
	private static JTextField getFirstname() {
		if (firstname == null) {
			firstname = new JTextField();
			firstname.setEditable(false);
			firstname.setColumns(10);
			firstname.setBounds(105, 101, 173, 22);
		}
		return firstname;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setText("Prenom");
			textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_1.setEditable(false);
			textArea_1.setBounds(28, 101, 68, 22);
		}
		return textArea_1;
	}
	private JTextArea getTextArea_2() {
		if (textArea_2 == null) {
			textArea_2 = new JTextArea();
			textArea_2.setText("Adresse");
			textArea_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_2.setEditable(false);
			textArea_2.setBounds(28, 131, 59, 22);
		}
		return textArea_2;
	}
	private static JTextField getAdress() {
		if (adress == null) {
			adress = new JTextField();
			adress.setEditable(false);
			adress.setColumns(10);
			adress.setBounds(105, 131, 244, 22);
		}
		return adress;
	}
	private JTextArea getTextArea_3() {
		if (textArea_3 == null) {
			textArea_3 = new JTextArea();
			textArea_3.setText("Ville");
			textArea_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_3.setEditable(false);
			textArea_3.setBounds(28, 161, 59, 22);
		}
		return textArea_3;
	}
	private static JTextField getAdress_cp() {
		if (adress_cp == null) {
			adress_cp = new JTextField();
			adress_cp.setEditable(false);
			adress_cp.setColumns(10);
			adress_cp.setBounds(105, 160, 59, 22);
		}
		return adress_cp;
	}
	private static JTextField getAdress_v() {
		if (adress_v == null) {
			adress_v = new JTextField();
			adress_v.setEditable(false);
			adress_v.setColumns(10);
			adress_v.setBounds(176, 160, 173, 22);
		}
		return adress_v;
	}
	private static JTextField getCoeff() {
		if (coeff == null) {
			coeff = new JTextField();
			coeff.setEditable(false);
			coeff.setColumns(10);
			coeff.setBounds(130, 190, 148, 22);
		}
		return coeff;
	}
	private JTextArea getTextArea_4() {
		if (textArea_4 == null) {
			textArea_4 = new JTextArea();
			textArea_4.setText("Coeff Notorie");
			textArea_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_4.setEditable(false);
			textArea_4.setBounds(26, 191, 92, 22);
		}
		return textArea_4;
	}
	private JTextArea getTextArea_5() {
		if (textArea_5 == null) {
			textArea_5 = new JTextArea();
			textArea_5.setText("Lieu d'exercice");
			textArea_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_5.setEditable(false);
			textArea_5.setBounds(26, 221, 92, 22);
		}
		return textArea_5;
	}
	private JTextArea getTextArea_6() {
		if (textArea_6 == null) {
			textArea_6 = new JTextArea();
			textArea_6.setText("Type");
			textArea_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_6.setEditable(false);
			textArea_6.setBounds(26, 250, 92, 22);
		}
		return textArea_6;
	}
	private JTextField getPra_type() {
		if (pra_type == null) {
			pra_type = new JTextField();
			pra_type.setEditable(false);
			pra_type.setColumns(10);
			pra_type.setBounds(130, 249, 148, 22);
		}
		return pra_type;
	}
	private JTextField getLieuEx() {
		if (lieuEx == null) {
			lieuEx = new JTextField();
			lieuEx.setEditable(false);
			lieuEx.setColumns(10);
			lieuEx.setBounds(130, 220, 148, 22);
		}
		return lieuEx;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Fermer");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
						dispose();
					
				}
			});
			button.setBounds(279, 281, 97, 25);
		}
		return button;
	}
}
