package Principal;

import java.awt.EventQueue;

/**
 * Clase principal del Juego.
 * 
 * @author Elena Nofuentes
 * @version 1.0 {@link VentanaPrincipal#inicializar()}
 *
 */
public class Principal {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
					ventanaPrincipal.inicializar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}