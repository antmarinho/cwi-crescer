package org.example.veiculos;

import org.example.militares.Militar;

import java.math.BigDecimal;
import java.util.List;

public abstract class Veiculo {

    protected Militar piloto;
    protected List<Militar> tripulacao;
    protected double quilometragemPorLitro;
    protected BigDecimal precoPorLitroDoCombustivel;

    public Veiculo(Militar piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {

        this.piloto = piloto;
        this.tripulacao = tripulacao;
        this.quilometragemPorLitro = quilometragemPorLitro;
        this.precoPorLitroDoCombustivel = precoPorLitroDoCombustivel;

    }

    public abstract boolean tripulacaoValida();

    public BigDecimal salarioTripulacao() {

        BigDecimal totalSalarios = BigDecimal.ZERO;

        BigDecimal bigDecimalStream = getTripulacao().stream().map(militar -> totalSalarios.add(militar.getSalario())).reduce(BigDecimal.ZERO, BigDecimal::add);

        return bigDecimalStream.add(piloto.getSalario());

    }

    public List<Militar> getTripulacao() {
        return tripulacao;
    }

    public BigDecimal getQuilometragemPorLitro() {
        return BigDecimal.valueOf(quilometragemPorLitro);
    }

    public BigDecimal getPrecoPorLitroDoCombustivel() {
        return precoPorLitroDoCombustivel;
    }

}
