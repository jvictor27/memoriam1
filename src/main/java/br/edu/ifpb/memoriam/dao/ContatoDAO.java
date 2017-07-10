package br.edu.ifpb.memoriam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Usuario;

public class ContatoDAO extends GenericDAO<Contato, Integer> 
{
	
	public ContatoDAO() 
	{
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public ContatoDAO(EntityManager em) 
	{
		super(em);
	}

	public List<Contato> findAllFromUser(Usuario usuario) 
	{
		Query q= this.getEntityManager().createQuery("from Contato c where c.usuario = :user");
		q.setParameter("user", usuario);
		return q.getResultList();
	}
	
	public List<Contato> buscarNome(String nome, Usuario usuario) {
		Query q = this.getEntityManager().createQuery("from Contato c where c.usuario = :user and c.nome LIKE :nome");
		q.setParameter("nome", "%" + nome + "%");
		q.setParameter("user", usuario);
		return q.getResultList();
	}
	
	public List<Contato> buscarTelefone(String nome, Usuario usuario ) {
		Query q= this.getEntityManager().createQuery("from Contato c where c.usuario = :user and c.fone LIKE :nome");
		q.setParameter("nome","%" +nome+"%");
		q.setParameter("user", usuario);
		return q.getResultList();
		}
}
