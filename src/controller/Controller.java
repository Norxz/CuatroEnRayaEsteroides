package controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import model.*;
import view.*;

/**
 * Comunica y controla las acciones de la vista y el modelo.
 *
 * @author lokci
 */
public class Controller implements ActionListener {

    private Timer timer;
    private Bienvenida bien;
    private Seleccionar select;
    private Jugadores jug;
    private Estadisticas est;
    private Persona per;
    private Juego jue;
    private Info info;
    private Ficha ficha;
    private Tablero table;
    private Color color1, color2;
    private Partida par;
    private Player pla;
    private Stats stats;
    private int turno = 1;
    private int cont1 = 0, cont2 = 0;
    private int modalidadActual = 0;
    private int juegos = 0, ganadas = 0, empates = 0;

    private ArrayList<Player> jugAL = new ArrayList<>();

    public Controller() {
        bien = new Bienvenida();
        bien.getBotonInicio().addActionListener(this);
        bien.getBotonInfo().addActionListener(this);
        ficha = new Ficha();
        table = new Tablero();
        stats = new Stats();
        par = new Partida(table);
    }

    public void Controller(Bienvenida bien) {
        this.bien = bien;
        bien.getBotonInicio().addActionListener(this);
        bien.getBotonInfo().addActionListener(this);
    }

    public void Controller(Seleccionar select) {
        this.select = select;
        select.getBotonJCJ().addActionListener(this);
        select.getBotonJCM().addActionListener(this);
        select.getBotonMCM().addActionListener(this);
        select.getBotonSTATS().addActionListener(this);
        select.getBotonReturn().addActionListener(this);
    }

    public void Controller(Estadisticas est) {
        this.est = est;
        est.getRegresarButton().addActionListener(this);
    }

    public void Controller(Jugadores jug) {
        this.jug = jug;
        jug.getTFJ1().addActionListener(this);
        jug.getTFJ2().addActionListener(this);
        jug.getbColor1().addActionListener(this);
        jug.getbColor2().addActionListener(this);
        jug.getbJugar().addActionListener(this);
        jug.getbRegresar().addActionListener(this);
    }

    public void Controller(Juego jue) {
        this.jue = jue;
        jue.getRegresarBoton().addActionListener(this);
        jue.getRevanchaBoton().addActionListener(this);
        jue.getMenuBoton().addActionListener(this);
    }

    public void Controller(Info info) {
        this.info = info;
        info.getbRegresar().addActionListener(this);
    }

