package br.edu.ifpb.memoriam.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.entity.Operadora;

public class OperadoraController {
	private Operadora operadora;
	private List<Mensagem> mensagensErro;
	
	public List<Operadora> consultar(){
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		List<Operadora> operadoras = dao.findAll();
		return operadoras;
	}
	
	public Resultado cadastrar(Map<String, String[]> parametros) {
		Resultado resultado= new Resultado();
		
		if(isParametrosValidos(parametros)) {
				OperadoraDAO dao= new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
				dao.beginTransaction();
				System.out.println("OP: "+operadora.getPrefixo());
				if(this.operadora.getId() == null) {
					dao.insert(this.operadora);
				} else{
					dao.update(this.operadora);
				}
				dao.commit();
				
				resultado.setErro(false);
				resultado.setMensagens(Collections.singletonList(new Mensagem("Operadadora criada com sucesso", Categoria.INFO)));
			} else{
				resultado.setEntidade(this.operadora);
				resultado.setErro(true);
				resultado.setMensagens(this.mensagensErro);
				}
			return resultado;
	}
	
	private boolean isParametrosValidos(Map<String, String[]> parametros) {
		// nomes dos parâ¢metros vem dos atributos 'name' das tags HTML do formulário
		String[] id= parametros.get("id");
		String[] nome= parametros.get("nome");
		String[] prefixo= parametros.get("prefixo");
		
		this.operadora= new Operadora();
		this.mensagensErro= new ArrayList<Mensagem>();
		
		if(id!= null && id.length>0 && !id[0].isEmpty()) {
			operadora.setId(Integer.parseInt(id[0]));
		}
		
		if(prefixo== null|| prefixo.length== 0 || prefixo[0].isEmpty()) {
			this.mensagensErro.add(new Mensagem("Prefixo é campo obrigatório!", Categoria.ERRO));
		}else{
			operadora.setPrefixo(Integer.parseInt(prefixo[0]));
		}
		
		if(nome== null|| nome.length== 0 || nome[0].isEmpty()) {
			this.mensagensErro.add(new Mensagem("Nome é campo obrigatório!", Categoria.ERRO));
		} else{
			operadora.setNome(nome[0]);
		}
	
		return this.mensagensErro.isEmpty();
	}
	
	public Operadora buscar(int id){
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.find(id);
	}
	public Resultado remove(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		String[] selecionadosform = parametros.get("del_selected");
		try {
			for (String s : selecionadosform) {
				Operadora o = dao.find(Integer.parseInt(s));
				dao.delete(o);
			}
			resultado.setErro(false);
			resultado.setMensagens(Collections.singletonList(new Mensagem("Operdadora(s) removida com sucesso", Categoria.INFO)));
		} catch (Exception exc) {
			resultado.setEntidade(this.operadora);
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		dao.commit();

		return resultado;
	}
}
