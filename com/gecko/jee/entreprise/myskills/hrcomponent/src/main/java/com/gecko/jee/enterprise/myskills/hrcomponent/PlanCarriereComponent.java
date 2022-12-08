/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriere;

/**
 * <b> Description : Interface de gestion du plan de carrière.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface PlanCarriereComponent {

	/**
	 * Ajoute un plan de carriere
	 * 
	 * @param entertienEvaluation
	 */
	void addOrUpdate(PlanCarriere planCarriere);

	/**
	 * lecture de tout les plans de carriere
	 * 
	 * @return
	 */
	List<PlanCarriere> readAll();
}
