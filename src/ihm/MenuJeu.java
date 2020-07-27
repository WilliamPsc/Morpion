package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import jeu.MorpionG;
import jeu.MorpionGAuto;

public class MenuJeu {
	private JFrame frame;
	private JPanel panel;
	private JButton nouv;
	private static JLabel joueur;
	
	private int sizeW = 1000;
	private int sizeH = 800;

	public MenuJeu(int type, int taille) {		
		// JFrame principale
		frame = new JFrame();
		frame.setTitle("Morpion");
		frame.setSize(sizeW, sizeH);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// Menu de l'interface
		MenuHaut menu = new MenuHaut();
		frame.setJMenuBar(menu);

		joueur = new JLabel("Joueur actuel : Joueur1 (ROUGE)");
		joueur.setHorizontalAlignment(SwingConstants.CENTER);
		joueur.setPreferredSize(new Dimension(100,40));
		frame.add(joueur, BorderLayout.NORTH);
		
		dessinerGrille(type, taille);
		
		nouv = new JButton("Nouvelle partie");
		nouv.setPreferredSize(new Dimension(100,60));
		nouv.setMaximumSize(new Dimension(100,60));
		frame.add(nouv, BorderLayout.SOUTH);
		
		nouv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				new Menu();
			}
		});
		

		// Affichage de l'IHM
		frame.setVisible(true);
	}

	private void dessinerGrille(int type, int taille) {
		// Grille de jeu
		if (type == 0) {
			MorpionGAuto mga = new MorpionGAuto(taille, panel, sizeW, sizeH - 50);
			frame.getContentPane().add(mga, BorderLayout.CENTER);
		}

		if (type == 1) {
			MorpionG mg = new MorpionG(taille, panel, sizeW, sizeH - 50);
			frame.getContentPane().add(mg, BorderLayout.CENTER);
		}
	}
	
	public static void setJoueur(String nom) {
		joueur.setText(nom);
	}
}
