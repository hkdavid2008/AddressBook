package addressbook;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ContactViewer extends AnchorPane {

    public ContactViewer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactViewerForm"));
        Parent form = loader.load();
    }
}
