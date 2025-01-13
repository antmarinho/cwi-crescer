package org.example.personagens;

import org.example.classes.Guerreiro;
import org.example.mapa.Mapa;
import org.example.racas.Monstro;

public class Orc extends Personagem implements Monstro, Guerreiro {

    private String grinhido;
    private String grupoPersonagem;

    public Orc() {

        this.inteligencia = 1;
        this.constituicao = 30;
        this.grinhido = "Arrrggghhh";
        this.grupoPersonagem = null;
        this.forca = 7;
        this.agilidade = 4;

    }

    @Override
    public void atacar(Mapa mapa) {
        atacarGuerreiroSauron(mapa);
    }

    @Override
    public String grupo() {
        return this.grupoPersonagem;
    }

    @Override
    public void movimentar(Mapa mapa) {
        movimentarGuerreiroSauron(mapa);
    }

    @Override
    public String toString() {
        return "O";
    }


    @Override
    public String grunir() {
        return this.grinhido;
    }

}
