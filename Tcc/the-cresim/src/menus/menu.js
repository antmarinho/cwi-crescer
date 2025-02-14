import { useQuestion } from "../../src/services/question/use-question.js";
import { clearBash } from "../utils/comuns.js";
import { setPersonagem, getPersonagem, getTodosPersonagens, deletarPersonagem, mostrarPersonagens, getPersonagemdead, atualizarPersonagens, validarEnergiaEHigiene, verificarCheat } from "../../src/services/crud/personagem.js";
import { getStorageDead, updateStorageDead } from "../../src/services/crud/storage.js"
import { dormir } from "../utils/dormir.js";
import { tomarBanho } from "../utils/tomarbanho.js";
import { iniciarTrabalho } from "../utils/trabalhar.js";
import { empregosApi, itensHabilidadesApi } from "../services/api/api.js";
import { comprarItemHabilidade, treinarHabilidade } from "../utils/habilidades.js";
import { executarCheat } from "../../src/utils/cheats.js";
import { calcularNivelHabilidade } from "../utils/habilidades.js";
import { getLevelInteracoes, interacao, listaDeInteracoes } from "../../src/utils/interacao.js";


export const startMenu = async () => {

  let mensagemAtencao = ``;
  let startMenu = true;

  const menucriarPersonagem = "\x1b[36m⭐ Criar Personagem\x1b[0m";
  const menuescolherPersonagem = "\x1b[32m👤 Escolher Personagem\x1b[0m";
  const menulistarPersonagens = "\x1b[33m📋 Listar Personagens\x1b[0m";
  const menudeletarPersonagem = "\x1b[34m🗑️  Deletar Personagem\x1b[0m";
  const menuvisitarCemiterio = "\x1b[35m🌟 Visitar Cemitério dos Personagens\x1b[0m";
  const menufinalizarJogo = "\x1b[31m❌ Finalizar jogo\x1b[0m";

  while (startMenu == true) {

    clearBash()

    const input = await useQuestion(`

        Bem Vindo

        ${theCresimsLogo()}
        ${await theCresimsLogoby()}
                                                                                          
        Escolha uma das opções: ${mensagemAtencao}

        1. ${menucriarPersonagem}

        2. ${menuescolherPersonagem}

        3. ${menulistarPersonagens}

        4. ${menudeletarPersonagem}

        5. ${menuvisitarCemiterio}

        X. ${menufinalizarJogo}

        Sua escolha: `
    );

    switch (input.toUpperCase()) {

      case "1":

        const nome = await useQuestion("Nome do personagem?");
        const aspiracao = await menuHabilidades("Aspiração do personagem:");

        return setPersonagem(nome, aspiracao);

      case "2":

        clearBash()

        mostrarPersonagens().forEach(mensagem => console.log(mensagem));

        const escolha = await useQuestion('SUA ESCOLHA:');

        return getPersonagem(escolha);

      case "3":

        clearBash()

        getTodosPersonagens().forEach(mensagem => console.log(mensagem));

        await useQuestion(`
          Pressione ENTER para continuar...`);

        break;

      case "4":

        clearBash()

        getTodosPersonagens().forEach(mensagem => console.log(mensagem));

        const escolhaDelete = await useQuestion(`Escolha o id do personagem que deseja deletar: `);

        if (escolhaDelete.toUpperCase() != "X")
          deletarPersonagem(escolhaDelete);

        break;

      case "5":

        clearBash()

        getTodosPersonagens(false).forEach(mensagem => console.log(mensagem));

        const escolhaPersonagemMorto = await useQuestion('SUA ESCOLHA:');

        await mortePersonagem(getPersonagemdead(escolhaPersonagemMorto));

        break;

      case "X":

        console.log("\nFoi Ótimo ter você aqui!! \nAte a proxima!!");

        return "exit";

      default:

        clearBash();

        mensagemAtencao = `Escolha uma opção válida`;

    }
  }
};

