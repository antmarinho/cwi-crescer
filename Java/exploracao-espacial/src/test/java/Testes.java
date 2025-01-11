import org.example.comparadores.ComparadorValorPeso;
import org.example.comparadores.ComparadorValorTotal;
import org.example.nave.Nave;
import org.example.planeta.Planeta;
import org.example.planeta.Recurso;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Testes {

    // testes obrigatorios

    @Test
    public void deveFicarADerivaQuandoFaltarCombustivelParaIrAteUmPlaneta() {

        Planeta marte  = new Planeta(4, new ArrayList<>());

        Nave starship = new Nave(8);

        List<Recurso> recursosNave = starship.explorar(marte);

        Assert.assertTrue(recursosNave.isEmpty());
        Assert.assertEquals(2,starship.getQuantidadeDeCombustivel());
        Assert.assertEquals(2,starship.getPosicao());

    }

    @Test
    public void deveTerValorTotalZeradoQuandoNaoExistirNenhumRecurso() {

        Planeta mercurio = new Planeta(1,new ArrayList<>());

        Assert.assertEquals(0,mercurio.getValorTotal());

    }

    @Test
    public void deveTerValorTotalQuandoExistirRecursosNoPlaneta() {

        Recurso agua = new Recurso(180,10);
        Recurso oxigenio = new Recurso(300,2);

        List<Recurso> recursoTerra = new ArrayList<>();

        recursoTerra.add(agua);
        recursoTerra.add(oxigenio);

        Planeta terra = new Planeta(1, recursoTerra);

        Assert.assertSame(recursoTerra,terra.getRecursos());
        Assert.assertEquals(480,terra.getValorTotal());

    }

    @Test
    public void deveTerValorPorPesoZeradoQuandoNaoExistirNenhumRecurso() {

        Planeta mercurio = new Planeta(1,new ArrayList<>());

        Assert.assertEquals(0,mercurio.getValorPorPeso());

    }

    @Test
    public void deveTerValorPorPesoQuandoExistirRecursosNoPlaneta() {

        Recurso agua = new Recurso(180,10);
        Recurso oxigenio = new Recurso(300,2);

        List<Recurso> recursoTerra = new ArrayList<>();

        recursoTerra.add(agua);
        recursoTerra.add(oxigenio);

        Planeta terra = new Planeta(1, recursoTerra);

        Assert.assertEquals(168,terra.getValorPorPeso());


    }

    // teste classes comparador

    @Test
    public void testeClassesComparador() {

        ComparadorValorPeso compVP = new ComparadorValorPeso();
        ComparadorValorTotal compVT = new ComparadorValorTotal();

        Recurso agua = new Recurso(180,10);
        Recurso oxigenio = new Recurso(300,2);

        List<Recurso> recursoTerra = new ArrayList<>();

        recursoTerra.add(agua);
        recursoTerra.add(oxigenio);

        Planeta terra = new Planeta(1, recursoTerra);

        Assert.assertEquals(0,compVP.compare(terra,terra));
        Assert.assertEquals(0,compVT.compare(terra,terra));

    }

    // teste explorar 1 planeta

    @Test
    public void testeExplorar1planeta() {

        List<Recurso> recurso = new ArrayList<>();

        Recurso agua = new Recurso(180,10);
        Recurso oxigenio = new Recurso(300,2);

        recurso.add(agua);
        recurso.add(oxigenio);

        Planeta marte  = new Planeta(3, new ArrayList<>(recurso));

        Nave starship = new Nave(9);

        List<Recurso> recursosNave = starship.explorar(marte);

        Assert.assertFalse(recursosNave.isEmpty());
        Assert.assertEquals(0,starship.getQuantidadeDeCombustivel());
        Assert.assertEquals(3,starship.getPosicao());

        Nave starship2 = new Nave(12);
        List<Recurso> recursosNave2 = starship2.explorar(marte);

        Assert.assertFalse(recursosNave2.isEmpty());
        Assert.assertEquals(0,starship2.getQuantidadeDeCombustivel());
        Assert.assertEquals(2,starship2.getPosicao());

        Nave starship1 = new Nave(18);
        List<Recurso> recursosNave1 = starship1.explorar(marte);

        Assert.assertFalse(recursosNave1.isEmpty());
        Assert.assertEquals(0,starship1.getQuantidadeDeCombustivel());
        Assert.assertEquals(0,starship1.getPosicao());

    }


    @Test
    public void testeExploracaoDePlanetas() {

        Nave artemis = new Nave(19);

        Recurso agua = new Recurso(180,10);
        Recurso oxigenio = new Recurso(300,2);
        Recurso ouro = new Recurso(120,25);

        List<Recurso> recursosPlaneta1 = new ArrayList<>();
        List<Recurso> recursosPlaneta2 = new ArrayList<>();

        recursosPlaneta1.add(ouro);
        recursosPlaneta1.add(agua);

        recursosPlaneta2.add(oxigenio);

        int valorEsperado = 600;

        Planeta marte = new Planeta(3, recursosPlaneta1);
        Planeta terra = new Planeta(1, recursosPlaneta2);

        List<Planeta> planetas = new ArrayList<>();

        planetas.add(marte);
        planetas.add(terra);

        List<Recurso> recursosNave = artemis.explorar(planetas);

        int valorExplorado = terra.getValorTotal() + marte.getValorTotal();

        Assert.assertEquals(valorEsperado,valorExplorado);
        Assert.assertEquals(0,artemis.getPosicao());
        Assert.assertEquals(1,artemis.getQuantidadeDeCombustivel());

    }

    // teste ordenadao

    @Test
    public void OrdenacaoPosicao() {

        int posicaoEsperada = 1;

        Recurso agua = new Recurso(180,10);

        List<Recurso> recursosPlaneta = new ArrayList<>();

        recursosPlaneta.add(agua);

        Nave starship = new Nave(17);

        Planeta marte = new Planeta(3, recursosPlaneta);
        Planeta terra = new Planeta(1, recursosPlaneta);
        Planeta plutao = new Planeta(2, recursosPlaneta);

        List<Planeta> planetas = new ArrayList<>();

        planetas.add(terra);
        planetas.add(plutao);
        planetas.add(marte);

        List<Planeta> ordenado = starship.ordenar(planetas,1);

        List<Recurso> recursos = starship.explorar(ordenado);

        int posicaoResultante = starship.getPosicao();

        Assert.assertEquals(posicaoEsperada, posicaoResultante);
    }

   @Test
    public void OrdenacaoValorTotal() {

        int posicaoEsperada = 1;

        Nave starship = new Nave(17);

        Recurso agua = new Recurso(180,10);
        Recurso silicio = new Recurso(60,16);
        Recurso ferro = new Recurso(30,32);
        Recurso oxigenio = new Recurso(300,2);

        List<Recurso> recursosPlaneta1 = new ArrayList<>();
        List<Recurso> recursosPlaneta2 = new ArrayList<>();
        List<Recurso> recursosPlaneta3 = new ArrayList<>();

        recursosPlaneta1.add(silicio);
        recursosPlaneta1.add(agua);

        recursosPlaneta2.add(oxigenio);

        recursosPlaneta3.add(ferro);

        Planeta marte = new Planeta(3, recursosPlaneta1);
        Planeta terra = new Planeta(1, recursosPlaneta2);
        Planeta plutao = new Planeta(2, recursosPlaneta3);

        List<Planeta> planetas = new ArrayList<>();

        planetas.add(terra);
        planetas.add(plutao);
        planetas.add(marte);

        List<Planeta> ordenado = starship.ordenar(planetas,2);

        List<Recurso> recursos = starship.explorar(ordenado);

        int posicaoResultante = starship.getPosicao();

        Assert.assertEquals(posicaoEsperada, posicaoResultante);

    }

    @Test
    public void OrdenacaoValorPorPeso() {

        int posicaoEsperada = 1;

        Nave starship = new Nave(17);

        Recurso agua = new Recurso(180,10);
        Recurso silicio = new Recurso(60,16);
        Recurso ferro = new Recurso(30,32);
        Recurso oxigenio = new Recurso(300,2);

        List<Recurso> recursosPlaneta1 = new ArrayList<>();
        List<Recurso> recursosPlaneta2 = new ArrayList<>();
        List<Recurso> recursosPlaneta3 = new ArrayList<>();

        recursosPlaneta1.add(silicio);
        recursosPlaneta1.add(agua);

        recursosPlaneta2.add(oxigenio);

        recursosPlaneta3.add(ferro);

        Planeta marte = new Planeta(3, recursosPlaneta1);
        Planeta terra = new Planeta(1, recursosPlaneta2);
        Planeta plutao = new Planeta(2, recursosPlaneta3);

        List<Planeta> planetas = new ArrayList<>();

        planetas.add(terra);
        planetas.add(plutao);
        planetas.add(marte);

        List<Planeta> ordenado = starship.ordenar(planetas,3);

        List<Recurso> recursos = starship.explorar(ordenado);

        int posicaoResultante = starship.getPosicao();

        Assert.assertEquals(posicaoEsperada, posicaoResultante);

    }

}
