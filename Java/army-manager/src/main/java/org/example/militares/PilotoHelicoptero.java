package org.example.militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoHelicoptero extends Militar {

    public PilotoHelicoptero(BigDecimal salario, LocalDate validadeLicencaHelicoptero) {

        super(salario);
        this.validadeLicencaHelicoptero = validadeLicencaHelicoptero;

    }

}