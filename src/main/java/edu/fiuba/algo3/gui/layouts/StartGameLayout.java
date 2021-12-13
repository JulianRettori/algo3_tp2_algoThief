package edu.fiuba.algo3.gui.layouts;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.gui.scenes.BorderPaneScene;
import edu.fiuba.algo3.modelo.AlgoThief;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartGameLayout extends VBox {

    private Label labelVariable;

    public StartGameLayout(Stage window, App app, AlgoThief algoThief) {

        Label label = new Label(algoThief.desplegarTextoInicial());
        Label labelHorario= new Label(algoThief.obtenerHorario());
        labelVariable = new Label("");

        Button buttonExit = new Button("Exit");
        buttonExit.setOnAction(e->{window.close();});

        Button actionButton = new Button("Action");
        actionButton.setOnAction(e->{
            app.gameLogic();
        });

        Button openMap = new Button("Abrir borderPane");
        openMap.setOnAction(e->{
            System.out.println("Abrite borderPane");
            window.setScene(new BorderPaneScene(window));
        });

        getChildren().addAll(label,labelVariable,labelHorario,buttonExit,actionButton,openMap);


    }

    public void setTextoVariable(String textoVariable) {

        this.labelVariable.setText(textoVariable);
    }
}
