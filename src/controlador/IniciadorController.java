package controlador;

import modelo.Densidad;

public class IniciadorController {

	public void iniciarJuego(Tablero tablero,int filas,int columnas,Densidad densidad) {
		tablero=new Tablero(filas, columnas, densidad);
	}
}
