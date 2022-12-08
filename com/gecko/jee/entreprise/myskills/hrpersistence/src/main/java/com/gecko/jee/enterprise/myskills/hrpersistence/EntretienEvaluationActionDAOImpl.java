/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluationAction;

/**
 * <b> Description : DAO permettant d'implémenter l'accès au donnée de la table
 * EntretienEvaluationAction.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class EntretienEvaluationActionDAOImpl extends DAOUtil
		implements EntretienEvaluationActionDAO<EntretienEvaluationAction> {

	/**
	 * Méthode de suppression d'une action
	 */
	@Override
	public void delete(EntretienEvaluationAction entretienEvaluationAction) throws DAOException {
		EntityManager entityManager = DAOUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.remove(entityManager.merge(entretienEvaluationAction));
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new DAOException(
					"Erreur lors de la suppression de l'action " + entretienEvaluationAction + " : " + e.getMessage());
		}

	}

	/**
	 * Méthode de lecture d'une action d'entretien d'évaluation
	 */
	@Override
	public EntretienEvaluationAction readUnEntretienEvaluationAction(
			EntretienEvaluationAction entretienEvaluationAction)
			throws DAOException {
		// Instance pour créer le CriteriaBuilder avec l'entity manager, qui vas initier
		// le CriteriaQuery pour créer la requête qui renverra un Objet
		// EntretienEvaluationAction.
		CriteriaBuilder criteriaBuilder = DAOUtil.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EntretienEvaluationAction> criteriaQueryEntretien = criteriaBuilder
				.createQuery(EntretienEvaluationAction.class);

		// La méthode Root<T> vas situé de sur qui va s'appliquer la requête
		Root<EntretienEvaluationAction> rootEntretienAction = criteriaQueryEntretien
				.from(EntretienEvaluationAction.class);

		// Créer le type du paramètre fournie au moment de la requête
		ParameterExpression<String> parameterDescriptionAction = criteriaBuilder.parameter(String.class);
		ParameterExpression<Timestamp> parameterDateLimiteAction = criteriaBuilder.parameter(Timestamp.class);
		ParameterExpression<String> parameterStatutAction = criteriaBuilder.parameter(String.class);
		ParameterExpression<EntretienEvaluation> parameterEntretienEvaluation = criteriaBuilder
				.parameter(EntretienEvaluation.class);

		// crée la requête
		criteriaQueryEntretien.select(rootEntretienAction).where(
				criteriaBuilder.equal(rootEntretienAction.get("description"), parameterDescriptionAction),
				criteriaBuilder.equal(rootEntretienAction.get("dateLimite"), parameterDateLimiteAction),
				criteriaBuilder.equal(rootEntretienAction.get("statut"), parameterStatutAction));
		// criteriaBuilder.equal(rootEntretienAction.get("entretienEvaluation"),
		// parameterEntretienEvaluation));

		// exécute la requête
		// Récupère la requête, ajoute les paramètre
		TypedQuery<EntretienEvaluationAction> typedQueryAction = DAOUtil.getEntityManager()
				.createQuery(criteriaQueryEntretien);
		typedQueryAction.setParameter(parameterDescriptionAction, entretienEvaluationAction.getDescription())
				.setParameter(parameterDateLimiteAction, entretienEvaluationAction.getDateLimite())
				.setParameter(parameterStatutAction, entretienEvaluationAction.getStatut());
		// .setParameter(parameterEntretienEvaluation,
		// entretienEvaluationAction.getEntretienEvaluation());

		// retourne l'entretiens d'évaluation
		EntretienEvaluationAction lEntretienEvaluationAction = typedQueryAction.getSingleResult();
		return lEntretienEvaluationAction;
	}

}
