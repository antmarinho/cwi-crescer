package org.example.personagens;

import org.example.classes.Mago;
import org.example.mapa.Mapa;
import org.example.racas.Maia;

import java.util.Objects;
import java.util.stream.Collectors;

public class Saruman extends Personagem implements Maia, Mago {

    private String fala;
    private String grupoPersonagem;

    public Saruman() {

        this.inteligencia = 9;
        this.constituicao = 70;
        this.forca = 2;
        this.agilidade = 2;
        this.fala = "Against the power of Mordor there can be no victory.";
        this.grupoPersonagem = null;

    }

    @Override
    public String grupo() {
        return this.grupoPersonagem;
    }

    @Override
    public String toString() {
        return "S";
    }

    @Override
    public void atacar(Mapa mapa) {

        mapa.mapa.stream().map(p -> {

            if(p != null && p.grupo() != null) {

                int i = p.getConstituicao() - getInteligencia();
                p.setConstituicao(i);

                return p;

            }

            return p;

        }).collect(Collectors.toList());
    }

    @Override
    public void movimentar(Mapa mapa) {

        int i = mapa.buscarPosicao(this);

        long count = mapa.mapa.stream().filter(Objects::isNull).count();

        if (count == 9) {

            mapa.mapa.set(i - 1, this);
            mapa.mapa.set(i, null);

        } else
            mapa.mapa.set(i, this);

    }

    @Override
    public Object ressucitar() {
        return null;
    }

    @Override
    public String falar() {
        return this.fala;
    }
}
