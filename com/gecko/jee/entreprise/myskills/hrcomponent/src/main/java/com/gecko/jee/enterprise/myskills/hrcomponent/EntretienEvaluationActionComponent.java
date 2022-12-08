/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluationAction;

/**
 * <b> Description : Interface de gestion des actions d'entretiens
 * d'�valuation..</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface EntretienEvaluationActionComponent {
	/**
	 * Supprime une action d'entretien d'évaluation
	 * 
	 * @param entretienEvaluationAction
	 */
	void delete(EntretienEvaluationAction entretienEvaluationAction);
}
