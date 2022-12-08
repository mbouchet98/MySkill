package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.sql.Timestamp;
import java.util.List;

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
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;

/**
 * 
 * <b> Description : DAO permettant d'implémenter l'accès au donnée de la table
 * EntretienEvaluation..</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class EntretienEvaluationDAOImpl extends DAOUtil implements EntretienEvaluationDAO<EntretienEvaluation> {

	/**
	 * Cette méthode va ajouter un entretien d'évaluation ou modifier l'entretien
	 * d'évaluation
	 * 
	 * @param entretienEvaluation
	 *
	 * 
	 */
	@Override
	public void addOrUpdate(EntretienEvaluation entretienEvaluation) throws DAOException {
		// Crée la connexion avec la base
		EntityManager entityManager = DAOUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			// ajout = persist, mais fonctionne aussi avec merge = update
			// exécute la requête Update/Insert
			entityManager.merge(entretienEvaluation);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new DAOException("Erreur lors de l'ajout de l'entretien d'évaluation " + entretienEvaluation + " : "
					+ e.getMessage());
		}
	}

	/**
	 * Cette méthode récupère tous les entretien d'évaluation
	 * 
	 * @return tout les entretiens d'évalaution
	 */
	@Override
	public List<EntretienEvaluation> readAll() throws DAOException {
		// création de la requête
		String req = "Select Object(l) from EntretienEvaluation l";

		// exécution/récupération de la requête
		return DAOUtil.getEntityManager().createQuery(req, EntretienEvaluation.class).getResultList();
	}

	/**
	 * Cette méthode récupère les entretiens d'évaluation dont la date d'évaluation
	 * supérieur ou égale à la date indiquée dans le filtre.
	 * 
	 * @param filtre : Par la date d'entretien.
	 * @return une liste des entretien d'évaluation ou une liste vide.
	 */
	@Override
	public List<EntretienEvaluation> readEntretienEvaluation(EntretienEvaluation filtre) throws DAOException {

		// Instance pour créer le CriteriaBuilder avec l'entity manager, qui vas initier
		// le CriteriaQuery pour créer la requête qui renverra un Objet
		// EntretienEvaluation.
		CriteriaBuilder criteriaBuilder = DAOUtil.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EntretienEvaluation> criteriaQueryEntretien = criteriaBuilder
				.createQuery(EntretienEvaluation.class);

		// La méthode Root<T> vas situé de sur qui va s'appliquer la requête
		Root<EntretienEvaluation> rootEntretien = criteriaQueryEntretien.from(EntretienEvaluation.class);

		// Créer le type du paramètre fournie au moment de la requête
		ParameterExpression<Timestamp> parameterExpressionDate = criteriaBuilder.parameter(Timestamp.class);

		// crée la requête, select * from entretie_evaluation where date_evaluation >=
		// filtre
		criteriaQueryEntretien.select(rootEntretien)
				.where(criteriaBuilder.greaterThanOrEqualTo(rootEntretien.get("dateCreation"),
						parameterExpressionDate));

		// exécute la requête
		// Récupère la requête, ajoute les paramètre
		TypedQuery<EntretienEvaluation> typedQueryEntretien = DAOUtil.getEntityManager()
				.createQuery(criteriaQueryEntretien);
		typedQueryEntretien.setParameter(parameterExpressionDate, filtre.getDateCreation());

		// retourne les entretiens d'évaluation
		List<EntretienEvaluation> listeEntretienEvaluations = typedQueryEntretien.getResultList();
		return listeEntretienEvaluations;
	}

	
	/**
	 * Cette méthode récupère l'entretiens d'évaluation dont la date d'évaluation,
	 * l'heure de début, l'heure de fin de l'évaluation, le nom du collaborateur et
	 * le type d'entretrien est égale aux date, au nom, et au type entretien
	 * indiquée dans le filtre.
	 * 
	 * @param entretienEvaluation rechercher un entretien d'évaluation
	 * @return un Entretien d'évalaution
	 */
	public EntretienEvaluation readUnEntretienEvaluation(EntretienEvaluation entretienEvaluation) throws DAOException {
		// Instance pour créer le CriteriaBuilder avec l'entity manager, qui vas initier
		// le CriteriaQuery pour créer la requête qui renverra un Objet
		// EntretienEvaluation.
		CriteriaBuilder criteriaBuilder = DAOUtil.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EntretienEvaluation> criteriaQueryEntretien = criteriaBuilder
				.createQuery(EntretienEvaluation.class);

		// La méthode Root<T> vas situé de sur qui va s'appliquer la requête
		Root<EntretienEvaluation> rootEntretien = criteriaQueryEntretien.from(EntretienEvaluation.class);

		// Créer le type du paramètre fournie au moment de la requête
		ParameterExpression<Timestamp> parameterDateHeureDebut = criteriaBuilder.parameter(Timestamp.class);
		ParameterExpression<Timestamp> parameterDateHeureFin = criteriaBuilder.parameter(Timestamp.class);
		ParameterExpression<Mskuser> parameterMskuser = criteriaBuilder.parameter(Mskuser.class);
		ParameterExpression<TypeEntretienEvaluation> parameterTypeEntretienEvaluation = criteriaBuilder
				.parameter(TypeEntretienEvaluation.class);

		// crée la requête, SELECT * FROM public.entretien_evaluation where
		// date_evaluation = entretienEvaluation and date_heure_debut
		// =entretienEvaluation and date_heure_fin = entretienEvaluation and id_mskuser
		// = entretienEvaluation and liferay_id = entretienEvaluation;
		criteriaQueryEntretien.select(rootEntretien).where(
				criteriaBuilder.equal(rootEntretien.get("heureDebut"), parameterDateHeureDebut),
				criteriaBuilder.equal(rootEntretien.get("heureFin"), parameterDateHeureFin),
				criteriaBuilder.equal(rootEntretien.get("mskuser"), parameterMskuser),
				criteriaBuilder.equal(rootEntretien.get("typeEntretienEvaluation"), parameterTypeEntretienEvaluation));

		// exécute la requête
		// Récupère la requête, ajoute les paramètre
		TypedQuery<EntretienEvaluation> typedQueryEntretien = DAOUtil.getEntityManager()
				.createQuery(criteriaQueryEntretien);
		typedQueryEntretien.setParameter(parameterDateHeureDebut, entretienEvaluation.getHeureDebut())
				.setParameter(parameterDateHeureFin, entretienEvaluation.getHeureFin())
				.setParameter(parameterMskuser, entretienEvaluation.getMskuser())
				.setParameter(parameterTypeEntretienEvaluation, entretienEvaluation.getTypeEntretienEvaluation());

		// retourne l'entretiens d'évaluation
		EntretienEvaluation lEntretienEvaluation = typedQueryEntretien.getSingleResult();
		return lEntretienEvaluation;

	}


	/**
	 * Cette Méthode supprime un entretien d'évaluation par la date
	 * d'entretien,l'heure de début et de fin, le mskuser et le liferayId
	 * 
	 * @param entretienEvaluation
	 */
	public void deleteEntretienEvaluation(EntretienEvaluation entretienEvaluation) throws DAOException {
		// Crée la connexion avec la base
		EntityManager entityManager = DAOUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			// exécute la requête delete
			entityManager.remove(entityManager.merge(entretienEvaluation));
			
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new DAOException("Erreur lors de la suppression de l'entretien d'évaluation " + entretienEvaluation
					+ " : " + e.getMessage());
		}
	}


	/**
	 * Catte méthode va supprimer une action d'entretien d'évaluation
	 * 
	 * @param entretienEvaluation
	 */
	@Override
	public void deleteEntertienEvaluationAction(EntretienEvaluationAction entretienEvaluationAction)
			throws DAOException {
		// Crée la connexion avec la base
		EntityManager entityManager = DAOUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			// exécute la requête delete
			entityManager.detach(entityManager.merge(entretienEvaluationAction));

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new DAOException("Erreur lors de la suppression de l'entretien d'évaluation "
					+ entretienEvaluationAction
					+ " : " + e.getMessage());
		}

	}

}
