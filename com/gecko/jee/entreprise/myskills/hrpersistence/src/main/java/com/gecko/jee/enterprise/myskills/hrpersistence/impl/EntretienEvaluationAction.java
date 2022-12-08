package com.gecko.jee.enterprise.myskills.hrpersistence.impl;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * The persistent class for the entretien_evaluation_action database table.
 * 
 */
@Entity
@Table(name="entretien_evaluation_action")
@NamedQuery(name="EntretienEvaluationAction.findAll", query="SELECT e FROM EntretienEvaluationAction e")
public class EntretienEvaluationAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_entretien_evaluation_action")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEntretienEvaluationAction;

	@Column(name="date_limite")
	private Timestamp dateLimite;

	private String description;

	private String statut;

	//bi-directional many-to-one association to EntretienEvaluation
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_entretien_evaluation_id")
	private EntretienEvaluation entretienEvaluation;

	//bi-directional many-to-one association to Formation
	@ManyToOne
	@JoinColumn(name="fk_formation_id")
	private Formation formation;

	//bi-directional many-to-one association to TypeEntretienEvaluationAction
	@ManyToOne
	@JoinColumn(name="fk_type_entretien_evaluation_action_id")
	private TypeEntretienEvaluationAction typeEntretienEvaluationAction;

	public EntretienEvaluationAction() {
	}

	/**
	 * @param dateLimite
	 * @param description
	 * @param statut
	 */
	public EntretienEvaluationAction(Timestamp dateLimite, String description, String statut) {
		this.dateLimite = dateLimite;
		this.description = description;
		this.statut = statut;
	}

	/**
	 * @param dateLimite
	 * @param description
	 * @param statut
	 * @param entretienEvaluation
	 */
	public EntretienEvaluationAction(Timestamp dateLimite, String description, String statut,
			EntretienEvaluation entretienEvaluation) {
		super();
		this.dateLimite = dateLimite;
		this.description = description;
		this.statut = statut;
		this.entretienEvaluation = entretienEvaluation;
	}
	

	public Long getIdEntretienEvaluationAction() {
		return this.idEntretienEvaluationAction;
	}

	public void setIdEntretienEvaluationAction(Long idEntretienEvaluationAction) {
		this.idEntretienEvaluationAction = idEntretienEvaluationAction;
	}

	public Timestamp getDateLimite() {
		return this.dateLimite;
	}

	public void setDateLimite(Timestamp dateLimite) {
		this.dateLimite = dateLimite;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public EntretienEvaluation getEntretienEvaluation() {
		return this.entretienEvaluation;
	}

	public void setEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		this.entretienEvaluation = entretienEvaluation;
	}

	public Formation getFormation() {
		return this.formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public TypeEntretienEvaluationAction getTypeEntretienEvaluationAction() {
		return this.typeEntretienEvaluationAction;
	}

	public void setTypeEntretienEvaluationAction(TypeEntretienEvaluationAction typeEntretienEvaluationAction) {
		this.typeEntretienEvaluationAction = typeEntretienEvaluationAction;
	}

	@Override
	public String toString() {
		return "EntretienEvaluationAction [idEntretienEvaluationAction=" + idEntretienEvaluationAction + ", dateLimite="
				+ dateLimite + ", description=" + description + ", statut=" + statut + ", entretienEvaluation="
				+ entretienEvaluation + ", formation=" + formation + ", typeEntretienEvaluationAction="
				+ typeEntretienEvaluationAction + "]";
	}

}