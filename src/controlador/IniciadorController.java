package controlador;

import modelo.Densidad;

public class IniciadorController {

	public Tablero iniciarJuego(int filas,int columnas,Densidad densidad) {
		return new Tablero(filas, columnas, densidad);
	}
}
