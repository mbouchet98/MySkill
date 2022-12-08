package com.gecko.jee.enterprise.myskills.hrpersistence.exeption;

/**
 * 
 * <b> Description : Gère les exception lié la persistence des données.</b>
 * <p>
 * </p>
 * 
 * @author GECKO SOFTWARE
 *
 */
public class DAOException extends Exception{

	private static final long serialVersionUID = 1L;

	public DAOException(String msg){
		super(msg);
	}
}
