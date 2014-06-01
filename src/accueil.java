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
	private JButton visiteurs;
	private JButton praticiens;
	private JButton medicaments;
	private JButton deconnect;
	private JTextPane txtpnGestionDesComptes;
	private JTextField textField;
	
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
		setBounds(100, 100, 528, 347);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCompteRendu());
		contentPane.add(getVisiteurs());
		contentPane.add(getPraticiens());
		contentPane.add(getMedicaments());
		contentPane.add(getDeconnect());
		contentPane.add(getTxtpnGestionDesComptes());
		contentPane.add(getTextField());
		System.out.println("matricule :"); 
   	 System.out.println(matricule); 
	}
	private JButton getCompteRendu() {
		if (compteRendu == null) {
			compteRendu = new JButton("Comptes Rendus");
			compteRendu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new rapport_visite().setVisible(true);
				}
			});
			compteRendu.setBounds(228, 97, 155, 25);
		}
		return compteRendu;
	}
	private JButton getVisiteurs() {
		if (visiteurs == null) {
			visiteurs = new JButton("Visiteurs");
			visiteurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new visiteur().setVisible(true); 
				}
			});
			visiteurs.setBounds(228, 135, 155, 25);
		}
		return visiteurs;
	}
	private JButton getPraticiens() {
		if (praticiens == null) {
			praticiens = new JButton("Praticiens");
			praticiens.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new praticiens().setVisible(true);
				}
			});
			praticiens.setBounds(228, 173, 155, 25);
		}
		return praticiens;
	}
	private JButton getMedicaments() {
		if (medicaments == null) {
			medicaments = new JButton("Medicaments");
			medicaments.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new medicaments().setVisible(true);
				}
			});
			medicaments.setBounds(228, 212, 155, 25);
		}
		return medicaments;
	}
	private JButton getDeconnect() {
		if (deconnect == null) {
			deconnect = new JButton("Quitter");
			deconnect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			deconnect.setBounds(228, 250, 155, 25);
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
			txtpnGestionDesComptes.setBounds(93, 0, 404, 62);
			txtpnGestionDesComptes.setEditable(false);
		}
		return txtpnGestionDesComptes;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBackground(new Color(100, 149, 237));
			textField.setBounds(0, -4, 93, 306);
			textField.setColumns(10);
			textField.setEditable(false);
		}
		return textField;
	}
}
