package com.gecko.jee.enterprise.myskills.hrpersistence.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the formation database table.
 * 
 */
@Entity
@NamedQuery(name="Formation.findAll", query="SELECT f FROM Formation f")
public class Formation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_formation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFormation;

	@Column(name="date_debut")
	private Timestamp dateDebut;

	@Column(name="date_fin")
	private Timestamp dateFin;

	private String description;

	@Column(name="nb_heure")
	private String nbHeure;

	private String nom;

	//bi-directional many-to-one association to EntretienEvaluationAction
	@OneToMany(mappedBy="formation")
	private List<EntretienEvaluationAction> entretienEvaluationActions;

	//bi-directional many-to-one association to TypeFormation
	@ManyToOne
	@JoinColumn(name="fk_type_formation_id")
	private TypeFormation typeFormation;

	//bi-directional many-to-many association to Mskuser
	@ManyToMany(mappedBy="formations")
	private List<Mskuser> mskusers;

	public Formation() {
	}

	public Long getIdFormation() {
		return this.idFormation;
	}

	public void setIdFormation(Long idFormation) {
		this.idFormation = idFormation;
	}

	public Timestamp getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Timestamp getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNbHeure() {
		return this.nbHeure;
	}

	public void setNbHeure(String nbHeure) {
		this.nbHeure = nbHeure;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<EntretienEvaluationAction> getEntretienEvaluationActions() {
		return this.entretienEvaluationActions;
	}

	public void setEntretienEvaluationActions(List<EntretienEvaluationAction> entretienEvaluationActions) {
		this.entretienEvaluationActions = entretienEvaluationActions;
	}

	public EntretienEvaluationAction addEntretienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction) {
		getEntretienEvaluationActions().add(entretienEvaluationAction);
		entretienEvaluationAction.setFormation(this);

		return entretienEvaluationAction;
	}

	public EntretienEvaluationAction removeEntretienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction) {
		getEntretienEvaluationActions().remove(entretienEvaluationAction);
		entretienEvaluationAction.setFormation(null);

		return entretienEvaluationAction;
	}

	public TypeFormation getTypeFormation() {
		return this.typeFormation;
	}

	public void setTypeFormation(TypeFormation typeFormation) {
		this.typeFormation = typeFormation;
	}

	public List<Mskuser> getMskusers() {
		return this.mskusers;
	}

	public void setMskusers(List<Mskuser> mskusers) {
		this.mskusers = mskusers;
	}

}