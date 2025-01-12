package org.example.veiculos;

import org.example.militares.Elite;
import org.example.militares.EspecialistaDoAr;
import org.example.militares.Militar;
import org.example.militares.PilotoHelicoptero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Helicoptero extends Veiculo {

    public Helicoptero(PilotoHelicoptero piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    public Helicoptero(EspecialistaDoAr piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    public Helicoptero(Elite piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    @Override
    public boolean tripulacaoValida() {

        if(this.piloto.getValidadeLicencaHelicoptero().isAfter(LocalDate.now())) {

            long count = this.tripulacao.stream().filter(Objects::nonNull).count();

            if (count <= 10)
                return true;

        }

        return false;

    }

}