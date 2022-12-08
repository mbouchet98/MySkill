/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent.impl;

import java.util.ArrayList;
import java.util.List;

import com.gecko.jee.enterprise.myskills.hrcomponent.EntretienEvaluationComponent;
import com.gecko.jee.enterprise.myskills.hrpersistence.EntretienEvaluationDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;


/**
 * <b> Description : g�re les entretien d'�valuation .</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class EntretienEvaluationComponentImpl implements EntretienEvaluationComponent {

	/**
	 * r�cup�re la liste des entretien d'�valuation
	 */
	@Override
	public List<EntretienEvaluation> readEntretienEvaluations() {
		EntretienEvaluationDAOImpl entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();
		List<EntretienEvaluation> entretienEvaluations = new ArrayList<EntretienEvaluation>();
		try {
			entretienEvaluations = entretienEvaluationDAOImpl.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entretienEvaluations;
	}

	/**
	 * Ajoute et/ou modifie un entretien d'�valuation
	 */
	@Override
	public void addOrUpdate(EntretienEvaluation entertienEvaluation) {
		EntretienEvaluationDAOImpl entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();
		try {
			entretienEvaluationDAOImpl.addOrUpdate(entertienEvaluation);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * récuppère un entretien d'évaluation
	 */
	@Override
	public EntretienEvaluation readEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		EntretienEvaluationDAOImpl entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();
		EntretienEvaluation monEntretienEvaluation = new EntretienEvaluation();
		try {
			monEntretienEvaluation = entretienEvaluationDAOImpl.readUnEntretienEvaluation(entretienEvaluation);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return monEntretienEvaluation;
	}

	/**
	 * Supprime un entretien d'évaluation
	 */
	@Override
	public void deleteEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		EntretienEvaluationDAOImpl entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();

		try {
			entretienEvaluationDAOImpl.deleteEntretienEvaluation(entretienEvaluation);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
