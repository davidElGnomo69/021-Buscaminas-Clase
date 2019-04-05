package controlador;

import java.awt.event.MouseAdapter;

import modelo.Coordenada;

public class AccionadorController extends MouseAdapter {
	Tablero tablero;
	
	public AccionadorController(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	public void accionaCasilla(Coordenada coordenada) {
		if (tablero.comprobarAccion(coordenada)) {
			tablero.desvelarCasilla(coordenada);
		}
		else {
			tablero.revelarCasilla(coordenada);
		}
	}

}
