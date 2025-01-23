export const SuperArray = (itens = []) => {

  const array = {
    /**
     * Propriedade para acessar os itens
     */

    itens: [...itens],
  }

  let vector = []
  
  function loopFor(callback, itens, cont) {
    if (cont == undefined) {
      for (let index = 0; index < itens.length; index++) {
        vector[index] = callback(itens[index])
      }
    }else{
      for (let index = 0; index < itens.length; index++) {
        cont += callback(itens[index])
      }
      return cont
    }
    return vector
  }

  /**
   * Adicionar um novo item ao final dos items
   */

  array.push = item => {
    array.itens[array.itens.length] = item;
  }

  /**
   * Itera sobre cada um dos elementos do SuperArray enviando o item e o index
   * como segundo parametro
   */

  array.forEach = callback => {
    function imprimir(indice){
      console.log(array.itens[indice])
    }
    function loop(vezes, callback) {
      for (let index = 0; index < vezes; index++) {
        callback(index)
      }
    }
    loop(array.itens.length, imprimir)
  }

  /**
   * Retorna um novo SuperArray com os itens mapeados
   */

  array.map = callback => {
    // const filtered = []
    vector = loopFor(callback, itens)
    // for(let index = 0; index < newItens.length; index++)
    //     filtered[index] = array.itens[index]
    //   }
    // return filtered
    return vector
  }


  /**
   * Retorna um SuperArray novo com os itens filtrados
   */
  
  array.filter = callback => {
    const filtered = []
    let i = 0
    vector = loopFor(callback, itens)
    for(let index = 0; index < vector.length; index++)
      if (vector[index] == true) {
        filtered[i] = array.itens[index]
        i++
      }
    return filtered

  }


  /**
   * Retorna o primeiro elemento do SuperArray que satisfazer o callback recebido
   * se não encontrar, deve retornar undefined
   */

  array.find = callback => {
    const filtered = []
    let i = 0
    vector = loopFor(callback, itens)
    while(true) {
      if (vector[i] == true){
        filtered[0] = array.itens[i]
        break
      }
      else{i++} 
    }
    return filtered
  }

  /**
   * Reduz o SuperArray em um único valor
   */


  array.reduce = (callback, valorInicial) => {
    const filtered = []
    vector = loopFor(callback, itens, valorInicial)
     filtered[0] = valorInicial
     for(let index = 0; index < array.itens.length; index++){
         filtered[0] += array.itens[index]
       }
    return filtered
  }

  return array
}
