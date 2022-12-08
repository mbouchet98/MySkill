/**
 * 
 */
package com.gecko.jee.enterprise.hrpersistence;

import java.sql.Timestamp;

import com.gecko.jee.enterprise.myskills.hrpersistence.EntretienEvaluationActionDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluationAction;

/**
 * <b> Description : Classe de test pour les DAO liés à action.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class EntretienEvaluationActionDAOImplTest {

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.EntretienEvaluationActionDAOImpl#delete(com.gecko.jee.enterprise.myskills.persistence.impl.EntretienEvaluationAction)}.
	 */
	// @Test
	public void testDelete() {
		// 1- Lecture d'un Mskuser
//		Mskuser mskuserCollaborateur = new Mskuser("Dupond", "Louis", "Louis.dupont@bygecko.com", "1");
//		Mskuser mskuserManager = new Mskuser("Gresse", "julien", "julien.gresse@bygecko.com", "2");
//
//		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();
//
//		Mskuser monMskuserCollaborateur = null;
//		Mskuser monMskuserManager = null;
//		try {
//			monMskuserCollaborateur = mskuserDAOImpl.readUnMskuser(mskuserCollaborateur);
//			monMskuserManager = mskuserDAOImpl.readUnMskuser(mskuserManager);
//		} catch (DAOException e) {
//			fail(e.getMessage());
//		}
//
//		// 2- Validation d'un mskuser
//		assertEquals(mskuserCollaborateur.getFirstName(), monMskuserCollaborateur.getFirstName());
//		assertEquals(mskuserCollaborateur.getLastName(), monMskuserCollaborateur.getLastName());
//		assertEquals(mskuserCollaborateur.getMailAddress(), monMskuserCollaborateur.getMailAddress());
//
//		assertEquals(mskuserManager.getFirstName(), monMskuserManager.getFirstName());
//		assertEquals(mskuserManager.getLastName(), monMskuserManager.getLastName());
//		assertEquals(mskuserManager.getMailAddress(), monMskuserManager.getMailAddress());
//
//		// 3- Créer l'entretien d'évaluation a filtré
//		EntretienEvaluation entretienEvaluationFiltre = new EntretienEvaluation(
//				Timestamp.valueOf("2021-01-01 08:00:00"), monMskuserManager.getLiferayId(),
//				Timestamp.valueOf("2021-04-08 13:00:00"), Timestamp.valueOf("2021-04-08 14:00:00"),
//				monMskuserCollaborateur);
//		// 4- Lire l'entretien d'évaluation
//		EntretienEvaluationDAOImpl entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();
//
//		EntretienEvaluation monEntretienEvaluation;
//		try {
//			monEntretienEvaluation = entretienEvaluationDAOImpl.readUnEntretienEvaluation(entretienEvaluationFiltre);
//			// 6- Valiadation de l'entretien d'évaluation
//			assertEquals(entretienEvaluationFiltre.getDateCreation(), monEntretienEvaluation.getDateCreation());
//			assertEquals(entretienEvaluationFiltre.getHeureDebut(), monEntretienEvaluation.getHeureDebut());
//			assertEquals(entretienEvaluationFiltre.getHeureFin(), monEntretienEvaluation.getHeureFin());
//			assertEquals(entretienEvaluationFiltre.getMskuser().getFirstName(),
//					monEntretienEvaluation.getMskuser().getFirstName());
//			assertEquals(entretienEvaluationFiltre.getFkLiferayId(), monEntretienEvaluation.getFkLiferayId());
			// 2- Lire l'action de l'entretien d'évalaution
			EntretienEvaluationAction entretienEvaluationAction = new EntretienEvaluationAction(
					Timestamp.valueOf("2024-03-18 11:00:00"), "Passer un concour", "En cours");

			EntretienEvaluationActionDAOImpl entretienEvaluationActionDAOImpl = new EntretienEvaluationActionDAOImpl();

			try {
				entretienEvaluationActionDAOImpl.delete(entretienEvaluationAction);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//		} catch (DAOException e) {
//			fail(e.getMessage());
//		}

		// 3- Supprimer de la liste
	}

}
