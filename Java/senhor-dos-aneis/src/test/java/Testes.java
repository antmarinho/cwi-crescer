import org.example.Simulador;
import org.example.exceptions.PersonagemJaEstaNoMapaException;
import org.example.exceptions.PersonagemNaoEncontradoNoMapaException;
import org.example.exceptions.PosicaoOcupadaException;
import org.example.exceptions.SauronDominaOMundoException;
import org.example.mapa.Mapa;
import org.example.personagens.*;
import org.junit.Assert;
import org.junit.Test;

public class Testes {
    @Test
    public void teste() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        String inicio =  "|A|B|I|L|G| |O|M|S|U|";

        String fraseAragorn = "A day may come when the courage of men fails… but it is not THIS day.";
        int vidaAragorn = 59;

        String fraseBoromir = "One does not simply walk into Mordor.";
        int vidaBoromir = 38;

        String fraseGandalf = "A Wizard is never late, nor is he early. He arrives precisely when he means to.";
        String fraseGimli = "Let them come. There is one Dwarf yet in Moria who still draws breath.";
        String fraseGimliBebado = "What did I say? He can't hold his liquor!";
        String fraseGoblim = "Iiisshhhh";
        String fraseLegolas = "They're taking the Hobbits to Isengard!";
        String fraseLegolasElfico = "I amar prestar aen, han mathon ne nem, han mathon ne chae, a han noston ned.";
        String fraseOrc = "Arrrggghhh";
        String fraseSaruman = "Against the power of Mordor there can be no victory.";
        String fraseUrukhai = "Looks like meat's back on the menu boys!";
        String grunirUrukhai = "Uuurrrrrr";

        int vidaUrakai = 43;

        Aragorn aragorn = new Aragorn();
        Boromir boromir = new Boromir();
        Gimli gimli = new Gimli();
        Legolas legolas = new Legolas();
        Gandalf gandalf = new Gandalf();
        Orc orc = new Orc();
        Goblim goblim = new Goblim();
        Saruman saruman = new Saruman();
        Urukhai urukhai = new Urukhai();
        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.inserir(1, boromir);
        mapa.inserir(2, gimli);
        mapa.inserir(3, legolas);
        mapa.inserir(4, gandalf);
        mapa.inserir(6, orc);
        mapa.inserir(7, goblim);
        mapa.inserir(8, saruman);
        mapa.inserir(9, urukhai);

        gimli.beber();
        gimli.beber();
        gimli.beber();

        aragorn.envelhecer();
        boromir.envelhecer();
        urukhai.envelhecer();
        gandalf.ressucitar();
        saruman.ressucitar();

