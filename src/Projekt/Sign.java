package Projekt;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by maus on 3.12.2015.
 */
public class Sign {
    public ImageView sign;

    public ImageView Sign() {
        int rnd = (int) (Math.random() * 8) + 1;
        Image picture = new Image("Res/Cardinal" + rnd + ".png");
        sign = new ImageView(picture);
        sign.setFitHeight(200);
        sign.setPreserveRatio(true);
        sign.setId(Integer.toString(rnd)); //String int-ks saab Integer.parseInt(string)
        return sign;
    }
}
