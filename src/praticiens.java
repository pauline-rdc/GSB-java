import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import connexion.ConnexionBDD;

public class praticiens extends accueil {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField titre;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JTextArea textArea_3;
	private JTextArea textArea_4;
	private JTextArea textArea_5;
	private JTextArea textArea_6;
	private JButton precedent;
	private JButton suivant;
	private JButton fermer;
	private JButton ok;
	private static JComboBox<String> chercheNom;
	private JTextField nom;
	private JTextField prenom;
	private JTextField adresse;
	private JTextField ville;
	private JTextField coeff;

	static Connection conn;
	static ResultSet resul;
	static ResultSetMetaData resultMe;
	static ResultSet result;
	static ResultSetMetaData resultMeta;
	static ResultSet result2;
	static ResultSetMetaData resultMeta2;
	static ResultSet result3;
	static ResultSetMetaData resultMeta3;
	
	static String table;
	static String table2;
	static String table3;	
	static String cherche;
	private JTextField typepra;
	private JTextField cp;
	static int num;
	static String tab [];
	private JTextField typepra2;
	private JTextArea textArea_7;
	
	public static void main(String[] args) {
		praticiens frame = new praticiens();
        frame.setVisible(true); 
	}

	public praticiens() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					table = "praticien";
					table2= "type_praticien";
					
					ConnexionBDD conn = new ConnexionBDD();
					Statement stat = (Statement) conn.execBDD().createStatement();
	
				    resul = stat.executeQuery("SELECT * FROM "+ table);
				    resultMe = (ResultSetMetaData) resul.getMetaData();
				    while(resul.next()){
				    	getChercheNom().addItem(resul.getString("PRA_NOM")+" "+resul.getString("PRA_PRENOM"));
				    }

            resul.close();
       	  	stat.close();					
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	  });
		
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 443);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		setVisible(true);
		
		
		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 0, 422, 412);
			panel.add(getTitre());
			panel.add(getTextArea());
			panel.add(getTextArea_1());
			panel.add(getTextArea_2());
			panel.add(getTextArea_3());
			panel.add(getTextArea_4());
			panel.add(getTextArea_5());
			panel.add(getTextArea_6());
			panel.add(getOk());
			panel.add(getChercheNom());
			panel.add(getNom());
			panel.add(getPrenom());
			panel.add(getAdresse());
			panel.add(getVille());
			panel.add(getCoeff());
			panel.add(getTypepra());
			panel.add(getCp());
			panel.add(getTypepra2());
			panel.add(getSuivant());
			panel.add(getPrecedent());
			panel.add(getFermer());
			panel.add(getTextArea_7());
		}
		return panel;
	}
	private JTextField getTitre() {
		if (titre == null) {
			titre = new JTextField();
			titre.setText("Praticiens");
			titre.setForeground(Color.WHITE);
			titre.setFont(new Font("Tahoma", Font.BOLD, 16));
			titre.setColumns(10);
			titre.setBackground(new Color(100, 149, 237));
			titre.setBounds(0, 0, 471, 58);
			titre.setEditable(false);
		}
		return titre;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setText("Chercher");
			textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea.setBounds(10, 73, 70, 22);
			textArea.setEditable(false);
		}
		return textArea;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setText("Nom");
			textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_1.setBounds(12, 113, 70, 22);
			textArea_1.setEditable(false);
		}
		return textArea_1;
	}
	private JTextArea getTextArea_2() {
		if (textArea_2 == null) {
			textArea_2 = new JTextArea();
			textArea_2.setText("Prenom");
			textArea_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_2.setBounds(12, 143, 68, 22);
			textArea_2.setEditable(false);
		}
		return textArea_2;
	}
	private JTextArea getTextArea_3() {
		if (textArea_3 == null) {
			textArea_3 = new JTextArea();
			textArea_3.setText("Adresse");
			textArea_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_3.setBounds(12, 173, 59, 22);
			textArea_3.setEditable(false);
		}
		return textArea_3;
	}
	private JTextArea getTextArea_4() {
		if (textArea_4 == null) {
			textArea_4 = new JTextArea();
			textArea_4.setText("Ville");
			textArea_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_4.setBounds(12, 203, 59, 22);
			textArea_4.setEditable(false);
		}
		return textArea_4;
	}
	private JTextArea getTextArea_5() {
		if (textArea_5 == null) {
			textArea_5 = new JTextArea();
			textArea_5.setText("Coeff Notorie");
			textArea_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_5.setBounds(10, 233, 92, 22);
			textArea_5.setEditable(false);
		}
		return textArea_5;
	}
	private JTextArea getTextArea_6() {
		if (textArea_6 == null) {
			textArea_6 = new JTextArea();
			textArea_6.setText("Lieu d'exercice");
			textArea_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_6.setBounds(10, 263, 92, 22);
			textArea_6.setEditable(false);
		}
		return textArea_6;
	}
	private JButton getPrecedent() {
		if (precedent == null) {
			precedent = new JButton("Pr\u00E9c\u00E9dent");
			precedent.setBounds(26, 346, 97, 29);
			precedent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (num!= 1){
						num=num-1;
					}
					PrecSuivantActionPerformed();
				}
			});
		}
		return precedent;
	}
	private JButton getSuivant() {
		if (suivant == null) {
			suivant = new JButton("Suivant");
			suivant.setBounds(144, 346, 97, 29);
			suivant.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (num!= 31){
						num=num+1;
					}
					PrecSuivantActionPerformed();
				}
			});
		}
		return suivant;
	}
	private JButton getFermer() {
		if (fermer == null) {
			fermer = new JButton("Fermer");
			fermer.setBounds(293, 346, 97, 29);
			fermer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return fermer;
	}
	private JButton getOk() {
		if (ok == null) {
			ok = new JButton("OK");
			ok.setMnemonic(KeyEvent.VK_ENTER); 
			getRootPane().setDefaultButton(ok); 
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					tab = connexion.selectionner.displayString(chercheNom.getSelectedItem());
					validerActionPerformed(tab);
				}
			});
			ok.setBounds(274, 71, 59, 25);
		}
		return ok;
	}
	private static JComboBox<String> getChercheNom() {
		if (chercheNom == null) {
			chercheNom = new JComboBox<String>();
			chercheNom.setBounds(89, 72, 173, 22);
		}
		return chercheNom;
	}
	private JTextField getNom() {
		if (nom == null) {
			nom = new JTextField();
			nom.setColumns(10);
			nom.setBounds(89, 113, 173, 22);
			nom.setEditable(false);
		}
		return nom;
	}
	private JTextField getPrenom() {
		if (prenom == null) {
			prenom = new JTextField();
			prenom.setColumns(10);
			prenom.setBounds(89, 143, 173, 22);
			prenom.setEditable(false);
		}
		return prenom;
	}
	private JTextField getAdresse() {
		if (adresse == null) {
			adresse = new JTextField();
			adresse.setColumns(10);
			adresse.setBounds(89, 173, 244, 22);
			adresse.setEditable(false);
		}
		return adresse;
	}
	private JTextField getVille() {
		if (ville == null) {
			ville = new JTextField();
			ville.setColumns(10);
			ville.setBounds(160, 202, 173, 22);
			ville.setEditable(false);
		}
		return ville;
	}
	private JTextField getCoeff() {
		if (coeff == null) {
			coeff = new JTextField();
			coeff.setColumns(10);
			coeff.setBounds(114, 232, 148, 22);
			coeff.setEditable(false);
		}
		return coeff;
	}
	private JTextField getTypepra() {
		if (typepra == null) {
			typepra = new JTextField();
			typepra.setColumns(10);
			typepra.setBounds(114, 262, 150, 22);
			typepra.setEditable(false);
		}
		return typepra;
	}
	private JTextField getCp() {
		if (cp == null) {
			cp = new JTextField();
			cp.setColumns(10);
			cp.setBounds(89, 202, 59, 22);
			cp.setEditable(false);
		}
		return cp;
	}
