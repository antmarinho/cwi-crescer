import poke from '../src/poke.json'
import { capturarpokemon } from '../src/index'
import { evoluir } from '../src/index'
import { subirLevel } from '../src/index'
import { addPokemon } from '../src/index'
import { novoTreinador } from '../src/index'

describe('Exemplo de testes', () => {
  it('Valor importado deve ser true', () => {
    expect(true).toBeTruthy()
  })
})

describe('test 1', () => {
  it('Deve subir o nível do pokemon corretamente', () => {
    const listaPokemons = [poke]
    const treinadoratualizado = novoTreinador('Pietro', 26, 'Squirtle')
    const result = 6
    capturarpokemon(treinadoratualizado, 'Bulbasaur')
    capturarpokemon(treinadoratualizado, 'Cyndaquil')
    capturarpokemon(treinadoratualizado, 'Blastoise')
    capturarpokemon(treinadoratualizado, 'Thyphlosion')
    capturarpokemon(treinadoratualizado, 'Venusaur')

    const levelpokemon = treinadoratualizado.listapokemontreinador[0].levelInicial

    expect(levelpokemon).toEqual(result)
  })
})

describe('test 2', () => {
  it('Deve evoluir pokemon ao atingir o nível necessário', () => {
    const treinador = novoTreinador('Pi', 26, 'Squirtle')

    capturarpokemon(treinador, 'Bulbasaur')
    capturarpokemon(treinador, 'Cyndaquil')
    capturarpokemon(treinador, 'Blastoise')
    capturarpokemon(treinador, 'Thyphlosion')
    
    const levelpokemon = treinador.listapokemontreinador[0]
    expect(treinador.listapokemontreinador[0].nome).toEqual('Wartortle')
  })
})

describe('test 3', () => {
  it('Não deve evoluir pokemon caso não possua o level necessário', () => {
    const treinadoratualizado = novoTreinador('Pietro', 26, 'Squirtle')

    capturarpokemon(treinadoratualizado, 'Bulbasaur')
    capturarpokemon(treinadoratualizado, 'Cyndaquil')
    capturarpokemon(treinadoratualizado, 'Blastoise')
    capturarpokemon(treinadoratualizado, 'Thyphlosion')
    
    
    expect(treinadoratualizado.listapokemontreinador[2].nome).toEqual('Cyndaquil')
  })
})

describe('test 4', () => {
  it('Treinador será criado com nome correto', () => {
    const treinadoratualizado = novoTreinador('Pietro', 26, 'Squirtle')

    capturarpokemon(treinadoratualizado, 'Bulbasaur')
    capturarpokemon(treinadoratualizado, 'Cyndaquil')
    capturarpokemon(treinadoratualizado, 'Blastoise')
    capturarpokemon(treinadoratualizado, 'Thyphlosion')
    
    
    expect(treinadoratualizado.nome).toEqual('Pietro')
  })
})

describe('test 5', () => {
  it('Treinador será criado com a idade correta', () => {
    const treinadoratualizado = novoTreinador('Pietro', 26, 'Squirtle')

    capturarpokemon(treinadoratualizado, 'Bulbasaur')
    capturarpokemon(treinadoratualizado, 'Cyndaquil')
    capturarpokemon(treinadoratualizado, 'Blastoise')
    capturarpokemon(treinadoratualizado, 'Thyphlosion')
    
    
    expect(treinadoratualizado.idade).toEqual(26)
  })
})

describe('test 6', () => {
  it('Treinador será criado com o pokemon inicial correto', () => {
    const treinadoratualizado = novoTreinador('Pietro', 26, 'Squirtle')

    capturarpokemon(treinadoratualizado, 'Bulbasaur')    
    
    expect(treinadoratualizado.listapokemontreinador[0].nome).toEqual('Squirtle')
  })
})

describe('test 7', () => {
  it('Treinador terá seus pokemons atualizados após nova captura', () => {
    const treinadoratualizado = novoTreinador('Pietro', 26, 'Squirtle')
    const resultadoesperado = novoTreinador('teste', 0, 'Squirtle')
    capturarpokemon(resultadoesperado, 'Bulbasaur')
    capturarpokemon(treinadoratualizado, 'Bulbasaur')
    
    expect(treinadoratualizado.listapokemontreinador).toEqual(resultadoesperado.listapokemontreinador)
  })
})