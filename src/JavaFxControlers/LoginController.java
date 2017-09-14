/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import StorageUnit.StorageManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class LoginController implements Initializable {

    @FXML
    private Pane loginPane;
    @FXML
    private TextField loginTextFild;
    @FXML
    private PasswordField passwordTextFild;
    @FXML
    private Button loginButtton;
    @FXML
    private Label wrongPasswordLabel;
    @FXML
    private Label RecLabelSystem;
    @FXML
    private Label animationLabel;
    @FXML
    private Button exitButton;

    
    private StorageManager storage = new StorageManager();//For Moving Resocrcess
    private SqlWaiter sqlWaiter;

    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.sqlWaiter = new SqlWaiter();
        this.wrongPasswordLabel.setVisible(false);
        this.animationLabel.setGraphic(storage.getAnimationLogin());
        this.RecLabelSystem.setStyle("-fx-text-fill: #800000;");
        this.loginPane.setStyle("-fx-base: #4682b4");
        this.exitButton.setStyle("-fx-base: #b22222;-fx-text-fill: #ffffff;");
        this.loginButtton.setStyle("-fx-base: #7b68ee; -fx-text-fill:  #ffffff;");
    }

    @FXML
    private void loginMauseClicked(MouseEvent event) {
    }

    @FXML
    private void enterPreased(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER))//if Enter is presed
        {
            if (this.sqlWaiter.login(this.loginTextFild.getText(), this.passwordTextFild.getText())) {

                Stage mainRecordingStudio = storage.getMainRecordingStudio(sqlWaiter);
                
                mainRecordingStudio.show();
                
//                FXMLLoader loader = new FXMLLoader(storage.getMainRecordingStudio());
//                MainRecordingStudioWindowController con = new MainRecordingStudioWindowController();
//
//                loader.setController(con);
//                con.setSqlWaiter(sqlWaiter);
//                Scene scene = new Scene(loader.load());
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
//                
//                Stage stage = new Stage();
//                Parent root = storage.getMainRecordingStudio();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.setMaximized(true);
//                stage.show();
//                 boss.passWaiterToMainProgramControler(sqlWaiter);//Pasing Sql Waiter to main program
                Stage stageL = (Stage) exitButton.getScene().getWindow();
                stageL.close();
            } else {
                this.wrongPasswordLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void looginButtonClicked(MouseEvent event) throws IOException {
        if (this.sqlWaiter.login(this.loginTextFild.getText(), this.passwordTextFild.getText())) {
            
            Stage mainRecordingStudio = storage.getMainRecordingStudio(sqlWaiter);
                mainRecordingStudio.setMaximized(true);
                mainRecordingStudio.show();
                
//            FXMLLoader loader = new FXMLLoader(storage.getMainRecordingStudio());
//            MainRecordingStudioWindowController con = new MainRecordingStudioWindowController();
//            loader.setController(con);
//            con.setSqlWaiter(sqlWaiter);
//            Scene scene = new Scene(loader.load());
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
                
                
//            Stage stage = new Stage();
//            Parent root = storage.getMainRecordingStudio();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.setMaximized(true);
//            stage.show();
//            boss.setWaiter(sqlWaiter);//Pasing Sql Waiter to main program
            Stage stageL = (Stage) exitButton.getScene().getWindow();
            stageL.close();
        } else {
            this.wrongPasswordLabel.setVisible(true);
        }
    }

    @FXML
    private void exitButtonPreased(MouseEvent event) {
        Stage stageL = (Stage) exitButton.getScene().getWindow();
        stageL.close();
    }

}
