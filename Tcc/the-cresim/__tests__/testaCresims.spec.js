import { calcularioSalario, iniciarTrabalho } from "../src/utils/trabalhar";
import { treinarHabilidade } from "../src/utils/habilidades";
import { comprarItemHabilidade } from "../src/utils/habilidades";
import { tomarBanho } from "../src/utils/tomarbanho";
import { atualizarPersonagens, deletarPersonagem, getPersonagem, getTodosPersonagens, mostrarPersonagens, setPersonagem, validarEnergiaEHigiene } from "../src/services/crud/personagem";
import { dormir } from "../src/utils/dormir";
import { interagir } from "../src/utils/relacionamentos"
import { executarCheat } from "../src/utils/cheats";
import { getLevelInteracoes, interacao } from "../src/utils/interacao";
import { setEnergia, setEnergiaDois, setTempoVida } from "../src/utils/comuns";
import { itensHabilidadesApi, interacoesApi, cheatsApi, empregosApi } from "../src/services/api/api.js";

describe('Testa funções de cresims', () => {
  it('Deve conseguir criar um novo Cresim com nome, pontos de higiene e energia carregados e 1500 Cresceleons', async () => {
    const personagem = await setPersonagem('João', 'pintura');

    expect(personagem.nome).toBe('João');
    expect(personagem.higiene).toBe(28);
    expect(personagem.energia).toBe(32);
    expect(personagem.cresceleons).toBe(1500);
  })

  it('Deve conseguir atribuir uma aspiração ao Cresim', async () => {
    const personagem = await setPersonagem('João', 'pintura');

    expect(personagem.aspiracao).toBe('pintura')
  })

  it('Deve validar os pontos de energia do personagem para que não passem de 32 pontos', () => {
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 28,
      energia: 42,
    };

    validarEnergiaEHigiene(personagem);

    expect(personagem.energia).toBe(32);
  })

  it('Deve validar os pontos de energia do personagem para que não fiquem negativados', () => {
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 28,
      energia: -2,
    };

    validarEnergiaEHigiene(personagem);

    expect(personagem.energia).toBe(0);
  })

  it('Deve conseguir dormir e receber seus pontos de energia', async () => {
    jest.useFakeTimers();
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 18,
      energia: 18,
    };

    const adormecer = dormir(personagem, 5000);

    jest.advanceTimersByTime(5000);

    const novoCresim = await adormecer;

    expect(novoCresim.energia).toBe(22);
  })

  it('Deve conseguir comprar um item de habilidade', () => {
    const cresim = { cresceleons: 5000, itens: [] };
    const item = { nome: "Frigideira", preco: 3000 };

    const resultado = comprarItemHabilidade(item, cresim);

    expect(resultado.cresceleons).toBe(2000);
    expect(resultado.itens).toContainEqual(item);
  })

  it('Deve validar ao tentar comprar um item de habilidade sem Cresceleons suficientes', () => {
    const cresim = { cresceleons: 2000, itens: [] };
    const item = { nome: "Frigideira", preco: 3000 };

    const resultado = comprarItemHabilidade(item, cresim);

    expect(resultado).toBe("Cresceleons insuficiente para comprar Frigideira. Você precisa de mais 1000 moedas.");
  })

  it('Deve conseguir concluir um ciclo de treino com habilidade que não é aspiração e receber os pontos corretamente', async () => {
    jest.useFakeTimers();

    const itensApi = await itensHabilidadesApi();
    const cresim = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 28,
      energia: 32,
      nivelTrabalho: "JUNIOR",
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 0 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ]
    };

    const item = { id: 1, nome: "Lápis mordido", categoria: "PINTURA", pontos: 4 };

    const treino = treinarHabilidade(cresim, item, itensApi);

    jest.advanceTimersByTime(8000);

    const novoCresim = await treino;

    expect(novoCresim.energia).toBe(28);
    expect(novoCresim.pontosHabilidade.find(h => h.categoria === "PINTURA").pontos).toBe(4);
    jest.useRealTimers();
  })

  it('Deve conseguir concluir um ciclo de treino com habilidade que é sua aspiração e receber os pontos corretamente', async () => {
    jest.useFakeTimers();

    const itensApi = await itensHabilidadesApi();

    const cresim = {
      nome: "Carlos",
      energia: 10,
      higiene: 20,
      aspiracao: "GASTRONOMIA",
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 17 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ]
    };

    const item = { id: 1, nome: "Frigideira da Polishop", pontos: 4 };

    const treino = treinarHabilidade(cresim, item, itensApi);

    jest.advanceTimersByTime(8000);

    const novoCresim = await treino;

    expect(novoCresim.energia).toBe(6);
    expect(novoCresim.pontosHabilidade.find(h => h.categoria === "GASTRONOMIA").pontos).toBe(22);
  })

  it('Deve perder pontos de energia ao terminar um ciclo de treino', async () => {
    jest.useFakeTimers();

    const itensApi = await itensHabilidadesApi();

    const cresim = {
      nome: "Carlos",
      energia: 10,
      higiene: 20,
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 23 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ],
      aspiracao: "GASTRONOMIA",
    };

    const item = { nome: "Panela elétrica mágica", categoria: "GASTRONOMIA", pontos: 4 };

    const treino = treinarHabilidade(cresim, item, itensApi);

    jest.advanceTimersByTime(8000);

    const novoCresim = await treino;

    expect(novoCresim.energia).toBe(6);
  })

  it('Deve perder pontos de higiene ao terminar um ciclo de treino', async () => {
    jest.useFakeTimers();
    const itensApi = await itensHabilidadesApi();

    const cresim = {
      nome: "Carlos",
      energia: 10,
      higiene: 10,
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 0 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ],
      aspiracao: "GASTRONOMIA",
    };

    const item = { nome: "Panela elétrica mágica", categoria: "GASTRONOMIA", pontos: 4 };

    const treino = treinarHabilidade(cresim, item, itensApi);

    jest.advanceTimersByTime(8000);

    const novoCresim = await treino;

    expect(novoCresim.higiene).toBe(8);
  })

  it('Deve avançar o nivel de habilidade quando completar os pontos necessarios', async () => {
    jest.useFakeTimers();

    const itensApi = await itensHabilidadesApi();

    const cresim = {
      nome: "Carlos",
      energia: 10,
      higiene: 10,
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 0 },
        { categoria: "JOGOS", pontos: 15 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ],
      aspiracao: "GASTRONOMIA",
    };

    const item = { nome: "Mouse com led", categoria: "JOGOS", pontos: 3 };

    const treino = treinarHabilidade(cresim, item, itensApi);

    jest.advanceTimersByTime(8000);

    const novoCresim = await treino;

    expect(novoCresim.pontosHabilidade.find(h => h.categoria === "JOGOS").pontos).toBe(18);
    expect(novoCresim.pontosHabilidade.find(h => h.categoria === "JOGOS").nivel).toBe("PLENO");
  })

  it('Deve perder os pontos de energia ao trabalhar uma jornada padrão', async () => {
    jest.useFakeTimers();

    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 28,
      energia: 32,
      nivelTrabalho: "JUNIOR",
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 0 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ]
    };

    const trabalho = {
      id: 1,
      cargo: "Jogador de Dota",
      categoria: "JOGOS",
      salario: [
        { nivel: "JUNIOR", valor: 160 },
        { nivel: "PLENO", valor: 250 },
        { nivel: "SENIOR", valor: 340 }
      ]
    };

    const trabalhar = iniciarTrabalho(personagem, trabalho);

    jest.advanceTimersByTime(20000);

    const novoPersonagem = await trabalhar;

    expect(novoPersonagem.energia).toBe(22);
  })

  it('Deve receber o salario do dia ao trabalhar uma jornda padrão', async () => {
    jest.useFakeTimers();

    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 28,
      energia: 11,
      nivelTrabalho: "JUNIOR",
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 0 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ]
    };

    const trabalho = {
      id: 1,
      cargo: "Jogador de Dota",
      categoria: "JOGOS",
      salario: [
        { "nivel": "JUNIOR", "valor": 160 },
        { "nivel": "PLENO", "valor": 250 },
        { "nivel": "SENIOR", "valor": 340 }
      ]
    };

    const trabalhar = iniciarTrabalho(personagem, trabalho);

    jest.advanceTimersByTime(20000);

    const novoPersonagem = await trabalhar;

    expect(novoPersonagem.cresceleons).toBe(1660);
  })

  it('Deve receber o salario equivalente quando começar a trabalhar com os pontos de energia menores que 10', async () => {
    jest.useFakeTimers();

    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 28,
      energia: 9,
      nivelTrabalho: "JUNIOR",
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 0 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ]
    };

    const trabalho = {
      id: 1,
      cargo: "Jogador de Dota",
      categoria: "JOGOS",
      salario: [
        { "nivel": "JUNIOR", "valor": 160 },
        { "nivel": "PLENO", "valor": 250 },
        { "nivel": "SENIOR", "valor": 340 }
      ]
    };

    const trabalhar = iniciarTrabalho(personagem, trabalho);

    jest.advanceTimersByTime(20000);

    const novoPersonagem = await trabalhar;

    expect(novoPersonagem.cresceleons).toBe(1607.2);
  })

  it('Deve receber o salario equivalente quando começar a trabalhar com os pontos de energia menores que 10 e pontos de higiene menores que 4', async () => {
    jest.useFakeTimers();

    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 7,
      energia: 9,
      nivelTrabalho: "JUNIOR",
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 0 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ]
    };

    const trabalho = {
      id: 1,
      cargo: "Jogador de Dota",
      categoria: "JOGOS",
      salario: [
        { "nivel": "JUNIOR", "valor": 160 },
        { "nivel": "PLENO", "valor": 250 },
        { "nivel": "SENIOR", "valor": 340 }
      ]
    };

    const trabalhar = iniciarTrabalho(personagem, trabalho);

    jest.advanceTimersByTime(20000);

    const novoPersonagem = await trabalhar;

    expect(novoPersonagem.cresceleons).toBe(1596.48);
  })

  it('Deve validar para que o Cresim não consiga começar a trabalhar com os pontos de energia menores que 4', async () => {
    jest.useFakeTimers();

    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 7,
      energia: 3,
      nivelTrabalho: "JUNIOR"
    };

    const trabalho = {
      id: 1,
      cargo: "Jogador de Dota",
      categoria: "JOGOS",
      salario: [
        { "nivel": "JUNIOR", "valor": 160 },
        { "nivel": "PLENO", "valor": 250 },
        { "nivel": "SENIOR", "valor": 340 }
      ]
    };

    const trabalhar = iniciarTrabalho(personagem, trabalho);

    expect(trabalhar).toBe("Energia insuficiente para iniciar o trabalho no momento");
  })

  it('Deve descontar 10 Cresceleons ao tomar banho', () => {
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 7,
      energia: 3,
      nivelTrabalho: "JUNIOR"
    };

    tomarBanho(personagem);

    expect(personagem.cresceleons).toBe(1490);
  })

  it('Deve evoluir o relacionamento de dois Cresims para AMIZADE', async () => {
    jest.useFakeTimers();

    const personagem1 = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
      relacionamentos: {}
    };

    const personagem2 = {
      id: 2,
      nome: "Maria",
      aspiracao: "jogos",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
      relacionamentos: {}
    }

    const interacao = {
      "id": 1,
      "interacao": "Cumprimentar",
      "pontos": 1,
      "energia": 0,
    }

    const interacao2 = {
      "id": 3,
      "interacao": "Elogiar",
      "pontos": 11,
      "energia": 1
    }

    const interage = interagir(personagem1, personagem2, interacao);

    jest.advanceTimersByTime(2000);

    const personagensPosInteracao1 = await interage;

    const interage2 = interagir(personagensPosInteracao1[0], personagensPosInteracao1[1], interacao2);

    jest.advanceTimersByTime(2000);

    const personagensPosInteracao2 = await interage2;

    expect(personagensPosInteracao2[0].relacionamentos[personagem2.id].nivel).toBe("AMIZADE");
    expect(personagensPosInteracao2[1].relacionamentos[personagem1.id].nivel).toBe("AMIZADE");
  })

  it('Deve recuar o relacionamento de dois Cresims para INIMIZADE', async () => {
    jest.useFakeTimers();

    const personagem1 = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
      relacionamentos: {}
    };

    const personagem2 = {
      id: 2,
      nome: "Maria",
      aspiracao: "jogos",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
      relacionamentos: {}
    }

    const interacao = {
      "id": 6,
      "interacao": "Criticar",
      "pontos": -3,
      "energia": 2
    }

    const interage = interagir(personagem1, personagem2, interacao);

    jest.advanceTimersByTime(4000);

    const personagensPosInteracao = await interage;

    expect(personagensPosInteracao[0].relacionamentos[personagem2.id].nivel).toBe("INIMIZADE");
    expect(personagensPosInteracao[1].relacionamentos[personagem1.id].nivel).toBe("INIMIZADE");
  })

  it('Deve descontar os pontos de energia em uma interação entre dois Cresims', async () => {
    jest.useFakeTimers();

    const personagem1 = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
      relacionamentos: {}
    };

    const personagem2 = {
      id: 2,
      nome: "Maria",
      aspiracao: "jogos",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 20,
      relacionamentos: {}
    }

    const interacao = {
      "id": 6,
      "interacao": "Criticar",
      "pontos": -3,
      "energia": 2
    }

    const interage = interagir(personagem1, personagem2, interacao);

    jest.advanceTimersByTime(4000);

    const personagensPosInteracao = await interage;

    expect(personagensPosInteracao[0].energia).toBe(21);
    expect(personagensPosInteracao[1].energia).toBe(19);
  })

  it('Deve conseguir aplicar o cheat SORTENAVIDA e receber as recompensas', async () => {
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 7,
      energia: 3,
      emprego: { salario: 10 }
    };

    const personagemComSorte = await executarCheat(personagem, "sorteNaVida");

    expect(personagemComSorte.emprego.salario).toBe(11);
  })

  it('Deve conseguir aplicar o cheat DEITADONAREDE e receber as recompensas', async () => {
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
    };

    const personagemComSorte = await executarCheat(personagem, "deitadonarede");

    expect(personagemComSorte.energia).toBe(28);
  })

  it('Deve conseguir aplicar o cheat JUNIM e receber as recompensas para a habilidade escolhida', async () => {
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
      pontosHabilidade: 2
    };

    const personagemComSorte = await executarCheat(personagem, "junim");

    expect(personagemComSorte.pontosHabilidade).toBe(7);
  })

  it('Deve conseguir aplicar o cheat CAROLINAS e receber as recompensas', async () => {
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 100000,
      higiene: 17,
      energia: 13,
    };

    const personagemComSorte = await executarCheat(personagem, "carolinaS");

    expect(personagemComSorte.tempo).toBe(200000);
  })

  it('Deve conseguir aplicar o cheat SINUSITE ter a vida zerada', async () => {
    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 13,
    };

    const personagemComSorte = await executarCheat(personagem, "SINUSITE");

    expect(personagemComSorte.tempo).toBe(0);
  })
}
)


