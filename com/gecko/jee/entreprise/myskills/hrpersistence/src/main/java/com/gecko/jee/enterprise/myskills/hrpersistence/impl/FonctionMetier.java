package com.gecko.jee.enterprise.myskills.hrpersistence.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the fonction_metier database table.
 * 
 */
@Entity
@Table(name="fonction_metier")
@NamedQuery(name="FonctionMetier.findAll", query="SELECT f FROM FonctionMetier f")
public class FonctionMetier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_fonction_metier")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFonctionMetier;

	private String description;

	private String nom;

	//bi-directional many-to-one association to Mskuser
	@OneToMany(mappedBy="fonctionMetier")
	private List<Mskuser> mskusers;

	public FonctionMetier() {
	}

	public Long getIdFonctionMetier() {
		return this.idFonctionMetier;
	}

	public void setIdFonctionMetier(Long idFonctionMetier) {
		this.idFonctionMetier = idFonctionMetier;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Mskuser> getMskusers() {
		return this.mskusers;
	}

	public void setMskusers(List<Mskuser> mskusers) {
		this.mskusers = mskusers;
	}

	public Mskuser addMskuser(Mskuser mskuser) {
		getMskusers().add(mskuser);
		mskuser.setFonctionMetier(this);

		return mskuser;
	}

	public Mskuser removeMskuser(Mskuser mskuser) {
		getMskusers().remove(mskuser);
		mskuser.setFonctionMetier(null);

		return mskuser;
	}

}