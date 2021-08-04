package exemplo;

public class Calculadora {
		
	public double soma(double parcela1, double parcela2) {
		double result = extracted(parcela1, parcela2);
		return result;
	}

	private double extracted(double parcela1, double parcela2) {
		double result = parcela1 + parcela2;
		return result;
	}
	
	public double subtracao(double minuendo, double subtraendo) {
		return minuendo - subtraendo;
	}
	
	public double divisao(double dividendo, double divisor) throws IllegalArgumentException {
		if(divisor == 0) 
			throw new IllegalArgumentException("Nao e possivel realizar divisao por 0");
		return dividendo/divisor;
	}
	
	public double multiplicacao(double multiplicando, double multiplicador) {
		return multiplicando * multiplicador;
	}
	
	public double radiciacao(double radicando, double indice) throws IllegalArgumentException {
		if(indice == 0)
			throw new IllegalArgumentException("Indice nao pode ser igual a 0");
		if((indice % 2 != 0) && (radicando < 0 ))
			throw new IllegalArgumentException("Radicando não pode ser negativo se o indice for par");
		
		if(indice < 0)
			indice = -1 * (1.0/indice);
		
		return Math.pow(radicando, indice);
	}
	
	public double potenciacao(double base, double expoente) {
		return (int) Math.pow(base, expoente);
	}
	
	public boolean isQuadradoPerfeito(int value) {
		double raiz = Math.sqrt(value);
		
		return (raiz - Math.round(value) == 0);
	}
	
	public String toHex(double number) {
		return Double.toHexString(number);
	}	
}