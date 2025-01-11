package org.example.clube;

public class Clube {

    private String nome;
    private int reputacaoHistorica;
    private double saldoCaixa;

    public Clube(String nome, int reputacaoHistorica, double saldoCaixa) {

        this.nome = nome;
        this.reputacaoHistorica = reputacaoHistorica;
        this.saldoCaixa = saldoCaixa;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getReputacaoHistorica() {
        return reputacaoHistorica;
    }

    public void setReputacaoHistorica(int reputacaoHistorica) {
        this.reputacaoHistorica = reputacaoHistorica;
    }

    public double getSaldoCaixa() {
        return saldoCaixa;
    }

    public void setSaldoCaixa(double saldoCaixa) {
        this.saldoCaixa = saldoCaixa;
    }
}
