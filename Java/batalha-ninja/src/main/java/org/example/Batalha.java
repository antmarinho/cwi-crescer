package org.example;

public class Batalha {

    public Ninja lutar(Ninja nin1, Ninja nin2) {

        nin1.atacar(nin2);
        nin2.atacar(nin1);

        nin1.atacar(nin2);
        nin2.atacar(nin1);

        nin1.atacar(nin2);
        nin2.atacar(nin1);

        if(nin1.getNome().equalsIgnoreCase("itachi")) {
            return nin1;
        } else if (nin2.getNome().equalsIgnoreCase("itachi")) {
            return nin2;
        } else if ((nin1.getChakra() == nin2.getChakra()) || (nin1.getChakra() > nin2.getChakra())) {
            return nin1;
        } else {
            return nin2;
        }

    }

}
