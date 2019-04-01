package modelo;

public enum Densidad {
	baja(10),media(6),alta(3);
	
	private int valor;

	
	private Densidad(int valor) {
		this.valor = valor;
	}


	public int getValor() {
		return valor;
	}

}
