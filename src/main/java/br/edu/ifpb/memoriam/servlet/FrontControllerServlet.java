package br.edu.ifpb.memoriam.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;
import br.edu.ifpb.memoriam.entity.Usuario;
import br.edu.ifpb.memoriam.facade.ContatoController;
import br.edu.ifpb.memoriam.facade.LoginController;
import br.edu.ifpb.memoriam.facade.OperadoraController;
import br.edu.ifpb.memoriam.facade.Resultado;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet("/controller.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ContatoController contatoCtrl = new ContatoController();
		OperadoraController operadoraCtrl = new OperadoraController();
		String proxPagina = null;
		String id;
		this.getServletContext().removeAttribute("msgs");
		String operacao = request.getParameter("op");
		
		// Pega o usuário logado
		HttpSession session= request.getSession();
		Usuario usuario= (Usuario) session.getAttribute("usuario");
		
		switch (operacao) {
		case "conctt":
			List<Contato> contatos = contatoCtrl.consultar(usuario);
			request.setAttribute("contatos", contatos);
			proxPagina = "contato/consulta.jsp";
			break;
			
		case "edtope":
			id = request.getParameter("id");
			Operadora operadora = operadoraCtrl.buscar(Integer.parseInt(id));
			request.setAttribute("operadora", operadora);
			proxPagina = "operadora/editar.jsp";
			break;
			
		case "edtctt":
			id = request.getParameter("id");
			Contato contato = contatoCtrl.buscar(Integer.parseInt(id));
			request.setAttribute("contato", contato);
			proxPagina = "contato/editar.jsp";
			break;
		case "conope":
			List<Operadora> operadoras = operadoraCtrl.consultar();
			request.setAttribute("operadoras", operadoras);
			proxPagina = "operadora/consulta.jsp";
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(proxPagina);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().removeAttribute("msgs");
		String operacao = request.getParameter("op");
		if (operacao == null) {
			this.getServletContext().setAttribute("msgs",
					new String[] { "Operaçãoo (op) nãoo especificada na requisição!" });
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
		OperadoraController operadoraCtrl = new OperadoraController();
		ContatoController contatoCtrl = new ContatoController();
		LoginController loginCtrl= new LoginController();
		Resultado resultado = null;
		String paginaSucesso = "controller.do?op=conctt";
		String paginaSucessoOpe = "controller.do?op=conope";
		String paginaErro = "contato/cadastro.jsp";
		String paginaErroOpe = "operadora/cadastro.jsp";
		String proxPagina = null;
		
		// Pega o usuário logado
		HttpSession session= request.getSession();
		Usuario usuario= (Usuario) session.getAttribute("usuario");
		switch (operacao) {
		// Login/logout
		case "login":
			paginaSucesso = "controller.do?op=conctt";
			paginaErro = "controller.do?op=login";
			resultado = loginCtrl.isValido(request.getParameterMap());
			if(resultado.isErro()) {
				request.setAttribute("msgs", resultado.getMensagens());
				proxPagina= paginaErro;
			} else {
				session.setAttribute("usuario", (Usuario) resultado.getEntidade());
				proxPagina = paginaSucesso;
				
				// trata o lembrarString 
				String lembrar = request.getParameter("lembrar");
				if(lembrar!= null) {				
					Usuario usuariologado = new Usuario();
					Cookie c= new Cookie("loginCookie", usuariologado.getEmail());
					c.setMaxAge(-1);
					response.addCookie(c);
					} else{
						for(Cookie cookie: request.getCookies()) {
							if(cookie.getName().equals("loginCookie")){
								cookie.setValue(null);
								cookie.setMaxAge(0);
								response.addCookie(cookie);			
							
						}
						
					}
				}
			}
			
			break;
		case "logout":
			resultado = new Resultado();
			proxPagina = "login/login.jsp";
			session.invalidate();
			resultado.setErro(false);
			break;	
		case "cadctt":
			resultado = contatoCtrl.cadastrar(request.getParameterMap());
			if (!resultado.isErro()) {
				proxPagina = paginaSucesso;
				request.setAttribute("msgs", resultado.getMensagens());
			} else {
				request.setAttribute("contato", (Contato) resultado.getEntidade());
				request.setAttribute("msgs", resultado.getMensagens());
				proxPagina = paginaErro;
			}

			break;
			
		case "deletectt":
			resultado = contatoCtrl.remove(request.getParameterMap());
			if(!resultado.isErro()) {
				proxPagina= paginaSucesso;
				request.setAttribute("msgs", resultado.getMensagens());
			} else{
				request.setAttribute("contato", (Contato) resultado.getEntidade());
				request.setAttribute("msgs", resultado.getMensagens());
				proxPagina= paginaErro;
			}
			
			break;
		case "deleteope":
			resultado = operadoraCtrl.remove(request.getParameterMap());
			if(!resultado.isErro()) {
				proxPagina= paginaSucessoOpe;
				request.setAttribute("msgs", resultado.getMensagens());
			} else{
				request.setAttribute("operadora", (Operadora) resultado.getEntidade());
				request.setAttribute("msgs", resultado.getMensagens());
				proxPagina= paginaErro;
			}
			
			break;

		case "cadope":
			resultado = operadoraCtrl.cadastrar(request.getParameterMap());
			if (!resultado.isErro()) {
				proxPagina = paginaSucessoOpe;
				request.setAttribute("msgs", resultado.getMensagens());
			} else {
				request.setAttribute("operadora", (Operadora) resultado.getEntidade());
				request.setAttribute("msgs", resultado.getMensagens());
				proxPagina = paginaErroOpe;
			}
			break;
		default:
			request.setAttribute("erro", "OperaÃ§Ã£o nÃ£o especificada no servlet!");
			proxPagina = "../erro/erro.jsp";
		}
		if (resultado.isErro()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(proxPagina);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(proxPagina);
		}
	}
}
