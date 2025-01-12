package org.example.militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoTanque extends Militar {

    public PilotoTanque(BigDecimal salario, LocalDate validadeLicencaTanque) {

        super(salario);
        this.validadeLicencaTanque = validadeLicencaTanque;

    }

}