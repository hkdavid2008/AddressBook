package addressbook;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class ContactEditor {

    private Contact contact;

    public ContactEditor() throws IOException {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);

        Parent form = FXMLLoader.load(getClass().getResource("ContactEditorForm.fxml"));

        newWindow.setScene(new Scene(form));
        newWindow.showAndWait();
    }

    public void setContact(Contact editContact) {
        contact = editContact;
    }

    public Contact getContact() {
        return contact;
    }
}
