package org.example.personagens;

import org.example.classes.Guerreiro;
import org.example.mapa.Mapa;
import org.example.racas.Humano;

public class Aragorn extends Personagem implements Humano, Guerreiro {

    private String fala;
    private String grupoPersonagem;

    public Aragorn() {

        this.fala = "A day may come when the courage of men fails… but it is not THIS day.";
        this.inteligencia = 6;
        this.agilidade = 7;
        this.grupoPersonagem = "Faz parte da sociedade do anel.";
        this.forca = 10;
        this.constituicao = 60;

    }

    @Override
    public String falar() {
        return this.fala;
    }

    @Override
    public String grupo() {
        return this.grupoPersonagem;
    }

    @Override
    public void atacar(Mapa mapa) {
        atacarGuerreiroSociedade(mapa);
    }

    @Override
    public void envelhecer() {
        this.setConstituicao(this.getConstituicao() - 1);
    }

    @Override
    public void movimentar(Mapa mapa) {
        movimentarGuerreiroSociedade(mapa);
    }

    @Override
    public String toString() {
        return "A";
    }

}
