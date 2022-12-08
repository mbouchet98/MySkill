/**
 * 
 */
package com.gecko.jee.enterprise.hrpersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.gecko.jee.enterprise.myskills.hrpersistence.EntretienEvaluationDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.MskuserDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;

/**
 * <b> Description : Classe test des méthodes.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class MySkillsTest {
	private EntretienEvaluationDAOImpl entretienEvaluationDAOImpl;

	// @Test
	public void testAddOrUpdateMskuser() {

		// 1- Créer des Mskuser
		Mskuser mskuserCollaborateur = new Mskuser("4 rue bidon, 79000 Bidon", Timestamp.valueOf("2021-02-03 08:00:00"),
				"Dupond", "Louis", "5", "lDupon", "Louis.dupont@bygecko.com", "password", "0102030405");
		Mskuser mskuserManager = new Mskuser("4 rue bidon, 79000 Bidon", Timestamp.valueOf("2021-02-03 08:00:00"),
				"Tatoo", "Arneaut", "6", "atatoo", "arneaut.tatoo@bygecko.com", "password", "0102030405");

		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();

		try {
			mskuserDAOImpl.addOrUpdate(mskuserCollaborateur);
			mskuserDAOImpl.addOrUpdate(mskuserManager);
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	// @Test
	public void testReadAllMskuser() {
		// 1- Lister tout les mskusers
		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();
		try {
			List<Mskuser> mskusers = mskuserDAOImpl.readAllMskuser();
			for (Mskuser leMskuser : mskusers) {
				System.out.println(leMskuser);
			}

			// 2-validation
			assertEquals(6, mskusers.size());
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	// @Test
	public void testReadUnMskuser() {
		// 1- Lecture des mskuser
		Mskuser mskuserCollaborateur = new Mskuser("Dupond", "Louis", "Louis.dupont@bygecko.com", "5");
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
	}
}
