package org.example.militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EspecialistaTerrestre extends Militar {

    public EspecialistaTerrestre(BigDecimal salario, LocalDate validadeLicencaTanque, LocalDate validadeLicencaCaminhao) {

        super(salario);
        this.validadeLicencaTanque = validadeLicencaTanque;
        this.validadeLicencaCaminhao = validadeLicencaCaminhao;

    }

}