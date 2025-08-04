package com.garage.mundial.model;
/**
 * Representa el resultado de un partido
 */
public class Resultado {
    private int golesLocal;
    private int golesVisitante;

    public Resultado() {
    }

    public Resultado(int golesLocal, int golesVisitante) {
        this.golesLocal = Math.max(0, golesLocal);
        this.golesVisitante = Math.max(0, golesVisitante);
    }
    
    // Getters y Setters
    public int getGolesLocal() {
        return golesLocal;
    }
    
    public void setGolesLocal(int golesLocal) {
        this.golesLocal = Math.max(0, golesLocal);
    }
    
    public int getGolesVisitante() {
        return golesVisitante;
    }
    
    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = Math.max(0, golesVisitante);
    }
    
    /**
     * Verifica si ganó el equipo local
     */
    public boolean ganoLocal() {
        return golesLocal > golesVisitante;
    }
    
    /**
     * Verifica si el partido terminó en empate
     */
    public boolean empate() {
        return golesLocal == golesVisitante;
    }
    
    public int getDiferenciaGoles() {
        return golesLocal - golesVisitante;
    }
    
    public int getTotalGoles() {
        return golesLocal + golesVisitante;
    }
    
    @Override
    public String toString() {
        return golesLocal + " - " + golesVisitante;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Resultado resultado = (Resultado) obj;
        return golesLocal == resultado.golesLocal && golesVisitante == resultado.golesVisitante;
    }
    
    @Override
    public int hashCode() {
        return golesLocal * 31 + golesVisitante;
    }
}