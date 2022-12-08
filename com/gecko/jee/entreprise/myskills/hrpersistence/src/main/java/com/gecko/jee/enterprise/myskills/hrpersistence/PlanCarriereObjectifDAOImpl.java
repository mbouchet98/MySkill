/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriere;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;

/**
 * <b> Description : DAO permettant d'implémenter l'accès au donnée de la table
 * PlanCarriereObjectif.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class PlanCarriereObjectifDAOImpl implements PlanCarriereObjectifDAO {

	/**
	 * Méthode de suppression d'un objectif
	 */
	@Override
	public void delete(PlanCarriereObjectif planCarriereObjectif) throws DAOException {
		EntityManager entityManager = DAOUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.remove(entityManager.merge(planCarriereObjectif));
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new DAOException(
					"Erreur lors de la suppression de l'action " + planCarriereObjectif + " : " + e.getMessage());
		}

	}

	/**
	 * Méthode de recherche d'un plan de carrière Objectif
	 */
	@Override
	public PlanCarriereObjectif read(PlanCarriereObjectif planCarriereObjectif) throws DAOException {
		// Instance pour créer le CriteriaBuilder avec l'entity manager, qui vas initier
		// le CriteriaQuery pour créer la requête qui renverra un Objet
		// PlanCarrièreObjectif.
		CriteriaBuilder criteriaBuilder = DAOUtil.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PlanCarriereObjectif> criteriaQueryObjectif = criteriaBuilder
				.createQuery(PlanCarriereObjectif.class);

		// La méthode Root<T> vas situé de sur qui va s'appliquer la requête
		Root<PlanCarriereObjectif> rootObjectif = criteriaQueryObjectif.from(PlanCarriereObjectif.class);

		// Créer le type du paramètre fournie au moment de la requête
		ParameterExpression<String> parameterNomObjectif = criteriaBuilder.parameter(String.class);
		ParameterExpression<String> parameterDescriptionObjectif = criteriaBuilder.parameter(String.class);
		ParameterExpression<String> parameterStatutObjectif = criteriaBuilder.parameter(String.class);
		ParameterExpression<PlanCarriere> parameterPlanCarriere = criteriaBuilder.parameter(PlanCarriere.class);

		// crée la requête
		criteriaQueryObjectif.select(rootObjectif).where(
				criteriaBuilder.equal(rootObjectif.get("nom"), parameterNomObjectif),
				criteriaBuilder.equal(rootObjectif.get("description"), parameterDescriptionObjectif),
				criteriaBuilder.equal(rootObjectif.get("statut"), parameterStatutObjectif),
				criteriaBuilder.equal(rootObjectif.get("planCarriere"), parameterPlanCarriere));

		// exécute la requête
		// Récupère la requête, ajoute les paramètre
		TypedQuery<PlanCarriereObjectif> typedQueryObjectif = DAOUtil.getEntityManager()
				.createQuery(criteriaQueryObjectif);
		typedQueryObjectif.setParameter(parameterNomObjectif, planCarriereObjectif.getNom())
				.setParameter(parameterDescriptionObjectif, planCarriereObjectif.getDescription())
				.setParameter(parameterStatutObjectif, planCarriereObjectif.getStatut())
				.setParameter(parameterPlanCarriere, planCarriereObjectif.getPlanCarriere());

		// retourne l'objectif de plan de carrière
		PlanCarriereObjectif plCarriereObjectif = typedQueryObjectif.getSingleResult();
		return plCarriereObjectif;
	}

}
