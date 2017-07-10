package br.edu.ifpb.memoriam.facade;

import java.util.ArrayList;
import java.util.List;

public class Resultado {
	private Object entidade;
	private boolean erro;
	private List<Mensagem> mensagens;
	
	public Resultado() {
		this.mensagens = new ArrayList<Mensagem>();
	}

	public void addMensagem(Mensagem m) {
		this.mensagens.add(m);
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

	public Object getEntidade() {
		return entidade;
	}

	public void setEntidade(Object entidade) {
		this.entidade = entidade;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public void addMensagens(List<String> mensagensErro, Categoria categoria) {
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		for (String s : mensagensErro) {
			mensagens.add(new Mensagem(s, categoria));
		}
		this.mensagens.addAll(mensagens);
		
	}

}


//package br.edu.ifpb.memoriam.facade;
//
//import java.util.List;
//
//public class Resultado {
//	private Object entidade;
//	private boolean erro;
//	private List<String> mensagensErro;
//	private List<String> mensagensSucesso;
//
//	public List<String> getMensagensSucesso() {
//		return mensagensSucesso;
//	}
//
//	public void setMensagensSucesso(List<String> mensagensSucesso) {
//		this.mensagensSucesso = mensagensSucesso;
//	}
//
//	public List<String> getMensagensErro() {
//		return mensagensErro;
//	}
//
//	public void setMensagensErro(List<String> mensagensErro) {
//		this.mensagensErro = mensagensErro;
//	}
//
//	public boolean isErro() {
//		return erro;
//	}
//
//	public void setErro(boolean erro) {
//		this.erro = erro;
//	}
//
//	public Object getEntidade() {
//		return entidade;
//	}
//
//	public void setEntidade(Object entidade) {
//		this.entidade = entidade;
//	}
//}
