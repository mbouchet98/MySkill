/**
 * 
 */
package com.gecko.jee.enterprise.hrpersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.MskuserDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;

/**
 * <b> Description : Classe de test pour les DAO liés à mskuser.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class MskuserDAOImplTest {

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.MskuserDAOImpl#addOrUpdate(com.gecko.jee.enterprise.myskills.persistence.impl2.Mskuser)}.
	 */
	// @Test
	public void testAddOrUpdate() {

		// 1- Créer des Mskuser
		Mskuser mskuserCollaborateur = new Mskuser("4 rue bidon, 79000 Bidon", Timestamp.valueOf("2021-02-03 08:00:00"),
				"Dupond", "Louis", "1", "lDupon", "Louis.dupont@bygecko.com", "password", "0102030405");
		Mskuser mskuserManager = new Mskuser("4 rue bidon, 79000 Bidon", Timestamp.valueOf("2021-02-03 08:00:00"),"Gresse","julien","2", "jgresse", "julien.gresse@bygecko.com", 
				"password", "0102030405");

		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();

		try {
			mskuserDAOImpl.addOrUpdate(mskuserCollaborateur);
			mskuserDAOImpl.addOrUpdate(mskuserManager);
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.MskuserDAOImpl#readAllMskuser()}.
	 */
	// @Test
	public void testReadAllMskuser() {
		// 1- Lister tout les mskusers
		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();
		try {
			List<Mskuser> mskusers = mskuserDAOImpl.readAllMskuser();
			for (Mskuser leMskuser : mskusers) {
				System.out.println(leMskuser.toString());
			}

			// 2-validation
			assertEquals(2, mskusers.size());
		} catch (DAOException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.MskuserDAOImpl#readUnMskuser(com.gecko.jee.enterprise.myskills.persistence.impl2.Mskuser)}.
	 */
	// @Test
	public void testReadUnMskuser() {
		// 1- Lecture des mskuser
		Mskuser mskuserCollaborateur = new Mskuser("Dupond", "Louis", "Louis.dupont@bygecko.com", "1");
		Mskuser mskuserManager = new Mskuser("Gresse", "julien", "julien.gresse@bygecko.com", "2");

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

	/**
	 * Test method for {@link com.gecko.jee.enterprise.myskills.persistence.MskuserDAOImpl#readByLiferayId(com.gecko.jee.enterprise.myskills.persistence.impl2.Mskuser)}.
	 */
	// @Test
	public void testReadByLiferayId() {

	}

}
