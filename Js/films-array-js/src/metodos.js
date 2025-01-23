export function filtarPorAnoERetornarNome(series, ano) {
  let novaList = [];
  series.filter(x => x.anoEstreia >= ano).map(list => {novaList.push(list.titulo)})
  return novaList
}

export function verificarSeAtorEstaEmSeriado(series, nomeAtor) {
  let result = [];
  result = series.find(x => x.elenco.find(ator => ator === nomeAtor))
  if (result){
    return true
  } else {return false}
}

export function calcularMediaTotalDeEpisodios(series) {
  let result = 0
  series.map(x => {
    result += x.numeroEpisodios
  })
  return result/series.length
}

export function agruparTituloDasSeriesPorPropriedade(series, propriedade) {
  const reducer = (previousValue, currentValue) => previousValue + ', ' + currentValue;
  let novaList = [];
  series.filter(x => x.distribuidora == propriedade).map(list => {novaList.push(list.titulo)})
  return novaList.reduce(reducer)
}