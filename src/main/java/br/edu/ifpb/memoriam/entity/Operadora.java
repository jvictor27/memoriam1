package br.edu.ifpb.memoriam.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity


@Table(name="TB_OPERADORA")
public class Operadora {
	//
	@Id	
	//@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@Column(name="ID_OPERADORA")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	
//	
//	public List<Contato> getContato() {
//		return contato;
//	}
//

//	}

	private Integer id;
	
	@OneToMany(mappedBy="operadora",cascade=CascadeType.ALL)
	private List<Contato> contato;
//	public void setContato(List<Contato> contato) {
//	this.contato = contato;
	//
	
	public List<Contato> getContato() {
		return contato;
	}

	public void setContato(List<Contato> contato) {
		this.contato = contato;
	}

	@Column(name="NM_OPERADORA")
	private String nome;
	
	@Column(name="NU_PREFIXO")
	private Integer prefixo;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(Integer prefixo) {
		this.prefixo = prefixo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((prefixo == null) ? 0 : prefixo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operadora other = (Operadora) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (prefixo == null) {
			if (other.prefixo != null)
				return false;
		} else if (!prefixo.equals(other.prefixo))
			return false;
		return true;
	}
	
	
	
}
