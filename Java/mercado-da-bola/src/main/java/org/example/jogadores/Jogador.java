package org.example.jogadores;

import org.example.Conservador;
import org.example.Indiferente;
import org.example.clube.Clube;

public class Jogador {

    private String nome;
    private int idade;
    private Clube clube;
    private int reputacaoHistorica;
    private Object apetiteFincanceiro;
    private double preco;

    public Jogador(String nome, int idade, Clube clube, int reputacaoHistorica, Object apetiteFincanceiro, double preco) {

        this.nome = nome;
        this.idade = idade;
        this.clube = clube;
        this.reputacaoHistorica = reputacaoHistorica;
        this.apetiteFincanceiro = apetiteFincanceiro;
        this.preco = preco;

    }

    public String nomeClube() {

        if(this.clube != null)
            return this.clube.getNome();
        else
            return "Sem Clube";

    }

    public void setReputacaoHistorica(int reputacaoHistorica) {

        if(reputacaoHistorica >= 0 && reputacaoHistorica <= 10)
            this.reputacaoHistorica = reputacaoHistorica;

    }

    public boolean interesseMudarClube(Clube clube) {

        if(clube.getReputacaoHistorica() >= 1)
            return true;

        return false;

    }

    public double valorCompra() {

        if(apetiteFincanceiro instanceof Indiferente)
            return this.preco;
        else if(apetiteFincanceiro instanceof Conservador) {
            return this.preco * 1.4;
        }
        else
            return this.preco * 1.8;

    }

    public void transferirClube(Clube clube) {
        setClube(clube);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public int getReputacaoHistorica() {
        return reputacaoHistorica;
    }

    public Object getApetiteFincanceiro() {
        return apetiteFincanceiro;
    }

    public void setApetiteFincanceiro(Object apetiteFincanceiro) {
        this.apetiteFincanceiro = apetiteFincanceiro;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
