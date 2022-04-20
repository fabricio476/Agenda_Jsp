package com.projetoagenda.conection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloDeConexao {
	
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	String url="jdbc:mysql://localhost:3306/dbagenda";
	
	/*LOGIN E SENHA DO USUARIO DO BANCO*/
	String user ="root";
	String password ="";
	
	private Connection getConectar() {
		
		Connection conexao =null;
		
		try {
			
			Class.forName(driver); /*conecta ao driver*/
			
			conexao = DriverManager.getConnection(url,user, password);
			
			return conexao;	
			  
		} catch (Exception e) {
			
			System.out.println(e);
			
			return null;
			
		} 	
	}
	
	public void testeConexao() {
		
		try {
			
			Connection conexao = getConectar();
			System.out.println(conexao);
			
			conexao.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
	}
	

}
