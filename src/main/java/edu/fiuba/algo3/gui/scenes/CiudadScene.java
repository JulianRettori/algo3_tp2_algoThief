package edu.fiuba.algo3.gui.scenes;

import edu.fiuba.algo3.gui.layouts.CiudadLayout;
import edu.fiuba.algo3.modelo.AlgoThief;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CiudadScene extends Scene {
    CiudadLayout ciudadLayout;

    public CiudadScene(Stage window, CiudadLayout ciudadLayout, AlgoThief algoThief) {
        super(ciudadLayout,640,480);
        this.ciudadLayout = ciudadLayout;
    }

    public CiudadLayout getCiudadLayout() {
        return ciudadLayout;
    }

    public void setCiudadLayout(CiudadLayout startGameLayout) {
        this.ciudadLayout = startGameLayout;
    }
}