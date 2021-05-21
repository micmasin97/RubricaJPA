package it.advancia.michele.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import it.advancia.michele.model.Account;
import it.advancia.michele.model.Contatto;

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
		EntityManager entityManager = EMFactoryProvider.getEM();		
		entityManager.getTransaction().begin();
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
	public static void deleteContact(String user, int id)
	{
		EntityManager entityManager = EMFactoryProvider.getEM();		
		entityManager.getTransaction().begin();
		Contatto contatto = entityManager.find(Contatto.class,id);
		Account account = entityManager.find(Account.class, user);
		account.getRubrica().getContatti().remove(contatto);
		entityManager.remove(contatto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	public static List<Contatto> searchContact(String user, String nome, String cognome)
	{
		EntityManager entityManager = EMFactoryProvider.getEM();
		Query query = entityManager.createQuery("Select c from Contatto c join Rubrica r on r.id=c.rubrica join Account a on a.rubrica=r.id where c.nome like :nome and c.cognome like :cognome and a.username=:user");
		query.setParameter("nome","%"+nome+"%");
		query.setParameter("cognome","%"+cognome+"%");
		query.setParameter("user", user);
		List<Contatto> contatti = query.getResultList();
		entityManager.close();
		return contatti;	
	}

	public static void updateContact(String user,int id, String nome, String cognome, String numero)
	{
		EntityManager entityManager = EMFactoryProvider.getEM();		
		entityManager.getTransaction().begin();
		Contatto contatto = entityManager.find(Contatto.class,id);
		contatto.setNome(nome);
		contatto.setCognome(cognome);
		contatto.setTelefono(numero);
		entityManager.persist(contatto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
