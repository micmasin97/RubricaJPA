package it.advancia.michele.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Rubrica
{
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	@Column(name="contatti")
	@OneToMany(mappedBy="id",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Contatto> contatti;
	
	public Rubrica()
	{
		contatti=new ArrayList<Contatto>();
	}
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public List<Contatto> getContatti()
	{
		return contatti;
	}
	public void setContatti(List<Contatto> contatti)
	{
		this.contatti = contatti;
	}
}
