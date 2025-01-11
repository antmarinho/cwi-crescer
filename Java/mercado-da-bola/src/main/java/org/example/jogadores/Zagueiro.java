package org.example.jogadores;

import org.example.clube.Clube;

public class Zagueiro extends Jogador{

    public Zagueiro(String nome, int idade, Clube clube, int reputacaoHistorica, Object apetiteFincanceiro, double preco) {

        super(nome, idade, clube, reputacaoHistorica, apetiteFincanceiro, preco);

    }

    @Override
    public double valorCompra() {

        if(this.getIdade() > 30)
            return super.valorCompra() * 0.8;
        else
            return super.valorCompra();

    }

}
