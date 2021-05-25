package it.advancia.michele.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactoryProvider
{
	private EMFactoryProvider()
	{
	}

	private static EntityManagerFactory emFactory;

	public static EntityManager getEM()
	{
		if (emFactory == null)
			createEMFactory();

		return emFactory.createEntityManager();
	}

	private static synchronized void createEMFactory()
	{
		if (emFactory == null)
		{
			emFactory = Persistence.createEntityManagerFactory("RubricaJPA");
		}
	}
}
