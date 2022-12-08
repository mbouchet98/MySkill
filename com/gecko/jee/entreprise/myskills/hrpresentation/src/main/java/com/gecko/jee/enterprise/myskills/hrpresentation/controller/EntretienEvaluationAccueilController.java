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
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;

import com.gecko.jee.enterprise.myskills.hrcomponent.impl.EntretienEvaluationComponentImpl;
import com.gecko.jee.enterprise.myskills.hrcomponent.impl.MskuserComponentImpl;
import com.gecko.jee.enterprise.myskills.hrcomponent.impl.TypeEntretienEvaluationComponentImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;
import com.gecko.jee.enterprise.myskills.hrpresentation.form.EntretienEvaluationAccueilForm;

/**
 * <b> Description : Gère les élements de la page
 * entretienEvaluationAccueil.</b>
 * <p>
 * 
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */

@ManagedBean
@SessionScoped
public class EntretienEvaluationAccueilController implements Serializable {

	private static final Logger LOGGER = Logger.getLogger("EntretienEvaluationAccueilController");
	private static final long serialVersionUID = 1L;

	private EntretienEvaluation entretienEvaluation;
	private List<EntretienEvaluation> entretienEvaluations;
	private EntretienEvaluation enEvaluation;

	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;

	private List<Mskuser> mskusers;
	private Mskuser mskuser;

	private List<TypeEntretienEvaluation> typeEntretienEvaluations;
	private TypeEntretienEvaluation typeEntretienEvaluation;

	private EntretienEvaluationComponentImpl entretienEvaluationComponentImpl;
	private TypeEntretienEvaluationComponentImpl typeEntretienEvaluationComponentImpl;
	private MskuserComponentImpl mskuserComponentImpl;

	@ManagedProperty(value = "#{entretienEvaluationAccueilForm}")
	private EntretienEvaluationAccueilForm entretienEvaluationAccueilForm;

	/**
	 * construteur
	 */
	public EntretienEvaluationAccueilController() {

	}

	/**
	 * Affiche les Entretiens d'évaluation dans le calendrier.
	 * 
	 * S'éxécute au chargement de la page entretienEvaluationAccueil.xhtml
	 */
	private void afficherEvenement() {
		// 1- Récupération des entretiens d'évaluation

		// instentiation du calendrier et récupération de tout les entretiens
		// d'évaluation
		entretienEvaluationAccueilForm.setCalendrierModel(new DefaultScheduleModel());
		entretienEvaluations = new ArrayList<EntretienEvaluation>();
		EntretienEvaluationComponentImpl entretienEvaluationComponentImpl = new EntretienEvaluationComponentImpl();
		entretienEvaluations = entretienEvaluationComponentImpl.readEntretienEvaluations();

		// 2- Création des évènements du calendrier avec mes entretiens d'évaluation
		for (EntretienEvaluation entretienEvaluation : entretienEvaluations) {
			String Titre = entretienEvaluation.getTypeEntretienEvaluation().getLibelle() + " - "
					+ entretienEvaluation.getMskuser().getFirstName() + " "
					+ entretienEvaluation.getMskuser().getLastName();
			LocalDateTime startDate = entretienEvaluation.getHeureDebut().toLocalDateTime();
			LocalDateTime endDate = entretienEvaluation.getHeureFin().toLocalDateTime();

			entretienEvaluationAccueilForm.setEvenement(
					DefaultScheduleEvent.builder().title(Titre).startDate(startDate).endDate(endDate).build());

			// 3- Ajout de mes évènements dans le calendrier
			entretienEvaluationAccueilForm.getCalendrierModel().addEvent(entretienEvaluationAccueilForm.getEvenement());
		}
	}

	/**
	 * Récupération de tout les Mskusers, afin de les faire apparaitre dans le
	 * selectOnMenu
	 * 
	 * S'éxécute au chargement de la page entretienEvaluationAccueil.xhtml
	 */
	private void afficherMskusers() {
		// 1- Instancie Mskuser et sa liste d'item dans entretienEvaluationAccueilForm
		entretienEvaluationAccueilForm.setMskusers(new ArrayList<Mskuser>());

		// 2- Récupération de tout les mskusers et insertion dans la liste de mskuser de
		// entretienEvaluationAccueilForm
		mskuserComponentImpl = new MskuserComponentImpl();

		// 3- Création de la liste d'item, Ajout des Mskusers dans la liste item
		for (Mskuser mskuser : mskuserComponentImpl.readAllMskuser()) {
			entretienEvaluationAccueilForm.getMskusers().add(mskuser);
		}
	}

