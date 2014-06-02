import connexion.ConnexionBDD;

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
import java.sql.SQLException;

import javax.swing.JTextPane;

import java.awt.SystemColor;

public class medicaments extends accueil {

	private static final long serialVersionUID = -7366817889264865746L;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField titre;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JTextArea textArea_3;
	private JTextArea textArea_4;
	private JTextArea textArea_5;
	private JTextArea textArea_6;
	private JButton prec;
	private JButton suiv;
	private JButton fermer;
	private JTextField code;
	private JTextField nom;
	private JTextField compo;
	private JTextField prix;
	private JTextArea textArea;
	private JTextArea textArea_7;
	private static JComboBox<String> chercheMed;
	private JButton ok;

	static Connection conn;
	static Statement state;
	static ResultSet result;
	static ResultSetMetaData resultMeta;
	static Statement state1;
	static ResultSet result1;
	static ResultSetMetaData resultM;
	static Statement state2;
	static ResultSet result2;
	static ResultSetMetaData resultM2;
	static Statement state3;
	static ResultSet result3;
	static ResultSetMetaData resultM3;
	static int idMed;
	static int numSUIVANT=0;
	static int numPRECEDENT=0;
	static String table;
	static String table2;
	static String BD;
	private JTextField famille;
	private JTextPane effet2;
	private JTextPane textPane_1;
	private JTextPane indicat2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		medicaments frame = new medicaments();
		frame.setVisible(true);
	}

	public medicaments() {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table = "medicament";
					table2="famille"; 
				    
					ConnexionBDD conn = new ConnexionBDD();
					Statement state = (Statement) conn.execBDD().createStatement();
				    result = state.executeQuery("SELECT * FROM "+ table+" Order by MED_NOMCOMMERCIAL");
				    resultMeta = (ResultSetMetaData) result.getMetaData();
				    while(result.next()){
				    	getChercheMed().addItem(result.getString("MED_NOMCOMMERCIAL"));
				    }
				    
		            result.close();
		       	  	state.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 533, 578);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		contentPane.add(getFermer());
		contentPane.add(getSuiv());
		contentPane.add(getPrec());
		contentPane.add(getTitre());
		
		setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 62, 517, 404);
			panel.add(getTextArea_1());
			panel.add(getTextArea_2());
			panel.add(getTextArea_3());
			panel.add(getTextArea_4());
			panel.add(getTextArea_5());
			panel.add(getTextArea_6());
			panel.add(getCode());
			panel.add(getNom());
			panel.add(getCompo());
			panel.add(getPrix());
			panel.add(getTextArea());
			panel.add(getTextArea_7());
			panel.add(getChercheMed());
			panel.add(getOk());
			panel.add(getFamille());
			panel.add(getEffet2());
			panel.add(getTextPane_1());
			panel.add(getIndicat2());
		}
		return panel;
	}
	private JTextField getTitre() {
		if (titre == null) {
			titre = new JTextField();
			titre.setBounds(0, 0, 521, 58);
			titre.setText("Médicaments");
			titre.setForeground(Color.WHITE);
			titre.setFont(new Font("Tahoma", Font.BOLD, 16));
			titre.setColumns(10);
			titre.setBackground(new Color(100, 149, 237));
			titre.setEditable(false);
		}
		return titre;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setText("Dépot Légal");
			textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_1.setBounds(12, 71, 70, 22);
			textArea_1.setEditable(false);
		}
		return textArea_1;
	}
	private JTextArea getTextArea_2() {
		if (textArea_2 == null) {
			textArea_2 = new JTextArea();
			textArea_2.setText("Nom Commercial");
			textArea_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_2.setBounds(12, 101, 116, 22);
			textArea_2.setEditable(false);
		}
		return textArea_2;
	}
	private JTextArea getTextArea_3() {
		if (textArea_3 == null) {
			textArea_3 = new JTextArea();
			textArea_3.setText("Famille");
			textArea_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_3.setBounds(12, 131, 107, 22);
			textArea_3.setEditable(false);
		}
		return textArea_3;
	}
	private JTextArea getTextArea_4() {
		if (textArea_4 == null) {
			textArea_4 = new JTextArea();
			textArea_4.setText("Composition");
			textArea_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_4.setBounds(12, 161, 107, 22);
			textArea_4.setEditable(false);
		}
		return textArea_4;
	}
	private JTextArea getTextArea_5() {
		if (textArea_5 == null) {
			textArea_5 = new JTextArea();
			textArea_5.setText("Effet indésirables");
			textArea_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_5.setBounds(12, 196, 108, 22);
			textArea_5.setEditable(false);
		}
		return textArea_5;
	}
	private JTextArea getTextArea_6() {
		if (textArea_6 == null) {
			textArea_6 = new JTextArea();
			textArea_6.setText("Contre Indications");
			textArea_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_6.setBounds(12, 282, 119, 22);
			textArea_6.setEditable(false);
		}
		return textArea_6;
	}
	private JButton getPrec() {
		if (prec == null) {
			prec = new JButton("Précédent");
			prec.setBounds(34, 491, 97, 29);
			prec.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						boolean valid = false;
						ConnexionBDD conn = new ConnexionBDD();
						Statement state;
						state = (Statement) ((ConnexionBDD) conn).execBDD().createStatement();
						 result2 = state.executeQuery("SELECT * FROM medicament Order by MED_NOMCOMMERCIAL DESC");
						 ResultSetMetaData resultMeta2 = (ResultSetMetaData) result2.getMetaData();
						 while(result2.next()){	
							 if (valid==true){
								 numSUIVANT=result2.getInt("id");
								 valid=false;
							 }
							 if(result2.getInt("id") == idMed){
								 valid=true;
							 }
						 }
						if (numSUIVANT!=0){
							idMed=numSUIVANT;
						}
						PrecSuivantActionPerformed(e);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
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
						 result2 = state.executeQuery("SELECT * FROM medicament Order by MED_NOMCOMMERCIAL");
						 ResultSetMetaData resultMeta2 = (ResultSetMetaData) result2.getMetaData();
						 while(result2.next()){	
							 if (valid==true){
								 numSUIVANT=result2.getInt("id");
								 valid=false;
							 }
							 if(result2.getInt("id") == idMed){
								 valid=true;
							 }
						 }
						if (numSUIVANT!=0){
							idMed=numSUIVANT;
						}
						PrecSuivantActionPerformed(e);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			suiv.setBounds(152, 491, 97, 29);
		}
		return suiv;
	}
	private JButton getFermer() {
		if (fermer == null) {
			fermer = new JButton("Fermer");
			fermer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			fermer.setBounds(338, 491, 97, 29);
		}
		return fermer;
	}
	private JTextField getCode() {
		if (code == null) {
			code = new JTextField();
			code.setColumns(10);
			code.setBounds(131, 71, 190, 22);
			code.setEditable(false);
		}
		return code;
	}
	private JTextField getNom() {
		if (nom == null) {
			nom = new JTextField();
			nom.setColumns(10);
			nom.setBounds(131, 100, 190, 22);
			nom.setEditable(false);
		}
		return nom;
	}
	private JTextField getCompo() {
		if (compo == null) {
			compo = new JTextField();
			compo.setColumns(10);
			compo.setBounds(131, 161, 362, 22);
			compo.setEditable(false);
		}
		return compo;
	}
	private JTextField getPrix() {
		if (prix == null) {
			prix = new JTextField();
			prix.setColumns(10);
			prix.setBounds(131, 369, 116, 22);
			prix.setEditable(false);
		}
		return prix;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setText("Prix Echantillon");
			textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea.setBounds(12, 369, 107, 22);
			textArea.setEditable(false);
		}
		return textArea;
	}
	private JTextArea getTextArea_7() {
		if (textArea_7 == null) {
			textArea_7 = new JTextArea();
			textArea_7.setText("Chercher");
			textArea_7.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			textArea_7.setBounds(12, 25, 70, 22);
			textArea_7.setEditable(false);
		}
		return textArea_7;
	}
	private static JComboBox<String> getChercheMed() {
		if (chercheMed == null) {
			chercheMed = new JComboBox<String>();
			chercheMed.setBounds(91, 24, 173, 22);
		}
		return chercheMed;
	}
	private JButton getOk() {
		if (ok == null) {
			ok = new JButton("OK");
			ok.setMnemonic(KeyEvent.VK_ENTER); 
			getRootPane().setDefaultButton(ok); 
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					validerActionPerformed(arg0);
				}
			});
			ok.setBounds(276, 23, 59, 25);
		}
		return ok;
	}
	private JTextField getFamille() {
		if (famille == null) {
			famille = new JTextField();
			famille.setColumns(10);
			famille.setBounds(131, 130, 362, 22);
			famille.setEditable(false);
		}
		return famille;
	}
	private JTextPane getEffet2() {
		if (effet2 == null) {
			effet2 = new JTextPane();
			effet2.setBackground(SystemColor.control);
			effet2.setBounds(132, 196, 362, 73);
			effet2.setEditable(false);
		}
		return effet2;
	}
	private JTextPane getTextPane_1() {
		if (textPane_1 == null) {
			textPane_1 = new JTextPane();
			textPane_1.setBounds(273, 212, 6, 22);
			textPane_1.setEditable(false);
		}
		return textPane_1;
	}
	private JTextPane getIndicat2() {
		if (indicat2 == null) {
			indicat2 = new JTextPane();
			indicat2.setBackground(SystemColor.control);
			indicat2.setBounds(130, 282, 362, 73);
			indicat2.setEditable(false);
		}
		return indicat2;
	}
	private void PrecSuivantActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			ConnexionBDD conn = new ConnexionBDD();
			Statement state1 = (Statement) conn.execBDD().createStatement();
		    result3 = state1.executeQuery("SELECT * FROM "+table +" where id='"+idMed+"'");
		    resultM3 = (ResultSetMetaData) result3.getMetaData();					      
				while(result3.next()){				
						idMed=result3.getInt("id"); 
					  getCode().setText(result3.getString("MED_DEPOTLEGAL"));
					  getNom().setText(result3.getString("MED_NOMCOMMERCIAL"));
					  getCompo().setText(result3.getString("MED_COMPOSITION"));
					  getIndicat2().setText(result3.getString("MED_CONTREINDIC"));
					  getPrix().setText(result3.getString("MED_PRIXECHANTILLON"));
					  getEffet2().setText(result3.getString("MED_EFFETS"));
					  
					  Statement state2 = (Statement) conn.execBDD().createStatement();
			           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where FAM_CODE='"+ result3.getString("FAM_CODE") +"'");
			           resultM2 = (ResultSetMetaData) result2.getMetaData();
			           while(result2.next()){	
			        	   getFamille().setText(result2.getString("FAM_LIBELLE"));
			           }
				}
		}catch(Exception f) {
			f.printStackTrace();						
		}
		
	}
	private void validerActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			ConnexionBDD conn = new ConnexionBDD();
			Statement state1 = (Statement) conn.execBDD().createStatement();
		    result1 = state1.executeQuery("SELECT * FROM "+table +" where MED_NOMCOMMERCIAL='"+chercheMed.getSelectedItem()+"'");
		    resultM = (ResultSetMetaData) result1.getMetaData();					      
				while(result1.next()){				
					System.out.print("\t" + result1.getString("MED_DEPOTLEGAL") + "\t |");
					System.out.print("\t" + result1.getString("MED_NOMCOMMERCIAL") + "\t |");
					System.out.println("\n");
						idMed=result1.getInt("id");
						System.out.println(idMed); 
					  getCode().setText(result1.getString("MED_DEPOTLEGAL"));
					  getNom().setText(result1.getString("MED_NOMCOMMERCIAL"));
					  getCompo().setText(result1.getString("MED_COMPOSITION"));
					  getIndicat2().setText(result1.getString("MED_CONTREINDIC"));
					  getPrix().setText(result1.getString("MED_PRIXECHANTILLON"));
					  getEffet2().setText(result1.getString("MED_EFFETS"));
					  
						Statement state2 = (Statement) conn.execBDD().createStatement();
			           result2 = state2.executeQuery("SELECT * FROM "+table2 +" where FAM_CODE='"+ result1.getString("FAM_CODE") +"'");
			           resultM2 = (ResultSetMetaData) result2.getMetaData();
			           while(result2.next()){	
			        	   getFamille().setText(result2.getString("FAM_LIBELLE"));
			           }
				}
		}catch(Exception e) {
			e.printStackTrace();						
		}
	}
}
