package application;

import java.util.Date;


public class CommandeTest {
	private Integer idCommande;
	private int idEtu;
	private int idMenu;
	private String nom;
	private String prenom;
	private Date datecommande;
	private String platcmdi;
	private String platcsoir;

	
	public Integer getIdCommande() {
		return this.idCommande;
	}

	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}
	
	public int getIdEtu() {
		return this.idEtu;
	}

	public void setIdEtu(int idEtu) {
		this.idEtu = idEtu;
	}

	public int getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public Date getDatecommande() {
		return this.datecommande;
	}

	public void setDatecommande(Date datecommande) {
		this.datecommande = datecommande;
	}

	public String getPlatcmdi() {
		return this.platcmdi;
	}

	public void setPlatcmdi(String platcmdi) {
		this.platcmdi = platcmdi;
	}
	
	public String getPlatcsoir() {
		return this.platcsoir;
	}

	public void setPlatcsoir(String platcsoir) {
		this.platcsoir = platcsoir;
	}

	/*public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}*/
}
