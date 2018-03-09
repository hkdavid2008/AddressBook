package addressbook;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

public class Program extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private TableView<Contact> contactList;
    private ContactViewer contactViewer;
    private WelcomeScreen welcomeScreen;
    private ScrollPane viewerPane;
    private TreeView<MailingList> bookList;
    private SQLiteConnect dbConnect = new SQLiteConnect("default.db");

    private ObservableList<Contact> getContats() {
        dbConnect.newContact(new Contact("Jan","Kowalski","665015862","paw.inter@onet.eu"));
        dbConnect.newContact(new Contact("Andrzej","Zygalski","235698940","andrzejuu@yopmail.com"));
        dbConnect.newContact(new Contact("Jędrzej","Jędrzejewski","445777998","janko@yopmail.com"));
        return dbConnect.getContactsList();
    }

    private TreeView<MailingList> getMailingLists() {
        TreeItem<MailingList> root = createNode(new MailingList(-1, "Wszystkie adresy"));
        return new TreeView<MailingList>(root);
    }

    private TreeItem<MailingList> createNode(MailingList list) {
        return new TreeItem<MailingList>(list) {
            @Override
            public boolean isLeaf() {
                if (list.getId()==-1) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public ObservableList<TreeItem<MailingList>> getChildren() {
                System.out.println(list.getName());
                ObservableList<TreeItem<MailingList>> observableList = FXCollections.observableArrayList();
                if (list.getId()==-1) {
                    for (MailingList newList : dbConnect.getMailingLists()) {
                        TreeItem<MailingList> item = new TreeItem<MailingList>(newList);
                        observableList.add(item);
                    }
                }
                return observableList;
            }
        };
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

        Button newMailingListButton = new Button();
        newMailingListButton.setText("Nowa lista dystrybucyjna");
        newMailingListButton.setPrefSize(150,25);
        newMailingListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setTitle("Nowa lista dystrybucyjna");
                nameDialog.setHeaderText("Podaj nazwę listy dystrybucyjnej");
                nameDialog.setContentText("Nazwa: ");
                Optional<String> result = nameDialog.showAndWait();
                if (result.isPresent()==true) {
                    if (result.get().isEmpty()==false) {
                        dbConnect.newMailingList(result.get());
                        bookList.refresh();
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
        topbar.getChildren().addAll(newContactButton, modifyContactButton, deleteContactButton, newMailingListButton, space, searchField);
        pane.setTop(topbar);

        //Center layout
        SplitPane center = new SplitPane();

        //Address books
        Node addressBookIcon = new ImageView(new Image(getClass().getResourceAsStream("addressbook-icon-small.png")));

        bookList = getMailingLists();
        bookList.setEditable(true);
        bookList.setCellFactory(new Callback<TreeView<MailingList>, TreeCell<MailingList>>() {
            @Override
            public TreeCell<MailingList> call(TreeView<MailingList> param) {
                return new MailingListCell();
            }
        });

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

    private final class MailingListCell extends TreeCell<MailingList> {
        private TextField editField;

        public MailingListCell() {

        }

        @Override
        public void startEdit() {
            super.startEdit();
            if (getTreeItem().isLeaf()==true) {
                if (editField==null) {
                    createEditField();
                }
                setText(null);
                setGraphic(editField);
                editField.selectAll();
            }
        }

        private void saveChange(String newText) {
            String oldText = getItem().getName();
            getItem().setName(newText);
            if (dbConnect.updateMailingList(getItem())==false) {
                getItem().setName(oldText);
            }
            commitEdit(getItem());
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem().getName());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        protected void updateItem(MailingList item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (editField!= null) {
                        editField.setText(getItem().getName());
                    }
                    setText(null);
                    setGraphic(editField);
                } else {
                    setText(getItem().getName());
                    setGraphic(getTreeItem().getGraphic());
                }
            }
        }

        private void createEditField() {

            editField = new TextField(getItem().getName());
            editField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode()==KeyCode.ENTER) {
                        saveChange(editField.getText());
                    } else if (event.getCode()==KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }
    }

}
