package addressbook;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

public class Program extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private TableView<Contact> contactList;
    private FilteredList<Contact> filteredList;
    private ContactViewer contactViewer;
    private WelcomeScreen welcomeScreen;
    private ScrollPane viewerPane;
    private SQLiteConnect dbConnect = new SQLiteConnect();


    public ObservableList<Contact> getContats() {
        ObservableList<Contact> list = FXCollections.observableArrayList();
        list.add(new Contact("Jan","Kowalski","665015862","paw.inter@onet.eu"));
        list.add(new Contact("Andrzej","Zygalski","235698940","andrzejuu@yopmail.com"));
        list.add(new Contact("Jędrzej","Jędrzejewski","445777998","janko@yopmail.com"));
        return list;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        dbConnect = new SQLiteConnect();
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
                        contactList.getItems().add(newEditor.getContact());
                        dbConnect.newContact(newEditor.getContact());
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

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
                        newEditor.getContact();
                        //contactViewer.setContact();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

        Button deleteContactButton = new Button();
        deleteContactButton.setDisable(true);
        deleteContactButton.setText("Usuń");
        deleteContactButton.setPrefSize(150,25);
        deleteContactButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (contactList.getSelectionModel().getSelectedItem()!=null) {
                    contactList.getItems().remove(contactList.getSelectionModel().getSelectedItem());
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

            }
        });

        //Add buttons to topbar
        topbar.getChildren().addAll(newContactButton, modifyContactButton, deleteContactButton,space, searchField);
        pane.setTop(topbar);

        //Center layout
        SplitPane center = new SplitPane();

        //Address books
        TreeView<String> bookList = new TreeView<String>();

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

        contactList.setItems(getContats());
        contactList.getColumns().addAll(firstNameCol,lastNameCol,phoneNumberCol,emailCol, companyCol);

        contactList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                viewerPane.setContent(contactViewer);
                modifyContactButton.setDisable(false);
                deleteContactButton.setDisable(false);
                contactViewer.setContact(newValue);
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
