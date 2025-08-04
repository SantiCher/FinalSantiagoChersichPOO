package com.garage.mundial.model;
import java.util.Date;

/**
 * Representa un partido entre dos equipos
 */
public class Partido {
    private Date fecha;
    private Equipo local;
    private Equipo visitante;
    private Resultado resultado;

    public Partido() {
    }

    public Partido(Date fecha, Equipo local, Equipo visitante) {
        this.fecha = fecha;
        this.local = local;
        this.visitante = visitante;
        
        // Agregar el partido al historial de ambos equipos
        if (local != null) local.addPartidoJugado(this);
        if (visitante != null) visitante.addPartidoJugado(this);
    }

    public Partido(Date fecha, Equipo local, Equipo visitante, Resultado resultado) {
        this(fecha, local, visitante);
        this.resultado = resultado;
    }
    
    // Getters y Setters
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Equipo getLocal() {
        return local;
    }
    
    public void setLocal(Equipo local) {
        this.local = local;
        if (local != null) local.addPartidoJugado(this);
    }
    
    public Equipo getVisitante() {
        return visitante;
    }
    
    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
        if (visitante != null) visitante.addPartidoJugado(this);
    }
    
    public Resultado getResultado() {
        return resultado;
    }
    
    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
    
    /**
     * Verifica si el partido ya fue jugado
     */
    public boolean fueJugado() {
        return resultado != null;
    }
    
    /**
     * Obtiene el equipo ganador
     */
    public Equipo getGanador() {
        if (resultado == null) return null;
        
        if (resultado.ganoLocal()) {
            return local;
        } else if (!resultado.empate()) {
            return visitante;
        }
        return null; // Empate
    }
    
    @Override
    public String toString() {
        String equipos = (local != null ? local.getNombre() : "TBD") + " vs " + 
                        (visitante != null ? visitante.getNombre() : "TBD");
        String res = resultado != null ? " (" + resultado + ")" : " (Por jugar)";
        
        return equipos + res;
    }
}