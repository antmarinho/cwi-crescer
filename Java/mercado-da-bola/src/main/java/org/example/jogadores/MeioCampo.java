package org.example.jogadores;

import org.example.clube.Clube;

public class MeioCampo extends Jogador{

    public MeioCampo(String nome, int idade, Clube clube, int reputacaoHistorica, Object apetiteFincanceiro, double preco) {
        super(nome, idade, clube, reputacaoHistorica, apetiteFincanceiro, preco);
    }

    @Override
    public boolean interesseMudarClube(Clube clube) {

        if((clube.getReputacaoHistorica() - this.getReputacaoHistorica()) <= -2)
            return true;

        return false;

    }

    @Override
    public double valorCompra() {

        if(this.getIdade() > 30)
            return super.valorCompra() * 0.7;
        else
            return super.valorCompra();


    }

}
