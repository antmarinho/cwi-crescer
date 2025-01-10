package org.example;

public class Ninja {

    private String nome;
    private int chakra;
    private Jutsu jutsu;

    public Ninja(String nome, Jutsu jutsu) {

        this.nome = nome;
        this.chakra = 100;
        this.jutsu = jutsu;

    }

    public void atacar(Ninja inimigo) {

        inimigo.receberGolpe(this.jutsu.getDanoCausado());

        this.chakra = this.chakra - this.jutsu.getChakraGasto();

    }

    public void receberGolpe(int dano) {
        this.chakra = this.chakra - dano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getChakra() {
        return chakra;
    }

    public void setChakra(int chakra) {
        this.chakra = chakra;
    }

    public Jutsu getJutsu() {
        return jutsu;
    }

    public void setJutsu(Jutsu jutsu) {
        this.jutsu = jutsu;
    }


}