export const menuAcaoPersonagem = async (personagem) => {

  let mensagemAviso = ''
  let status;

  while (true) {

    if (personagem == null)
      return

    validarEnergiaEHigiene(personagem) //chamar validacao de energia e higiene

    //verificar tempo, caso chegue a 0 o personagem morre
    if (personagem.tempo <= 0) {

      await mortePersonagem(personagem)

      updateStorageDead(...getStorageDead(), personagem)
      deletarPersonagem(personagem.id)

      return

    }


    const input = await useQuestion(`${theCresimsLogo()}

    ${await infoDisplay(personagem)}
    ${mensagemAviso}
  
    Escolha uma opção para o(a) ${personagem.nome}

    1 - Trabalhar 💵

    2 - Treinar habilidade

    3 - Dormir ⌛️

    4 - Tomar banho 🛁

    5 - Comprar item 💵 

    6 - Interagir com outro personagem ❤️

    X - Voltar ao menu principal 🔙


    SUA ESCOLHA:`)


    switch (input.toUpperCase()) {

      case '1':
        //trabalhar
        if (personagem.energia <= 4) {

          mensagemAviso = `
      - Opção ${input} escolhida
      \x1b[33m !!! O personagem precisa de no mínimo 5 de energia para trabalhar !!! \x1b[0m
              `;

          break;
        }

        clearBash();

        mensagemAviso = `
      - Opção ${input} escolhida
              `;

        //chama o trabalho

        let respostaTrabalho;

        if(personagem.emprego){
            respostaTrabalho = await iniciarTrabalho(personagem, personagem.emprego);
        }else {
          const empregoEscolhido = await menuHabilidades(`Escolha a área de trabalho de ${personagem.nome}`);
          console.log(empregoEscolhido)
          const empregosAPI = await empregosApi();
          const emprego = empregosAPI.find((e) => {
            return e.categoria === empregoEscolhido;
          })
          personagem = {...personagem, emprego: emprego}
          respostaTrabalho = await iniciarTrabalho(personagem, emprego);
        }

        respostaTrabalho.nome == undefined ? console.log(respostaTrabalho) : personagem = respostaTrabalho;


        break;

      case '2':
        //treinar habilidade
        mensagemAviso = `
        - Opção ${input} escolhida
        `;

        //vai chamar o treino

        let respostaTreino;

        if(personagem.itens.length > 0){   
          const itensHabApi = await itensHabilidadesApi();     
          const itensNumero = Math.floor(Math.random() * personagem.itens.length);
          respostaTreino = await treinarHabilidade(personagem, personagem.itens[itensNumero], itensHabApi);
        }else {
        mensagemAviso = `
        - ${personagem.nome} ainda não possui item dosponíveis para treino,
        compre um antes de treinar.
        `;
        }

        respostaTreino.nome == undefined ? console.log(respostaTreino) : personagem = respostaTreino;

        break;

      case '3':

        //dormir

        if (personagem.energia >= 32) {

          personagem.energia = 32;
          mensagemAviso = `
        - Opção ${input} escolhida
        O personagem está com a energia completa
        `;
          break;

        }

        clearBash();

        const tempoDormido = await useQuestion("Quanto tempo ira dormir?");
        personagem = await dormir(personagem, tempoDormido);

        break;

      case '4':
        //tomar banho

        if (personagem.higiene >= 28) {

          personagem.higiene = 28;

          mensagemAviso = `
            - Opção ${input} escolhida
            O personagem está completamente limpo
            `;

        }

        if (personagem.cresceleons < 10) {

          mensagemAviso = `
            - Opção ${input} escolhida
            O personagem não tem 10 Cresceleons
            `;

        }

        clearBash();

        personagem = await tomarBanho(personagem);

        break;

      case '5':

        //comprar item
        clearBash();

        mensagemAviso = `
    - Opção ${input} escolhida
            `;

        // chamar o comprar item

        const opcao = await menuHabilidades(`Escolha a área do item a ser comprado: `);

        let itensApi = await itensHabilidadesApi();
        itensApi = itensApi[opcao];

        const inputItens = await useQuestion(`
          Escolha o item a ser comprado: 
          1. Nome: ${itensApi[0].nome}. Valor: ${itensApi[0].preco}
          2. Nome: ${itensApi[1].nome}. Valor: ${itensApi[1].preco}
          3. Nome: ${itensApi[2].nome}. Valor: ${itensApi[2].preco}
          X. Voltar
        `)
        
        let respostaItem;

        switch(inputItens) {
          case '1':
            respostaItem = comprarItemHabilidade(itensApi[0], personagem);
            break;

          case '2':
            respostaItem = comprarItemHabilidade(itensApi[1], personagem);
            break;

          case '3':
            respostaItem = comprarItemHabilidade(itensApi[2], personagem);
            break;

          case "x":
            return "x";

          case "X":
            return "X";

          default:
            console.log("### Escolha uma opção válida ###");
        }

        respostaItem.nome == undefined ? console.log(respostaItem) : personagem = respostaItem;

        break;

      case '6':

      clearBash();
      [personagem, status] = await menuInteracao(personagem);

      mensagemAviso = `
- Opção ${input} escolhida
\x1b[Interação entre usuarios realizado com sucesso\x1b[0m`;
      if (!status) {
          mensagemAviso = `
- Opção ${input} escolhida
\x1b[33m Interação não realizada \x1b[0m`;
      }


        break;

      case 'X':

        clearBash();

        //voltar menu iniciar
        return startMenu()

        // OPÇÃO INVALIDA e Cheat
        default:

          personagem = await executarCheat(personagem, input);

      mensagemAviso = `
      - Opção ${input} escolhida
      Opção Invalida! Escolha uma opção válida !!!
      `;

      if (verificarCheat(input)) {

        mensagemAviso = `
      Cheat aplicado com sucesso
      `;
      }

      clearBash();
      break;

    }

    atualizarPersonagens(personagem);

  }

};


