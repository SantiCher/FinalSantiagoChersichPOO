import java.util.*;

public class MundialCompleto {
    
    public static void main(String[] args) {
        System.out.println("MUNDIAL DE FÚTBOL\n");
        
        // Crear los 32 equipos participantes
        List<Equipo> equipos = crearEquipos32();
        
        // PRIMERA ETAPA: FASE DE GRUPOS
        System.out.println("PRIMERA ETAPA: FASE DE GRUPOS");
        List<Grupo> grupos = crearFaseGrupos(equipos);
        List<Equipo> clasificadosGrupos = simularFaseGrupos(grupos);
        
        System.out.println("\nCLASIFICADOS A OCTAVOS: " + clasificadosGrupos.size() + " equipos");
        mostrarClasificados(clasificadosGrupos);
        
        // SEGUNDA ETAPA: FASE ELIMINATORIA
        System.out.println("\nSEGUNDA ETAPA: FASE ELIMINATORIA");
        
        // Octavos de Final
        System.out.println("\nOCTAVOS DE FINAL");
        Llave octavos = crearOctavos(clasificadosGrupos);
        List<Equipo> clasificadosOctavos = simularLlave(octavos);
        
        // Cuartos de Final
        System.out.println("\nCUARTOS DE FINAL");
        Llave cuartos = crearCuartos(clasificadosOctavos);
        List<Equipo> clasificadosCuartos = simularLlave(cuartos);
        
        // Semifinales
        System.out.println("\nSEMIFINALES");
        Llave semifinales = crearSemifinales(clasificadosCuartos);
        List<Equipo> finalistas = simularLlave(semifinales);
        
        // Final
        System.out.println("\nFINAL DEL MUNDIAL");
        Llave finalMundial = crearFinal(finalistas);
        List<Equipo> campeon = simularLlave(finalMundial);

    }
    
    /**
     * Crea los 32 equipos participantes del mundial
     */
    private static List<Equipo> crearEquipos32() {
        String[] nombresEquipos = {
            // Grupo A
            "Qatar", "Ecuador", "Senegal", "Países Bajos",
            // Grupo B  
            "Inglaterra", "Irán", "Estados Unidos", "Gales",
            // Grupo C
            "Argentina", "Arabia Saudita", "México", "Polonia",
            // Grupo D
            "Francia", "Australia", "Dinamarca", "Túnez",
            // Grupo E
            "España", "Costa Rica", "Alemania", "Japón",
            // Grupo F
            "Bélgica", "Canadá", "Marruecos", "Croacia",
            // Grupo G
            "Brasil", "Serbia", "Suiza", "Camerún",
            // Grupo H
            "Portugal", "Ghana", "Uruguay", "Corea del Sur"
        };
        
        List<Equipo> equipos = new ArrayList<>();
        for (String nombre : nombresEquipos) {
            equipos.add(new Equipo(nombre));
        }
        
        System.out.println("Creados " + equipos.size() + " equipos participantes");
        return equipos;
    }
    
    /**
     * Crea los 8 grupos de la fase inicial
     */
    private static List<Grupo> crearFaseGrupos(List<Equipo> equipos) {
        List<Grupo> grupos = new ArrayList<>();
        char[] letrasGrupos = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        
        for (int i = 0; i < 8; i++) {
            String nombreGrupo = "Grupo " + letrasGrupos[i];
            Grupo grupo = new Grupo(nombreGrupo);
            
            // Tomar 4 equipos para cada grupo
            List<Equipo> equiposGrupo = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                equiposGrupo.add(equipos.get(i * 4 + j));
            }
            
            // Crear partidos del grupo (todos contra todos = 6 partidos por grupo)
            for (int j = 0; j < equiposGrupo.size(); j++) {
                for (int k = j + 1; k < equiposGrupo.size(); k++) {
                    Partido partido = new Partido(
                        new Date(),
                        equiposGrupo.get(j),
                        equiposGrupo.get(k)
                    );
                    grupo.addPartido(partido);
                }
            }
            
            grupos.add(grupo);
        }
        
