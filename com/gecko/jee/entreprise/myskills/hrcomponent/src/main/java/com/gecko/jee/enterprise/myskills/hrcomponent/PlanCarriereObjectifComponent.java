package com.gecko.jee.enterprise.myskills.hrcomponent;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;

/**
 * 
 * <b> Description : Iterface de gestion des Objectifs de plan de carriere.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface PlanCarriereObjectifComponent {

	/**
	 * Supprime un objectif de plan de carriere
	 * 
	 * @param planCarriereObjectif
	 */
	void delete(PlanCarriereObjectif planCarriereObjectif);

	/**
	 * recherche un objectif
	 * 
	 * @param planCarriereObjectif
	 * @return un planCarriereObjectif
	 */
	PlanCarriereObjectif read(PlanCarriereObjectif planCarriereObjectif);
}
