package org.example.militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EspecialistaDoAr extends Militar {

    public EspecialistaDoAr(BigDecimal salario, LocalDate validadeLicencaHelicoptero, LocalDate validadeLicencaAviao) {

        super(salario);
        this.validadeLicencaHelicoptero = validadeLicencaHelicoptero;
        this.validadeLicencaAviao = validadeLicencaAviao;

    }

}