    public void Controller(Partida par) {
        this.par = par;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (bien != null && e.getSource() == bien.getBotonInicio()) {
            bien.dispose();
            select = new Seleccionar();
            Controller(select);
        }
        if (bien != null && e.getSource() == bien.getBotonInfo()) {
            bien.dispose();
            info = new Info();
            Controller(info);

        }
        if (info != null && e.getSource() == info.getbRegresar()) {
            info.dispose();
            bien = new Bienvenida();
            Controller(bien);

        }
        if (select != null && e.getSource() == select.getBotonJCJ()) {
            select.dispose();
            jug = new Jugadores();
            modalidadActual = 1;
            Controller(jug);

        }
        if (select != null && e.getSource() == select.getBotonJCM()) {
            select.dispose();
            jug = new Jugadores();
            modalidadActual = 2;
            Controller(jug);

        }
        if (select != null && e.getSource() == select.getBotonMCM()) {
            select.dispose();
            jug = new Jugadores();
            modalidadActual = 3;
            Controller(jug);
        }
        if (select != null && e.getSource() == select.getBotonSTATS()) {
            select.dispose();
            est = new Estadisticas();
            Controller(est);
        }
        if (select != null && e.getSource() == select.getBotonReturn()) {
            select.dispose();
            bien = new Bienvenida();
            Controller(bien);
        }
        if (jug != null && e.getSource() == jug.getbRegresar()) {

            jug.dispose();
            select = new Seleccionar();
            Controller(select);
        }
        if (est != null && e.getSource() == est.getRegresarButton()) {
            est.dispose();
            select = new Seleccionar();
            Controller(select);

        }
        if (jug != null && e.getSource() == jug.getbJugar()) {
            try {
                // Obtiene los nombres de los jugadores
                String nombreJugador1 = jug.getTFJ1().getText();
                String nombreJugador2 = jug.getTFJ2().getText();

                // Verifica la longitud de los nombres
                if (nombreJugador1.length() <= 10 && nombreJugador2.length() <= 10) {
                    // Los nombres tienen una longitud válida
                    //Crea pantalla
                    jug.dispose();
                    jue = new Juego();
                    Controller(jue);

                    //Cambia el color de los jugadores
                    color1 = jug.getbColor1().getBackground();
                    jue.setBackground(color1);
                    jue.getlColor1().setBackground(color1);
                    color2 = jug.getbColor2().getBackground();
                    jue.setBackground(color2);
                    jue.getlColor2().setBackground(color2);

                    //Cambia el nombre de los jugadores 
                    if (!jug.getTFJ1().getText().isEmpty()) {
                        jue.getlJugador1().setText(jug.getTFJ1().getText());
                    }
                    if (!jug.getTFJ2().getText().isEmpty()) {
                        jue.getlJugador2().setText(jug.getTFJ2().getText());
                    }

                    //Inicializa el tablero
                    jue.getpTurno().setBackground(jug.getbColor1().getBackground());
                    table.iniTablero(jue.getpTablero(), this);

                    //Crea el jugador
                    per = new Persona(jug.getTFJ1().getText(), juegos, ganadas, empates, per.getEfectividad());
                    jugAL.add(per);
                    //stats.configurarJugadoresList(jugAL);

                } else {
                    throw new Exception("El nombre de un jugador no puede tener más de 10 caracteres.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (jue != null && e.getSource() == jue.getRegresarBoton()) {
            jue.dispose();
            jug = new Jugadores();
            Controller(jug);
            turno = 1;
            cont1 = 0;
            cont2 = 0;
            if (modalidadActual == 3) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
            }

        }
        if (jue != null && e.getSource() == jue.getRevanchaBoton()) {

            table.vaciarTablero();
            table.activarTablero();

            turno = 1;
            cont1 = 0;
            cont2 = 0;
            jue.getRevanchaBoton().setVisible(false);
        }
        if (jue != null && e.getSource() == jue.getMenuBoton()) {
            jue.dispose();
            bien = new Bienvenida();
            Controller(bien);
            turno = 1;
            cont1 = 0;
            cont2 = 0;
            if (modalidadActual == 3) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
            }

        }
        if (jug != null && e.getSource() == jug.getbColor1()) {
            do {
                jug.getbColor1().setBackground(ficha.nextColor());
            } while (jug.getbColor1().getBackground().equals(jug.getbColor2().getBackground()));
        }

        if (jug != null && e.getSource() == jug.getbColor2()) {
            do {
                jug.getbColor2().setBackground(ficha.nextColor());
            } while (jug.getbColor2().getBackground().equals(jug.getbColor1().getBackground()));
        }

        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (jue != null && button.getParent() == jue.getpTablero()) {

                int columna = par.getColumnaDelBoton(button, table.getBotonesTablero());
                int fila = table.getFilaDisponible(columna);
                if (fila >= 0) {

                    switch (modalidadActual) {
                        case 1:
                            // modo Jugador contra Jugador
                            if (turno == 1) {
                                table.getBotonesTablero()[fila][columna].setBackground(jug.getbColor1().getBackground());
                                table.setFilaDisponible(columna, fila - 1);
                                jue.getpTurno().setBackground(jug.getbColor2().getBackground());

                                cont1++;
                                if (par.verificarGanador(fila, columna, jug.getbColor1().getBackground())) {
                                    JOptionPane.showMessageDialog(null, jue.getlJugador1().getText() + " ha ganado.", "Ganador", JOptionPane.PLAIN_MESSAGE);
                                    table.desactivarTablero();
                                    jue.getRevanchaBoton().setVisible(true);
                                }
                                turno = 2;
                            } else if (turno == 2) {

                                table.getBotonesTablero()[fila][columna].setBackground(jug.getbColor2().getBackground());
                                table.setFilaDisponible(columna, fila - 1);
                                jue.getpTurno().setBackground(jug.getbColor1().getBackground());
                                cont2++;
                                if (par.verificarGanador(fila, columna, jug.getbColor2().getBackground())) {
                                    JOptionPane.showMessageDialog(null, jue.getlJugador2().getText() + " ha ganado.", "Ganador", JOptionPane.PLAIN_MESSAGE);
                                    table.desactivarTablero();
                                    jue.getRevanchaBoton().setVisible(true);
                                }
                                turno = 1;
                            }
                            break;
                        case 2:
                            if (turno == 1) {

                                table.getBotonesTablero()[fila][columna].setBackground(jug.getbColor1().getBackground());
                                table.setFilaDisponible(columna, fila - 1);
                                jue.getpTurno().setBackground(jug.getbColor2().getBackground());
                                cont1++;
                                if (par.verificarGanador(fila, columna, jug.getbColor1().getBackground())) {
                                    JOptionPane.showMessageDialog(null, jue.getlJugador1().getText() + " ha ganado.", "Ganador", JOptionPane.PLAIN_MESSAGE);
                                    //juegoTerminado = true;
                                    jue.getRevanchaBoton().setVisible(true);
                                    return;
                                }
                                turno = 2;
                            }

                            int columnaAleatoria;
                            do {
                                columnaAleatoria = (int) (Math.random() * 7);
                            } while (table.getFilaDisponible(columnaAleatoria) < 0);

                            int filaAleatoria = table.getFilaDisponible(columnaAleatoria);

                            table.getBotonesTablero()[filaAleatoria][columnaAleatoria].setBackground(jug.getbColor2().getBackground());
                            table.setFilaDisponible(columnaAleatoria, filaAleatoria - 1);
                            jue.getpTurno().setBackground(jug.getbColor1().getBackground());
                            cont2++;
                            if (par.verificarGanador(filaAleatoria, columnaAleatoria, jug.getbColor2().getBackground())) {
                                JOptionPane.showMessageDialog(null, jue.getlJugador2().getText() + " ha ganado.", "Ganador", JOptionPane.PLAIN_MESSAGE);
                                jue.getRevanchaBoton().setVisible(true);
                            }

                            turno = 1;

                            break;
                        case 3:
                            //  temporizador para el modo Máquina contra Máquina
                            timer = new Timer(1000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    if (turno == 1) {

                                        int columnaAleatoria;
                                        do {
                                            columnaAleatoria = (int) (Math.random() * 7);
                                        } while (table.getFilaDisponible(columnaAleatoria) < 0);

                                        int filaAleatoria = table.getFilaDisponible(columnaAleatoria);

                                        table.getBotonesTablero()[filaAleatoria][columnaAleatoria].setBackground(jug.getbColor1().getBackground());
                                        table.setFilaDisponible(columnaAleatoria, filaAleatoria - 1);
                                        jue.getpTurno().setBackground(jug.getbColor1().getBackground());
                                        cont1++;

                                        if (par.verificarGanador(filaAleatoria, columnaAleatoria, jug.getbColor1().getBackground())) {
                                            JOptionPane.showMessageDialog(null, "Máquina 1 ha ganado.", "Ganador", JOptionPane.PLAIN_MESSAGE);
                                            jue.getRevanchaBoton().setVisible(true);
                                            if (timer != null && timer.isRunning()) {
                                                timer.stop();
                                            }
                                        }
                                        turno = 2;
                                    } else if (turno == 2) {
                                        int columnaAleatoria;
                                        do {
                                            columnaAleatoria = (int) (Math.random() * 7);
                                        } while (table.getFilaDisponible(columnaAleatoria) < 0);

                                        int filaAleatoria = table.getFilaDisponible(columnaAleatoria);

                                        table.getBotonesTablero()[filaAleatoria][columnaAleatoria].setBackground(jug.getbColor2().getBackground());
                                        table.setFilaDisponible(columnaAleatoria, filaAleatoria - 1);
                                        jue.getpTurno().setBackground(jug.getbColor2().getBackground());
                                        cont2++;
                                        if (par.verificarGanador(filaAleatoria, columnaAleatoria, jug.getbColor2().getBackground())) {
                                            JOptionPane.showMessageDialog(null, "Máquina 2 ha ganado.", "Ganador", JOptionPane.PLAIN_MESSAGE);
                                            jue.getRevanchaBoton().setVisible(true);
                                            if (timer != null && timer.isRunning()) {
                                                timer.stop();
                                            }
                                        }
                                        turno = 1;
                                    }
                                }
                            });
                            timer.start();
                            break;
                    }
                    // Verificar empate
                    if (cont1 + cont2 == 42) {
                        JOptionPane.showMessageDialog(null, "Empate", "Empate", JOptionPane.PLAIN_MESSAGE);
                        jue.getRevanchaBoton().setVisible(true);
                    }
                }
            }
        }

    }

}
