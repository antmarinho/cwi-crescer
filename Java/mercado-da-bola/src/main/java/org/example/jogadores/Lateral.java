package org.example.jogadores;

import org.example.clube.Clube;

public class Lateral extends Jogador{

    private int cruzamento;

    public Lateral(String nome, int idade, Clube clube, int reputacaoHistorica, Object apetiteFincanceiro, double preco, int cruzamento) {

        super(nome, idade, clube, reputacaoHistorica, apetiteFincanceiro, preco);
        this.cruzamento = cruzamento;

    }

    @Override
    public double valorCompra() {

        if(this.getIdade() > 28)
            return (super.valorCompra() * (1 + (0.02 * cruzamento))) * 0.7;
        else
            return (super.valorCompra() * (1 + (0.02 * cruzamento)));

    }

}
