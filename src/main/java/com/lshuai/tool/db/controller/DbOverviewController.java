package com.lshuai.tool.db.controller;

import com.lshuai.tool.db.MainApp;
import com.lshuai.tool.db.entity.DbParam1;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * @author LShuai
 * @date 2019-09-02 15:28
 */
public class DbOverviewController {
    @FXML
    private TableView<DbParam1> dbTable;
    @FXML
    private TableColumn<DbParam1, String> nameColumn;
    @FXML
    private TableColumn<DbParam1, String> typeColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label driverLabel;
    @FXML
    private Label urlLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public DbOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getType());
        showDbParams(null);
        dbTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showDbParams(newValue)));
    }

    @FXML
    private void handleDeleteParam(){
        int selectedIndex = dbTable.getSelectionModel().getSelectedIndex();
        dbTable.getItems().remove(selectedIndex);
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        dbTable.setItems(mainApp.getDbData());
    }

    public void showDbParams(DbParam1 param) {
        if (param != null) {
            nameLabel.setText(param.getName().getValue());
            typeLabel.setText(param.getType().getValue());
            driverLabel.setText(param.getDriver().getValue());
            urlLabel.setText(param.getUrl().getValue());
            usernameLabel.setText(param.getUsername().getValue());
            passwordLabel.setText(param.getPassword().getValue());
        }else{
            nameLabel.setText("");
            typeLabel.setText("");
            driverLabel.setText("");
            urlLabel.setText("");
            usernameLabel.setText("");
            passwordLabel.setText("");
        }
    }
}
