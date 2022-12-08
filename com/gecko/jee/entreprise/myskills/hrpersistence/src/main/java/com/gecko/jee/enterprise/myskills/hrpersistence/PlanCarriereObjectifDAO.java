package com.gecko.jee.enterprise.myskills.hrpersistence;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;

/**
 * 
 * <b> Description : Permet l'accès aux données des objectif de plan de
 * carrière.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface PlanCarriereObjectifDAO {
	/**
	 * Méthode de suppression d'un objectif de plan de carrières
	 * 
	 * @param planCarriereObjectif
	 * @throws DAOException
	 */
	void delete(PlanCarriereObjectif planCarriereObjectif) throws DAOException;

	/**
	 * Recherche un plan de carrière Objectif
	 * 
	 * @param planCarriereObjectif
	 * @return un planCarriereObjectif
	 * @throws DAOException
	 */
	PlanCarriereObjectif read(PlanCarriereObjectif planCarriereObjectif) throws DAOException;
}
