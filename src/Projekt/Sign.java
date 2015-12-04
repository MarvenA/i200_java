package Projekt;

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

        return  answers;
    }
}
