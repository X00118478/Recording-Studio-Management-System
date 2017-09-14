/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFx;

import StorageUnit.StorageManager;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Daniel
 */
public class RecordinStudioMain extends Application {
     StorageManager storage =new StorageManager();
    @Override
    public void start(Stage primaryStage) throws IOException {
     
    Parent root = this.storage.getLogin();
        Scene loginScene = new Scene(root);
        primaryStage.setScene(loginScene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(storage.getIcon());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
