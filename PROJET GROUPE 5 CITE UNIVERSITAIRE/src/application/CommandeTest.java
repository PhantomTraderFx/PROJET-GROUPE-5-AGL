package application;

import java.util.Date;


public class CommandeTest {
	private Integer idCommande;
	private String etudiant;
	private String prenom_etudiant;
	private Date datecommande;
	private String platcmdi;
	private String platcsoir;
	
	
	public CommandeTest() {

	}
	
	
	public CommandeTest(Integer idCommande, String etudiant, String prenom_etudiant, Date datecommande, String platcmdi,
			String platcsoir) {
		super();
		this.idCommande = idCommande;
		this.etudiant = etudiant;
		this.prenom_etudiant = prenom_etudiant;
		this.datecommande = datecommande;
		this.platcmdi = platcmdi;
		this.platcsoir = platcsoir;
	}
	public Integer getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}
	public String getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}
	public String getPrenom_etudiant() {
		return prenom_etudiant;
	}
	public void setPrenom_etudiant(String prenom_etudiant) {
		this.prenom_etudiant = prenom_etudiant;
	}
	public Date getDatecommande() {
		return datecommande;
	}
	public void setDatecommande(Date datecommande) {
		this.datecommande = datecommande;
	}
	public String getPlatcmdi() {
		return platcmdi;
	}
	public void setPlatcmdi(String platcmdi) {
		this.platcmdi = platcmdi;
	}
	public String getPlatcsoir() {
		return platcsoir;
	}
	public void setPlatcsoir(String platcsoir) {
		this.platcsoir = platcsoir;
	}
	
}