private void PrecSuivantActionPerformed() {
	try{
		ConnexionBDD conn = new ConnexionBDD();
		Statement state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
	    result = state.executeQuery("SELECT * FROM "+table +" where PRA_NUM='"+num+"'");
	    resultMeta = (ResultSetMetaData) result.getMetaData();	

			while(result.next()){	
				num = result.getInt("PRA_NUM");								
				  getNom().setText(result.getString("PRA_NOM"));
				  getPrenom().setText(result.getString("PRA_PRENOM"));
				  getAdresse().setText(result.getString("PRA_ADRESSE"));
				  getCp().setText(result.getString("PRA_CP"));
				  getVille().setText(result.getString("PRA_VILLE"));
				  getCoeff().setText(result.getString("PRA_COEFNOTORIETE"));
				  
				  Statement state2 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
		           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where TYP_CODE='"+ result.getString("TYP_CODE") +"'");
		           resultMeta2 = (ResultSetMetaData) result2.getMetaData();
		           while(result2.next()){	
		        	   getTypepra().setText(result2.getString("TYP_LIBELLE"));
		        	   getTypepra2().setText(result2.getString("TYP_LIEU"));
		           }
		       
		           
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
private void validerActionPerformed(String[] tab) {	
					try{
						ConnexionBDD conn = new ConnexionBDD();
						Statement state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
					    result = state.executeQuery("SELECT * FROM "+table +" where PRA_NOM='"+tab[0]+"' and PRA_PRENOM='"+ tab[1] +"'");
					    resultMeta = (ResultSetMetaData) result.getMetaData();	

							while(result.next()){	
								num = result.getInt("PRA_NUM");								
								  getNom().setText(result.getString("PRA_NOM"));
								  getPrenom().setText(result.getString("PRA_PRENOM"));
								  getAdresse().setText(result.getString("PRA_ADRESSE"));
								  getCp().setText(result.getString("PRA_CP"));
								  getVille().setText(result.getString("PRA_VILLE"));
								  getCoeff().setText(result.getString("PRA_COEFNOTORIETE"));
								  
								  Statement state2 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where TYP_CODE='"+ result.getString("TYP_CODE") +"'");
						           resultMeta2 = (ResultSetMetaData) result2.getMetaData();
						           while(result2.next()){	
						        	   getTypepra().setText(result2.getString("TYP_LIBELLE"));
						        	   getTypepra2().setText(result2.getString("TYP_LIEU"));
						           }
						       
						           
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
	}
	private JTextField getTypepra2() {
		if (typepra2 == null) {
			typepra2 = new JTextField();
			typepra2.setEditable(false);
			typepra2.setColumns(10);
			typepra2.setBounds(114, 292, 150, 22);
		}
		return typepra2;
	}
	private JTextArea getTextArea_7() {
		if (textArea_7 == null) {
			textArea_7 = new JTextArea();
			textArea_7.setText("Type");
			textArea_7.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_7.setEditable(false);
			textArea_7.setBounds(10, 292, 92, 22);
		}
		return textArea_7;
	}
}
