import { calcularNivelHabilidade } from "./habilidades.js";

const iniciarTrabalho = (cresim, trabalho) => {
    //verifica se sim tem energia minima para trabalhar
    if (!podeTrabalhar(cresim)) {
        return 'Energia insuficiente para iniciar o trabalho no momento';
    }

    //retorna o novo cresim após calcular com base na energia
    return calcularioSalario(cresim, trabalho);
}

//retorna false se cresim tiver energia menor ou igual a 4
const podeTrabalhar = (cresim) => cresim.energia > 4 && cresim.higiene - 4 > 0;

const calcularioSalario = (cresim, trabalho) => {
    if (cresim.energia <= 4 || cresim.higiene - 4 <= 0) return "Energia insuficiente para iniciar o trabalho no momento";

    console.log(`${cresim.nome} começou a trabalhar em ${trabalho.categoria}`)

    //atribuição de valores que serão usados para os calculos
    let cresimAtualizado;
    const jornadaTrabalhoPadrao = 20.000;
    const energiaAtual = cresim.energia;
    const habilidadeObjeto = cresim.pontosHabilidade.find(habilidade => habilidade.categoria === trabalho.categoria);
    const salarioObjeto = trabalho.salario.find(s => s.nivel === calcularNivelHabilidade(habilidadeObjeto.pontos));
    const salarioBase = salarioObjeto.valor;

    let quantAReceber;

    if (energiaAtual <= 11 || cresim.higiene - 4 < 4) {
        //atribuição de valores que serão usados para os calculos de cresim com energia igual ou menor que 10
        const perdaEnergiaPadrao = 10;
        const energiaMinima = 2;
        const msParaEnergia = jornadaTrabalhoPadrao / perdaEnergiaPadrao;
        const tempoATrabalhar = energiaAtual - energiaMinima * msParaEnergia;
        const tempoParaUmCresceleon = jornadaTrabalhoPadrao / salarioBase;
        const cresceleonsPorEnergia = msParaEnergia / tempoParaUmCresceleon;

        const energiaRecalculo = 5 - energiaMinima;

        return new Promise((resolve) => {
            setTimeout(() => {

                console.log(`${cresim.nome} está trabalhando no momento, aguarde até o fim da sua jornada de trabalho.`);

                //calcula salario de cresim cansado
                const quantAReceberCansado = (energiaRecalculo * cresceleonsPorEnergia) * 0.9;

                //calcula salario de cresim descansado
                const energiaDescansado = energiaAtual - 5;
                const quantAReceberDescansado = energiaDescansado * cresceleonsPorEnergia;

                //total a receber
                quantAReceber = quantAReceberCansado + quantAReceberDescansado;

                //copia informações de cresim para um novo e atualiza valores
                cresimAtualizado = {
                    ...cresim, energia: energiaMinima,
                    tempo: cresim.tempo - tempoATrabalhar,
                    higiene: cresim.higiene - 4
                }
                //só tem desconto se ele começa a trabalhar com 10 de energia ou menos e com higiene menor que quatro
                if (cresimAtualizado.higiene < 4) {
                    cresimAtualizado.cresceleons = cresimAtualizado.cresceleons + (quantAReceber * 0.9);
                } else if (energiaAtual == 11 || energiaAtual == 10) {
                    cresimAtualizado.cresceleons += salarioBase;
                } else cresimAtualizado.cresceleons += quantAReceber;

                resolve(cresimAtualizado);
            }, tempoATrabalhar);
        });
    };

    return new Promise((resolve) => {
        setTimeout(() => {

            console.log(`${cresim.nome} está trabalhando no momento, aguarde até o fim da sua jornada de trabalho.`);

            //copia informações de cresim para um novo e atualiza valores
            cresimAtualizado = {
                ...cresim, energia: cresim.energia - 10,
                cresceleons: cresim.cresceleons + salarioBase,
                tempo: cresim.tempo - jornadaTrabalhoPadrao,
                higiene: cresim.higiene - 4
            };

            resolve(cresimAtualizado);
        }, jornadaTrabalhoPadrao);
    });
};

export { iniciarTrabalho, calcularioSalario, podeTrabalhar };