        Assert.assertEquals(inicio, mapa.exibir());
        Assert.assertEquals(vidaAragorn, aragorn.getConstituicao(), 0.01);
        Assert.assertEquals(vidaBoromir, boromir.getConstituicao(), 0.01);
        Assert.assertEquals(vidaUrakai, urukhai.getConstituicao(), 0.01);
        Assert.assertEquals(fraseAragorn, aragorn.falar());
        Assert.assertEquals(fraseBoromir, boromir.falar());
        Assert.assertEquals(fraseGandalf, gandalf.falar());
        Assert.assertEquals(fraseGimliBebado, gimli.falar());
        Assert.assertEquals(fraseGoblim, goblim.grunir());
        Assert.assertEquals(fraseLegolas, legolas.falar());
        Assert.assertEquals(fraseLegolasElfico, legolas.falarElfico());
        Assert.assertEquals(fraseOrc, orc.grunir());
        Assert.assertEquals(fraseSaruman, saruman.falar());
        Assert.assertEquals(fraseUrukhai, urukhai.falar());
        Assert.assertEquals(grunirUrukhai, urukhai.grunir());

    }

    @Test(expected = PersonagemJaEstaNoMapaException.class)
    public void gandalfTeste() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        String inicio =  "|A|G| | | | | |O| |M|";
        String resultadoEsperado = "|A|G| | | | | | | | |";

        Aragorn aragorn = new Aragorn();
        Gandalf gandalf = new Gandalf();

        Orc orc = new Orc();
        Goblim goblim = new Goblim();

        Mapa mapa = new Mapa();

        mapa.inserir(0, gandalf);
        mapa.inserir(1, gandalf);
        mapa.inserir(7, orc);
        mapa.inserir(9, goblim);

        gandalf.atacar(mapa);
        gandalf.atacar(mapa);
        gandalf.atacar(mapa);
        gandalf.movimentar(mapa);

        Assert.assertEquals(resultadoEsperado, mapa.exibir());

    }

    @Test(expected = SauronDominaOMundoException.class)
    public void sarumanTeste() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        String inicio =  "|G| | | | | | | | |S|";

        String resultadoEsperado = "| | | | | | | | |S| |";

        Saruman saruman = new Saruman();
        Aragorn aragorn = new Aragorn();

        Mapa mapa = new Mapa();
        Simulador simulador = new Simulador(mapa);

        mapa.inserir(2, aragorn);
        mapa.inserir(9, saruman);

        simulador.simular();

        Assert.assertEquals(resultadoEsperado, mapa.exibir());

    }

    @Test(expected = PosicaoOcupadaException.class)
    public void aragornTeste() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        String inicio =  "|A| | |S| | | | | | |";
        String resultadoEsperado = "| | |A|G| | | | | | |";

        Aragorn aragorn = new Aragorn();
        Saruman saruman = new Saruman();
        Gandalf gandalf = new Gandalf();

        Mapa mapa = new Mapa();

        mapa.inserir(0, aragorn);
        mapa.inserir(3, gandalf);
        mapa.inserir(3, saruman);

        aragorn.movimentar(mapa);
        aragorn.movimentar(mapa);
        aragorn.atacar(mapa);
        aragorn.atacar(mapa);
        aragorn.atacar(mapa);
        aragorn.atacar(mapa);

        System.out.println(mapa.exibir());
        Assert.assertEquals(resultadoEsperado, mapa.exibir());

    }

    @Test(expected = PersonagemNaoEncontradoNoMapaException.class)
    public void legolasTeste() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        String inicio =  "| |L|O|S| | | | | | |";
        String resultadoEsperado = "| |L|O|S| | | | | | |";

        Legolas legolas = new Legolas();
        Gandalf gandalf = new Gandalf();

        Saruman saruman = new Saruman();
        Orc orc = new Orc();

        Mapa mapa = new Mapa();

        mapa.inserir(1, legolas);
        mapa.inserir(2, orc);
        mapa.inserir(3, saruman);

        mapa.buscarPosicao(gandalf);

        Assert.assertEquals(resultadoEsperado, mapa.exibir());

    }

    @Test
    public void deveVencerSociedadeQuandoAragornELegolasBatalharemContraOrcEGoblim() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        String resultadoEsperado = "| | | | |A| | | | |L|";

        Aragorn aragorn = new Aragorn();
        Legolas legolas = new Legolas();

        Orc orc = new Orc();
        Goblim goblim = new Goblim();

        Mapa mapa = new Mapa();

        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, aragorn);
        mapa.inserir(1, legolas);
        mapa.inserir(7, orc);
        mapa.inserir(9, goblim);

        simulador.simular();

        Assert.assertEquals(resultadoEsperado, mapa.exibir());

    }

    @Test(expected = SauronDominaOMundoException.class)
    public void deveLancarSauronDominaOMundoExceptionQuandoInimigosDerrotaremMembrosDaSociedade() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        Aragorn aragorn = new Aragorn();

        Gimli gimli = new Gimli();
        Urukhai urukhai = new Urukhai();
        Orc orc = new Orc();
        Goblim goblim = new Goblim();

        Mapa mapa = new Mapa();

        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, aragorn);
        mapa.inserir(2, gimli);
        mapa.inserir(7, urukhai);
        mapa.inserir(8, orc);
        mapa.inserir(9, goblim);

        simulador.simular();

    }

    @Test
    public void deveVencerSociedadeQuandoGandalfBatalharSozinhoContraSaruman() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        String resultadoEsperado = "| | | | | | | | | |G|";

        Gandalf gandalf = new Gandalf();
        Saruman saruman = new Saruman();

        Mapa mapa = new Mapa();

        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, gandalf);
        mapa.inserir(9, saruman);

        simulador.simular();

        Assert.assertEquals(resultadoEsperado, mapa.exibir());

    }

    @Test(expected = SauronDominaOMundoException.class)
    public void deveLancarSauronDominaOMundoExceptionQuandoLegolasBatalharSozinhoContraOrcEUrukhai() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        Legolas legolas = new Legolas();

        Orc orc = new Orc();
        Urukhai urukhai = new Urukhai();

        Mapa mapa = new Mapa();

        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, legolas);
        mapa.inserir(8, urukhai);
        mapa.inserir(9, orc);

        simulador.simular();

    }

    @Test(expected = SauronDominaOMundoException.class)
    public void deveLancarSauronDominaOMundoExceptionQuandoBoromirBatalharSozinhoContraUrukhai() throws PersonagemJaEstaNoMapaException, PosicaoOcupadaException, SauronDominaOMundoException, PersonagemNaoEncontradoNoMapaException {

        Boromir boromir = new Boromir();
        Urukhai urukhai = new Urukhai();

        Mapa mapa = new Mapa();

        Simulador simulador = new Simulador(mapa);

        mapa.inserir(0, boromir);
        mapa.inserir(9, urukhai);

        simulador.simular();

    }

}
