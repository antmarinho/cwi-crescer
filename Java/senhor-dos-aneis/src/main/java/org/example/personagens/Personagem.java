package org.example.personagens;

import org.example.classes.Arqueiro;
import org.example.classes.Guerreiro;
import org.example.classes.Mago;
import org.example.mapa.Mapa;

import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Personagem implements Arqueiro, Guerreiro, Mago {

    protected int forca;
    protected int agilidade;
    protected int inteligencia;
    protected int constituicao;
    protected String toString;
    protected String grupo;

    public Integer getForca() {
        return forca;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public Integer getInteligencia() {
        return inteligencia;
    }

    public Integer getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(Integer constituicao) {

        if(constituicao > 0 && constituicao <= getConstituicao())
            this.constituicao = constituicao;
        else if (constituicao <= 0)
            this.constituicao = 0;

    }

    public String toString() {
        return toString;
    }

    public String grupo() {
        return grupo;
    }

    public void atacarGuerreiroSociedade(Mapa mapa) {

        int i = mapa.buscarPosicao(this);

        if(mapa.buscarCasa(i + 1) != null) {

            Personagem personagem = mapa.buscarCasa(i + 1);

            if(Objects.equals(personagem.grupo(), null)) {

                int dano = personagem.getConstituicao() - 2 * getForca();
                personagem.setConstituicao(dano);

            }
        }
    }

    public void movimentarGuerreiroSociedade(Mapa mapa) {

        int i = mapa.buscarPosicao(this);

        if(mapa.buscarCasa(i + 1) == null) {

            mapa.mapa.set(i + 1, this);
            mapa.mapa.set(i, null);

        }
    }

    public void atacarMagoSociedade(Mapa mapa) {

        mapa.mapa.stream().map(personagem -> {

            if(personagem != null && (Objects.equals(personagem.grupo(), null))) {

                int i = personagem.getConstituicao() - getInteligencia();

                personagem.setConstituicao(i);

            }

            return personagem;

        }).collect(Collectors.toList());

    }

    public void movimentarMagoSociedade(Mapa mapa) {

        int i = mapa.buscarPosicao(this);

        long count = mapa.mapa.stream().filter(n -> n == null).count();

        if(count == 9) {

            mapa.mapa.set(i + 1, this);
            mapa.mapa.set(i, null);

        } else
            mapa.mapa.set(i, this);

    }

    public void atacarArqueiroSociedade(Mapa mapa) {

        int i = mapa.buscarPosicao(this);
        int n;

        for(n = 3; n > 0; --n) {

            while(i + n > 9) {
                n--;
            }

            if(mapa.buscarCasa(i + n)!=null) {

                Personagem personagem = mapa.buscarCasa(i + n);

                if(Objects.equals(personagem.grupo(), null)) {

                    int dano = personagem.getConstituicao() - n * getAgilidade();

                    personagem.setConstituicao(dano);

                    break;

                }
            }
        }
    }

    public void movimentarArqueiroSociedade(Mapa mapa) {

        int i = mapa.buscarPosicao(this);

        if(mapa.buscarCasa(i + 2) == null) {

            if(mapa.buscarCasa(i + 1) == null) {

                mapa.mapa.set(i + 2, this);
                mapa.mapa.set(i, null);

            }

        } else if(mapa.buscarCasa(i + 1) == null) {

            mapa.mapa.set(i + 1, this);
            mapa.mapa.set(i, null);

        }

    }

    public void atacarGuerreiroSauron(Mapa mapa) {

        int i = mapa.buscarPosicao(this);

        if(mapa.buscarCasa(i - 1) != null) {

            Personagem personagem = mapa.buscarCasa(i - 1);

            if (personagem.grupo() != null) {

                int dano = personagem.getConstituicao() - 2 * getForca();

                personagem.setConstituicao(dano);

            }
        }
    }

    public void movimentarGuerreiroSauron(Mapa mapa) {

        int i = mapa.buscarPosicao(this);

        if (mapa.buscarCasa(i - 1)==null) {

            mapa.mapa.set(i - 1, this);
            mapa.mapa.set(i, null);

        }

    }

}