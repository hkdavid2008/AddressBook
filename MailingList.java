package addressbook;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MailingList {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty name = new SimpleStringProperty();

    public MailingList(int id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    @Override
    public String toString() {
        return name.get();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
