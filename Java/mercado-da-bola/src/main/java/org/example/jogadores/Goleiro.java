package org.example.jogadores;

import org.example.clube.Clube;

public class Goleiro extends Jogador{

    private int penaltisDef;

    public Goleiro(String nome, int idade, Clube clube, int reputacaoHistorica, Object apetiteFincanceiro, double preco, int penaltisDef) {

        super(nome, idade, clube, reputacaoHistorica, apetiteFincanceiro, preco);
        this.penaltisDef = penaltisDef;

    }

    @Override
    public double valorCompra() {

        return super.valorCompra() * (1 + (0.04 * penaltisDef));

    }

}
