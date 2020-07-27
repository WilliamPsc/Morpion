package jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ihm.MenuJeu;

@SuppressWarnings("serial")
public class MorpionGAuto extends JPanel implements ActionListener {
	private int width;
	private int height;
	private String[][] grille;
	private boolean joueur;
	private boolean win;

	// JFrame
	private JButton[][] boutons;

	public MorpionGAuto(int x, JPanel panel, int sizeW, int sizeH) {
		int val = 0;
		this.width = x;
		this.height = x;
		grille = new String[this.height][this.width];
		this.joueur = true;
		this.win = false;
		boutons = new JButton[this.height][this.width];
		panel = new JPanel(new GridLayout(x, x, 5, 5));
		panel.repaint();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.grille[i][j] = "_";
				this.boutons[i][j] = new JButton();
				this.boutons[i][j].setName("" + (val));
				this.boutons[i][j].setActionCommand("bouton" + (val++));
				this.boutons[i][j].addActionListener(this);
				this.boutons[i][j].setBackground(Color.WHITE);
				this.boutons[i][j].setPreferredSize(new Dimension((sizeW - 200) / x, (sizeH - 200) / x));
				panel.add(this.boutons[i][j]);
			}
		}
		this.add(panel);
	}

	public void completerGrille(String sym, int x1, int y1) {
		this.grille[x1][y1] = sym;

		this.gagner(sym);
	}

	public void display() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print('|');
				System.out.print(this.grille[i][j]);
			}
			System.out.println('|');
		}
		System.out.println();
	}

	public String toString() {
		String res = "";

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				res += this.grille[i][j];
			}
		}

		return res;
	}

	public void gagner(String sym) {
		int x = this.height;
		int iter = 0;
		String res = "";
		String[] finalRes = new String[(this.height * 2) + 2];
		String val = "";
		int nulle = 0;

		for (int i = 0; i < x; i++) {
			res += sym;
		}

		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				val += this.grille[i][j];
			}
			finalRes[iter] = val;
			iter++;
			val = "";
		}

		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				val += this.grille[j][i];
			}
			finalRes[iter] = val;
			iter++;
			val = "";
		}

		for (int i = 0; i < this.height; i++) {
			val += this.grille[i][i];
		}
		finalRes[iter] = val;
		iter++;
		val = "";

		int j = 0;
		for (int i = this.height - 1; i >= 0; i--) {
			val += this.grille[i][j];
			j++;
		}
		finalRes[iter] = val;
		iter++;
		val = "";

		for (int i = 0; i < finalRes.length; i++) {
			if (finalRes[i].contains(res)) {
				if (this.joueur == false) {
					System.out.println("Joueur 1 a gagné !");
					MenuJeu.setJoueur("Joueur 1 a gagné !");
					for (int i2 = 0; i2 < boutons.length; i2++) {
						for (int j2 = 0; j2 < boutons[i2].length; j2++) {
							boutons[i2][j2].setEnabled(false);
						}
					}
					this.win = true;
				} else {
					System.out.println("Joueur 2 a gagné !");
					MenuJeu.setJoueur("Joueur 2 a gagné !");
					for (int i2 = 0; i2 < 3; i2++) {
						for (int j2 = 0; j2 < 3; j2++) {
							boutons[i2][j2].setEnabled(false);
						}
					}
					this.win = true;
				}
				return;
			}

			if (!finalRes[i].contains("_")) {
				nulle++;

				if (nulle == ((this.height * 2) + 2)) {
					System.out.println("Partie nulle !");
					MenuJeu.setJoueur("Partie nulle !");
					this.win = true;
				}
			}
		}

	}

	private void switchCase(String bouton, String sym) {
		int x = -1;
		int y = -1;
		int incrI = 0;

		int num = Integer.parseInt(bouton);
		int tailleX = boutons.length;
		int tailleY = boutons[0].length;

		if (incrI == num) {
			x = 0;
			y = 0;
		} else {
			label: for (int i = 0; i < tailleX; i++) {
				for (int j = 0; j < tailleY; j++) {
					if (incrI == num) {
						x = i;
						y = j;
						break label;
					}
					incrI++;
				}
			}
		}

		completerGrille(sym, x, y);
		this.display();

	}

	private void jeuIA() {
		int x = 0;
		int y = 0;
		int incr = 0;
		x = (int) (Math.random() * (this.width - 0));

		y = (int) (Math.random() * (this.height - 0));

		while (this.boutons[x][y].isEnabled() == false) {
			x = (int) (Math.random() * (this.width - 0));
			y = (int) (Math.random() * (this.height - 0));
			/*System.out
					.println("x = " + x + " | y = " + y + " | this.boutons[y][x] = " + this.boutons[x][y].isEnabled());*/
		}

		this.boutons[x][y].setBackground(Color.RED);
		this.boutons[x][y].setEnabled(false);

		label: for (int i = 0; i < this.boutons.length; i++) {
			for (int j = 0; j < this.boutons[0].length; j++) {
				if (this.boutons[i][j] == this.boutons[x][y]) {
					/*System.out.println(
							"i = " + i + " | j = " + j + " | this.boutons[i][j] = " + this.boutons[i][j].isEnabled());*/
					break label;
				}
				incr++;
			}
		}
		switchCase("" + incr, "o");

		MenuJeu.setJoueur("Joueur actuel : Joueur2 (Jaune)");
		joueur = !joueur;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String recuperationBouton = ((JButton) arg0.getSource()).getName();

		//System.out.println(recuperationBouton);

		if (joueur) {
			((JButton) arg0.getSource()).setBackground(Color.YELLOW);
			((JButton) arg0.getSource()).setEnabled(false);
			MenuJeu.setJoueur("Joueur actuel : Joueur1 (ROUGE)");
			switchCase(recuperationBouton, "x");
			joueur = !joueur;

			if (!this.win)
				jeuIA();
		}
	}
}