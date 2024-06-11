package codigoFIFO;

import java.util.LinkedList;
import java.util.Queue;

class Proceso {
    private String nombre;
    private int tiempoEjecucion;

    public Proceso(String nombre, int tiempoEjecucion) {
        this.nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }
}

class ProcesoEnTabla {
    private Proceso proceso;
    private int tiempoEspera;

    public ProcesoEnTabla(Proceso proceso, int tiempoEspera) {
        this.proceso = proceso;
        this.tiempoEspera = tiempoEspera;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }
}

class PlanificadorFIFO {
    private Queue<ProcesoEnTabla> colaProcesos;
    private int tiempoEsperaTotal;

    public PlanificadorFIFO() {
        colaProcesos = new LinkedList<>();
        tiempoEsperaTotal = 0;
    }

    public void agregarProceso(Proceso proceso) {
        colaProcesos.add(new ProcesoEnTabla(proceso, tiempoEsperaTotal));
        System.out.println("Proceso " + proceso.getNombre() + " agregado a la tabla de procesos. Tiempo de espera: " + tiempoEsperaTotal);
        tiempoEsperaTotal += proceso.getTiempoEjecucion();
    }

    public void ejecutarProcesos() {
        while (!colaProcesos.isEmpty()) {
            ProcesoEnTabla procesoActual = colaProcesos.poll();
            Proceso proceso = procesoActual.getProceso();
            System.out.println("Ejecutando proceso: " + proceso.getNombre() + ". Orden en el planificador: " + (colaProcesos.size() + 1));
            int tiempoEjecucion = proceso.getTiempoEjecucion();
            for (int i = 0; i < tiempoEjecucion; i++) {
                // Simulación de ejecución del proceso
                System.out.println("  Ejecutando instrucción " + (i + 1) + "/" + tiempoEjecucion + " de " + proceso.getNombre());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Proceso " + proceso.getNombre() + " completado.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        PlanificadorFIFO planificador = new PlanificadorFIFO();

        planificador.agregarProceso(new Proceso("Proceso1", 5));
        planificador.agregarProceso(new Proceso("Proceso2", 3));        planificador.agregarProceso(new Proceso("Proceso3", 7));        planificador.agregarProceso(new Proceso("Proceso4", 4));

        planificador.ejecutarProcesos();
    }
}

