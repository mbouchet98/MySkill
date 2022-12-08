/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent.impl;

import com.gecko.jee.enterprise.myskills.hrcomponent.EntretienEvaluationActionComponent;
import com.gecko.jee.enterprise.myskills.hrpersistence.EntretienEvaluationActionDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluationAction;

/**
 * <b> Description : g�re les action d'entretien d'�valuation.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class EntretienEvaluationActionComponentImpl implements EntretienEvaluationActionComponent {

	/**
	 * Supprime une action d'entretien d'évaluation
	 */
	@Override
	public void delete(EntretienEvaluationAction entretienEvaluationAction) {
		EntretienEvaluationActionDAOImpl entretienEvaluationActionDAOImpl = new EntretienEvaluationActionDAOImpl();

		try {
			entretienEvaluationActionDAOImpl.delete(entretienEvaluationAction);
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}

}
