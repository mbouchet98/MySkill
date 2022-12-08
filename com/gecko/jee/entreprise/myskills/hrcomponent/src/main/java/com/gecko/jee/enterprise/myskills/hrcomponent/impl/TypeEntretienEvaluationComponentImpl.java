/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent.impl;

import java.util.ArrayList;
import java.util.List;

import com.gecko.jee.enterprise.myskills.hrcomponent.TypeEntretienEvaluationComponent;
import com.gecko.jee.enterprise.myskills.hrpersistence.TypeEntretienEvaluationDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;

/**
 * <b> Description : Gère les types entretien évaluation.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class TypeEntretienEvaluationComponentImpl implements TypeEntretienEvaluationComponent {

	/**
	 * Récupère la liste des Type d'entretien d'évaluation
	 */
	@Override
	public List<TypeEntretienEvaluation> readAll() {
		TypeEntretienEvaluationDAOImpl typeEntretienEvaluationDAOImpl = new TypeEntretienEvaluationDAOImpl();
		List<TypeEntretienEvaluation> typeEntretienEvaluations = new ArrayList<TypeEntretienEvaluation>();

		try {
			typeEntretienEvaluations = typeEntretienEvaluationDAOImpl.readAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return typeEntretienEvaluations;
	}

}
