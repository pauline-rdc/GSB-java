import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import java.awt.Font;

import javax.swing.JTextField;

public class accueil extends authentification {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton compteRendu;
	private static JButton visiteurs;
	private JButton praticiens;
	private JButton medicaments;
	private JButton deconnect;
	private JTextPane txtpnGestionDesComptes;
	private JTextField textField;
	private JButton autreRapports;
	private JTextField roleUser;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accueil frame = new accueil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public accueil() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 297);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextField_1_1());
		contentPane.add(getCompteRendu());
		contentPane.add(getVisiteurs());
		contentPane.add(getPraticiens());
		contentPane.add(getMedicaments());
		contentPane.add(getDeconnect());
		contentPane.add(getTxtpnGestionDesComptes());
		contentPane.add(getTextField());
		contentPane.add(getAutreRapports());
		if (role.equals("Visiteur")){
			visiteurs.setVisible(false);
			autreRapports.setVisible(false);
		}else{
			visiteurs.setVisible(true);
			autreRapports.setVisible(true);
		}
		System.out.println("matricule :"); 
   	 System.out.println(matricule); 
	}
	private JButton getCompteRendu() {
		if (compteRendu == null) {
			compteRendu = new JButton("Rapports de visites");
			compteRendu.setBackground(new Color(255, 255, 255));
			compteRendu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new rapport_visite().setVisible(true);
				}
			});
			compteRendu.setBounds(124, 137, 155, 25);
		}
		return compteRendu;
	}
	private JButton getVisiteurs() {
		if (visiteurs == null) {
			visiteurs = new JButton("Visiteurs");
			visiteurs.setBackground(new Color(255, 255, 255));
			visiteurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new visiteur().setVisible(true); 
				}
			});
			visiteurs.setBounds(292, 176, 155, 25);
		}
		return visiteurs;
	}
	private JButton getPraticiens() {
		if (praticiens == null) {
			praticiens = new JButton("Praticiens");
			praticiens.setBackground(new Color(255, 255, 255));
			praticiens.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new praticiens().setVisible(true);
				}
			});
			praticiens.setBounds(292, 99, 155, 25);
		}
		return praticiens;
	}
	private JButton getMedicaments() {
		if (medicaments == null) {
			medicaments = new JButton("Medicaments");
			medicaments.setBackground(new Color(255, 255, 255));
			medicaments.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new medicaments().setVisible(true);
				}
			});
			medicaments.setBounds(292, 137, 155, 25);
		}
		return medicaments;
	}
	private JButton getDeconnect() {
		if (deconnect == null) {
			deconnect = new JButton("Se Déconnecter");
			deconnect.setBackground(new Color(255, 255, 255));
			deconnect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					role="";
					matricule="";
					new authentification().setVisible(true);
					dispose();
				}
			});
			deconnect.setBounds(292, 214, 155, 25);
		}
		return deconnect;
	}
	private JTextPane getTxtpnGestionDesComptes() {
		if (txtpnGestionDesComptes == null) {
			txtpnGestionDesComptes = new JTextPane();
			txtpnGestionDesComptes.setFont(new Font("Times New Roman", Font.BOLD, 24));
			txtpnGestionDesComptes.setForeground(new Color(255, 255, 255));
			txtpnGestionDesComptes.setBackground(new Color(100, 149, 237));
			txtpnGestionDesComptes.setText("Gestion des comptes rendus");
			txtpnGestionDesComptes.setBounds(98, 0, 412, 62);
			txtpnGestionDesComptes.setEditable(false);
		}
		return txtpnGestionDesComptes;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBackground(new Color(100, 149, 237));
			textField.setBounds(0, 0, 99, 250);
			textField.setColumns(10);
			textField.setEditable(false);
		}
		return textField;
	}
	private JButton getAutreRapports() {
		if (autreRapports == null) {
			autreRapports = new JButton("Autres rapports");
			autreRapports.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (role.equals("Délégué")){
						new rapport_visite2().setVisible(true);
					}else{
						new rapport_visite3().setVisible(true);
					}
					
				}
			});
			autreRapports.setBackground(Color.WHITE);
			autreRapports.setBounds(124, 175, 155, 25);
		}
		return autreRapports;
	}
	private JTextField getTextField_1_1() {
		if (roleUser == null) {
			roleUser = new JTextField(role);
			roleUser.setBounds(124, 86, 155, 38);
			roleUser.setColumns(20);
			roleUser.setEditable(false);
			roleUser.setFont(new Font("Times New Roman", Font.BOLD, 15));
		}
		return roleUser;
	}
}
