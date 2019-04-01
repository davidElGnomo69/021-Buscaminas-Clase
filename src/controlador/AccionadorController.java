package controlador;

import modelo.Tablero;

public class AccionadorController {
	public void accionaCasilla(int x, int y, Tablero tablero) {
		if (tablero.comprobarAccion(x, y)) {
			tablero.desvelarCasilla(x, y);
		}
		else {
			tablero.revelarCasilla(x, y);
		}
	}

}
