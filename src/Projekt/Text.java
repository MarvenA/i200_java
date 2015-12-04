package Projekt;

import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Created by maus on 3.12.2015.
 */
public class Text {
    public Label text = new Label("Millise meremärgiga on tegemist?");

    public Label newText() {
        ArrayList<String> labels = new ArrayList();

        labels.add("Mis meremärk on kujutatud alloleval pildil?");
        labels.add("Mis meremärk on pildil?");
        labels.add("Mis meremärk on alloleval pildil?");

        int rnd = (int) (Math.random() * (labels.size()));
        text.setText(labels.get(rnd));
        return text;
    }
}
