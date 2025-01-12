package org.example.veiculos;

import org.example.militares.Elite;
import org.example.militares.EspecialistaTerrestre;
import org.example.militares.Militar;
import org.example.militares.PilotoTanque;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Tanque extends Veiculo {

    public Tanque(PilotoTanque piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    public Tanque(EspecialistaTerrestre piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    public Tanque(Elite piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    @Override
    public boolean tripulacaoValida() {

        if (this.piloto.getValidadeLicencaTanque().isAfter(LocalDate.now())) {

            long count = this.tripulacao.stream().filter(Objects::nonNull).count();

            if (count == 3)
                return true;

        }

        return false;

    }
    
}
