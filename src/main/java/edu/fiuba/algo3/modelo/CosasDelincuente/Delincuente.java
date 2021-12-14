package edu.fiuba.algo3.modelo.CosasDelincuente;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ExceptionDatoNoExistente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Delincuente{
    private HashMap<String,String> hashDeAtributos;
    private Arma arma;
    private int largoRecorrido;



    public Delincuente() {
        this.hashDeAtributos= new HashMap<String,String>();
        this.arma = new Cuchillo();

    }

    public static  Delincuente crearDelincuenteParaNovato(){
        Delincuente delincuenteNuevo= new Delincuente();
        delincuenteNuevo.setLargoRecorrido(4);
        return delincuenteNuevo;
    }

    private void setLargoRecorrido(int largo) {
        this.largoRecorrido=largo;
    }


    public static  Delincuente crearDelincuenteParaDetective(){
        Delincuente delincuenteNuevo= new Delincuente();
        delincuenteNuevo.setLargoRecorrido(5);
        return delincuenteNuevo;
    }

    public static  Delincuente crearDelincuenteParaInvestigador(){
        Delincuente delincuenteNuevo= new Delincuente();
        delincuenteNuevo.setArma(new Pistola());
        delincuenteNuevo.setLargoRecorrido(6);
        return delincuenteNuevo;
    }

    public static  Delincuente crearDelincuenteParaSargento(){
        Delincuente delincuenteNuevo= new Delincuente();
        delincuenteNuevo.setArma(new Pistola());
        delincuenteNuevo.setLargoRecorrido(7);
        return delincuenteNuevo;
    }

    private void setArma(Arma arma) {
        this.arma=arma;
    }

    //{Clave:valor} ---> {Pelo: Rubio}
    public void agregarDato(String clave,String valor) {
        this.hashDeAtributos.put(clave,valor);
    }

    public String obtenerDato(String clave){
        String claveCorregida;
        if (clave.length() >= 2 ) {
            claveCorregida = clave.substring(0, 1).toUpperCase() + clave.substring(1);
        }
        else{
            claveCorregida=clave.toUpperCase();
        }

        String dato = this.hashDeAtributos.get(claveCorregida);

        if (dato == null){
            throw new ExceptionDatoNoExistente();
        }
        return dato;
    }

    public String generarPista() {

        ArrayList<String> atributosLista = new ArrayList<String>();
        int randIdx = new Random().nextInt(hashDeAtributos.size()-1);

        atributosLista.add("Tiene pelo de color " + hashDeAtributos.get("Hair"));
        atributosLista.add("El delincuente era " + hashDeAtributos.get("Sex"));
        atributosLista.add("Y vi a ese delincuente irse con un auto " + hashDeAtributos.get("Auto"));
        atributosLista.add("Tenia un " + hashDeAtributos.get("Feature"));
        atributosLista.add("");

        return atributosLista.get(randIdx);

    }

    public int cantidadDeCiudadesRecorridas(){
        return 4;
    }

    public int atacar(){
        return this.arma.atacar();
    }

    public boolean comparar(Delincuente delincuente) {
        if (this.obtenerDato("Name").equals(delincuente.obtenerDato("Name")))
            return true;

        return false;
    }

    public boolean cumpleLasCaracteristicas(ArrayList<ArrayList<String>> listaCaracteristicas) {

        for (ArrayList<String> claveYValorCaracteristica: listaCaracteristicas){
            String clave=claveYValorCaracteristica.get(0);
            String valor=claveYValorCaracteristica.get(1);
            if(valor == "???"){continue;}
            if(obtenerDato(clave) != valor ){
                return false;
            }
        }

        return true;
    }
}