import java.util.List;
import java.util.ArrayList;

/**
 * Representa un equipo participante en el mundial
 */
public class Equipo {
    private String nombre;
    private List<Partido> partidosJugados;

    public Equipo() {
        this.partidosJugados = new ArrayList<>();
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.partidosJugados = new ArrayList<>();
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<Partido> getPartidosJugados() {
        return partidosJugados;
    }
    
    public void addPartidoJugado(Partido partido) {
        if (partido != null && !partidosJugados.contains(partido)) {
            this.partidosJugados.add(partido);
        }
    }
    
    public int getCantidadPartidosJugados() {
        return partidosJugados.size();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return nombre != null ? nombre.equals(equipo.nombre) : equipo.nombre == null;
    }
    
    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return "Equipo{nombre='" + nombre + "'}";
    }
}