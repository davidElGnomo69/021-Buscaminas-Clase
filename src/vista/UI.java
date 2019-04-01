package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class UI extends JFrame {

	/**
	 * Create the frame.
	 * 
	 */
	private JButton[][] botonera;
	private JPanel panelBotones;
	
	public UI() {
		setTitle("Busca Minas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 675);
		setLocationRelativeTo(null);
		
		JLabel lblBuscaminas = new JLabel("Buscaminas");
		lblBuscaminas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBuscaminas.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblBuscaminas, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		
		JLabel lblUsoFuturo = new JLabel("USO FUTURO ");
		panel.add(lblUsoFuturo);
		
		//La asignacion de botones se hace dinamicamentes, todavia no se sabe cuantas filas, columnas
		setPanelBotones(new JPanel());
		getContentPane().add(getPanelBotones(), BorderLayout.CENTER);
		
	}

	public JButton[][] getBotonera() {
		return botonera;
	}

	public void setBotonera(JButton[][] botonera) {
		this.botonera = botonera;
	}

	public JPanel getPanelBotones() {
		return panelBotones;
	}

	public void setPanelBotones(JPanel panelBotones) {
		this.panelBotones = panelBotones;
	}

		
}
