package controlador;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import modelo.Densidad;
import vista.UI;

public class ParaUI extends UI {

	private Tablero tablero;

	public ParaUI() {
		super();
		// Estos valores pueden ser configurables
		new IniciadorController().iniciarJuego(tablero, 10, 10, Densidad.media);
		crearBotonera(10, 10);
		
	}

	// Como los botones se crean en tiempo de ejecución deben ponerse aqui a pesar
	// de pertenecer a la vista
	private void crearBotonera(int filas, int columnas) {
		getPanelBotones().removeAll();
		getPanelBotones().setLayout(new GridLayout(filas, columnas));
		setBotonera(new JButton[filas][columnas]);
		for (int fila = 0; fila < getBotonera().length; fila++) {
			for (int columna = 0; columna < getBotonera()[fila].length; columna++) {
				getBotonera()[fila][columna] = new JButton();
				getPanelBotones().add(getBotonera()[fila][columna]);
			}
		}
		getPanelBotones().revalidate();

		// Le a�adimos a la botonera los action listener
		for (int i = 0; i < getBotonera().length; i++) {
			for (int j = 0; j < getBotonera()[i].length; j++) {
				getBotonera()[i][j].setBackground(Color.DARK_GRAY);
			}
		}
	}

}
