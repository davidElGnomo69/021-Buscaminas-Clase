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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;


public class UI extends JFrame {

	/**
	 * Create the frame.
	 * 
	 */
	private JButton[][] botonera;
	private JPanel panelBotones;
	private JLabel lblMensajeFin;
	private JButton btnRestart;
	
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
		
		lblMensajeFin = new JLabel("mensajeFin");
		
		btnRestart = new JButton("Restart");
		btnRestart.setEnabled(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblMensajeFin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRestart, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(16)
					.addComponent(lblMensajeFin, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRestart)
					.addContainerGap(521, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
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

	public JLabel getLblMensajeFin() {
		return lblMensajeFin;
	}

	public void setLblMensajeFin(JLabel lblMensajeFin) {
		this.lblMensajeFin = lblMensajeFin;
	}

	public JButton getBtnRestart() {
		return btnRestart;
	}

	public void setBtnRestart(JButton btnRestart) {
		this.btnRestart = btnRestart;
	}
	
}
