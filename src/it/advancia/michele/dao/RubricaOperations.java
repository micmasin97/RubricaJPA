package it.advancia.michele.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.advancia.michele.model.Account;
import it.advancia.michele.model.Contatto;
import it.advancia.michele.model.Rubrica;

public class RubricaOperations
{
	private static EntityManagerFactory emFactory;
	
	public static List<Contatto> showContact(String user)
	{
		EntityManager entityManager = EMFactoryProvider.getEM();
		Query query = entityManager.createNativeQuery("select c.id, c.numero_di_telefono, c.cognome_contatto, c.nome_contatto, c.rubrica_id from contatti c  join rubrica r on c.rubrica_id=r.id join utenti u on u.rubrica_id=r.id where u.user=:user","RubricaContattoAccount");
		query.setParameter("user", user);
		List<Contatto> results = query.getResultList();
		entityManager.close();
		return results;
	}
	public static void createContact(String user, String nome, String cognome, String numero)
	{
		EntityManager entityManager = EMFactoryProvider.getEM();		entityManager.getTransaction().begin();
		Contatto contatto = new Contatto();
		contatto.setNome(nome);
		contatto.setCognome(cognome);
		contatto.setTelefono(numero);
		Account account = entityManager.find(Account.class, user);
		entityManager.persist(contatto);
		account.getRubrica().getContatti().add(contatto);
		contatto.setRubrica(account.getRubrica());
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
