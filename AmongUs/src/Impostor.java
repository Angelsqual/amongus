import java.util.LinkedList;

public class Impostor extends Xogador {
    private LinkedList<Xogador> eliminados;

    public Impostor(String alias) {
        super(alias);
        this.eliminados = new LinkedList<>();
    }

    // Método para registrar estudiantes eliminados
    public void registrarEliminado(Xogador eliminado) {
        this.eliminados.add(eliminado);
    }

    public LinkedList<Xogador> getEliminados() {
        return eliminados;
    }

    public void setEliminados(LinkedList<Xogador> eliminados) {
        this.eliminados = eliminados;
    }
}
