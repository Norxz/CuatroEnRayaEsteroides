package model;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class Tablero {

    private BotonRedondo[][] tablero;
    private int[] filasDisponibles;
    private JButton[][] botonesTablero;
    private int turno;
    private int cont2;
    private int cont1;
    private boolean juegoTerminado;

    public void iniTablero(JPanel pTablero, ActionListener actionListener) {
        tablero = new BotonRedondo[6][7];
        filasDisponibles = new int[7];
        botonesTablero = new JButton[6][7];

        Color customColorBlue = new Color(133, 157, 235);
        pTablero.setBackground(customColorBlue);

        for (int columna = 0; columna < filasDisponibles.length; columna++) {
            filasDisponibles[columna] = 5;
        }

        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[fila].length; columna++) {
                tablero[fila][columna] = new BotonRedondo();
                tablero[fila][columna].addActionListener(actionListener);
                botonesTablero[fila][columna] = tablero[fila][columna];
                pTablero.add(tablero[fila][columna]);
            }
        }
    }

    public void vaciarTablero() {
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < filasDisponibles.length; columna++) {
                filasDisponibles[columna] = 5;
                tablero[fila][columna].setBackground(Color.WHITE);
            }
        }

    }

    public int getColumnaDelBoton(JButton button, JButton[][] botonesTablero) {
        for (int fila = 0; fila < botonesTablero.length; fila++) {
            for (int columna = 0; columna < botonesTablero[fila].length; columna++) {
                if (botonesTablero[fila][columna] == button) {
                    return columna;
                }
            }
        }
        return -1; // Botón no encontrado en el tablero
    }

    public void desactivarTablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tablero[i][j].setEnabled(false);
            }
        }
    }

    public void activarTablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tablero[i][j].setEnabled(true);
            }
        }
    }

    public boolean tableroLleno() {
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < tablero[fila].length; columna++) {
                if (filasDisponibles[columna] < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getFilaDisponible(int columna) {
        return filasDisponibles[columna];
    }

    public JButton[][] getBotonesTablero() {
        return botonesTablero;
    }

    public void setFilaDisponible(int columna, int fila) {
        filasDisponibles[columna] = fila;
    }

    public BotonRedondo[][] getTablero() {
        return tablero;
    }

}

class BotonRedondo extends JButton {

    public BotonRedondo() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(50, 50)); // Personaliza el tamaño del botón
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(getBackground());
        } else {
            g.setColor(getBackground());
        }
        int radio = Math.min(getSize().width, getSize().height);
        g.fillRoundRect(0, 0, getSize().width, getSize().height, radio, radio);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(java.awt.Graphics g) {
        g.setColor(getForeground());
        int radio = Math.min(getSize().width, getSize().height);
        g.drawRoundRect(0, 0, getSize().width, getSize().height, radio, radio);
    }
}
