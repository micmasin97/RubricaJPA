package it.advancia.michele.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "contatti")
@SqlResultSetMapping(name = "RubricaContattoAccount", entities =
{ @EntityResult(entityClass = Contatto.class, fields =
{ @FieldResult(name = "id", column = "id"), @FieldResult(name = "nome", column = "nome_contatto"), @FieldResult(name = "cognome", column = "cognome_contatto"), @FieldResult(name = "telefono", column = "numero_di_telefono"), @FieldResult(name = "rubrica", column = "rubrica_id"),

}) })
@XmlRootElement(name = "Contatto")
@XmlType(propOrder =
{ "id", "cognome", "nome", "telefono" })
public class Contatto
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "nome_contatto")
	private String nome;
	@Column(name = "cognome_contatto")
	private String cognome;
	@Column(name = "numero_di_telefono")
	private String telefono;
	@ManyToOne
	private Rubrica rubrica;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public String getTelefono()
	{
		return telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	@XmlTransient
	public Rubrica getRubrica()
	{
		return rubrica;
	}

	public void setRubrica(Rubrica rubrica)
	{
		this.rubrica = rubrica;
	}
}
