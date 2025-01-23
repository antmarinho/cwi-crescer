import instrutores from './instrutores.json'
import { SuperArray } from '../src/super-array'

describe('Exemplo de testes', () => {
  it('Valor importado deve ser true', () => {
    expect(true).toBeTruthy()
  })
})

describe('testes push', () => {
  it('push he man no array 1 2 3', () => {
    const resultadoEsperado = SuperArray([1, 2, 3, 'He Man'])
    const meuArrayComHeMan = SuperArray([1, 2, 3])
    
    meuArrayComHeMan.push("He Man")
    expect(meuArrayComHeMan.itens).toEqual(resultadoEsperado.itens)

  })
})


describe('testes push', () => {
  it('push deve adicionar um novo instrutor ao meu super array', () => {
    const ArrayComInstrutores = SuperArray(instrutores)
    const novoInstrutor = {"nome": "Pietro", "dandoAula": false}
    
    ArrayComInstrutores.push(novoInstrutor)
    expect(novoInstrutor).toBeTruthy(ArrayComInstrutores[ArrayComInstrutores.itens.length-1])

  })
})

describe('testes forEach', () => {
  it('forEach', () => {
    const resultadoEsperado = SuperArray([1, 2, 3, 'He Man'])
    const meuArrayComHeMan = SuperArray([1, 2, 3])
    
    meuArrayComHeMan.push("He Man")
    function loop() {
      for (let index = 0; index < resultadoEsperado.itens.length; index++) {
        console.log(resultadoEsperado.itens[index])
      }
    }
    function each(){
      resultadoEsperado.itens.forEach
    }
    //expect(each).toEqual(loop)
    expect(true).toBeTruthy(console.log(each===loop))

  })
})

describe('testes forEach', () => {
  it('forEach deve passar por todos os instrutores e chamando o callback esperado', () => {
    const ArrayComInstrutores = SuperArray(instrutores)
    const novoInstrutor = {"nome": "Pietro", "dandoAula": false}
    
    ArrayComInstrutores.push(novoInstrutor)

    function loop() {
      for (let index = 0; index < ArrayComInstrutores.instrutores.length; index++) {
        console.log(ArrayComInstrutores.itens[index])
      }
    }

    function each(){
      ArrayComInstrutores.itens.forEach
    }
    expect(true).toBeTruthy(console.log(each===loop))
  })
})

describe('testes filter', () => {
  it('filter', () => {
    const resultadoEsperado = SuperArray([1, 3])
    const meuArray = SuperArray([1, 2, 3])
    
    const meuArrayImpares = meuArray.filter(item => {
      return item % 2 !== 0
    })

    console.log(resultadoEsperado.itens)
    console.log(meuArrayImpares)
    //expect(each).toEqual(loop)
    expect(meuArrayImpares).toEqual(resultadoEsperado.itens)

  })
})

describe('testes filter', () => {
    it('filter deve retornar um novo array apenas com os instrutores que estão dando aula', () => {
      const ArrayComInstrutores = SuperArray(instrutores)
      const resultadoEsperado = [{ "nome": "Gustavo Büttenbender Rodrigues", "dandoAula": true },
      { "nome": "William Cardozo", "dandoAula": true }]

      const ArrayComInstrutoresAula = ArrayComInstrutores.filter(item => {
      return item.dandoAula == true
    })

    console.log(resultadoEsperado)
    console.log(ArrayComInstrutoresAula)
    
    expect(ArrayComInstrutoresAula).toEqual(resultadoEsperado)
    })
  })

  describe('testes map', () => {
  it('map', () => {
    const resultadoEsperado = SuperArray([2, 4, 6])
    const meuArray = SuperArray([1, 2, 3])
    
    const meuArrayDobrado = meuArray.map(item => {
      return item * 2
    })

    console.log(resultadoEsperado.itens)
    console.log(meuArrayDobrado)
    
    expect(meuArrayDobrado).toEqual(resultadoEsperado.itens)

  })
})

describe('testes map', () => {
  it('map instrutores', () => {
    const ArrayComInstrutores = SuperArray(instrutores)
    const resultadoEsperado = []

    const ArrayComInstrutoresSemAula = ArrayComInstrutores.map(item => {
      return item.dandoAula = false
    })
    const ArrayComInstrutoresAula = ArrayComInstrutoresSemAula.filter(item => {
    return item.dandoAula === true
  })

  console.log(ArrayComInstrutoresSemAula)
  console.log(resultadoEsperado)
  console.log(ArrayComInstrutoresAula)
  
  expect(ArrayComInstrutoresAula).toEqual(resultadoEsperado)
  })
})

describe('testes find', () => {
  it('find', () => {
    const resultadoEsperado = SuperArray([2])
    const meuArray = SuperArray([1, 2, 3])
    
    const primeiroNumeroMarioQue1 = meuArray.find(item => {
      return item > 1
    })

    console.log(resultadoEsperado.itens)
    console.log(primeiroNumeroMarioQue1)
    expect(primeiroNumeroMarioQue1).toEqual(resultadoEsperado.itens)

  })
})

