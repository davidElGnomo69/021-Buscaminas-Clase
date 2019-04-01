package modelo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

	public boolean comprobarAccion(int x,int y) {
		return getCasilla(x, y).isVelada();
	}
	
	public boolean desvelarCasilla(int x, int y) {
		boolean mina = false;
		Casilla casilla = getCasilla(x, y);
		if (casilla.isVelada()) {
			casilla.setVelada(false);
			mina = casilla.isMina();
			if (casilla.getAlrededor() == 0) {
				// aqui va el proceso recursivo para recorrer casillas con 0 minas alrededor
			}
		}
		return mina;
	}

	public boolean revelarCasilla(int x, int y) {
		// necesito saber las casillas marcadas alrededor
		int marcadas = getCasillasMarcadasAlrededor(x, y);
		if (!getCasilla(x, y).isVelada() && marcadas == getCasilla(x, y).getAlrededor()) {
			desvelarCasilla(x, y);
		}
		return false;
	}

	private void contarMinasAlrededor() {
		//Suponemos que esta es la casilla a inspeccionar
		int posX=1,posY=1;
		for (int i = posX-1; i < posX+2; i++) {
			if(casilla[i][j])
		}
			
		

	}

	private void colocarMinas() {
		// TODO Auto-generated method stub
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

	// Esto da un fallo porque junit no encuentra el objeto de pruebas
	// @Test
	// void test() {
	// int filas = 10, columnas = 10;
	// crearTablero(filas, columnas);
	// calcularMinas(filas, columnas, Densidad.alta);
	// colocarMinas();
	// }
	// sigue sin poder probarse porque necesita de un objeto para
	// ejecutar el test
	// @Test
	// void test() {
	// int filas = 10, columnas = 10;
	// Tablero tablero=new Tablero();
	// tablero.crearTablero(filas, columnas);
	// tablero.calcularMinas(filas, columnas, Densidad.alta);
	// tablero.colocarMinas();
	// }
	// proponer un caso en el que no pueda no fallar


	public int getCasillasMarcadasAlrededor(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Casilla getCasilla(int x, int y) {
		return casillas[x][y];
	}

	public boolean isInToTablero(int posx, int posy) {
		return posx >= 0 && posx < casillas.length && posy >= 0 && posy < casillas[posx].length;
	}
	static class Tablerotest {
		@Test
		void testgetCasillasMarcadasAlrededor() {
			Tablero tablero = new Tablero();
			int filas=3,columnas=3;
			tablero.crearTablero(filas, columnas);
			//casillas marcadas
			int marcadasX[]= {0,1,2};
			int marcadasY[]= {1,0,0};
			for (int i = 0; i < marcadasY.length; i++) {
				tablero.getCasilla(marcadasX[i], marcadasY[i]).setMarcada(true);
			}
			//Casillas a probar
			int pruebaX[]= {0,1,2};
			int pruebaY[]= {2,1,0};
			//de salida
			int esperados[]= {1,3,2};
			for (int i = 0; i < esperados.length; i++) {
				assertEquals(esperados[i], tablero.getCasillasMarcadasAlrededor(pruebaX[i], pruebaY[i]));
			}
			
		}
		
		@Test
		void testDesvelarCasilla() {
			//Com oeste constructor es privado
			//tengo que hacer el test dentro de la propia clase
			Tablero tablero = new Tablero();
			int filas=3,columnas=3;
			tablero.crearTablero(filas, columnas);
			int xs[]= {0,0,1,2,2};
			int xy[]= {1,2,1,1,2};
			tablero.casillas[1][2].setMina(true);
			for (int i = 0; i < xy.length; i++) {
				
				tablero.casillas[xs[i]][xy[i]].setAlrededor(1);
			}
			int x=0,y=0;
			tablero.desvelarCasilla(x, y);
			boolean desveladas[][]={{true,true,false},{true,true,false},{true,true,false}};
			for (int i = 0; i < desveladas.length; i++) {
				for (int j = 0; j < desveladas[i].length; j++) {
					assertEquals(desveladas[i][j], tablero.casillas[i][j].isVelada());
				}
			}
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
					assertEquals(resultados[i][j], tablero.casillas[i][j].getAlrededor());
				}
			}

		}
		// static class Tablerotest {
		// @Test
		// void test() {
		// int filas = 10, columnas = 10;
		// Tablero tablero = new Tablero();
		// tablero.crearTablero(filas, columnas);
		// tablero.calcularMinas(filas, columnas, Densidad.alta);
		// tablero.colocarMinas();
		// int contadorMinas = 0;
		// for (int i = 0; i < tablero.casillas.length; i++) {
		// for (int j = 0; j < tablero.casillas[i].length; j++) {
		// if (tablero.casillas[i][j].mina) {
		// contadorMinas++;
		// }
		// }
		// }
		// assertEquals(contadorMinas, tablero.minas);
		// }
		// Se deben probar los metodos privadows o no
		// aun asi https://www.artima.com/suiterunner/private2.html
	}
}
