package br.edu.ifpb.memoriam.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.memoriam.dao.ContatoDAO;
import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.dao.UsuarioDAO;
import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;
import br.edu.ifpb.memoriam.entity.Usuario;

public class ContatoController {
	private List<Mensagem> mensagensErro;
	private Contato contato;

	public List<Contato> consultar(Usuario usuario) {
		ContatoDAO dao = new ContatoDAO();
		List<Contato> contatos = dao.findAllFromUser(usuario);
		return contatos;
	}

	public Contato buscar(int id) {
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.find(id);

	}

	public Resultado cadastrar(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		if (isParametrosValidos(parametros)) {
			ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
			dao.beginTransaction();
			Operadora operadora = null;
			String idOperadora = parametros.get("operadora")[0];
			Usuario usuario = null;
			String idUsuario = parametros.get("usuario")[0];
			
			if (idOperadora != null) {
				UsuarioDAO usDao = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
				usuario = usDao.find(Integer.parseInt(idUsuario));
				OperadoraDAO opDao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
				operadora = opDao.find(Integer.parseInt(idOperadora));
			}
			contato.setOperadora(operadora);
			contato.setUsuario(usuario);
			if (this.contato.getId() == null) {
				dao.insert(this.contato);
			} else {
				dao.update(this.contato);
			}
			dao.commit();
			resultado.setErro(false);
			resultado.setMensagens(Collections.singletonList(new Mensagem("Contato criado com sucesso", Categoria.INFO)));
		} else {
			resultado.setEntidade(this.contato);
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		return resultado;
	}

	private boolean isParametrosValidos(Map<String, String[]> parametros) {
		// nomes dos par�metros vem dos atributos 'name' das tags HTML do
		// formul�rio
		String[] id = parametros.get("id");
		String[] nome = parametros.get("nome");
		String[] fone = parametros.get("fone");
		String[] dataAniv = parametros.get("dataaniv");
		this.contato = new Contato();
		this.mensagensErro = new ArrayList<Mensagem>();
		if (id != null && id.length > 0 && !id[0].isEmpty()) {
			contato.setId(Integer.parseInt(id[0]));
		}
		if (nome == null || nome.length == 0 || nome[0].isEmpty()) {
			this.mensagensErro.add(new Mensagem("Nome � campo obrigat�rio!", Categoria.ERRO));
		} else {
			contato.setNome(nome[0]);
		}
		if (fone == null || fone.length == 0 || fone[0].isEmpty()) {
			this.mensagensErro.add(new Mensagem("Fone � campo obrigat�rio!", Categoria.ERRO));
		} else {
			contato.setFone(fone[0]);
		}
		if (dataAniv == null || dataAniv.length == 0 || dataAniv[0].isEmpty()) {
			this.mensagensErro.add(new Mensagem("Data de aniversario � campo obrigat�rio!", Categoria.ERRO));
		} else {
			if (dataAniv[0].matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2,2}")) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					sdf.setLenient(false);
					Date dataIni = sdf.parse(dataAniv[0]);
					contato.setDataAniversario(dataIni);
				} catch (ParseException e) {
					this.mensagensErro.add(new Mensagem("Data inv�lida para a data de anivers�rio!", Categoria.ERRO));
				}
			} else {
				System.out.println("errou mizera!");
				this.mensagensErro.add(new Mensagem("Formato inv�lido para a data de anivers�rio(use dd/mm/aaaa)!", Categoria.ERRO));
			}
		}
		return this.mensagensErro.isEmpty();
	}

	public Resultado remove(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		String[] selecionadosform = parametros.get("del_selected");
		try {
			for (String s : selecionadosform) {
				Contato c = dao.find(Integer.parseInt(s));
				dao.delete(c);
			}
			resultado.setErro(false);
			resultado.setMensagens(Collections.singletonList(new Mensagem("Contato removido com sucesso", Categoria.INFO)));
		} catch (Exception exc) {
			resultado.setEntidade(this.contato);
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		dao.commit();

		return resultado;
	}
}
