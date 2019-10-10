package com.lshuai.tool.db;

import com.alibaba.fastjson.JSONArray;
import com.lshuai.tool.db.controller.DbOverviewController;
import com.lshuai.tool.db.entity.DbParam;
import com.lshuai.tool.db.entity.DbParam1;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private String configPath = "E:\\idea-workspace\\tool\\src\\main\\resources\\dbConfig.json";
    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<DbParam1> dbData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        try {
            String context = IOUtils.toString(Paths.get(configPath).toUri(), "utf-8");
            List<DbParam> list = JSONArray.parseArray(context, DbParam.class);
            list.forEach(param -> dbData.add(new DbParam1(param)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("数据库工具");

        initRootLayout();

        showDbOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showDbOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MainOverview.fxml"));
            AnchorPane dbOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(dbOverview);
            // Give the controller access to the main app.
            DbOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<DbParam1> getDbData() {
        return dbData;
    }

}
