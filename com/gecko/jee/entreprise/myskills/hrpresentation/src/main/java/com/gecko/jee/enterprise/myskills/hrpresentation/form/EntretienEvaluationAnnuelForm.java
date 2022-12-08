package com.gecko.jee.enterprise.myskills.hrpresentation.form;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluationAction;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;

/**
 * 
 * <b> Description : formulaire contenent les éléments d'affichage de l'écran
 * entretienEvaluationAnnuel avec le textEditor, le tableau des actions de
 * l'entretien d'évaluation et des objectifs de plans de carrière .</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
@ManagedBean
@SessionScoped
public class EntretienEvaluationAnnuelForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// textEditor
	private String textEditor;

	// dataTable Action
	private List<EntretienEvaluationAction> entretienEvaluationActions;
	private EntretienEvaluationAction entretienEvaluationAction;

	// inputText Description Action Pop-up
	private String textInputActionDesc;

	// bouton radio action statut
	private String radioStatutAction;

	// inputText Plan Carrière
	private String textInputPlanCarriereNom;
	private String textInputPlanCarriereDesc;

	// inputText Plan Carriere Objectif
	private String textInputObjectifNom;
	private String textInputObjectifDesc;

	// bouton radio Objectif statut
	private String radioStatutObjectif;

	// dataTable Objectif
	private List<PlanCarriereObjectif> planCarriereObjectifs;
	private PlanCarriereObjectif planCarriereObjectif;

	/**
	 * 
	 * @return textEditor
	 */
	public String getTextEditor() {
		return textEditor;
	}

	/**
	 * 
	 * @param textEditor
	 */
	public void setTextEditor(String textEditor) {
		this.textEditor = textEditor;
	}

	/**
	 * @return the entretienEvaluationActions
	 */
	public List<EntretienEvaluationAction> getEntretienEvaluationActions() {
		return entretienEvaluationActions;
	}

	/**
	 * @param entretienEvaluationActions the entretienEvaluationActions to set
	 */
	public void setEntretienEvaluationActions(List<EntretienEvaluationAction> entretienEvaluationActions) {
		this.entretienEvaluationActions = entretienEvaluationActions;
	}

	/**
	 * @return the entretienEvaluationAction
	 */
	public EntretienEvaluationAction getEntretienEvaluationAction() {
		return entretienEvaluationAction;
	}

	/**
	 * @param entretienEvaluationAction the entretienEvaluationAction to set
	 */
	public void setEntretienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction) {
		this.entretienEvaluationAction = entretienEvaluationAction;
	}

	/**
	 * @return the textInputPlanCarriereNom
	 */
	public String getTextInputPlanCarriereNom() {
		return textInputPlanCarriereNom;
	}

	/**
	 * @param textInputPlanCarriereNom the textInput to set
	 */
	public void setTextInputPlanCarriereNom(String textInputPlanCarriereNom) {
		this.textInputPlanCarriereNom = textInputPlanCarriereNom;
	}

	/**
	 * @return the planCarriereObjectifs
	 */
	public List<PlanCarriereObjectif> getPlanCarriereObjectifs() {
		return planCarriereObjectifs;
	}

	/**
	 * @param planCarriereObjectifs the planCarriereObjectifs to set
	 */
	public void setPlanCarriereObjectifs(List<PlanCarriereObjectif> planCarriereObjectifs) {
		this.planCarriereObjectifs = planCarriereObjectifs;
	}

	/**
	 * @return the planCarriereObjectif
	 */
	public PlanCarriereObjectif getPlanCarriereObjectif() {
		return planCarriereObjectif;
	}

	/**
	 * @param planCarriereObjectif the planCarriereObjectif to set
	 */
	public void setPlanCarriereObjectif(PlanCarriereObjectif planCarriereObjectif) {
		this.planCarriereObjectif = planCarriereObjectif;
	}

	/**
	 * @return the textInputActionDesc
	 */
	public String getTextInputActionDesc() {
		return textInputActionDesc;
	}

	/**
	 * @param textInputActionDesc the textInputActionDesc to set
	 */
	public void setTextInputActionDesc(String textInputActionDesc) {
		this.textInputActionDesc = textInputActionDesc;
	}

	/**
	 * @return the radioStatutAction
	 */
	public String getRadioStatutAction() {
		return radioStatutAction;
	}

	/**
	 * @param radioStatutAction the radioStatutAction to set
	 */
	public void setRadioStatutAction(String radioStatutAction) {
		this.radioStatutAction = radioStatutAction;
	}

	/**
	 * @return the textInputPlanCarriereDesc
	 */
	public String getTextInputPlanCarriereDesc() {
		return textInputPlanCarriereDesc;
	}

	/**
	 * @param textInputPlanCarriereDesc the textInputPlanCarriereDesc to set
	 */
	public void setTextInputPlanCarriereDesc(String textInputPlanCarriereDesc) {
		this.textInputPlanCarriereDesc = textInputPlanCarriereDesc;
	}

	/**
	 * @return the textInputObjectifNom
	 */
	public String getTextInputObjectifNom() {
		return textInputObjectifNom;
	}

	/**
	 * @param textInputObjectifNom the textInputObjectifNom to set
	 */
	public void setTextInputObjectifNom(String textInputObjectifNom) {
		this.textInputObjectifNom = textInputObjectifNom;
	}

	/**
	 * @return the textInputObjectifDesc
	 */
	public String getTextInputObjectifDesc() {
		return textInputObjectifDesc;
	}

	/**
	 * @param textInputObjectifDesc the textInputObjectifDesc to set
	 */
	public void setTextInputObjectifDesc(String textInputObjectifDesc) {
		this.textInputObjectifDesc = textInputObjectifDesc;
	}

	/**
	 * @return the radioStatutObjectif
	 */
	public String getRadioStatutObjectif() {
		return radioStatutObjectif;
	}

	/**
	 * @param radioStatutObjectif the radioStatutObjectif to set
	 */
	public void setRadioStatutObjectif(String radioStatutObjectif) {
		this.radioStatutObjectif = radioStatutObjectif;
	}

}
