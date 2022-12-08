package com.gecko.jee.enterprise.myskills.hrpersistence;

import java.util.List;

import com.gecko.jee.enterprise.myskills.hrpersistence.exeption.DAOException;

/**
 * 
 * <b> Description : .</b>
 * <p>
 * Permet l'accès aux données de Mskuser
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public interface MskuserDAO<Mskuser> {
	/**
	 * Méthode d'ajout et de modification d'un mskuser
	 * 
	 * @param mskuser
	 * @throws DAOException
	 */
	void addOrUpdate(Mskuser mskuser) throws DAOException;

	/**
	 * Méthode de lecture de tous les mskusers
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<Mskuser> readAllMskuser() throws DAOException;

	/**
	 * Methode de lecture d'un mskuser avec en param un mskuser
	 * 
	 * @param mskuser
	 * @return
	 * @throws DAOException
	 */
	Mskuser readUnMskuser(Mskuser mskuser) throws DAOException;

	/**
	 * Méthode de lecture d'un mskuser par liferayId
	 * 
	 * @param liferayId
	 * @return
	 * @throws DAOException
	 */
	Mskuser readByLiferayId(Mskuser liferayId) throws DAOException;
}
