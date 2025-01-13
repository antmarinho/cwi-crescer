package org.example.personagens;

import org.example.classes.Guerreiro;
import org.example.mapa.Mapa;
import org.example.racas.Humano;
import org.example.racas.Monstro;

public class Urukhai extends Personagem implements Monstro, Humano, Guerreiro {

    private String fala;
    private String grinhido;
    private String grupoPersonagem;

    public Urukhai() {

        this.grinhido = "Uuurrrrrr";
        this.fala = "Looks like meat's back on the menu boys!";
        this.grupoPersonagem = null;
        this.forca = 8;
        this.agilidade = 6;
        this.inteligencia = 3;
        this.constituicao = 45;

    }

    @Override
    public String grupo() {
        return this.grupoPersonagem;
    }

    @Override
    public String grunir() {
        return this.grinhido;
    }

    @Override
    public String falar() {
        return this.fala;
    }

    @Override
    public void atacar(Mapa mapa) {
        atacarGuerreiroSauron(mapa);
    }

    @Override
    public void movimentar(Mapa mapa) {
        movimentarGuerreiroSauron(mapa);
    }

    @Override
    public String toString(){
        return "U";
    }

    @Override
    public void envelhecer() {
        this.setConstituicao(this.getConstituicao() - 2);
    }

}
