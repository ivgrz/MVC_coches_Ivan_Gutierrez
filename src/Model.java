import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de manejar los datos
 */
public class Model {
    int litros = 0;
    static ArrayList<Coche> parking = new ArrayList<>();




    private List<Observer> observadores = new ArrayList<>();


    private final int CAPACIDAD_MAXIMA = 50;



    public void agregarObservador(Observer obs) {
        observadores.add(obs);
    }


    private void notificarCambioGasolina(String mensaje) {
        for (Observer obs : observadores) {
            obs.actualizarGasolina(mensaje);
        }
    }
    /**
     * Crea un coche y lo mete en el parking
     * @param modelo del coche
     * @param matricula identificador unico
     * @return el coche creado
     */
    public Coche crearCoche(String modelo, String matricula){
        Coche aux = new Coche(modelo, matricula);
        parking.add(aux);
        return aux;
    }

    /**
     * Busca coche segun matricula
     * @param matricula a buscar
     * @return chche o null si no existe
     */
    public Coche getCoche(String matricula){
        Coche aux = null;
        // recorre el array buscando por matricula
        for (Coche e: parking) {
            if (e.matricula.equals(matricula)) {
                aux = e;
            }
        }
        return aux;
    }
    public ArrayList<Coche> getTodosLosCoches() {
        return parking;
    }

    /**
     * Cambia la velocidad de un coche
     * @param matricula
     * @param v nueva velocidad
     * @return velocidad modificada
     */
    public int cambiarVelocidad(String matricula, Integer v) {
        // busca el coche
        getCoche(matricula).velocidad = v;
        // retorna la nueva velocidad
        return getCoche(matricula).velocidad;
    }

    /**
     * Ddevuelve la velocidad segun la matricula
     * @param matricula
     * @return
     */
    public int getVelocidad(String matricula) {
        return getCoche(matricula).velocidad;
    }
    /**
     * Añade un coche al parking
     * @param coche el coche a añadir
     */
    public void addCoche(Coche coche) {
        parking.add(coche);
    }
    /**
     * Aumenta la velocidad de un coche en el parking
     * @param index índice del coche
     * @param incremento cantidad a aumentar
     */
    public void aumentarVelocidadCoche(int index, int incremento) {
        if (index >= 0 && index < parking.size()) {
            parking.get(index).aumentarVelocidad(incremento);
        } else {
            System.out.println("Índice fuera de rango");
        }
    }
    /**
     * Disminuye la velocidad de un coche en el parking
     * @param index índice del coche
     * @param decremento cantidad a disminuir
     */
    public void disminuirVelocidadCoche(int index, int decremento) {
        if (index >= 0 && index < parking.size()) {
            parking.get(index).disminuirVelocidad(decremento);
        } else {
            System.out.println("Índice fuera de alcance");
        }
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
    public void avanzar(int metros, int velocidad) {
        int posicionInicial = 0;
        int nuevaPosicion = posicionInicial + metros;

        if (nuevaPosicion > 100 || velocidad > 60) {
            reducirGasolina();
            System.out.println("Avanzando " + metros + " metros a velocidad " + velocidad + " km/h, pero se ha reducido la gasolina.");
            notificarCambioGasolina("Gasolina reducida tras avanzar " + metros + " metros.");
            if (litros < 10) {
                AlarmaGasolina alarmaGasolina = new AlarmaGasolina();
                alarmaGasolina.actualizarGasolina("Alerta: Gasolina baja, quedan " + litros + " litros, repostar inmediatamente.");
            }


        } else {
            System.out.println("Avanzando " + metros + " metros a velocidad " + velocidad + " km/h.");
        }

    }
    public boolean reducirGasolina() {
        if (litros >= 10) {
            litros -= 10;
            System.out.println("Gasolina restante: " + litros + " litros.");
        } else {
            System.out.println("No hay suficiente gasolina. Gasolina actual: " + litros + " litros.");
        }
        return true;
    }

    /**
     * Reposta una cantidad de gasolina ingresada
     * @param litrosRepostar litros a añadir
     */
    public void ponerGasolina(int litrosRepostar) {
        if (litrosRepostar <= 0) {
            System.out.println("Cantidad inválida. Ingrese un valor positivo.");
            return;
        }
        litros += litrosRepostar;
        System.out.println("Se han añadido " + litrosRepostar + " litros. Total actual: " + litros + " litros.");

    }

    public int getGasolinaActual() {
        return litros;
    }

}
