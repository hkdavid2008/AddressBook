package addressbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactEditorController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField nameField;
    @FXML private TextField pseudonymField;
    @FXML private TextField hausePhoneNumberField;
    @FXML private TextField faxPhoneNumberField;
    @FXML private TextField pagerPhoneNumberField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField workPhoneNumberField;
    @FXML private TextField secondEmailField;
    @FXML private TextField addressField;
    @FXML private TextField cityField;
    @FXML private TextField voivodeshipField;
    @FXML private TextField postalCodeField;
    @FXML private TextField countryField;
    @FXML private TextField websiteField;
    @FXML private DatePicker birthdayField;
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

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public void validateEmail() {
        //Problem: wziać kliknięty element
    }
}
