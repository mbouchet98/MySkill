/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpresentation.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.gecko.jee.enterprise.myskills.hrcomponent.impl.EntretienEvaluationActionComponentImpl;
import com.gecko.jee.enterprise.myskills.hrcomponent.impl.EntretienEvaluationComponentImpl;
import com.gecko.jee.enterprise.myskills.hrcomponent.impl.PlanCarriereComponentImpl;
import com.gecko.jee.enterprise.myskills.hrcomponent.impl.PlanCarriereObjectifComponentImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluationAction;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriere;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;
import com.gecko.jee.enterprise.myskills.hrpresentation.form.EntretienEvaluationAnnuelForm;

/**
 * <b> Description : Gère les élements de la page entretienEvaluationAnnuel.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
@ManagedBean
@SessionScoped
public class EntretienEvaluationAnnuelController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntretienEvaluation entretienEvaluation;

	private EntretienEvaluationAction entretienEvaluationAction;
	private List<EntretienEvaluationAction> entretienEvaluationActions;

	// action stoker pour modifier
	private EntretienEvaluationAction EvaluationAction;

	private LocalDateTime dateLimite;

	private Mskuser mskuser;
	private List<Mskuser> mskusers;

	private PlanCarriere planCarriere;
	private List<PlanCarriere> planCarrieres;

	private PlanCarriereObjectif planCarriereObjectif;
	private List<PlanCarriereObjectif> planCarriereObjectifs;

	private EntretienEvaluationComponentImpl entretienEvaluationComponentImpl;

	@ManagedProperty(value = "#{entretienEvaluationAnnuelForm}")
	private EntretienEvaluationAnnuelForm entretienEvaluationAnnuelForm;

	/**
	 * construteur
	 */
	public EntretienEvaluationAnnuelController() {

	}

	/**
	 * S'éxécute l'or du clique sur le bouton Enregistrer du formulaire
	 * 
	 * Créer ou Modifie l'entretien d'évaluation
	 */
	public void creerModifierEntretienEvaluation() {
		if (entretienEvaluationAnnuelForm.getTextEditor() != null) {
			entretienEvaluation.setCompteRendu(entretienEvaluationAnnuelForm.getTextEditor());
			entretienEvaluationComponentImpl.addOrUpdate(entretienEvaluation);
		}
	}

	/**
	 * S'éxécute l'or du clique sur le bouton Enregistrer dans le formulaire de la
	 * pop-up du tableau action
	 * 
	 * Ajoute une action à l'entretien d'évaluation ou Modifier une action
	 */
	public void ajouterEntretienEvaluationAction() {
		// Vérifié la saisie des champs
		entretienEvaluationComponentImpl = new EntretienEvaluationComponentImpl();
		if (entretienEvaluationAnnuelForm.getTextInputActionDesc() != null && dateLimite != null
				&& entretienEvaluationAnnuelForm.getRadioStatutAction() != null) {
			// Ajouter
			if (this.getEvaluationAction() == null) {
				if (entretienEvaluationActions != null) {
					// création de l'action
					entretienEvaluationAction = new EntretienEvaluationAction();
					entretienEvaluationAction.setDescription(entretienEvaluationAnnuelForm.getTextInputActionDesc());
					entretienEvaluationAction.setDateLimite(Timestamp.valueOf(dateLimite));
					entretienEvaluationAction.setStatut(entretienEvaluationAnnuelForm.getRadioStatutAction());
					entretienEvaluationAction.setEntretienEvaluation(entretienEvaluation);
					// ajout de l'action dans le tableau et en base
					entretienEvaluationActions.add(entretienEvaluationAction);
					entretienEvaluation.setEntretienEvaluationActions(entretienEvaluationActions);
					entretienEvaluationComponentImpl.addOrUpdate(entretienEvaluation);
					creerModifierEntretienEvaluation();

				}
			} else {
				// Modifier
				List<EntretienEvaluationAction> listAction = new ArrayList<EntretienEvaluationAction>();
				for (EntretienEvaluationAction action : entretienEvaluation.getEntretienEvaluationActions()) {
					// Vérifier les données qui on changer
					// Vérifier description
					if (this.getEvaluationAction().equals(action)) {
						System.out.println("action : " + action);
						// vérifier Description
						if (this.getEvaluationAction().getDescription()
								.equals(entretienEvaluationAnnuelForm.getTextInputActionDesc())) {
							System.out.println("description ok");
						} else {
							action.setDescription(entretienEvaluationAnnuelForm.getTextInputActionDesc());
						}
						// vérifier Date limite
						if (this.getEvaluationAction().getDateLimite().equals(Timestamp.valueOf(dateLimite))) {
							System.out.println("date limite ok");
						} else {
							action.setDateLimite(Timestamp.valueOf(dateLimite));
						}
						// vérifier statut
						if (this.getEvaluationAction().getStatut()
								.equals(entretienEvaluationAnnuelForm.getRadioStatutAction())) {
							System.out.println("statut ok");
						} else {
							action.setStatut(entretienEvaluationAnnuelForm.getRadioStatutAction());
						}
						listAction.add(action);
					} else {
						System.out.println("Au suivant");
						listAction.add(action);
					}
					// Modification de l'action dans la liste et en base
					entretienEvaluation.setEntretienEvaluationActions(listAction);
					entretienEvaluationComponentImpl.addOrUpdate(entretienEvaluation);

					creerModifierEntretienEvaluation();

				}
			}
			redirectionFormualaireEntretien(this.getEntretienEvaluation());
		}
	}

	/**
	 * S'éxécute l'or du clique sur le bouton Ajouter du tableau Plan carrière
	 * objectif
	 * 
	 * Ajoute ou modifie un objectif de plan de carrière dans la liste
	 */
	public void ajouterModifierPlanCarriereObjectif() {
		PlanCarriereComponentImpl planCarriereComponentImpl = new PlanCarriereComponentImpl();

		// plan de carrière objectif dépend de plan de carrière donc il faut vérifier
		// que le mskuser possède un plan de carrière
		if (entretienEvaluationAnnuelForm.getTextInputPlanCarriereNom() != null
				&& entretienEvaluationAnnuelForm.getTextInputPlanCarriereDesc() != null) {
			if (planCarriere != null) {
				// Si il a été modifier via les champs
				if (planCarriere.getNom().equals(entretienEvaluationAnnuelForm.getTextInputPlanCarriereNom())) {

				} else {
					planCarriere.setNom(entretienEvaluationAnnuelForm.getTextInputPlanCarriereNom());
				}

				if (planCarriere.getDescription()
						.equals(entretienEvaluationAnnuelForm.getTextInputPlanCarriereDesc())) {

				} else {
					planCarriere.setDescription(entretienEvaluationAnnuelForm.getTextInputPlanCarriereDesc());
				}

				for (Mskuser user : planCarriere.getMskusers()) {
					if (user.equals(mskuser)) {

					} else {
						mskuser.getPlanCarrieres().add(planCarriere);
					}
				}

				// modifie en base
				entretienEvaluation.getMskuser().getPlanCarrieres().add(planCarriere);
				this.setPlanCarriere(planCarriere);
				planCarriereComponentImpl.addOrUpdate(planCarriere);

			} else {
				// Le créer le plan de carrière
				planCarriere = new PlanCarriere();
				planCarriere.setNom(entretienEvaluationAnnuelForm.getTextInputPlanCarriereNom());
				planCarriere.setDescription(entretienEvaluationAnnuelForm.getTextInputPlanCarriereDesc());
				planCarriere.getMskusers().add(mskuser);
				entretienEvaluation.getMskuser().getPlanCarrieres().add(planCarriere);
				mskuser.getPlanCarrieres().add(planCarriere);
				this.setPlanCarriere(planCarriere);
				planCarriereComponentImpl.addOrUpdate(planCarriere);

			}
		}

		// récupérer les données pour les objectifs
		if (entretienEvaluationAnnuelForm.getTextInputObjectifNom() != null
				&& entretienEvaluationAnnuelForm.getTextInputObjectifDesc() != null
				&& entretienEvaluationAnnuelForm.getRadioStatutObjectif() != null) {


			// Vérifier qu'il n'y a pas de objectif
			if (planCarriereObjectif == null) {
				if (planCarriereObjectifs != null) {
					// Ajouter un Objectif
					planCarriereObjectif = new PlanCarriereObjectif();
					planCarriereObjectif.setNom(entretienEvaluationAnnuelForm.getTextInputObjectifNom());
					planCarriereObjectif.setDescription(entretienEvaluationAnnuelForm.getTextInputObjectifDesc());
					planCarriereObjectif.setStatut(entretienEvaluationAnnuelForm.getRadioStatutObjectif());
					planCarriereObjectif.setPlanCarriere(planCarriere);

					planCarriereObjectifs.add(planCarriereObjectif);
					planCarriere.setPlanCarriereObjectifs(planCarriereObjectifs);
					mskuser.getPlanCarrieres().add(planCarriere);
					planCarriereComponentImpl.addOrUpdate(planCarriere);
					entretienEvaluation.setMskuser(mskuser);

					creerModifierEntretienEvaluation();

				}
			} else {
				// Modifer Objectif
				List<PlanCarriereObjectif> listObjectifs = new ArrayList<PlanCarriereObjectif>();
				for (PlanCarriereObjectif objectif : this.getPlanCarriereObjectifs()) {
					// Vérifier les données qui on changer
					// Vérifier description
					if (this.getPlanCarriereObjectif().equals(objectif)) {
						// vérifier Nom
						if (this.getPlanCarriereObjectif().getNom()
								.equals(entretienEvaluationAnnuelForm.getTextInputObjectifNom())) {
						} else {
							objectif.setNom(entretienEvaluationAnnuelForm.getTextInputObjectifNom());
						}
						// vérifier Description
						if (this.getPlanCarriereObjectif().getDescription()
								.equals(entretienEvaluationAnnuelForm.getTextInputObjectifDesc())) {
						} else {
							objectif.setDescription(entretienEvaluationAnnuelForm.getTextInputObjectifDesc());
						}
						// vérifier statut
						if (this.getPlanCarriereObjectif().getStatut()
								.equals(entretienEvaluationAnnuelForm.getRadioStatutObjectif())) {
						} else {
							objectif.setStatut(entretienEvaluationAnnuelForm.getRadioStatutObjectif());
						}
						listObjectifs.add(objectif);
					} else {
						listObjectifs.add(objectif);
					}
				}
				// Modification de l'objectid en base et dans la liste
				this.setPlanCarriereObjectifs(listObjectifs);
				planCarriere.setPlanCarriereObjectifs(this.getPlanCarriereObjectifs());
				mskuser.getPlanCarrieres().add(planCarriere);
				entretienEvaluation.setMskuser(mskuser);
				planCarriereComponentImpl.addOrUpdate(planCarriere);

				creerModifierEntretienEvaluation();

			}
		}
		redirectionFormualaireEntretien(this.getEntretienEvaluation());
	}

	/**
	 * S'éxécute l'or du clique sur le bouton modifier du tableau d'action
	 * 
	 * Modifie l'action de la ligne du tableau
	 */
	public void modifierEntretienEvaluationAction(String enString) {
		// chercher dans la liste d'action cets action
		for (EntretienEvaluationAction enEvaluationAction : this.getEntretienEvaluation()
				.getEntretienEvaluationActions()) {
			if (enEvaluationAction.toString().equals(enString)) {

				// afficher dans les champs
				entretienEvaluationAnnuelForm.setTextInputActionDesc(enEvaluationAction.getDescription());
				this.setDateLimite(enEvaluationAction.getDateLimite().toLocalDateTime());
				entretienEvaluationAnnuelForm.setRadioStatutAction(enEvaluationAction.getStatut());

				// stoker cette action
				this.setEvaluationAction(enEvaluationAction);
			}
		}
	}

	/**
	 * S'éxécute l'or du clique sur le bouton modifier du tableau d'objectif
	 * 
	 * Modifie l'objectif de la ligne du tableau
	 */
	public void modifierPlanCarriereObjectif(String pcoString) {

		for (PlanCarriereObjectif plCarriereObjectif : this.getPlanCarriereObjectifs()) {
			if (plCarriereObjectif.toString().equals(pcoString)) {

				// afficher dans les champs
				entretienEvaluationAnnuelForm.setTextInputObjectifNom(plCarriereObjectif.getNom());
				entretienEvaluationAnnuelForm.setTextInputObjectifDesc(plCarriereObjectif.getDescription());
				entretienEvaluationAnnuelForm.setRadioStatutObjectif(plCarriereObjectif.getStatut());

				// stoker cette action
				this.setPlanCarriereObjectif(plCarriereObjectif);
			}
		}
	}

	/**
	 * s'éxécute l'or du clique sur le bouton supprimer du tableau d'action
	 * 
	 * supprime l'action de la ligne du tableau
	 */
	public void supprimerEntretienEvaluationAction(String enString) {
		EntretienEvaluationActionComponentImpl entretienEvaluationActionComponentImpl = new EntretienEvaluationActionComponentImpl();
		// création d'une nouvelle liste d'action
		List<EntretienEvaluationAction> lActions = new ArrayList<EntretienEvaluationAction>();
		// recherche de l'action a supprimer
		for (EntretienEvaluationAction enEvaluationAction : this.getEntretienEvaluation()
				.getEntretienEvaluationActions()) {
			if (enEvaluationAction.toString().equals(enString)) {
				// suppression de l'action recherché en base
				entretienEvaluationActionComponentImpl.delete(enEvaluationAction);
			} else {
				// récupère le reste des actions
				lActions.add(enEvaluationAction);
			}
		}
		// Modification de la liste d'action dans l'entretien
		entretienEvaluation.setEntretienEvaluationActions(lActions);
		entretienEvaluationComponentImpl.addOrUpdate(entretienEvaluation);

		redirectionFormualaireEntretien(this.getEntretienEvaluation());
	}

	/**
	 * s'éxécute l'or du clique sur le bouton supprimer du tableau d'objectif
	 * 
	 * @param pcoString
	 */
	public void supprimerPlanCarriereObjectif(String pcoString) {
		PlanCarriereObjectifComponentImpl planCarriereObjectifComponentImpl = new PlanCarriereObjectifComponentImpl();
		// création d'une nouvelle liste d'Objectif
		List<PlanCarriereObjectif> pObjectifs = new ArrayList<PlanCarriereObjectif>();
		// recherche de l'objectif a créer
		for (PlanCarriereObjectif pObjectif : this.getPlanCarriereObjectifs()) {
			if (pObjectif.toString().equals(pcoString)) {
				// suppression de l'objectif en en base
				planCarriereObjectifComponentImpl.delete(pObjectif);
				entretienEvaluationAnnuelForm.getPlanCarriereObjectifs().remove(pObjectif);
			} else {
				// récupération des autres objectifs
				pObjectifs.add(pObjectif);
			}
		}
		// modification de l'entretien, du mskuser et du plan de carrière
		this.setPlanCarriereObjectifs(pObjectifs);
		planCarriere.setPlanCarriereObjectifs(planCarriereObjectifs);
		mskuser.getPlanCarrieres().add(planCarriere);
		entretienEvaluation.setMskuser(mskuser);


		redirectionFormualaireEntretien(entretienEvaluation);
	}

	/**
	 * S'éxécute l'or du clique sur Ajouter dans le tableau Action
	 * 
	 * Vide les saisies des champs
	 */
	public void viderChampsAction() {
		entretienEvaluationAnnuelForm.setTextInputActionDesc(null);
		this.setDateLimite(null);
		entretienEvaluationAnnuelForm.setRadioStatutAction(null);
		this.setEvaluationAction(null);
	}

	/**
	 * S'éxécute l'or du clique sur Ajouter dans le tableau Objectif
	 * 
	 * vide les champs de saisie
	 */
	public void viderChampsObjectif() {
		entretienEvaluationAnnuelForm.setTextInputObjectifNom(null);
		entretienEvaluationAnnuelForm.setTextInputObjectifDesc(null);
		entretienEvaluationAnnuelForm.setRadioStatutObjectif(null);
		this.setPlanCarriereObjectif(null);
	}

	/**
	 * S'éxécute l'or du clique sur le bouton détail de la pop-up dans
	 * entretienEvaluationAccueil.xhtml
	 * 
	 * Redirige vers les formulaire de saisi complète des entretiens d'évaluation
	 * 
	 * @param evaluation
	 */
	public void redirectionFormualaireEntretien(EntretienEvaluation evaluation) {

		// Récupère l'entretien d'évaluation
		this.setEntretienEvaluation(evaluation);
		entretienEvaluationComponentImpl = new EntretienEvaluationComponentImpl();
		EntretienEvaluation lEntretienEvaluation = entretienEvaluationComponentImpl.readEntretienEvaluation(evaluation);
		// Vérification des données existante
		// Vérification du Compte rendu
		String compteRendu = lEntretienEvaluation.getCompteRendu();
		if (compteRendu != null) {
			entretienEvaluationAnnuelForm.setTextEditor(String.valueOf(lEntretienEvaluation.getCompteRendu()));
		} else {
			entretienEvaluationAnnuelForm.setTextEditor(null);
		}

		// Vérification de la liste d'action
		this.setEntretienEvaluationActions(lEntretienEvaluation.getEntretienEvaluationActions());
		if (entretienEvaluationActions != null) {
			// instantiation de la liste des actions
			entretienEvaluationAnnuelForm.setEntretienEvaluationActions(new ArrayList<EntretienEvaluationAction>());
			for (EntretienEvaluationAction enAction : entretienEvaluationActions) {
				entretienEvaluationAnnuelForm.getEntretienEvaluationActions().add(enAction);
			}
		} else {
			entretienEvaluationAnnuelForm.setEntretienEvaluationActions(new ArrayList<EntretienEvaluationAction>());
		}

		// récupérer les Mskuser
		this.setMskuser(lEntretienEvaluation.getMskuser());

		// Vérification du Plan de carrière
		 this.setPlanCarrieres(lEntretienEvaluation.getMskuser().getPlanCarrieres());

		this.setPlanCarriere(planCarrieres.get(planCarrieres.size() - 1));

		// Plan de carriere Objectif
		this.setPlanCarriereObjectifs(planCarriere.getPlanCarriereObjectifs());
//		 for(PlanCarriere pCarriere : planCarrieres) {
//			 this.setPlanCarriereObjectifs(pCarriere.getPlanCarriereObjectifs());
//		 }
		 

		// Redirige vers une page pour les types entretiens Annuel ou de Fin de projet,
		// en fonction du type d'entretien saisi dans la pop-up
		if (this.getEntretienEvaluation().getTypeEntretienEvaluation().getLibelle().equals("Annuel")) {

			if (planCarriere != null) {
				entretienEvaluationAnnuelForm.setTextInputPlanCarriereNom(planCarriere.getNom());
				entretienEvaluationAnnuelForm.setTextInputPlanCarriereDesc(planCarriere.getDescription());
				// Vérification de la liste d'objectif
				if (planCarriereObjectifs != null) {
					// instation de la liste d'objectif
					entretienEvaluationAnnuelForm.setPlanCarriereObjectifs(new ArrayList<PlanCarriereObjectif>());
					for (PlanCarriereObjectif plObjectif : planCarriereObjectifs) {
						entretienEvaluationAnnuelForm.getPlanCarriereObjectifs().add(plObjectif);
					}
				} else {
					entretienEvaluationAnnuelForm.setPlanCarriereObjectifs(new ArrayList<PlanCarriereObjectif>());
				}
			} else {
				// instantion des champs de plan de carrière et de la liste d'objectif
				entretienEvaluationAnnuelForm.setTextInputPlanCarriereNom(null);
				entretienEvaluationAnnuelForm.setTextInputPlanCarriereDesc(null);
				entretienEvaluationAnnuelForm.setPlanCarriereObjectifs(new ArrayList<PlanCarriereObjectif>());
			}

			this.setEntretienEvaluation(lEntretienEvaluation);

			// redirection vers la page entretienEvaluationAnnuel.xhtml
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("entretienEvaluationAnnuel.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setEntretienEvaluation(lEntretienEvaluation);
			// redirection vers la page entretienEvaluationProjet.xhtml
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("entretienEvaluationProjet.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the entretienEvaluationAnnuelForm
	 */
	public EntretienEvaluationAnnuelForm getEntretienEvaluationAnnuelForm() {
		return entretienEvaluationAnnuelForm;
	}

	/**
	 * @param entretienEvaluationAnnuelForm the entretienEvaluationAnnuelForm to set
	 */
	public void setEntretienEvaluationAnnuelForm(EntretienEvaluationAnnuelForm entretienEvaluationAnnuelForm) {
		this.entretienEvaluationAnnuelForm = entretienEvaluationAnnuelForm;
	}

	/**
	 * @return the entretienEvaluation
	 */
	public EntretienEvaluation getEntretienEvaluation() {
		return entretienEvaluation;
	}

	/**
	 * @param entretienEvaluation the entretienEvaluation to set
	 */
	public void setEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		this.entretienEvaluation = entretienEvaluation;
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
	 * @return the dateLimite
	 */
	public LocalDateTime getDateLimite() {
		return dateLimite;
	}

	/**
	 * @param dateLimite the dateLimite to set
	 */
	public void setDateLimite(LocalDateTime dateLimite) {
		this.dateLimite = dateLimite;
	}

	/**
	 * @return the evaluationAction
	 */
	public EntretienEvaluationAction getEvaluationAction() {
		return EvaluationAction;
	}

	/**
	 * @param evaluationAction the evaluationAction to set
	 */
	public void setEvaluationAction(EntretienEvaluationAction evaluationAction) {
		EvaluationAction = evaluationAction;
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
	 * @return the mskusers
	 */
	public List<Mskuser> getMskusers() {
		return mskusers;
	}

	/**
	 * @param mskusers the mskusers to set
	 */
	public void setMskusers(List<Mskuser> mskusers) {
		this.mskusers = mskusers;
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

}
