package com.gecko.jee.enterprise.myskills.hrpersistence.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the plan_carriere database table.
 * 
 */
@Entity
@Table(name="plan_carriere")
@NamedQuery(name="PlanCarriere.findAll", query="SELECT p FROM PlanCarriere p")
public class PlanCarriere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_plan_carriere")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlanCarriere;

	private String description;

	private String nom;

	//bi-directional many-to-many association to Mskuser
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="mskuer_plan_carriere"
		, joinColumns={
			@JoinColumn(name="fk_plan_carriere_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="fk_mskuser_id")
			}
		)
	private List<Mskuser> mskusers;

	//bi-directional many-to-one association to PlanCarriereObjectif
	@OneToMany(mappedBy = "planCarriere", cascade = CascadeType.PERSIST)
	private List<PlanCarriereObjectif> planCarriereObjectifs;

	public PlanCarriere() {
	}

	/**
	 * @param description
	 * @param nom
	 */
	public PlanCarriere(String description, String nom) {
		super();
		this.description = description;
		this.nom = nom;
	}

	public Long getIdPlanCarriere() {
		return this.idPlanCarriere;
	}

	public void setIdPlanCarriere(Long idPlanCarriere) {
		this.idPlanCarriere = idPlanCarriere;
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

	public List<PlanCarriereObjectif> getPlanCarriereObjectifs() {
		return this.planCarriereObjectifs;
	}

	public void setPlanCarriereObjectifs(List<PlanCarriereObjectif> planCarriereObjectifs) {
		this.planCarriereObjectifs = planCarriereObjectifs;
	}

	public PlanCarriereObjectif addPlanCarriereObjectif(PlanCarriereObjectif planCarriereObjectif) {
		getPlanCarriereObjectifs().add(planCarriereObjectif);
		planCarriereObjectif.setPlanCarriere(this);

		return planCarriereObjectif;
	}

	public PlanCarriereObjectif removePlanCarriereObjectif(PlanCarriereObjectif planCarriereObjectif) {
		getPlanCarriereObjectifs().remove(planCarriereObjectif);
		planCarriereObjectif.setPlanCarriere(null);

		return planCarriereObjectif;
	}

	@Override
	public String toString() {
		return "PlanCarriere [idPlanCarriere=" + idPlanCarriere + ", description=" + description + ", nom=" + nom
				+ ", mskusers=" + mskusers + ", planCarriereObjectifs=" + planCarriereObjectifs + "]";
	}

}