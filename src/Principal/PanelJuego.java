package Principal;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import Pantallas.PantallaInicio;

import java.awt.event.KeyEvent;
import java.awt.event.*;

/**
 * Clase Panel Juego. Maneja el paso de pantallas. En el constructor están
 * implementados todos los listener necesarios para el correcto funcionamiento
 * del juego.
 * 
 * @author Elena Nofuentes
 * @since 20-02-2021
 * @version 1.4
 */

public class PanelJuego extends JPanel implements Runnable, MouseListener, MouseMotionListener, ComponentListener {
    /** Atributos de la clase */
    private static final long serialVersionUID = 1L;
    private Interface pantalla;

    /** Constructor */
    public PanelJuego() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
        pantalla = new PantallaInicio(this);
        this.setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pantalla.pulsarTeclado(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pantalla.soltarTeclado(e);
            }
        });
        new Thread(this).start();
    }

    /** Método que pinta los componentes de la pantalla */
    @Override
    public void paintComponent(Graphics g) {
        pantalla.pintarPantalla(g);
    }

    /** Método que controla cuando presionamos el ratón */
    @Override
    public void mousePressed(MouseEvent e) {
        pantalla.pulsarRaton(e);
    }

    /** Método que redimensiona la pantalla. */
    @Override
    public void componentResized(ComponentEvent e) {
        pantalla.redimensionarPantalla();
    }

    /** Método para cambiar la pantalla */
    public void setPantalla(Interface pantalla) {
        this.pantalla = pantalla;
    }

    /** Método que define la acción del hilo */
    @Override
    public void run() {
        while (true) {
            pantalla.ejecutarFrame();
            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}