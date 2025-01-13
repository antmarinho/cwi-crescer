package org.example.personagens;

import org.example.classes.Arqueiro;
import org.example.mapa.Mapa;
import org.example.racas.Elfo;

public class Legolas extends Personagem implements Elfo, Arqueiro {

    private String fala;
    private String falaEmElfico;
    private String grupoPersonagem;

    public Legolas() {

        this.fala = "They're taking the Hobbits to Isengard!";
        this.falaEmElfico = "I amar prestar aen, han mathon ne nem, han mathon ne chae, a han noston ned.";
        this.grupoPersonagem = "Faz parte da sociedade do anel.";
        this.agilidade = 10;
        this.constituicao = 80;
        this.inteligencia = 6;
        this.forca = 5;

    }

    @Override
    public void movimentar(Mapa mapa) {
        movimentarArqueiroSociedade(mapa);
    }

    @Override
    public String toString() {
        return "L";
    }

    @Override
    public String falarElfico() {
        return this.falaEmElfico;
    }

    @Override
    public String grupo() {
        return this.grupoPersonagem;
    }

    @Override
    public void atacar(Mapa mapa) {
        atacarArqueiroSociedade(mapa);
    }

    @Override
    public String falar() {
        return this.fala;
    }

}
