package org.example.personagens;

import org.example.classes.Arqueiro;
import org.example.mapa.Mapa;
import org.example.racas.Monstro;

public class Goblim extends Personagem implements Monstro, Arqueiro {

    private String grupoPersonagem;
    private String grinhido;

    public Goblim() {

        this.constituicao = 20;
        this.forca = 3;
        this.agilidade = 6;
        this.grinhido = "Iiisshhhh";
        this.grupoPersonagem = null;
        this.inteligencia = 1;

    }

    @Override
    public String grunir() {
        return this.grinhido;
    }

    @Override
    public void movimentar(Mapa mapa) {

        int i = mapa.buscarPosicao(this);

        if(mapa.buscarCasa(i - 2) == null) {

            if(mapa.buscarCasa(i - 1) == null) {

                mapa.mapa.set(i - 2, this);
                mapa.mapa.set(i, null);

            }

        } else if(mapa.buscarCasa(i - 1) == null) {

            mapa.mapa.set(i - 1, this);
            mapa.mapa.set(i, null);

        }
    }

    @Override
    public String toString() {
        return "M";
    }

    @Override
    public void atacar(Mapa mapa) {

        int i = mapa.buscarPosicao(this);
        int n;

        for(n = 3; n > 0; --n) {

            while (i - n < 0) {

                n--;

            }

            if(mapa.buscarCasa(i - n) != null) {

                Personagem personagem = mapa.buscarCasa(i - n);

                if(personagem.grupo() != null) {

                    int dano = personagem.getConstituicao() - n * getAgilidade();

                    personagem.setConstituicao(dano);

                    break;

                }
            }
        }
    }

    @Override
    public String grupo() {
        return this.grupoPersonagem;
    }

}
