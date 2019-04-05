package controlador;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;

import modelo.Densidad;
import vista.UI;

public class ParaUI extends UI {

	public Tablero tablero;
	private MyMouseListener miListener;
	private AccionadorController accionadorController;

	public ParaUI() {
		super();
		// Estos valores pueden ser configurables
		tablero = new IniciadorController().iniciarJuego(10, 10, Densidad.media);
		accionadorController = new AccionadorController(tablero);
		miListener = new MyMouseListener(accionadorController);
		crearBotonera(10, 10);
		miListener.setBotonera(getBotonera());
		miListener.setPanel(getPanelBotones());
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
				JButton jButton = getBotonera()[fila][columna];
				jButton.setName(String.valueOf(fila) + String.valueOf(columna));
				jButton.addMouseListener(miListener);
				getPanelBotones().add(jButton);
			}
		}
		getPanelBotones().revalidate();

		// Le a�adimos a la botonera los action listener
		for (int i = 0; i < getBotonera().length; i++) {
			for (int j = 0; j < getBotonera()[i].length; j++) {
				getBotonera()[i][j].setBackground(Color.LIGHT_GRAY);
			}
		}
	}

}
