// Função para comprar um item de habilidade e adicionar ao inventario do Cresim
export const comprarItemHabilidade = (item, cresim) => {
    if (cresim.cresceleons >= item.preco) {
        return {
            ...cresim,
            cresceleons: cresim.cresceleons - item.preco,
            itens: [...cresim.itens, item]
        };
    } else {
        return `Cresceleons insuficiente para comprar ${item.nome}. Você precisa de mais ${item.preco - cresim.cresceleons} moedas.`;
    };
};

// Função para calcular o nivel da habilidade que foi treinada
export const calcularNivelHabilidade = (pontos) => {
    if (pontos >= 0 && pontos <= 16) return "JUNIOR";
    if (pontos >= 17 && pontos <= 26) return "PLENO";
    return "SÊNIOR";
};

// retorna falso caso o Cresim não tenha energia para treinar as habilidades
const podeTreinarHabilidade = (cresim) => cresim.energia > 4 && cresim.higiene > 0;

// Funçao que treina a habilidade selecionada e atualiza o nivel das habilidades
export const treinarHabilidade = (cresim, item, itensAPI) => {
    if (!podeTreinarHabilidade(cresim)) {
        return `Energia insuficiente para treinar habilidade! Voce precisa de no minimo 4 pontos de energia para treinar habilidades`
    };

    const habilidadeObjeto = cresim.pontosHabilidade.find((habilidade) => {
        return itensAPI[habilidade.categoria]?.some((obj) => obj.nome === item.nome);
    });

    console.log(`${cresim.nome} esta treinando com ${item.nome}...`);

    // Utiliza uma promisse para retornar a nova instancia do personagem com as infos atualizadas
    return new Promise((resolve) => {
        setTimeout(() => { 
            const pontosAspiracao = cresim.aspiracao === habilidadeObjeto.categoria ? 1 : 0;
            const novosPontos = habilidadeObjeto.pontos + item.pontos + pontosAspiracao;
            const novoNivel = calcularNivelHabilidade(novosPontos);
            console.log(pontosAspiracao, novosPontos, novoNivel)

            const cresimAtualizado = {
                ...cresim,
                energia: Math.max(0, cresim.energia - 4),
                higiene: Math.max(0, cresim.higiene - 2),
                pontosHabilidade: cresim.pontosHabilidade.map((habilidade) =>
                    habilidade.categoria === habilidadeObjeto.categoria
                      ? { ...habilidade, pontos: novosPontos, nivel: novoNivel}
                      : habilidade
                  ),
            };

            console.log(`Treino concluido! ${cresim.nome} agora tem ${novosPontos} pontos de habilidade em ${habilidadeObjeto.categoria} e é ${novoNivel}`);

            resolve(cresimAtualizado);

        }, 8000);
    });
};