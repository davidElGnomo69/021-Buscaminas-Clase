package modelo;

public class Coordenada {

	private int x=0,y=0;

	public Coordenada(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Coordenada() {
		super();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public boolean equals(Object arg0) {
		boolean retorno=super.equals(arg0);
		if(!retorno&&arg0!=null) {
			Coordenada obj=(Coordenada)arg0;
			retorno=this.getX()==obj.getX()&&this.getY()==obj.getY();
		}
		return retorno;
	}
	@Override
	public String toString() {
		return x+":"+y;
	}
}
