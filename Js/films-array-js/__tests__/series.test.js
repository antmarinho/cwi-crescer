import axios from 'axios'

import {
  verificarSeAtorEstaEmSeriado,
  filtarPorAnoERetornarNome,
  calcularMediaTotalDeEpisodios,
  agruparTituloDasSeriesPorPropriedade,
} from '../src/metodos'


describe('Exemplo de testes', () => {
  it('Valor importado deve ser true', () => {
    expect(true).toBeTruthy()
  })
})

describe('Exemplo de testes', () => {
  it('Deve filtrar as series com ano de estreia maior ou igual a 2010 e retornar uma listagem com os nomes', async() => {
    
    const respost = await axios.get('https://gustavobuttenbender.github.io/film-array/data/films.json')
    
    //console.log(respost.data)
    
    const aux = ['Stranger Things',
    'Game Of Thrones',
    'The Walking Dead',
    'Band of Brothers',
    'Gus and Will The Masters of the Wizards',
    '10 Days Why',
    'Mr. Robot',
    'Narcos',
    'Westworld']
    const list = filtarPorAnoERetornarNome(respost.data, 2010)
    console.log(list)

    expect(list).toEqual(aux)
  })
})

describe('Exemplo de testes', () => {
  it('Deve retornar true ao procurar ator que está em elenco', async () => {
    
    const respost = await axios.get('https://gustavobuttenbender.github.io/film-array/data/films.json')
    
    const list = verificarSeAtorEstaEmSeriado(respost.data, 'Wagner Moura')

    expect(list).toEqual(true)
  })
})

describe('Exemplo de testes', () => {
  it('Deve retornar false ao procurar ator que não participa de elenco', async () => {
    
    const respost = await axios.get('https://gustavobuttenbender.github.io/film-array/data/films.json')
    
    const list = verificarSeAtorEstaEmSeriado(respost.data, 'Pietro')

    expect(list).toEqual(false)
  })
})

describe('Exemplo de testes', () => {
  it('Deve calcular corretamente a media total de episódios de todas as series', async () => {
    
    const respost = await axios.get('https://gustavobuttenbender.github.io/film-array/data/films.json')
    
    const aux = 35.8
    const list = calcularMediaTotalDeEpisodios(respost.data)

    expect(list).toEqual(aux)
  })
})

describe('Exemplo de testes', () => {
  it('Deve agrupar corretamente em um objeto os titulos das series baseado na Distribuidora', async () => {
    
    const respost = await axios.get('https://gustavobuttenbender.github.io/film-array/data/films.json')
    
    const aux = 'Game Of Thrones, Band of Brothers, Westworld'
    const list = agruparTituloDasSeriesPorPropriedade(respost.data, 'HBO')

    expect(list).toEqual(aux)
  })
})