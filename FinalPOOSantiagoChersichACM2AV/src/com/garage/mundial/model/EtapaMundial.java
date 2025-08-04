package com.garage.mundial.model;
import java.util.List;
import java.util.ArrayList;

/**
 * Representa una etapa del mundial
 */
public abstract class EtapaMundial {
    protected String descripcionEtapa;
    protected List<Partido> partidos;

    public EtapaMundial() {
        this.partidos = new ArrayList<>();
    }

    public EtapaMundial(String descripcionEtapa) {
        this.descripcionEtapa = descripcionEtapa;
        this.partidos = new ArrayList<>();
    }
    
    // Getters y Setters
    public String getDescripcionEtapa() {
        return descripcionEtapa;
    }
    
    public void setDescripcionEtapa(String descripcionEtapa) {
        this.descripcionEtapa = descripcionEtapa;
    }
    
    public List<Partido> getPartidos() {
        return partidos;
    }
    
    public void addPartido(Partido partido) {
        if (partido != null) {
            this.partidos.add(partido);
        }
    }

    public abstract List<Equipo> getEquiposQueAvanzan();
    
    /**
     * Verifica si todos los partidos han sido jugados
     */
    public boolean estaCompleta() {
        for (Partido partido : partidos) {
            if (partido.getResultado() == null) {
                return false;
            }
        }
        return true;
    }
    
    public int getCantidadPartidos() {
        return partidos.size();
    }
    
    @Override
    public String toString() {
        return descripcionEtapa + " (" + partidos.size() + " partidos)";
    }
}