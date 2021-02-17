package Pantallas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

import Principal.Interface;
import Principal.PanelJuego;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Generico.Utilidades;

import java.awt.Image;

/**
 * Clase PantallaFin.
 */
public class PantallaFin implements Interface {
    /** Atributos de la clase */
    private PanelJuego panel;
    private int puntuacion;
    private Color colorLetraInicio = Color.WHITE;
    private String tiempo;

    private Image fondoR;
    private BufferedImage fondo;

    /** Constructor */
    public PantallaFin(PanelJuego panel, int cont, String tiempo) {
        this.panel = panel;
        puntuacion = cont;
        this.tiempo = tiempo;
        inicializarPantalla();
    }

    @Override
    public void inicializarPantalla() {
        try {
            fondo = ImageIO.read(new File("imagenes/fondoTenis.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        redimensionarPantalla();
    }

    /** Método que se llama automáticamente para pintar la pantalla */
    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        g.setFont(Utilidades.FUENTE_INTERMEDIA);
        g.setColor(Utilidades.NEGRO);
        g.drawString("¡Partida terminada!", 40, (panel.getHeight() / 2) - 100);
        g.setColor(Utilidades.ROJO);
        g.setFont(Utilidades.FUENTE_MEDIANA);
        g.drawString("Tiempo : " + tiempo, 145, (panel.getHeight() / 2) - 25);
        g.setColor(Utilidades.NEGRO);
        g.drawRect(125, panel.getHeight() / 2 - 75, 240, 150);
        g.drawRect(120, panel.getHeight() / 2 - 80, 250, 160);
        g.setColor(Utilidades.ROJO);
        g.drawString("Puntuación : " + puntuacion, 140, (panel.getHeight() / 2) + 40);
        g.setColor(Utilidades.NEGRO);
        g.setFont(Utilidades.FUENTE_PEQUE);
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
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Cambiamos las letras de color.
        colorLetraInicio = colorLetraInicio == Color.WHITE ? Color.RED : Color.WHITE;
    }

    /**
     * Método que controla si pulsamos en el ratón. Si pulsamos significa que
     * queremos volver a jugar, por lo que establecemos otra pantalla de juego.
     */
    @Override
    public void pulsarRaton(MouseEvent e) {
        panel.setPantalla(new PantallaJuego(panel));
    }

    @Override
    public void moverRaton(MouseEvent e) {
    }

    @Override
    public void redimensionarPantalla() {
        fondoR = fondo.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
    }

    @Override
    public void pulsarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void soltarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}