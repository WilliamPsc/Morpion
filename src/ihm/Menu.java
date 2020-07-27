package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import jeu.SpringUtilities;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	private JButton jeuAuto = new JButton("Morpion contre ordinateur");
	private JButton jeuHumain = new JButton("Morpion à 2 joueurs");

	public Menu() {
		// JFrame principale
		this.setTitle("Morpion");
		this.setSize(400, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Menu de l'interface
		MenuHaut menu = new MenuHaut();
		this.setJMenuBar(menu);

		// Contenu
		JPanel layout = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new SpringLayout());

		// Spinner
		JLabel labelSpin = new JLabel("Taille de la grille : ");
		SpinnerModel value = new SpinnerNumberModel(3, // initial value
				3, // minimum value
				15, // maximum value
				1); // step
		JSpinner spinner = new JSpinner(value);
		spinner.setPreferredSize(new Dimension(30, 30));
		spinner.setMaximumSize(new Dimension(30, 30));

		jeuAuto.setHorizontalAlignment(SwingConstants.CENTER);
		jeuHumain.setHorizontalAlignment(SwingConstants.CENTER);
		labelSpin.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelSpin);
		panel.add(spinner);
		panel.add(jeuAuto);
		panel.add(jeuHumain);

		SpringUtilities.makeCompactGrid(panel, 4, 1, 100, 10, 0, 5);

		layout.add(panel, BorderLayout.CENTER);

		// Affichage de l'IHM
		this.add(layout);
		this.setVisible(true);

		// Action sur boutons
		jeuAuto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int spin = (int) spinner.getValue();
				setVisible(false);
				new MenuJeu(0, spin);
			}
		});

		jeuHumain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int spin = (int) spinner.getValue();
				setVisible(false);
				new MenuJeu(1, spin);
			}
		});
	}
}
