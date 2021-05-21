package it.advancia.michele.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="utenti")
public class Account
{
	@Id
	@Column(name="user")
	private String username;
	@Column(name="password")
	private String password;
	@OneToOne(cascade=CascadeType.ALL)
	private Rubrica rubrica;
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public Rubrica getRubrica()
	{
		return rubrica;
	}
	
	public void setRubrica(Rubrica rubrica)
	{
		this.rubrica = rubrica;
	}

}
