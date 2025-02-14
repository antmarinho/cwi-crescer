export const dormir = async (cresim, tempoDormido) => {
    // Cria uma promisse para rodar tempo da acao
    // Se o tempo dormido não for maior que 5000ms não ira recuperar energia
        
    if (tempoDormido < 5000) return cresim;

    return new Promise((resolve) => {
        setTimeout(() => {
            // A cada 5000ms recupera energia
            let energiaRecuperada = Math.floor(tempoDormido / 5000) * 4;
            // Adiciona um bonus a cada 5000ms que dormir direto
            let bonus = Math.max(0, Math.floor((tempoDormido - 5000) / 5000)) * 2;
            // Soma a energia recuperada com o bonus, limitando seu valor total em 32
            let novaEnergia = Math.min(32, cresim.energia + energiaRecuperada + bonus);
            resolve({...cresim, energia: novaEnergia});
        }, tempoDormido);
    });
};