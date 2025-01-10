import org.example.Batalha;
import org.example.Jutsu;
import org.example.Ninja;
import org.junit.Assert;
import org.junit.Test;

public class Teste {

    @Test
    public void deveAtualizarOChakraDoOponenteDeAcordoComODanoDoJutsoQuandoAtacar() {

        Jutsu jutsu1 = new Jutsu(4, 8);
        Ninja nin1 = new Ninja("Neji", jutsu1);

        Jutsu jutsu2 = new Jutsu(4, 5);
        Ninja nin2 = new Ninja("Sakura", jutsu2);

        int chakra = 92;

        nin1.atacar(nin2);

        Assert.assertEquals(chakra, nin2.getChakra());

    }

    @Test
    public void deveRetornarNinjaComJutsuMaisForteSeOsDoisGastamOMesmoChakra() {

        Jutsu jutsu1 = new Jutsu(4, 8);
        Ninja nin1 = new Ninja("Neji", jutsu1);

        Jutsu jutsu2 = new Jutsu(4, 5);
        Ninja nin2 = new Ninja("Sakura", jutsu2);

        Batalha batalha = new Batalha();

        Ninja vencedor = batalha.lutar(nin2, nin1);

        Assert.assertSame(nin1, vencedor);


    }

    @Test
    public void testeNinja1ComMaisChakra() {

        Jutsu jutsu1 = new Jutsu(3, 8);
        Ninja nin1 = new Ninja("Neji", jutsu1);

        Jutsu jutsu2 = new Jutsu(4, 5);
        Ninja nin2 = new Ninja("Sakura", jutsu2);

        Batalha batalha = new Batalha();

        Ninja vencedor = batalha.lutar(nin2, nin1);

        Assert.assertSame(nin1, vencedor);


    }

    @Test
    public void testeChakraGastoMaior5OuMenor0() {

        Jutsu jutsu1 = new Jutsu(5, 8);
        Jutsu jutsu2 = new Jutsu(-1, 8);

        int chakraEsperadoJustsu1 = 5;
        int chakraEsperadoJustsu2 = 0;

        Assert.assertEquals(chakraEsperadoJustsu1,jutsu1.getChakraGasto());
        Assert.assertEquals(chakraEsperadoJustsu2,jutsu2.getChakraGasto());

    }

    @Test
    public void testeDanoCausadoMaior10OuMenor0() {

        Jutsu jutsu1 = new Jutsu(5, 10);
        Jutsu jutsu2 = new Jutsu(5, -2);

        int danoJustsu1 = 10;
        int danoJustsu2 = 0;

        Assert.assertEquals(danoJustsu1,jutsu1.getDanoCausado());
        Assert.assertEquals(danoJustsu2,jutsu2.getDanoCausado());

    }

    @Test
    public void testSetGetNinja() {

        Jutsu jutsu1 = new Jutsu(3, 8);
        Ninja nin1 = new Ninja("Neji", jutsu1);

        String novoNome = "Naruto";
        int novoChakra = 90;

        nin1.setNome("Naruto");
        nin1.setChakra(90);

        Jutsu jut2 = new Jutsu(3, 8);
        Jutsu jut3 = new Jutsu(5, 10);

        Assert.assertEquals(novoNome,nin1.getNome());
        Assert.assertEquals(novoChakra,nin1.getChakra());
        Assert.assertEquals(jut2.getChakraGasto(),nin1.getJutsu().getChakraGasto());
        Assert.assertEquals(jut2.getDanoCausado(),nin1.getJutsu().getDanoCausado());

        nin1.setJutsu(jut3);

        Assert.assertSame(jut3,nin1.getJutsu());

    }

    @Test
    public void deveRetornarPrimeiroNinjaComoVencedorQuandoONomeForItachi() {

        Jutsu jutsu1 = new Jutsu(5, 10);
        Ninja nin1 = new Ninja("Itachi", jutsu1);

        Jutsu jutsu2 = new Jutsu(4, 5);
        Ninja nin2 = new Ninja("Sakura", jutsu2);

        Batalha batalha = new Batalha();

        Ninja vencedor = batalha.lutar(nin2, nin1);

        Assert.assertSame(nin1, vencedor);

    }

    @Test
    public void deveRetornarPrimeiroNinjaComoVencedorQuandoEmpatar() {

        Jutsu jutsu1 = new Jutsu(4, 5);
        Ninja nin1 = new Ninja("Neji", jutsu1);

        Jutsu jutsu2 = new Jutsu(4, 5);
        Ninja nin2 = new Ninja("Sakura", jutsu2);

        Batalha batalha = new Batalha();

        Ninja vencedor = batalha.lutar(nin1, nin2);

        Assert.assertSame(nin1, vencedor);

    }

    @Test
    public void deveRetornarSegundoNinjaComoVencedorQuandoONomeForItachi() {

        Jutsu jutsu2 = new Jutsu(5, 10);
        Ninja nin2 = new Ninja("Itachi", jutsu2);

        Jutsu jutsu1 = new Jutsu(4, 5);
        Ninja nin1 = new Ninja("Sakura", jutsu1);

        Batalha batalha = new Batalha();

        Ninja vencedor = batalha.lutar(nin2, nin1);

        Assert.assertSame(nin2, vencedor);

    }

}
