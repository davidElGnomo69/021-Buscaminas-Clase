package controlador;

import modelo.Densidad;
import modelo.Tablero;

public class IniciadorController {

	public void iniciarJuego(Game game,int filas,int columnas,Densidad densidad) {
		game.tablero=new Tablero(filas, columnas, densidad);
	}
}
