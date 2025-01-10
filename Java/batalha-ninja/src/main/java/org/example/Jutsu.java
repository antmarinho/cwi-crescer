package org.example;

public class Jutsu {

    private int chakraGasto;
    private int danoCausado;

    public  Jutsu() {}

    public Jutsu(int chakraGasto, int danoCausado) {

        if(chakraGasto <= 5 && chakraGasto >= 0)
            this.chakraGasto = chakraGasto;

        if(danoCausado <= 10 && danoCausado >= 0)
            this.danoCausado = danoCausado;

    }

    public int getChakraGasto() {
        return chakraGasto;
    }

    public int getDanoCausado() {
        return danoCausado;
    }


}
