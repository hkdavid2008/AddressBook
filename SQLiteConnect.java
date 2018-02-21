package addressbook;

import java.sql.*;

public class SQLiteConnect {

    private Connection c;
    private Statement statement;

    public SQLiteConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.err.println("Brak sterownika JDBC.");
            e.printStackTrace();
        }

        try {
            c = DriverManager.getConnection("jdbc:sqlite:default.db");
            statement = c.createStatement();
        } catch (Exception e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą danych.");
        }
        createTable();
    }

    private boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Contacts (\n" +
                "\tid\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\tfirstName\tTEXT,\n" +
                "\tlastName\tTEXT,\n" +
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
                "\tnotes\tTEXT\n" +
                ");";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.println("Nie udało się utworzyć tabeli.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean newContact(Contact contact) {
        String sql = "INSERT INTO Contacts values (NULL, '"
                + contact.getFirstName() + "','"
                + contact.getLastName() + "','"
                + contact.getPseudonym() + ","
                + contact.getWebsite() + "','"
                + contact.getHausePhoneNumber() + "','"
                + contact.getFaxPhoneNumber() + "','"
                + contact.getPagerPhoneNumber() + "','"
                + contact.getEmail() + "','"
                + contact.getMobilePhoneNumber() + "','"
                + contact.getWorkPhoneNumber() + "','"
                + contact.getSecondEmail() + "','"
                + contact.getAddress() + "','"
                + contact.getCity() + "','"
                + contact.getVoivodeship() + "','"
                + contact.getPostalCode() + "','"
                + contact.getBirthday() + "','"
                + contact.getOffice() + "','"
                + contact.getDepartament() + "','"
                + contact.getCompanyName() + "','"
                + contact.getCompanyAddress() + "','"
                + contact.getCompanyPostalCode() + "','"
                + contact.getCompanyCountry() + "','"
                + contact.getCompanyWebsite() + "','"
                + contact.getInfo1() + "','"
                + contact.getInfo2() + "','"
                + contact.getInfo3() + "','"
                + contact.getInfo4() + "','"
                + contact.getNotes() + "');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
