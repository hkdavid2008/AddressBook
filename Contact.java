package addressbook;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Contact {
    private int id=-1;
    private StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private StringProperty name = new SimpleStringProperty(this, "name", "");
    private StringProperty pseudonym = new SimpleStringProperty(this, "pseudonym", "");
    private StringProperty website = new SimpleStringProperty(this, "website", "");
    private StringProperty hausePhoneNumber = new SimpleStringProperty(this, "hausePhoneNumber", "");
    private StringProperty faxPhoneNumber = new SimpleStringProperty(this, "faxPhoneNumber", "");
    private StringProperty pagerPhoneNumber = new SimpleStringProperty(this, "pagerPhoneNumber", "");
    private StringProperty email = new SimpleStringProperty(this, "email", "");
    private StringProperty mobilePhoneNumber = new SimpleStringProperty(this, "mobilePhoneNumber", "");
    private StringProperty workPhoneNumber = new SimpleStringProperty(this, "workPhoneNumber", "");
    private StringProperty secondEmail = new SimpleStringProperty(this, "secondEmail", "");
    private StringProperty address = new SimpleStringProperty(this, "address", "");
    private StringProperty city = new SimpleStringProperty(this, "city", "");
    private StringProperty voivodeship = new SimpleStringProperty(this, "voivodeship", "");
    private StringProperty postalCode = new SimpleStringProperty(this, "postalCode", "");
    private StringProperty country = new SimpleStringProperty(this, "country", "");
    private LocalDate birthday;
    private StringProperty office = new SimpleStringProperty(this, "office", "");
    private StringProperty departament = new SimpleStringProperty(this, "departament", "");
    private StringProperty companyName = new SimpleStringProperty(this, "companyName", "");
    private StringProperty companyAddress = new SimpleStringProperty(this, "companyAddress", "");
    private StringProperty companyPostalCode = new SimpleStringProperty(this, "companyPostalCode", "");
    private StringProperty companyCountry = new SimpleStringProperty(this, "companyCountry", "");
    private StringProperty companyWebsite = new SimpleStringProperty(this, "companyWebsite", "");
    private StringProperty info1 = new SimpleStringProperty(this, "info1", "");
    private StringProperty info2 = new SimpleStringProperty(this, "info2", "");
    private StringProperty info3 = new SimpleStringProperty(this, "info3", "");
    private StringProperty info4 = new SimpleStringProperty(this, "info4", "");
    private StringProperty notes = new SimpleStringProperty(this, "notes", "");

    public Contact() {
        birthday = LocalDate.of(1997,10,7);
    }

    public Contact(String firstName, String lastName, String mobilePhoneNumber, String email) {
        //this.firstName = firstName;
        this.firstName.setValue(firstName);
        this.lastName.setValue(lastName);
        this.email.setValue(email);
        this.mobilePhoneNumber.setValue(mobilePhoneNumber);
        birthday = LocalDate.of(1997,10,7);
    }

    public String getWebsite() {
        return website.get();
    }

    public StringProperty websiteProperty() {
        return website;
    }

    public void setWebsite(String website) {
        this.website.set(website);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPseudonym() {
        return pseudonym.get();
    }

    public StringProperty pseudonymProperty() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym.set(pseudonym);
    }

    public String getHausePhoneNumber() {
        return hausePhoneNumber.get();
    }

    public StringProperty hausePhoneNumberProperty() {
        return hausePhoneNumber;
    }

    public void setHausePhoneNumber(String hausePhoneNumber) {
        this.hausePhoneNumber.set(hausePhoneNumber);
    }

    public String getFaxPhoneNumber() {
        return faxPhoneNumber.get();
    }

    public StringProperty faxPhoneNumberProperty() {
        return faxPhoneNumber;
    }

    public void setFaxPhoneNumber(String faxPhoneNumber) {
        this.faxPhoneNumber.set(faxPhoneNumber);
    }

    public String getPagerPhoneNumber() {
        return pagerPhoneNumber.get();
    }

    public StringProperty pagerPhoneNumberProperty() {
        return pagerPhoneNumber;
    }

    public void setPagerPhoneNumber(String pagerPhoneNumber) {
        this.pagerPhoneNumber.set(pagerPhoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber.get();
    }

    public StringProperty mobilePhoneNumberProperty() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber.set(mobilePhoneNumber);
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber.get();
    }

    public StringProperty workPhoneNumberProperty() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber.set(workPhoneNumber);
    }

    public String getSecondEmail() {
        return secondEmail.get();
    }

    public StringProperty secondEmailProperty() {
        return secondEmail;
    }

    public void setSecondEmail(String secondEmail) {
        this.secondEmail.set(secondEmail);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getVoivodeship() {
        return voivodeship.get();
    }

    public StringProperty voivodeshipProperty() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship.set(voivodeship);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getOffice() {
        return office.get();
    }

    public StringProperty officeProperty() {
        return office;
    }

    public void setOffice(String office) {
        this.office.set(office);
    }

    public String getDepartament() {
        return departament.get();
    }

    public StringProperty departamentProperty() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament.set(departament);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getCompanyAddress() {
        return companyAddress.get();
    }

    public StringProperty companyAddressProperty() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress.set(companyAddress);
    }

    public String getCompanyPostalCode() {
        return companyPostalCode.get();
    }

    public StringProperty companyPostalCodeProperty() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode.set(companyPostalCode);
    }

    public String getCompanyCountry() {
        return companyCountry.get();
    }

    public StringProperty companyCountryProperty() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry.set(companyCountry);
    }

    public String getCompanyWebsite() {
        return companyWebsite.get();
    }

    public StringProperty companyWebsiteProperty() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite.set(companyWebsite);
    }

    public String getInfo1() {
        return info1.get();
    }

    public StringProperty info1Property() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1.set(info1);
    }

    public String getInfo2() {
        return info2.get();
    }

    public StringProperty info2Property() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2.set(info2);
    }

    public String getInfo3() {
        return info3.get();
    }

    public StringProperty info3Property() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3.set(info3);
    }

    public String getInfo4() {
        return info4.get();
    }

    public StringProperty info4Property() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4.set(info4);
    }

    public String getNotes() {
        return notes.get();
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }
}
