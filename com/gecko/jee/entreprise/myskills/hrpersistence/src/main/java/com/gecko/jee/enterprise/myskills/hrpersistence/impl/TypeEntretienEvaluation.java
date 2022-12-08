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
 * The persistent class for the type_entretien_evaluation database table.
 * 
 */
@Entity
@Table(name="type_entretien_evaluation")
@NamedQuery(name="TypeEntretienEvaluation.findAll", query="SELECT t FROM TypeEntretienEvaluation t")
public class TypeEntretienEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_type_entretien_evaluation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTypeEntretienEvaluation;

	private String description;

	private String libelle;

	//bi-directional many-to-one association to EntretienEvaluation
	@OneToMany(mappedBy="typeEntretienEvaluation")
	private List<EntretienEvaluation> entretienEvaluations;

	public TypeEntretienEvaluation() {
	}

	/**
	 * @param description
	 * @param libelle
	 */
	public TypeEntretienEvaluation(String description, String libelle) {
		this.description = description;
		this.libelle = libelle;
	}

	public Long getIdTypeEntretienEvaluation() {
		return this.idTypeEntretienEvaluation;
	}

	public void setIdTypeEntretienEvaluation(Long idTypeEntretienEvaluation) {
		this.idTypeEntretienEvaluation = idTypeEntretienEvaluation;
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

	public List<EntretienEvaluation> getEntretienEvaluations() {
		return this.entretienEvaluations;
	}

	public void setEntretienEvaluations(List<EntretienEvaluation> entretienEvaluations) {
		this.entretienEvaluations = entretienEvaluations;
	}

	public EntretienEvaluation addEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		getEntretienEvaluations().add(entretienEvaluation);
		entretienEvaluation.setTypeEntretienEvaluation(this);

		return entretienEvaluation;
	}

	public EntretienEvaluation removeEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		getEntretienEvaluations().remove(entretienEvaluation);
		entretienEvaluation.setTypeEntretienEvaluation(null);

		return entretienEvaluation;
	}

	@Override
	public String toString() {
		return "TypeEntretienEvaluation [idTypeEntretienEvaluation=" + idTypeEntretienEvaluation + ", description="
				+ description + ", libelle=" + libelle + ", entretienEvaluations=" + entretienEvaluations + "]";
	}

}