const menuHabilidades = async (text) => {

  let displayMenuHabilidades = true;

  const Gastronomia = "\x1b[36m 🍔 Gastronomia \x1b[0m";
  const Pintura = "\x1b[33m 🎨 Pintura \x1b[0m";
  const Jogos = "\x1b[32m 🎲 Jogos \x1b[0m";
  const Jardinagem = "\x1b[35m 🪴 Jardinagem \x1b[0m";
  const Musica = "\x1b[34m 🎼 Música \x1b[0m";

  while (displayMenuHabilidades == true) {

    clearBash();

    const input = await useQuestion(`

      ${text}

      1.  ${Gastronomia}

      2.  ${Pintura}

      3.  ${Jogos}

      4.  ${Jardinagem}

      5.  ${Musica}

      X.  Voltar ao menu principal

      Sua escolha:`);

      clearBash();

    switch (input) {

      case "1":
        return "GASTRONOMIA";

      case "2":
        return "PINTURA";

      case "3":
        return "JOGOS";

      case "4":
        return "JARDINAGEM";

      case "5":
        return "MUSICA";

      case "x":
        return "x";

      case "X":
        return "X";

      default:
        console.log("### Escolha uma opção válida ###");

    }

  }
  
};

const getListaItens = async (personagem) => {
  return `${personagem.itens
      .map(h => `${h.nome}`)
      .join(` `)}`;

}

const infoDisplay = async (personagem) => {
  let emprego = "Desempregado"
  let salario = "Ainda sem salario"

  if (personagem.emprego) {

      emprego = personagem.emprego.cargo;
      const habilidadeObjeto = personagem.pontosHabilidade.find(habilidade => habilidade.categoria === personagem.emprego.categoria);
      const salarioObj = personagem.emprego.salario.find(s => s.nivel === calcularNivelHabilidade(habilidadeObjeto.pontos));
      salario = salarioObj.valor;
  }

  return `Meu nome é ${personagem.nome}

  Tempo de jogo: ${personagem.tempo}
  Energia: ${personagem.energia}/32
  Higiene: ${personagem.higiene}/28
  Cresceleons: ${personagem.cresceleons}                                                              
  Pontos de habilidades: 
  ${personagem.pontosHabilidade
          .map(h => `| ${h.categoria}: ${h.pontos}`)
          .join(` `)
      }
  Emprego: ${emprego}
  Salario: ${salario}
  Itens: ${await getListaItens(personagem)}`

}

