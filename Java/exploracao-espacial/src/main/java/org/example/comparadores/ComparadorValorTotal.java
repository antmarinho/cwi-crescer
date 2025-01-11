package org.example.comparadores;

import org.example.planeta.Planeta;

import java.util.Comparator;

public class ComparadorValorTotal implements Comparator {


    @Override
    public int compare(Object o1, Object o2) {

        Planeta p1 = (Planeta) o1;
        Planeta p2 = (Planeta) o2;

        return p2.getValorTotal() - p1.getValorTotal();

    }
}