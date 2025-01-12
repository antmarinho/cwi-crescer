package org.example.militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Elite extends Militar {

    public Elite(BigDecimal salario, LocalDate validadeLicencaHelicoptero, LocalDate validadeLicencaAviao, LocalDate validadeLicencaCaminhao, LocalDate validadeLicencaTanque) {

        super(salario);
        this.validadeLicencaHelicoptero = validadeLicencaHelicoptero;
        this.validadeLicencaAviao = validadeLicencaAviao;
        this.validadeLicencaCaminhao = validadeLicencaCaminhao;
        this.validadeLicencaTanque = validadeLicencaTanque;

    }

}