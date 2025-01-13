package org.example.dicionario;

public enum TipoDicionario {

    PORTUGUES("PORTUGUES"), INGLES("INGLES");

    private String idioma;

    TipoDicionario(String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }

}