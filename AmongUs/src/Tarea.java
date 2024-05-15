public class Tarea {
    private String nombre;
    private String habitacion;
    private int tempo;

    public Tarea(String nombre, String habitacion, int tempo) {
        this.nombre = nombre;
        this.habitacion = habitacion;
        this.tempo = tempo;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getDuracion() {
        return tempo;
    }
    public int getTiempoEstimado() {
        return tempo; // Devuelve el tiempo estimado de la tarea
    }

    @Override
    public String toString() {
        return nombre + " (" + habitacion + ")";
    }
}
