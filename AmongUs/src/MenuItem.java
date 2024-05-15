public class MenuItem implements MenuComponent {
    private String nombre;

    public MenuItem(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void engadirElemento(MenuComponent elemento) {
        // No se implementa para elementos individuales
    }

    @Override
    public void eliminarElemento(MenuComponent elemento) {
        // No se implementa para elementos individuales
    }

    @Override
    public void mostrar() {
        System.out.println(nombre);
    }
}
