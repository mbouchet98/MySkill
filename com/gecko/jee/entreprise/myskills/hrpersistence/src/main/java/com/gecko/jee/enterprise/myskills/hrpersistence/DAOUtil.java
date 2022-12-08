package com.gecko.jee.enterprise.myskills.hrpersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * <b> Description : .</b>
 * <p>
 * Permet l'initialisation de EntityManager, pour la persistence des données.
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class DAOUtil {
	
	private static EntityManagerFactory entityManagerFactory;

	static{
		entityManagerFactory = Persistence.createEntityManagerFactory("hrpersistence");
	}
	
	public static EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	public static void close(){
		entityManagerFactory.close();
	}
}
