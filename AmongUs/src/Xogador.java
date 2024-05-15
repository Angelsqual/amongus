import java.util.ArrayList;
import java.util.List;

class Xogador {
    private String alias;
    private boolean impostor;
    private boolean eliminado;
    private List<Tarea> tareasRealizadas;
    private List<Tarea> tareasAsignadas;

    public Xogador(String alias) {
        this.alias = alias;
        this.impostor = false;
        this.eliminado = false;
        this.tareasRealizadas = new ArrayList<>();
    }

    public String getAlias() {
        return alias;
    }

    public boolean isImpostor() {
        return impostor;
    }

    public void setImpostor(boolean impostor) {
        this.impostor = impostor;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    public List<Tarea> getTareasRealizadas() {
        return tareasRealizadas;
    }
    public void realizarTarea(Tarea tarea) {
        tareasRealizadas.add(tarea);
    }

    public void setTareasRealizadas(List<Tarea> tareasRealizadas) {
        this.tareasRealizadas = tareasRealizadas;
    }
    public boolean haCompletadoTodasLasTareas(List<Tarea> tareas) {
        // Comprueba si todas las tareas de la lista dada han sido completadas por el jugador
        for (Tarea tarea : tareas) {
            if (!this.tareasRealizadas.contains(tarea)) {
                return false;
            }
        }
        return true;
    }
    public boolean puedeRealizarTarea(Tarea tarea) {
        // Verificar si la tarea no ha sido realizada previamente
        if (tareasRealizadas.contains(tarea)) {
            return false;
        }
    
        // Verificar si el tiempo estimado de la tarea es menor o igual al tiempo restante del estudiante
        int tiempoRestante = calcularTiempoRestante();
        return tarea.getTiempoEstimado() <= tiempoRestante;
    }
    
    private int calcularTiempoRestante() {
        // Calcula el tiempo restante del estudiante restando el tiempo total de la ronda
        // menos el tiempo total de las tareas realizadas
        int tiempoTotalRonda = AmongSanClemente.getInstancia().getTempoRonda();
        int tiempoTotalTareasRealizadas = 0;
        for (Tarea tarea : tareasRealizadas) {
            tiempoTotalTareasRealizadas += tarea.getTiempoEstimado();
        }
        return tiempoTotalRonda - tiempoTotalTareasRealizadas;
    }
    
    
    
}