	/**
	 * Récupération de tout les Types d'entretien d'évaluation, afin de les faire
	 * apparaitre dans le selectOnMenu
	 * 
	 * S'éxécute au chargement de la page entretienEvaluationAccueil.xhtml
	 */
	private void afficherTypesEntretienEvaluation() {
		// 1-Instancie Type Entretien Evaluation et sa liste d'item dans
		// entretienEvaluationAccueilForm
		entretienEvaluationAccueilForm.setTypeEntretienEvaluations(new ArrayList<TypeEntretienEvaluation>());

		// 2- Récupération de tout les Types d'entretien d'évaluation et insertion dans
		// cet liste dans
		// entretienEvaluationAccueilForm
		typeEntretienEvaluationComponentImpl = new TypeEntretienEvaluationComponentImpl();

		// 3- Création de la liste d'item, Ajout des Types d'entretien évaluation dans
		// la liste item
		for (TypeEntretienEvaluation typeEntretienEvaluation : typeEntretienEvaluationComponentImpl.readAll()) {
			entretienEvaluationAccueilForm.getTypeEntretienEvaluations().add(typeEntretienEvaluation);
		}
	}

	/**
	 * S'éxécute l'or du clique sur le bouton planifier, pour vider les champs de
	 * saisie
	 */
	public void champsVide() {
		this.setDateDebut(null);
		this.setDateFin(null);
		this.setEnEvaluation(null);
		entretienEvaluationAccueilForm.setMskuser(null);
		entretienEvaluationAccueilForm.setTypeEntretienEvaluation(null);
	}

	/**
	 * 
	 * Affiche les données d'un évènement entretien évaluation, dans le formulaire
	 * de la pop-up planifé
	 * 
	 * S'éxécute viva le clique sur l'évenement entretien évaluation de la page
	 * entretienEvaluationAccueil.xhtml
	 * 
	 * @param eventSelect
	 * 
	 *                    TODO : ne s'affiche pas dans les selectOneMenu (à
	 *                    retravailler)
	 */
	public void afficherDetailEvent(SelectEvent<ScheduleEvent<EntretienEvaluation>> eventSelect) {

		// 1- Récupération des données de l'évènement entretien évaluation sélectionné
		entretienEvaluationAccueilForm.selectEvent(eventSelect);
		dateDebut = entretienEvaluationAccueilForm.getEvenement().getStartDate();
		dateFin = entretienEvaluationAccueilForm.getEvenement().getEndDate();

		// Création un nouvelle entretien via les données de l'évènement entretien
		// evaluation (utile pour vérifier l'ajout, la modification d'un entretien
		// d'évaluation)
		enEvaluation = new EntretienEvaluation();
		enEvaluation.setHeureDebut(Timestamp.valueOf(dateDebut));
		enEvaluation.setHeureFin(Timestamp.valueOf(dateFin));

		// Récupérer le Libelle de Type entretien évauation et FirstName et LastName du
		// Mskuser
		String titre = entretienEvaluationAccueilForm.getEvenement().getTitle();
		String[] titreSeparer = titre.split(" - ");
		String typeEntretienEnvaluationString = titreSeparer[0];
		String mskuserString = titreSeparer[1];

		// compteurMskuser sert à déterminer la position du mskuser recherché
		// Recherche de l'item Mskuser
		for (Mskuser mskuser : entretienEvaluationAccueilForm.getMskusers()) {
			String firstLastName = mskuser.getFirstName() + " " + mskuser.getLastName();
			if (firstLastName.equals(mskuserString)) {
				// entretienEvaluationAccueilForm.getMskusersGroup().indexOf(selectItem.getValue());
				// insertion du mskuser dans l'entretien d'évaluation
				this.setMskuser(mskuser);
				enEvaluation.setMskuser(mskuser);
				entretienEvaluationAccueilForm.setMskuser(mskuser);
			}
		}

		// compteurTypeEntretien sert à déterminer la position du type entretien
		// rechercher
		// Recherche de l'item TypeEntertienEvaluation
		for (TypeEntretienEvaluation typeEntretienEvaluation : entretienEvaluationAccueilForm
				.getTypeEntretienEvaluations()) {
			if (typeEntretienEvaluation.getLibelle().equals(typeEntretienEnvaluationString)) {
				// entretienEvaluationAccueilForm.getTypesEntretiensEvaluationsGroup().indexOf(selectItemType.getValue());
				// insertion du type d'entretien évaluation dans l'entretien d'évaluation
				this.setTypeEntretienEvaluation(typeEntretienEvaluation);
				enEvaluation.setTypeEntretienEvaluation(typeEntretienEvaluation);
				entretienEvaluationAccueilForm.setTypeEntretienEvaluation(typeEntretienEvaluation);
			}
		}
	}

