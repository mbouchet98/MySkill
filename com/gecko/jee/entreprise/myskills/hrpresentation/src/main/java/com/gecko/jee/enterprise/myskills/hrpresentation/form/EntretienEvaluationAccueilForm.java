/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpresentation.form;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;

/**
 * <b> Description : formulaire contenent les éléments d'affichage de l'écran
 * entretienEvaluationAccueil avec le calendrier, le formulaire pour planifier
 * les entretiens d'évaluation </b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
@ManagedBean
@SessionScoped
public class EntretienEvaluationAccueilForm implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Evènement sélectionné du calendrier. Utilisé pour la popup de détail.
	 */
	private ScheduleEvent<EntretienEvaluation> evenement = new DefaultScheduleEvent<>();

	/**
	 * Calendrier (JSF) à afficher
	 */
	private ScheduleModel calendrierModel = new DefaultScheduleModel();

	/**
	 * pour la sélection d'un type d'entretien d'évaluation
	 */
	private TypeEntretienEvaluation typeEntretienEvaluation;
	private List<TypeEntretienEvaluation> typeEntretienEvaluations;

	/**
	 * pour la selection d'un mskuser
	 */
	private Mskuser mskuser;
	private List<Mskuser> mskusers;

	/**
	 * constructeur par défaut
	 */
	public EntretienEvaluationAccueilForm() {
		super();
	}

	/**
	 * @return the evenement
	 */
	public ScheduleEvent getEvenement() {
		return evenement;
	}

	/**
	 * @return the calendrierModel
	 */
	public ScheduleModel getCalendrierModel() {
		return calendrierModel;
	}

	/**
	 * Action permettant afficher les détails d'un évènement du calendrier.
	 *
	 * @param selectEvent l'évènement sélectionné dans le calendrier.
	 */
	public void selectEvent(SelectEvent<ScheduleEvent<EntretienEvaluation>> selectEvent) {
		evenement = selectEvent.getObject();
	}

	/**
	 * Instancie les champs date
	 * 
	 * @param selectEvent
	 */
	public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
		evenement = DefaultScheduleEvent.builder().startDate(selectEvent.getObject())
				.endDate(selectEvent.getObject().plusHours(1)).build();
	}
	/**
	 * @param evenement the evenement to set
	 */
	public void setEvenement(ScheduleEvent<EntretienEvaluation> evenement) {
		this.evenement = evenement;
	}

	/**
	 * @param calendrierModel the calendrierModel to set
	 */
	public void setCalendrierModel(ScheduleModel calendrierModel) {
		this.calendrierModel = calendrierModel;
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


}
