package addressbook;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WelcomeScreen extends AnchorPane {

    public WelcomeScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WelcomeScreenForm.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
