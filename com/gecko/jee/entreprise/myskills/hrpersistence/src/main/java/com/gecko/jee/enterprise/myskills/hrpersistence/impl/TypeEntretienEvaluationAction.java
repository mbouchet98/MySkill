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
 * The persistent class for the type_entretien_evaluation_action database table.
 * 
 */
@Entity
@Table(name="type_entretien_evaluation_action")
@NamedQuery(name="TypeEntretienEvaluationAction.findAll", query="SELECT t FROM TypeEntretienEvaluationAction t")
public class TypeEntretienEvaluationAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_type_entretien_evaluation_action")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTypeEntretienEvaluationAction;

	private String description;

	private String libelle;

	//bi-directional many-to-one association to EntretienEvaluationAction
	@OneToMany(mappedBy="typeEntretienEvaluationAction")
	private List<EntretienEvaluationAction> entretienEvaluationActions;

	public TypeEntretienEvaluationAction() {
	}

	public Long getIdTypeEntretienEvaluationAction() {
		return this.idTypeEntretienEvaluationAction;
	}

	public void setIdTypeEntretienEvaluationAction(Long idTypeEntretienEvaluationAction) {
		this.idTypeEntretienEvaluationAction = idTypeEntretienEvaluationAction;
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

	public List<EntretienEvaluationAction> getEntretienEvaluationActions() {
		return this.entretienEvaluationActions;
	}

	public void setEntretienEvaluationActions(List<EntretienEvaluationAction> entretienEvaluationActions) {
		this.entretienEvaluationActions = entretienEvaluationActions;
	}

	public EntretienEvaluationAction addEntretienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction) {
		getEntretienEvaluationActions().add(entretienEvaluationAction);
		entretienEvaluationAction.setTypeEntretienEvaluationAction(this);

		return entretienEvaluationAction;
	}

	public EntretienEvaluationAction removeEntretienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction) {
		getEntretienEvaluationActions().remove(entretienEvaluationAction);
		entretienEvaluationAction.setTypeEntretienEvaluationAction(null);

		return entretienEvaluationAction;
	}

}