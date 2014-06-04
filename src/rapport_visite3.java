import java.awt.EventQueue;

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

public class rapport_visite3 extends accueil {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titre;
	private JTextField rapport;
	private JButton detail;
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
	static ResultSet requete;
	static ResultSetMetaData requeteMeta;
	static ResultSet requete2;
	static ResultSetMetaData requete2Meta;
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
	static int numSuivant=0;
	static int numPrecedent=0;
	static int rapNum;
	static String secteurUser="";
	static String laboUser="";
	
	private static JComboBox<String> chercheNom;
	private JButton ok;
	private JTextArea textArea_1;
	private JTextPane bilan2;
	private JTextPane echantillons;
	private JButton prec;
	private JButton suiv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		rapport_visite frame = new rapport_visite();
		frame.setVisible(true);
	}
	public void Fenetre2(){
		this.setTitle("Fenetre2");
	}
	/**
	 * Create the frame.
	 */
	public rapport_visite3() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 444);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTitre());
		contentPane.add(getRapport());
		contentPane.add(getDetail());
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
		contentPane.add(getPrec());
		contentPane.add(getSuiv());
		setVisible(true);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println(matricule); 
					table = "rapport_visite";
					table2="praticien";
					table3="offrir";
					
					getChercheNom().removeAllItems();
					
					ConnexionBDD conn = new ConnexionBDD();
					//recherche le secteur du User
					Statement state = (Statement) conn.execBDD().createStatement();
					requete = state.executeQuery("SELECT SEC_CODE from region where REG_CODE=( "
										+"SELECT REG_CODE from travailler where VIS_MATRICULE='"+ matricule +"'  and  JJMMAA='"+ dateEmbauche +"')");
					requeteMeta=(ResultSetMetaData)requete.getMetaData();
					while(requete.next()){
						secteurUser=requete.getString("SEC_CODE");
					}
					
					//recherche le labo du User
					requete2= state.executeQuery("SELECT LAB_CODE from visiteur where VIS_MATRICULE='"+ matricule +"'");
					requete2Meta=(ResultSetMetaData)requete2.getMetaData();
					while(requete2.next()){
						laboUser=requete2.getString("LAB_CODE");
					}
					
					//recherche tous les rapports n'appartenant pas au User, des visiteurs étant dans le même secteur et le même labo 
				    result = state.executeQuery("SELECT RAP_NUM as numRapport,SUBSTRING(RAP_DATE,1,10) as dateRapport from rapport_visite "
				    		+ "where VIS_MATRICULE<>'"+ matricule +"' and VIS_MATRICULE in ("
									+"SELECT VIS_MATRICULE from travailler where REG_CODE in ("
									+"SELECT REG_CODE from region where SEC_CODE='"+ secteurUser +"')) and VIS_MATRICULE in ("
									+"SELECT VIS_MATRICULE from visiteur where LAB_CODE='"+  laboUser+"')");
				    resultMeta = (ResultSetMetaData) result.getMetaData();
				    while(result.next()){
				    	getChercheNom().addItem(result.getString("numRapport"));
				    	rapNum=result.getInt("numRapport");
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
					new praticien().setVisible(true);
				}
			});
			detail.setBounds(332, 132, 97, 25);
		}
		return detail;
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
			btnNew.setVisible(false);
			btnNew.setBounds(248, 354, 97, 25);
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
			fermer.setBounds(357, 354, 97, 25);
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
								
								  rapNum= result1.getInt("RAP_NUM");
								  System.out.println(" | RAP_num: " + numRap +" | ");
								 
								  getRapport().setText(result1.getString("RAP_NUM"));
								  getDate().setText(result1.getString("RAP_DATE"));
								  getBilan2().setText(result1.getString("RAP_BILAN"));
								  getMotif().setText(result1.getString("RAP_MOTIF"));
								  
								  Statement state4 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						           ResultSet result4 = state4.executeQuery("SELECT * FROM visiteur where VIS_MATRICULE='"+ result1.getString("VIS_MATRICULE") +"'");
						           ResultSetMetaData resultM4 = (ResultSetMetaData) result4.getMetaData();
						           while(result4.next()){	
						        	   titre.setText("Rapports de visite de "+result4.getString("VIS_NOM")+" "+result4.getString("Vis_PRENOM"));
						           }
								  
								  
								  Statement state2 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where PRA_NUM='"+ result1.getString("PRA_NUM") +"'");
						           resultM2 = (ResultSetMetaData) result2.getMetaData();
						           
						           while(result2.next()){	
						        	   getPraticien().setText(result2.getString("PRA_NOM"));
						        	   numPraticien=result2.getInt("PRA_NUM");
						        	   System.out.println("| numPraticien" +numPraticien + " |"); 
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
	private JButton getPrec() {
		if (prec == null) {
			prec = new JButton("Précédent");
			prec.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						boolean valid = false;
						ConnexionBDD conn = new ConnexionBDD();
						Statement state;
						state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						result2=state.executeQuery("SELECT * from rapport_visite "
				    		+ "where VIS_MATRICULE<>'"+ matricule +"' and VIS_MATRICULE in ("
									+"SELECT VIS_MATRICULE from travailler where REG_CODE in ("
									+"SELECT REG_CODE from region where SEC_CODE='"+ secteurUser +"')) and VIS_MATRICULE in ("
									+"SELECT VIS_MATRICULE from visiteur where LAB_CODE='"+  laboUser+"') order by RAP_NUM DESC");
						 ResultSetMetaData resultMeta2 = (ResultSetMetaData) result2.getMetaData();
						 while(result2.next()){	
							 if (valid==true){
								 numPrecedent=result2.getInt("RAP_NUM");
								 valid=false;
							 }
							 if(result2.getInt("RAP_NUM") == rapNum){
								 valid=true;
							 }
						 }
						if (numPrecedent!=0){
							rapNum=numPrecedent;
						}
						PrecSuivantActionPerformed(arg0);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			prec.setBounds(12, 354, 97, 25);
		}
		return prec;
	}
	private JButton getSuiv() {
		if (suiv == null) {
			suiv = new JButton("Suivant");
			suiv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						boolean valid = false;
						ConnexionBDD conn = new ConnexionBDD();
						Statement state;
						state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						result2=state.executeQuery("SELECT * from rapport_visite "
				    		+ "where VIS_MATRICULE<>'"+ matricule +"' and VIS_MATRICULE in ("
									+"SELECT VIS_MATRICULE from travailler where REG_CODE in ("
									+"SELECT REG_CODE from region where SEC_CODE='"+ secteurUser +"')) and VIS_MATRICULE in ("
									+"SELECT VIS_MATRICULE from visiteur where LAB_CODE='"+  laboUser+"') order by RAP_NUM ");
						 ResultSetMetaData resultMeta2 = (ResultSetMetaData) result2.getMetaData();
						 System.out.println(" | Avant le while :" + rapNum);
						 while(result2.next()){	
							 if (valid==true){
								 numSuivant=result2.getInt("RAP_NUM");
								 valid=false;
							 }
							 if(result2.getInt("RAP_NUM") == rapNum){
								 valid=true;
							 }
						 }
						if (numSuivant!=0){
							rapNum=numSuivant;
						}
						PrecSuivantActionPerformed(e);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			suiv.setBounds(120, 354, 97, 25);
		}
		return suiv;
	}
	private void PrecSuivantActionPerformed(java.awt.event.ActionEvent evt) {
		try{	 
			echant=null;
			ConnexionBDD conn = new ConnexionBDD();
			Statement state1 = (Statement) conn.execBDD().createStatement();
			System.out.println(" | RAP_num du select: " + rapNum +" | ");
			 ResultSet result4 = state1.executeQuery("SELECT * FROM rapport_visite where RAP_NUM='"+rapNum+"'");
			 ResultSetMetaData resultMeta4 = (ResultSetMetaData) result4.getMetaData();	
				while(result4.next()){	
						rapNum= result4.getInt("RAP_NUM");
						System.out.println(" | RAP_num après clic: " + rapNum +" | ");
					  
					  getRapport().setText(result4.getString("RAP_NUM"));
					  getDate().setText(result4.getString("RAP_DATE"));
					  getBilan2().setText(result4.getString("RAP_BILAN"));
					  getMotif().setText(result4.getString("RAP_MOTIF"));
					  
					  Statement state2 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
			           ResultSet result5 = state2.executeQuery("SELECT * FROM "+table2 +" where PRA_NUM='"+ result4.getString("PRA_NUM") +"'");
			           ResultSetMetaData resultM5 = (ResultSetMetaData) result5.getMetaData();
			           while(result5.next()){	
			        	   numPraticien=result5.getInt("PRA_NUM");
			        	   getPraticien().setText(result5.getString("PRA_NOM"));
			           }
			           
			           Statement stat = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
			           requete = stat.executeQuery("SELECT * FROM visiteur where VIS_MATRICULE='"+ result4.getString("VIS_MATRICULE") +"'");
			           ResultSetMetaData requeteMeta = (ResultSetMetaData) requete.getMetaData();
			           while(requete.next()){	
			        	   titre.setText("Rapports de visite de "+requete.getString("VIS_NOM")+" "+requete.getString("Vis_PRENOM"));
			           }
			           Statement state3 = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
			           result3 = state3.executeQuery("SELECT * FROM "+table3 +" where RAP_NUM='"+ result4.getString("RAP_NUM") +"'");
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
}
