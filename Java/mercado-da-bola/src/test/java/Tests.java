import org.example.Conservador;
import org.example.Indiferente;
import org.example.Mercenario;
import org.example.clube.Clube;
import org.example.jogadores.*;
import org.example.negociacao.Negociacao;
import org.junit.Assert;
import org.junit.Test;

public class Tests {

    // testes goleiro

    @Test
    public void testeClasseGoleiro() {

        Goleiro gol = new Goleiro("goleiro 1",20,null, 5,new Indiferente(), 100,5);
        Goleiro gol2 = new Goleiro("goleiro 2",31,null, 5,new Conservador(), 100,10);
        Goleiro gol3 = new Goleiro("goleiro 3",28,null, 5,new Mercenario(), 100,3);

        double valorEsperado1 = 120;
        double valorEsperado2 = 196;
        double valorEsperado3 = 201.6;

        Assert.assertEquals(valorEsperado1,gol.valorCompra(),0.01);
        Assert.assertEquals(valorEsperado2,gol2.valorCompra(),0.01);
        Assert.assertEquals(valorEsperado3,gol3.valorCompra(),0.01);

    }

    // testes atacante

    @Test
    public void testeClasseAtacante() {

        Clube clube = new Clube("clube1",8,500);
        Clube clube2 = new Clube("clube2",4,500);

        Atacante ataq = new Atacante("atacante",20,null, 5,new Indiferente(), 100,5);
        Atacante ataq2 = new Atacante("atacante 2",20,null, 5,new Conservador(), 100,0);
        Atacante ataq3 = new Atacante("atacante 3",31,null, 4,new Mercenario(), 100,8);

        double valorEsperado1 = 105;
        double valorEsperado2 = 140;
        double valorEsperado3 = 145.8;

        Assert.assertEquals(valorEsperado1,ataq.valorCompra(),0.01);
        Assert.assertEquals(valorEsperado2,ataq2.valorCompra(),0.01);
        Assert.assertEquals(valorEsperado3,ataq3.valorCompra(),0.01);

        Assert.assertTrue(ataq2.interesseMudarClube(clube));
        Assert.assertFalse(ataq2.interesseMudarClube(clube2));

    }

    // testes Lateral

    @Test
    public void testeLateral() {

        Lateral lat = new Lateral("lateral 1",20,null, 5,new Indiferente(), 100,5);
        Lateral lat2 = new Lateral("lateral 2",31,null, 5,new Conservador(), 100,10);

        double valorEsperado1 = 110;
        double valorEsperado2 = 117.6;

        Assert.assertEquals(valorEsperado1,lat.valorCompra(),0.01);
        Assert.assertEquals(valorEsperado2,lat2.valorCompra(),0.01);

    }

    // testes Meio campo

    @Test
    public void testeMeioCampo() {

        Clube clube = new Clube("clube1",3,500);
        Clube clube2 = new Clube("clube2",8,500);

        MeioCampo meio = new MeioCampo("meio 1",20 ,null, 5, new Indiferente(), 100);
        MeioCampo meio2 = new MeioCampo("meio 2",36 ,null, 6, new Mercenario(), 100);

        double valorEsperado1 = 100;
        double valorEsperado2 = 126;

        Assert.assertEquals(valorEsperado1,meio.valorCompra(),0.01);
        Assert.assertEquals(valorEsperado2,meio2.valorCompra(),0.01);

        Assert.assertTrue(meio.interesseMudarClube(clube));
        Assert.assertFalse(meio2.interesseMudarClube(clube2));


    }

    // testes Zagueiro

    @Test
    public void testeZagueiro() {

        Zagueiro zag = new Zagueiro("lateral 1",20,null, 5, new Indiferente(), 100);
        Zagueiro zag2 = new Zagueiro("lateral 2",31,null, 5, new Conservador(), 100);

        double valorEsperado1 = 100;
        double valorEsperado2 = 112;

        Assert.assertEquals(valorEsperado1,zag.valorCompra(),0.01);
        Assert.assertEquals(valorEsperado2,zag2.valorCompra(),0.01);

    }