	/**
	 * s'éxecute l'or du clique sur le bouton Enregistrer, Permet l'ajout d'un
	 * entretien ou sa modification
	 */
	public void creerModifierEntretienEvaluation() {

		// 1- vérifier la saisi des champs

		if (dateDebut != null && dateFin != null && entretienEvaluationAccueilForm.getMskuser() != null
				&& entretienEvaluationAccueilForm.getTypeEntretienEvaluation() != null) {

			// Récupération du Mskuser sélectionner
			mskuser = new Mskuser();
			this.setMskuser(entretienEvaluationAccueilForm.getMskuser());

			// Récupération du type d'entretien d'évaluation sélectionner
			typeEntretienEvaluation = new TypeEntretienEvaluation();
			this.setTypeEntretienEvaluation(entretienEvaluationAccueilForm.getTypeEntretienEvaluation());

			// Stocker les valeur récupéré dans un entretien d'évaluation
			entretienEvaluation = new EntretienEvaluation();
			entretienEvaluation.setDateCreation(new Timestamp(System.currentTimeMillis()));
			entretienEvaluation.setHeureDebut(Timestamp.valueOf(dateDebut));
			entretienEvaluation.setHeureFin(Timestamp.valueOf(dateFin));
			entretienEvaluation.setMskuser(mskuser);
			entretienEvaluation.setTypeEntretienEvaluation(typeEntretienEvaluation);

			// Rechercher si l'entretien d'évaluation existe, et si des données était déjà
			// saisi dans le formulaire de la pop-up
			entretienEvaluationComponentImpl = new EntretienEvaluationComponentImpl();
			EntretienEvaluation lEntretienEvaluation = new EntretienEvaluation();
			// Données Pop-up
			if (enEvaluation != null) {
				lEntretienEvaluation = entretienEvaluationComponentImpl.readEntretienEvaluation(enEvaluation);
				// Données exitant en base
				if (lEntretienEvaluation.getIdEntretienEvaluation() != null) {
					// Vérification de si il y a eu des modification de faire
					if (lEntretienEvaluation.getHeureDebut().equals(entretienEvaluation.getHeureDebut())) {
						System.out.println("Heure début: " + enEvaluation.getHeureDebut());
					} else {
						lEntretienEvaluation.setHeureDebut(entretienEvaluation.getHeureDebut());
					}

					if (lEntretienEvaluation.getHeureFin().equals(entretienEvaluation.getHeureFin())) {
						System.out.println("Heure Fin : " + enEvaluation.getHeureFin());
					} else {
						lEntretienEvaluation.setHeureFin(entretienEvaluation.getHeureFin());
					}

					if (lEntretienEvaluation.getMskuser().equals(entretienEvaluation.getMskuser())) {
						System.out.println("Mskuser : " + entretienEvaluation.getMskuser());
					} else {
						lEntretienEvaluation.setMskuser(entretienEvaluation.getMskuser());
					}

					if (lEntretienEvaluation.getTypeEntretienEvaluation()
							.equals(entretienEvaluation.getTypeEntretienEvaluation())) {
						System.out.println(
								"Type Entretien Evaluation : " + entretienEvaluation.getTypeEntretienEvaluation());
					} else {
						lEntretienEvaluation
								.setTypeEntretienEvaluation(entretienEvaluation.getTypeEntretienEvaluation());
					}
					// Modifie l'entretien d'évaluation
					entretienEvaluationComponentImpl.addOrUpdate(lEntretienEvaluation);
				}

			} else {
				// Ajout l'entretien d'évaluation
				entretienEvaluationComponentImpl.addOrUpdate(entretienEvaluation);
			}

			// 2- réfréchir la page
			redirection();
		} else {
			System.out.println("saisir données");
		}
	}

	/**
	 * s'éxècute l'or du clique sur le bouton supprimer de la pop-up dans
	 * entretienEvaluationAccueil.xhtml
	 * 
	 * Supprime un entretien d'évaluation planifier
	 */
	public void supprimerEntretienEvaluation() {

		// Rechercher si l'entretien d'évaluation existe, et si des données était déjà
		// saisi dans le formulaire de la pop-up
		entretienEvaluationComponentImpl = new EntretienEvaluationComponentImpl();
		EntretienEvaluation lEntretienEvaluation = new EntretienEvaluation();
		// Donnée Pop-up
		if (enEvaluation != null) {
			// Donnée existent en base
			lEntretienEvaluation = entretienEvaluationComponentImpl.readEntretienEvaluation(enEvaluation);
			if (lEntretienEvaluation.getIdEntretienEvaluation() != null) {
				// Supprime l'entretien d'évaluation
				entretienEvaluationComponentImpl.deleteEntretienEvaluation(lEntretienEvaluation);
			}
		}
		// rafraîchie la page
		redirection();
	}

