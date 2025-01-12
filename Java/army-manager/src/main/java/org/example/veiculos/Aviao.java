package org.example.veiculos;

import org.example.militares.Elite;
import org.example.militares.EspecialistaDoAr;
import org.example.militares.Militar;
import org.example.militares.PilotoAviao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Aviao extends Veiculo {

    public Aviao(PilotoAviao piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    public Aviao(EspecialistaDoAr piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    public Aviao(Elite piloto, List<Militar> tripulacao, double quilometragemPorLitro, BigDecimal precoPorLitroDoCombustivel) {
        super(piloto, tripulacao, quilometragemPorLitro, precoPorLitroDoCombustivel);
    }

    @Override
    public boolean tripulacaoValida() {

        if(this.piloto.getValidadeLicencaAviao().isAfter(LocalDate.now())) {

            long count = this.tripulacao.stream().filter(Objects::nonNull).count();

            if (count == 1)
                return true;

        }

        return false;

    }

}
