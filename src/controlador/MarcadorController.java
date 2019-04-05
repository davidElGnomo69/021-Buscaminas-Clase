package controlador;

import modelo.Coordenada;

public class MarcadorController {
 public boolean marcarCasilla(Tablero tablero, Coordenada coordenada) {
	 //Preguntas para luego pensar?
	 //Realmente hace falta este Controller o es solo por estetica?
	 return tablero.marcarCasilla(coordenada);
 }
}
