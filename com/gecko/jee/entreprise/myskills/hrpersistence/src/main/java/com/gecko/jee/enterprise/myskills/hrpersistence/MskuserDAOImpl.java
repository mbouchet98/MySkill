/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;

/**
 * <b> Description : DAO permettant d'implémenter l'accès au donnée de la table
 * Mskuser.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class MskuserDAOImpl extends DAOUtil implements MskuserDAO<Mskuser> {

	/**
	 * Cette méthode va ajouter un Mskuser ou le modifier.
	 * 
	 * @param mskuser
	 */
	@Override
	public void addOrUpdate(Mskuser mskuser) throws DAOException {
		// Crée la connexion à la base
		EntityManager entityManager = DAOUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			// execure l'ajout ou la modification du mskuser
			entityManager.merge(mskuser);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new DAOException("Erreur lors de l'ajout de mskuser " + mskuser + " : " + e.getMessage());
		}
	}

	/**
	 * Cette méthode récupère tous les mskusers
	 * 
	 * @return tous les mskusers
	 */
	@Override
	public List<Mskuser> readAllMskuser() throws DAOException {
		// création de la requête
		String req = "Select Object(m) from Mskuser m";

		// exécution/récupération de la requête
		return DAOUtil.getEntityManager().createQuery(req, Mskuser.class).getResultList();
	}

	/**
	 * Cette méthode récupère un mskuser avec son nom, prenom égale au filtre
	 * mskuser
	 * 
	 * @param mskuser
	 * @return un Mskuser
	 */
	@Override
	public Mskuser readUnMskuser(Mskuser mskuser) throws DAOException {
		// Instance pour créer le CriteriaBuilder avec l'entity manager, qui vas initier
		// le CriteriaQuery pour créer la requête qui renverra un Objet
		// Mskuser.
		CriteriaBuilder criteriaBuilder = DAOUtil.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Mskuser> criteriaQueryMskuser = criteriaBuilder
				.createQuery(Mskuser.class);

		// La méthode Root<T> vas situé de sur qui va s'appliquer la requête
		Root<Mskuser> rootMskuser = criteriaQueryMskuser.from(Mskuser.class);

		// Créer le type du paramètre fournie au moment de la requête
		ParameterExpression<String> parameterMskuserNom = criteriaBuilder.parameter(String.class);
		ParameterExpression<String> parameterMskuserPrenom = criteriaBuilder.parameter(String.class);
		ParameterExpression<String> parameterMskuserEmail = criteriaBuilder.parameter(String.class);
		ParameterExpression<String> parameterMskuserLiferayId = criteriaBuilder.parameter(String.class);
		// crée la requête, SELECT * FROM public.mskuser where
		// first_name = mskuser and last_name
		// =mskuser and mail_adresse = mskuser;
		criteriaQueryMskuser.select(rootMskuser).where(
				criteriaBuilder.equal(rootMskuser.get("firstName"), parameterMskuserPrenom),
				criteriaBuilder.equal(rootMskuser.get("lastName"), parameterMskuserNom),
				criteriaBuilder.equal(rootMskuser.get("mailAddress"), parameterMskuserEmail),
				criteriaBuilder.equal(rootMskuser.get("liferayId"), parameterMskuserLiferayId));

		// exécute la requête
		// Récupère la requête, ajoute les paramètre
		TypedQuery<Mskuser> typedQueryMskuser = DAOUtil.getEntityManager()
				.createQuery(criteriaQueryMskuser);
		typedQueryMskuser.setParameter(parameterMskuserPrenom, mskuser.getFirstName())
				.setParameter(parameterMskuserNom, mskuser.getLastName())
				.setParameter(parameterMskuserEmail, mskuser.getMailAddress())
				.setParameter(parameterMskuserLiferayId, mskuser.getLiferayId());

		// retourne le mskuser
		Mskuser leMskuser = typedQueryMskuser.getSingleResult();
		return leMskuser;
	}

	/**
	 * Cette methode récupère un Mskuser via liferayId de la session connecter.
	 * 
	 * @param lirayId
	 * @return un Mskuser
	 */
	@Override
	public Mskuser readByLiferayId(Mskuser liferayId) throws DAOException {
		String req = "Select Object(m) from Mskuser m where m.liferayId = " + liferayId.getLiferayId();
		return DAOUtil.getEntityManager().createQuery(req, Mskuser.class).getSingleResult();
	}

}
