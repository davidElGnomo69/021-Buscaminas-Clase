package controlador;

import java.awt.event.MouseAdapter;

import modelo.Coordenada;

public class AccionadorController extends MouseAdapter {
	Tablero tablero;
	
	public AccionadorController(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	public boolean accionaCasilla(Coordenada coordenada) {
		boolean retorno=false;
		if (tablero.comprobarAccion(coordenada)) {
			retorno=tablero.desvelarCasilla(coordenada);
		}
		else {
			retorno=tablero.revelarCasilla(coordenada);
		}
		return retorno;
	}

}
