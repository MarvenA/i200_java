package Projekt;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by maus on 13.12.15.
 */
public class Help {
    Integer count = 0;

    public Stage kuvaHelp(){

        Stage stageHelp = new Stage();
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

    public boolean count() {
        if(count==3) {
            return true;
        }
        return false;
    }
}
