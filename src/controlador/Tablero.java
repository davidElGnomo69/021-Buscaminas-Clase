package controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Densidad;

public class Tablero {
	private Casilla casillas[][];
	private int minas = 0;

	private Tablero() {

	}

	public Tablero(int filas, int columnas, Densidad densidad) {
		crearTablero(filas, columnas);
		calcularMinas(filas, columnas, densidad);
		colocarMinas();
		contarMinasAlrededor();
	}

	private void contarMinasAlrededor() {
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[0].length; j++) {
				Coordenada coordenadaMinada = new Coordenada(i, j);
				if (getCasilla(coordenadaMinada).isMina()) {
					sumarAlrededorMina(coordenadaMinada);
				}
			}
		}

	}

	public boolean comprobarAccion(Coordenada coordenada) {
		return getCasilla(coordenada).isVelada();
	}

	public boolean desvelarCasilla(Coordenada coordenada) {
		boolean mina = false;
		Casilla casillaDesvelar = getCasilla(coordenada);
		if (casillaDesvelar.isVelada() && !casillaDesvelar.isMarcada()) {
			casillaDesvelar.setVelada(false);
			if (casillaDesvelar.isMina()) {
				mina = true;
			} else {
				if (casillaDesvelar.getMinasAlrededor() == 0) {
					for (int i = -1; i < +2; i++) {
						for (int j = -1; j < +2; j++) {
							Coordenada recursiva = new Coordenada(coordenada.getX() + i, coordenada.getY() + j);
							if (!coordenada.equals(recursiva) && isInToTablero(recursiva)) {
								desvelarCasilla(recursiva);
							}

						}
					}
				}
			}
		}
		return mina;
	}

	public boolean revelarCasilla(Coordenada coordenada) {
		int marcadas = getCasillasMarcadasAlrededor(coordenada);
		boolean retorno=false;
		if (!getCasilla(coordenada).isVelada() && marcadas == getCasilla(coordenada).getMinasAlrededor()) {
			retorno=desvelarCasilla(coordenada);
		}
		return retorno;
	}

	private void sumarAlrededorMina(Coordenada coordenada) {
		if (getCasilla(coordenada).isMina()) {
			for (int i = coordenada.getX() - 1; i < coordenada.getX() + 2; i++) {
				for (int j = coordenada.getY() - 1; j < coordenada.getY() + 2; j++) {
					Coordenada controlada = new Coordenada(i, j);
					if (!coordenada.equals(controlada) && isInToTablero(controlada)
							&& !getCasilla(controlada).isMina()) {
						getCasilla(controlada).incrementaUnaMinasAlrededor();
					}
				}
			}
		}

	}

	private void colocarMinas() {
		Coordenada propuesta = null;
		for (int i = 0; i < minas; i++) {
			do {
				propuesta = getRandomPosition();
			} while (getCasilla(propuesta).isMina());
			getCasilla(propuesta).setMina(true);
		}
	}

	private Coordenada getRandomPosition() {
		return new Coordenada(new Random().nextInt(casillas.length), new Random().nextInt(casillas[0].length));
	}

	private void calcularMinas(int filas, int columnas, Densidad densidad) {
		minas = filas * columnas / densidad.getValor();
	}

	private void crearTablero(int filas, int columnas) {
		casillas = new Casilla[filas][columnas];
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}

	public int getCasillasMarcadasAlrededor(Coordenada coordenada) {
		int retorno = 0;
		for (int i = coordenada.getX() - 1; i < coordenada.getX() + 2; i++) {
			for (int j = coordenada.getY() - 1; j < coordenada.getY() + 2; j++) {
				Coordenada controlada = new Coordenada(i, j);
				if (!coordenada.equals(controlada) && isInToTablero(controlada) && getCasilla(controlada).isMarcada()) {
					retorno++;
				}
			}
		}
		return retorno;
	}

	public Casilla getCasilla(Coordenada coordenada) {
		return casillas[coordenada.getX()][coordenada.getY()];
	}

	private boolean isInToTablero(Coordenada coordenada) {
		return coordenada.getX() >= 0 && coordenada.getX() < casillas.length && coordenada.getY() >= 0
				&& coordenada.getY() < casillas[coordenada.getX()].length;
	}

	static class Tablerotest {
		@Test
		void testgetCasillasMarcadasAlrededor() {
			Tablero tablero = new Tablero();
			int filas = 3, columnas = 3;
			tablero.crearTablero(filas, columnas);
			// casillas marcadas
			int marcadasX[] = { 0, 1, 2 };
			int marcadasY[] = { 1, 0, 0 };
			for (int i = 0; i < marcadasY.length; i++) {
				tablero.getCasilla(new Coordenada(marcadasX[i], marcadasY[i])).setMarcada(true);
			}
			// Casillas a probar
			int pruebaX[] = { 0, 1, 2 };
			int pruebaY[] = { 2, 1, 1 };
			// de salida
			int esperados[] = { 1, 3, 2 };
			for (int i = 0; i < esperados.length; i++) {
				assertEquals(esperados[i],
						tablero.getCasillasMarcadasAlrededor(new Coordenada(pruebaX[i], pruebaY[i])));
			}

		}

		@Test
		void testDesvelarCasilla() {
			// Com oeste constructor es privado
			// tengo que hacer el test dentro de la propia clase
			Tablero tablero = new Tablero();
			int filas = 3, columnas = 3;
			tablero.crearTablero(filas, columnas);
			int xs[] = { 0, 0, 1, 2, 2 };
			int xy[] = { 1, 2, 1, 1, 2 };
			tablero.casillas[1][2].setMina(true);
			for (int i = 0; i < xy.length; i++) {

				tablero.casillas[xs[i]][xy[i]].setMinasAlrededor(1);
			}
			int x = 0, y = 0;
			tablero.desvelarCasilla(new Coordenada(x, y));
			boolean desveladas[][] = { { false, false, true }, { false, false, true }, { false, false, true } };
			for (int i = 0; i < desveladas.length; i++) {
				for (int j = 0; j < desveladas[i].length; j++) {
//					System.out.println("en coordenada " + i + ":" + j);
					assertEquals(desveladas[i][j], tablero.casillas[i][j].isVelada());
				}
			}
		}

		@Test
		void testDesvelarCasillaconYSinBomba() {
			// Com oeste constructor es privado
			// tengo que hacer el test dentro de la propia clase
			Tablero tablero = new Tablero();
			int filas = 3, columnas = 3;
			tablero.crearTablero(filas, columnas);
			int xs[] = { 0, 0, 1, 2, 2 };
			int xy[] = { 1, 2, 1, 1, 2 };
			tablero.casillas[1][2].setMina(true);
			for (int i = 0; i < xy.length; i++) {

				tablero.casillas[xs[i]][xy[i]].setMinasAlrededor(1);
			}
			int x = 1, y = 2;
			assertTrue(tablero.desvelarCasilla(new Coordenada(x, y)));
			x = 0; y = 1;
			assertFalse(tablero.desvelarCasilla(new Coordenada(x, y)));
		}

		@Test
		void testColocar() {
			int filas = 5, columnas = 5;
			Tablero tablero = new Tablero();
			tablero.crearTablero(filas, columnas);
			// tablero.calcularMinas(filas, columnas, Densidad.alta);
			// yo pongo el numero de minas
			tablero.minas = 25;
			tablero.colocarMinas();
			int contadorMinas = 0;
			for (int i = 0; i < tablero.casillas.length; i++) {
				for (int j = 0; j < tablero.casillas[i].length; j++) {
					if (tablero.casillas[i][j].isMina()) {
						contadorMinas++;
					}
				}
			}
			assertEquals(contadorMinas, tablero.minas);
		}

		@Test
		void testContar() {
			int filas = 5, columnas = 5;
			Tablero tablero = new Tablero();
			tablero.crearTablero(filas, columnas);
			int x[] = { 1, 1, 2, 3, 4 };
			int y[] = { 0, 4, 2, 2, 4 };
			for (int k = 0; k < x.length; k++) {
				tablero.casillas[x[k]][y[k]].setMina(true);
			}
			int resultados[][] = { { 1, 1, 0, 1, 1 }, { 0, 2, 1, 2, 0 }, { 1, 3, 0, 3, 1 }, { 0, 2, 0, 3, 1 },
					{ 0, 1, 1, 2, 0 } };
			tablero.contarMinasAlrededor();

			for (int i = 0; i < resultados.length; i++) {
				for (int j = 0; j < resultados[i].length; j++) {
					assertEquals(resultados[i][j], tablero.casillas[i][j].getMinasAlrededor());
				}
			}

		}
	}

	public int getAncho() {
		return casillas.length;
	}

	public int getAlto() {
		return casillas[0].length;
	}
}
