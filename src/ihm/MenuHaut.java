package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MenuHaut extends JMenuBar {
	
	public MenuHaut() {
		// Menu principal
		JMenu fichier = new JMenu("Fichier");
		JMenu about = new JMenu("A Propos");
		
		// Sous menu
		JMenuItem quitter = new JMenuItem("Quitter");
		JMenuItem version = new JMenuItem("Version");
		JMenuItem info = new JMenuItem("Informations");
		
		// Ajout des menus dans la barre menu
		fichier.add(quitter);
		about.add(version);
		about.add(info);
		this.add(fichier);
		this.add(about);
		
		// Action
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		version.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Version 1.0", "Version", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Auteur : William PENSEC\n"
												+ "Date : 27/07/2020\n"
												+ "Description : Jeu du morpion\n"
												+ "Licence : MIT", "A Propos", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	}
}
