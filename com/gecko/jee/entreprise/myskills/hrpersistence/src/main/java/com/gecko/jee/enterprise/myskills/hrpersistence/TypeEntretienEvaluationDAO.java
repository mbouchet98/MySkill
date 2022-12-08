/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;

/**
 * <b> Description : Permet l'accès aux données de Type entretien
 * d'évaluation.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface TypeEntretienEvaluationDAO<TypeEntretienEvaluation> {

	/**
	 * Méthode pour lister tout les type entretiens d'évaluation.
	 * 
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<TypeEntretienEvaluation> readAll() throws DAOException;
}
