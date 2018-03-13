package addressbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SQLiteConnect {

    private Connection c;
    private Statement statement;
    private ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private ObservableList<MailingList> mailingLists = FXCollections.observableArrayList();

    public SQLiteConnect(String path) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.err.println("Brak sterownika JDBC.");
            e.printStackTrace();
        }

        String url = "jdbc:sqlite:" + path;

        try {
            c = DriverManager.getConnection(url);
            if (c==null) {
                DatabaseMetaData meta = c.getMetaData();
                System.out.println("Utworzono nowy plik bazy danych.");
            }
            statement = c.createStatement();
        } catch (Exception e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą danych.");
        }
        createTables();
    }

    private boolean createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS Contacts (\n" +
                "\tid\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\tfirstName\tTEXT,\n" +
                "\tlastName\tTEXT,\n" +
                "\tname\tTEXT,\n" +
                "\tpseudonym\tTEXT,\n" +
                "\twebsite\tTEXT,\n" +
                "\thausePhoneNumber\tTEXT,\n" +
                "\tfaxPhoneNumber\tTEXT,\n" +
                "\tpagerPhoneNumber\tTEXT,\n" +
                "\temail\tTEXT,\n" +
                "\tmobilePhoneNumber\tTEXT,\n" +
                "\tworkPhoneNumber\tTEXT,\n" +
                "\tsecondEmail\tTEXT,\n" +
                "\taddress\tTEXT,\n" +
                "\tcity\tTEXT,\n" +
                "\tvoivodeship\tTEXT,\n" +
                "\tpostalCode\tTEXT,\n" +
                "\tcountry\tTEXT,\n" +
                "\tbirthday\tTEXT,\n" +
                "\toffice\tTEXT,\n" +
                "\tdepartament\tTEXT,\n" +
                "\tcompanyName\tTEXT,\n" +
                "\tcompanyAddress\tTEXT,\n" +
                "\tcompanyPostalCode\tTEXT,\n" +
                "\tcompanyCountry\tTEXT,\n" +
                "\tcompanyWebsite\tTEXT,\n" +
                "\tinfo1\tTEXT,\n" +
                "\tinfo2\tTEXT,\n" +
                "\tinfo3\tTEXT,\n" +
                "\tinfo4\tTEXT,\n" +
                "\tnotes\tTEXT,\n" +
                "\tmailingListId\tINTEGER\n" +
                ");";
        String sql2 = "CREATE TABLE IF NOT EXISTS MailingLists (\n" +
                "\tid\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\tname\tTEXT\n" +
                ");";
        try {
            statement.execute(sql);
            statement.execute(sql2);
        } catch (SQLException e) {
            System.err.println("Nie udało się utworzyć tabeli.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean newContact(Contact contact) {
        String sql = "INSERT INTO Contacts VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pre = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, contact.getFirstName());
            pre.setString(2, contact.getLastName());
            pre.setString(3, contact.getName());
            pre.setString(4, contact.getPseudonym());
            pre.setString(5, contact.getWebsite());
            pre.setString(6, contact.getHausePhoneNumber());
            pre.setString(7, contact.getFaxPhoneNumber());
            pre.setString(8, contact.getPagerPhoneNumber());
            pre.setString(9, contact.getEmail());
            pre.setString(10, contact.getMobilePhoneNumber());
            pre.setString(11, contact.getWorkPhoneNumber());
            pre.setString(12, contact.getSecondEmail());
            pre.setString(13, contact.getAddress());
            pre.setString(14, contact.getCity());
            pre.setString(15, contact.getVoivodeship());
            pre.setString(16, contact.getPostalCode());
            pre.setString(17, contact.getCountry());
            if (contact.getBirthday()==null) {
                pre.setString(18, "");
            } else {
                pre.setString(18, contact.getBirthday().toString());
            }
            pre.setString(19, contact.getOffice());
            pre.setString(20, contact.getDepartament());
            pre.setString(21, contact.getCompanyName());
            pre.setString(22, contact.getCompanyAddress());
            pre.setString(23, contact.getCompanyPostalCode());
            pre.setString(24, contact.getCompanyCountry());
            pre.setString(25, contact.getCompanyWebsite());
            pre.setString(26, contact.getInfo1());
            pre.setString(27, contact.getInfo2());
            pre.setString(28, contact.getInfo3());
            pre.setString(29, contact.getInfo4());
            pre.setString(30, contact.getNotes());
            pre.setInt(31, contact.getMailingListId());
            pre.executeUpdate();
            ResultSet keys = pre.getGeneratedKeys();
            if (keys.next()) {
                contact.setId(keys.getInt(1));
            }
            contactList.add(contact);
            System.out.println("Pomyślnie wstawiono wpis id = " + keys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateContact(Contact contact) {
        if (contact.getId()==-1) {
            return false;
        }
        String updateSql = "UPDATE Contacts SET " +
                "firstName = ?, " +
                "lastName = ?, " +
                "name = ?, " +
                "pseudonym = ?, " +
                "website = ?, " +
                "hausePhoneNumber = ?, " +
                "faxPhoneNumber = ?, " +
                "pagerPhoneNumber = ?, " +
                "email = ?, " +
                "mobilePhoneNumber = ?, " +
                "workPhoneNumber = ?, " +
                "secondEmail = ?, " +
                "address = ?, " +
                "city = ?, " +
                "voivodeship = ?, " +
                "postalCode = ?, " +
                "country = ?, " +
                "birthday = ?, " +
                "office = ?, " +
                "departament = ?, " +
                "companyName = ?, " +
                "companyAddress = ?, " +
                "companyPostalCode = ?, " +
                "companyCountry = ?, " +
                "companyWebsite = ?, " +
                "info1 = ?, " +
                "info2 = ?, " +
                "info3 = ?, " +
                "info4 = ?, " +
                "notes = ?, " +
                "mailingListId = ? " +
                "WHERE id = " + contact.getId() + ";";
        try {
            PreparedStatement stmtUpdate = c.prepareStatement(updateSql);
            stmtUpdate.setString(1, contact.getFirstName());
            stmtUpdate.setString(2, contact.getLastName());
            stmtUpdate.setString(3, contact.getName());
            stmtUpdate.setString(4, contact.getPseudonym());
            stmtUpdate.setString(5, contact.getWebsite());
            stmtUpdate.setString(6, contact.getHausePhoneNumber());
            stmtUpdate.setString(7, contact.getFaxPhoneNumber());
            stmtUpdate.setString(8, contact.getPagerPhoneNumber());
            stmtUpdate.setString(9, contact.getEmail());
            stmtUpdate.setString(10, contact.getMobilePhoneNumber());
            stmtUpdate.setString(11, contact.getWorkPhoneNumber());
            stmtUpdate.setString(12, contact.getSecondEmail());
            stmtUpdate.setString(13, contact.getAddress());
            stmtUpdate.setString(14, contact.getCity());
            stmtUpdate.setString(15, contact.getVoivodeship());
            stmtUpdate.setString(16, contact.getPostalCode());
            stmtUpdate.setString(17, contact.getCountry());
            if (contact.getBirthday()==null) {
                stmtUpdate.setString(18, "");
            } else {
                stmtUpdate.setString(18, contact.getBirthday().toString());
            }
            stmtUpdate.setString(19, contact.getOffice());
            stmtUpdate.setString(20, contact.getDepartament());
            stmtUpdate.setString(21, contact.getCompanyName());
            stmtUpdate.setString(22, contact.getCompanyAddress());
            stmtUpdate.setString(23, contact.getCompanyPostalCode());
            stmtUpdate.setString(24, contact.getCompanyCountry());
            stmtUpdate.setString(25, contact.getCompanyWebsite());
            stmtUpdate.setString(26, contact.getInfo1());
            stmtUpdate.setString(27, contact.getInfo2());
            stmtUpdate.setString(28, contact.getInfo3());
            stmtUpdate.setString(29, contact.getInfo4());
            stmtUpdate.setString(30, contact.getNotes());
            stmtUpdate.setInt(31,contact.getMailingListId());
            stmtUpdate.executeUpdate();
        } catch (SQLException updateerror) {
            updateerror.printStackTrace();
            return false;
        }
        return true;
    }

    public ObservableList<Contact> getContactsList() {
        try {
            Statement selectStmt = c.createStatement();
            ResultSet results = selectStmt.executeQuery("SELECT * FROM Contacts;");
            contactList.clear();
            while (results.next()) {
                Contact newContact = new Contact();
                newContact.setFirstName(results.getString("firstName"));
                newContact.setLastName(results.getString("lastName"));
                newContact.setName(results.getString("name"));
                newContact.setPseudonym(results.getString("pseudonym"));
                newContact.setWebsite(results.getString("website"));
                newContact.setHausePhoneNumber(results.getString("hausePhoneNumber"));
                newContact.setFaxPhoneNumber(results.getString("faxPhoneNumber"));
                newContact.setPagerPhoneNumber(results.getString("pagerPhoneNumber"));
                newContact.setEmail(results.getString("email"));
                newContact.setMobilePhoneNumber(results.getString("mobilePhoneNumber"));
                newContact.setWorkPhoneNumber(results.getString("workPhoneNumber"));
                newContact.setSecondEmail(results.getString("secondEmail"));
                newContact.setAddress(results.getString("address"));
                newContact.setCity(results.getString("city"));
                newContact.setVoivodeship(results.getString("voivodeship"));
                newContact.setPostalCode(results.getString("postalCode"));
                newContact.setCountry(results.getString("country"));
                //newContact.setBirthday(results.getString("birthday"));    //TODO poprawić datę
                newContact.setOffice(results.getString("office"));
                newContact.setDepartament(results.getString("departament"));
                newContact.setCompanyName(results.getString("companyName"));
                newContact.setCompanyAddress(results.getString("companyAddress"));
                newContact.setCompanyPostalCode(results.getString("companyPostalCode"));
                newContact.setCompanyCountry(results.getString("companyCountry"));
                newContact.setCompanyWebsite(results.getString("companyWebsite"));
                newContact.setInfo1(results.getString("info1"));
                newContact.setInfo2(results.getString("info2"));
                newContact.setInfo3(results.getString("info3"));
                newContact.setInfo4(results.getString("info4"));
                newContact.setNotes(results.getString("notes"));
                newContact.setId(results.getInt("mailingListId"));
                newContact.setId(results.getInt("id"));
                if (newContact.getId()>0) {
                    contactList.add(newContact);
                }
            }
        } catch (SQLException selecterror){
            selecterror.printStackTrace();
        }
        return contactList;
    }

    public boolean deleteContact(Contact contact) {
        if (contact.getId()==-1) {
            return false;
        }
        try {
            PreparedStatement deleteStmt = c.prepareStatement("DELETE FROM Contacts WHERE id = ?");
            deleteStmt.setInt(1,contact.getId());
            deleteStmt.executeUpdate();
            contactList.remove(contact);
            System.out.println("Pomyślnie usunięto kontakt id = " + contact.getId());
        } catch (SQLException deleteerror) {
            deleteerror.printStackTrace();
            return false;
        }
        return true;
    }

    //Mailing lists
    public boolean newMailingList(String name) {
        String sql = "INSERT INTO MailingLists VALUES (NULL, ?);";
        try {
            PreparedStatement pre = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, name);
            pre.executeUpdate();
            ResultSet keys = pre.getGeneratedKeys();
            getMailingLists();
            System.out.println("Pomyślnie wstawiono listę mailingową id = " + keys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateMailingList(MailingList list) {
        if (list.getId()==0) {
            return false;
        }
        String updateSql = "UPDATE MailingLists SET " +
                "name = ? " +
                "WHERE id = " + list.getId() + ";";
        try {
            PreparedStatement stmtUpdate = c.prepareStatement(updateSql);
            stmtUpdate.setString(1, list.getName());
            stmtUpdate.executeUpdate();
            System.out.println("Pomyślnie zaktualizowano listę mailingową");
        } catch (SQLException updateerror) {
            updateerror.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteMailingList(MailingList list) {
        if (list.getId()==0) {
            return false;
        }
        try {
            PreparedStatement deleteStmt = c.prepareStatement("DELETE FROM MailingLists WHERE id = ?");
            deleteStmt.setInt(1,list.getId());
            deleteStmt.executeUpdate();
            contactList.remove(list);
            System.out.println("Pomyślnie usunięto listę mailingową id = " + list.getId());
        } catch (SQLException deleteerror) {
            deleteerror.printStackTrace();
            return false;
        }
        return true;
    }

    public ObservableList<MailingList> getMailingLists() {
        try {
            Statement selectStmt = c.createStatement();
            ResultSet results = selectStmt.executeQuery("SELECT * FROM MailingLists;");
            mailingLists.clear();
            if (results.isBeforeFirst()==false) {
                String sql3 = "INSERT INTO MailingLists VALUES (-1, 'Wszystkie adresy');";
                statement.execute(sql3);
            }
            while (results.next()) {
                MailingList newList = new MailingList(results.getInt(1), results.getString(2));
                mailingLists.add(newList);
            }
        } catch (SQLException selecterror){
            selecterror.printStackTrace();
        }
        return mailingLists;
    }

    public boolean changeMailingList(Contact contact, MailingList targetList) {
        String updateSql = "UPDATE Contacts SET " +
                "mailingListId = ? " +
                "WHERE id = " + contact.getId() + ";";
        try {
            PreparedStatement stmtUpdate = c.prepareStatement(updateSql);
            stmtUpdate.setInt(1, targetList.getId());
            stmtUpdate.executeUpdate();
            System.out.println("Pomyślnie przeniesiono kontakt");
        } catch (SQLException updateerror) {
            updateerror.printStackTrace();
            return false;
        }
        getContactsList();
        return true;
    }
}
