package org.example;

import org.example.veiculos.Veiculo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class SimulacaoFinanceira {

    public int distanciaViagem;
    public List<Veiculo> veiculosM;
    public int duracaoM;

    public SimulacaoFinanceira(int distanciaViagem, List<Veiculo> veiculosM, int duracaoM) {

        this.distanciaViagem = distanciaViagem;
        this.veiculosM = veiculosM;
        this.duracaoM = duracaoM;

    }

    public BigDecimal getDistanciaDaViagem() {
        return BigDecimal.valueOf(distanciaViagem);
    }

    public BigDecimal gastoComCombustivel() {

        BigDecimal combustivelTotal = BigDecimal.ZERO;

        BigDecimal bigDecimalStream1 = veiculosM.stream().map(veiculo -> combustivelTotal.add(getDistanciaDaViagem().divide(veiculo.getQuilometragemPorLitro().multiply(veiculo.getPrecoPorLitroDoCombustivel()),3, RoundingMode.CEILING))).reduce(BigDecimal.ZERO, BigDecimal::add);

        return bigDecimalStream1;

    }

    public BigDecimal getCustoTotalMissao() {

        BigDecimal custoTotal = BigDecimal.ZERO;

        BigDecimal finalCustoTotal = custoTotal;

        BigDecimal bigDecimalStream = veiculosM.stream().map(veiculo -> finalCustoTotal.add(veiculo.salarioTripulacao())).reduce(BigDecimal.ZERO, BigDecimal::add);

        custoTotal = bigDecimalStream.multiply(BigDecimal.valueOf(duracaoM));

        custoTotal = custoTotal.add(gastoComCombustivel());

        return custoTotal;

    }

    public boolean todasTripulacoesValidas() {

        this.veiculosM.stream().map(veiculo -> {

            if(veiculo.tripulacaoValida())
                return veiculo;
            else
                return false;

        }).collect(Collectors.toList());

        return true;

    }

}
