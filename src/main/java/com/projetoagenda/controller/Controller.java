package com.projetoagenda.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetoagenda.model.AgendaBeans;
import com.projetoagenda.model.AgendaDAO;

@WebServlet(urlPatterns = { "/Controller", "/main","/select", "/insert","/update","/delete","/report" }) /* URL caminhos - configura a pagina - requisiçoes  (definimos nos <form> <button> etc..*/
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	AgendaDAO DAOagenda = new AgendaDAO();
	AgendaBeans contato = new AgendaBeans();
	/* construtor */
	public Controller() {
		
		super();
	}

	// METODO PRINCIPAL DO SEVERLET / - QUE É EXECUTADO QUANDO CHAMA O SERVELET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String action = request.getServletPath(); /*metodo que retorna o caminho da requisição*/
		
		System.out.println(action); /*prinat o caminho*/
		
		if (action.equalsIgnoreCase("/main")) { /*se o caminho for igual a "/main" */
			
			contatos(request, response); 
			
		}else if(action.equalsIgnoreCase("/insert")) { /*action="insert" - nome que o FORMULARIO<html> / action= envia com os parametros a request http(formatada)*/
			
			System.out.println(action);
			novoContato(request,response);
			
		}else if(action.equalsIgnoreCase("/select")) {
			
			listarContato(request,response);
			
		}else if(action.equalsIgnoreCase("/update")) {
			
			editarContato(request,response);
			
		}else if(action.equalsIgnoreCase("/delete")) {
			
			removerContato(request,response);
			
		}else if(action.equalsIgnoreCase("/report")) {
			
			gerarRelatorio(request,response);
			
		}else{
			
			response.sendRedirect("index.html");
			
		}
		
		
		
	}

	//METODO QUE RETORNA A PAGINA AGENDA.JSP
	protected void contatos(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		//response.sendRedirect("agenda.jsp"); /*metodo para redirecionar uma pagina*/
		
		ArrayList<AgendaBeans> listacontatos = DAOagenda.listarContatos();
		
		request.setAttribute("contatos", listacontatos); /*SETA A LISTA NO atributo "conatos" (CONATOS - poderia ser outro nome)*/
		
	   System.out.println(listacontatos.get(2).getNome());
		
		/*CLASSE QUE TRABALHA as request e response - PARA MANDAR PARA A PAGINA*/
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		
		rd.forward(request, response); /*encaminha o OBJETO LISTACONTATOS-(pelo "contatos") para a tela JSP*/
		
		
	}
	
      protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println(request.getParameter("nome")); /*request.getParameter("nome") - são os parametros dos elemnetos do formulario (action="insert")*/
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));
		
		/*OBjeto - rebe o valor dos PARAMETROS enviados do FORMULARIO da pagina html*/
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		
	   DAOagenda.InserirContato(contato); /*armazena no BANCO*/
	   
	   response.sendRedirect("main"); /* EXECUTA REQUISIÇÃO IGUAL A ESSE SÓ QUE PELO SEVELETS -> href="main" */
		

	}
	
	    //METODO QUE RETORNA A PAGINA AGENDA.JSP
		protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			/*pega o parametro do id da lista que aparece na tela*/
			String idcon = request.getParameter("idcon"); /*idcon vai receber o parametro ?idcon= da pagina agenda.jso */
			//System.out.println(idcon);
			
			contato.setIdcon(Integer.valueOf(idcon));
			
			DAOagenda.selecionarContato(contato);
			
			/*
			 * System.out.println(contato.getIdcon());
			 * System.out.println(contato.getNome()); System.out.println(contato.getFone());
			 * System.out.println(contato.getEmail());
			 */
			
			request.setAttribute("idcon", contato.getIdcon()); // "idcon" - atributo do formulario editar.jsp vai ser setado= no contato.getIdcon()
			request.setAttribute("nome", contato.getNome());
			request.setAttribute("fone", contato.getFone());
			request.setAttribute("email", contato.getEmail());
			
			// emcaminha os dados para o front end
			RequestDispatcher rd = request.getRequestDispatcher("editar.jsp"); /*prepara*/
			rd.forward(request, response);/*executa*/
			
		}
		
		//EDITAR DADOS DO BANCO
		protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			//PASA OS PARAMETROS DA PAGINA PARA O OBJETO CONTATO
			contato.setIdcon(Integer.valueOf(request.getParameter("idcon"))); //converter a string para int (a parametro vem da tela com string)
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
			
			/* TESTAR SE ESTAVA RECEBENDO OS PARAMETROS DA PAGINA editar.jsp
			 * System.out.println(request.getParameter("idcon"));
			 * System.out.println(request.getParameter("nome"));
			 * System.out.println(request.getParameter("fone"));
			 * System.out.println(request.getParameter("email"));
			 */
			
			DAOagenda.alterarContato(contato);
			
			response.sendRedirect("main"); // sem barrra - redireciona para o metodo MAIN do servelets
			
		}
		
		
		protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				
			String idcon = request.getParameter("idcon");
			
		//	System.out.println(idcon); teste para checar se estava recebendo o id do contato da pagina agenda.jsp pelo metodo javascript
					
			contato.setIdcon(Integer.valueOf(idcon));	
			
			
			DAOagenda.deletarContato(contato);
			
			
			response.sendRedirect("main");
		}
		
		
		protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
		     // Classe da BIBLIOTECA ITEXT
			 Document documento = new Document();
			 
			 try {
				 
				 //para informar que a resposta vai ser um documneto PDF
				 response.setContentType("apllication/pdf");
				 
				 //nome do documento
				 response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
				 
				 //criar documento
				 PdfWriter.getInstance(documento, response.getOutputStream());
				 
				 //abrir o documneto
				 documento.open();
				 
				 //escrever uma paragrafo no pdf
				 documento.add(new Paragraph("Lista de contatos:"));
				 
				 documento.add(new Paragraph(" ")); //pular uma linha no documneto
				 
				 //criar tabela
				 PdfPTable tabela = new PdfPTable(3);//numero 3 para criar 3 colunas
				 
				 //criar cabeçalho - nome das colunas
				 PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
				 PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				 PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
				 
				 //adicionar na tabela
				 tabela.addCell(col1);
				 tabela.addCell(col2);
				 tabela.addCell(col3);
				 
				 ArrayList<AgendaBeans> listacontatos = DAOagenda.listarContatos();
				 
				 //adicionar os dados da lista no na tabela
				 for (AgendaBeans a : listacontatos) {
					 
					 tabela.addCell(a.getNome());
					 tabela.addCell(a.getFone());
					 tabela.addCell(a.getEmail());
				}
				 
				 
				 //adicuonar no documento a tabela
				 documento.add(tabela);
				
			} catch (Exception e) {


				System.out.println(" ");
				
				
			}finally {
				
				documento.close();
				
			}
			
			
			
		}
		
		

}
