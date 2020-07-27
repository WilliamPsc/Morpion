package jeu;

import java.util.Scanner;

public class Morpion {
	private int width;
	private int height;
	private String[][] grille;
	private boolean win;
	private boolean joueur;
	
	public Morpion(int x) {
		this.width = x;
		this.height = x;
		grille = new String[this.height][this.width];
		this.win = false;
		this.joueur = false;
		
		System.out.println("Carré de \"" + this.width + " x " + this.height + "\"");
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.grille[i][j] = "_";
			}
		}
		this.jeu();
	}
	
	
	public void jeu() {
		while(!win) {
			if(joueur) {
				System.out.println(" === Joueur 2 ===");
				completerGrille("x");
			} else {
				System.out.println(" === Joueur 1 ===");
				completerGrille("o");
			}
			this.display();
		}
		System.exit(0);
	}
	
	
	@SuppressWarnings("resource")
	public void completerGrille(String sym) {
		Scanner in = new Scanner(System.in);
		int x = -1;
		int y = -1;
		String s;
		
		while(x <= 0 || x > this.width || y <= 0 || y > this.height) {
			System.out.print("Entrer une coordonnée (x) : ");
			s = in.nextLine();
			x = Integer.parseInt(s);
			
			System.out.print("Entrer une coordonnée (y) : ");
			s = in.nextLine();
			y = Integer.parseInt(s);
		}
		
		if(this.grille[y-1][x-1] == "_") {
			this.grille[y-1][x-1] = sym;
		} else {
			System.out.println("Case déjà complète, veuillez choisir une case libre");
			this.completerGrille(sym);
			return;
		}
		
		this.gagner(sym);
		this.joueur = !this.joueur;
	}
	
	
	public void display() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print('|');
				System.out.print(this.grille[i][j]);
			}
			System.out.println('|');
		}
		System.out.println();
	}
	
	public String toString() {
		String res = "";
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
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
		
		for(int i = 0; i < x; i++) {
			res += sym;
		}
		
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				val += this.grille[i][j];				
			}
			finalRes[iter] = val;
			iter++;
			val = "";
		}
		
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				val += this.grille[j][i];				
			}
			finalRes[iter] = val;
			iter++;
			val = "";
		}
		
		for(int i = 0; i < this.height; i++) {
			val += this.grille[i][i];
		}
		finalRes[iter] = val;
		iter++;
		val = "";
		
		int j = 0;
		for(int i = this.height - 1; i >= 0; i--) {
			val += this.grille[i][j];
			j++;
		}
		finalRes[iter] = val;
		iter++;
		val = "";
		
		for(int i = 0; i < finalRes.length; i++) {
			if(finalRes[i].contains(res)) {
				if(this.joueur == false) {
					System.out.println("Joueur 1 a gagné !");
				} else {
					System.out.println("Joueur 2 a gagné !");
				}
				this.win = true;
				return;
			}
			
			if(!finalRes[i].contains("_")) {
				nulle++;
				
				if(nulle == ((this.height * 2) + 2)) {
					System.out.println("Partie nulle !");
					System.exit(0);
				}
			}
		}
		
	}
	

}
