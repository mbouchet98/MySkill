/**
 * 
 */
package com.gecko.jee.enterprise.hrpersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.TypeEntretienEvaluationDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;

/**
 * <b> Description : Classe de test pour les DAO liés aux type d'entretien.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class TypeEntretienEvaluationDAOImplTest {

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.TypeEntretienEvaluationDAOImpl#readAll()}.
	 */
	// @Test
	public void testReadAll() {
		// 1- Lister tout les Type d'entretien d'évaluation
		TypeEntretienEvaluationDAOImpl typeEntretienEvaluationDAOImpl = new TypeEntretienEvaluationDAOImpl();
		try {
			List<TypeEntretienEvaluation> typeEntretienEvaluations = typeEntretienEvaluationDAOImpl.readAll();
			for (TypeEntretienEvaluation typeEntretienEvaluation : typeEntretienEvaluations) {
				System.out.println(typeEntretienEvaluation.toString());
			}

			// 2-validation
			assertEquals(2, typeEntretienEvaluations.size());
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

}
