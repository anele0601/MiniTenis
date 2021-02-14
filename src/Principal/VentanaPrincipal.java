package Principal;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * Ventana Principal.
 */
public class VentanaPrincipal {
	// La ventana principal, en este caso, guarda todos los componentes:
	private JFrame ventana;
	private PanelJuego panelJuego;

	/**
	 * Constructor, marca el tamaño y el cierre del frame
	 */
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(450, 50, 500, 700);
		ventana.setResizable(false);
		ventana.setTitle(" MiniTenis ");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Inicializa todos los componentes del frame
	 */
	public void inicializarComponentes() {
		// Definimos el layout:
		ventana.setLayout(new GridLayout(1, 1));

		// PANEL JUEGO
		panelJuego = new PanelJuego();
		ventana.add(panelJuego);
	}

	/**
	 * Inicializa todos los lísteners del frame
	 */
	public void inicializarListeners() {
	}

	/**
	 * Método que realiza todas las llamadas necesarias para inicializar la ventana
	 * correctamente.
	 */
	public void inicializar() {
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}
}