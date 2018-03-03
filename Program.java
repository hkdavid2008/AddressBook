package addressbook;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.function.Predicate;

public class Program extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private TableView<Contact> contactList;
    private ContactViewer contactViewer;
    private WelcomeScreen welcomeScreen;
    private ScrollPane viewerPane;
    private SQLiteConnect dbConnect = new SQLiteConnect("default.db");

    public ObservableList<Contact> getContats() {
        ObservableList<Contact> list = dbConnect.getContactsList();
        dbConnect.newContact(new Contact("Jan","Kowalski","665015862","paw.inter@onet.eu"));
        dbConnect.newContact(new Contact("Andrzej","Zygalski","235698940","andrzejuu@yopmail.com"));
        dbConnect.newContact(new Contact("Jędrzej","Jędrzejewski","445777998","janko@yopmail.com"));
        return list;
    }

    FilteredList<Contact> filteredList = new FilteredList<Contact>(getContats());

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Layout
        BorderPane pane = new BorderPane();

        //Topbar
        HBox topbar = new HBox();
        topbar.setPadding(new Insets(10,10,10,10));

        //Button - New contact
        Button newContactButton = new Button();
        newContactButton.setText("Nowa wizytówka");
        newContactButton.setPrefSize(150,25);
        newContactButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ContactEditor newEditor = new ContactEditor();
                    if (newEditor.getContact()!=null) {
                        dbConnect.newContact(newEditor.getContact());
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        //Button - Delete contact
        Button modifyContactButton = new Button();
        modifyContactButton.setDisable(true);
        modifyContactButton.setText("Modyfikuj");
        modifyContactButton.setPrefSize(150,25);
        modifyContactButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (contactList.getSelectionModel().getSelectedItem()!=null) {
                    try {
                        ContactEditor newEditor = new ContactEditor(contactList.getSelectionModel().getSelectedItem());
                        Contact updatedContact = newEditor.getContact();
                        if (updatedContact!=null) {
                            if (dbConnect.updateContact(updatedContact)==true) {
                                System.out.println("Pomyślnie zaktualizowano kontakt.");
                            } else {
                                System.out.println("Błąd! Nie udało się zauktualizować kontaktu.");
                            }
                        }
                        //contactViewer.setContact();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

        //Button - Modify contact
        Button deleteContactButton = new Button();
        deleteContactButton.setDisable(true);
        deleteContactButton.setText("Usuń");
        deleteContactButton.setPrefSize(150,25);
        deleteContactButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (contactList.getSelectionModel().getSelectedItem()!=null) {
                    Contact toDelete = contactList.getSelectionModel().getSelectedItem();
                    contactList.getSelectionModel().selectPrevious();
                    if (dbConnect.deleteContact(toDelete)==false) {
                        contactList.getSelectionModel().selectNext();
                    }
                }
            }
        });

        //Add space
        Region space = new Region();
        topbar.setHgrow(space, Priority.ALWAYS);

        TextField searchField = new TextField();
        searchField.setPromptText("Wyszukaj");
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredList.setPredicate(new Predicate<Contact>() {
                    @Override
                    public boolean test(Contact contact) {
                        if (newValue==null | newValue.isEmpty()==true) {
                            return true;
                        }

                        long count = contactList.getColumns().stream().count();
                        for (int j = 0; j < count; j++) {
                            String entry = "" + contactList.getColumns().get(j).getCellData(contact);
                            if (entry.toLowerCase().contains(newValue.toLowerCase())) {
                                return true;
                            }
                        }
                        return false;
                    }
                });
            }
        });

        //Add buttons to topbar
        topbar.getChildren().addAll(newContactButton, modifyContactButton, deleteContactButton,space, searchField);
        pane.setTop(topbar);

        //Center layout
        SplitPane center = new SplitPane();

        //Address books
        Node addressBookIcon = new ImageView(new Image(getClass().getResourceAsStream("addressbook-icon-small.png")));
        TreeItem<String> defaultAddressBook = new TreeItem<String>("Wszystkie adresy", addressBookIcon);
        defaultAddressBook.setExpanded(true);
        for

        TreeView<String> bookList = new TreeView<String>(defaultAddressBook);

        center.getItems().add(bookList);

        //Center-right layout
        SplitPane centerRight = new SplitPane();
        centerRight.setOrientation(Orientation.VERTICAL);

        //Contact list
        contactList = new TableView<>();
        TableColumn<Contact,String> firstNameCol = new TableColumn("Imię");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Contact,String> lastNameCol = new TableColumn("Nazwisko");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Contact,String> phoneNumberCol = new TableColumn("Telefon komórkowy");
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("mobilePhoneNumber"));

        TableColumn<Contact,String> emailCol = new TableColumn("Adres Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Contact,String> companyCol = new TableColumn("Firma");
        companyCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        contactList.setItems(filteredList);
        contactList.getColumns().addAll(firstNameCol,lastNameCol,phoneNumberCol,emailCol, companyCol);

        contactList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                if (newValue==null) {
                    viewerPane.setContent(welcomeScreen);
                    modifyContactButton.setDisable(true);
                    deleteContactButton.setDisable(true);
                } else {
                    viewerPane.setContent(contactViewer);
                    modifyContactButton.setDisable(false);
                    deleteContactButton.setDisable(false);
                    contactViewer.setContact(newValue);
                }
            }
        });

        viewerPane = new ScrollPane();

        //Contact Viewer
        contactViewer = new ContactViewer();

        //Welcome screen
        welcomeScreen = new WelcomeScreen();

        viewerPane.setContent(welcomeScreen);

        //Add elements to center
        centerRight.getItems().add(contactList);
        centerRight.getItems().add(viewerPane);

        centerRight.setDividerPosition(0,0.5);

        center.getItems().add(centerRight);
        center.setDividerPosition(0,0.2);
        pane.setCenter(center);

        primaryStage.setScene(new Scene(pane,1200,750));
        primaryStage.show();
    }
}
