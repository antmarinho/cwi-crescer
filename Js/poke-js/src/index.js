 const listaPokemonsOriginal = [ 
    {
      "id": 1,
      "nome": "Squirtle",
      "poderAtaque": 1,
      "levelInicial": 1,
      "evolucao": {
        "level": 5,
        "id": 2
      } 
    },
    {
      "id": 2,
      "nome": "Wartortle",
      "poderAtaque": 10,
      "levelInicial": 5,
      "evolucao": {
        "level": 10,
        "id": 3
      } 
    },
    {
      "id": 3,
      "nome": "Blastoise",
      "poderAtaque": 100,
      "levelInicial": 10,
      "evolucao": null
    },
    {
      "id": 4,
      "nome": "Cyndaquil",
      "poderAtaque": 1,
      "levelInicial": 1,
      "evolucao": {
        "level": 5,
        "id": 5
      }
    },
    {
      "id": 5,
      "nome": "Quilava",
      "poderAtaque": 10,
      "levelInicial": 5,
      "evolucao": {
        "level": 10,
        "id": 6
      }
    }, 
    {
      "id": 6,
      "nome": "Thyphlosion",
      "poderAtaque": 100,
      "levelInicial": 10,
      "evolucao": null
    }, 
    {
      "id": 7,
      "nome": "Bulbasaur",
      "poderAtaque": 1,
      "levelInicial": 1,
      "evolucao": {
        "level": 5,
        "id": 8
      }
    },
    {
      "id": 8,
      "nome": "Ivysaur",
      "poderAtaque": 10,
      "levelInicial": 5,
      "evolucao": {
        "level": 10,
        "id": 9
      }
    },
    {
      "id": 9,
      "nome": "Venusaur",
      "poderAtaque": 100,
      "levelInicial": 10,
      "evolucao": null
    }
  ]
  
  function listaPokemons(listapokemontreinador){
    let listaPokemons = listapokemontreinador
    return listaPokemons
  }

 function novoTreinador (nome, idade, pokeInicial) {
    let lista = listaPokemons(listaPokemonsOriginal)
    const pokemoninicial = lista.find(pokemon => pokemon.nome == pokeInicial)
    let treinador = {
        nome,
        idade,
        listapokemontreinador:[{... pokemoninicial}]
    }
    return treinador
}

 function addPokemon(treinador, pokemon) {
    let lista = listaPokemons(listaPokemonsOriginal)
    treinador.listapokemontreinador.push(lista.find(poke => poke.nome == pokemon))
}

 function subirLevel(treinador){
    return treinador.listapokemontreinador.map(pokemon =>{return {...pokemon, levelInicial: pokemon.levelInicial++}})
}

 function evoluir(treinador){
  let lista = listaPokemons(listaPokemonsOriginal)
    for (let index = 0; index < treinador.listapokemontreinador.length; index++) {
        if (treinador.listapokemontreinador[index].evolucao !== null) {
            if (treinador.listapokemontreinador[index].evolucao.level == treinador.listapokemontreinador[index].levelInicial) {
                treinador.listapokemontreinador[index] = lista.find(pokemon => pokemon.id == treinador.listapokemontreinador[index].evolucao.id)
            }
         }
    } 
}

 function capturarpokemon(treinador, pokemon) {
    
    addPokemon(treinador, pokemon)
    subirLevel(treinador)
    evoluir(treinador)   
}

export { capturarpokemon, evoluir, subirLevel, addPokemon, novoTreinador };
