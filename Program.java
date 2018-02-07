package addressbook;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class Program extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private TableView<Contact> contactList;

    public ObservableList<Contact> getContats() {
        ObservableList<Contact> list = FXCollections.observableArrayList();
        list.add(new Contact("Jan","Kowalski","665015862","paw.inter@onet.eu"));
        list.add(new Contact("Andrzej","Zygalski","235698940","andrzejuu@yopmail.com"));
        list.add(new Contact("Jędrzej","Jędrzejewski","445777998","janko@yopmail.com"));
        return list;
    }

    @Override
    public void start(Stage primaryStage) {
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
                    contactList.getItems().add(newEditor.getContact());
                    contactList.refresh();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        Button modifyContactButton = new Button();
        modifyContactButton.setText("Modyfikuj");
        modifyContactButton.setPrefSize(150,25);
        modifyContactButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ContactEditor newEditor = new ContactEditor(contactList.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        });

        Button deleteContactButton = new Button();
        deleteContactButton.setText("Usuń");
        deleteContactButton.setPrefSize(150,25);

        //Add buttons to topbar
        topbar.getChildren().addAll(newContactButton, modifyContactButton, deleteContactButton);
        pane.setTop(topbar);

        //Center layout
        SplitPane center = new SplitPane();

        //Address books
        TreeView<String> bookList = new TreeView<String>();
        center.getItems().add(bookList);
        //pane.setLeft(bookList);

        //Center-right layout
        SplitPane centerRight = new SplitPane();
        centerRight.setOrientation(Orientation.VERTICAL);

        //Contact list
        contactList = new TableView<>();
        TableColumn<Contact,String> firstNameCol = new TableColumn("Imię");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Contact,String> lastNameCol = new TableColumn("Nazwisko");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Contact,String> phoneNumberCol = new TableColumn("Numer telefonu");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Contact,String> emailCol = new TableColumn("Adres Email");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        contactList.setItems(getContats());
        contactList.getColumns().addAll(firstNameCol,lastNameCol,phoneNumberCol,emailCol);

        //Info panel
        WebView infoPanel = new WebView();
        infoPanel.getEngine().load("https://www.mozilla.org/pl/thunderbird/");

        //Add elements to center
        centerRight.getItems().addAll(contactList, infoPanel);
        center.getItems().add(centerRight);
        center.setDividerPosition(0,0.2);
        pane.setCenter(center);

        primaryStage.setScene(new Scene(pane,1200,750));
        primaryStage.show();
    }
}
