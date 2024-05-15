import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class AmongSanClemente {
    private static AmongSanClemente instancia;
    private Scanner scanner;
    private List<Xogador> xogadores;
    private List<Tarea> tarefas;
    private int tempoRonda;
    private int rondaActual;
    private boolean juegoFinalizado;
    private boolean acusacionRealizada; // Nuevo campo para controlar si ya se realizó una acusación en la ronda actual

    private AmongSanClemente() {
        this.scanner = new Scanner(System.in);
        this.xogadores = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.tempoRonda = 30; // Tiempo por ronda en segundos (por ejemplo, 30 segundos)
        this.rondaActual = 0;
        this.juegoFinalizado = false;
        this.acusacionRealizada = false; // Inicialmente no se ha realizado ninguna acusación
    }

    // Implementación del patrón Singleton
    public static AmongSanClemente getInstancia() {
        if (instancia == null) {
            instancia = new AmongSanClemente();
        }
        return instancia;
    }
    public int getTempoRonda() {
        return tempoRonda; // Devuelve el tiempo por ronda en segundos
    }

    // Métodos para gestionar el juego (por ejemplo, iniciar partida, realizar acciones, etc.)

    public void inicializarTareas() {
        // Agrega todas las tareas necesarias con su tiempo estimado
        this.tarefas.add(new Tarea("Arranxar o proxector", "Aula da bodega", 5));
        this.tarefas.add(new Tarea("Reparar la impresora", "Sala de profesores", 8));
        this.tarefas.add(new Tarea("Recoger xices", "Conserxería", 6));
        this.tarefas.add(new Tarea("Limpar o lab", "Laboratorio", 10));
        this.tarefas.add(new Tarea("Sacar a basura", "Patio", 4));
        this.tarefas.add(new Tarea("Preparar o material", "Taller de tecnoloxía", 7));
        this.tarefas.add(new Tarea("Buscar un libro", "Biblioteca", 5));
        this.tarefas.add(new Tarea("Preparar un experimento", "Laboratorio", 9));
    }

    public void engadirXogadorPrincipal() {
        // Solicita al usuario que ingrese su alias
        System.out.println("Ingresa tu alias:");
        String alias = scanner.nextLine();
        Xogador xogadorPrincipal = new Xogador(alias);
        this.xogadores.add(xogadorPrincipal);
    }

    public void engadirXogadoresAleatorios() {
        // Genera 9 nombres aleatorios para los estudiantes
        List<String> nombresAleatorios = generarNombresAleatorios(9);

        // Crea a los estudiantes con los nombres aleatorios generados
        for (String nombre : nombresAleatorios) {
            Xogador estudiante = new Xogador(nombre);
            this.xogadores.add(estudiante);
        }
    }

    private List<String> generarNombresAleatorios(int cantidad) {
        List<String> nombres = new ArrayList<>();
        Random random = new Random();
    
        for (int i = 0; i < cantidad; i++) {
            StringBuilder nombre = new StringBuilder();
            nombre.append("@"); // Agrega el carácter '@' al inicio del alias
    
            // Genera una secuencia de tres caracteres alfanuméricos aleatorios
            for (int j = 0; j < 3; j++) {
                char caracter = (char) (random.nextInt(26) + 'A'); // Letra mayúscula aleatoria
                nombre.append(caracter);
            }
    
            nombres.add(nombre.toString());
        }
    
        return nombres;
    }
    
    public void configurarImpostores() {
        // Selecciona aleatoriamente a algunos jugadores y los marca como impostores
        // Por ejemplo, seleccionamos aleatoriamente a 2 jugadores
        Collections.shuffle(this.xogadores); // Mezclamos la lista de jugadores
        for (int i = 0; i < 2; i++) {
            Xogador impostor = this.xogadores.get(i);
            impostor.setImpostor(true);
        }
    }

    private void verificarFinPartida() {
        int cantidadEstudiantesVivos = 0;
        int cantidadImpostoresVivos = 0;
    
        // Verificar la cantidad de estudiantes e impostores vivos
        for (Xogador xogador : this.xogadores) {
            if (!xogador.isEliminado()) {
                if (xogador.isImpostor()) {
                    cantidadImpostoresVivos++;
                } else {
                    cantidadEstudiantesVivos++;
                }
            }
        }
        // Comprobar las condiciones de finalización del juego
        if (cantidadImpostoresVivos == 0) {
         juegoFinalizado = true;
         System.out.println("¡Todos los impostores han sido eliminados! ¡Victoria para los estudiantes!");
       } else if (cantidadEstudiantesVivos == 0 || cantidadImpostoresVivos >= cantidadEstudiantesVivos) {
         juegoFinalizado = true;
        if (cantidadEstudiantesVivos == 0) {
         System.out.println("¡Todos los estudiantes han sido eliminados! ¡Victoria para los impostores!");
           } else {
              System.out.println("¡El número de impostores es mayor o igual que el número de estudiantes vivos! ¡Victoria para los impostores!");
                    }
              } else {
             // Permitir que los jugadores realicen acusaciones si hay al menos un impostor vivo y jugadores no eliminados
              if (cantidadImpostoresVivos > 0 && cantidadEstudiantesVivos > 0) {
        acusarImpostor();
                } else {
        avanzarSiguienteRonda(); // Avanzar a la siguiente ronda si no hay impostores vivos
         }
             }

    }
    

    private void acusarImpostor() {
        mostrarJugadoresVivos();
        System.out.println("¿A quién quieres acusar como impostor? (Ingresa el alias)");
        String aliasAcusado = scanner.nextLine();
        for (Xogador xogador : this.xogadores) {
            if (xogador.getAlias().equals(aliasAcusado)) {
                if (xogador.isImpostor()) {
                    System.out.println("¡Has acertado! El jugador " + aliasAcusado + " era un impostor. ¡Victoria para los estudiantes!");
                    xogador.setEliminado(true); // Eliminar al impostor
                    acusacionRealizada = true; // Marcar que se realizó una acusación en esta ronda
                } else {
                    System.out.println("¡Oops! El jugador " + aliasAcusado + " no era un impostor.");
                    acusacionRealizada = true; // Marcar que se realizó una acusación en esta ronda
                }
                return;
            }
        }
        System.out.println("No se encontró ningún jugador con el alias " + aliasAcusado + ". Por favor, intenta de nuevo.");
    }

    private void avanzarSiguienteRonda() {
        // Reiniciar el estado de la acusación para la próxima ronda
        acusacionRealizada = false;
        // Avanzar a la siguiente ronda
        this.rondaActual++;
    }

    public void xogar() {
        while (!juegoFinalizado) {
            this.rondaActual++;
            System.out.println("=== Ronda " + this.rondaActual + " ===");

            // Mostrar las tareas al inicio de la ronda
            mostrarTareas();

            // Realiza las tareas asignadas en cada ronda
            realizarTareasPorRonda();

            mostrarTareasRealizadas();

            // Simula intentos de asesinato entre los impostores
            simularIntentosDeAsesinato();

            // Verificar si la partida ha terminado
            verificarFinPartida();
            
            // Mostrar la lista de jugadores vivos
            mostrarJugadoresVivos();
        }
        scanner.close(); // Cerrar el scanner al finalizar el juego
    }

    private void mostrarTareas() {
        for (Tarea tarea : this.tarefas) {
            System.out.println("Tarea: " + tarea.getNombre() + ", Lugar: " + tarea.getHabitacion());
        }
    }

    private void mostrarTareasRealizadas() {
        System.out.println("Tareas realizadas por cada estudiante:");
        for (Xogador xogador : this.xogadores) {
            if (!xogador.isEliminado()) {
                // Mostrar las tareas realizadas por cada estudiante
                System.out.println("- " + xogador.getAlias() + ": " + xogador.getTareasRealizadas());
            }
        }
    }

    private void mostrarJugadoresVivos() {
        System.out.println("Jugadores vivos:");
        for (Xogador xogador : this.xogadores) {
            if (!xogador.isEliminado()) {
                System.out.println("- " + xogador.getAlias());
            }
        }
    }
    private void realizarTareasPorRonda() {
        // Copiar la lista de tareas para que podamos eliminar las tareas realizadas
        List<Tarea> tareasDisponibles = new ArrayList<>(this.tarefas);
    
        // Asignar tareas aleatoriamente a los jugadores y eliminarlas de la lista de tareas disponibles
        for (Xogador xogador : this.xogadores) {
            if (!xogador.isEliminado() && !xogador.isImpostor()) {
                while (!tareasDisponibles.isEmpty()) {
                    Collections.shuffle(tareasDisponibles); // Mezclar las tareas disponibles
                    Tarea tareaAsignada = tareasDisponibles.get(0); // Tomar la primera tarea de la lista mezclada
                    if (xogador.puedeRealizarTarea(tareaAsignada)) {
                        xogador.getTareasRealizadas().add(tareaAsignada); // Agregar la tarea al jugador
                        tareasDisponibles.remove(tareaAsignada); // Eliminar la tarea de la lista de tareas disponibles
                    } else {
                        break; // Si el estudiante no puede realizar la tarea, salimos del bucle
                    }
                }
            }
        }
    }
    
    
    private void simularIntentosDeAsesinato() {
        Random random = new Random();
        for (Xogador xogador : this.xogadores) {
            if (xogador.isImpostor() && !xogador.isEliminado()) {
                Xogador victima;
                do {
                    victima = this.xogadores.get(random.nextInt(this.xogadores.size()));
                } while (victima == xogador || victima.isImpostor() || victima.isEliminado());
                
                // Eliminar al jugador víctima
                victima.setEliminado(true);
                
                // Mostrar el resultado del intento de asesinato
                System.out.println("El impostor " + xogador.getAlias() + " elimina al estudiante " + victima.getAlias());
            }
        }
    }

    public void mostrarMenu() {
        MenuComponent engadirTarefa = new MenuItem("Engadir tarefa");
        MenuComponent borrarTarefa = new MenuItem("Borrar tarefa");
        MenuComponent verTarefas = new MenuItem("Ver tarefas");
        MenuComponent sairTarefas = new MenuItem("Sair");
    
        MenuComponent tarefas = new Menu("Tarefas");
        tarefas.engadirElemento(engadirTarefa);
        tarefas.engadirElemento(borrarTarefa);
        tarefas.engadirElemento(verTarefas);
        tarefas.engadirElemento(sairTarefas);
    
        // Repite el mismo proceso para los demás elementos del menú
    
        MenuComponent menuPrincipal = new Menu("Menu Principal");
        menuPrincipal.engadirElemento(tarefas);
        // Engadir otros elementos de menú al menú principal
    
        menuPrincipal.mostrar();
    }


    public static void main(String[] args) {
        AmongSanClemente juego = AmongSanClemente.getInstancia();
        juego.mostrarMenu();
        juego.inicializarTareas();
        juego.engadirXogadorPrincipal();
        juego.engadirXogadoresAleatorios();
        juego.configurarImpostores();
        juego.xogar();
    }
}
