package org.example.nave;

import org.example.comparadores.ComparadorValorPeso;
import org.example.comparadores.ComparadorValorTotal;
import org.example.planeta.Planeta;
import org.example.planeta.Recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Nave {

    private int posicao;
    private int combustivel;

    public Nave(int combustivel) {
        this.posicao = 0;
        this.combustivel = combustivel;
    }

    public List<Recurso> explorar(Planeta planeta) {

        List<Recurso> recursosNave = new ArrayList<>();

        int verificaDistanciaCombustivel = this.combustivel/3;

        if(planeta.getPosicao() > verificaDistanciaCombustivel) {

            setCombustivel(this.combustivel - (verificaDistanciaCombustivel * 3));
            setPosicao(verificaDistanciaCombustivel);

        } else if((verificaDistanciaCombustivel >= planeta.getPosicao()) && (verificaDistanciaCombustivel < (planeta.getPosicao() * 2))) {

            setCombustivel(this.combustivel - (verificaDistanciaCombustivel * 3));

            if(verificaDistanciaCombustivel == planeta.getPosicao()) {

                setPosicao(planeta.getPosicao());
                recursosNave.addAll(planeta.getRecursos());

            }
            else {

                setPosicao(planeta.getPosicao() - (verificaDistanciaCombustivel - planeta.getPosicao()));
                recursosNave.addAll(planeta.getRecursos());

            }

        } else {

            recursosNave.addAll(planeta.getRecursos());
            setPosicao(0);
            setCombustivel(this.combustivel - ((planeta.getPosicao() * 2 ) * 3));

        }

        return recursosNave;
    }

    public List<Recurso> explorar(List<Planeta> planetas) {

        List<Recurso> recursosExplorados = new ArrayList<>();

            int posicaoAtual = 0;

            for (Planeta planeta : planetas) {

                while (planeta.getPosicao() > posicaoAtual) {

                    if(getQuantidadeDeCombustivel() < 3) {

                        this.setPosicao(posicaoAtual);
                        break;

                    }

                    posicaoAtual = posicaoAtual + 1;

                    setCombustivel(getQuantidadeDeCombustivel() - 3);

                    if (planeta.getPosicao() == posicaoAtual) {

                        recursosExplorados.addAll(planeta.getRecursos());

                        setPosicao(posicaoAtual);

                        break;

                    }

                    setPosicao(posicaoAtual);

                }

                while(planeta.getPosicao() < posicaoAtual) {

                    if(getQuantidadeDeCombustivel() < 3) {

                        setPosicao(posicaoAtual);
                        break;

                    }

                    posicaoAtual = posicaoAtual - 1;

                    setCombustivel(getQuantidadeDeCombustivel() - 3);

                    if(planeta.getPosicao() == posicaoAtual){

                        recursosExplorados.addAll(planeta.getRecursos());

                        setPosicao(posicaoAtual);

                        break;

                    }

                    setPosicao(posicaoAtual);

                }

            }

            while(getQuantidadeDeCombustivel() > 2 && !(getPosicao() == 0)) {

                setPosicao(getPosicao() - 1);

                setCombustivel(getQuantidadeDeCombustivel()-3);

            }

            return recursosExplorados;

    }


    public List<Planeta> ordenar(List<Planeta> planeta, int tipo) {

        if(tipo == 1)
            Collections.sort(planeta, Comparator.comparing(Planeta::getPosicao));
        else if(tipo == 2)
            Collections.sort(planeta, new ComparadorValorTotal());
        else
            Collections.sort(planeta, new ComparadorValorPeso());

        return planeta;
    }

    public int getQuantidadeDeCombustivel() {
        return combustivel;
    }

    public int getPosicao() {
        return posicao;
    }

    private void setCombustivel(int combustivel) {
        this.combustivel = combustivel;
    }

    private void setPosicao(int posicao) {
        this.posicao = posicao;
    }

}
