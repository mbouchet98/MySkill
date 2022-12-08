/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;

/**
 * <b> Description : Permet l'accès aux données de plan de carriere.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface PlanCarriereDOA<PlanCarriere> {

	/**
	 * 
	 * Cette méthode Ajoute ou Modifie le plan de carriere.
	 * 
	 * @param planCarriere
	 * @throws DAOException
	 */
	void addOrUpdate(PlanCarriere planCarriere) throws DAOException;

	/**
	 * Cette Méthode Lit tous les plans de carrière
	 * 
	 * @return liste des tout les plans de carrière
	 * @throws DAOException
	 */
	List<PlanCarriere> readAll() throws DAOException;

}