const menuInteracao = async (personagem) => {

  let menuInteractionDisplay = true;

  while ((menuInteractionDisplay == true)) {

    clearBash();

    mostrarPersonagens().forEach(mensagem => console.log(mensagem));

    const escolha = await useQuestion('SUA ESCOLHA:');

    if (escolha == 'X' || escolha == 'x' || escolha == personagem.id)
      return [personagem, false];
    
    const personagemInteracao =  getPersonagem(  escolha);

    if (personagemInteracao == personagem) 
      return [personagem, false];

    try {

      if (personagemInteracao.id != personagem.id) {

        const novoUsuario = criarRelacao(personagem, personagemInteracao);
        const segundoUsuario = criarRelacao(personagemInteracao, personagem);

        return await selecionarInteracao(novoUsuario, segundoUsuario);

      } else {

        console.log("Informe um id de personagem diferente do seu");
      }
    } catch {

      console.log("Informe um id valido");
    }
  }
};

const selecionarInteracao = async (personagem, personagem2) => {

  let selecionarInteracaoNaTela = true;

    console.log(`
    ${theCresimsLogo()}
    Escolha uma das opções de interação abaixo:`);

    const pontos = pontosDeInteracao(personagem, personagem2.id);
    const level = getLevelInteracoes(pontos); // esse vem de interacao.js
    const lista = await listaDeInteracoes(level); // esse vem de interacao.js

    while (true) {

      await mostrarInteracoes(lista);

      const input = await useQuestion(`
      Seu level de relacionamento 
      com "${personagem2.nome}" é "${level}" com ${pontos} pontos

      Id da interação escolhida: `);

      try {

        const objInteracao = lista[input - 1];

        const [novoPersonagem, novoPersonagem2] = await interacao( //isso vem de interacao.js
          personagem,
          personagem2,
          objInteracao
        );

        if (novoPersonagem.energy < 0 || novoPersonagem2.energy < 0) {

          clearBash();

          console.log(
            `
          ${await theCresimsLogo()}
          Pontos de energia insuficiente para realizar a interação`
          );

          await useQuestion(`Pressione ENTER para continuar...`);

          return [personagem, false];

        }

        await atualizarPersonagens([novoPersonagem2]);

        clearBash();
       
        console.log(
          `
        ${theCresimsLogo()}

        Interação "${objInteracao.interacao}" realizada com sucesso
        `
        );

        await useQuestion(`Pressione ENTER para continuar...`);

        return [novoPersonagem, true];
      } catch {

        console.log("Adicione um id de interação possivel");
      }
  }
};

const criarRelacao = (personagem, personagem2) => {

  const relacao = getRelacao(personagem, personagem2.id);

  if (!relacao) {

      personagem.relacionamento.push({
      id: personagem2.id,
      nome: personagem2.nome,
      level: 0,

    });

  }

  return personagem;

};

const getRelacao = (personagem, personagemID) => {

  const listaRelacao = personagem.relacionamento;

  return listaRelacao.find((perso) => perso.id == personagemID);

};

const pontosDeInteracao = (personagem, personagemID) => {

  const relacao = getRelacao(personagem, personagemID);

  return relacao.level;

};

const mostrarInteracoes = async (list) => {

  let cont = 1;

  clearBash();

  console.log(`
${await theCresimsLogo()}

Escolha uma das Interações a seguir:
  `);

  for (const obj of list) {

    console.log(
      `${cont} - ${obj.interacao} (pontos: ${obj.pontos}, energia: ${obj.energia})`
    );

    cont++;

  }

  console.log("");
  
};

export const mortePersonagem = async (personagem) => {

  console.clear();

  console.log(`

                     ||   ||
                     ||   ||
                     ||   ||
          _ _ _ _ _ _||   ||_ _ _ _ _ _
          
          Aqui jaz ${personagem.nome}... 
          _ _ _ _ _       _ _ _ _ _ _ _
                     ||   ||
                     ||   ||        
                     ||   ||    
                     ||   ||   
                     ||   ||   
                     ||   ||    
                     ||   ||    
                     

`);

await new Promise((resolve) => setTimeout(resolve, 1000));
  return personagem;
};

export const theCresimsLogo =  () => {

  return `THE CRESIMS
  `;
  
};

export const theCresimsLogoby = async () => {

  return `Grupo 11`;

};