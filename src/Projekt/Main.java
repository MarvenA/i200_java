package Projekt;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

;import java.util.EventListener;
import java.util.Stack;

/**
 * Created by maus on 14.10.2015.
 */
public class Main extends Application {

    Stage window;
    Stage windowHelp;

    Integer countRight = 0;
    Integer countWrong = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("COLREG - Cardinal Signs");
        //Alustamise ja lõpetamise nuppude loomine
        HBox topMenu = new HBox();
        topMenu.setPadding(new Insets(10, 0, 10, 10));
        topMenu.setSpacing(15);
        topMenu.setAlignment(Pos.CENTER);
        //topMenu.setStyle("-fx-background-color: #009999");

        Button buttonStart = new Button("Alusta");
        buttonStart.setPrefSize(100, 18);
        Button buttonFinish = new Button("Lõpeta");
        buttonFinish.setPrefSize(100, 18);
        topMenu.getChildren().addAll(buttonStart, buttonFinish);

        HBox checkNext = new HBox();
        checkNext.setPadding(new Insets(10, 0, 10, 10));
        checkNext.setSpacing(15);
        checkNext.setAlignment(Pos.CENTER);
        Button btnCheck = new Button("Kontrolli");
        Button btnNext  = new Button("Järgmine");
        btnCheck.setPrefSize(100, 18);
        btnNext.setPrefSize(100, 18);
        checkNext.getChildren().addAll(btnCheck, btnNext);

        //Küsimus, pilt ja valikvastused
        VBox centreVbox = new VBox();
        centreVbox.setSpacing(15);
        centreVbox.setPadding(new Insets(5, 0, 5, 0));
        centreVbox.setAlignment(Pos.CENTER);
        centreVbox.setStyle("-fx-background-color: #99CCFF");
        Label question = new Label("Milline meremärk on kujutatud alloleval pildil?");
        question.setFont(Font.font(15));
        centreVbox.getChildren().addAll(question, addSign(), answers(), checkNext);

        //Abiaknad - spikker ja kompass
        HBox bottomMenu = new HBox();
        bottomMenu.setPadding(new Insets(10, 0, 10, 10));
        bottomMenu.setSpacing(15);
        bottomMenu.setAlignment(Pos.CENTER);

        Button button1 = new Button("Spikker xxx");
        button1.setOnAction(e -> {
            windowHelp = new Stage();
            windowHelp.setTitle("Kardinaalmärgid");

            Image image1 = new Image("res/Spikker.png");
            ImageView Compass = new ImageView();
            Compass.setImage(image1);

            StackPane stack = new StackPane();
            Scene sceneSpikker = new Scene(stack);
            stack.getChildren().add(Compass);

            windowHelp.setScene(sceneSpikker);
            windowHelp.setResizable(false);
            windowHelp.show();
        });
        Button button2 = new Button("Kompass");
        button2.setOnAction(e -> {
            windowHelp = new Stage();
            windowHelp.setTitle("Kompass");

            Image image1 = new Image("res/Kompass.png");
            ImageView Compass = new ImageView();
            Compass.setImage(image1);

            StackPane stack = new StackPane();
            Scene sceneCompass = new Scene(stack);
            stack.getChildren().add(Compass);

            windowHelp.setScene(sceneCompass);
            windowHelp.setResizable(false);
            windowHelp.show();
        });
        button1.setPrefSize(80, 15);
        button2.setPrefSize(80, 15);
        bottomMenu.getChildren().addAll(button1, button2);

        //Programmi layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setCenter(centreVbox);
        borderPane.setBottom(bottomMenu);
        borderPane.setStyle("-fx-background-color: #009999");

        Scene scene1 = new Scene(borderPane, 350, 470);
        window.setScene(scene1);
        window.setResizable(false);
        window.show();
    }

    public VBox answers() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 0, 0, 20));

        //vbox.setAlignment(Pos.CENTER);

        ToggleGroup vastused = new ToggleGroup();
        RadioButton north    = new RadioButton("Põhjatooder");
        RadioButton south    = new RadioButton("Lõunatooder");
        RadioButton east     = new RadioButton("Idatooder");
        RadioButton west     = new RadioButton("Läänetooder");
        north.setToggleGroup(vastused);
        south.setToggleGroup(vastused);
        east.setToggleGroup(vastused);
        west.setToggleGroup(vastused);
        vbox.getChildren().addAll(north, south, east, west);

        return vbox;
    }

    public ImageView addSign() {
        //Valitakse üks pilt neljast
        int       rnd  = (int) (Math.random() * 4) + 1;
        Image     sign = new Image("res/Cardinal" + rnd + ".png");
        ImageView Sign = new ImageView();
        Sign.setImage(sign);
        Sign.setFitHeight(150);
        Sign.setPreserveRatio(true);
        return Sign;
    }
}


