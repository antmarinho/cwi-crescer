package org.example;

import org.example.exceptions.SauronDominaOMundoException;
import org.example.mapa.Mapa;
import org.example.personagens.Personagem;

import java.util.Objects;
import java.util.stream.Collectors;

public class Simulador {

    Mapa mapa;

    public Simulador(Mapa mapa) {
        this.mapa = mapa;
    }

    public void simular() {

        while(true) {

            long count = mapa.mapa.stream().filter(n -> n != null && !Objects.equals(n.grupo(), null)).count();

            if(count == 0)
                throw new SauronDominaOMundoException("A humanidade sofre perante a tirania de Sauron.");

            Personagem personagemfim = mapa.buscarCasa(9);

            if(personagemfim != null) {

                if(!Objects.equals(personagemfim.grupo(), null))
                    break;


            }

            final String[] x = { null };

            mapa.mapa.stream().map(personagem -> {

                if(personagem != null) {

                    Personagem personagem1 = mapa.buscarCasa(9);

                    if(personagem1 != null) {

                        if(!Objects.equals(personagem1.grupo(), null))
                            return personagem;

                    }

                    if(!Objects.equals(personagem.toString(), x[0])) {

                        personagem.atacar(mapa);

                        mapa.verificarMortos();

                        personagem.movimentar(mapa);

                        x[0] = personagem.toString();

                        return personagem;

                    }

                }

                return personagem;

            }).collect(Collectors.toList());

        }

    }

}
