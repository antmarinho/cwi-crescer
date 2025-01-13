import org.example.dicionario.Dicionario;
import org.example.dicionario.TipoDicionario;
import org.example.exception.PalavraNaoEncontradaException;
import org.junit.Assert;
import org.junit.Test;

public class Testes {

    @Test
    public void deveTraduzirCarroELivroDoInglesParaPortuguesEDoPortuguesParaOIngles() {

        Dicionario dicionario = new Dicionario();

        dicionario.adicionarPalavra("cachorro", "dog", TipoDicionario.INGLES);
        dicionario.adicionarPalavra("gato", "cat", TipoDicionario.INGLES);

        dicionario.adicionarPalavra("table", "mesa", TipoDicionario.PORTUGUES);
        dicionario.adicionarPalavra("GAME", "jogo", TipoDicionario.PORTUGUES);

        Assert.assertEquals("dog", dicionario.traduzir("CACHORRO", TipoDicionario.INGLES));
        Assert.assertEquals("cat", dicionario.traduzir("GaTo", TipoDicionario.INGLES));

        Assert.assertEquals("jogo", dicionario.traduzir("game", TipoDicionario.PORTUGUES));
        Assert.assertEquals("mesa", dicionario.traduzir("Table", TipoDicionario.PORTUGUES));

    }

    @Test(expected = PalavraNaoEncontradaException.class)
    public void deveLancarExceptionQuandoUmaPalavraNaoForEncontrada() {

        Dicionario dicionario = new Dicionario();

        dicionario.adicionarPalavra("cachorro", "dog", TipoDicionario.INGLES);
        dicionario.adicionarPalavra("gato", "cat", TipoDicionario.INGLES);

        String traducao = dicionario.traduzir("bola", TipoDicionario.INGLES);

    }

}