package addressbook;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

@SuppressWarnings("Convert2Lambda")
public class ContactViewer extends AnchorPane {

    private Contact currentContact;

    @FXML private GridPane grid;

    @FXML private Label nameLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label secondEmailLabel;
    @FXML private Label officeLabel;
    @FXML private Label departamentLabel;
    @FXML private Label companyNameLabel;
    @FXML private Label companyAddressLabel;
    @FXML private Label companyWebsiteLabel;
    @FXML private Label workPhoneNumber;
    @FXML private Label hausePhoneNumberLabel;
    @FXML private Label faxPhoneNumberLabel;
    @FXML private Label mobilePhoneNumberLabel;
    @FXML private Label pagerPhoneNumberLabel;
    @FXML private Label addressLabel;
    @FXML private Label cityLabel;
    @FXML private Label voivodeshipLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label websiteLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label info1Label;
    @FXML private Label info2Label;


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
    
    public void setContact(Contact contact) {
        currentContact = contact;

        currentContact.nameProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()==false) {
                    nameLabel.setText(newValue);
                } else {
                    nameLabel.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
                }
            }
        });

        currentContact.firstNameProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (currentContact.getName().isEmpty()) {
                    nameLabel.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
                }
            }
        });

        currentContact.lastNameProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (currentContact.getName().isEmpty()) {
                    nameLabel.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
                }
            }
        });

        if (currentContact.getName().isEmpty()==true) {
            nameLabel.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
        } else {
            nameLabel.setText(currentContact.getName());
        }

        lastNameLabel.textProperty().bind(currentContact.lastNameProperty());
        firstNameLabel.textProperty().bind(currentContact.firstNameProperty());
        emailLabel.textProperty().bind(currentContact.emailProperty());
        secondEmailLabel.textProperty().bind(currentContact.secondEmailProperty());
        officeLabel.textProperty().bind(currentContact.officeProperty());
        departamentLabel.textProperty().bind(currentContact.departamentProperty());
        companyNameLabel.textProperty().bind(currentContact.companyAddressProperty());
        companyAddressLabel.textProperty().bind(currentContact.addressProperty());
        companyWebsiteLabel.textProperty().bind(currentContact.companyWebsiteProperty());
        workPhoneNumber.textProperty().bind(currentContact.workPhoneNumberProperty());
        hausePhoneNumberLabel.textProperty().bind(currentContact.hausePhoneNumberProperty());
        faxPhoneNumberLabel.textProperty().bind(currentContact.faxPhoneNumberProperty());
        mobilePhoneNumberLabel.textProperty().bind(currentContact.mobilePhoneNumberProperty());
        pagerPhoneNumberLabel.textProperty().bind(currentContact.pagerPhoneNumberProperty());
        addressLabel.textProperty().bind(currentContact.addressProperty());
        cityLabel.textProperty().bind(currentContact.cityProperty());
        voivodeshipLabel.textProperty().bind(currentContact.voivodeshipProperty());
        postalCodeLabel.textProperty().bind(currentContact.postalCodeProperty());
        websiteLabel.textProperty().bind(currentContact.websiteProperty());
        //birthdayLabel.textProperty().bind(currentContact);        //TODO poprawić datę
        info1Label.textProperty().bind(currentContact.info1Property());
        info2Label.textProperty().bind(currentContact.info2Property());
    }
}
