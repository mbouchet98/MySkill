/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent.impl;

import com.gecko.jee.enterprise.myskills.hrcomponent.PlanCarriereObjectifComponent;
import com.gecko.jee.enterprise.myskills.hrpersistence.PlanCarriereObjectifDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;

/**
 * <b> Description : g�re les Objectif de plan de carrière.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class PlanCarriereObjectifComponentImpl implements PlanCarriereObjectifComponent {

	/**
	 * Supprime un objectif de plan de carriere
	 */
	@Override
	public void delete(PlanCarriereObjectif planCarriereObjectif) {
		PlanCarriereObjectifDAOImpl planCarriereObjectifDAOImpl = new PlanCarriereObjectifDAOImpl();

		try {
			planCarriereObjectifDAOImpl.delete(planCarriereObjectif);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * recherche un objectif
	 */
	@Override
	public PlanCarriereObjectif read(PlanCarriereObjectif planCarriereObjectif) {
		PlanCarriereObjectifDAOImpl planCarriereObjectifDAOImpl = new PlanCarriereObjectifDAOImpl();
		PlanCarriereObjectif plObjectif = new PlanCarriereObjectif();
		try {
			plObjectif = planCarriereObjectifDAOImpl.read(planCarriereObjectif);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return plObjectif;
	}

}
