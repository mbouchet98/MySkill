package com.gecko.jee.enterprise.myskills.hrpersistence.impl;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the plan_carriere_objectif database table.
 * 
 */
@Entity
@Table(name="plan_carriere_objectif")
@NamedQuery(name="PlanCarriereObjectif.findAll", query="SELECT p FROM PlanCarriereObjectif p")
public class PlanCarriereObjectif implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_objectif")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idObjectif;

	private String description;

	private String nom;

	private String statut;

	//bi-directional many-to-one association to PlanCarriere
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="fk_plan_carriere_id")
	private PlanCarriere planCarriere;

	public PlanCarriereObjectif() {
	}

	/**
	 * @param description
	 * @param nom
	 * @param statut
	 */
	public PlanCarriereObjectif(String description, String nom, String statut) {
		super();
		this.description = description;
		this.nom = nom;
		this.statut = statut;
	}

	public Long getIdObjectif() {
		return this.idObjectif;
	}

	public void setIdObjectif(Long idObjectif) {
		this.idObjectif = idObjectif;
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

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public PlanCarriere getPlanCarriere() {
		return this.planCarriere;
	}

	public void setPlanCarriere(PlanCarriere planCarriere) {
		this.planCarriere = planCarriere;
	}

}