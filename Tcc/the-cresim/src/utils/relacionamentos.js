import { interacoesApi } from "../services/api/api.js";

// Funcao para interagir
export const interagir = async (cresim1, cresim2, interacao) => {
    const interacoesObj = await interacoesApi();

    // Obtem ou inicializa o relacionamento entre os dois cresims escolhidos
    const rela = getRelacionamento(cresim1, cresim2);

    // Verifica se a interacao escolhida esta disponivel de acordo com o realcionamento atual
    const interacoesDisponiveis = getInteracoesDisponiveis(rela.pontos);

    const categoriainteracao = obterCategoria(interacao.interacao, interacoesObj);

    if (!categoriainteracao || !interacoesDisponiveis.includes(categoriainteracao)) {
        console.log(`Interação não está disponível para o nível atual (${rela.nivel}).`);
        return { cresim1, cresim2 };
    }

    // calcula o tempo da interacao
    const tempoInteracao = interacao.energia * 2000;


    // calula a energia que cada cresim gasta 
    const energiaGastaCresim1 = interacao.energia;
    const energiaGastaCresim2 = Math.ceil(interacao.energia / 2);

    // Atualiza a energia dos cresims
    const cresimAtualizado1 = {
        ...cresim1,
        energia: Math.max(0, cresim1.energia - energiaGastaCresim1)
    };

    const cresimAtualizado2 = {
        ...cresim2,
        energia: Math.max(0, cresim2.energia - energiaGastaCresim2)
    };

    console.log(`${cresim1.nome} inicia a interação "${interacao.interacao}" com ${cresim2.nome}.`);
    console.log(`Tempo da interação: ${tempoInteracao}ms`);

    return new Promise((resolve) => {
        // Atualiza os pontos do relacionamento
        const novosPontos = rela.pontos + interacao.pontos;
        const novoNivel = getNivelRelacao(novosPontos);

        // Cria um objeto de relacionamento atualizado
        const relAtualizado = { pontos: novosPontos, nivel: novoNivel };

        // Atualiza o relacionamento no Cresim 1
        const finalCresim1 = {
            ...cresimAtualizado1,
            relacionamentos: {
                ...cresimAtualizado1.relacionamentos,
                [cresim2.id]: relAtualizado
            }
        };

        // Atualiza o relacionamento no Cresim 2 
        const finalCresim2 = {
            ...cresimAtualizado2,
            relacionamentos: {
                ...cresimAtualizado2.relacionamentos,
                [cresim1.id]: relAtualizado
            }
        };

        console.log(`Interação "${interacao.interacao}" concluída.`);
        console.log(`Relacionamento entre ${cresim1.nome} e ${cresim2.nome}: ${novosPontos > 0 ? `subiu ${novosPontos}` : `desceu ${novosPontos}`} pontos, nivel de relacionamento atual: ${novoNivel}.`);
        console.log(`Interações disponíveis agora: ${getInteracoesDisponiveis(novosPontos).join(', ')}`);

        // Retorna os Cresims atualizados
        resolve([finalCresim1, finalCresim2]);
    }, tempoInteracao);
};

// Retorna o niel de relacionamento com base nos pontos acumulados
export const getNivelRelacao = (pontos) => {
    if (pontos < 0) return 'INIMIZADE';
    if (pontos >= 0 && pontos <= 10) return 'NEUTRO';
    if (pontos >= 11 && pontos <= 25) return 'AMIZADE';
    if (pontos > 25) return 'AMOR';
}

// Retrona as interacoes disponiveis de acordo com o nivel de relacionamento
export const getInteracoesDisponiveis = (pontos) => {
    const nivel = getNivelRelacao(pontos);

    switch (nivel) {
        case 'NEUTRO':
            return ['NEUTRO'];
        case 'AMIZADE':
            return ['NEUTRO', 'AMIZADE'];
        case 'AMOR':
            return ['NEUTRO', 'AMIZADE', 'AMOR'];
        case 'INIMIZADE':
            return ['NEUTRO', 'INIMIZADE'];
        default:
            return ['NEUTRO'];
    }
}

// Obtem ou inicializa o relacionamento entre dois cresims
export const getRelacionamento = (cresimA, cresimB) => {

    // Inicializa o relacionamentos se não existir
    if (!cresimA.relacionamentos) {
        cresimA.relacionamentos = {};
    }

    if (!cresimA.relacionamentos[cresimB.id]) {
        cresimA.relacionamentos[cresimB.id] = { pontos: 0, nivel: 'NEUTRO' };
    }

    // Inicializa o relacionamentos se não existir
    if (!cresimB.relacionamentos) {
        cresimB.relacionamentos = {};
    }

    if (!cresimB.relacionamentos[cresimA.id]) {
        cresimB.relacionamentos[cresimA.id] = { pontos: 0, nivel: 'NEUTRO' };
    }

    return cresimA.relacionamentos[cresimB.id];
}

export const obterCategoria = (interacao, interacoesObj) => {
    for (const categoria in interacoesObj) {
        if (interacoesObj[categoria].some(item => item.interacao === interacao)) {
            return categoria;
        }
    }
    return null;
}