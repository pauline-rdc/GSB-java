import connexion.ConnexionBDD;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import javax.swing.JTextPane;

import java.awt.SystemColor;

public class visiteur extends accueil {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
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
	private JButton btnOK;
	private static JComboBox<String> chercheNom;
	private static JTextField nom;
	private static JTextField prenom;
	private static JTextField adresse;
	private static JTextField cp;
	private static JTextField ville;
	private static JTextField labo;
	
	static Connection conn;
	static ResultSet resul;
	static ResultSetMetaData resultMe;
	static ResultSet result;
	static ResultSetMetaData resultMeta;
	static ResultSet result2;
	static ResultSetMetaData resultMeta2;
	static ResultSet result3;
	static ResultSetMetaData resultMeta3;
	static String visMatricule;
	static int numSuivant;
	static int numPrecedent;
	static int idVis;
	static String table;
	static String table2;
	static String table3;	
	static String regionLibel="";
	static String cherche;
	static String matric;
	static String numPrec="";
	static String numSuiv="";
	private JTextArea textArea_7;
	private static JTextField sect;
	private JTextArea textArea_8;
	private JTextPane region;


	public static void main(String[] args) {
			        visiteur frame = new visiteur();
			        frame.setVisible(true);                        
				} 

	public visiteur() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table = "visiteur";
					table2= "secteur";
					table3= "labo";
					
					ConnexionBDD conn = new ConnexionBDD();
					Statement stat = (Statement) conn.execBDD().createStatement();

				    resul = stat.executeQuery("SELECT * FROM "+ table+" Order by Vis_NOM");
				    resultMe = (ResultSetMetaData) resul.getMetaData();
				    while(resul.next()){
				    	getChercheNom().addItem(resul.getString("VIS_NOM")+" "+resul.getString("VIS_PRENOM"));
				    }

