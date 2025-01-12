package org.example.militares;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Militar {

    protected BigDecimal salario;
    protected LocalDate validadeLicencaAviao = null;
    protected LocalDate validadeLicencaCaminhao = null;
    protected LocalDate validadeLicencaHelicoptero = null;
    protected LocalDate validadeLicencaTanque = null;

    public Militar(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

    public LocalDate getValidadeLicencaAviao() {
        return this.validadeLicencaAviao;
    }

    public LocalDate getValidadeLicencaCaminhao() {
        return this.validadeLicencaCaminhao;
    }

    public LocalDate getValidadeLicencaHelicoptero() {
        return this.validadeLicencaHelicoptero;
    }

    public LocalDate getValidadeLicencaTanque() {
        return this.validadeLicencaTanque;
    }

}
