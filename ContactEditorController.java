package addressbook;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactEditorController {
    @FXML private Label errorLabel;
    @FXML private Button okButton;
    @FXML private Button cancelButton;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField nameField;
    @FXML private TextField pseudonymField;
    @FXML private TextField hausePhoneNumberField;
    @FXML private TextField faxPhoneNumberField;
    @FXML private TextField pagerPhoneNumberField;
    @FXML private TextField emailField;             //regexp
    @FXML private TextField phoneNumberField;       //regexp
    @FXML private TextField workPhoneNumberField;   //regexp
    @FXML private TextField secondEmailField;       //regexp
    @FXML private TextField addressField;
    @FXML private TextField cityField;
    @FXML private TextField voivodeshipField;
    @FXML private TextField postalCodeField;
    @FXML private TextField countryField;
    @FXML private TextField websiteField;
    @FXML private DatePicker birthdayField;         //TODO
    @FXML private TextField officeField;
    @FXML private TextField departamentField;
    @FXML private TextField companyNameField;
    @FXML private TextField companyAddressField;
    @FXML private TextField companyAddres;
    @FXML private TextField companyPostalCodeField;
    @FXML private TextField companyCountyField;
    @FXML private TextField companyWebsiteField;
    @FXML private TextField info1Field;
    @FXML private TextField info2Field;
    @FXML private TextField info3Field;
    @FXML private TextField info4Field;
    @FXML private TextArea notesField;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    private boolean hasMinData() {
        if (nameField.getText().isEmpty()==false || firstNameField.getText().isEmpty()==false || lastNameField.getText().isEmpty()==false) {
            return true;
        } else {
            return false;
        }
    }

    private void setError(String error) {
        System.out.println("Error");
        okButton.setDisable(false);
    }

    public void validateField(Event event) throws MalformedURLException {
        TextField source = (TextField)event.getSource();
        if (source==emailField || source==secondEmailField) {
            if (validateEmail(source.getText()) == false) {
                okButton.setDisable(true);
            } else {

            }
        } else if (source==websiteField || source==companyWebsiteField) {
            try {
                URL myURL = new URL(source.getText());
            } catch (MalformedURLException e) {
                System.out.println("Error");
            }

        }
    }

    public void buttonClicked(ActionEvent event) {
        if (event.getSource() == cancelButton) {
            Stage myStage = (Stage)cancelButton.getScene().getWindow();
            myStage.close();
        } else {

        }
    }
}
