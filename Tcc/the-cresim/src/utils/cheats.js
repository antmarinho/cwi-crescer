import { cheatsApi } from "../services/api/api.js"

export const executarCheat = async (personagem, input) => {

  //chama a api dos cheats
  const listaCheats = await cheatsApi();

  const code = input.toUpperCase();

  //procura o codigo digitado na lista dos cheats
  const cheat = listaCheats.find((che) => che.codigo == code);

  // se n tem nenhum cheats
  if (!cheat) 
    return personagem;

  // se tem
  switch (cheat.codigo) 
  {
    case "SORTENAVIDA":

      const salario = personagem.emprego.salario;

      // calculo do novo salario
      return {

        ...personagem,
        emprego: {

          ...personagem.emprego,
          salario: salario + (salario * cheat.valor) / 100,

        },

      };

    // regenera a energia  
    case "DEITADONAREDE":

      let novaEnergia = personagem.energia + 5;

      if (32 <= novaEnergia)
        novaEnergia = 32;
      
      return {

        ...personagem,
        energia: novaEnergia,

      };

    // aumenta a habilidade do personagem
    case "JUNIM":

      return {

        ...personagem,
        pontosHabilidade: personagem.pontosHabilidade + 5,

      };

    // mais vida pro personagem
    case "CAROLINAS":

      return {

        ...personagem,
        tempo: personagem.tempo + 100000,

      };

    // zera o tempo  
    case "SINUSITE":

      return {

        ...personagem,
        tempo: 0,

      };

  }

};