        System.out.println("Creados " + grupos.size() + " grupos con " +
                          (grupos.get(0).getCantidadPartidos()) + " partidos cada uno");
        return grupos;
    }
    
    /**
     * Simula toda la fase de grupos
     */
    private static List<Equipo> simularFaseGrupos(List<Grupo> grupos) {
        List<Equipo> todosLosClasificados = new ArrayList<>();
        
        for (Grupo grupo : grupos) {
            System.out.println("\n" + grupo.getDescripcionEtapa());
            
            // Simular todos los partidos del grupo
            for (Partido partido : grupo.getPartidos()) {
                Resultado resultado = generarResultadoAleatorio();
                partido.setResultado(resultado);
                System.out.println("  " + partido);
            }
            
            // Mostrar tabla de posiciones
            grupo.mostrarTablaPosiciones();
            
            // Obtener los 2 equipos que avanzan
            List<Equipo> clasificados = grupo.getEquiposQueAvanzan();
            todosLosClasificados.addAll(clasificados);
            
            System.out.println("Clasifican: " + clasificados.get(0).getNombre() + " y " + clasificados.get(1).getNombre());
        }
        
        return todosLosClasificados;
    }
    
    /**
     * Crea los octavos de final
     */
    private static Llave crearOctavos(List<Equipo> clasificados) {
        Llave octavos = new Llave("Octavos de Final");
        
        // Crear 8 partidos de octavos
        for (int i = 0; i < clasificados.size(); i += 2) {
            if (i + 1 < clasificados.size()) {
                Partido partido = new Partido(
                    new Date(),
                    clasificados.get(i),
                    clasificados.get(i + 1)
                );
                octavos.addPartido(partido);
            }
        }
        
        return octavos;
    }
    
    /**
     * Crea los cuartos de final
     */
    private static Llave crearCuartos(List<Equipo> clasificados) {
        Llave cuartos = new Llave("Cuartos de Final");
        
        for (int i = 0; i < clasificados.size(); i += 2) {
            if (i + 1 < clasificados.size()) {
                Partido partido = new Partido(
                    new Date(),
                    clasificados.get(i),
                    clasificados.get(i + 1)
                );
                cuartos.addPartido(partido);
            }
        }
        
        return cuartos;
    }
    
    /**
     * Crea las semifinales
     */
    private static Llave crearSemifinales(List<Equipo> clasificados) {
        Llave semifinales = new Llave("Semifinales");
        
        for (int i = 0; i < clasificados.size(); i += 2) {
            if (i + 1 < clasificados.size()) {
                Partido partido = new Partido(
                    new Date(),
                    clasificados.get(i),
                    clasificados.get(i + 1)
                );
                semifinales.addPartido(partido);
            }
        }
        
        return semifinales;
    }
    
    /**
     * Crea la final
     */
    private static Llave crearFinal(List<Equipo> finalistas) {
        Llave finalMundial = new Llave("Final");
        
        if (finalistas.size() >= 2) {
            Partido partido = new Partido(
                new Date(),
                finalistas.get(0),
                finalistas.get(1)
            );
            finalMundial.addPartido(partido);
        }
        
        return finalMundial;
    }
    
    /**
     * Simula una llave eliminatoria completa
     */
    private static List<Equipo> simularLlave(Llave llave) {
        System.out.println("Partidos de " + llave.getDescripcionEtapa() + ":");
        
        // Simular todos los partidos
        for (Partido partido : llave.getPartidos()) {
            Resultado resultado = generarResultadoEliminatorio();
            partido.setResultado(resultado);
            
            String ganador = "";
            if (resultado.ganoLocal()) {
                ganador = " Ganó " + partido.getLocal().getNombre();
            } else {
                ganador = " Ganó " + partido.getVisitante().getNombre();
            }
            
            System.out.println("  " + partido + ganador);
        }
        
        // Obtener equipos que avanzan
        List<Equipo> clasificados = llave.getEquiposQueAvanzan();
        
        if (llave.getDescripcionEtapa().equals("Final")) {
            System.out.println("\nCAMPEÓN DEL MUNDIAL: " + clasificados.get(0).getNombre());
        } else {
            System.out.println("\nClasifican " + clasificados.size() + " equipos:");
            for (Equipo equipo : clasificados) {
                System.out.println(". " + equipo.getNombre());
            }
        }
        
        return clasificados;
    }
    
    /**
     * Muestra la lista de equipos clasificados
     */
    private static void mostrarClasificados(List<Equipo> clasificados) {
        System.out.println("Equipos clasificados a octavos de final:");
        for (int i = 0; i < clasificados.size(); i++) {
            System.out.println((i + 1) + ". " + clasificados.get(i).getNombre());
        }
    }
    
    /**
     * Genera resultado aleatorio para fase de grupos
     */
    private static Resultado generarResultadoAleatorio() {
        Random random = new Random();
        int golesLocal = random.nextInt(4); // 0-3 goles
        int golesVisitante = random.nextInt(4); // 0-3 goles
        return new Resultado(golesLocal, golesVisitante);
    }
    
    /**
     * Genera resultado para eliminatorias (sin empates)
     */
    private static Resultado generarResultadoEliminatorio() {
        Random random = new Random();
        int golesLocal = random.nextInt(3) + 1; // 1-3 goles
        int golesVisitante = random.nextInt(3); // 0-2 goles
        
        // Asegurar que no haya empate
        if (golesLocal == golesVisitante) {
            if (random.nextBoolean()) {
                golesLocal++;
            } else {
                golesVisitante = Math.max(0, golesVisitante - 1);
            }
        }
        
        return new Resultado(golesLocal, golesVisitante);
    }

}