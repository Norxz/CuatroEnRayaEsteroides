package model;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author lokci
 */
public class Partida {

    private Tablero tablero;
    private int[] filasDisponibles;

    public Partida(Tablero tablero) {
        this.tablero = tablero;

        filasDisponibles = new int[7];
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

    public boolean verificarGanador(int fila, int columna, Color color) {
        return (verificarHorizontal(fila, color)
                || verificarVertical(columna, color)
                || verificarDiagonalDerecha(fila, columna, color)
                || verificarDiagonalIzquierda(fila, columna, color));
    }

    private boolean verificarHorizontal(int fila, Color color) {
        int count = 0;
        for (int c = 0; c < 7; c++) {
            if (tablero.getTablero()[fila][c].getBackground().equals(color)) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean verificarVertical(int columna, Color color) {
        int count = 0;
        for (int f = 0; f < 6; f++) {
            if (tablero.getTablero()[f][columna].getBackground().equals(color)) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean verificarDiagonalDerecha(int fila, int columna, Color color) {
        int count = 0;
        for (int f = fila, c = columna; f < 6 && c < 7; f++, c++) {
            if (tablero.getTablero()[f][c].getBackground().equals(color)) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        for (int f = fila - 1, c = columna - 1; f >= 0 && c >= 0; f--, c--) {
            if (tablero.getTablero()[f][c].getBackground().equals(color)) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean verificarDiagonalIzquierda(int fila, int columna, Color color) {
        int count = 0;
        for (int f = fila, c = columna; f < 6 && c >= 0; f++, c--) {
            if (tablero.getTablero()[f][c].getBackground().equals(color)) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        for (int f = fila - 1, c = columna + 1; f >= 0 && c < 7; f--, c++) {
            if (tablero.getTablero()[f][c].getBackground().equals(color)) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

}
