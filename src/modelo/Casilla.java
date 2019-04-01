package modelo;

public class Casilla {
	private boolean mina = false;
	private boolean velada=true;
	private boolean marcada=false;
	private int minasAlrededor=0;
	public boolean isMina() {
		return mina;
	}
	public void setMina(boolean mina) {
		this.mina = mina;
	}
	public int getMinasAlrededor() {
		return minasAlrededor;
	}
	public void setMinasAlrededor(int alrededor) {
		this.minasAlrededor = alrededor;
	}
	public boolean isVelada() {
		return velada;
	}
	public void setVelada(boolean velada) {
		this.velada = velada;
	}
	public void incrementaUnaMinasAlrededor() {
		minasAlrededor++;
	}
	public boolean isMarcada() {
		return marcada;
	}
	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}
	

}
