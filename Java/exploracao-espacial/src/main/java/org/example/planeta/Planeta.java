package org.example.planeta;

import java.util.List;

public class Planeta {

    private int posicao;
    private List<Recurso> recursos;
    private int valorTotal;
    private int valorPorPeso;

    public Planeta(int posicao, List<Recurso> recursos) {
        this.posicao = posicao;
        this.recursos = recursos;
    }

    public int getPosicao() {
        return posicao;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public int getValorTotal() {

        setValorTotal(recursos.stream().mapToInt(Recurso::getValor).sum());

        return this.valorTotal;
    }

    public int getValorPorPeso() {

        setValorPorPeso(recursos.stream().mapToInt(r -> r.getValor()/r.getPeso()).sum());

        return valorPorPeso;
    }

    private void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    private void setValorPorPeso(int valorPorPeso) {
        this.valorPorPeso = valorPorPeso;
    }

}
