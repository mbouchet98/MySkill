package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluationAction;

/**
 * 
 * <b> Description : Permet l'accès aux données d'Entretien d'Evaluation.</b>
 * <p>
 * 
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface EntretienEvaluationDAO<EntretienEvaluation> {

	/**
	 * Méthode permettant d'ajouter ou modifier un entretien.
	 * 
	 * @param entertienEvaluation
	 * @return
	 */

	void addOrUpdate(EntretienEvaluation entertienEvaluation) throws DAOException;

	/**
	 * Méthode pour lister les entretiens d'évalaution filtré par date
	 * 
	 * @param filtres
	 * @return
	 * @throws DAOException
	 */
	List<EntretienEvaluation> readEntretienEvaluation(EntretienEvaluation filtres) throws DAOException;

	/**
	 * Méthode pour lister tout les entretiens d'évaluation, ne sert qu'au test.
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<EntretienEvaluation> readAll() throws DAOException;

	/**
	 * Methode de lecture d'un entretien d'évaluation filtré par les date, heure
	 * début, fin mskuser et liferayId
	 * 
	 * @param entretienEvaluation
	 * @return
	 * @throws DAOException
	 */
	EntretienEvaluation readUnEntretienEvaluation(EntretienEvaluation entretienEvaluation) throws DAOException;

	/**
	 * Méthode de suppression d'un entretien d'évaluation par la date
	 * d'entretien,l'heure de début et de fin, le mskuser et le liferayId
	 * 
	 * @param entretienEvaluation
	 * @throws DAOException
	 */
	void deleteEntretienEvaluation(EntretienEvaluation entretienEvaluation) throws DAOException;

	/**
	 * Methode qui permet de supprimer un action de l'entretien d'évalaution
	 * 
	 * @param entretienEvaluationAction
	 * @throws DAOException
	 */
	void deleteEntertienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction) throws DAOException;


}
