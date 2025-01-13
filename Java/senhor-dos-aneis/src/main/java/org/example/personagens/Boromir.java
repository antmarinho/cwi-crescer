package org.example.personagens;

import org.example.classes.Guerreiro;
import org.example.mapa.Mapa;
import org.example.racas.Humano;

public class Boromir extends Personagem implements Humano, Guerreiro {

    private String fala;
    private String grupoPersonagem;

    public Boromir() {

        this.forca = 7;
        this.agilidade = 6;
        this.inteligencia = 3;
        this.constituicao = 40;
        this.fala = "One does not simply walk into Mordor.";
        this.grupoPersonagem = "Faz parte da sociedade do anel.";

    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public String grupo() {
        return this.grupoPersonagem;
    }

    @Override
    public String falar() {
        return this.fala;
    }

    @Override
    public void envelhecer() {
        this.setConstituicao(this.getConstituicao() - 2);
    }

    @Override
    public void atacar(Mapa mapa) {
        atacarGuerreiroSociedade(mapa);
    }

    @Override
    public void movimentar(Mapa mapa) {
        movimentarGuerreiroSociedade(mapa);
    }

}