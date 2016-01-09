package Projekt;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by maus on 3.12.2015.
 */
public class Sign {
    public ImageView sign;
    public VBox answers;
    public Label text;
    Integer count = 0;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb4;
    RadioButton rb3;
    ToggleGroup radiobuttons;
    Integer rnd;

    //Genereerib küsimuse
    public Label newQuestion() {
        ArrayList<String> labels = new ArrayList<String>();
        count++;
        text = new Label();
        labels.add("Millise meremärgiga on tegemist?");
        labels.add("Mis meremärk on kujutatud alloleval pildil?");
        labels.add("Mis meremärk on pildil?");
        labels.add("Mis meremärk on alloleval pildil?");

        int rand = (int) (Math.random() * (labels.size()));
        text.setText(count + ". " + labels.get(rand));
        text.getStyleClass().add("kokkuvote");
        return text;
    }

    public void clearCount() {
        count = 0;
    }

    //Pildi valimine Res kausta salvestatud piltide seast
    public ImageView generateSign() {
        rnd = (int) (Math.random() * 15);
        Image picture = new Image("Res/Cardinal" + rnd + ".png");
        sign = new ImageView(picture);

        sign.setFitHeight(200);
        sign.setPreserveRatio(true);
        return sign;
    }

    //Testi vastuste osa. Meetod tagastab Vboxi, kuhu sisse on pandud 4 vastust
    public VBox answers() throws FileNotFoundException {
        answers = new VBox();
        answers.getStyleClass().add("vastused");

        //Igale Res kaustas sisalduva pildi nimetus pannakse ArrayListi vastavale kohale
        //Pildi Cardinal0.png nimetus läheb kohale 0, Cardinal1.png nimetus kohale 1 jne
        ArrayList<String> a = new ArrayList<>();

        //Vastused loetakse vastuste failist ja salvestatakse Arraylisti.
        //Failist lugemise idee KirkeN projektist (nippide fail) - https://github.com/kirkeN/JavaProjektPrykkar/blob/master/src/Nipp.java
        //Koodi osa muudetud vastavalt StackOverflow-le - http://stackoverflow.com/questions/11918747/reading-txt-file-contents-and-storing-in-array
        Scanner sc = new Scanner(new File("C:\\Users\\maus\\Code\\i200_java\\vastused"));
        while (sc.hasNextLine()) {
            a.add(sc.nextLine());
        }

        //Juhuslikult genereeritud pildi nimetus ehk siis õige vastus pannakse rb1 nimeks
        //Ülejäänud kolmele valitakse nimi pildi nimetuste ArrayListist
        //Samal ajal eemaldatakse juba kasutatud nimetus ArrayListist, et vältida kahe sama nimetuse sattumist nelja vastuse hulka
        rb1 = new RadioButton(a.get(rnd));
        a.remove(rb1.getText());
        rb2 = new RadioButton(a.get((int) (Math.random() * (a.size()))));
        a.remove(rb2.getText());
        rb3 = new RadioButton(a.get((int) (Math.random() * (a.size()))));
        a.remove(rb3.getText());
        rb4 = new RadioButton(a.get((int) (Math.random() * (a.size()))));
        a.remove(rb4.getText());

        //Togglegroup on selleks, et korraga saaks valitud olla üks radiobutton
        radiobuttons = new ToggleGroup();
        rb1.setToggleGroup(radiobuttons);
        rb2.setToggleGroup(radiobuttons);
        rb3.setToggleGroup(radiobuttons);
        rb4.setToggleGroup(radiobuttons);

        //Vasstuste lisamiseks Vboxi erinevas järjekorras lisatakse need ArrayListi,
        ArrayList<RadioButton> list = new ArrayList<>();
        list.add(rb1);
        list.add(rb2);
        list.add(rb3);
        list.add(rb4);

        for (int i = 0; i < 4; i++) {
            int rnd = (int) (Math.random() * list.size());
            answers.getChildren().add(list.get(rnd));
            list.remove(rnd);
        }
        return answers;
    }

}
