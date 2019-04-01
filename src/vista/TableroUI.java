package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;
import javax.swing.BoxLayout;

import java.awt.ComponentOrientation;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableroUI extends JFrame {

	protected JPanel contentPane;
	protected JButton[][] botonera;
	protected JPanel panelBotones;
	protected JMenuItem mntmFacil;
	protected JMenuItem mntmMedio;
	protected JMenuItem mntmHardcore;
	protected JMenu mnJugar;
	protected JMenuBar menuBar;
	protected JPanel panel;
	protected JTextField txtNMinas;
	protected JTextField textDificultad;
	protected JLabel lblFin;
	protected JMenuItem mntmPersonalizado;
	protected JPanel panelInformativo;
	protected JLabel lblTamano;
	protected JLabel lblMinas;
	protected JPanel panelPersonalizado;
	protected JButton btnAceptar;
	protected JSpinner spnMinas;
	protected JSpinner spnTamanoX;
	protected JLabel lblSonido;



	
	/**
	 * Create the frame.
	 */
	public TableroUI() {
		setTitle("Busca Minas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TableroUI.class.getResource("/Imagenes/minaMaximizada.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 300);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnJugar = new JMenu("Jugar");
		menuBar.add(mnJugar); 
		
		mntmFacil = new JMenuItem("Facil");
		mnJugar.add(mntmFacil);
		
		mntmMedio = new JMenuItem("Medio");
		mnJugar.add(mntmMedio);
		
		mntmHardcore = new JMenuItem("Hardcore");
		mnJugar.add(mntmHardcore);
		
		mntmPersonalizado = new JMenuItem("Personalizado");
		mnJugar.add(mntmPersonalizado);
		
		lblSonido = new JLabel();
		menuBar.add(lblSonido);
		
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setFocusable(false);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panelInformativo = new JPanel();
		panel.add(panelInformativo);
		
		JLabel lblNMinas = new JLabel("N\u00BA Minas");
		panelInformativo.add(lblNMinas);
		
		txtNMinas = new JTextField();
		panelInformativo.add(txtNMinas);
		txtNMinas.setEditable(false);
		txtNMinas.setColumns(10);
		
		lblFin = new JLabel("");
		panelInformativo.add(lblFin);
		lblFin.setForeground(Color.RED);
		lblFin.setFont(new Font("Viner Hand ITC", Font.BOLD, 24));
		
		JLabel lblDifucultad = new JLabel("Dificultad");
		panelInformativo.add(lblDifucultad);
		
		textDificultad = new JTextField();
		panelInformativo.add(textDificultad);
		textDificultad.setEditable(false);
		textDificultad.setColumns(10);
		
		panelPersonalizado = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelPersonalizado.getLayout();
		flowLayout.setHgap(20);
		panelPersonalizado.setVisible(false);
		panel.add(panelPersonalizado);
		
		lblMinas = new JLabel("N\u00BA Minas");
		panelPersonalizado.add(lblMinas);
		
		spnMinas = new JSpinner();
		spnMinas.setModel(new SpinnerNumberModel(1, 1, 300, 1));
		panelPersonalizado.add(spnMinas);
		
		lblTamano = new JLabel("Tama\u00F1o Tablero");
		panelPersonalizado.add(lblTamano);
		
		spnTamanoX = new JSpinner();
		spnTamanoX.setModel(new SpinnerNumberModel(5, 5, 40, 1));
		panelPersonalizado.add(spnTamanoX);
		
		btnAceptar = new JButton("Aceptar");
		panelPersonalizado.add(btnAceptar);
	}

}