		            resul.close();
		       	  	stat.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 435);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextField());
		contentPane.add(getTextArea());
		contentPane.add(getTextArea_1());
		contentPane.add(getTextArea_2());
		contentPane.add(getTextArea_3());
		contentPane.add(getTextArea_4());
		contentPane.add(getTextArea_5());
		contentPane.add(getTextArea_6());
		contentPane.add(getPrecedent());
		contentPane.add(getSuivant());
		contentPane.add(getFermer());
		contentPane.add(getBtnOK());
		contentPane.add(getChercheNom());
		contentPane.add(getNom());
		contentPane.add(getPrenom());
		contentPane.add(getAdresse());
		contentPane.add(getCp());
		contentPane.add(getVille());
		contentPane.add(getLabo());
		contentPane.add(getTextArea_7());
		contentPane.add(getSect());
		contentPane.add(getTextArea_8());
		contentPane.add(getRegion());
		setVisible(true);
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText("Visiteurs");
			textField.setForeground(Color.WHITE);
			textField.setFont(new Font("Tahoma", Font.BOLD, 16));
			textField.setColumns(10);
			textField.setBackground(new Color(100, 149, 237));
			textField.setBounds(0, 0, 471, 58);
			textField.setEditable(false);
		}
		return textField;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setText("Chercher");
			textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea.setBounds(10, 64, 70, 22);
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
			textArea_5.setText("Secteur");
			textArea_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_5.setBounds(10, 233, 59, 22);
			textArea_5.setEditable(false);
		}
		return textArea_5;
	}
	private JTextArea getTextArea_6() {
		if (textArea_6 == null) {
			textArea_6 = new JTextArea();
			textArea_6.setText("Labo");
			textArea_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_6.setBounds(10, 263, 70, 22);
			textArea_6.setEditable(false);
		}
		return textArea_6;
	}
	private JButton getPrecedent() {
		if (precedent == null) {
			precedent = new JButton("Pr\u00E9c\u00E9dent");
			precedent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						boolean valid = false;
						ConnexionBDD conn = new ConnexionBDD();
						Statement state;
						state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						 result2 = state.executeQuery("SELECT * FROM visiteur Order by VIS_NOM DESC");
						 resultMeta2 = (ResultSetMetaData) result2.getMetaData();
						 while(result2.next()){	
							 if (valid==true){
								// numPrecedent=result2.getInt("id");
								 numPrec = result2.getString("VIS_MATRICULE");
								 valid=false;
							 }
							 if(result2.getString("VIS_MATRICULE").equals(visMatricule)){
								 valid=true;
							 }
						 }
						if (numPrec.isEmpty()==false){
							visMatricule=numPrec;
						}
						PrecSuivantActionPerformed(e);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			precedent.setBounds(30, 348, 97, 29);
		}
		return precedent;
	}
	private JButton getSuivant() {
		if (suivant == null) {
			suivant = new JButton("Suivant");
			suivant.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						boolean valid = false;
						ConnexionBDD conn = new ConnexionBDD();
						Statement state;
						state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						 result2 = state.executeQuery("SELECT * FROM visiteur Order by VIS_NOM");
						 resultMeta2 = (ResultSetMetaData) result2.getMetaData();
						 
						 while(result2.next()){	
							 if (valid==true){
								 numSuiv = result2.getString("VIS_MATRICULE");
								 valid=false;
							 }
							 if(result2.getString("VIS_MATRICULE").equals(visMatricule)){
								 valid=true;
							 }
						 }
						 System.out.println("numSuiv" + numSuiv);
						if (numSuiv.isEmpty()==false){
							 System.out.println("numSuiv2" + numSuiv);
							visMatricule=numSuiv;
						}
						PrecSuivantActionPerformed(arg0);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			suivant.setBounds(148, 348, 97, 29);
		}
		return suivant;
	}
	private JButton getFermer() {
		if (fermer == null) {
			fermer = new JButton("Fermer");
			fermer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			fermer.setBounds(334, 348, 97, 29);
		}
		return fermer;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.setMnemonic(KeyEvent.VK_ENTER); 
			getRootPane().setDefaultButton(btnOK); 
			btnOK.addActionListener(new java.awt.event.ActionListener(){
					 public void actionPerformed(java.awt.event.ActionEvent evt) {
			                validerActionPerformed(evt);
			            }	

			});
			btnOK.setBounds(326, 62, 59, 25);
		}
		return btnOK;
	}
	private static JComboBox<String> getChercheNom() {
		if (chercheNom == null) {
			chercheNom = new JComboBox<String>();
			chercheNom.setBounds(89, 63, 225, 22);
		}
		return chercheNom;
	}
	private static JTextField getNom() {
		if (nom == null) {
			nom = new JTextField();
			nom.setColumns(10);
			nom.setBounds(89, 113, 173, 22);
			nom.setEditable(false);
		}
		return nom;
	}
	private static JTextField getPrenom() {
		if (prenom == null) {
			prenom = new JTextField();
			prenom.setColumns(10);
			prenom.setBounds(89, 143, 173, 22);
			prenom.setEditable(false);
		}
		return prenom;
	}
	private static JTextField getAdresse() {
		if (adresse == null) {
			adresse = new JTextField();
			adresse.setColumns(10);
			adresse.setBounds(89, 173, 296, 22);
			adresse.setEditable(false);
		}
		return adresse;
	}
	private static JTextField getCp() {
		if (cp == null) {
			cp = new JTextField();
			cp.setColumns(10);
			cp.setBounds(89, 203, 70, 22);
			cp.setEditable(false);
		}
		return cp;
	}
	private static JTextField getVille() {
		if (ville == null) {
			ville = new JTextField();
			ville.setColumns(10);
			ville.setBounds(212, 202, 173, 22);
			ville.setEditable(false);
		}
		return ville;
	}
	private static JTextField getLabo() {
		if (labo == null) {
			labo = new JTextField();
			labo.setColumns(10);
			labo.setBounds(89, 262, 116, 22);
			labo.setEditable(false);
		}
		return labo;
	}
	private JTextArea getTextArea_7() {
		if (textArea_7 == null) {
			textArea_7 = new JTextArea();
			textArea_7.setText("Ville");
			textArea_7.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_7.setBounds(171, 203, 59, 22);
			textArea_7.setEditable(false);
		}
		return textArea_7;
	}
	private static JTextField getSect() {
		if (sect == null) {
			sect = new JTextField();
			sect.setColumns(10);
			sect.setBounds(89, 233, 116, 22);
			sect.setEditable(false);
		}
		return sect;
	}
	

	private void PrecSuivantActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			ConnexionBDD conn = new ConnexionBDD();
			Statement state = (Statement) conn.execBDD().createStatement();
			result = state.executeQuery("SELECT * FROM "+table +" where VIS_MATRICULE='"+visMatricule+"'");
			resultMeta = (ResultSetMetaData) result.getMetaData();
			
			
			while(result.next()){							
				  getNom().setText(result.getString("Vis_NOM"));
				  getPrenom().setText(result.getString("Vis_PRENOM"));
				  getAdresse().setText(result.getString("Vis_ADRESSE"));
				  getCp().setText(result.getString("Vis_CP"));
				  getVille().setText(result.getString("Vis_VILLE"));		  
					matric=result.getString("VIS_MATRICULE");	
					Statement state2 = (Statement) conn.execBDD().createStatement();
		           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where SEC_CODE='"+ result.getString("SEC_CODE") +"'");
		           resultMeta2 = (ResultSetMetaData) result2.getMetaData();
		           while(result2.next()){	
		        	   getSect().setText(result2.getString("SEC_LIBELLE"));
		           }
		           Statement state3 = (Statement) conn.execBDD().createStatement();
		           result3 = state3.executeQuery("SELECT * FROM "+table3 +" where LAB_CODE='"+ result.getString("LAB_CODE") +"'");
		           resultMeta3 = (ResultSetMetaData) result3.getMetaData();
		           while(result3.next()){	
		        	   getLabo().setText(result3.getString("LAB_NOM"));
		           }
		           Statement state4 = (Statement) conn.execBDD().createStatement();
		           ResultSet result4 = state4.executeQuery("SELECT REG_CODE FROM travailler WHERE VIS_MATRICULE= '"+ result.getString("Vis_MATRICULE")+"'");
		           while(result4.next()){
		        	   regionLibel="";
		        	   System.out.println("test 1");
		        	   System.out.println(result4.getString("REG_CODE")); 
		        	   Statement state5 = (Statement) conn.execBDD().createStatement();
			           ResultSet result5 = state5.executeQuery("SELECT * from region where REG_CODE='"+ result4.getString("REG_CODE") + "'");
			           while(result5.next()){
			        	   String Newligne=System.getProperty("line.separator"); 
			        	   if ( regionLibel==""){
			        		   regionLibel=result5.getString("REG_NOM");
			        	   }else{
			        		   regionLibel=regionLibel+Newligne+result5.getString("REG_NOM");
			        	   }
			           }
		           }	  
		           getRegion().setText(regionLibel);
		           System.out.println(regionLibel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void validerActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			ConnexionBDD conn = new ConnexionBDD();
			Statement state = (Statement) conn.execBDD().createStatement();
			String[] tab = connexion.selectionner.displayString(chercheNom.getSelectedItem());
			result = state.executeQuery("SELECT * FROM "+table +" where VIS_NOM='"+tab[0]+"' and Vis_PRENOM='"+ tab[1]+"'");
			resultMeta = (ResultSetMetaData) result.getMetaData();
			
			
			while(result.next()){						
				// idVis= result.getInt("id");
				 visMatricule = result.getString("VIS_MATRICULE");
				  getNom().setText(result.getString("Vis_NOM"));
				  getPrenom().setText(result.getString("Vis_PRENOM"));
				  getAdresse().setText(result.getString("Vis_ADRESSE"));
				  getCp().setText(result.getString("Vis_CP"));
				  getVille().setText(result.getString("Vis_VILLE"));		  
					matric=result.getString("VIS_MATRICULE");	
					Statement state2 = (Statement) conn.execBDD().createStatement();
		           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where SEC_CODE='"+ result.getString("SEC_CODE") +"'");
		           resultMeta2 = (ResultSetMetaData) result2.getMetaData();
		           while(result2.next()){	
		        	   getSect().setText(result2.getString("SEC_LIBELLE"));
		           }
		           Statement state3 = (Statement) conn.execBDD().createStatement();
		           result3 = state3.executeQuery("SELECT * FROM "+table3 +" where LAB_CODE='"+ result.getString("LAB_CODE") +"'");
		           resultMeta3 = (ResultSetMetaData) result3.getMetaData();
		           while(result3.next()){	
		        	   getLabo().setText(result3.getString("LAB_NOM"));
		           }
		           Statement state4 = (Statement) conn.execBDD().createStatement();
		           ResultSet result4 = state4.executeQuery("SELECT REG_CODE FROM travailler WHERE VIS_MATRICULE= '"+ result.getString("Vis_MATRICULE")+"'");
		          
		           while(result4.next()){
		        	   regionLibel="";
		        	   System.out.println("test 1");
		        	   System.out.println(result4.getString("REG_CODE")); 
		        	   Statement state5 = (Statement) conn.execBDD().createStatement();
			           ResultSet result5 = state5.executeQuery("SELECT * from region where REG_CODE='"+ result4.getString("REG_CODE") + "'");
			           while(result5.next()){
			        	   String Newligne=System.getProperty("line.separator"); 
			        	   if ( regionLibel==""){
			        		   regionLibel=result5.getString("REG_NOM");
			        	   }else{
			        		   regionLibel=regionLibel+Newligne+result5.getString("REG_NOM");
			        	   }
			           }
		           }	  
		           getRegion().setText(regionLibel);
		           System.out.println(regionLibel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private JTextArea getTextArea_8() {
		if (textArea_8 == null) {
			textArea_8 = new JTextArea();
			textArea_8.setText("Région");
			textArea_8.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_8.setEditable(false);
			textArea_8.setBounds(10, 298, 70, 22);
		}
		return textArea_8;
	}
	private JTextPane getRegion() {
		if (region == null) {
			region = new JTextPane();
			region.setEditable(false);
			region.setBackground(SystemColor.menu);
			region.setBounds(89, 295, 122, 40);
		}
		return region;
	}
}
