
package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;

/**
 * <b> Description : DAO permettant d'implémenter l'accès au donnée de la table
 * TypeEntretienEvaluation.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class TypeEntretienEvaluationDAOImpl extends DAOUtil
		implements TypeEntretienEvaluationDAO<TypeEntretienEvaluation> {

	/**
	 * Cette méthode récupère tous les types entretien d'évaluation.
	 * 
	 * @return tout les types
	 */
	@Override
	public List<TypeEntretienEvaluation> readAll() throws DAOException {
		// création de la requête
		String req = "Select Object(t) from TypeEntretienEvaluation t";

		// exécution/récupération de la requête
		return DAOUtil.getEntityManager().createQuery(req, TypeEntretienEvaluation.class).getResultList();
	}

}
