package controlador;

import java.awt.event.MouseAdapter;

import modelo.Coordenada;

public class AccionadorController extends MouseAdapter {
	
	//Aqui desaparece el retorno boolean porque revelar casilla
	//podia encontrar una bomba y el resultado se perdia en la recursividad
	public void accionaCasilla(Tablero tablero,Coordenada coordenada) {
		if (tablero.comprobarAccion(coordenada)) {
			tablero.desvelarCasilla(coordenada,false);
		}
		else {
			tablero.revelarCasilla(coordenada);
		}
	}

}
