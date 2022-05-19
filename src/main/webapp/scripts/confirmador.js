/**
 * 
 */
 
 
 function confirmar(idcon){
	
	let resposta = confirm("Confirma a exclusão deste contato ?")
	if (resposta === true){
		
		//alert(idcon)
		
		//window.location.href - metodo que redireciona para outro local
		window.location.href = "delete?idcon=" + idcon
	}
	
	
}