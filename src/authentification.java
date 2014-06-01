import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import connexion.ConnexionBDD;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
 
public class authentification extends JFrame {

	public static String matricule;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Connection conn1;
	static Statement stat;
	static ResultSet resul;
	static ResultSetMetaData resultMeta;
	private JTextField login;
	private JLabel lblLogin;
	private JLabel lblMotDePass;
	private JTextField titre;
	private JTextField textField;
	private JButton ok;
	private JPasswordField pass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					authentification frame = new authentification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public authentification() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 276);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLogin());
		contentPane.add(getLblLogin());
		contentPane.add(getLblMotDePass());
		contentPane.add(getTitre());
		contentPane.add(getTextField_1());
		contentPane.add(getOk());
		contentPane.add(getPass());
	}
	
	private void validerActionPerformed(java.awt.event.ActionEvent evt) {
		String loginn = login.getText();
        @SuppressWarnings("deprecation")
		String passwordd = pass.getText();
 
		try {
		
		ConnexionBDD conn = new ConnexionBDD();
		Statement stat = (Statement) conn.execBDD().createStatement();
		
		ResultSet resul = stat.executeQuery("SELECT * FROM visiteur WHERE VIS_NOM ='"+loginn +"'");
	    resultMeta = (ResultSetMetaData) resul.getMetaData();
	    
		if(resul.next())	
			{	String date = resul.getString("VIS_DATEEMBAUCHE"); 
				matricule= resul.getString("VIS_MATRICULE");
				String annee= date.substring(0,4);
				String mois= date.substring(5,7);
				String jour= date.substring(8,10);
				System.out.print(date +" "+ annee+" "+ mois+" "+ jour);
				
				if (mois.equals("01"))	{mois = "-jan-";}
				if (mois.equals("02"))	{mois = "-feb-";}
				if (mois.equals("03"))	{mois = "-mar-";}
				if (mois.equals("04"))	{mois = "-apr-";}
				if (mois.equals("05"))	{mois = "-may-";}
				if (mois.equals("06"))	{mois = "-jun-";}
				if (mois.equals("07"))	{mois = "-jul-";}
				if (mois.equals("08"))	{mois = "-aug-";}
				if (mois.equals("09"))	{mois = "-sep-";}
				if (mois.equals("10"))	{mois = "-oct-";}
				if (mois.equals("11"))	{mois = "-nov-";}
				if (mois.equals("12"))	{mois = "-dec-";}
				
				date=jour+mois+annee;					// nouveau format de la date 
				String motDePasse = date;				
				passwordd= stripAccents(passwordd); 	// on retire les accents
					
				if(motDePasse.equalsIgnoreCase(passwordd))	// connexion réussit
				{	new accueil().setVisible(true);
					this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null,"Mot de passe incorrect ! ","Error",1);
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"Login incorrect ! ","Error",1);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	private JTextField getLogin() {
		if (login == null) {
			login = new JTextField();
			login.setBounds(145, 82, 116, 22);
			login.setColumns(10);
		}
		return login;
	}
	private String stripAccents(String data){
	    data = Normalizer.normalize(data, Normalizer.Form.NFD);
	    return data = data.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	private JLabel getLblLogin() {
		if (lblLogin == null) {
			lblLogin = new JLabel("login : ");
			lblLogin.setBounds(89, 85, 56, 16);
		}
		return lblLogin;
	}
	
	
	private JLabel getLblMotDePass() {
		if (lblMotDePass == null) {
			lblMotDePass = new JLabel("mot de passe : ");
			lblMotDePass.setBounds(43, 120, 104, 16);
		}
		return lblMotDePass;
	}
	private JTextField getTitre() {
		if (titre == null) {
			titre = new JTextField();
			titre.setText("Authentification");
			titre.setForeground(Color.WHITE);
			titre.setFont(new Font("Tahoma", Font.BOLD, 16));
			titre.setColumns(10);
			titre.setBackground(new Color(100, 149, 237));
			titre.setBounds(0, -4, 432, 58);
			titre.setEditable(false);
		}
		return titre;
	}
	private JTextField getTextField_1() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText("");
			textField.setForeground(Color.WHITE);
			textField.setFont(new Font("Tahoma", Font.BOLD, 16));
			textField.setColumns(10);
			textField.setBackground(new Color(100, 149, 237));
			textField.setBounds(0, 197, 432, 58);
		}
		return textField;
	}
	private JButton getOk() {
		if (ok == null) {
			ok = new JButton("Se Connecter");
			ok.setMnemonic(KeyEvent.VK_ENTER); 
			getRootPane().setDefaultButton(ok); 
			//ok.setMnemonic(KeyEvent.VK_ENTER);
			ok.addActionListener(new ActionListener() {
				 public void actionPerformed(java.awt.event.ActionEvent evt) {
		                validerActionPerformed(evt);
				}
			});
			ok.setBounds(272, 152, 116, 25);
		}
		return ok;
	}
	private JPasswordField getPass() {
		if (pass == null) {
			pass = new JPasswordField();
			pass.setBounds(145, 117, 116, 22);
		}
		return pass;
	}
}
