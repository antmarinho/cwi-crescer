package org.example.militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoAviao extends Militar {

    public PilotoAviao(BigDecimal salario, LocalDate validadeLicencaAviao) {

        super(salario);
        this.validadeLicencaAviao = validadeLicencaAviao;

    }

}
