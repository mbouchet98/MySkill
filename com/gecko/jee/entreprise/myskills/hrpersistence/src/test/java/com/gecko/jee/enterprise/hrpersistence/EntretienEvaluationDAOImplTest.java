/**
 * 
 */
package com.gecko.jee.enterprise.hrpersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.EntretienEvaluationDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.MskuserDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.TypeEntretienEvaluationDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluation;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.EntretienEvaluationAction;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.TypeEntretienEvaluation;

/**
 * <b> Description : Classe de test pour les DAO liés à l'évaluation.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class EntretienEvaluationDAOImplTest {

	private EntretienEvaluationDAOImpl entretienEvaluationDAOImpl;
	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.EntretienEvaluationDAOImpl#add(com.gecko.jee.enterprise.myskills.persistence.impl2.EntretienEvaluation)}.
	 */
	// @Test
	public void testAddOrUpdate() {

		/**
		 * Partie Ajouter
		 */

		// 1- Lecture d'un Mskuser
		Mskuser mskuserCollaborateur = new Mskuser("Henry", "Gammel", "henry.gammel@bygecko.com", "1");
		Mskuser mskuserManager = new Mskuser("Julien", "Gresse", "julien.gresse@bygecko.com", "4");

		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();

		Mskuser monMskuserCollaborateur = null;
		Mskuser monMskuserManager = null;
		try {
			monMskuserCollaborateur = mskuserDAOImpl.readUnMskuser(mskuserCollaborateur);
			monMskuserManager = mskuserDAOImpl.readUnMskuser(mskuserManager);
		} catch (DAOException e) {
			fail(e.getMessage());
		}

		// 2- Validation d'un mskuser
		assertEquals(mskuserCollaborateur.getFirstName(), monMskuserCollaborateur.getFirstName());
		assertEquals(mskuserCollaborateur.getLastName(), monMskuserCollaborateur.getLastName());
		assertEquals(mskuserCollaborateur.getMailAddress(), monMskuserCollaborateur.getMailAddress());

		assertEquals(mskuserManager.getFirstName(), monMskuserManager.getFirstName());
		assertEquals(mskuserManager.getLastName(), monMskuserManager.getLastName());
		assertEquals(mskuserManager.getMailAddress(), monMskuserManager.getMailAddress());

		// 3- Création d'un évaluation par le manager pour le collborateur, sans action

		Timestamp dateEntretienEvaluation = Timestamp.valueOf("2021-06-22 08:00:00");
		Timestamp dateHeurDebut = Timestamp.valueOf("2021-06-22 11:00:00");
		Timestamp dateHeurFin = Timestamp.valueOf("2021-06-22 12:00:00");

		// Ajoute d'un type d'entretien
		EntretienEvaluation entretienEvaluation = new EntretienEvaluation();
		TypeEntretienEvaluation monTypeEntretienEvaluation = new TypeEntretienEvaluation();
		TypeEntretienEvaluationDAOImpl typeEntretienEvaluationDAO = new TypeEntretienEvaluationDAOImpl();
		List<TypeEntretienEvaluation> typeEntretienEvaluations;
		try {
			typeEntretienEvaluations = typeEntretienEvaluationDAO.readAll();
			for (TypeEntretienEvaluation tEntretienEvaluation : typeEntretienEvaluations) {
				if (tEntretienEvaluation.getLibelle().equals("Annuel")) {

					entretienEvaluation = new EntretienEvaluation(
							"Entretien Annuel de Louis par julien définition des futures projets",
							dateEntretienEvaluation, monMskuserManager.getLiferayId(), dateHeurDebut, dateHeurFin,
							monMskuserCollaborateur, tEntretienEvaluation);
					monTypeEntretienEvaluation = tEntretienEvaluation;
					// 4- Vérification des liens entre les entités mskuser, entretientEvaluation

					assertEquals(monMskuserManager.getLiferayId(), entretienEvaluation.getFkLiferayId());
					assertEquals(monMskuserCollaborateur.getFirstName(),
							entretienEvaluation.getMskuser().getFirstName());
				}
			}
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		// 5- Ajout d'actions à l'entretien

		Timestamp dateLimiteAction1 = Timestamp.valueOf("2022-03-18 11:00:00");
		Timestamp dateLimiteAction2 = Timestamp.valueOf("2023-03-18 11:00:00");
		Timestamp dateLimiteAction3 = Timestamp.valueOf("2024-03-18 11:00:00");

		EntretienEvaluationAction entretienEvaluationAction1 = new EntretienEvaluationAction(dateLimiteAction1,
				"Faire des formations", "En cours");
		entretienEvaluationAction1.setEntretienEvaluation(entretienEvaluation);

		EntretienEvaluationAction entretienEvaluationAction2 = new EntretienEvaluationAction(dateLimiteAction2,
				"Passer différents entretien", "En cours");
		entretienEvaluationAction2.setEntretienEvaluation(entretienEvaluation);

		EntretienEvaluationAction entretienEvaluationAction3 = new EntretienEvaluationAction(dateLimiteAction3,
				"Passer un concour", "En cours");
		entretienEvaluationAction3.setEntretienEvaluation(entretienEvaluation);

		// 6- Création de la liste d'action d'entretien d'évaluation
		List<EntretienEvaluationAction> listEntretienEvaluationActions = new ArrayList<EntretienEvaluationAction>();
		listEntretienEvaluationActions.add(entretienEvaluationAction1);
		listEntretienEvaluationActions.add(entretienEvaluationAction2);
		listEntretienEvaluationActions.add(entretienEvaluationAction3);

		entretienEvaluation.setEntretienEvaluationActions(listEntretienEvaluationActions);

		entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();

		try {
			entretienEvaluationDAOImpl.addOrUpdate(entretienEvaluation);
		} catch (DAOException e) {
			fail(e.getMessage());
		}

		// 7- Vérification du lien avec les actions

		List<EntretienEvaluation> listeEntretienEvaluation = new ArrayList<EntretienEvaluation>();
		try {
			listeEntretienEvaluation = entretienEvaluationDAOImpl.readAll();
			for (EntretienEvaluation unEntretienEvaluation : listeEntretienEvaluation) {
				assertEquals(entretienEvaluation.getDateCreation(), unEntretienEvaluation.getDateCreation());
				assertEquals(entretienEvaluation.getHeureDebut(), unEntretienEvaluation.getHeureDebut());
				assertEquals(entretienEvaluation.getHeureFin(), unEntretienEvaluation.getHeureFin());
				assertEquals(entretienEvaluation.getCompteRendu(), unEntretienEvaluation.getCompteRendu());
				assertEquals(entretienEvaluation.getEntretienEvaluationActions().size(),
						unEntretienEvaluation.getEntretienEvaluationActions().size());
			}
		} catch (DAOException e) {
			e.getMessage();
		}



		/**
		 * Partie Modifier
		 */



		// 8- Créer l'entretien d'évaluation a filtré
		EntretienEvaluation entretienEvaluationFiltre = new EntretienEvaluation(
				Timestamp.valueOf("2021-06-22 08:00:00"), monMskuserManager.getLiferayId(),
				Timestamp.valueOf("2021-06-22 11:00:00"), Timestamp.valueOf("2021-06-22 12:00:00"),
				monMskuserCollaborateur, monTypeEntretienEvaluation);

		EntretienEvaluation monEntretienEvaluation;
		try {
			monEntretienEvaluation = entretienEvaluationDAOImpl.readUnEntretienEvaluation(entretienEvaluationFiltre);
			// 9- Valiadation de l'entretien d'évaluation
			assertEquals(entretienEvaluationFiltre.getDateCreation(), monEntretienEvaluation.getDateCreation());
			assertEquals(entretienEvaluationFiltre.getHeureDebut(), monEntretienEvaluation.getHeureDebut());
			assertEquals(entretienEvaluationFiltre.getHeureFin(), monEntretienEvaluation.getHeureFin());
			assertEquals(entretienEvaluationFiltre.getMskuser().getFirstName(),
					monEntretienEvaluation.getMskuser().getFirstName());
			assertEquals(entretienEvaluationFiltre.getFkLiferayId(), monEntretienEvaluation.getFkLiferayId());

			// 10- Modification de l'entretien

			List<TypeEntretienEvaluation> listTypeEntretienEvaluation = typeEntretienEvaluationDAO.readAll();
			for (TypeEntretienEvaluation unTypeEntretienEvaluation : listTypeEntretienEvaluation) {
				if (unTypeEntretienEvaluation.getLibelle().equals("Fin de Projet")) {
					monEntretienEvaluation.setTypeEntretienEvaluation(unTypeEntretienEvaluation);
					entretienEvaluationDAOImpl.addOrUpdate(monEntretienEvaluation);
					EntretienEvaluation unEntretienEvaluation = entretienEvaluationDAOImpl
							.readUnEntretienEvaluation(monEntretienEvaluation);
					// 11- Vérification de la modification
					assertEquals(unTypeEntretienEvaluation.getLibelle(),
							unEntretienEvaluation.getTypeEntretienEvaluation().getLibelle());

				}
			}

		} catch (DAOException e) {
			fail(e.getMessage());
		}


	}

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.EntretienEvaluationDAOImpl#readEntretienEvaluation(com.gecko.jee.enterprise.myskills.persistence.impl2.EntretienEvaluation)}.
	 */
	// @Test
	public void testReadEntretienEvaluation() {

		// 1- Lire la liste d'entretien filtré par la date d'entretien d'évaluation égal
		// à l'année en cours.
		List<EntretienEvaluation> entretienEvaluations = new ArrayList<EntretienEvaluation>();
		entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();

		EntretienEvaluation entretienEvaluationFiltre = new EntretienEvaluation(
				Timestamp.valueOf("2021-06-22 08:00:00"));
		try {
			entretienEvaluations = entretienEvaluationDAOImpl.readEntretienEvaluation(entretienEvaluationFiltre);
		} catch (DAOException e) {
			fail(e.getMessage());
		}
		// 2- Afficher les résultats d'entretien d'évaluation filtré par la date
		// d'entretien d'évaluation.
		for (EntretienEvaluation entretienEvaluation : entretienEvaluations) {
			System.out.println("Date de l'entretien : " + entretienEvaluation.getDateCreation() + "\n Heure début : "
					+ entretienEvaluation.getHeureDebut() + "\n Heure fin : " + entretienEvaluation.getHeureFin()
					+ "\n LiferayId : " + entretienEvaluation.getFkLiferayId() + "\n mskuser : "
					+ entretienEvaluation.getMskuser().getFirstName() + "\n Type Entretien : "
					+ entretienEvaluation.getTypeEntretienEvaluation());
			// 3- Validation de la lecture des entretiens d'évalaution filtré par date
			
			assertEquals(entretienEvaluation.getDateCreation(), entretienEvaluationFiltre.getDateCreation());
		}
	}
	
	/**
	 * Test method for
	 * {@link com.gecko.jee.enterprise.myskills.persistence.EntretienEvaluationDAOImpl#readUnEntretienEvaluation(com.gecko.jee.enterprise.myskills.persistence.impl2.EntretienEvaluation)}.
	 */
	// @Test
	public void testTestReadUnEntretienEvaluation() {

		// 1- Lecture d'un Mskuser
		Mskuser mskuserCollaborateur = new Mskuser("Henry", "Gammel", "henry.gammel@bygecko.com", "1");
		Mskuser mskuserManager = new Mskuser("Julien", "Gresse", "julien.gresse@bygecko.com", "4");
		
		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();
		
		Mskuser monMskuserCollaborateur = null;
		Mskuser monMskuserManager = null;
		try {
			monMskuserCollaborateur = mskuserDAOImpl.readUnMskuser(mskuserCollaborateur);
			monMskuserManager = mskuserDAOImpl.readUnMskuser(mskuserManager);
		} catch (DAOException e) {
			fail(e.getMessage());
		}

		//2- Validation d'un mskuser
		assertEquals(mskuserCollaborateur.getFirstName(), monMskuserCollaborateur.getFirstName());
		assertEquals(mskuserCollaborateur.getLastName(), monMskuserCollaborateur.getLastName());
		assertEquals(mskuserCollaborateur.getMailAddress(), monMskuserCollaborateur.getMailAddress());
		
		assertEquals(mskuserManager.getFirstName(), monMskuserManager.getFirstName());
		assertEquals(mskuserManager.getLastName(), monMskuserManager.getLastName());
		assertEquals(mskuserManager.getMailAddress(), monMskuserManager.getMailAddress());

		EntretienEvaluation entretienEvaluationFiltre = new EntretienEvaluation();
		// 3- Créer l'entretien d'évaluation a filtré
		TypeEntretienEvaluationDAOImpl typeEntretienEvaluationDAO = new TypeEntretienEvaluationDAOImpl();
		List<TypeEntretienEvaluation> listTypeEntretienEvaluation;
		try {
			listTypeEntretienEvaluation = typeEntretienEvaluationDAO.readAll();
			for (TypeEntretienEvaluation unTypeEntretienEvaluation : listTypeEntretienEvaluation) {
				if (unTypeEntretienEvaluation.getLibelle().equals("Fin de Projet")) {

					entretienEvaluationFiltre = new EntretienEvaluation(Timestamp.valueOf("2021-06-22 08:00:00"),
							monMskuserManager.getLiferayId(), Timestamp.valueOf("2021-06-22 11:00:00"),
							Timestamp.valueOf("2021-06-22 12:00:00"), monMskuserCollaborateur,
							unTypeEntretienEvaluation);
				}
			}
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 4- Lire l'entretien d'évaluation
		EntretienEvaluationDAOImpl entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();

		EntretienEvaluation monEntretienEvaluation;
		try {
			monEntretienEvaluation = entretienEvaluationDAOImpl.readUnEntretienEvaluation(entretienEvaluationFiltre);
			// 6- Valiadation de l'entretien d'évaluation
			assertEquals(entretienEvaluationFiltre.getDateCreation(), monEntretienEvaluation.getDateCreation());
			assertEquals(entretienEvaluationFiltre.getHeureDebut(), monEntretienEvaluation.getHeureDebut());
			assertEquals(entretienEvaluationFiltre.getHeureFin(), monEntretienEvaluation.getHeureFin());
			assertEquals(entretienEvaluationFiltre.getMskuser().getFirstName(),
					monEntretienEvaluation.getMskuser().getFirstName());
			assertEquals(entretienEvaluationFiltre.getFkLiferayId(), monEntretienEvaluation.getFkLiferayId());
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.gecko.jee.enterprise.myskills.persistence.EntretienEvaluationDAOImpl#deleteEntretienEvaluation(com.gecko.jee.enterprise.myskills.persistence.impl2.EntretienEvaluation)}.
	 */
	// @Test
	public void testDeleteEntretienEvaluation() {

		// 1- Lecture d'un Mskuser
		Mskuser mskuserCollaborateur = new Mskuser("Henry", "Gammel", "henry.gammel@bygecko.com", "1");
		Mskuser mskuserManager = new Mskuser("Julien", "Gresse", "julien.gresse@bygecko.com", "4");

		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();

		Mskuser monMskuserCollaborateur = null;
		Mskuser monMskuserManager = null;
		try {
			monMskuserCollaborateur = mskuserDAOImpl.readUnMskuser(mskuserCollaborateur);
			monMskuserManager = mskuserDAOImpl.readUnMskuser(mskuserManager);
		} catch (DAOException e) {
			fail(e.getMessage());
		}

		// 2- Validation d'un mskuser
		assertEquals(mskuserCollaborateur.getFirstName(), monMskuserCollaborateur.getFirstName());
		assertEquals(mskuserCollaborateur.getLastName(), monMskuserCollaborateur.getLastName());
		assertEquals(mskuserCollaborateur.getMailAddress(), monMskuserCollaborateur.getMailAddress());

		assertEquals(mskuserManager.getFirstName(), monMskuserManager.getFirstName());
		assertEquals(mskuserManager.getLastName(), monMskuserManager.getLastName());
		assertEquals(mskuserManager.getMailAddress(), monMskuserManager.getMailAddress());

		EntretienEvaluation entretienEvaluationFiltre = new EntretienEvaluation();
		// 3- Créer l'entretien d'évaluation a filtré
		TypeEntretienEvaluationDAOImpl typeEntretienEvaluationDAO = new TypeEntretienEvaluationDAOImpl();
		List<TypeEntretienEvaluation> listTypeEntretienEvaluation;
		try {
			listTypeEntretienEvaluation = typeEntretienEvaluationDAO.readAll();
			for (TypeEntretienEvaluation unTypeEntretienEvaluation : listTypeEntretienEvaluation) {
				if (unTypeEntretienEvaluation.getLibelle().equals("Fin de Projet")) {

					entretienEvaluationFiltre = new EntretienEvaluation(Timestamp.valueOf("2021-06-22 08:00:00"),
							monMskuserManager.getLiferayId(), Timestamp.valueOf("2021-06-22 11:00:00"),
							Timestamp.valueOf("2021-06-22 12:00:00"), monMskuserCollaborateur,
							unTypeEntretienEvaluation);
				}
			}
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		// 4- Lire l'entretien d'évaluation
		EntretienEvaluationDAOImpl entretienEvaluationDAOImpl = new EntretienEvaluationDAOImpl();

		EntretienEvaluation monEntretienEvaluation;
		try {
			monEntretienEvaluation = entretienEvaluationDAOImpl.readUnEntretienEvaluation(entretienEvaluationFiltre);
			// 6- Valiadation de l'entretien d'évaluation
			assertEquals(entretienEvaluationFiltre.getDateCreation(), monEntretienEvaluation.getDateCreation());
			assertEquals(entretienEvaluationFiltre.getHeureDebut(), monEntretienEvaluation.getHeureDebut());
			assertEquals(entretienEvaluationFiltre.getHeureFin(), monEntretienEvaluation.getHeureFin());
			assertEquals(entretienEvaluationFiltre.getMskuser().getFirstName(),
					monEntretienEvaluation.getMskuser().getFirstName());
			assertEquals(entretienEvaluationFiltre.getFkLiferayId(), monEntretienEvaluation.getFkLiferayId());

			// 7- Supprimer l'entretien d'évaluation
			entretienEvaluationDAOImpl.deleteEntretienEvaluation(monEntretienEvaluation);

		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.gecko.jee.enterprise.myskills.persistence.EntretienEvaluationDAOImpl#deleteEntertienEvaluationAction(com.gecko.jee.enterprise.myskills.persistence.impl.EntretienEvaluationAction)}.
	 */
	// @Test
	public void testDeleteEntertienEvaluationAction() {
//		// 1- Lecture d'un Mskuser
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
//				Timestamp.valueOf("2021-03-18 11:00:00"), Timestamp.valueOf("2021-03-18 12:00:00"),
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
//
//			
//			// 7- instantiation de l'action à supprimer
//			EntretienEvaluationAction entretienEvaluationAction = new EntretienEvaluationAction(
//					Timestamp.valueOf("2024-03-18 11:00:00"), "Passer un concour", "En cours");
//
//			EntretienEvaluationActionDAOImpl entretienEvaluationActionSupp = new EntretienEvaluationActionDAOImpl();
//
//			EntretienEvaluationAction enAction = entretienEvaluationActionSupp
//					.readUnEntretienEvaluationAction(entretienEvaluationAction);
//
////			EntretienEvaluation evaluation = enAction.getEntretienEvaluation();
////
////			int sizeListAction = evaluation.getEntretienEvaluationActions().size();
////
////			enAction.setEntretienEvaluation(null);
////
////			entretienEvaluationActionSupp.delete(enAction);
////
////			assertEquals(sizeListAction - 1, evaluation.getEntretienEvaluationActions().size());
//			//monEntretienEvaluation.removeEntretienEvaluationAction(enAction);
////			List<EntretienEvaluationAction> actions = monEntretienEvaluation.getEntretienEvaluationActions();
////			
////			actions.remove(enAction);
////
////			monEntretienEvaluation.setEntretienEvaluationActions(actions);
////			entretienEvaluationDAOImpl.addOrUpdate(monEntretienEvaluation);
//			// entretienEvaluationActionSupp.delete(enAction);
//			// 8- Lire la liste des actions
//
////			List<EntretienEvaluationAction> entretienEvaluationActions = monEntretienEvaluation
////					.getEntretienEvaluationActions();
////
////			for (EntretienEvaluationAction enAction : entretienEvaluationActions) {
////				if (enAction.getDescription().equals(entretienEvaluationAction.getDescription())) {
////					monEntretienEvaluation.removeEntretienEvaluationAction(enAction);
////					entretienEvaluationActionSupp.delete(enAction);
////				}
////			}
//
//			// 8- Valiation
//			// assertEquals(2,
//			// monEntretienEvaluation.getEntretienEvaluationActions().size());
//
//		} catch (DAOException e) {
//			fail(e.getMessage());
//		}
	}
}
