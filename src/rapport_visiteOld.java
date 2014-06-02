import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import connexion.ConnexionBDD;

import javax.swing.JTextPane;

import java.awt.SystemColor;

public class rapport_visiteOld extends accueil {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titre;
	private JTextField rapport;
	private JButton detail;
	private JButton precedent;
	private JButton suivant;
	private JButton btnNew;
	private JButton fermer;
	private JTextField date;
	private JTextArea txtrNumroRapport;
	private JTextArea txtrPraticien;
	private JTextArea textArea;
	private JTextArea txtrMotifVisite;
	private JTextArea txtrBilan;
	private JTextArea txtrOffreDchantillons;
	private JTextField motif;
	private JTextField praticien;

	static Connection conn;
	static ResultSet result;
	static ResultSetMetaData resultMeta;
	static ResultSet result1;
	static ResultSetMetaData resultM;
	static ResultSet result2;
	static ResultSetMetaData resultM2;
	static ResultSet result3;
	static ResultSetMetaData resultM3;
	
	static String table;
	static String table2;
	static String table3;
	static String echant;
	static int numRap;
	static int numSuivant;
	static int numPrecedent;
	
	private static JComboBox<String> chercheNom;
	private JButton ok;
	private JTextArea textArea_1;
	private JTextPane bilan2;
	private JTextPane echantillons;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		rapport_visiteOld frame = new rapport_visiteOld();
		frame.setVisible(true);
	}
	public void Fenetre2(){
		this.setTitle("Fenetre2");
	}
	/**
	 * Create the frame.
	 */
	public rapport_visiteOld() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 452);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTitre());
		contentPane.add(getRapport());
		contentPane.add(getDetail());
		contentPane.add(getPrecedent());
		contentPane.add(getSuivant());
		contentPane.add(getBtnNew());
		contentPane.add(getFermer());
		contentPane.add(getDate());
		contentPane.add(getTxtrNumroRapport());
		contentPane.add(getTxtrPraticien());
		contentPane.add(getTextArea());
		contentPane.add(getTxtrMotifVisite());
		contentPane.add(getTxtrBilan());
		contentPane.add(getTxtrOffreDchantillons());
		contentPane.add(getMotif());
		contentPane.add(getPraticien());
		contentPane.add(getChercheNom());
		contentPane.add(getOk());
		contentPane.add(getTextArea_1());
		contentPane.add(getBilan2());
		contentPane.add(getEchantillons());
		setVisible(true);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println(matricule); 
					table = "rapport_visite";
					table2="praticien";
					table3="offrir";
					
					ConnexionBDD conn = new ConnexionBDD();
					Statement state = (Statement) conn.execBDD().createStatement();
					
				    result = state.executeQuery("SELECT * FROM  rapport_visite WHERE VIS_MATRICULE='"+matricule+"'");
				    resultMeta = (ResultSetMetaData) result.getMetaData();
				    while(result.next()){
				    	getChercheNom().addItem(result.getString("RAP_NUM"));
				    }
		            result.close();
		       	  	state.close();					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField getTitre() {
		if (titre == null) {
			titre = new JTextField();
			titre.setForeground(Color.WHITE);
			titre.setBackground(new Color(100, 149, 237));
			titre.setFont(new Font("Tahoma", Font.BOLD, 16));
			titre.setText("Rapports de visite");
			titre.setBounds(0, 0, 533, 58);
			titre.setColumns(10);
			titre.setEditable(false);
		}
		return titre;
	}
	private JTextField getRapport() {
		if (rapport == null) {
			rapport = new JTextField();
			rapport.setText("");
			rapport.setBounds(142, 102, 59, 22);
			rapport.setColumns(10);
			rapport.setEditable(false);
		}
		return rapport;
	}
	private JButton getDetail() {
		if (detail == null) {
			detail = new JButton("Détails");
			detail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new praticiens().setVisible(true);
					setVisible(false);
				}
			});
			detail.setBounds(332, 132, 97, 25);
		}
		return detail;
	}
	private JButton getPrecedent() {
		if (precedent == null) {
			precedent = new JButton("Précédent");
			precedent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						boolean valid = false;
						ConnexionBDD conn = new ConnexionBDD();
						Statement state;
						state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						 result2 = state.executeQuery("SELECT * FROM rapport_visite Order by RAP_NUM");
						 ResultSetMetaData resultMeta2 = (ResultSetMetaData) result2.getMetaData();
						 while(result2.next()){	
							 if (valid==true){
								 numPrecedent=result2.getInt("RAP_NUM");
								 valid=false;
							 }
							 if(result2.getInt("RAP_NUM") == numRap ){
								 valid=true;
							 }
						 }
						if (numPrecedent!=0){
							numRap=numPrecedent;
						}
						PrecSuivantActionPerformed();
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
					
				}
			});
			precedent.setBounds(10, 369, 97, 25);
		}
		return precedent;
	}
	private JButton getSuivant() {
		if (suivant == null) {
			suivant = new JButton("Suivant");
			suivant.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						ConnexionBDD conn = new ConnexionBDD();
						Statement st;
						st = (Statement) conn.execBDD().createStatement();
						ResultSet rs = st.executeQuery("SELECT COUNT(*) as count FROM rapport_visite");
						rs.next();
						int number = rs.getInt(1);

						if(numRap!=number){
							numRap=numRap+1;
						}
						PrecSuivantActionPerformed();
					} catch (SQLException e) {
						e.printStackTrace();
					} 	
				}
			});
			suivant.setBounds(128, 369, 97, 25);
		}
		return suivant;
	}
	private JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton("Nouveau");
			btnNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new rapport_saisie().setVisible(true);
					setVisible(false);
				}
			});
			btnNew.setBounds(250, 369, 97, 25);
		}
		return btnNew;
	}
	private JButton getFermer() {
		if (fermer == null) {
			fermer = new JButton("Fermer");
			fermer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			fermer.setBounds(386, 369, 97, 25);
		}
		return fermer;
	}
	private JTextField getDate() {
		if (date == null) {
			date = new JTextField();
			date.setBounds(142, 162, 173, 22);
			date.setColumns(10);
			date.setEditable(false);
		}
		return date;
	}
	private JTextArea getTxtrNumroRapport() {
		if (txtrNumroRapport == null) {
			txtrNumroRapport = new JTextArea();
			txtrNumroRapport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			txtrNumroRapport.setText("Num\u00E9ro Rapport");
			txtrNumroRapport.setBounds(12, 102, 116, 22);
			txtrNumroRapport.setEditable(false);
		}
		return txtrNumroRapport;
	}
	private JTextArea getTxtrPraticien() {
		if (txtrPraticien == null) {
			txtrPraticien = new JTextArea();
			txtrPraticien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			txtrPraticien.setText("Praticien");
			txtrPraticien.setBounds(12, 132, 116, 22);
			txtrPraticien.setEditable(false);
		}
		return txtrPraticien;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setText("Date de rapport");
			textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea.setBounds(12, 162, 116, 22);
			textArea.setEditable(false);
		}
		return textArea;
	}
	private JTextArea getTxtrMotifVisite() {
		if (txtrMotifVisite == null) {
			txtrMotifVisite = new JTextArea();
			txtrMotifVisite.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			txtrMotifVisite.setText("Motif Visite");
			txtrMotifVisite.setBounds(12, 192, 107, 22);
			txtrMotifVisite.setEditable(false);
		}
		return txtrMotifVisite;
	}
	private JTextArea getTxtrBilan() {
		if (txtrBilan == null) {
			txtrBilan = new JTextArea();
			txtrBilan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			txtrBilan.setText("Bilan");
			txtrBilan.setBounds(12, 222, 37, 22);
			txtrBilan.setEditable(false);
		}
		return txtrBilan;
	}
	private JTextArea getTxtrOffreDchantillons() {
		if (txtrOffreDchantillons == null) {
			txtrOffreDchantillons = new JTextArea();
			txtrOffreDchantillons.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			txtrOffreDchantillons.setText("Offre d'\u00E9chantillons");
			txtrOffreDchantillons.setBounds(332, 180, 151, 22);
			txtrOffreDchantillons.setEditable(false);
		}
		return txtrOffreDchantillons;
	}
	private JTextField getMotif() {
		if (motif == null) {
			motif = new JTextField();
			motif.setBounds(142, 192, 173, 22);
			motif.setColumns(10);
			motif.setEditable(false);
		}
		return motif;
	}
	private JTextField getPraticien() {
		if (praticien == null) {
			praticien = new JTextField();
			praticien.setColumns(10);
			praticien.setBounds(142, 133, 173, 22);
			praticien.setEditable(false);
		}
		return praticien;
	}
	private static JComboBox<String> getChercheNom() {
		if (chercheNom == null) {
			chercheNom = new JComboBox<String>();
			chercheNom.setBounds(113, 65, 161, 22);
		}
		return chercheNom;
	}
	
	private void PrecSuivantActionPerformed(){
		try{	
			echant=null;
			ConnexionBDD conn = new ConnexionBDD();
			Statement state1 = (Statement) conn.execBDD().createStatement();
			 result1 = state1.executeQuery("SELECT * FROM "+table +" where RAP_NUM='"+ numRap+"'");
			 resultMeta = (ResultSetMetaData) result1.getMetaData();	
				while(result1.next()){												
					  getRapport().setText(result1.getString("RAP_NUM"));
					  getDate().setText(result1.getString("RAP_DATE"));
					  getBilan2().setText(result1.getString("RAP_BILAN"));
					  getMotif().setText(result1.getString("RAP_MOTIF"));
					  
					  Statement state2 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
			           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where PRA_NUM='"+ result1.getString("PRA_NUM") +"'");
			           resultM2 = (ResultSetMetaData) result2.getMetaData();
			           while(result2.next()){	
			        	   getPraticien().setText(result2.getString("PRA_NOM"));
			           }
			           
			           Statement state3 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
			           result3 = state3.executeQuery("SELECT * FROM "+table3 +" where RAP_NUM='"+ result1.getString("RAP_NUM") +"'");
			           resultM3 = (ResultSetMetaData) result3.getMetaData();
			           String Newligne=System.getProperty("line.separator"); 
			           while(result3.next()){	
			        	   if (echant==null){
			        		   echant =result3.getString("MED_DEPOTLEGAL")+" : "+ result3.getString("OFF_QTE")+"";
			        	   }else{
			        		   echant =echant +Newligne+ result3.getString("MED_DEPOTLEGAL")+" : "+ result3.getString("OFF_QTE")+"";
			        	   }
			           }
			           getEchantillons().setText(echant);
			           System.out.println(echant); 
				}
		}catch(Exception e) {
			e.printStackTrace();						
		}
	}
	private JButton getOk() {
		if (ok == null) {
			ok = new JButton("OK");
			ok.setMnemonic(KeyEvent.VK_ENTER); 
			getRootPane().setDefaultButton(ok); 
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{	 
						echant=null;
						ConnexionBDD conn = new ConnexionBDD();
						Statement state1 = (Statement) conn.execBDD().createStatement();
						 result1 = state1.executeQuery("SELECT * FROM "+table +" where RAP_NUM='"+chercheNom.getSelectedItem()+"'");
						 resultMeta = (ResultSetMetaData) result1.getMetaData();	
							while(result1.next()){	
								  numRap= result1.getInt("RAP_NUM");
								  getRapport().setText(result1.getString("RAP_NUM"));
								  getDate().setText(result1.getString("RAP_DATE"));
								  getBilan2().setText(result1.getString("RAP_BILAN"));
								  getMotif().setText(result1.getString("RAP_MOTIF"));
								  
								  Statement state2 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where PRA_NUM='"+ result1.getString("PRA_NUM") +"'");
						           resultM2 = (ResultSetMetaData) result2.getMetaData();
						           while(result2.next()){	
						        	   getPraticien().setText(result2.getString("PRA_NOM"));
						           }
						           
						           Statement state3 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						           result3 = state3.executeQuery("SELECT * FROM "+table3 +" where RAP_NUM='"+ result1.getString("RAP_NUM") +"'");
						           resultM3 = (ResultSetMetaData) result3.getMetaData();
						           String Newligne=System.getProperty("line.separator"); 					
						           while(result3.next()){	
						        	   if (echant==null){
						        		   echant =result3.getString("MED_DEPOTLEGAL")+" : "+ result3.getString("OFF_QTE")+"";
						        	   }else{
						        		   echant =echant +Newligne+ result3.getString("MED_DEPOTLEGAL")+" : "+ result3.getString("OFF_QTE")+"";
						        	   }
						           }
						           getEchantillons().setText(echant);
						           System.out.println(echant); 
							}
					}catch(Exception e) {
						e.printStackTrace();						
					}
				}
			});
			ok.setBounds(286, 64, 59, 25);
		}
		return ok;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setText("Chercher");
			textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_1.setBounds(10, 67, 70, 22);
		}
		return textArea_1;
	}
	private JTextPane getBilan2() {
		if (bilan2 == null) {
			bilan2 = new JTextPane();
			bilan2.setBackground(SystemColor.control);
			bilan2.setBounds(58, 233, 257, 94);
			bilan2.setEditable(false);
		}
		return bilan2;
	}
	private JTextPane getEchantillons() {
		if (echantillons == null) {
			echantillons = new JTextPane();
			echantillons.setBackground(SystemColor.menu);
			echantillons.setBounds(332, 211, 122, 117);
			echantillons.setEditable(false);
		}
		return echantillons;
	}
}
