package addressbook;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class ContactEditor {

    private Contact contact;
    private ContactEditorController editorController;

    public ContactEditor() throws IOException  {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactEditorForm.fxml"));
        Parent form = loader.load();
        editorController = loader.getController();
        setContact(new Contact());

        newWindow.setScene(new Scene(form));
        newWindow.showAndWait();
    }

    public ContactEditor(Contact contactEdit) throws IOException  {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactEditorForm.fxml"));
        Parent form = loader.load();
        editorController = loader.getController();
        setContact(contactEdit);

        newWindow.setScene(new Scene(form));
        newWindow.showAndWait();
    }


    public void setContact(Contact editContact) {
        contact = editContact;
        editorController.setFirstNameField(contact.getFirstName());
        editorController.setLastNameField(contact.getLastName());
        editorController.setNameField(contact.getName());
        editorController.setPseudonymField(contact.getPseudonym());
        editorController.setHausePhoneNumberField(contact.getHausePhoneNumber());
        editorController.setFaxPhoneNumberField(contact.getFaxPhoneNumber());
        editorController.setPagerPhoneNumberField(contact.getPagerPhoneNumber());
        editorController.setEmailField(contact.getEmail());
        editorController.setPhoneNumberField(contact.getPhoneNumber());
        editorController.setWorkPhoneNumberField(contact.getWorkPhoneNumber());
        editorController.setSecondEmailField(contact.getSecondEmail());
        editorController.setAddressField(contact.getAddress());
        editorController.setCityField(contact.getCity());
        editorController.setVoivodeshipField(contact.getVoivodeship());
        editorController.setPostalCodeField(contact.getPostalCode());
        editorController.setCountryField(contact.getCountry());
        editorController.setWebsiteField(contact.getCompanyWebsite());
        editorController.setOfficeField(contact.getOffice());
        editorController.setDepartamentField(contact.getDepartament());
        editorController.setCompanyNameField(contact.getCompanyName());
        editorController.setCompanyAddressField(contact.getCompanyAddress());
        editorController.setCompanyPostalCodeField(contact.getCompanyPostalCode());
        editorController.setCompanyCountyField(contact.getCompanyCounty());
        editorController.setCompanyWebsiteField(contact.getCompanyWebsite());
        editorController.setInfo1Field(contact.getInfo1());
        editorController.setInfo2Field(contact.getInfo2());
        editorController.setInfo3Field(contact.getInfo3());
        editorController.setInfo4Field(contact.getInfo4());
        editorController.setNotesField(contact.getNotes());
        editorController.setBirthdayField(contact.getBirthday());
    }

    public Contact getContact() {
        return contact;
    }
}
