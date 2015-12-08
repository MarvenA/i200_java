package Projekt;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
public class Kysimus {
    Stage window;
    Stage windowHelp;
    BorderPane layOut;
    HBox topMenu;
    VBox centreVbox;
    HBox bottomMenu;
    HBox checkNext;
    VBox vbox;
    Button buttonStart;
    Button buttonFinish;
    Button buttonCheck;
    Button buttonNext;
    Button buttonHelp;
    ArrayList countRight;
    ArrayList countWrong;
    ArrayList<RadioButton> list;
    Text question = new Text();
    Sign sign = new Sign();

    public Kysimus() {
        window = new Stage();
        setLayout();
        clickOnHelp();
        clickOnNext();
        clickOnCheck();
        clickOnFinish();
    }

    public HBox checkNext() {
        checkNext = new HBox();
        checkNext.getStyleClass().add("Hbox");

        buttonCheck = new Button("Kontrolli");
        buttonNext = new Button("Järgmine");
        buttonNext.setDisable(true);
        checkNext.getChildren().addAll(buttonCheck, buttonNext);
        return checkNext;
    }

    private void clickOnHelp() {
        buttonHelp.setOnAction(e -> {
            windowHelp = new Stage();
            windowHelp.setTitle("Kardinaalmärgid");

            Image image1 = new Image("res/Spikker.png");
            ImageView Spikker = new ImageView();
            Spikker.setImage(image1);

            StackPane stack = new StackPane();
            Scene sceneSpikker = new Scene(stack);
            stack.getChildren().add(Spikker);

            windowHelp.setScene(sceneSpikker);
            windowHelp.setResizable(false);
            windowHelp.show();
        });
    }

    private void clickOnNext() {
        buttonNext.setOnAction(event -> {
            sign.rb1.setSelected(false);
            sign.rb1.setSelected(false);
            sign.rb1.setSelected(false);
            sign.rb1.setSelected(false);

            centreVbox.getChildren().remove(0, 1);
            centreVbox.getChildren().add(0, question.newText());
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

            Image wheel = new Image("res/Rool.png");
            ImageView ivWheel = new ImageView(wheel);
            ivWheel.setFitHeight(120);
            ivWheel.setPreserveRatio(true);

            Image anchor = new Image("res/Anchor.png");
            ImageView ivAnchor = new ImageView(anchor);
            ivAnchor.setFitHeight(120);
            ivAnchor.setPreserveRatio(true);

            VBox kokkuvote = new VBox();
            kokkuvote.getChildren().addAll(ivWheel, total, countR, countW, ivAnchor);
            kokkuvote.getStyleClass().add("kokkuvote");

            layOut.getChildren().removeAll(topMenu, bottomMenu);
            layOut.setCenter(kokkuvote);
        });
    }

    private HBox topMenu() {
        topMenu = new HBox();
        topMenu.getStyleClass().add("Hbox");
        buttonStart = new Button("Alusta");
        buttonFinish = new Button("Lõpeta");
        topMenu.getChildren().addAll(buttonStart, buttonFinish);
        return topMenu;
    }

    private VBox centreVbox() {
        centreVbox = new VBox();
        centreVbox.getStyleClass().add("Vbox");
        centreVbox.getChildren().addAll(question.newText(), sign.generateSign(), sign.answers(), checkNext());
        return centreVbox;
    }

    private HBox bottomMenu() {
        bottomMenu = new HBox();
        bottomMenu.getStyleClass().add("Hbox");
        buttonHelp = new Button("Spikker");
        bottomMenu.getChildren().add(buttonHelp);
        return bottomMenu;
    }

    private void setLayout() {
        layOut = new BorderPane();
        layOut.setTop(topMenu());
        layOut.setCenter(centreVbox());
        layOut.setBottom(bottomMenu());

        Scene scene = new Scene(layOut);
        scene.getStylesheets().add("Projekt/style.css");
        window.setScene(scene);
        window.setTitle("Navigatsioonimärgid Eesti vetes");
        window.show();
        window.setOnCloseRequest(event -> System.exit(0));
    }
}
