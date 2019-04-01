package modelo;

public class Casilla {
	private boolean mina = false;
	private boolean velada=true;
	private boolean marcada=false;
	private int alrededor=0;
	public boolean isMina() {
		return mina;
	}
	public void setMina(boolean mina) {
		this.mina = mina;
	}
	public int getAlrededor() {
		return alrededor;
	}
	public void setAlrededor(int alrededor) {
		this.alrededor = alrededor;
	}
	public boolean isVelada() {
		return velada;
	}
	public void setVelada(boolean velada) {
		this.velada = velada;
	}
	public void incrementaUnaMinasAlrededor() {
		alrededor++;
	}
	public boolean isMarcada() {
		return marcada;
	}
	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}
	

}
