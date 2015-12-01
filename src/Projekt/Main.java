package Projekt;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by maus on 11.11.2015.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new Game();
    }
}

/*
* Main
*   Oskab mängu käivitada
*   new Game();
*
*Mäng
*   Oskab mängu seadistada
*   Oskab mängu lõpetada
*
*Question - raamistik
*   Teab millal mäng on läbi
*   Oskab "mängulauda" kuvada (raamistik)
*   Oskab küsimust kuvada ja vahetada
*   Oskab pilti kuvada ja vahetada
*   Oskab vastuseid kuvada
*   Oskab mängu seisu näidata (kokkuvõte)
*
* Sign
*   Oskab pilti genereerida (valida)
*   Teab pilti
*   Oskab vastavalt sellele küsimuse valida
*   Teab vastust
*   Oskab kasutaja valikut kontrollida 
*   Oskab õigeid ja valesid vastuseid lugeda
*
* */
