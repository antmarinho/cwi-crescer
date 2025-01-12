import org.example.SimulacaoFinanceira;
import org.example.militares.*;
import org.example.veiculos.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Testes {

    @Test
    public void deveCalcularOCustoTotalDaMissaoCorretamente() {

        List<Veiculo> veiculos = new ArrayList<>();

        veiculos.add(criarAviao());
        veiculos.add(criarAviao1());
        veiculos.add(criarTanque());
        veiculos.add(criarTanque1());
        veiculos.add(criarHelicoptero());
        veiculos.add(criarCaminhao());
        veiculos.add(criarCaminhao1());

        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(1010, veiculos, 7);

        BigDecimal custoTotal = simulacao.getCustoTotalMissao();

        Assert.assertEquals(BigDecimal.valueOf(683914.116), custoTotal);
        Assert.assertTrue(simulacao.todasTripulacoesValidas());

    }

    @Test
    public void deveCalcularOCustoTotalComCombustivelCorretamente() {

        List<Veiculo> veiculos = new ArrayList<>();

        veiculos.add(criarAviao());
        veiculos.add(criarTanque());

        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(500, veiculos, 2);

        BigDecimal custoTotal = simulacao.gastoComCombustivel();

        Assert.assertEquals(BigDecimal.valueOf(976.191), custoTotal);

    }

    @Test
    public void deveCalcularOCustoToralComSalariosCorretamente() {

        List<Veiculo> veiculos = new ArrayList<>();

        veiculos.add(criarAviao());
        veiculos.add(criarCaminhao());
        veiculos.add(criarHelicoptero());

        SimulacaoFinanceira simulacao = new SimulacaoFinanceira(1450, veiculos, 3);

        BigDecimal custoTotal = veiculos.get(2).salarioTripulacao();

        Assert.assertEquals(BigDecimal.valueOf(39500), custoTotal);

    }

    private Tanque criarTanque() {

        Elite piloto = new Elite(BigDecimal.valueOf(2500), LocalDate.now().plusDays(10), LocalDate.now().plusDays(15), LocalDate.now().plusDays(20), LocalDate.now().plusDays(50));

        List<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(1500), LocalDate.now().minusYears(1)));
        tripulacao.add(new Militar(BigDecimal.valueOf(500)));
        tripulacao.add(new Militar(BigDecimal.valueOf(500)));

        return new Tanque(piloto, tripulacao, 0.3, BigDecimal.valueOf(3.5));

    }

    private Tanque criarTanque1() {

        EspecialistaTerrestre piloto = new EspecialistaTerrestre(BigDecimal.valueOf(2000), LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));

        List<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(1500), LocalDate.now().minusYears(2)));
        tripulacao.add(new Militar(BigDecimal.valueOf(500)));
        tripulacao.add(new Militar(BigDecimal.valueOf(500)));

        return new Tanque(piloto, tripulacao, 0.3, BigDecimal.valueOf(3.5));
    }

    private Aviao criarAviao() {

        EspecialistaDoAr piloto = new EspecialistaDoAr(BigDecimal.valueOf(7000), LocalDate.now().plusDays(-20), LocalDate.now().plusDays(20));

        List<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new Militar(BigDecimal.valueOf(2500)));

        return new Aviao(piloto, tripulacao, 0.1, BigDecimal.valueOf(10));

    }

    private Aviao criarAviao1() {

        PilotoAviao piloto = new PilotoAviao(BigDecimal.valueOf(8000), LocalDate.now().plusDays(40));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        return new Aviao(piloto, tripulacao, 0.1, BigDecimal.valueOf(10));
    }

    private Caminhao criarCaminhao() {

        EspecialistaTerrestre piloto = new EspecialistaTerrestre(BigDecimal.valueOf(3000), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20));

        List<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoCaminhao(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoAviao(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));

        return new Caminhao(piloto, tripulacao, 0.32, BigDecimal.valueOf(3.5));

    }

    private Caminhao criarCaminhao1() {

        PilotoCaminhao piloto = new PilotoCaminhao(BigDecimal.valueOf(3000), LocalDate.now().plusDays(20));

        ArrayList<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoCaminhao(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoAviao(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoHelicoptero(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new EspecialistaDoAr(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1), LocalDate.now().minusYears(1)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new Elite(BigDecimal.valueOf(3000), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20)));

        return new Caminhao(piloto, tripulacao, 0.4, BigDecimal.valueOf(3));

    }

    private Helicoptero criarHelicoptero() {

        Elite piloto = new Elite(BigDecimal.valueOf(3000), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20));

        List<Militar> tripulacao = new ArrayList<>();

        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoCaminhao(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoAviao(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoHelicoptero(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new EspecialistaDoAr(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1), LocalDate.now().minusYears(1)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new Elite(BigDecimal.valueOf(3000), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20)));
        tripulacao.add(new PilotoTanque(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoCaminhao(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoAviao(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new PilotoHelicoptero(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1)));
        tripulacao.add(new EspecialistaDoAr(BigDecimal.valueOf(2500), LocalDate.now().minusYears(1), LocalDate.now().minusYears(1)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new Militar(BigDecimal.valueOf(600)));
        tripulacao.add(new Elite(BigDecimal.valueOf(3000), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20), LocalDate.now().plusDays(20)));

        return new Helicoptero(piloto, tripulacao, 0.22, BigDecimal.valueOf(3.46));

    }

}