    // testes Jogador

    @Test public void testeJogador() {

        Clube clube = new Clube("clube 1", 10, 5000);
        Clube clube2 = new Clube("clube 1", 0, 5000);
        Jogador jog = new Jogador("jogador 1", 20, null, 5, new Indiferente(), 100);

        Assert.assertEquals("Sem Clube",jog.nomeClube());

        jog.setClube(clube);
        Assert.assertSame(clube,jog.getClube());
        Assert.assertEquals("clube 1", jog.nomeClube());

        Assert.assertEquals(100, jog.getPreco(),0.01);

        jog.setPreco(200);
        Assert.assertEquals(200, jog.getPreco(),0.01);

        Mercenario mer = new Mercenario();

        jog.setApetiteFincanceiro(mer);
        Assert.assertSame(mer,jog.getApetiteFincanceiro());

        jog.setNome("jog 2");
        Assert.assertEquals("jog 2", jog.getNome());

        jog.setReputacaoHistorica(8);
        Assert.assertEquals(8, jog.getReputacaoHistorica());

        Assert.assertTrue(jog.interesseMudarClube(clube));
        Assert.assertFalse(jog.interesseMudarClube(clube2));

    }

    // testes clube

    @Test
    public void testesClube() {

        Clube clube = new Clube("clube 1", 10, 5000);

        Assert.assertEquals("clube 1",clube.getNome());

        clube.setNome("clube 2");
        Assert.assertEquals("clube 2",clube.getNome());

        clube.setReputacaoHistorica(2);
        Assert.assertEquals(2,clube.getReputacaoHistorica());

    }

    // testes obrigatorios

    @Test
    public void deveSerPossivelNegociarUmGoleiroComUmClubeQueTemSaldoEmCaixa() {

        Negociacao negociacao = new Negociacao();
        Clube clube = new Clube("clube 1", 10, 5000);
        Goleiro goleiro = new Goleiro("goleiro", 33, null, 8, new Indiferente(), 500, 12);

        boolean foiPossivelNegociar = negociacao.negociar(clube, goleiro);

        Assert.assertTrue(foiPossivelNegociar);


    }

    @Test
    public void naoDeveSerPossivelNegociarUmAtacanteComUmClubeQueTemReputacaoHistoricaMenorQueASua() {

        Negociacao negociacao = new Negociacao();
        Clube clube = new Clube("clube 1", 4, 20000);
        Atacante ataq = new Atacante("atacante", 33, null, 8, new Indiferente(), 500, 12);

        boolean foiPossivelNegociar = negociacao.negociar(clube, ataq);

        Assert.assertFalse(foiPossivelNegociar);

    }

    @Test
    public void naoDeveSerPossivelNegociarPorFaltaDeCaixaDisponivel() {

        Negociacao negociacao = new Negociacao();
        Clube clube = new Clube("clube 1", 7, 200);
        Goleiro goleiro = new Goleiro("goleiro", 33, null, 8, new Indiferente(), 500, 12);

        boolean foiPossivelNegociar = negociacao.negociar(clube, goleiro);

        Assert.assertFalse(foiPossivelNegociar);

    }

    @Test
    public void deveCalcularCorretamenteOPrecoDoMeioCampoComMenosDeTrintaAnos() {

        MeioCampo meio = new MeioCampo("meio",28,null,5, new Conservador(), 100);

        double precoEsperado = 140;

        Assert.assertEquals(precoEsperado,meio.valorCompra(),0.01);

    }

    @Test
    public void deveCalcularCorretamenteOPrecoDoMeioCampoComMaisDeTrintaAnos() {

        MeioCampo meio = new MeioCampo("meio",35,null,5, new Indiferente(), 100);

        double precoEsperado = 70;

        Assert.assertEquals(precoEsperado,meio.valorCompra(),0.01);

    }


}
