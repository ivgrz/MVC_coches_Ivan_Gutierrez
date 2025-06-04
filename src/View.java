import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase encargada de la interacción con el usuario
 */
public class View implements Observer {
    private final Controller controller;
    private final Scanner sc = new Scanner(System.in);

    public View(Controller controller) {
        this.controller = controller;
    }
    /**
     * Muestra la velocidad de un coche
     * @param matricula del coche
     * @param v velocidad
     * @return true si se ha mostrado correctamente
     */
    public boolean muestraVelocidad(String matricula, Integer v){
        System.out.println(matricula + ": " + v + "km/hr");
        return true;
    }
    public void mostrarTodosLosCoches(ArrayList<Coche> coches) {
        System.out.println("--- Lista de Coches ---");
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            for (Coche coche : coches) {
                System.out.println(coche);
            }
        }
    }
    public void menu() {

        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");

            System.out.println("1. Aumentar velocidad");
            System.out.println("2. Disminuir velocidad");
            System.out.println("3. Mostrar gasolina actual");
            System.out.println("4. Avanzar");
            System.out.println("5. Repostar gasolina");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    modificarVelocidad(true);
                    break;
                case 2:
                    modificarVelocidad(false);
                    break;
                case 3:
                    mostrarGasolina();
                    break;
                case 4:
                    avanzar();
                    break;
                case 5:
                    repostar();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void modificarVelocidad(boolean aumentar) {

        System.out.print("Introduce la matrícula del coche: ");
        String matricula = sc.nextLine().trim();

        System.out.print("Introduce el valor a " + (aumentar ? "aumentar" : "disminuir") + ": ");
        int valor = sc.nextInt();

        if (aumentar) {
            controller.aumentarVelocidad(matricula, valor);
        } else {
            controller.disminuirVelocidad(matricula, valor);
        }
    }



    private void mostrarGasolina() {
        int gasolina = controller.consultarGasolina();
        System.out.println("Gasolina actual: " + gasolina + " litros.");
    }

    private void avanzar() {
        System.out.print("Introduce los metros a avanzar: ");
        int metros = sc.nextInt();

        System.out.print("Introduce la velocidad actual (km/h): ");
        int velocidad = sc.nextInt();

        controller.avanzar(metros, velocidad);
    }

    private void repostar() {
        System.out.print("Introduce los litros de gasolina a repostar: ");
        int litros = sc.nextInt();

        controller.Gasolina(litros);
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    @Override
    public void actualizarGasolina(String mensaje) {
        System.out.println("Alerta de Gasolina: " + mensaje);



        String[] partesDelMensaje = mensaje.split(":");


        String textoDelNivel = partesDelMensaje[1].trim();
        int nivelDeGasolina = Integer.parseInt(textoDelNivel);


        if (nivelDeGasolina < 10) {
            System.out.println("[AVISO] Quedan " + nivelDeGasolina + " litros de gasolina. Recomendado repostar pronto.");
        }
    }
}
