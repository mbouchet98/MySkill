/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriere;

/**
 * <b> Description :DAO permettant d'implémenter l'accès au donnée de la table
 * PlanCarriere .</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class PlanCarriereDAOImpl extends DAOUtil implements PlanCarriereDOA<PlanCarriere> {

	/**
	 * Cette méthode va ajouter un plan de carriere ou modifier le plan de carriere
	 */
	@Override
	public void addOrUpdate(PlanCarriere planCarriere) throws DAOException {
		EntityManager entityManager = DAOUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.merge(planCarriere);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new DAOException(
					"Erreur lors de l'ajout ou la modification de " + planCarriere + " : " + e.getMessage());
		}
	}

	/**
	 * Cette métode lit tous le plan de carriere, ne sert qu'au test
	 */
	@Override
	public List<PlanCarriere> readAll() throws DAOException {
		// création de la requête
		String req = "Select Object(p) from PlanCarriere p";

		// exécution/récupération de la requête
		return DAOUtil.getEntityManager().createQuery(req, PlanCarriere.class).getResultList();

	}

}
