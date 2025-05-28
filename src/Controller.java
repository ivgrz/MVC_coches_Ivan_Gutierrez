import java.lang.module.ModuleDescriptor;
import java.util.Scanner;

public class Controller {
    // Instanciamos la vista y el modelo
    View miView = new View();
    Model miModel = new Model();

    public Controller() {
        this.miModel = new Model();
        this.miView = new View();
    }
    public static void main(String[] args) {
        // Instanciamos la vista y el modelo
        View miView = new View();
        Model miModel = new Model();

        // Crear tres coches
        miModel.crearCoche("LaFerrari", "SBC 1234");
        miModel.crearCoche("Alpine", "HYU 4567");
        miModel.crearCoche("Aston Martin", "FGH 3333");

        Coche ferrari = miModel.getCoche("SBC 1234");
        // modifica la velocidad
        int nuevaVelocidad = miModel.cambiarVelocidad("SBC 1234", 30);

        // recoje la velocidad y la muestra (tarea de la View)
        boolean hecho = miView.muestraVelocidad("SBC 1234", miModel.getVelocidad("SBC 1234"));

        if (hecho) {
            System.out.println("Correcto");
        } else {
            System.out.println("Error");
        } ;
    }
    public void aumentarVelocidad(String matricula, int incremento) {
        Coche coche = miModel.getCoche(matricula);
        if (coche != null) {
            miModel.cambiarVelocidad(matricula, coche.velocidad + incremento);
            System.out.println("Velocidad aumentada correctamente.");
        } else {
            System.out.println("Coche no encontrado.");
        }
    }

    public void disminuirVelocidad(String matricula, int decremento) {
        Coche coche = miModel.getCoche(matricula);
        if (coche != null) {
            miModel.cambiarVelocidad(matricula, coche.velocidad - decremento);
            System.out.println("Velocidad disminuida correctamente.");
        } else {
            System.out.println("Coche no encontrado.");
        }
    }
    public void avanzar(){
        Scanner sc = new Scanner(System.in);
        int metros = 0;
        System.out.println("Introduce los metros a avanzar:");
        metros = sc.nextInt();
        miModel.Avanzar(metros);

    }
    public void Gasolina(){
        Scanner sc = new Scanner(System.in);
        int litros = 0;
        System.out.println("Introduce los litros de gasolina:");
        litros = sc.nextInt();
        miModel.PonerGasolina();
    }
}