package com.G5.model;
// Generated 17 mars 2022, 15:44:51 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Alerte generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "alerte", catalog = "bd_cite3")
public class Alerte implements java.io.Serializable {

	private Integer idAlerte;
	private Etudiant etudiant;

	public Alerte() {
	}

	public Alerte(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_ALERTE", unique = true, nullable = false)
	public Integer getIdAlerte() {
		return this.idAlerte;
	}

	public void setIdAlerte(Integer idAlerte) {
		this.idAlerte = idAlerte;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ETUAlert", nullable = false)
	public Etudiant getEtudiant() {
		return this.etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