describe("5 - Relacionamentos", () => {
  const personagemBase = {
    id: 1,
    energia: 50,
    tempo: 100000,
    relacionamento: [
      { id: 2, level: 0 }
    ]
  };

  const personagem2Base = {
    id: 2,
    energia: 50,
    tempo: 100000,
    relacionamento: [
      { id: 1, level: 0 }
    ]
  };

  const relacaoElogiar = {
    id: 3,
    interacao: "Elogiar",
    pontos: 4,
    energia: 1,
  };

  it("Deve evoluir o relacionamento de dois Cresims para AMIZADE", () => {
    let [novoPersonagem, novoPersonagem2] = [personagemBase, personagem2Base];
    for (let cont = 0; cont < 6; cont++) {
      [novoPersonagem, novoPersonagem2] = interacao(
        novoPersonagem,
        novoPersonagem2,
        relacaoElogiar
      );
    }

    const pontosPersonagem = novoPersonagem.relacionamento[0].level;
    const pontosPersonagem2 = novoPersonagem2.relacionamento[0].level;

    const nivelPersonagem = getLevelInteracoes(pontosPersonagem);
    const nivelPersonagem2 = getLevelInteracoes(pontosPersonagem2);

    expect(nivelPersonagem).toBe("AMIZADE");
    expect(nivelPersonagem2).toBe("AMIZADE");
  });

  it("Deve recuar o relacionamento de dois Cresims para INIMIZADE", () => {
    const personagemInimigo = { ...personagemBase };
    const personagem2Inimigo = { ...personagem2Base };

    const objInteracao = {
      id: 6,
      interacao: "Criticar",
      pontos: -3,
      energia: 2,
    };

    let [novoPersonagem, novoPersonagem2] = [personagemInimigo, personagem2Inimigo];
    for (let cont = 0; cont < 5; cont++) {
      [novoPersonagem, novoPersonagem2] = interacao(
        novoPersonagem,
        novoPersonagem2,
        objInteracao
      );
    }

    const pontosPersonagem = novoPersonagem.relacionamento[0].level;
    const pontosPersonagem2 = novoPersonagem2.relacionamento[0].level;

    const nivelPersonagem = getLevelInteracoes(pontosPersonagem);
    const nivelPersonagem2 = getLevelInteracoes(pontosPersonagem2);

    expect(nivelPersonagem).toBe("INIMIZADE");
    expect(nivelPersonagem2).toBe("INIMIZADE");
  });

  it("Deve descontar os pontos de energia em uma interação entre dois Cresims", () => {
    const personagemEnergia = { ...personagemBase, energia: 32 };
    const personagem2Energia = { ...personagem2Base, energia: 33 };

    const objInteracao = {
      id: 4,
      interacao: "Conversar",
      pontos: 2,
      energia: 2,
    };

    const [novoPersonagem, novoPersonagem2] = interacao(
      personagemEnergia,
      personagem2Energia,
      objInteracao
    );

    const energiaPersonagem = novoPersonagem.energia;
    const energiaPersonagem2 = novoPersonagem2.energia;

    const energiaEsperada = 30;
    const energiaEsperada2 = 31;

    expect(energiaPersonagem).toBe(energiaEsperada);
    expect(energiaPersonagem2).toBe(energiaEsperada2);
  });

});

