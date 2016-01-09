package Projekt;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * Created by maus on 10.11.2015.
 * Küsimuse klass sisaldab endas kõike, mis on seotud javaFX-ga
 * *
 */
public class Test {
    Stage stage = new Stage();
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
    Button btnStrtAgn;
    Button btnClose;

    Integer countRight = 0;
    Integer countWrong = 0;
    Sign sign = new Sign();
    Help help = new Help();

    public Test() {
        setupScene();
    }

    //Kontrollimise ja järgmise küsimuse juurde liikumise nupud
    //Nupud paigutatakse Hboxi, mis läheb borderpane'i keskel asuva Vboxi viimaseks elemendiks
    private HBox checkNext() {
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

    private void clickOnNext() {
        buttonNext.setOnAction(event -> {
            //Vana küsimus, pilt ja vastused vahetatakse uute vastu
            //Esmalt eemaldatakse Vboxi vastavalt kohalt ja seejärel pannakse sama koha peale uus
            centreVbox.getChildren().remove(0);
            centreVbox.getChildren().add(0, sign.newQuestion());
            centreVbox.getChildren().remove(1);
            centreVbox.getChildren().add(1, sign.generateSign());
            centreVbox.getChildren().remove(2);
            try {
                centreVbox.getChildren().add(2, sign.answers());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Sulgeb spikri akna, kui see on lahti jäetud
            help.stageClose();
            //Järgmise küsimuse nupp muutub mittaktiivseks, kontrolli nupp mitteaktiivseks
            buttonNext.setDisable(true);
            buttonCheck.setDisable(false);
        });
    }

    private void clickOnCheck() {
        buttonCheck.setOnAction(event -> {
            //Kasutaja poolt tehtud valiku kontroll
            //Sign klassis pannakse õige vastus alati sama radiobuttoni (rb1) nimetuseks
            //Kontrollitakse, kas rb1 on valitud või mitte
            if (sign.rb1.isSelected()) {
                countRight++;
                //Õige vastus värvitakse roheliseks
                sign.rb1.getStyleClass().add("radio-correct");
            } else {
                countWrong++;
                //Et samal ajal saaks olla sisse lülitatud kaks valikut, tuleb rb1 üks togglegroupist välja arvata!
                sign.rb1.setToggleGroup(null);
                sign.rb1.setSelected(true);
                sign.rb1.getStyleClass().add("radio-correct");
                //Kontrollitakse, milline valedest valikutest on valitud ja värvitakse roheliseks
                if (sign.rb2.isSelected()) sign.rb2.getStyleClass().add("radio-incorrect");
                else if (sign.rb3.isSelected()) sign.rb3.getStyleClass().add("radio-incorrect");
                else if (sign.rb4.isSelected()) sign.rb4.getStyleClass().add("radio-incorrect");
            }
            //Pärast kontrollimist muutub järgmise küsimuse nupp aktiivseks
            buttonNext.setDisable(false);
            buttonCheck.setDisable(true);
        });
    }

    //Ülemise menüü nupud paigutatakse Hboxi
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

    //Alusta nupu vajutamisel alustatakse testiga, spikri ja lõpetamise nupp muutub aktiivseks.
    private void clickOnStart() {
        buttonStart.setOnAction(event -> {
            try {
                layout.setCenter(centreVbox());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            buttonHelp.setDisable(false);
            buttonStart.setDisable(true);
            buttonFinish.setDisable(false);
        });
    }

    private void clickOnFinish() {
        buttonFinish.setOnAction(event -> {
            //Borderpane'lt eemaldatakse ülemine ja alumine menüü, jääb ainult kokkuvõte
            layout.getChildren().removeAll(topMenu, bottomMenu);

            Label total = new Label("Vastasid kokku " + (countRight+countWrong) + " küsimusele.");
            Label countR = new Label("Õigeid vastuseid:   " + countRight);
            Label countW = new Label("Valesid vastuseid:   " + countWrong);

            Image compass = new Image("res/Kompass.png");
            ImageView ivCompass = new ImageView(compass);
            ivCompass.setFitHeight(180);
            ivCompass.setPreserveRatio(true);

            Image anchor = new Image("res/Anchor.png");
            ImageView ivAnchor = new ImageView(anchor);
            ivAnchor.setFitHeight(180);
            ivAnchor.setPreserveRatio(true);

            btnStrtAgn = new Button("Alusta uuesti");
            clickOnStrtAgn();
            btnClose = new Button("Sulge");
            clickOnClose();

            bottomMenu.getChildren().removeAll(buttonHelp);
            bottomMenu.getChildren().addAll(btnStrtAgn, btnClose);

            VBox kokkuvote = new VBox();
            kokkuvote.getChildren().addAll(ivCompass, total, countR, countW, ivAnchor, bottomMenu);
            kokkuvote.getStyleClass().add("kokkuvote");

            layout.setCenter(kokkuvote);
        });
    }

    //Valides "Alusta uuesti" käivitatakse programm algusest ja nullitakse loendurid
    private void clickOnStrtAgn() {
        btnStrtAgn.setOnAction(event -> {
            countRight = 0;
            countWrong = 0;
            help.clearCount();
            sign.clearCount();
            buttonHelp.setDisable(false);
            setupScene();
        });
    }

    private void clickOnClose() {
        btnClose.setOnAction(event -> stage.close());
    }

    private VBox centreVbox() throws FileNotFoundException {
        centreVbox = new VBox();
        centreVbox.getStyleClass().add("Vbox");

        centreVbox.getChildren().addAll(sign.newQuestion(), sign.generateSign(), sign.answers(), checkNext());
        return centreVbox;
    }

    //Alumise menüü jaoks on samuti Hbox, et jääks võimalus nuppe juurde lisada.
    private HBox bottomMenu() {
        bottomMenu = new HBox();
        bottomMenu.getStyleClass().add("Hbox");

        buttonHelp = new Button("Spikker (" + (3 - help.getCount()) + ")");
        buttonHelp.setDisable(true);
        clickOnHelp();

        bottomMenu.getChildren().add(buttonHelp);
        return bottomMenu;
    }

    private void clickOnHelp() {
        buttonHelp.setOnAction(e -> {
            help.kuvaHelp();
            buttonHelp.setText("Spikker (" + (3 - help.getCount()) + ")");
            //Kui spikrit on kolm korda vaadatud lülitub spikri nupp välja
            if (help.checkCount()) {
                buttonHelp.setDisable(true);
            }
        });
    }

    //Layout on Borderpane, mille sisse pannakse kaks Hboxi (top ja botoom) ning keskmiseks läheb Vbox.
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

    //Programmi käivitamisel avanev sissejuhatav info
    private VBox introduction() {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("kokkuvote");

        Label label1 = new Label("Tegemist on valikvastustega navigatsioonimärkide testiga. Enne järgmise küsimuse juurde suundumist kontrolli vastust. ");
        Label label2 = new Label("Kui oled hädas, siis kliki Spikril - abi saad kasutada kolm korda.");

        Image wheel = new Image("res/Rool.png");
        ImageView ivWheel = new ImageView(wheel);
        ivWheel.setFitHeight(180);
        ivWheel.setPreserveRatio(true);

        vbox.getChildren().addAll(label1, ivWheel, label2);
        return vbox;
    }
}
