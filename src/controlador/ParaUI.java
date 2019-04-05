package controlador;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Densidad;
import vista.UI;

public class ParaUI extends UI {

	public Tablero tablero;
	private AccionadorController accionadorController;
	public boolean gameState = true;

	private Coordenada obtenCoordenada(String posicion) {
		return new Coordenada(Integer.parseInt(posicion.substring(0, 1)), Integer.parseInt(posicion.substring(1, 2)));
	}

	private void actualizaBotonera(JButton[][] botonera2, Tablero tablero) {
		for (int i = 0; i < botonera2.length; i++) {
			for (int j = 0; j < botonera2[i].length; j++) {
				Coordenada coordenada = new Coordenada(i, j);
				if (!tablero.isCasillaVelada(coordenada)) {
					String letrero = "M";
					if (!tablero.isCasillaMina(coordenada)) {
						letrero = String.valueOf(tablero.getMinasAlrededorCasilla(coordenada));
					}
					botonera2[i][j].setText(letrero);

				}
			}
		}
	}

	public ParaUI() {
		super();
		// Estos valores pueden ser configurables
		tablero = new IniciadorController().iniciarJuego(10, 10, Densidad.media);
		accionadorController = new AccionadorController(tablero);
		//Crear aqui el listener para el raton me permite ahorrarme el paso de parametros
		//en el constructor del listener porque al estar aquí accede a las propiedades del paraui
		//Si lo comparas con la version anterior hay menos lineas de conexion y dependencias en el
		//diagrama de clases 
		MouseAdapter mio = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (gameState) {
					System.out.println("llego al listener");
					if (e.getButton() == 1) {
						Coordenada obtenCoordenada = obtenCoordenada(((JButton) e.getSource()).getName());
						System.out.println(obtenCoordenada.toString());
						if (accionadorController.accionaCasilla(obtenCoordenada)) {
							System.out.println("que es una mina");
							gameState = false;
						}
					}
					if (e.getButton() == 3)
						System.out.println("derecho");
					actualizaBotonera(getBotonera(), accionadorController.tablero);
					getPanelBotones().revalidate();
				}
			}
		};
		crearBotonera(10, 10, mio);
	}

	// Como los botones se crean en tiempo de ejecución deben ponerse aqui a pesar
	// de pertenecer a la vista
	private void crearBotonera(int filas, int columnas, MouseAdapter mio) {
		getPanelBotones().removeAll();
		getPanelBotones().setLayout(new GridLayout(filas, columnas));
		setBotonera(new JButton[filas][columnas]);
		for (int fila = 0; fila < getBotonera().length; fila++) {
			for (int columna = 0; columna < getBotonera()[fila].length; columna++) {
				getBotonera()[fila][columna] = new JButton();
				JButton jButton = getBotonera()[fila][columna];
				jButton.setName(String.valueOf(fila) + String.valueOf(columna));
				jButton.addMouseListener(mio);
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
