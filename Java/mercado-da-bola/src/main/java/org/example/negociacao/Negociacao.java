package org.example.negociacao;

import org.example.clube.Clube;
import org.example.jogadores.Jogador;

public class Negociacao {

    public boolean negociar(Clube clube, Jogador jogador) {

        if(jogador.interesseMudarClube(clube)) {

            double precoJogador = jogador.valorCompra();
            double caixaClube = clube.getSaldoCaixa();

            double novoSaldo = caixaClube - precoJogador;

            if(novoSaldo >= 0) {

                jogador.transferirClube(clube);
                clube.setSaldoCaixa(novoSaldo);

                return true;

            }

        }

        return false;
    }

}
