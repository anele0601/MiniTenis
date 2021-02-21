package Pantallas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Color;
import Principal.Interface;
import Principal.PanelJuego;
import Principal.Sprite;
import Generico.Utilidades;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

/**
 * Clase PantallaInicio. El juego se iniciará con esta pantalla, la cual te
 * permite elegir 1 o 2 jugadores pulsando las teclas correspondientes. Una vez
 * pulsemos 1 ó 2, cambiaremos la pantalla a PantallaJuego.
 * 
 * Esta pantalla cuenta con pelotas moviendose y cambio de colores en los textos
 * para hacerla más llamativa.
 * 
 * @author Elena Nofuentes
 * @since 20-02-2021
 * @version 2.3
 * @see PantallaJuego
 */
public class PantallaInicio implements Interface {
    /** Atributos de la clase */
    private final int N_PELOTAS = 3;
    private final int TAMA_PELOTA = 50;

    private PanelJuego panel;
    private Color colorLetraInicio;
    private int velX, velY;
    private ArrayList<Sprite> pelotas;
    private Image fondoR;
    private BufferedImage fondo;

    /** Constructor parametrizado */
    public PantallaInicio(PanelJuego panelJuego) {
        panel = panelJuego;
        pelotas = new ArrayList<>();
        inicializarPantalla();
    }

    /**
     * Metodo para retornar la velocidad aleatoria.
     * 
     * @return entero entre -15 y 15.
     */
    public int velocidad() {
        return (int) (Math.random() * 31) - 15;
    }

    /**
     * Método para rellenar el fondo del componente.
     * 
     * @param g gráfico.
     */
    private void rellenarFondo(Graphics g) {
        g.drawImage(fondoR, 0, 0, null);
    }

    /** Método para inicializar la pantalla y sus componentes */
    @Override
    public void inicializarPantalla() {
        colorLetraInicio = Utilidades.BLANCO;
        for (int i = 0; i < N_PELOTAS; i++) {
            do {
                velX = velocidad();
                velY = velocidad();
            } while (velX == 0 && velY == 0);
            pelotas.add(new Sprite("imagenes/pelota.png", TAMA_PELOTA, TAMA_PELOTA, 10, 10, velX, velY));
            pelotas.add(new Sprite("imagenes/pelota.png", TAMA_PELOTA, TAMA_PELOTA, 100, 100, velX, velY));
        }
        try {
            fondo = ImageIO.read(new File("imagenes/fondoTenis.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que se llama automáticamente para pintar la pantalla Utiliza atributos
     * de la clase Utilidades, como los colores y las fuentes. {@link Utilidades}
     */
    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        g.setFont(Utilidades.FUENTE_GRANDE);
        g.setColor(Utilidades.NEGRO);
        g.drawString(" MINITENIS ", 80, (panel.getHeight() / 2) - 100);
        g.fillRect(90, (panel.getHeight() / 2) - 25, 320, 40);
        g.setColor(colorLetraInicio);
        g.setFont(Utilidades.FUENTE_PEQUE);
        g.drawString("- Pulsa para 1 o 2 para jugar - ", 105, panel.getHeight() / 2);
        g.setColor(Utilidades.NEGRO);
        g.drawString(" 1 = Un jugador ", (panel.getWidth() / 2) - 75, (panel.getHeight() / 2) + 80);
        g.drawString(" 2 = Dos jugadores ", (panel.getWidth() / 2) - 75, (panel.getHeight() / 2) + 110);
        for (int i = 0; i < pelotas.size(); i++) {
            pelotas.get(i).estampar(g);
        }
    }

    /**
     * Método que se llama automáticamente y ejecuta el movimiento de la pantalla
     */
    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < pelotas.size(); i++) {
            pelotas.get(i).mover(panel.getWidth(), panel.getHeight());
        }
        // Cambiamos las letras de color
        colorLetraInicio = colorLetraInicio == Utilidades.BLANCO ? Utilidades.VERDE : Utilidades.BLANCO;
    }

    /** Método que redimensiona la pantalla */
    @Override
    public void redimensionarPantalla() {
        fondoR = fondo.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
    }

    /**
     * Método que controla la pulsación del teclado para iniciar la pantallaDeJuego
     * con 1 o 2 jugadores, dependiendo de la tecla que pulsemos. Indicamos cuantos
     * jugadores habrá mediante un booleano. false -> 1 jugador, true -> 2 jugadores
     */
    @Override
    public void pulsarTeclado(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
            panel.setPantalla(new PantallaJuego(panel, false));
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            panel.setPantalla(new PantallaJuego(panel, true));
        }
    }

    @Override
    public void pulsarRaton(MouseEvent e) {
    }

    @Override
    public void moverRaton(MouseEvent e) {
    }

    @Override
    public void soltarTeclado(KeyEvent e) {
    }
}