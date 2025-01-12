package org.example.veiculos;

import org.example.militares.Elite;
import org.example.militares.EspecialistaTerrestre;
import org.example.militares.Militar;
import org.example.militares.PilotoCaminhao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Caminhao extends Veiculo {

    public Caminhao(PilotoCaminhao piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    public Caminhao(EspecialistaTerrestre piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    public Caminhao(Elite piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    @Override
    public boolean tripulacaoValida() {

        if(this.piloto.getValidadeLicencaCaminhao().isAfter(LocalDate.now())) {

            long count = this.tripulacao.stream().filter(Objects::nonNull).count();

            if (count >= 5 && count <= 30)
                return  true;

        }

        return false;

    }

}
