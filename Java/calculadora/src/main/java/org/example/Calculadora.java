package org.example;

public class Calculadora {

    public double soma(double x, double y) {
        return  x + y;
    }

    public double sub(double x, double y) {
        return  x - y;
    }

    public double mult(double x, double y) {
        return  x * y;
    }

    public double div(double x, double y) {
        return  x / y;
    }

    public double raiz(double x) {
        return Math.sqrt(x);
    }

    public double expo(double x, double y) {
        return Math.pow(x,y);
    }

    public double bhaskara(double a, double b, double c) {

        double r1,r2;
        double i;

        double delta = (expo(b,2) - (4 * a * c));

        if(delta == 0) {

            r1 = (-b / (2 * a));

            return 2 * r1;

        } else if (delta > 0) {

            r1 = (-b + raiz(delta)) / (2 * a);

            r2 = (-b - raiz(delta)) / (2 * a);

            return r1 + r2;

        } else {

            r1 = (-b / (2 * a));

            i = raiz(delta * -1) / (2 * a);

            return 2 * r1;

            // parte imaginaria vai cancelar na soma das raizes

        }

    }

}
