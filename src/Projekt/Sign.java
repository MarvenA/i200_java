package Projekt;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by maus on 29.11.2015.
 */
public class Sign {
    static ImageView sign;
    static ArrayList vastused;
    static int   rnd     = (int) (Math.random() * 8) + 1;

    public static Label question() {
        ArrayList<String> labels = new ArrayList();

        labels.add("Milline kardinaalmärk on kujutatud alloleval pildil?");
        labels.add("Mis meremärk on pildil?");
        labels.add("Mis meremärk on alloleval pildil?");

        int   rnd = (int) (Math.random() * (3));
        Label txt = new Label();
        txt.setText(labels.get(rnd));

        return txt;
    }

    public static ImageView generateSign() {

        Image picture = new Image("res/Cardinal" + rnd + ".png");
        sign = new ImageView(picture);
        sign.setFitHeight(200);
        sign.setPreserveRatio(true);
        sign.setId(String.valueOf(rnd)); //String int-ks saab Integer.parseInt(string)
        return sign;
    }

    public static VBox getAnswers() {
        vastused = new ArrayList<String>();
        vastused.add(0, "Faarvaateri parema ääre tooder");
        vastused.add(1, "Idatooder");
        vastused.add(2, "Lõunatooder");
        vastused.add(3, "Läänetooder");
        vastused.add(4, "Põhjatooder");
        vastused.add(5, "Idapoi");
        vastused.add(6, "Lõunapoi");
        vastused.add(7, "Läänepoi");
        vastused.add(8, "Põhjapoi");
        vastused.add(9, "Faarvaateri vasaku ääre tooder");
        VBox v = new VBox();
        RadioButton rb1 = new RadioButton("1");
        RadioButton rb2 = new RadioButton("2");
        RadioButton rb3 = new RadioButton("3");
        RadioButton rb4 = new RadioButton("4");
        int i = 1;

        rb1.setText((String) vastused.get(Integer.parseInt(sign.getId())));

        v.getChildren().addAll(rb1,rb2,rb3,rb4);
        return v;
    }
}
