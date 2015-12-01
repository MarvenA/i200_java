package Projekt;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by maus on 29.11.2015.
 */
public class Sign {
    static ImageView sign;
    private HashMap<String, String> answers;

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
        int   rnd     = (int) (Math.random() * 8) + 1;
        Image picture = new Image("res/Cardinal" + rnd + ".png");
        sign = new ImageView(picture);
        sign.setFitHeight(200);
        sign.setPreserveRatio(true);
        sign.setId(Integer.toString(rnd)); //String int-ks saab Integer.parseInt(string)
        return sign;
    }

    private void Answers() {
        answers = new HashMap<>();

        answers.put("1", "Idatooder");
        answers.put("5", "Idapoi");

        answers.put("2", "Lõunatooder");
        answers.put("4", "Lõunapoi");

        answers.put("3", "Läänetooder");
        answers.put("7", "Läänepoi");

        answers.put("4", "Põhjatooder");
        answers.put("8", "Põhjapoi");

        answers.put("9", "Eraldiseisev oht");
        answers.put("10", "Faarvaateri parem äär");

        answers.put("11", "Faarvaateri vasak äär");
    }

}
