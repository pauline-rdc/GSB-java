import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import connexion.ConnexionBDD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
public class rapport_saisie extends accueil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField titre;
	private JButton fermer;
	private JTextField jour;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JTextArea textArea_3;
	private JTextArea textArea_4;
	private JTextArea textArea_5;

	static Connection conn;
	static Statement state;
	static ResultSet result;
	static ResultSetMetaData resultMeta;
	static Statement state1;
	static int result1;
	static ResultSet results;
	static int result3;
	static ResultSetMetaData resultM;
	static String tab [];
	static Statement state4;
	static ResultSet result4;
	static ResultSetMetaData resultMeta4;
	
	
	static int numRap;
	static int numPra;
	static String dateRap;
	static String med_echant;
	static String med_echant0;
	static String med_echant1;
	static String nbEchant;
	static String med_echant_m;
	static String table;
	static String table2;
	static String table3;
	static String test;
	static String motif_combo;
	
	private static JComboBox<String> praticien;
	private JButton valider;
	private JTextArea bilan;
	private JButton plus;
	private JTextField mois;
	private JTextField annee;
	private JTextArea textArea_6;
	private JPanel panel_1;
	private JPanel pan;
	
	public JComboBox mon_combo[] = new JComboBox[10];
	public JTextField ma_qte[] = new JTextField[10];
	public int hauteur_fenetre = 500;
	public int hauteur_panel= 398;
	public int hauteur_pan = 19;
	public int hauteur_btn_plus =172;
	public int hauteur_btnplus=209;
	public int hauteur_validation = 400;
	public int hauteur_combo=30;
	public int n=2;
	public int m=0;
	private JComboBox combo_motif;
	private JTextField autre_motif;
	public boolean validate =true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		rapport_saisie frame = new rapport_saisie();
		frame.setVisible(true);
	}

	public rapport_saisie() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 539, 443);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		setVisible(true);
		System.out.println("matricule :"); 
   	 	System.out.println(matricule); 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table = "praticien";
					table2="rapport_visite"; 
					table3="medicament";
				    
					ConnexionBDD conn = new ConnexionBDD();						
					
					Statement state = (Statement) conn.execBDD().createStatement();
				    result = state.executeQuery("SELECT * FROM "+ table);
				    resultMeta = (ResultSetMetaData) result.getMetaData();
				    while(result.next()){
				    	getPraticien().addItem(result.getString("PRA_NOM")+" "+result.getString("PRA_PRENOM"));
				    }
				    
		            result.close();
		       	  	state.close();		
		       	  	
		       	  	
					getCombo_motif().addItem("Périodicité");
					getCombo_motif().addItem("Actualisation");
				    getCombo_motif().addItem("Relance");
				    getCombo_motif().addItem("Solicitation praticien");
				    getCombo_motif().addItem("Autre");
					
					
				} catch (Exception s) {
					s.printStackTrace();
				}
			}
		});
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 0, 521, 398);
			panel.add(getTitre());
			panel.add(getFermer());
			panel.add(getJour());
			panel.add(getTextArea_1());
			panel.add(getTextArea_2());
			panel.add(getTextArea_3());
			panel.add(getTextArea_4());
			panel.add(getTextArea_5());
			panel.add(getPraticien());
			panel.add(getValider());
			panel.add(getBilan());
			panel.add(getPlus());
			panel.add(getTextField_1_2());
			panel.add(getTextField_1_3());
			panel.add(getTextArea_6());
			panel.add(getPanel_1());
			panel.add(getPan());
			panel.add(getCombo_motif());
			panel.add(getTextField_1_1());
			
		}
		return panel;
	}
	private JTextField getTitre() {
		if (titre == null) {
			titre = new JTextField();
			titre.setText("Saisie d'un rapport de visite");
			titre.setForeground(Color.WHITE);
			titre.setFont(new Font("Tahoma", Font.BOLD, 16));
			titre.setColumns(10);
			titre.setBackground(new Color(100, 149, 237));
			titre.setBounds(0, 0, 515, 58);
			titre.setEditable(false);
		}
		return titre;
	}
	private JButton getFermer() {
		if (fermer == null) {
			fermer = new JButton("Fermer");
			fermer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			fermer.setBounds(350, 318, 97, 25);
		}
		return fermer;
	}
	private JTextField getJour() {
		if (jour == null) {
			jour = new JTextField();
			jour.setColumns(10);
			jour.setBounds(155, 101, 27, 22);
		}
		return jour;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setText("Praticien");
			textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_1.setBounds(25, 71, 116, 22);
			textArea_1.setEditable(false);
		}
		return textArea_1;
	}
	private JTextArea getTextArea_2() {
		if (textArea_2 == null) {
			textArea_2 = new JTextArea();
			textArea_2.setText("Date de de la visite");
			textArea_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_2.setBounds(25, 101, 118, 22);
			textArea_2.setEditable(false);
		}
		return textArea_2;
	}
	private JTextArea getTextArea_3() {
		if (textArea_3 == null) {
			textArea_3 = new JTextArea();
			textArea_3.setText("Motif Visite");
			textArea_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_3.setBounds(25, 131, 107, 22);
			textArea_3.setEditable(false);
		}
		return textArea_3;
	}
	private JTextArea getTextArea_4() {
		if (textArea_4 == null) {
			textArea_4 = new JTextArea();
			textArea_4.setText("Bilan");
			textArea_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_4.setBounds(25, 162, 59, 22);
			textArea_4.setEditable(false);
		}
		return textArea_4;
	}
	private JTextArea getTextArea_5() {
		if (textArea_5 == null) {
			textArea_5 = new JTextArea();
			textArea_5.setText("Ajout d'un Echantillon");
			textArea_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_5.setBounds(280, 168, 151, 22);
			textArea_5.setEditable(false);
		}
		return textArea_5;
	}
	private static JComboBox<String> getPraticien() {
		if (praticien == null) {
			praticien = new JComboBox<String>();
			praticien.setBounds(155, 71, 173, 22);
		}
		return praticien;
	}
	
	private JButton getValider() {
		if (valider == null) {
			valider = new JButton("Valider");
			valider.setMnemonic(KeyEvent.VK_ENTER); 
			getRootPane().setDefaultButton(valider); 
			valider.addActionListener(new java.awt.event.ActionListener(){
				 public void actionPerformed(java.awt.event.ActionEvent evt) {
		                validerActionPerformed(evt);
		            }
			});
			valider.setBounds(65, 318, 97, 25);
		}
		return valider;
	}
	
	private void beforeValidate() throws SQLException{
		// Motif
		if (( autre_motif.getText().length() != 0  )&&( motif_combo == "Autre" )){
			motif_combo =autre_motif.getText();
		}else if ((autre_motif.getText().length() == 0)&&( motif_combo == "Autre" )){
			JOptionPane.showMessageDialog(null,"Il faut entré un motif dans le champs correspondant ");
			validate=false;
		}
		
		
		ConnexionBDD conn = new ConnexionBDD();
		
		// Numéro du rapport saisit
		Statement st =  (Statement) conn.execBDD().createStatement(); 
		ResultSet rs = st.executeQuery("SELECT COUNT(*) as count FROM rapport_visite");
		rs.next();
		int number = rs.getInt(1);
		numRap = number+1;
		
		//Numéro du praticien
		tab = connexion.selectionner.displayString(praticien.getSelectedItem());
		Statement state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();	
		result = state.executeQuery("SELECT * FROM praticien where PRA_NOM='"+tab[0]+"' and PRA_PRENOM='"+ tab[1] +"'");
	    resultMeta = (ResultSetMetaData) result.getMetaData();	
	    
			while(result.next()){	
				numPra = result.getInt("PRA_NUM");
				System.out.println(numPra);
			}
		//Date de la visite
			dateRap=String.valueOf(annee.getText())+"-"+String.valueOf(mois.getText())+"-"+String.valueOf(jour.getText());
			dateRap=dateRap +" 00:00:00";
	}
		
	
	private void validerActionPerformed(java.awt.event.ActionEvent evt) {
        try{ 	
        	beforeValidate();
        	boolean okey = true;	// vérification : s'il y a plusieurs fois le même produit sélectionné
			for (int i=0; i<m; i++){
				for (int j=0; j<m;j++){
					if((mon_combo[i]==mon_combo[j]) && (i != j) ) {	
						okey=false;
					}
				}
			}
			if (okey==false){	//Message d'aletre
				validate=false;
				JOptionPane.showMessageDialog(null,"Vous avez sélectionné plusieurs fois le même produit ! ");
			}
			if (validate==true){	//Si aucun problème rencontré lors des vérifications: on enregistre le rapport et les échantillons
				ConnexionBDD conn = new ConnexionBDD();// Connexion
				//insertion des informations du rapport
	           	Statement state1 = (Statement) conn.execBDD().createStatement();
				result1 = state1.executeUpdate("INSERT INTO rapport_visite  VALUES('"+matricule+"','"+numRap+"','"+ numPra +"','"
						+ dateRap+"','"+ bilan.getText()+"','"+ motif_combo+"')");
				System.out.println(matricule+"| "+ numRap + "| " + med_echant+"| " + nbEchant);
				Statement state3 = (Statement) conn.execBDD().createStatement();
			
				// insertion des échantillons et de leurs quantités
				for(int i=0; i<m; i++){	
					med_echant_m = (String) mon_combo[i].getSelectedItem();
					nbEchant=(String) ma_qte[i].getText();
					System.out.println(med_echant_m +"|"+ nbEchant);
					result3 = state3.executeUpdate("INSERT INTO offrir  VALUES('"+matricule+"','"+numRap+"','"+ med_echant_m +"','"
							+ nbEchant +"')");
				}
				
				setVisible(false);
				new rapport_visite().setVisible(true);
	        }
        }
        catch (SQLException s){
            System.out.println("SQL code does not execute.");
            JOptionPane.showMessageDialog(null,"Problème lors de l'enregistrement ! ");
        } 
	}
	

	
	private JTextArea getBilan() {
		if (bilan == null) {
			bilan = new JTextArea();
			bilan.setBackground(SystemColor.control);
			bilan.setBounds(25, 197, 241, 108);
		}
		return bilan;
	}
	private JButton getPlus() {
		if (plus == null) {
			plus = new JButton("+");
			plus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						//Lorsque nous cliquons sur notre bouton, on passe a l'autre fenêtre
					if(arg0.getSource() == plus){
						if (hauteur_btn_plus<=449){		//changement de position du bouton et/ou agrandissement du panel
						hauteur_btn_plus= hauteur_btn_plus+30;	
						hauteur_pan=hauteur_pan+30;
						pan.setBounds(278, 197, 181, hauteur_pan );		
						plus.setBounds(468, hauteur_btn_plus, 41, 25);	
						}
						
						if (hauteur_pan>=124)	// position du bouton fermer
						{ 	fermer.setBounds(65, 356, 97, 25);
						hauteur_panel=hauteur_panel+30;			
						panel.setBounds(0,0,521,hauteur_panel);
						}
						
						if (hauteur_pan==199 || hauteur_pan==289){	// agrandissement de la fenetre
							hauteur_fenetre= hauteur_fenetre+30;		
							setBounds(100,100,539,hauteur_fenetre);
						}
						
						mon_combo[m] = new JComboBox<String>();		// ajout des nouveaux champs pour le nouvel échantillon
						ma_qte[m] = new JTextField("1");		
						pan.add(mon_combo[m]);		
						pan.add(ma_qte[m]);			
						
						try {
							ConnexionBDD conn = new ConnexionBDD();
							table = "medicament";		
							Statement state = (Statement) conn.execBDD().createStatement();
							test = "SELECT * FROM "+ table +"";
						    result = state.executeQuery("SELECT * FROM "+ table);
						    resultMeta = (ResultSetMetaData) result.getMetaData();
						    while(result.next()){
						    	mon_combo[m].addItem(result.getString("MED_DEPOTLEGAL"));
						    } 
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						m++;	
						n++;
				}}
			});
			plus.setBounds(468, 166, 41, 25);
		}
		return plus;
	}
	private JTextField getTextField_1_2() {
		if (mois == null) {
			mois = new JTextField();
			mois.setBounds(194, 101, 27, 22);
			mois.setColumns(10);
		}
		return mois;
	}
	private JTextField getTextField_1_3() {
		if (annee == null) {
			annee = new JTextField();
			annee.setBounds(233, 101, 48, 22);
			annee.setColumns(10);
		}
		return annee;
	}
	private JTextArea getTextArea_6() {
		if (textArea_6 == null) {
			textArea_6 = new JTextArea();
			textArea_6.setText("(jj-mm-aaaa)");
			textArea_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_6.setBounds(288, 102, 85, 22);
		}
		return textArea_6;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBounds(450, 215, -175, -28);
		}
		return panel_1;
	}
	private JPanel getPan() {
		if (pan == null) {
			pan = new JPanel();
			pan.setBounds(278, 197, 181, 49);
		}
		return pan;
	}
	private JComboBox<String> getCombo_motif() {
		if (combo_motif == null) {
			combo_motif = new JComboBox<String>();
			combo_motif.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					motif_combo= (String) combo_motif.getSelectedItem();
					
					System.out.println("|"+motif_combo+"|");
					
					if (motif_combo=="Autre"){
						autre_motif.setEnabled(true);
					}else
					{	autre_motif.setEnabled(false);
					}
					System.out.println();
				}
			});
			combo_motif.setBounds(155, 130, 116, 22);
		}
		return combo_motif;
	}
	private JTextField getTextField_1_1() {
		if (autre_motif == null) {
			autre_motif = new JTextField();
			autre_motif.setBounds(280, 130, 116, 22);
			autre_motif.setColumns(10);
		}
		return autre_motif;
	}
}
