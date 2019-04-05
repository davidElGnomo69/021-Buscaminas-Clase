package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import modelo.Casilla;
import modelo.Coordenada;

//Aqui convendría explicar lo del Adapter
public class MyMouseListener extends MouseAdapter {

	private AccionadorController accionador;
	JButton[][] botonera;
	JPanel panel;

	public MyMouseListener(AccionadorController accionador) {
		super();
		this.accionador = accionador;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("llego al listener");
		if (e.getButton() == 1) {
			Coordenada obtenCoordenada = obtenCoordenada(((JButton) e.getSource()).getName());
			System.out.println(obtenCoordenada.toString());
			accionador.accionaCasilla(obtenCoordenada);
		}
		if (e.getButton() == 3)
			System.out.println("derecho");
		actualizaBotonera(botonera, accionador.tablero);
		panel.revalidate();

	}

	private void actualizaBotonera(JButton[][] botonera2, Tablero tablero) {
		for (int i = 0; i < botonera2.length; i++) {
			for (int j = 0; j < botonera2[i].length; j++) {

				Casilla casilla = tablero.getCasilla(new Coordenada(i, j));
				if (!casilla.isVelada()) {
					botonera2[i][j].setText(String.valueOf(casilla.getMinasAlrededor()));
				}
			}
		}
	}

	private Coordenada obtenCoordenada(String posicion) {
		return new Coordenada(Integer.parseInt(posicion.substring(0, 1)), Integer.parseInt(posicion.substring(1, 2)));
	}

	public void setBotonera(JButton[][] botonera) {
		this.botonera = botonera;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
