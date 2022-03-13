package com.G5.model;
// Generated 14 mars 2022, 00:29:40 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Commande generated by hbm2java
 */
@Entity
@Table(name = "commande", catalog = "bd_cite2")
public class Commande implements java.io.Serializable {

	private Integer idCommande;
	private int idEtu;
	private int idMenu;
	private Date datecommande;

	public Commande() {
	}

	public Commande(int idEtu, int idMenu) {
		this.idEtu = idEtu;
		this.idMenu = idMenu;
	}

	public Commande(int idEtu, int idMenu, Date datecommande) {
		this.idEtu = idEtu;
		this.idMenu = idMenu;
		this.datecommande = datecommande;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_COMMANDE", unique = true, nullable = false)
	public Integer getIdCommande() {
		return this.idCommande;
	}

	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}

	@Column(name = "ID_ETU", nullable = false)
	public int getIdEtu() {
		return this.idEtu;
	}

	public void setIdEtu(int idEtu) {
		this.idEtu = idEtu;
	}

	@Column(name = "ID_MENU", nullable = false)
	public int getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATECOMMANDE", length = 10)
	public Date getDatecommande() {
		return this.datecommande;
	}

	public void setDatecommande(Date datecommande) {
		this.datecommande = datecommande;
	}

}
