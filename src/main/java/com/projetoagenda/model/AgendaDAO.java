package com.projetoagenda.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.projetoagenda.conection.ModuloDeConexao;

public class AgendaDAO {
	
	
	
	
	/*INSERIR DADOS NO BANCO*/
	public void InserirContato(AgendaBeans contato) {
		
		
		/*QUERY PARA SER EXECUTADA NO BANCO*/
		String sql_create = "insert into contatos (nome,fone,email) values (?,?,?)"; /*COMANDO QUE ARMAZENA NO BANCO OS PARAMETROS*/
		
		try {
			
			Connection conexao = new ModuloDeConexao().getConectar(); /*INICIOA A CANEXÃO COM O BANCO*/
			
			/*PREPARAR a query sql para a execução no BANCO DE DADOS*/
			PreparedStatement pst = conexao.prepareCall(sql_create);
			
			/*subistituir os parametros (?) pelo conteudo do OBJETO*/
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			
			pst.executeUpdate(); /*EXECUTAR A QUERY*/
			
			conexao.close();
			pst.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
		}	
	}
	
	// RETORNA LISTA DE CONTATOS
	public ArrayList<AgendaBeans> listarContatos(){
		
		ArrayList<AgendaBeans> contatos = new ArrayList<AgendaBeans>();
		
		String sql_read = "select * from contatos order by nome"; /*comando que retorna os dados do banco em ordem alfabetica*/
		
		try {
			
			Connection conexao = new ModuloDeConexao().getConectar(); /*INICIOA A CANEXÃO COM O BANCO*/
			
			PreparedStatement pst = conexao.prepareStatement(sql_read); /*PREPARA a query para ser executado no BANCO*/
			
			ResultSet rs = pst.executeQuery(); /*ResultSet - RECEBE E ARMAZENA OS DADOS DO BANCO */
			
			while(rs.next()) {/*enquanto ouver contatos ele executa*/
				
				int idcon = rs.getInt(1); /*numeração é a POSIÇÃO da coluna no BANCO*/
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				
				contatos.add(new AgendaBeans(idcon, nome, fone, email));
				
			}
			
			conexao.close();
			pst.close();
			rs.close();
			
			return contatos;
			
		} catch (Exception e) {
			
			System.out.println(e);
			return null;
		}
		
	}
	
	//SELECIONA O CONTATO PELO ID do BANCO DE DADOS
	public void selecionarContato(AgendaBeans contato) {

		String sql_lerId = "select * from contatos where idcon = ?"; /*comando que BUSCA OS DADOS NO BANCO PELO PARAEMTRO INFORMADO*/
		
		try {
			
			Connection conexao = new ModuloDeConexao().getConectar();
			
			PreparedStatement pst = conexao.prepareStatement(sql_lerId); /*prepara o comendo a ser executado*/
			
			pst.setInt(1, contato.getIdcon());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) { /*enquanto ouver dados */
				
				//setar as variaveis javabeans
				contato.setIdcon(rs.getInt(1)); /*o numero é a posição das colunas na tabela*/
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));	
			}
			conexao.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//update
	public void alterarContato(AgendaBeans contato) {
		
		
		String sql_update = "update contatos set nome=?,fone=?,email=? where idcon=?";
		
		try {
			
			Connection conexao = new ModuloDeConexao().getConectar();
			
			PreparedStatement pst = conexao.prepareStatement(sql_update);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getIdcon());
			
			pst.executeUpdate();
			conexao.close();
			
		} catch (Exception e) {
			
			
			System.out.println(e);
		}
		
		
	}
	
	//delete
	public void deletarContato(AgendaBeans contato) {
		
		String sql_delete = "delete from contatos where idcon=?";
		
		try {
			
			Connection conexao = new ModuloDeConexao().getConectar();
			
			PreparedStatement pst = conexao.prepareStatement(sql_delete);
			
			pst.setInt(1, contato.getIdcon());
			
			pst.executeUpdate();
			
			conexao.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		
	}
	
	
	
	
	
	
	

}
