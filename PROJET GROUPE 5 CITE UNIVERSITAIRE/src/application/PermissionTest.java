package application;

import java.util.Date;

public class PermissionTest {
	private Integer idPermission;
	private String etudiant;
	private String Prenom;
	private String motifSorti;
	private Date dateSorti;
	private Date heureArrive;
	
	
	public PermissionTest() {
		
	}
	
	
	public PermissionTest(Integer idPermission, String etudiant, String prenom, String motifSorti, Date dateSorti,
			Date heureSorti, Date heureArrive) {
		super();
		this.idPermission = idPermission;
		this.etudiant = etudiant;
		Prenom = prenom;
		this.motifSorti = motifSorti;
		this.dateSorti = dateSorti;
		this.heureArrive = heureArrive;
	}
	public Integer getIdPermission() {
		return idPermission;
	}
	public void setIdPermission(Integer idPermission) {
		this.idPermission = idPermission;
	}
	public String getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getMotifSorti() {
		return motifSorti;
	}
	public void setMotifSorti(String motifSorti) {
		this.motifSorti = motifSorti;
	}
	public Date getDateSorti() {
		return dateSorti;
	}
	public void setDateSorti(Date dateSorti) {
		this.dateSorti = dateSorti;
	}
	public Date getHeureArrive() {
		return heureArrive;
	}
	public void setHeureArrive(Date heureArrive) {
		this.heureArrive = heureArrive;
	}
	
	
}
