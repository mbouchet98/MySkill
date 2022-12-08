package com.gecko.jee.enterprise.myskills.hrpersistence.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the entretien_evaluation database table.
 * 
 */
@Entity
@Table(name="entretien_evaluation")
@NamedQuery(name="EntretienEvaluation.findAll", query="SELECT e FROM EntretienEvaluation e")
public class EntretienEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_entretien_evaluation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEntretienEvaluation;

	@Column(name="compte_rendu")
	private String compteRendu;

	@Column(name="date_creation")
	private Timestamp dateCreation;

	@Column(name="fk_liferay_id")
	private String fkLiferayId;

	@Column(name="heure_debut")
	private Timestamp heureDebut;

	@Column(name="heure_fin")
	private Timestamp heureFin;

	//bi-directional many-to-one association to Mskuser
	@ManyToOne
	@JoinColumn(name="fk_mskuser_id")
	private Mskuser mskuser;

	//bi-directional many-to-one association to TypeEntretienEvaluation
	@ManyToOne
	@JoinColumn(name="fk_type_entretien_evaluation_id")
	private TypeEntretienEvaluation typeEntretienEvaluation;

	//bi-directional many-to-one association to EntretienEvaluationAction
	@OneToMany(mappedBy = "entretienEvaluation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EntretienEvaluationAction> entretienEvaluationActions;

	public EntretienEvaluation() {
	}

	/**
	 * @param compteRendu
	 * @param dateCreation
	 * @param fkLiferayId
	 * @param heureDebut
	 * @param heureFin
	 * @param mskuser
	 */
	public EntretienEvaluation(String compteRendu, Timestamp dateCreation, String fkLiferayId, Timestamp heureDebut,
			Timestamp heureFin, Mskuser mskuser) {
		this.compteRendu = compteRendu;
		this.dateCreation = dateCreation;
		this.fkLiferayId = fkLiferayId;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.mskuser = mskuser;
	}

	/**
	 * @param dateCreation
	 * @param fkLiferayId
	 * @param heureDebut
	 * @param heureFin
	 * @param mskuser
	 */
	public EntretienEvaluation(Timestamp dateCreation, String fkLiferayId, Timestamp heureDebut, Timestamp heureFin,
			Mskuser mskuser) {
		this.dateCreation = dateCreation;
		this.fkLiferayId = fkLiferayId;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.mskuser = mskuser;
	}

	/**
	 * @param dateCreation
	 * @param fkLiferayId
	 * @param heureDebut
	 * @param heureFin
	 * @param mskuser
	 * @param typeEntretienEvaluation
	 */
	public EntretienEvaluation(Timestamp dateCreation, String fkLiferayId, Timestamp heureDebut, Timestamp heureFin,
			Mskuser mskuser, TypeEntretienEvaluation typeEntretienEvaluation) {
		super();
		this.dateCreation = dateCreation;
		this.fkLiferayId = fkLiferayId;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.mskuser = mskuser;
		this.typeEntretienEvaluation = typeEntretienEvaluation;
	}

	/**
	 * 
	 * @param compteRendu
	 * @param dateCreation
	 * @param fkLiferayId
	 * @param heureDebut
	 * @param heureFin
	 * @param mskuser
	 * @param typeEntretienEvaluation
	 */
	public EntretienEvaluation(String compteRendu, Timestamp dateCreation, String fkLiferayId, Timestamp heureDebut,
			Timestamp heureFin, Mskuser mskuser, TypeEntretienEvaluation typeEntretienEvaluation) {
		super();
		this.dateCreation = dateCreation;
		this.fkLiferayId = fkLiferayId;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.mskuser = mskuser;
		this.typeEntretienEvaluation = typeEntretienEvaluation;
	}
	/**
	 * @param dateCreation
	 */
	public EntretienEvaluation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Long getIdEntretienEvaluation() {
		return this.idEntretienEvaluation;
	}

	public void setIdEntretienEvaluation(Long idEntretienEvaluation) {
		this.idEntretienEvaluation = idEntretienEvaluation;
	}

	public String getCompteRendu() {
		return this.compteRendu;
	}

	public void setCompteRendu(String compteRendu) {
		this.compteRendu = compteRendu;
	}

	public Timestamp getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getFkLiferayId() {
		return this.fkLiferayId;
	}

	public void setFkLiferayId(String fkLiferayId) {
		this.fkLiferayId = fkLiferayId;
	}

	public Timestamp getHeureDebut() {
		return this.heureDebut;
	}

	public void setHeureDebut(Timestamp heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Timestamp getHeureFin() {
		return this.heureFin;
	}

	public void setHeureFin(Timestamp heureFin) {
		this.heureFin = heureFin;
	}

	public Mskuser getMskuser() {
		return this.mskuser;
	}

	public void setMskuser(Mskuser mskuser) {
		this.mskuser = mskuser;
	}

	public TypeEntretienEvaluation getTypeEntretienEvaluation() {
		return this.typeEntretienEvaluation;
	}

	public void setTypeEntretienEvaluation(TypeEntretienEvaluation typeEntretienEvaluation) {
		this.typeEntretienEvaluation = typeEntretienEvaluation;
	}

	public List<EntretienEvaluationAction> getEntretienEvaluationActions() {
		return this.entretienEvaluationActions;
	}

	public void setEntretienEvaluationActions(List<EntretienEvaluationAction> entretienEvaluationActions) {
		this.entretienEvaluationActions = entretienEvaluationActions;
	}

	public EntretienEvaluationAction addEntretienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction) {
		getEntretienEvaluationActions().add(entretienEvaluationAction);
		entretienEvaluationAction.setEntretienEvaluation(this);

		return entretienEvaluationAction;
	}

	public EntretienEvaluationAction removeEntretienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction) {
		getEntretienEvaluationActions().remove(entretienEvaluationAction);
		entretienEvaluationAction.setEntretienEvaluation(null);

		return entretienEvaluationAction;
	}

	@Override
	public String toString() {
		return "EntretienEvaluation [idEntretienEvaluation=" + idEntretienEvaluation + ", compteRendu=" + compteRendu
				+ ", dateCreation=" + dateCreation + ", fkLiferayId=" + fkLiferayId + ", heureDebut=" + heureDebut
				+ ", heureFin=" + heureFin + ", mskuser=" + mskuser + ", typeEntretienEvaluation="
				+ typeEntretienEvaluation + ", entretienEvaluationActions=" + entretienEvaluationActions + "]";
	}

}