	/**
	 * S'éxécute l'or du clique sur le bouton détail de la pop-up dans
	 * entretienEvaluationAccueil.xhtml
	 * 
	 * Redirige vers les formulaire de saisi complète des entretiens d'évaluation
	 */
	public void redirectionFormualaireEntretien() {

		// TODO : faire un chargement des données avant

		// Redirige vers une page pour les types entretiens Annuel ou de Fin de projet,
		// en fonction du type d'entretien saisi dans la pop-up
		if (enEvaluation.getTypeEntretienEvaluation().getLibelle().equals("Annuel")) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("entretienEvaluationAnnuel.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("entretienEvaluationProjet.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * S'éxécute via le bouton enregistrer dans la page
	 * TestCommunicationController.xthml
	 * 
	 * et permet le rafraichissement de la page entretienEvaluationAccueil.xhtml
	 */
	public void redirection() {
		// appel les méthode qui charge le Form avec les données
		afficherEvenement();
		afficherMskusers();
		afficherTypesEntretienEvaluation();
		// redirige
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("entretienEvaluationAccueil.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return entretienEvaluation
	 */
	public EntretienEvaluation getEntretienEvaluation() {
		return entretienEvaluation;
	}

	/**
	 * 
	 * @param entretienEvaluation
	 */
	public void setEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		this.entretienEvaluation = entretienEvaluation;
	}

	/**
	 * 
	 * @return dateDebut
	 */
	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	/**
	 * 
	 * @param dateDebut
	 */
	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * 
	 * @return dateFin
	 */
	public LocalDateTime getDateFin() {
		return dateFin;
	}

	/**
	 * 
	 * @param dateFin
	 */
	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * 
	 * @return mskuser
	 */
	public Mskuser getMskuser() {
		return mskuser;
	}

	/**
	 * 
	 * @param mskuser
	 */
	public void setMskuser(Mskuser mskuser) {
		this.mskuser = mskuser;
	}

	/**
	 * 
	 * @return typeEntretienEvaluation
	 */
	public TypeEntretienEvaluation getTypeEntretienEvaluation() {
		return typeEntretienEvaluation;
	}

	/**
	 * 
	 * @param typeEntretienEvaluation
	 */
	public void setTypeEntretienEvaluation(TypeEntretienEvaluation typeEntretienEvaluation) {
		this.typeEntretienEvaluation = typeEntretienEvaluation;
	}

	/**
	 * 
	 * @return entretienEvaluationAccueilForm
	 */
	public EntretienEvaluationAccueilForm getEntretienEvaluationAccueilForm() {
		return entretienEvaluationAccueilForm;
	}

	/**
	 * 
	 * @param entretienEvaluationAccueilForm
	 */
	public void setEntretienEvaluationAccueilForm(EntretienEvaluationAccueilForm entretienEvaluationAccueilForm) {
		this.entretienEvaluationAccueilForm = entretienEvaluationAccueilForm;
	}

	/**
	 * 
	 * @return entretienEvaluations
	 */
	public List<EntretienEvaluation> getEntretienEvaluations() {
		return entretienEvaluations;
	}

	/**
	 * 
	 * @param entretienEvaluations
	 */
	public void setEntretienEvaluations(List<EntretienEvaluation> entretienEvaluations) {
		this.entretienEvaluations = entretienEvaluations;
	}

	/**
	 * 
	 * @return mskusers
	 */
	public List<Mskuser> getMskusers() {
		return mskusers;
	}

	/**
	 * 
	 * @param mskusers
	 */
	public void setMskusers(List<Mskuser> mskusers) {
		this.mskusers = mskusers;
	}

	/**
	 * 
	 * @return typeEntretienEvaluations
	 */
	public List<TypeEntretienEvaluation> getTypeEntretienEvaluations() {
		return typeEntretienEvaluations;
	}

	/**
	 * 
	 * @param typeEntretienEvaluations
	 */
	public void setTypeEntretienEvaluations(List<TypeEntretienEvaluation> typeEntretienEvaluations) {
		this.typeEntretienEvaluations = typeEntretienEvaluations;
	}

	/**
	 * 
	 * @return enEvaluation
	 */
	public EntretienEvaluation getEnEvaluation() {
		return enEvaluation;
	}

	/**
	 * 
	 * @param enEvaluation
	 */
	public void setEnEvaluation(EntretienEvaluation enEvaluation) {
		this.enEvaluation = enEvaluation;
	}

}
