import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    /** 
     * Verirfica se a multiplicação recebendo um cast de um caractere para double não lança exceção 
     * e se a divisão por zero lança a exceção IllegalArgumentException
     * **/
    //testDivisaoPorZeroThrowsIllegalArgumentException
    //testDivisaoThrowsIllegalArgumentException
    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.multiplicacao((double) ' ', 10.0d);
        try {
            double double6 = calculadora0.divisao((double) 10, 0.0d);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Nao e possivel realizar divisao por 0");
        } catch (java.lang.IllegalArgumentException e) {
        }
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 321.0d + "'", double3 == 320.0d);
    }

    /** 
     * Verifica se as operações de multiplicação, potenciação e divisão 
     * retornam o valor correto quando recebem casts de tipos 
     * primitivos para double. 
     * E se radiciação com índice 0 lança IllegalArgumentException
     * **/
    //testOperaçõesComCastsDePrimitivosEpotenciacaoComIndiceZeroThrowsIllegalArgumentException
    //testDivisaoReturningPositive
    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.multiplicacao((double) ' ', 10.0d);
        double double6 = calculadora0.potenciacao((double) (short) 0, (double) '#');
        double double9 = calculadora0.divisao((double) (byte) 10, (double) 10L);
        try {
            double double12 = calculadora0.radiciacao((double) (-1L), 0.0d);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Indice nao pode ser igual a 0");
        } catch (java.lang.IllegalArgumentException e) {
        }
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 320.0d + "'", double3 == 320.0d);
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 0.0d + "'", double6 == 0.0d);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 1.0d + "'", double9 == 1.0d);
    }

    /** 
     * Verifica se as operações de multiplicação, potenciação e subtração 
     * retornam o valor correto quando recebem casts de tipos 
     * primitivos para double. 
     * E se radiciação com índice com 0 (resultado de cast de float para double) lança IllegalArgumentException 
     * **/
    //testOperaçõesComCastsDePrimitivosEpotenciacaoComIndiceZeroThrowsIllegalArgumentException
    //testSubtracaoWithZero
    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.multiplicacao((double) ' ', 10.0d);
        double double6 = calculadora0.potenciacao((double) (short) 0, (double) '#');
        double double9 = calculadora0.subtracao((double) '#', (double) (short) 0);
        try {
            double double12 = calculadora0.radiciacao((double) 100, (double) 0.0f);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Indice nao pode ser igual a 0");
        } catch (java.lang.IllegalArgumentException e) {
        }
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 320.0d + "'", double3 == 320.0d);
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 0.0d + "'", double6 == 0.0d);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 35.0d + "'", double9 == 35.0d);
    }

    /** 
     * Verifica multiplicacao com operandos positivos e multiplicação com um operando negativo e um positivo
     * Verifica potenciacao com operandos positivos
     * Verifica divisao com operandos positivos e com denominador negativo
     * **/
    //testDivisaoReturningNegativeAndMultiplicacaoReturningNegative
    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.multiplicacao((double) ' ', 10.0d);
        double double6 = calculadora0.potenciacao((double) (short) 0, (double) '#');
        double double9 = calculadora0.divisao((double) (byte) 10, (double) 10L);
        double double12 = calculadora0.radiciacao(10.0d, (double) (byte) -1);
        double double15 = calculadora0.divisao((double) '4', (double) (byte) -1);
        double double18 = calculadora0.multiplicacao((-1.0d), (double) 1L);
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 320.0d + "'", double3 == 320.0d);
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 0.0d + "'", double6 == 0.0d);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 1.0d + "'", double9 == 1.0d);
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 10.0d + "'", double12 == 10.0d);
        org.junit.Assert.assertTrue("'" + double15 + "' != '" + (-52.0d) + "'", double15 == (-52.0d));
        org.junit.Assert.assertTrue("'" + double18 + "' != '" + (-1.0d) + "'", double18 == (-1.0d));
    }

    /** 
     * Verifica radiciação com indice negativo
     * verifica subtracao com subtraendo negativo
     * Verifica se radiciação com indice negativo lança IllegalArgumentException
     * **/
    //testRadiciacaoReturningPositive
    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.radiciacao((double) 100L, (double) (byte) -1);
        double double6 = calculadora0.subtracao((double) 1.0f, (double) (-1.0f));
        try {
            double double9 = calculadora0.radiciacao((double) (short) 10, 0.0d);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Indice nao pode ser igual a 0");
        } catch (java.lang.IllegalArgumentException e) {
        }
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 100.0d + "'", double3 == 100.0d);
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 2.0d + "'", double6 == 2.0d);
    }

    /** 
     * Verifica potenciacao com base e expoentes positivos
     * Divisão com operandos positivos 
     * E se radiciação com indice negativo lança IllegalArgumentException
     * **/
    //testDivisaoWithPositive
    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.potenciacao(1.0d, (double) 1.0f);
        double double6 = calculadora0.divisao((double) 0, (double) 10.0f);
        try {
            double double9 = calculadora0.radiciacao((double) 'a', 0.0d);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Indice nao pode ser igual a 0");
        } catch (java.lang.IllegalArgumentException e) {
        }
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 1.0d + "'", double3 == 1.0d);
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 0.0d + "'", double6 == 0.0d);
    }

    /** 
     * Verifica a soma com parcela1 negativa e parcela 2 positiva
     * Verifica a subtração com minuendo positivo e subtraendo negativo
     * Verifica a radiciacao com radicando 0 e indice negativo
     * Verifica a multiplicacao com multiplicando positivo e multiplicador positivo
     * Verifica a potenciacao com base positiva e expoente positivo
     * **/
    //testMultiplicacaoAndSoma
    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.soma((-1.0d), 0.0d);
        double double6 = calculadora0.subtracao((double) '#', (double) (-1.0f));
        double double9 = calculadora0.radiciacao(0.0d, (double) (-1));
        double double12 = calculadora0.divisao((-0.0d), (double) (-1));
        double double15 = calculadora0.multiplicacao((double) 1.0f, (double) 1.0f);
        double double18 = calculadora0.potenciacao((double) '#', 2.147483647E9d);
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + (-1.0d) + "'", double3 == (-1.0d));
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 36.0d + "'", double6 == 36.0d);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 0.0d + "'", double9 == 0.0d);
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 0.0d + "'", double12 == 0.0d);
        org.junit.Assert.assertTrue("'" + double15 + "' != '" + 1.0d + "'", double15 == 1.0d);
        org.junit.Assert.assertTrue("'" + double18 + "' != '" + 2.147483647E9d + "'", double18 == 2.147483647E9d);
    }

    /** 
     * Verifica potenciacao com base positiva e expoente positivo
     * Verifica divisao com dividendo 0 e divisor positivo
     * Verifica toHex com number negativo
     * Verifica potenciacao com base positiva e expoente negativo
     * Verifica radiciaco com radicando negativo e indice 0 lanca IllegalArgumentException
     * **/
    //testToHex
    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.potenciacao(1.0d, (double) 1.0f);
        double double6 = calculadora0.divisao((double) 0, (double) 10.0f);
        boolean boolean8 = calculadora0.isQuadradoPerfeito(0);
        java.lang.String str10 = calculadora0.toHex((double) (-1L));
        double double13 = calculadora0.potenciacao((double) (short) 100, (double) (-1.0f));
        try {
            double double16 = calculadora0.radiciacao((-300.0d), (double) 0.0f);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Indice nao pode ser igual a 0");
        } catch (java.lang.IllegalArgumentException e) {
        }
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 1.0d + "'", double3 == 1.0d);
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 0.0d + "'", double6 == 0.0d);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "-0x1.0p0" + "'", str10.equals("-0x1.0p0"));
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 0.0d + "'", double13 == 0.0d);
    }

    
    //testIsQuadradoPerfeito
    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.radiciacao((double) 100L, (double) (byte) -1);
        boolean boolean5 = calculadora0.isQuadradoPerfeito(0);
        java.lang.Class<?> wildcardClass6 = calculadora0.getClass();
        try {
            double double9 = calculadora0.radiciacao((double) '4', 0.0d);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Indice nao pode ser igual a 0");
        } catch (java.lang.IllegalArgumentException e) {
        }
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 100.0d + "'", double3 == 100.0d);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    //
    //testPotenciacaoWithNegative
    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        exemplo.Calculadora calculadora0 = new exemplo.Calculadora();
        double double3 = calculadora0.soma((-1.0d), 0.0d);
        double double6 = calculadora0.subtracao((double) '#', (double) (-1.0f));
        double double9 = calculadora0.radiciacao(0.0d, (double) (-1));
        double double12 = calculadora0.divisao((-0.0d), (double) (-1));
        double double15 = calculadora0.multiplicacao((double) 1.0f, (double) 1.0f);
        java.lang.Class<?> wildcardClass16 = calculadora0.getClass();
        double double19 = calculadora0.potenciacao((double) 100.0f, (-1.0d));
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + (-1.0d) + "'", double3 == (-1.0d));
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 36.0d + "'", double6 == 36.0d);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 0.0d + "'", double9 == 0.0d);
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 0.0d + "'", double12 == 0.0d);
        org.junit.Assert.assertTrue("'" + double15 + "' != '" + 1.0d + "'", double15 == 1.0d);
        org.junit.Assert.assertNotNull(wildcardClass16);
        org.junit.Assert.assertTrue("'" + double19 + "' != '" + 0.0d + "'", double19 == 0.0d);
    }
}