describe('Testes de cobertura', () => {
  it('Deve impedir cresim de trabalhar se energia for menor que quatro', () => {

    const personagem = {
      id: 1,
      nome: "João",
      higiene: 28,
      energia: 2,
    };

    const trabalho = {
      id: 1,
      cargo: "Jogador de Dota",
    };

    expect(calcularioSalario(personagem, trabalho)).toBe("Energia insuficiente para iniciar o trabalho no momento");

  })
  it('Deve impedir cresim de trabalhar se higiene for menor que quatro', () => {
    const personagem = {
      id: 1,
      nome: "João",
      higiene: 2,
      energia: 20,
    };

    const trabalho = {
      id: 1,
      cargo: "Jogador de Dota",
    };

    expect(calcularioSalario(personagem, trabalho)).toBe("Energia insuficiente para iniciar o trabalho no momento");

  })
  it('Deve receber salario normal se energia for 10', async () => {
    jest.useFakeTimers();

    const personagem = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
      nivelTrabalho: "JUNIOR",
      pontosHabilidade: [
        { categoria: "PINTURA", pontos: 0 },
        { categoria: "GASTRONOMIA", pontos: 0 },
        { categoria: "JOGOS", pontos: 0 },
        { categoria: "MUSICA", pontos: 0 },
        { categoria: "JARDINAGEM", pontos: 0 }
      ]
    };

    const trabalho = {
      id: 1,
      cargo: "Jogador de Dota",
      categoria: "JOGOS",
      salario: [
        { "nivel": "JUNIOR", "valor": 160 },
        { "nivel": "PLENO", "valor": 250 },
        { "nivel": "SENIOR", "valor": 340 }
      ]
    };

    const trabalhar = iniciarTrabalho(personagem, trabalho);

    jest.advanceTimersByTime(20000);

    const cresimNovo = await trabalhar;

    expect(cresimNovo.cresceleons).toBe(1660);
  })
  it('Deve impedir cresim de trabalhar se energia for menor que 4', () => {
    const cresim = {
      nome: "Carlos",
      energia: 3,
      higiene: 10,
    };

    const item = { nome: "Mouse com led", categoria: "JOGOS", pontos: 3 };

    const cresimNovo = treinarHabilidade(cresim, item);

    expect(cresimNovo).toBe(`Energia insuficiente para treinar habilidade! Voce precisa de no minimo 4 pontos de energia para treinar habilidades`);
  })
  it('Retorna cresim sem alteracao se tempo dormido for pouco', async () => {
    jest.useFakeTimers();

    const cresim = {
      nome: "Carlos",
      energia: 3,
      higiene: 10,
    };

    const dorme = dormir(cresim, 10);

    jest.advanceTimersByTime(5000);

    const cresimNovo = await dorme;

    expect(cresimNovo).toEqual(cresim);
  })

  it('Retorna cresim sem alteracao se nao houver cheat correspondente', async () => {

    const cresim = {
      nome: "Carlos",
      energia: 3,
      higiene: 10,
    };

    const cresimNovo = await executarCheat(cresim, "oi");

    expect(cresimNovo).toEqual(cresim);
  })

  it('Retorna energia 32', async () => {

    const cresim = {
      nome: "Carlos",
      energia: 36,
      higiene: 10,
    };

    const cresimNovo = await executarCheat(cresim, "deitadonarede");

    expect(cresimNovo.energia).toEqual(32);
  })

  it('Impede interacao fora do disponivel', async () => {
    jest.useFakeTimers();

    const cresim1 = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 23,
      relacionamentos: {}
    };

    const cresim2 = {
      id: 2,
      nome: "Maria",
      aspiracao: "jogos",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 20,
      relacionamentos: {}
    }

    const interacao = {
      "id": 3,
      "interacao": "Abraçar romanticamente",
      "pontos": 2,
      "energia": 1
    }

    const interage = interagir(cresim1, cresim2, interacao);

    jest.advanceTimersByTime(4000);

    const personagensPosInteracao = await interage;

    expect(personagensPosInteracao).toEqual({ cresim1, cresim2 })
  })

  it('Retorna personagem igual se energia se um dos cresims for menor que 0', () => {

    const cresim1 = {
      id: 1,
      nome: "João",
      aspiracao: "pintura",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: -23,
      relacionamentos: {}
    };

    const cresim2 = {
      id: 2,
      nome: "Maria",
      aspiracao: "jogos",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 20,
      relacionamentos: {}
    }

    const intera = {
      "id": 3,
      "interacao": "Abraçar romanticamente",
      "pontos": 2,
      "energia": 1
    }

    const personagensPosInteracao = interacao(cresim1, cresim2, intera)

    expect(personagensPosInteracao).toEqual([cresim1, cresim2])
  })

  it('Retorna personagem corretamente', () => {
    const personagem = { "id": 1, "nome": "João", "aspiracao": "pintura", "cresceleons": 1500, "tempo": 3600000, "higiene": 28, "energia": 32, "relacionamento": [], "pontosHabilidade": [{ "categoria": "PINTURA", "pontos": 0 }, { "categoria": "GASTRONOMIA", "pontos": 0 }, { "categoria": "JOGOS", "pontos": 0 }, { "categoria": "MUSICA", "pontos": 0 }, { "categoria": "JARDINAGEM", "pontos": 0 }], "itens": [], "emojiAspiracao": "" };

    const retorno = getPersonagem(1);

    expect(retorno).toEqual(personagem);
  })

  it('executa funções', () =>{
    const cresim2 = {
      id: 2,
      nome: "Maria",
      aspiracao: "jogos",
      cresceleons: 1500,
      tempo: 3600000,
      higiene: 17,
      energia: 20,
      relacionamentos: {}
    }
    getTodosPersonagens();
    mostrarPersonagens();
    deletarPersonagem(2);
    atualizarPersonagens(cresim2);
  })
})

