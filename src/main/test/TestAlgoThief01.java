import edu.fiuba.algo3.Model.AlgoThief;
import edu.fiuba.algo3.Model.CosasDelincuente.Delincuente;
import edu.fiuba.algo3.Model.EstadoJugando;
import edu.fiuba.algo3.Model.Excepciones.ExceptionDatoNoExistente;
import edu.fiuba.algo3.Model.ciudad.Ciudad;
import edu.fiuba.algo3.Model.dificultad.DificultadNovato;
import edu.fiuba.algo3.Model.dificultad.DificultadSargento;
import edu.fiuba.algo3.Model.fakes.AlgoThiefFake;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestAlgoThief01 {

    private final String directorioCiudades = "src/main/java/edu/fiuba/algo3/Model/ArchivosDeTexto/Ciudades.txt";
    private final String directorioDelincuentes = "src/main/java/edu/fiuba/algo3/Model/ArchivosDeTexto/Delincuentes.txt";

    @Test
    public void Test01EntrarAEdificioPrimeraVezAumentaEnUnoHorario() {

        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        algoThief.entrarABanco();

        assertEquals("Monday 08:00", algoThief.obtenerHorario());
    }

    @Test
    public void Test02EntrarAEdificioDosVecesAumentaHorarioTresHoras() {
        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        algoThief.entrarABanco();
        algoThief.entrarAAeropuerto();


        assertEquals("Monday 10:00", algoThief.obtenerHorario());
    }

    @Test
    public void Test03EntrarAEdificioTresVecesAumentaHorarioSeisHoras() {
        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        algoThief.entrarABanco();
        algoThief.entrarAAeropuerto();
        algoThief.entrarABiblioteca();
        assertEquals("Monday 13:00", algoThief.obtenerHorario());
    }

    @Test
    public void Test04EntrarAEdificioDespuesDeEntrarTresVecesAumentaTresHoras() {

        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        algoThief.entrarABanco();
        algoThief.entrarABanco();
        algoThief.entrarABanco();
        assertEquals("Monday 13:00", algoThief.obtenerHorario());

        algoThief.entrarABanco();
        assertEquals("Monday 16:00", algoThief.obtenerHorario());
    }

    @Test
    public void Test05EntrarAEdificioEnHorarioDeDormirAumentaOchoHoras() {

        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);

        for (int i = 0; i < 6; i++) {
            algoThief.entrarABanco();
        }
        assertEquals("Tuesday 06:00", algoThief.obtenerHorario());
    }


    @Test
    public void Test07PoliciaViajaTiempoAvanza() {
        AlgoThief algothief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        String horaSalida = algothief.obtenerHorario();
        ArrayList<Ciudad> listaOpcionesViaje = algothief.verOpcionesDeViaje();
        Ciudad ciudadSeleccionada = listaOpcionesViaje.get(1);
        algothief.viajar(ciudadSeleccionada);
        ArrayList<Ciudad> listaOpcionesViaje2 = algothief.verOpcionesDeViaje();
        algothief.viajar(listaOpcionesViaje2.get(0));
        String horaLlegada = algothief.obtenerHorario();
        assertNotSame(horaSalida, horaLlegada);
    }

    @Test
    public void Test08MostrarOpcionesViajeEnCiudadDestinoContieneACiudadOrigen() {

        AlgoThief algothief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        Ciudad ciudadOrigen = algothief.policia.ciudadActual;
        ArrayList<Ciudad> listaOpcionesViajeOrigen = algothief.verOpcionesDeViaje();
        Ciudad ciudadDestino = listaOpcionesViajeOrigen.get(0);
        algothief.viajar(ciudadDestino);
        ArrayList<Ciudad> listaOpcionesViajeDestino = algothief.verOpcionesDeViaje();

        assertTrue(listaOpcionesViajeDestino.contains(ciudadOrigen));
    }

    @Test
    public void Test09MostrarOpcionesViajeEnCiudadNoRecorridaYContieneACiudadOrigen() {
        AlgoThief algothief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        Ciudad ciudadOrigen = algothief.policia.ciudadActual;
        ArrayList<Ciudad> listaOpcionesViajeOrigen = algothief.verOpcionesDeViaje();
        Ciudad ciudadDestino = listaOpcionesViajeOrigen.get(1);
        algothief.viajar(ciudadDestino);
        ArrayList<Ciudad> listaOpcionesViajeDestino = algothief.verOpcionesDeViaje();

        assertTrue(listaOpcionesViajeDestino.contains(ciudadOrigen));
    }


    @Test
    public void Test10() {
        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
    }

    @Test
    public void Test11Presentacion() {
        AlgoThiefFake algoThief = new AlgoThiefFake(directorioCiudades, directorioDelincuentes,new DificultadNovato());
        algoThief.ingresarUsuario("juan");
        Ciudad ciudad = algoThief.obtenerCiudad("Port Moresby");
        algoThief.setearCiudadInicial(ciudad);
        Delincuente delincuente = new Delincuente();
        delincuente.agregarDato("Female");
        ciudad.agregarDato("Treasure", "ancient tribal totem");
        algoThief.setearDelincuente(delincuente);

        assertEquals(algoThief.desplegarTextoInicial(), "***FLASH***\n" +
                "National treasure stolen from Port Moresby.\n" +
                "The treasure has been identified as an ancient" +
                " tribal totem.\n" +
                "Female suspect reported at the scene of the crime.\n" +
                "Your assignment:\n" +
                "Track the thief from Port Moresby to her" +
                " hideout and arrest her !\n" +
                "You must apprehend the thief by Sunday, 5pm.\n" +
                "Good luck," + "juan" + "\n" +
                "\n");
    }

    @Test
    public void PruebaEntrarAEdificioQueVisitoDelincuenteDespliegaPistaEspecifica() {
        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        String pista = algoThief.entrarABanco();
        assertNotSame("The thief has NOT been seen in this building", pista);
    }

    @Test
    public void PruebaEntrarAEdificioConDelincuenteAtacarPoliciaAumentaHorario() {
        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);

        String pista = algoThief.entrarABanco();
    }
    @Test
    public void PruebaFiltrarSospechososAvanza1Hora() {
        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        algoThief.filtrarSospechosos();
        assertEquals("Monday 08:00",algoThief.obtenerHorario());
    }
    @Test
    public void PruebaFiltrarSospechososSinElegirOpcionesNoModificaEstadoDeJuego() {
        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        algoThief.filtrarSospechosos();
        assertTrue(algoThief.getEstadoDeJuego() instanceof  EstadoJugando);
    }

    @Test
    public void PruebaObtenerCiudadDevuelveElNombreDeLaCiudad() {
        AlgoThiefFake algoThief = new AlgoThiefFake(directorioCiudades, directorioDelincuentes, new DificultadNovato());
        Ciudad ciudad = new Ciudad();
        ciudad.agregarDato("City","Buenos Aires");
        algoThief.setearCiudad(ciudad);
        assertEquals("Buenos Aires",algoThief.ciudadActual());
    }


    @Test
    public void PruebaObtenerInformacionCiudad() {
        AlgoThief algoThief = new AlgoThief(directorioCiudades, directorioDelincuentes);
        assertTrue(algoThief.obtenerInformacionCiudad() instanceof String);
    }

    @Test
    public void PruebaExcepcionDatoNoExistente() {
        Ciudad ciudad = new Ciudad();
        assertThrows(ExceptionDatoNoExistente.class ,() -> ciudad.obtenerDato("lalal"));
    }


    @Test
    public void PruebaAumentarDificultadJuegoSinGanar() {
        AlgoThiefFake algoThief = new AlgoThiefFake(directorioCiudades, directorioDelincuentes, new DificultadNovato());
        /*algoThief.SetearDificultadJuego(new DificultadSargento());
        assertTrue(algoThief.getDificultadJuego() instanceof DificultadSargento);*/

         algoThief.siguienteNivel();
         assertTrue(algoThief.getDificultadJuego() instanceof DificultadNovato);
    }

    @Test
    public void PruebaPoliciaEsAtacadoConPistola()
    {
        AlgoThiefFake algoThief = new AlgoThiefFake(directorioCiudades, directorioDelincuentes, new DificultadSargento());
        algoThief.setearCiudad(algoThief.obtenerUltimaCiudad());
        algoThief.entrarABanco();
        assertEquals("Monday 12:00", algoThief.obtenerHorario());
    }

}