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
        editorController.setWebsiteField(contact.getCompanyWebsite());      //TODO - poprawić
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
        //if no errors in controller
        contact.setFirstName(editorController.getFirstNameField());
        contact.setLastName(editorController.getLastNameField());
        contact.setName(editorController.getNameField());
        contact.setPseudonym(editorController.getPseudonymField());
        contact.setHausePhoneNumber(editorController.getHausePhoneNumberField());
        contact.setFaxPhoneNumber(editorController.getFaxPhoneNumberField());
        contact.setPagerPhoneNumber(editorController.getPagerPhoneNumberField());
        contact.setEmail(editorController.getEmailField());
        contact.setPhoneNumber(editorController.getPhoneNumberField());
        contact.setWorkPhoneNumber(editorController.getWorkPhoneNumberField());
        contact.setSecondEmail(editorController.getSecondEmailField());
        contact.setAddress(editorController.getAddressField());
        contact.setCity(editorController.getCityField());
        contact.setVoivodeship(editorController.getVoivodeshipField());
        contact.setPostalCode(editorController.getPostalCodeField());
        contact.setCountry(editorController.getCountryField());
        contact.setCompanyWebsite(editorController.getWebsiteField());  //TODO - poprawić
        contact.setDepartament(editorController.getDepartamentField());
        contact.setCompanyName(editorController.getCompanyNameField());
        contact.setCompanyAddress(editorController.getCompanyAddressField());
        contact.setCompanyPostalCode(editorController.getCompanyPostalCodeField());
        contact.setCompanyCounty(editorController.getCompanyCountyField());     //TODO - country
        contact.setCompanyWebsite(editorController.getCompanyWebsiteField());
        contact.setInfo1(editorController.getInfo1Field());
        contact.setInfo2(editorController.getInfo2Field());
        contact.setInfo3(editorController.getInfo3Field());
        contact.setInfo4(editorController.getInfo4Field());
        contact.setNotes(editorController.getNotesField());
        contact.setBirthday(editorController.getBirthdayField());
        return contact;
    }
}
