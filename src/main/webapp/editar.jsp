<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Editar contato</title>
</head>
<body>

  	<h1>Editar Contato</h1>

     <!-- name="formContato" - para pegar os dados pelo javascript  -->
	<form name="formContato" action="update" > <!--  nome da ação para o servelets receber -->

		<table>           
		    <tr>                                <!--  request.getAttribute("idcon") - "idcon" é o atributo do request.setAttribute("idcon", contato.getIdcon())  -->
				<td><input type="text" name="idcon"  id="caix3" readonly value="<%out.print(request.getAttribute("idcon"));%>" ></td> <!-- readonly - não permitir digitar na caixa -->
			</tr>
			<tr>
			<tr> <!-- tr LINHA DA TABELA -->
				<td><input type="text" name="nome"  class="caixa1" value="<%out.print(request.getAttribute("nome"));%>" ></td>
			</tr>
			<tr>
				<td><input type="text" name="fone"  class="caxa2" value="<%out.print(request.getAttribute("fone"));%>"></td> <!-- <td> COLUNAS DA TABELA-->
			</tr>
			<tr>
				<td><input type="text" name="email"  class="caixa1" value="<%out.print(request.getAttribute("email"));%>" ></td>
			</tr>
		</table>
		
		<input type="button" value="Salvar" class="botao1" onclick="validar()">
	</form>

<script src="scripts/validador.js" ></script> 

</body>
</html>