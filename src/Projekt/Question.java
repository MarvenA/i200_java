package Projekt;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by maus on 29.11.2015.
 */
public class Question {
    Stage stage = new Stage();
    BorderPane layout;
    Button btnStart;
    Button btnFinish;
    Button btnCheck;
    Button btnNext;
    Button btnHelp;
    Integer count=0;
    VBox vbox;

    public Question() {
        setupScene();
        clickOnHelp();
    }

    private void clickOnHelp() {
        btnHelp.setOnAction(e -> {
            Stage stageHelp = new Stage();
            stageHelp.setTitle("Kardinaalmärgid");

            Image     image1  = new Image("res/Spikker.png");
            ImageView Spikker = new ImageView();
            Spikker.setImage(image1);

            StackPane stack        = new StackPane();
            Scene     sceneSpikker = new Scene(stack);
            stack.getChildren().add(Spikker);

            count++;
            if (count == 2){
                btnHelp.setDisable(true);
            }

            stageHelp.setScene(sceneSpikker);
            stageHelp.setResizable(false);
            stageHelp.show();

        });
    }

    public void setupScene() {
        layout = new BorderPane();
        Scene scene = new Scene(layout, 450, 650);
        scene.getStylesheets().add("Projekt/style.css");

        layout.setTop(topMenu());
        layout.setCenter(introduction());
        layout.setBottom(bottomMenu());

        stage.setTitle("Kardinaalmärkide test");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    private Label introduction() {
        Label label = new Label();
        label.setText("Tegemist on valikvastustega kardinaalmärkide testiga. Alustamiseks vajuta 'Alusta' ja lõpetamiseks 'Lõpeta'. " +
                "Kui oled hädas, siis kliki Spikril. ");
        return label;
    }

    private HBox bottomMenu() {
        HBox bottomMenu = new HBox();
        bottomMenu.getStyleClass().add("topMenu");
        btnHelp = new Button("Spikker");

        bottomMenu.getChildren().add(btnHelp);
        return bottomMenu;
    }

    public HBox topMenu() {
        HBox topMenu = new HBox();
        topMenu.getStyleClass().add("topMenu");

        btnStart = new Button("Alusta");
        btnFinish = new Button("Lõpeta");
        topMenu.getChildren().addAll(btnStart, btnFinish);
        return topMenu;
    }

    public boolean clickOnFinish() {
        return false;
    }

    public void clickOnStart() {
        btnStart.setOnAction(event -> {
            vbox = new VBox();
            vbox.getStyleClass().add("Vbox");
            ImageView sign = Sign.generateSign();

            vbox.getChildren().addAll(Sign.question(),Sign.generateSign());

            layout.setCenter(vbox);
        });
    }
}
