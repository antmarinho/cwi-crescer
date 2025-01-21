export const OPERACAO_INVALIDA = 'OPERACAO_INVALIDA'

export const calculadora = (operacao, valores) => {

  let resultado;
  
  switch(operacao) {

    case "soma":

      resultado = 0;

      for(let i = 0; i < valores.length; i++)
        resultado = resultado + valores[i]
      
      return resultado

    case "subtracao":

      resultado = valores[0]

      for(let i = 1; i < valores.length; i++)
        resultado = resultado - valores[i]
      
      return resultado

    case "multiplicacao":

      resultado = 1

      for(let i = 0; i < valores.length; i++)
        resultado = resultado * valores[i]
      
      return resultado

    case "divisao": 

      resultado = valores[0]

      for(let i = 1; i < valores.length; i++)
        resultado = resultado / valores[i]
      
      return resultado

    default:

      return OPERACAO_INVALIDA
      
  }

}
