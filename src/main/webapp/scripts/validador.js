/**
 *  Validação de formuladorio
 *  //campo nome="" dos elemnetos html serve para usar com o javascript como se fose um identificador(this) do elemento
 * 
 */
 
 function validar(){


    let nome = formContato.nome.value
     /*vai receber do formulario(name="formContato") do campo <imput name="name"> o valor (value)
     colocado na caixa de testo */
     let fone = formContato.fone.value /*ducumento recebe os dados escritos na caixa de texto*/

     

     if(nome === ''){
        
        alert('Prencha o campo Nome') /* metodo para mostrar uma mensagem de alerta na tela */
        formContato.nome.focus() /*posiciona o cursor novamente na caixa de alerta*/
        return false
     } else if (fone === '') {
        
        alert('Prencha o campo Phone') /* metodo para mostrar uma mensagem de alerta na tela */
        formContato.fone.focus() /*posiciona o cursor novamente na caixa de alerta*/
        return false

     }else {

        document.forms['formContato'].submit() /*submete os dados para a camada controller*/
     }
}