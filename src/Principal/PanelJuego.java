package Principal;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * Clase Panel Juego.
 */

public class PanelJuego extends JPanel implements Runnable, MouseListener, MouseMotionListener, ComponentListener {
    /** Atributos de la clase */
    private static final long serialVersionUID = 1L;

    /** Constructor */
    public PanelJuego() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
    public void componentResized(ComponentEvent e) {
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

    /** Método para cambiar la pantalla */
    public void setPantalla(Interface pantalla) {
    }

    /** Método que define la acción del hilo */
    @Override
    public void run() {
        while (true) {
            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }
}