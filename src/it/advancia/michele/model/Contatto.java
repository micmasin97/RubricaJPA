package it.advancia.michele.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="contatto")
public class Contatto
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="nome_contatto")
	private String nome;
	@Column(name="cognome_contatto")
	private String cognome;
	@Column(name="numero_di_telefono")
	private String Telefono;
	@ManyToOne
	private Rubrica rubrica;
}
