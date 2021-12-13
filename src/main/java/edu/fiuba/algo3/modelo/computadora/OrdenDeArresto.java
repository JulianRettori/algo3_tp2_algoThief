package edu.fiuba.algo3.modelo.computadora;

import java.util.ArrayList;
import java.util.Arrays;

public class OrdenDeArresto {
    ArrayList<String> hair;
    int hairIndex;
    ArrayList<String> auto;
    int autoIndex;
    ArrayList<String> sex;
    int sexIndex;
    ArrayList<String> hobby;
    int hobbyIndex;
    ArrayList<String> feature;
    int featureIndex;

    public OrdenDeArresto(){
        hair = new ArrayList<>(Arrays.asList("???","Brown", "Blond", "Red", "Black"));
        auto = new ArrayList<>(Arrays.asList("???","Convertible", "Limousine", "Race car", "Motorcycle"));
        hobby = new ArrayList<>(Arrays.asList("???","Tennis", "Music", "Mount climbing", "Skydiving","Swimming","Croquet"));
        feature = new ArrayList<>(Arrays.asList("???", "Limps","Ring","Tattoo","Scar","Jewelry"));
        sex = new ArrayList<>(Arrays.asList("???", "Male", "Female"));
    }

    public ArrayList<ArrayList<String>> siguientePelo(){
        hairIndex+=1;
        if((hair.size()) == hairIndex)
        {
            hairIndex = 0;
        }
        return mostrarOpcionesSeleccionadas();
    }
    public ArrayList<ArrayList<String>>siguienteSex(){
        sexIndex+=1;
        if((sex.size()) == sexIndex)
        {
            sexIndex = 0;
        }
        return mostrarOpcionesSeleccionadas();
    }
    public ArrayList<ArrayList<String>> siguienteCar(){
        autoIndex+=1;
        if((auto.size()) == autoIndex)
        {
            autoIndex = 0;
        }
        return mostrarOpcionesSeleccionadas();
    }
    public ArrayList<ArrayList<String>> siguienteFeature(){
        featureIndex+=1;
        if((feature.size()) == featureIndex)
        {
            featureIndex = 0;
        }
        return mostrarOpcionesSeleccionadas();
    }
    public ArrayList<ArrayList<String>> siguienteHobby(){
        hobbyIndex+=1;
        if((hobby.size()) == hobbyIndex)
        {
            hobbyIndex = 0;
        }
        return mostrarOpcionesSeleccionadas();
    }



    public ArrayList<ArrayList<String>> mostrarOpcionesSeleccionadas(){
        ArrayList<ArrayList<String>> listaDeSeleccionados= new ArrayList<ArrayList<String>>();
        ArrayList<String> listaSex= new ArrayList<String>();
        listaSex.add("sex"); //1era posicion es la clave de la lista
        listaSex.add(sex.get(sexIndex)); //Obtenes el valor del atributo que elegiste
        listaDeSeleccionados.add(listaSex);

        ArrayList<String> listaHobby= new ArrayList<String>();
        listaHobby.add("hobby");
        listaHobby.add(hobby.get(hobbyIndex));
        listaDeSeleccionados.add(listaHobby);

        ArrayList<String> listaHair= new ArrayList<String>();
        listaHair.add("hair");
        listaHair.add(hair.get(hairIndex));
        listaDeSeleccionados.add(listaHair);

        ArrayList<String> listaFeature = new ArrayList<String>();
        listaFeature.add("feature");
        listaFeature.add(feature.get(featureIndex));
        listaDeSeleccionados.add(listaFeature);

        ArrayList<String> listaAuto= new ArrayList<String>();
        listaAuto.add("auto");
        listaAuto.add(auto.get(autoIndex));
        listaDeSeleccionados.add(listaAuto);

        return listaDeSeleccionados;
    }
}