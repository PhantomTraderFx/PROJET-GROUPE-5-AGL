package com.G5.model;
// Generated 17 mars 2022, 15:44:51 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Menu generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "menu", catalog = "bd_cite3")
public class Menu implements java.io.Serializable {

	private Integer idMenu;
	private String plat1mdi;
	private String jour;
	private String plat2midi;
	private String plat1soir;
	private String plat2soir;
	private Set<Commande> commandes = new HashSet<Commande>(0);

	public Menu() {
	}

	public Menu(String plat1mdi, String jour, String plat2midi, String plat1soir, String plat2soir,
			Set<Commande> commandes) {
		this.plat1mdi = plat1mdi;
		this.jour = jour;
		this.plat2midi = plat2midi;
		this.plat1soir = plat1soir;
		this.plat2soir = plat2soir;
		this.commandes = commandes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_MENU", unique = true, nullable = false)
	public Integer getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	@Column(name = "PLAT1MDI", length = 65535)
	public String getPlat1mdi() {
		return this.plat1mdi;
	}

	public void setPlat1mdi(String plat1mdi) {
		this.plat1mdi = plat1mdi;
	}

	@Column(name = "JOUR", length = 65535)
	public String getJour() {
		return this.jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	@Column(name = "PLAT2MIDI", length = 65535)
	public String getPlat2midi() {
		return this.plat2midi;
	}

	public void setPlat2midi(String plat2midi) {
		this.plat2midi = plat2midi;
	}

	@Column(name = "PLAT1SOIR", length = 65535)
	public String getPlat1soir() {
		return this.plat1soir;
	}

	public void setPlat1soir(String plat1soir) {
		this.plat1soir = plat1soir;
	}

	@Column(name = "PLAT2SOIR", length = 65535)
	public String getPlat2soir() {
		return this.plat2soir;
	}

	public void setPlat2soir(String plat2soir) {
		this.plat2soir = plat2soir;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}

}
