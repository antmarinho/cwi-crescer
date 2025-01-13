package org.example.personagens;

import org.example.classes.Guerreiro;
import org.example.mapa.Mapa;
import org.example.racas.Anao;

public class Gimli extends Personagem implements Anao, Guerreiro {

    private String fala;
    private String falaBebado;
    private Integer quantidadeDeBebida = 0;
    private String grupoPersonagem;

    public Gimli() {

        this.agilidade = 2;
        this.grupoPersonagem = "Faz parte da sociedade do anel.";
        this.inteligencia = 4;
        this.forca = 9;
        this.fala = "Let them come. There is one Dwarf yet in Moria who still draws breath.";
        this.constituicao = 60;
        this.falaBebado = "What did I say? He can't hold his liquor!";

    }

    @Override
    public void atacar(Mapa mapa) {
        atacarGuerreiroSociedade(mapa);
    }

    @Override
    public void movimentar(Mapa mapa) {
        movimentarGuerreiroSociedade(mapa);
    }

    @Override
    public String toString() {
        return "I";
    }

    @Override
    public Integer beber() {

        quantidadeDeBebida += 1;
        return quantidadeDeBebida;

    }

    @Override
    public String grupo() {
        return this.grupoPersonagem;
    }

    @Override
    public String falar() {

        if(this.beber() >= 3)
            return this.falaBebado;

        return this.fala;
        
    }

}
