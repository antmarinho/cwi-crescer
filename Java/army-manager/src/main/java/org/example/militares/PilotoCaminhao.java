package org.example.militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PilotoCaminhao extends Militar {

    public PilotoCaminhao(BigDecimal salario, LocalDate validadeLicencaCaminhao) {

        super(salario);
        this.validadeLicencaCaminhao = validadeLicencaCaminhao;

    }

}