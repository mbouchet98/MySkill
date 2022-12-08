package com.gecko.jee.enterprise.myskills.hrcomponent;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;

/**
 * <b> Description : Interface de gestion des types entretiens d'�valuation.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface TypeEntretienEvaluationComponent {
	/**
	 * récupère tout les types d'entretien d'évalaution
	 * 
	 * @return
	 */
	List<TypeEntretienEvaluation> readAll();
}
