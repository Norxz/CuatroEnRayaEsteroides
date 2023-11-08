package model;

/**
 *
 * @author lokci
 */
public abstract class Player{
    
    private final String nombre;
    private int juegos;
    private int ganadas;
    private int empates;
    private double efectividad;
    public Player(String nombre, int juegos, int ganadas, int empates, double efectividad) {
        
        this.nombre = nombre;
        this.juegos = juegos;
        this.ganadas = ganadas;
        this.empates = empates;
        this.efectividad = efectividad;
        
    }
    
    public abstract void jugar();
    
    public void calEfectividad(){
        
                
        efectividad = ganadas/juegos*100;
        
    }

    public String getNombre() {
        return nombre;
    }

    public int getJuegos() {
        return juegos;
    }

    public int getGanadas() {
        return ganadas;
    }

    public int getEmpates() {
        return empates;
    }

    public double getEfectividad() {
        return efectividad;
    }
    
}
