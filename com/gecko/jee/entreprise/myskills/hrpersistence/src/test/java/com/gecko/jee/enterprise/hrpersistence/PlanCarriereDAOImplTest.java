/**
 * 
 */
package com.gecko.jee.enterprise.hrpersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.MskuserDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.PlanCarriereDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriere;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.PlanCarriereObjectif;

/**
 * <b> Description : Classe de test pour les DAO liés à PlanCarriere.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class PlanCarriereDAOImplTest {

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.PlanCarriereDAOImpl#addOrUpdate(com.gecko.jee.enterprise.myskills.persistence.impl2.PlanCarriere)}.
	 */
	// @Test
	public void testAddOrUpdate() {

		/**
		 * Ajouter un plan de carrière et ses objectif
		 */
		// 1- Lire un Mskuser
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

		// 3- Création d'un plan de carriere
		PlanCarriere planCarriere = new PlanCarriere("Gestion d'une agence", "Chef d'agence");
		List<PlanCarriere> planCarrieresCollaborateur = new ArrayList<PlanCarriere>();

		// 4- Ajouter des objectifs de plan de carriere
		PlanCarriereObjectif planCarriereObjectif = new PlanCarriereObjectif("Faire des formation apté", "Formation",
				"En Cours");
		planCarriereObjectif.setPlanCarriere(planCarriere);

		PlanCarriereObjectif planCarriereObjectifConcour = new PlanCarriereObjectif(
				"Passer des concours de validation du niveau", "Concour", "En Cours");
		planCarriereObjectifConcour.setPlanCarriere(planCarriere);

		PlanCarriereObjectif planCarriereObjectifEntretien = new PlanCarriereObjectif("Passer un entretien",
				"Entretien", "En Cours");
		planCarriereObjectifEntretien.setPlanCarriere(planCarriere);

		planCarrieresCollaborateur.add(planCarriere);

		// 5- Modifier le Mskuser en y ajoutant le plan de carriere

		monMskuserCollaborateur.setPlanCarrieres(planCarrieresCollaborateur);
		List<Mskuser> mskusers = new ArrayList<Mskuser>();
		mskusers.add(monMskuserCollaborateur);
		// monMskuserCollaborateur.setPlanCarriere(planCarriere);

		// 6- Création des liste Mskuser et PlanCarriereObjectif

		List<PlanCarriereObjectif> planCarriereObjectifs = new ArrayList<PlanCarriereObjectif>();
		planCarriereObjectifs.add(planCarriereObjectif);
		planCarriereObjectifs.add(planCarriereObjectifConcour);
		planCarriereObjectifs.add(planCarriereObjectifEntretien);

		planCarriere.setPlanCarriereObjectifs(planCarriereObjectifs);
		planCarriere.setMskusers(mskusers);
		// planCarriere.setMskuser(monMskuserCollaborateur);

		PlanCarriereDAOImpl planCarriereDAOImpl = new PlanCarriereDAOImpl();

		try {
			planCarriereDAOImpl.addOrUpdate(planCarriere);
		} catch (DAOException e) {
			fail(e.getMessage());
		}

		// 7- Valiadation du plan de carriere

		List<PlanCarriere> planCarrieres = new ArrayList<PlanCarriere>();
		try {
			planCarrieres = planCarriereDAOImpl.readAll();
			for (PlanCarriere lePlanCarriere : planCarrieres) {
				if (lePlanCarriere.getIdPlanCarriere() == 10) {
					assertEquals(planCarriere.getDescription(), lePlanCarriere.getDescription());
					assertEquals(planCarriere.getNom(), lePlanCarriere.getNom());
					assertEquals(planCarriere.getMskusers().get(0).getFirstName(),
							lePlanCarriere.getMskusers().get(0).getFirstName());
					assertEquals(planCarriere.getPlanCarriereObjectifs().get(0).getNom(),
							lePlanCarriere.getPlanCarriereObjectifs().get(0).getNom());
				}
			}

		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}
}
