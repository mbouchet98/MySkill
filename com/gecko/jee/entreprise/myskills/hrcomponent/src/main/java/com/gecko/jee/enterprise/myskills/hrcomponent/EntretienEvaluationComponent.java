/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;

/**
 * <b> Description : Interface de gestion des entretiens d'�valuation.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface EntretienEvaluationComponent {

	/**
	 * R�cup�re le liste des entretien d'�valuation
	 * 
	 * @param filtres
	 * @return
	 */
	List<EntretienEvaluation> readEntretienEvaluations();

	/**
	 * Ajoute un entretien d'�valuation
	 * 
	 * @param entertienEvaluation
	 */
	void addOrUpdate(EntretienEvaluation entertienEvaluation);

	/**
	 * Récupère un entretien d'évaluation
	 * 
	 * @param entretienEvaluation
	 * @return
	 */
	EntretienEvaluation readEntretienEvaluation(EntretienEvaluation entretienEvaluation);

	/**
	 * Supprime un entretien d'évaluation
	 * 
	 * @param entretienEvaluation
	 */
	void deleteEntretienEvaluation(EntretienEvaluation entretienEvaluation);
}
