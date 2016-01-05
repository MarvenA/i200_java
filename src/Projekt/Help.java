package Projekt;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Help sisaldab kõike, mis on seotud spikriga.
 *
 * kuvaHelp() on meetod spikri kuvamiseks, sama meetodi sees loendatatkse, mitu korda on spikrit vaadatud
 * checkCount() on meetod, mis kontrollib kas spikrit on juba kolm korda vaadatud, loendamine käib
 */
public class Help {
    Integer count = 0;
    Stage stageHelp = new Stage();
    public Stage kuvaHelp(){


        stageHelp.setTitle("Navigatsioonimärgid - kardinaal- ja lateraalmärgid");

        Image image1 = new Image("res/Spikker.png");
        ImageView Spikker = new ImageView();
        Spikker.setImage(image1);

        StackPane stack = new StackPane();
        Scene sceneSpikker = new Scene(stack);
        stack.getChildren().add(Spikker);

        stageHelp.setScene(sceneSpikker);
        stageHelp.setResizable(false);
        stageHelp.show();
        count++;

        return stageHelp;
    }

    //Kontrollib, kas spikrit on kolm korda kasutatud
    public boolean checkCount() {
        return count == 3;
    }
    //Sulgeb spikri akna enne järgmist küsimust
    public void stageClose() {
        stageHelp.close();
    }
}
