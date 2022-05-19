<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="com.projetoagenda.model.AgendaBeans"%>
    <%@ page import="java.util.ArrayList"%>
   
    
    <%
    
   ArrayList<AgendaBeans> lista = (ArrayList<AgendaBeans>) request.getAttribute("contatos");
   
    	
    %>
    
<!DOCTYPE html>
<html lang="pt=br">
<head>
<meta charset="utf-8">
<link rel="icon" href="imagens/telefone.png"> <!-- inclue uma imagens na ABA da pagina-->
<link rel="stylesheet" type="text/css" href="style.css">

<title>Agenda de contatos</title>
</head>
<body>
    <h1>Agenda de contatos</h1>
    <a  href="novo.html" class="botao1" href="">Novo contato</a>
    <table id="tabela" >
        <thead>
            <tr> <!-- COLUNA -->
              <th>id</th>  <!-- LABEL DAS COLUNAS-->
              <th>Nome</th>  
              <th>Fone</th>  
              <th>E-mail</th>
              <th>Opções</th>  
            </tr>
        </thead>
        <tbody> <!-- CONTEUDOS DA TABELA -->
            <%for (int i=0; i< lista.size(); i++){ %>
            <tr> <!--  LINHA DA TABELA -->
             <td><%=lista.get(i).getIdcon()%></td>
             <td><%=lista.get(i).getNome()%></td>
             <td><%=lista.get(i).getFone()%></td>
             <td><%=lista.get(i).getEmail()%></td>
             <td><a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="botao1">Editar</a>
             
                    <!--  busca o metodo javascript e envia a id para o metodo do documneto javascript -->
                 <a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="botao2">Excluir</a>
             
             </td> <!-- href="select" - para apontar para o servelets  -->
                     <!--    (?) para informar que vai enviar um parametro -->
            </tr>
            <%} %>
        </tbody>
    </table>
    
    <!-- linkar o arquivo javascriot -->
    <script src="scripts/confirmador.js" ></script>
</body>
</html>















