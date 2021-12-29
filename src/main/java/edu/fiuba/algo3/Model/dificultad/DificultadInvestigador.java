package edu.fiuba.algo3.Model.dificultad;

import edu.fiuba.algo3.Model.CosasDelincuente.Delincuente;

public class DificultadInvestigador implements DificultadJuego{
    @Override
    public Delincuente crearDelincuente() {
        return Delincuente.crearDelincuenteParaInvestigador();
    }
    public  DificultadJuego aumentarDificultad(){
        return  new DificultadSargento();
    }
}
