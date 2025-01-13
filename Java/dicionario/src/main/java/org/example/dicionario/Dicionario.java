package org.example.dicionario;

import org.example.exception.PalavraNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class Dicionario {

    private String palavra;
    private String traducao;
    private TipoDicionario tipoDicionario;
    private List<Dicionario> palavras = new ArrayList<>();

    public Dicionario() {}

    private Dicionario(String palavra, String traducao, TipoDicionario tipoDicionario) {

        this.palavra = palavra;
        this.traducao = traducao;
        this.tipoDicionario = tipoDicionario;

    }

    public void adicionarPalavra(String palavra, String traducao, TipoDicionario tipoDicionario) {

        Dicionario dicionario = new Dicionario(palavra.toLowerCase(), traducao, tipoDicionario);

        palavras.add(dicionario);

    }

    public String traduzir(String palavra, TipoDicionario tipoDicionario) {

        Dicionario palavraTraduzida = palavras.stream().filter(p -> p.getPalavra().equalsIgnoreCase(palavra) && p.getTipoDicionario().getIdioma().equals(tipoDicionario.getIdioma())).findFirst().orElseThrow(() -> new PalavraNaoEncontradaException("Palavra nao existe"));

        return palavraTraduzida.getTraducao();

    }

    private String getPalavra() {
        return palavra;
    }

    private String getTraducao() {
        return traducao;
    }

    private TipoDicionario getTipoDicionario() {
        return tipoDicionario;
    }

}