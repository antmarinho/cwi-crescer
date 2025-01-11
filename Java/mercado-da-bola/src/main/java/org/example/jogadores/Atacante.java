package org.example.jogadores;

import org.example.clube.Clube;

public class Atacante extends Jogador {

    private int gols;

    public Atacante(String nome, int idade, Clube clube, int reputacaoHistorica, Object apetiteFincanceiro, double preco, int gols) {

        super(nome, idade, clube, reputacaoHistorica, apetiteFincanceiro, preco);
        this.gols = gols;

    }

    @Override
    public boolean interesseMudarClube(Clube clube) {

        if(clube.getReputacaoHistorica() > this.getReputacaoHistorica())
            return true;
        else
            return false;

    }

    @Override
    public double valorCompra() {

        if(this.getIdade() > 30)
            return (super.valorCompra() * (1 + (0.01 * gols))) * 0.75;
        else
            return (super.valorCompra() * (1 + (0.01 * gols)));

    }

}
