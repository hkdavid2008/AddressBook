package addressbook;

import java.time.LocalDate;

public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String name;
    private String pseudonym;
    private String hausePhoneNumber;
    private String faxPhoneNumber;
    private String pagerPhoneNumber;
    private String email;
    private String phoneNumber;
    private String workPhoneNumber;
    private String secondEmail;
    private String address;
    private String city;
    private String voivodeship;
    private String postalCode;
    private String country;
    private LocalDate birthday;
    private String office;
    private String departament;
    private String companyName;
    private String companyAddress;
    private String companyAddres;
    private String companyPostalCode;
    private String companyCounty;
    private String companyWebsite;
    private String info1;
    private String info2;
    private String info3;
    private String info4;
    private String notes;

    public Contact() {
        firstName="";
        lastName="";
        name="";
        pseudonym="";
        hausePhoneNumber="";
        faxPhoneNumber="";
        pagerPhoneNumber="";
        email="";
        phoneNumber="";
        workPhoneNumber="";
        secondEmail="";
        address="";
        city="";
        voivodeship="";
        postalCode="";
        country="";
        office="";
        departament="";
        companyName="";
        companyAddress="";
        companyAddres="";
        companyPostalCode="";
        companyCounty="";
        companyWebsite="";
        info1="";
        info2="";
        info3="";
        info4="";
        notes="";
        birthday = LocalDate.of(1997,10,7);
    }

    public Contact(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        name="";
        pseudonym="";
        hausePhoneNumber="";
        faxPhoneNumber="";
        pagerPhoneNumber="";
        workPhoneNumber="";
        secondEmail="";
        address="";
        city="";
        voivodeship="";
        postalCode="";
        country="";
        office="";
        departament="";
        companyName="";
        companyAddress="";
        companyAddres="";
        companyPostalCode="";
        companyCounty="";
        companyWebsite="";
        info1="";
        info2="";
        info3="";
        info4="";
        notes="";
        birthday = LocalDate.of(1997,10,7);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getHausePhoneNumber() {
        return hausePhoneNumber;
    }

    public void setHausePhoneNumber(String hausePhoneNumber) {
        this.hausePhoneNumber = hausePhoneNumber;
    }

    public String getFaxPhoneNumber() {
        return faxPhoneNumber;
    }

    public void setFaxPhoneNumber(String faxPhoneNumber) {
        this.faxPhoneNumber = faxPhoneNumber;
    }

    public String getPagerPhoneNumber() {
        return pagerPhoneNumber;
    }

    public void setPagerPhoneNumber(String pagerPhoneNumber) {
        this.pagerPhoneNumber = pagerPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public void setSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddres() {
        return companyAddres;
    }

    public void setCompanyAddres(String companyAddres) {
        this.companyAddres = companyAddres;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyCounty() {
        return companyCounty;
    }

    public void setCompanyCounty(String companyCounty) {
        this.companyCounty = companyCounty;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
