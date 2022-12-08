/**
 * 
 */
package com.gecko.jee.enterprise.myskills.hrcomponent;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.impl.Mskuser;

/**
 * <b> Description : Interface de gestion des mskusers.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface MskuserComponent {

	/**
	 * Ajouter ou Modifier un Mskuser
	 * 
	 * @param mskuser
	 */
	void addOrUpdateMskuser(Mskuser mskuser);

	/**
	 * Récupère la liste des utilisateurs
	 * 
	 * @return
	 */
	List<Mskuser> readAllMskuser();

}
