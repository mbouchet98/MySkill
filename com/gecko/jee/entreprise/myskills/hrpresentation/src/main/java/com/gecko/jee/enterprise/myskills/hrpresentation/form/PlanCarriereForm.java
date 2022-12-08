/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpresentation.form;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriere;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;

/**
 * <b> Description : Formulaire contenant les éléments a afficher dans la page
 * planCarriere.xhtml.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */

@ManagedBean
@SessionScoped
public class PlanCarriereForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * pour la selection d'un mskuser
	 */
	private Mskuser mskuser;

	// inputText Plan Carrière
	private String textInputPlanCarriereNom;
	private String textInputPlanCarriereDesc;

	// inputText Plan Carriere Objectif
	private String textInputObjectifNom;
	private String textInputObjectifDesc;

	// dataTable Objectif
	private List<PlanCarriereObjectif> planCarriereObjectifs;
	private PlanCarriereObjectif planCarriereObjectif;

	// dataTable Plan carrière
	private List<PlanCarriere> planCarrieres;
	private PlanCarriere planCarriere;

	// selectOnMenu plan carriere
	private List<PlanCarriere> toutPlanCarrieres;
	private PlanCarriere selectPlanCarriere;

	public PlanCarriereForm() {

	}

	/**
	 * @return the textInputPlanCarriereNom
	 */
	public String getTextInputPlanCarriereNom() {
		return textInputPlanCarriereNom;
	}

	/**
	 * @param textInputPlanCarriereNom the textInputPlanCarriereNom to set
	 */
	public void setTextInputPlanCarriereNom(String textInputPlanCarriereNom) {
		this.textInputPlanCarriereNom = textInputPlanCarriereNom;
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
	 * @return the planCarrieres
	 */
	public List<PlanCarriere> getPlanCarrieres() {
		return planCarrieres;
	}

	/**
	 * @param planCarrieres the planCarrieres to set
	 */
	public void setPlanCarrieres(List<PlanCarriere> planCarrieres) {
		this.planCarrieres = planCarrieres;
	}

	/**
	 * @return the planCarriere
	 */
	public PlanCarriere getPlanCarriere() {
		return planCarriere;
	}

	/**
	 * @param planCarriere the planCarriere to set
	 */
	public void setPlanCarriere(PlanCarriere planCarriere) {
		this.planCarriere = planCarriere;
	}

	/**
	 * @return the mskuser
	 */
	public Mskuser getMskuser() {
		return mskuser;
	}

	/**
	 * @param mskuser the mskuser to set
	 */
	public void setMskuser(Mskuser mskuser) {
		this.mskuser = mskuser;
	}

	/**
	 * @return the toutPlanCarrieres
	 */
	public List<PlanCarriere> getToutPlanCarrieres() {
		return toutPlanCarrieres;
	}

	/**
	 * @param toutPlanCarrieres the toutPlanCarrieres to set
	 */
	public void setToutPlanCarrieres(List<PlanCarriere> toutPlanCarrieres) {
		this.toutPlanCarrieres = toutPlanCarrieres;
	}

	/**
	 * @return the selectPlanCarriere
	 */
	public PlanCarriere getSelectPlanCarriere() {
		return selectPlanCarriere;
	}

	/**
	 * @param selectPlanCarriere the selectPlanCarriere to set
	 */
	public void setSelectPlanCarriere(PlanCarriere selectPlanCarriere) {
		this.selectPlanCarriere = selectPlanCarriere;
	}

}