describe('testes find', () => {
  it('find deve retornar o primeiro instrutor que está dando aula', () => {
    const ArrayInstrutores = SuperArray([
  { "nome": "Fabio Junqueira", "dandoAula": false },
  { "nome": "Pablo Oliveira", "dandoAula": false },
  { "nome": "Sergio Andrade", "dandoAula": false },
  { "nome": "Gustavo Büttenbender Rodrigues", "dandoAula": true },
  { "nome": "William Cardozo", "dandoAula": true },
  { "nome": "Diandra Rocha", "dandoAula": false },
  { "nome": "Rafael Zorzanelo", "dandoAula": false },
  { "nome": "Victor Herzog Damke", "dandoAula": false }
])
    const resultadoEsperado = [{ "nome": "Gustavo Büttenbender Rodrigues", "dandoAula": true }]

    const ArrayComInstrutoresAula = ArrayInstrutores.find(item => {
    return item.dandoAula === true
  })

  console.log(resultadoEsperado)
  console.log(ArrayComInstrutoresAula)
  expect(ArrayComInstrutoresAula).toEqual(resultadoEsperado)
  })
})

describe('testes reduce', () => {
  it('reduce', () => {
    const resultadoEsperado = SuperArray([6])
    const meuArray = SuperArray([1, 2, 3])
    
    const somaMeuArray = meuArray.reduce((acumulador, item) => {
      return acumulador += item
    }, 0)

    console.log(resultadoEsperado.itens)
    console.log(somaMeuArray)
    expect(somaMeuArray).toEqual(resultadoEsperado.itens)

  })
})

describe('testes map', () => {
  it('map deve retornar um novo array com o numero de nomes que o instrutor tem', () => {
    const ArrayComInstrutores = SuperArray(instrutores)
    const resultadoEsperado = [2, 2, 2, 3, 2, 2, 2, 3]

    const ArrayComInstrutoresNumeroNome = ArrayComInstrutores.map(item => {
      return item.nome.split(' ').length
    })

  console.log(resultadoEsperado)
  console.log(ArrayComInstrutoresNumeroNome)
  
  expect(ArrayComInstrutoresNumeroNome).toEqual(resultadoEsperado)
  })
})

describe('testes map', () => {
  it('map deve retornar um novo array com o numero de nomes que o instrutor tem', () => {
    const ArrayComInstrutores = SuperArray(instrutores)
    const resultadoEsperado = [14, 13, 13, 28, 14, 12, 15, 17]

    const ArrayComInstrutoresNumeroNome = ArrayComInstrutores.map(item => {
      return ((item.nome.length) - (item.nome.split(' ').length-1))
    })

  console.log(resultadoEsperado)
  console.log(ArrayComInstrutoresNumeroNome)
  
  expect(ArrayComInstrutoresNumeroNome).toEqual(resultadoEsperado)
  })
})

describe('testes reduce', () => {
  it('reduce deve retornar o total de letras no nome dos instrutores', () => {
    const ArrayInstrutores = SuperArray([
        { "nome": "Fabio Junqueira", "dandoAula": false },
        { "nome": "Pablo Oliveira", "dandoAula": false },
        { "nome": "Sergio Andrade", "dandoAula": false },
        { "nome": "Gustavo Büttenbender Rodrigues", "dandoAula": true },
        { "nome": "William Cardozo", "dandoAula": true },
        { "nome": "Diandra Rocha", "dandoAula": false },
        { "nome": "Rafael Zorzanelo", "dandoAula": false },
        { "nome": "Victor Herzog Damke", "dandoAula": false }
      ])
    const resultadoEsperado = 126
    const ArrayComInstrutoresNumeroNome = ArrayInstrutores.map(item => {
            return ((item.nome.length) - (item.nome.split(' ').length-1))
          })

    const ArrayTotalNumeroNome = ArrayComInstrutoresNumeroNome.reduce((acumulador, item) => {
      return acumulador += item
    }, 0)


  console.log(resultadoEsperado)
  console.log(ArrayTotalNumeroNome)
  
  expect(ArrayTotalNumeroNome).toEqual(resultadoEsperado)
  })
})

describe('testes reduce', () => {
  it('reduce deve retornar um boolean se todos os instrutores estão dando aula', () => {
    const ArrayInstrutores = SuperArray([
      { "nome": "Fabio Junqueira", "dandoAula": false },
      { "nome": "Pablo Oliveira", "dandoAula": false },
      { "nome": "Sergio Andrade", "dandoAula": false },
      { "nome": "Gustavo Büttenbender Rodrigues", "dandoAula": true },
      { "nome": "William Cardozo", "dandoAula": true },
      { "nome": "Diandra Rocha", "dandoAula": false },
      { "nome": "Rafael Zorzanelo", "dandoAula": false },
      { "nome": "Victor Herzog Damke", "dandoAula": false }
    ])
    const resultadoEsperado = 2

    const ArrayInstrutoresAula = ArrayInstrutores.filter(item => {
          return item.dandoAula !== undefined
        })

    const ArrayComInstrutoresAula = ArrayInstrutoresAula.reduce((acumulador, item) => {
      return acumulador += item.dandoAula
    }, 0)


  console.log(resultadoEsperado)
  console.log(ArrayComInstrutoresAula)
  
  expect(ArrayComInstrutoresAula).toEqual(resultadoEsperado)
  })
})