describe('Funções de comuns', () => {
  let personagem;

  beforeEach(() => {
    personagem = {
      tempo: 100,
      energia: 50,
      higiene: 30,
    };
  });

  it('Deve ajustar corretamente o tempo de vida', () => {
    expect(setTempoVida(personagem, 20)).toBe(80);
    expect(setTempoVida(personagem, 120)).toBe(0); // Não pode ser menor que 0
  });

  it('Deve ajustar corretamente a energia', () => {
    expect(setEnergia(personagem, 10)).toBe(40);
    expect(setEnergia(personagem, 60)).toBe(0); // Não pode ser menor que 0
  });

  it('Deve ajustar corretamente a energia com setEnergiaDois', () => {
    expect(setEnergiaDois(personagem, 10)).toBe(40);
    expect(setEnergiaDois(personagem, 48)).toBe(2); // Não pode ser menor que 2
  });


});

describe('getLevelInteracoes', () => {
  it('Deve retornar INIMIZADE para pontos menores que 0', () => {
    const result = getLevelInteracoes(-5);
    expect(result).toBe('INIMIZADE');
  });

  it('Deve retornar NEUTRO para pontos entre 0 e 9', () => {
    const result = getLevelInteracoes(5);
    expect(result).toBe('NEUTRO');
  });

  it('Deve retornar AMIZADE para pontos entre 10 e 24', () => {
    const result = getLevelInteracoes(15);
    expect(result).toBe('AMIZADE');
  });

  it('Deve retornar AMOR para pontos maiores ou iguais a 25', () => {
    const result = getLevelInteracoes(30);
    expect(result).toBe('AMOR');
  });
});


test('Obtém dados da API de itens de habilidades', async () => {
  const data = await itensHabilidadesApi();
  expect(data).toBeDefined();
});


test('Obtém dados da API de interações', async () => {
  const data = await interacoesApi();
  expect(data).toBeDefined();
});

test('Obtém dados da API de cheats', async () => {
  const data = await cheatsApi();
  expect(data).toBeDefined();
});

test('Obtém dados da API de empregos', async () => {
  const data = await empregosApi();
  expect(data).toBeDefined();
});

