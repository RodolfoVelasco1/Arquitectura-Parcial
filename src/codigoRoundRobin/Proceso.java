package codigoRoundRobin;

import java.util.LinkedList;
import java.util.Queue;

class Proceso {
    private String nombre;
    private int tiempoEjecucion;
    private int tiempoRestante;

    public Proceso(String nombre, int tiempoEjecucion) {
        this.nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
        this.tiempoRestante = tiempoEjecucion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void ejecutar(int quantum) {
        if (tiempoRestante > quantum) {
            tiempoRestante -= quantum;
        } else {
            tiempoRestante = 0;
        }
    }
}

class PlanificadorRR {
    private Queue<Proceso> colaProcesos;
    private int quantum;

    public PlanificadorRR(int quantum) {
        this.colaProcesos = new LinkedList<>();
        this.quantum = quantum;
    }

    public void agregarProceso(Proceso proceso) {
        colaProcesos.add(proceso);
        System.out.println("Proceso " + proceso.getNombre() + " agregado a la cola de procesos.");
    }

    public void ejecutarProcesos() {
        while (!colaProcesos.isEmpty()) {
            Proceso procesoActual = colaProcesos.poll();
            System.out.println("Ejecutando proceso: " + procesoActual.getNombre() + ". Tiempo restante: " + procesoActual.getTiempoRestante());

            procesoActual.ejecutar(quantum);

            if (procesoActual.getTiempoRestante() > 0) {
                colaProcesos.add(procesoActual);
                System.out.println("Proceso " + procesoActual.getNombre() + " no completado. Tiempo restante: " + procesoActual.getTiempoRestante() + ". Se reinsertará en la cola.");
            } else {
                System.out.println("Proceso " + procesoActual.getNombre() + " completado.");
            }

            // Simulación de quantum
            try {
                Thread.sleep(quantum * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        PlanificadorRR planificador = new PlanificadorRR(2); // Quantum de 2 segundos

        planificador.agregarProceso(new Proceso("Proceso1", 5));
        planificador.agregarProceso(new Proceso("Proceso2", 3));
        planificador.agregarProceso(new Proceso("Proceso3", 7));
        planificador.agregarProceso(new Proceso("Proceso4", 4));

        planificador.ejecutarProcesos();
    }
}

