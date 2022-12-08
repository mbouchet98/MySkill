/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.TypeEntretienEvaluationDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;

/**
 * <b> Description : Classe test du component d'entretien d'évaluation.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class EntretienEvaluationComponentImplTest {

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.hrcomponent.impl.EntretienEvaluationComponentImpl#readEntretienEvaluations()}.
	 */
	// @Test
	public void testReadEntretienEvaluations() {
		//creation d'un Mskuer
		Mskuser mskuserCollaborateur = new Mskuser("Dupond", "Louis", "Louis.dupont@bygecko.com", "1");		
		// creation des dates d'entretien
		Timestamp dateHeurDebut = Timestamp.valueOf("2021-03-18 11:00:00");
		Timestamp dateHeurFin = Timestamp.valueOf("2021-03-18 12:00:00");
		// 1- Lecture de tous entretiens d'évaluation
		EntretienEvaluationComponentImpl entretienEvaluationComponentImpl = new EntretienEvaluationComponentImpl();
		List<EntretienEvaluation> entretienEvaluations = new ArrayList<EntretienEvaluation>();
		entretienEvaluations = entretienEvaluationComponentImpl.readEntretienEvaluations();
		for (EntretienEvaluation entretienEvaluation : entretienEvaluations) {
			System.out.println(entretienEvaluation.getHeureDebut());
			//2-Validation
			assertEquals(mskuserCollaborateur.getFirstName(), entretienEvaluation.getMskuser().getFirstName());
			assertEquals(dateHeurDebut, entretienEvaluation.getHeureDebut());
			assertEquals(dateHeurFin, entretienEvaluation.getHeureFin());

		}
	}

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.hrcomponent.impl.EntretienEvaluationComponentImpl#addOrUpdate(com.gecko.jee.enterprise.myskills.persistence.impl.EntretienEvaluation)}.
	 */
	// @Test
	public void testAddOrUpdate() {
		fail("Not yet implemented");
	}

	// @Test
	public void typeEntretienEvaluationReadAll() {
		TypeEntretienEvaluationDAOImpl typeEntretienEvaluationDAOImpl = new TypeEntretienEvaluationDAOImpl();
		List<TypeEntretienEvaluation> typeEntretienEvaluations = new ArrayList<TypeEntretienEvaluation>();
		try {
			typeEntretienEvaluations = typeEntretienEvaluationDAOImpl.readAll();
			assertEquals(1, typeEntretienEvaluations.size());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
