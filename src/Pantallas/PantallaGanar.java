package Pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Image;
import javax.imageio.ImageIO;

import Generico.Utilidades;
import Principal.Interface;
import Principal.PanelJuego;
import Principal.Sprite;

import java.awt.image.BufferedImage;

/**
 * Clase PantallaGanar.
 */

public class PantallaGanar implements Interface {
    /** Atributos de la clase */
    private PanelJuego panel;
    private Color colorLetraInicio = Color.WHITE;
    private ArrayList<Sprite> pelotas;
    private boolean controlJugadores;
    private Image fondoR;
    private BufferedImage fondo;

    private final int N_PELOTAS = 3;
    private final int TAMA_PELOTA = 50;

    private int velX, velY;

    /** Constructor */
    public PantallaGanar(PanelJuego panel, boolean jugador) {
        this.panel = panel;
        pelotas = new ArrayList<>();
        controlJugadores = jugador;
        inicializarPantalla();
    }

    /** Método que inicializar los componentes de la pantalla */
    @Override
    public void inicializarPantalla() {
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
        Utilidades.reproducirSonidos("sonidos/ganar.wav");
        redimensionarPantalla();
    }

    /**
     * Metodo para retornar la velocidad aleatoria.
     * 
     * @return entero entre -15 y 15.
     */
    public int velocidad() {
        return (int) (Math.random() * 31) - 15;
    }

    /** Método que se llama automáticamente para pintar la pantalla */
    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        for (int i = 0; i < pelotas.size(); i++) {
            pelotas.get(i).estampar(g);
        }
        g.setFont(Utilidades.FUENTE_MEDIANA);
        g.setColor(Utilidades.NEGRO);
        g.drawString("¡JUEGO COMPLETADO!", 75, (panel.getHeight() / 2) - 100);
        g.fillRect(90, (panel.getHeight() / 2) - 25, 320, 40);
        g.setColor(colorLetraInicio);
        g.setFont(Utilidades.FUENTE_PEQUE);
        g.drawString("- PUNTUACIÓN MÁXIMA - ", 125, panel.getHeight() / 2);
        g.setColor(Utilidades.AMARILLO);
        g.drawString("- Pulsa para volver a jugar - ", 115, (panel.getHeight() / 2) + 125);
    }

    /**
     * Método para rellenar el fondo del componente.
     * 
     * @param g gráfico.
     */
    private void rellenarFondo(Graphics g) {
        g.drawImage(fondoR, 0, 0, null);
    }

    /** Dormimos el hilo para que la ejecución sea correcta */
    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Cambiamos las letras de color.
        colorLetraInicio = colorLetraInicio == Color.WHITE ? Color.YELLOW : Color.WHITE;
        for (int i = 0; i < pelotas.size(); i++) {
            pelotas.get(i).mover(panel.getWidth(), panel.getHeight());
        }
    }

    /**
     * Método que controla si pulsamos en el ratón. Si pulsamos significa que
     * queremos volver a jugar, por lo que establecemos otra pantalla de juego.
     * Diferencia si la partida era de uno o dos jugadores.
     */
    @Override
    public void pulsarRaton(MouseEvent e) {
        if (controlJugadores) {
            panel.setPantalla(new PantallaJuego(panel, controlJugadores));
        } else {
            panel.setPantalla(new PantallaJuego(panel));
        }
    }

    @Override
    public void moverRaton(MouseEvent e) {
    }

    /** Método que redimensiona la imagen de fondo de pantalla */
    @Override
    public void redimensionarPantalla() {
        fondoR = fondo.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
    }

    @Override
    public void pulsarTeclado(KeyEvent e) {
    }

    @Override
    public void soltarTeclado(KeyEvent e) {
    }
}