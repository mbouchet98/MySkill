package com.gecko.jee.enterprise.myskills.hrpersistence;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;

/**
 * 
 * <b> Description : Permet l'accès aux données d'Entretien d'Evaluation
 * Action.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface EntretienEvaluationActionDAO<EntretienEvaluationAction> {

	/**
	 * Méthode de lecture d'une action d'entretien d'évaluation
	 * 
	 * @param entretienEvaluationAction
	 * @throws DAOException
	 */
	EntretienEvaluationAction readUnEntretienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction)
			throws DAOException;

	/**
	 * Méthode de suppression d'une action d'entretien d'évaluation
	 * 
	 * @param entretienEvaluationAction
	 * @throws DAOException
	 */
	void delete(EntretienEvaluationAction entretienEvaluationAction) throws DAOException;

}
