/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent.impl;

import java.util.ArrayList;
import java.util.List;

import com.gecko.jee.enterprise.myskills.hrcomponent.PlanCarriereComponent;
import com.gecko.jee.enterprise.myskills.hrpersistence.PlanCarriereDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriere;

/**
 * <b> Description : gère les plan de carrière.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class PlanCarriereComponentImpl implements PlanCarriereComponent {

	/**
	 * Ajout et modifie un plan de carriere
	 */
	@Override
	public void addOrUpdate(PlanCarriere planCarriere) {
		PlanCarriereDAOImpl planCarriereDAOImpl = new PlanCarriereDAOImpl();
		try {
			planCarriereDAOImpl.addOrUpdate(planCarriere);
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<PlanCarriere> readAll() {
		// TODO Auto-generated method stub
		PlanCarriereDAOImpl planCarriereDAOImpl = new PlanCarriereDAOImpl();
		List<PlanCarriere> planCarrieres = new ArrayList<PlanCarriere>();
		try {
			planCarrieres = planCarriereDAOImpl.readAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return planCarrieres;
	}

}
