/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent.impl;

import java.util.ArrayList;
import java.util.List;

import com.gecko.jee.enterprise.myskills.hrcomponent.MskuserComponent;
import com.gecko.jee.enterprise.myskills.hrpersistence.MskuserDAOImpl;
import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;
import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;

/**
 * <b> Description : g�re les mskusers.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class MskuserComponentImpl implements MskuserComponent {

	/**
	 * récupère la liste des mskusers
	 */
	@Override
	public List<Mskuser> readAllMskuser() {
		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();
		List<Mskuser> mskusers = new ArrayList<Mskuser>();

		try {
			mskusers = mskuserDAOImpl.readAllMskuser();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return mskusers;
	}

	/**
	 * Ajouter modifier un mskuser
	 */
	@Override
	public void addOrUpdateMskuser(Mskuser mskuser) {
		MskuserDAOImpl mskuserDAOImpl = new MskuserDAOImpl();
		try {
			mskuserDAOImpl.addOrUpdate(mskuser);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
