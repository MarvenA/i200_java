package Projekt;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by maus on 3.12.2015.
 */
public class Sign {
    public ImageView sign;
    public VBox answers;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb4;
    RadioButton rb3;
    ToggleGroup radiobuttons;
    String number;

    public ImageView generateSign() {
        int rnd = (int) (Math.random() * 8) + 1;
        Image picture = new Image("Res/Cardinal" + rnd + ".png");
        sign = new ImageView(picture);
        sign.setFitHeight(200);
        sign.setPreserveRatio(true);
        number = Integer.toString(rnd);
        return sign;
    }

    public VBox answers() {
        answers = new VBox();
        answers.getStyleClass().add("vastused");

        ArrayList<String> a = new ArrayList<>();
        a.add("Faarvaatteri parema ääre märk");
        a.add("Idatooder");
        a.add("Lõunatooder");
        a.add("Läänetooder");
        a.add("Põhjatooder");
        a.add("Idapoi");
        a.add("Lõunapoi");
        a.add("Läänepoi");
        a.add("Põhjapoi");
        a.add("Faarvaatteri vasaku ääre märk");

        rb1 = new RadioButton(a.get(Integer.parseInt(number)));
        a.remove(rb1.getText());
        rb2 = new RadioButton(a.get((int) (Math.random() * (a.size()))));
        a.remove(rb2.getText());
        rb3 = new RadioButton(a.get((int) (Math.random() * (a.size()))));
        a.remove(rb3.getText());
        rb4 = new RadioButton(a.get((int) (Math.random() * (a.size()))));
        a.remove(rb4.getText());

        radiobuttons = new ToggleGroup();
        rb1.setToggleGroup(radiobuttons);
        rb2.setToggleGroup(radiobuttons);
        rb3.setToggleGroup(radiobuttons);
        rb4.setToggleGroup(radiobuttons);

        ArrayList<RadioButton> list = new ArrayList<>();
        list.add(rb1);
        list.add(rb2);
        list.add(rb3);
        list.add(rb4);

        for (int i = 0; i < 4; i++) {
            int rnd = (int) (Math.random() * (4 - i));
            answers.getChildren().add(list.get(rnd));
            list.remove(rnd);
        }
        return answers;
    }
}
