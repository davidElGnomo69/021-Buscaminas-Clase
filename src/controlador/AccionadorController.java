package controlador;

import modelo.Coordenada;

public class AccionadorController {
	public void accionaCasilla(Coordenada coordenada, Tablero tablero) {
		if (tablero.comprobarAccion(coordenada)) {
			tablero.desvelarCasilla(coordenada);
		}
		else {
			tablero.revelarCasilla(coordenada);
		}
	}

}
