package org.example.planeta;

/*
    Água (valor: 180, peso: 10)
    Oxigênio (valor: 300, peso: 2)
    Silício (valor: 60, peso: 16)
    Ouro (valor: 120, peso: 25)
    Ferro (valor: 30, peso: 32)

                                    */

public class Recurso {

    private int valor;
    private int peso;

    public Recurso(int valor, int peso) {
        this.valor = valor;
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public int getPeso() {
        return peso;
    }

}
