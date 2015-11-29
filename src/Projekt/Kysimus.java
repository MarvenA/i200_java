package Projekt;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.LiteralNode;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by maus on 10.11.2015.
 */
public class Kysimus extends Application {
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
    Label question;
    ImageView sign;
    ToggleGroup vastused;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    ArrayList countRight;
    ArrayList countWrong;
    ArrayList<RadioButton> list;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Koosta mängu layout
        window = primaryStage;
        setLayout();
        clickOnHelp();
        clickOnNext();
        clickOnCheck();
        clickOnFinish();
    }

    public HBox checkNext() {
        checkNext = new HBox();
        checkNext.setPadding(new Insets(10, 0, 10, 10));
        checkNext.setSpacing(15);
        checkNext.setAlignment(Pos.CENTER);

        buttonCheck = new Button("Kontrolli");
        buttonCheck.getStyleClass().add("buttonChckNxt");
        buttonNext = new Button("Järgmine");
        buttonNext.getStyleClass().add("buttonChckNxt");
        buttonNext.setDisable(true);

        checkNext.getChildren().addAll(buttonCheck, buttonNext);
        return checkNext;
    }

    public ImageView generateSign() {
        int   rnd     = (int) (Math.random() * 8) + 1;
        Image picture = new Image("res/Cardinal" + rnd + ".png");
        sign = new ImageView(picture);
        sign.setFitHeight(200);
        sign.setPreserveRatio(true);
        sign.setId(Integer.toString(rnd)); //String int-ks saab Integer.parseInt(string)
        return sign;
    }

    public VBox answers() {
        vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 0, 0, 20));

        ToggleGroup vastused = new ToggleGroup();
        rb1 = new RadioButton("Põhjatooder");
        rb1.setUserData("Põhjatooder");
        rb2 = new RadioButton("Lõunatooder");
        rb2.setUserData("Lõunatooder");
        rb3 = new RadioButton("Idatooder");
        rb3.setUserData("Idatooder");
        rb4 = new RadioButton("Läänetooder");
        rb4.setUserData("Läänetooder");
        rb1.setToggleGroup(vastused);
        rb2.setToggleGroup(vastused);
        rb3.setToggleGroup(vastused);
        rb4.setToggleGroup(vastused);

        list = new ArrayList<>();
        list.add(rb1);
        list.add(rb2);
        list.add(rb3);
        list.add(rb4);

        //Paneb vastused erinevas järjekorras (Lisada see clickOnNextButtonile)
        for (int i = 0; i < 4; i++) {
            int rnd = (int) (Math.random() * (4 - i));
            vbox.getChildren().add(list.get(rnd));
            list.remove(rnd);
        }
        return vbox;
    }

    private VBox centreVbox() {
        centreVbox = new VBox();
        centreVbox.getStyleClass().add("Vbox");

        question = new Label("Milline meremärk on kujutatud alloleval pildil xxxxxxxxxxxxxxxxx?");
        centreVbox.getChildren().addAll(question, generateSign(), answers(), checkNext());

        return centreVbox;
    }

    private void bottomMenu() {
        bottomMenu = new HBox();
        bottomMenu.setPadding(new Insets(10, 0, 10, 10));
        bottomMenu.setSpacing(15);
        bottomMenu.setAlignment(Pos.CENTER);

        buttonHelp = new Button("Spikker");
        buttonHelp.setPrefSize(100, 18);

        bottomMenu.getChildren().add(buttonHelp);
    }

    private void clickOnHelp() {
        buttonHelp.setOnAction(e -> {
            windowHelp = new Stage();
            windowHelp.setTitle("Kardinaalmärgid");

            Image     image1  = new Image("res/Spikker.png");
            ImageView Spikker = new ImageView();
            Spikker.setImage(image1);

            StackPane stack        = new StackPane();
            Scene     sceneSpikker = new Scene(stack);
            stack.getChildren().add(Spikker);

            windowHelp.setScene(sceneSpikker);
            windowHelp.setResizable(false);
            windowHelp.show();
        });
    }

    private void clickOnNext() {
        buttonNext.setOnAction(event -> {
            rb1.setSelected(false);
            rb2.setSelected(false);
            rb3.setSelected(false);
            rb4.setSelected(false);

            centreVbox.getChildren().remove(1, 2);
            centreVbox.getChildren().add(1, generateSign());
            centreVbox.getChildren().remove(2, 3);
            centreVbox.getChildren().add(2, answers());

            buttonNext.setDisable(true);
            buttonCheck.setDisable(false);
        });
    }

    private void clickOnCheck() {
        countRight = new ArrayList();
        countWrong = new ArrayList();

        buttonCheck.setOnAction(event -> {
            if (rb1.isSelected() && (sign.getId().equals("4") || sign.getId().equals("8"))) {
                countRight.add(1);
            } else if (rb2.isSelected() && (sign.getId().equals("2") || sign.getId().equals("6"))) {
                countRight.add(1);
            } else if (rb3.isSelected() && (sign.getId().equals("1") || sign.getId().equals("5"))) {
                countRight.add(1);
            } else if (rb4.isSelected() && (sign.getId().equals("3") || sign.getId().equals("7"))) {
                countRight.add(1);
            } else {
                countWrong.add(1);
            }
            buttonNext.setDisable(false);
            buttonCheck.setDisable(true);
        });
    }

    private void clickOnFinish() {
        buttonFinish.setOnAction(event -> {
            int   sum    = countRight.size() + countWrong.size();
            Label total  = new Label("Vastasid kokku " + sum + " küsimusele.");
            Label countR = new Label("Õigeid vastuseid: " + countRight.size());
            Label countW = new Label("Valesid vastuseid: " + countWrong.size());
            Image wheel = new Image("res/Rool.png");
            ImageView ivWheel = new ImageView(wheel);
            ivWheel.setFitHeight(120);
            ivWheel.setPreserveRatio(true);

            Image anchor = new Image("res/Anchor.png");
            ImageView ivAnchor = new ImageView(anchor);
            ivAnchor.setFitHeight(120);
            ivAnchor.setPreserveRatio(true);

            VBox kokkuvote = new VBox();
            kokkuvote.getChildren().addAll(ivWheel, total, countR, countW,ivAnchor);
            kokkuvote.setAlignment(Pos.CENTER);
            kokkuvote.getStyleClass().add("kokkuvote");

            layOut.setCenter(kokkuvote);
        });
    }

    private void topMenu() {
        topMenu = new HBox();
        topMenu.getStyleClass().add("topMenu");

        buttonStart = new Button("Alusta");
        buttonFinish = new Button("Lõpeta");
        topMenu.getChildren().addAll(buttonStart, buttonFinish);
    }

    private void setLayout() {
        layOut = new BorderPane();

        topMenu();
        layOut.setTop(topMenu);
        layOut.setCenter(centreVbox());

        bottomMenu();
        layOut.setBottom(bottomMenu);

        Scene scene = new Scene(layOut);
        scene.getStylesheets().add("Projekt/style.css");
        window.setScene(scene);
        window.setTitle("Test");
        window.show();
        window.setOnCloseRequest(event -> System.exit(0));
    }
}
