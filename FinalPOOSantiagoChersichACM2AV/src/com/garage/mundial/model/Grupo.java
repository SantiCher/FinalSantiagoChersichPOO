package com.garage.mundial.model;
import java.util.*;

public class Grupo extends EtapaMundial {
    

    public Grupo() {
        super();
    }

    public Grupo(String descripcionEtapa) {
        super(descripcionEtapa);
    }

    @Override
    public List<Equipo> getEquiposQueAvanzan() {
        if (!estaCompleta()) {
            System.out.println("Advertencia: El grupo " + descripcionEtapa + " no está completo");
            return new ArrayList<>();
        }
        
        Map<Equipo, Integer> puntajes = calcularTablaPosiciones();
        
        // Convertir a lista y ordenar por puntaje
        List<Map.Entry<Equipo, Integer>> lista = new ArrayList<>(puntajes.entrySet());
        lista.sort(new Comparator<Map.Entry<Equipo, Integer>>() {
            @Override
            public int compare(Map.Entry<Equipo, Integer> e1, Map.Entry<Equipo, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        
        List<Equipo> clasificados = new ArrayList<>();
        for (int i = 0; i < Math.min(2, lista.size()); i++) {
            clasificados.add(lista.get(i).getKey());
        }
        
        return clasificados;
    }
    
    /**
     * Calcula la tabla de posiciones del grupo
     */
    public Map<Equipo, Integer> calcularTablaPosiciones() {
        Map<Equipo, Integer> puntajes = new HashMap<>();
        
        // Inicializar puntajes
        for (Partido partido : partidos) {
            puntajes.put(partido.getLocal(), 0);
            puntajes.put(partido.getVisitante(), 0);
        }
        
        // Calcular puntajes
        for (Partido partido : partidos) {
            if (partido.getResultado() != null) {
                Equipo local = partido.getLocal();
                Equipo visitante = partido.getVisitante();
                Resultado resultado = partido.getResultado();
                
                if (resultado.ganoLocal()) {
                    puntajes.put(local, puntajes.get(local) + 3);
                } else if (resultado.empate()) {
                    puntajes.put(local, puntajes.get(local) + 1);
                    puntajes.put(visitante, puntajes.get(visitante) + 1);
                } else {
                    puntajes.put(visitante, puntajes.get(visitante) + 3);
                }
            }
        }
        
        return puntajes;
    }
    
    /**
     * Muestra la tabla de posiciones
     */
    public void mostrarTablaPosiciones() {
        System.out.println("\nTABLA DE POSICIONES - " + descripcionEtapa);
        Map<Equipo, Integer> puntajes = calcularTablaPosiciones();
        
        // Convertir a lista y ordenar
        List<Map.Entry<Equipo, Integer>> lista = new ArrayList<>(puntajes.entrySet());
        lista.sort(new Comparator<Map.Entry<Equipo, Integer>>() {
            @Override
            public int compare(Map.Entry<Equipo, Integer> e1, Map.Entry<Equipo, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        
        int posicion = 1;
        for (Map.Entry<Equipo, Integer> entrada : lista) {
            String clasificacion = posicion <= 2 ? " CLASIFICA" : "";
            System.out.println(posicion + ". " + entrada.getKey().getNombre() + 
                             " - " + entrada.getValue() + " puntos" + clasificacion);
            posicion++;
        }
    }
}