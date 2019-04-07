package controlador;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
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
				String letrero = "B";
				if (!tablero.isCasillaVelada(coordenada)) {
					if (!tablero.isCasillaMina(coordenada)) {
						letrero = String.valueOf(tablero.getMinasAlrededorCasilla(coordenada));
					}
				} else {
					letrero = " ";
					if (tablero.isCasillaMarcada(coordenada)) {
						 letrero = "M";
					}
				}
				botonera2[i][j].setText(letrero);
			}
		}
	}


	public ParaUI() {
		super();
		// Estos valores pueden ser configurables
		tablero = new IniciadorController().iniciarJuego(10, 10, Densidad.baja);
		accionadorController = new AccionadorController();
		// Crear aqui el listener para el raton me permite ahorrarme el paso de
		// parametros
		// en el constructor del listener porque al estar aquí accede a las propiedades
		// del paraui
		// Si lo comparas con la version anterior hay menos lineas de conexion y
		// dependencias en el
		// diagrama de clases
		MouseAdapter mio = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Esta es la forma de controlar el flujo del juego
				//Si no hay una explosion se sigue jugando
				if (!tablero.isExplosion()) {
					Coordenada obtenCoordenada = obtenCoordenada(((JButton) e.getSource()).getName());
					if (e.getButton() == 1) {
						accionadorController.accionaCasilla(tablero, obtenCoordenada);
					}
					if (e.getButton() == 3) {
						System.out.println("BOTON DERECHO");
						new MarcadorController().marcarCasilla(tablero, obtenCoordenada);
					}
					actualizaBotonera(getBotonera(), tablero);
				}
				else {
					//Lo dejamos como ejercicio. Si hay una explosion podiamos tener un mensaje que lo diga
					//en el ui y podíamos habilitar un boton en el panel derecho para reiniciar el juego
					getLblMensajeFin().setText("HAS PERDIDO");
					getBtnRestart().setEnabled(true);
				}
			}
		};
		crearBotonera(10, 10, mio);
		getBtnRestart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
				
			}
		});
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
		
		for (int i = 0; i < getBotonera().length; i++) {
			for (int j = 0; j < getBotonera()[i].length; j++) {
				getBotonera()[i][j].setBackground(Color.LIGHT_GRAY);
			}
		}
	}

}
