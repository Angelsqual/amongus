import java.util.ArrayList;
import java.util.List;

public class Menu implements MenuComponent {
    private String nombre;
    private List<MenuComponent> elementos;

    public Menu(String nombre) {
        this.nombre = nombre;
        this.elementos = new ArrayList<>();
    }

    @Override
    public void engadirElemento(MenuComponent elemento) {
        elementos.add(elemento);
    }

    @Override
    public void eliminarElemento(MenuComponent elemento) {
        elementos.remove(elemento);
    }

    @Override
    public void mostrar() {
        System.out.println(nombre);
        for (MenuComponent elemento : elementos) {
            elemento.mostrar();
        }
    }
}
