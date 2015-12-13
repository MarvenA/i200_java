package Projekt;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by maus on 10.11.2015.
 */
public class Question {
    Stage stage = new Stage();
    Stage stageHelp;
    BorderPane layout;
    HBox topMenu;
    VBox centreVbox;
    HBox bottomMenu;
    HBox checkNext;
    Button buttonStart;
    Button buttonFinish;
    Button buttonCheck;
    Button buttonNext;
    Button buttonHelp;
    Integer count = 0;
    ArrayList countRight;
    ArrayList countWrong;
    Sign sign = new Sign();

    public Question() {
        setupScene();
    }

    public HBox checkNext() {
        checkNext = new HBox();
        checkNext.getStyleClass().add("Hbox");

        buttonCheck = new Button("Kontrolli");
        clickOnCheck();

        buttonNext = new Button("Järgmine");
        buttonNext.setDisable(true);
        clickOnNext();

        checkNext.getChildren().addAll(buttonCheck, buttonNext);
        return checkNext;
    }

    private void clickOnHelp() {
        buttonHelp.setOnAction(e -> {
            stageHelp = new Stage();
            stageHelp.setTitle("Kardinaalmärgid");

            Image image1 = new Image("res/Spikker.png");
            ImageView Spikker = new ImageView();
            Spikker.setImage(image1);

            StackPane stack = new StackPane();
            Scene sceneSpikker = new Scene(stack);
            stack.getChildren().add(Spikker);

            count++;
            if (count == 3) {
                buttonHelp.setDisable(true);
            }

            stageHelp.setScene(sceneSpikker);
            stageHelp.setResizable(false);
            stageHelp.show();
        });
    }

    private void clickOnNext() {
        buttonNext.setOnAction(event -> {
            sign.rb1.setSelected(false);
            sign.rb1.setSelected(false);
            sign.rb1.setSelected(false);
            sign.rb1.setSelected(false);

            centreVbox.getChildren().remove(0, 1);
            centreVbox.getChildren().add(0, sign.newQuestion());
            centreVbox.getChildren().remove(1, 2);
            centreVbox.getChildren().add(1, sign.generateSign());
            centreVbox.getChildren().remove(2, 3);
            centreVbox.getChildren().add(2, sign.answers());

            buttonNext.setDisable(true);
            buttonCheck.setDisable(false);
        });
    }

    private void clickOnCheck() {
        countRight = new ArrayList();
        countWrong = new ArrayList();

        buttonCheck.setOnAction(event -> {

            if (sign.rb1.isSelected()) {
                countRight.add(1);
                sign.rb1.getStyleClass().add("radio-correct");
            } else {
                countWrong.add(1);

                sign.rb1.setToggleGroup(null);
                sign.rb1.setSelected(true);
                sign.rb1.getStyleClass().add("radio-correct");

                if (sign.rb2.isSelected()) sign.rb2.getStyleClass().add("radio-incorrect");
                else if (sign.rb3.isSelected()) sign.rb3.getStyleClass().add("radio-incorrect");
                else if (sign.rb4.isSelected()) sign.rb4.getStyleClass().add("radio-incorrect");
            }
            buttonNext.setDisable(false);
            buttonCheck.setDisable(true);
        });
    }

    private void clickOnFinish() {
        buttonFinish.setOnAction(event -> {

            int sum = countRight.size() + countWrong.size();

            Label total = new Label("Vastasid kokku " + sum + " küsimusele.");
            Label countR = new Label("Õigeid vastuseid:   " + countRight.size());
            Label countW = new Label("Valesid vastuseid:   " + countWrong.size());

            Image compass = new Image("res/Kompass.png");
            ImageView ivCompass = new ImageView(compass);
            ivCompass.setFitHeight(180);
            ivCompass.setPreserveRatio(true);

            Image anchor = new Image("res/Anchor.png");
            ImageView ivAnchor = new ImageView(anchor);
            ivAnchor.setFitHeight(180);
            ivAnchor.setPreserveRatio(true);

            VBox kokkuvote = new VBox();
            kokkuvote.getChildren().addAll(ivCompass, total, countR, countW, ivAnchor);
            kokkuvote.getStyleClass().add("kokkuvote");

            layout.getChildren().removeAll(topMenu, bottomMenu);
            layout.setCenter(kokkuvote);
        });
    }

    private HBox topMenu() {
        topMenu = new HBox();
        topMenu.getStyleClass().add("Hbox");

        buttonStart = new Button("Alusta");
        clickOnStart();
        buttonFinish = new Button("Lõpeta");
        buttonFinish.setDisable(true);
        clickOnFinish();
        topMenu.getChildren().addAll(buttonStart, buttonFinish);
        return topMenu;
    }

    private VBox centreVbox() {
        centreVbox = new VBox();
        centreVbox.getStyleClass().add("Vbox");

        centreVbox.getChildren().addAll(sign.newQuestion(), sign.generateSign(), sign.answers(), checkNext());
        return centreVbox;
    }

    private HBox bottomMenu() {
        bottomMenu = new HBox();
        bottomMenu.getStyleClass().add("Hbox");

        buttonHelp = new Button("Spikker");
        buttonHelp.setDisable(true);
        clickOnHelp();
        bottomMenu.getChildren().add(buttonHelp);
        return bottomMenu;
    }

    private void setupScene() {
        layout = new BorderPane();
        layout.setTop(topMenu());
        layout.setCenter(introduction());
        layout.setBottom(bottomMenu());

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("Projekt/style.css");
        stage.setScene(scene);
        stage.setTitle("Navigatsioonimärgid Eesti vetes");
        stage.show();
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    private VBox introduction() {
        VBox vbox = new VBox();

        Label label1 = new Label();
        label1.getStyleClass().add("kokkuvote");

        label1.setText("Tegemist on valikvastustega navigatsioonimärkide testiga. Enne järgmise küsimuse juurde suundumist kontrolli vastust. ");
        Label label2 = new Label("Kui oled hädas, siis kliki Spikril - abi saad kasutada kolm korda.");
        label2.getStyleClass().add("kokkuvote");
        Image wheel = new Image("res/Rool.png");
        ImageView ivWheel = new ImageView(wheel);
        ivWheel.setFitHeight(180);
        ivWheel.setPreserveRatio(true);

        vbox.getChildren().addAll(ivWheel, label1, label2);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public void clickOnStart() {
        buttonStart.setOnAction(event -> {
            layout.setCenter(centreVbox());
            buttonHelp.setDisable(false);
            buttonStart.setDisable(true);
            buttonFinish.setDisable(false);
        });
    }
}
