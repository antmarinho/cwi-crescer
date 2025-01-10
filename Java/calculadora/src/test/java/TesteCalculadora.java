import org.example.Calculadora;
import org.junit.Assert;
import org.junit.Test;

public class TesteCalculadora {

    Calculadora calc = new Calculadora();

    @Test
    public void deveSomarCorretamenteQuandoOsValoresForemInteiros() {

        double a = 4;
        double b = 2;
        double result = 6;

        Assert.assertEquals(result, calc.soma(a,b), 0.01);

    }

    @Test
    public void deveDividirCorretamenteQuandoNumerosForemInteiros() {

        double a = 4;
        double b = 2;
        double result = 2;

        Assert.assertEquals(result, calc.div(a,b), 0.01);

    }

    @Test
    public void deveMultiplicarCorretamenteQuandoNumerosForemInteiros() {

        double a = 4;
        double b = 2;
        double result = 8;

        Assert.assertEquals(result, calc.mult(a,b), 0.01);

    }

    @Test
    public void deveDividirCorretamenteQuandoNumerosPossuemPontosFlutuantes() {

        double a = 5;
        double b = 2;
        double result = 2.5;

        Assert.assertEquals(result, calc.div(a,b), 0.01);

    }

    @Test
    public void deveSubtrairCorretamenteQuandoForemInteiros() {

        double a = 5;
        double b = 2;
        double result = 3;

        Assert.assertEquals(result, calc.sub(a,b), 0.01);

    }

    @Test
    public void testeRaiz() {

        double a = 25;
        double result = 5;

        Assert.assertEquals(result, calc.raiz(a), 0.01);

    }

    @Test
    public void testeExpo() {

        double a = 2;
        double b = 6;
        double result = 64;

        Assert.assertEquals(result, calc.expo(a,b), 0.01);

    }

    @Test
    public void testeBhaskaraDeltaNegativo() {

        double a = 4;
        double b = -4;
        double c = 2;
        double result = 1;

        Assert.assertEquals(result, calc.bhaskara(a,b,c), 0.01);

    }

    @Test
    public void testeBhaskaraDeltaZero() {

        double a = 1;
        double b = -6;
        double c = 9;
        double result = 6;

        Assert.assertEquals(result, calc.bhaskara(a,b,c), 0.01);

    }

    @Test
    public void testeBhaskaraDeltaPositivo() {

        double a = 1;
        double b = 3;
        double c = -4;
        double result = -3;

        Assert.assertEquals(result, calc.bhaskara(a,b,c), 0.01);

    }

}
