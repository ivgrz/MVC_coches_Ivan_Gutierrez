public class AlarmaGasolina implements Observer {
    @Override
    public void actualizarGasolina(String mensaje) {
        System.out.println("Alarma de gasolina: " + mensaje);

        String[] partesDelMensaje = mensaje.split(":");


        String textoDelNivel = partesDelMensaje[1].trim();
        int nivelDeGasolina = Integer.parseInt(textoDelNivel);


        if(nivelDeGasolina< 10)

        {
            System.out.println("[AVISO] Quedan " + nivelDeGasolina + " litros de gasolina. Recomendado repostar pronto.");
        }

    }


    public void setGasolinaActual(int litros) {
    }
}

