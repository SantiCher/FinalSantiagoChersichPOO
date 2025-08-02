import java.util.*;

/**
 * Representa una llave eliminatoria del mundial
 */
public class Llave extends EtapaMundial {

    public Llave() {
        super();
    }

    public Llave(String descripcionEtapa) {
        super(descripcionEtapa);
    }
    
    /**
     * Solo los equipos ganadores avanzan
     */
    @Override
    public List<Equipo> getEquiposQueAvanzan() {
        if (!estaCompleta()) {
            System.out.println("Advertencia: La llave " + descripcionEtapa + " no está completa");
            return new ArrayList<>();
        }
        
        List<Equipo> ganadores = new ArrayList<>();
        
        for (Partido partido : partidos) {
            Resultado resultado = partido.getResultado();
            
            if (resultado.ganoLocal()) {
                ganadores.add(partido.getLocal());
            } else if (!resultado.empate()) {
                ganadores.add(partido.getVisitante());
            } else {
                // En eliminatorias, si hay empate, avanza el local
                ganadores.add(partido.getLocal());
            }
        }
        
        return ganadores;
    }
    
    /**
     * Muestra los resultados de la llave
     */
    public void mostrarResultados() {
        System.out.println("\nRESULTADOS - " + descripcionEtapa);
        for (Partido partido : partidos) {
            if (partido.getResultado() != null) {
                Resultado resultado = partido.getResultado();
                String ganador;
                
                if (resultado.ganoLocal()) {
                    ganador = partido.getLocal().getNombre() + " ✓";
                } else if (!resultado.empate()) {
                    ganador = partido.getVisitante().getNombre() + " ✓";
                } else {
                    ganador = "EMPATE";
                }
                
                System.out.println(partido.getLocal().getNombre() + " " + 
                                 resultado.getGolesLocal() + " - " + 
                                 resultado.getGolesVisitante() + " " + 
                                 partido.getVisitante().getNombre() + 
                                 " | Ganador: " + ganador);
            }
        }
    }
}