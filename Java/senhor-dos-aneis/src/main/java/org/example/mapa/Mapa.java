package org.example.mapa;

import org.example.exceptions.PersonagemJaEstaNoMapaException;
import org.example.exceptions.PersonagemNaoEncontradoNoMapaException;
import org.example.exceptions.PosicaoOcupadaException;
import org.example.personagens.Personagem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa {

    public List<Personagem> mapa;

    public Mapa() {
        this.mapa = Arrays.asList(null, null, null, null, null, null, null, null, null, null);
    }

    public String exibir() {

        verificarMortos();

        List<String> mapsString = mapa.stream().map(x -> {

            if(x==null)
                return " ";
            else
                return x.toString();

        }).collect(Collectors.toList());

        return "|" + mapsString.get(0) + "|" + mapsString.get(1) + "|" + mapsString.get(2) + "|" + mapsString.get(3) + "|" + mapsString.get(4) + "|" + mapsString.get(5) + "|" + mapsString.get(6) + "|" + mapsString.get(7) + "|" + mapsString.get(8) + "|" + mapsString.get(9) + "|";

    }

    public void inserir(int posicao, Personagem personagem) {

        mapa.stream().filter(m ->  m != null && m.toString().equals(personagem.toString())).findFirst().ifPresent(x -> { throw new PersonagemJaEstaNoMapaException(); } );

        if(mapa.get(posicao) == null)
            mapa.set(posicao, personagem);
        else
            throw new PosicaoOcupadaException();
    }

    public int buscarPosicao(Personagem personagem) {

        if(mapa.stream().anyMatch(m -> m != null && m.toString().equals(personagem.toString())))
            return mapa.indexOf(personagem);
        else
            throw new PersonagemNaoEncontradoNoMapaException();
    }

    public Personagem buscarCasa(int posicao) {
        return mapa.get(posicao);
    }

    public void verificarMortos() {

        mapa.stream().filter(p -> p != null && p.getConstituicao().equals(0) && p.getConstituicao() != null).findAny().ifPresent(z -> {

            int i = mapa.indexOf(z);
            mapa.set(i, null);

        });

    }

}
