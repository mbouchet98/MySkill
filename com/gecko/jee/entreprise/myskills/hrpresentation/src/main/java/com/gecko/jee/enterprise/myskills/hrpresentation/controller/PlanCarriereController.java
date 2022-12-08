/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpresentation.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.gecko.jee.enterprise.myskills.hrcomponent.impl.MskuserComponentImpl;
import com.gecko.jee.enterprise.myskills.hrcomponent.impl.PlanCarriereComponentImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriere;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;
import com.gecko.jee.enterprise.myskills.hrpresentation.form.PlanCarriereForm;

/**
 * <b> Description : Gère les éléments de la page planCarriere.xhtml.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */

@ManagedBean
@SessionScoped
public class PlanCarriereController implements Serializable {

	private static final long serialVersionUID = 1L;

	public EntretienEvaluation entretien;

	public Mskuser mskuser;
	public MskuserComponentImpl mskuserComponentImpl;

	public PlanCarriere selectionPlanCarriere;
	public PlanCarriere planCarriere;
	public List<PlanCarriere> planCarrieres;
	public PlanCarriereComponentImpl planCarriereComponentImpl;

	public PlanCarriereObjectif planCarriereObjectif;
	public List<PlanCarriereObjectif> planCarriereObjectifs;

	@ManagedProperty(value = "#{planCarriereForm}")
	private PlanCarriereForm planCarriereForm;

	/**
	 * construteur
	 */
	public PlanCarriereController() {

	}

	public void viderChampsPlanCarriere() {
		planCarriereForm.setTextInputPlanCarriereNom(null);
		planCarriereForm.setTextInputPlanCarriereDesc(null);
	}

	/**
	 * Ajoute une plan de carriere existant a l'utilisateur
	 */
	public void ajouterExistantPlanCarriere() {
		selectionPlanCarriere = new PlanCarriere();
		this.setSelectionPlanCarriere(planCarriereForm.getSelectPlanCarriere());
		this.getSelectionPlanCarriere().getMskusers().add(this.getEntretien().getMskuser());
		planCarriereComponentImpl = new PlanCarriereComponentImpl();
		planCarriereComponentImpl.addOrUpdate(selectionPlanCarriere);
		// mskuser.getPlanCarrieres().add(selectionPlanCarriere);
		// this.getEntretien().setMskuser(mskuser);
		redirectionPlanCarriere(this.getEntretien());
	}

	public void ajouter() {
		System.out.println("ici, je suis bien la");
	}
	/**
	 * Ajoute une nouveau plan de carriere a la liste des plan de carriere
	 */
	public void ajouterNewPlanCarriere() {
		//vérifier la saisie des champs
		System.out.println("je suis bien la ");
		System.out.println(planCarriereForm.getTextInputPlanCarriereNom());
		System.out.println(planCarriereForm.getTextInputPlanCarriereDesc());
		if(planCarriereForm.getTextInputPlanCarriereNom()!= null &&
				planCarriereForm.getTextInputPlanCarriereDesc() != null) {
			// entretien = new EntretienEvaluation();
			PlanCarriere pc = new PlanCarriere();
//			List<Mskuser> mskusers = new ArrayList<Mskuser>();
//			mskusers.add(this.getEntretien().getMskuser());
//			mskusers.add(mskuser);
			pc.setNom(planCarriereForm.getTextInputPlanCarriereNom());
			pc.setDescription(planCarriereForm.getTextInputPlanCarriereDesc());
			System.out.println(pc.toString());
			// pc.setMskusers(mskusers);
			planCarriereComponentImpl = new PlanCarriereComponentImpl();
			planCarriereComponentImpl.addOrUpdate(pc);
			// mskuser.getPlanCarrieres().add(pc);
			// entretien.setMskuser(mskuser);
			redirectionPlanCarriere(this.getEntretien());
		} else {
			System.out.println("erreur j'ai pas de donnée");
		}
		System.out.println("ici");
	}

	public void chargementPlanCarriereMskuser() {

		// afficher la liste de plan de carriere
		planCarrieres = new ArrayList<PlanCarriere>();
		this.setPlanCarrieres(this.getEntretien().getMskuser().getPlanCarrieres());
		planCarriereForm.setPlanCarrieres(this.getPlanCarrieres());

	}

	public void chargementToutPlanCarriere() {
		// tout les plan de carrière
		planCarriereComponentImpl = new PlanCarriereComponentImpl();
		List<PlanCarriere> pCarrieres = new ArrayList<PlanCarriere>();
		pCarrieres = planCarriereComponentImpl.readAll();
		planCarriereForm.setToutPlanCarrieres(pCarrieres);
	}

	/**
	 * S'éxécute l'or du clique sur le bouton Plan Carrière de la page
	 * entretienEvaluationAccueil.xhtml
	 * 
	 * Redirige vers les formulaire de saisi complète des Plan de carrière
	 */
	public void redirectionPlanCarriere(EntretienEvaluation evaluation) {
		this.setEntretien(evaluation);
		// tout les plan de carrière
		chargementToutPlanCarriere();
		// plan de carrière rattaché a l'utilisateurs
		chargementPlanCarriereMskuser();

		// redirection
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("planCarriere.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the entretien
	 */
	public EntretienEvaluation getEntretien() {
		return entretien;
	}

	/**
	 * @param entretien the entretien to set
	 */
	public void setEntretien(EntretienEvaluation entretien) {
		this.entretien = entretien;
	}

	/**
	 * @return the planCarriereForm
	 */
	public PlanCarriereForm getPlanCarriereForm() {
		return planCarriereForm;
	}

	/**
	 * @param planCarriereForm the planCarriereForm to set
	 */
	public void setPlanCarriereForm(PlanCarriereForm planCarriereForm) {
		this.planCarriereForm = planCarriereForm;
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
	 * @return the selectionPlanCarriere
	 */
	public PlanCarriere getSelectionPlanCarriere() {
		return selectionPlanCarriere;
	}

	/**
	 * @param selectionPlanCarriere the selectionPlanCarriere to set
	 */
	public void setSelectionPlanCarriere(PlanCarriere selectionPlanCarriere) {
		this.selectionPlanCarriere = selectionPlanCarriere;
	}

}
