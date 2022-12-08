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
 * The persistent class for the type_formation database table.
 * 
 */
@Entity
@Table(name="type_formation")
@NamedQuery(name="TypeFormation.findAll", query="SELECT t FROM TypeFormation t")
public class TypeFormation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_type_formation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTypeFormation;

	private String description;

	private String libelle;

	//bi-directional many-to-one association to Formation
	@OneToMany(mappedBy="typeFormation")
	private List<Formation> formations;

	public TypeFormation() {
	}

	public Long getIdTypeFormation() {
		return this.idTypeFormation;
	}

	public void setIdTypeFormation(Long idTypeFormation) {
		this.idTypeFormation = idTypeFormation;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Formation> getFormations() {
		return this.formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public Formation addFormation(Formation formation) {
		getFormations().add(formation);
		formation.setTypeFormation(this);

		return formation;
	}

	public Formation removeFormation(Formation formation) {
		getFormations().remove(formation);
		formation.setTypeFormation(null);

		return formation;
	}

}