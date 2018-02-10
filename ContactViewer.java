package addressbook;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ContactViewer extends AnchorPane {

    //@FXML private Label

    public ContactViewer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ContactViewerForm.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
