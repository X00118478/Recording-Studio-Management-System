/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import StorageUnit.StorageManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PopUpController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private Label label;
    @FXML
    private Button okButton;
    @FXML
    private TextArea textFild;

    /**
     * Initializes the controller class.
     */
   private  StorageManager storage =new StorageManager();
    String message;
   public PopUpController(String message)
   {
   this.message=message;
   }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.textFild.setText(message);
        this.label.setGraphic(storage.getPopupWindowImage());
        this.okButton.setStyle("-fx-base: #dc143c; -fx-text-fill:  #ffffff;");
        this.textFild.setStyle("-fx-base:  #fff5ee;-fx-text-fill:   #000080;");
        this.pane.setStyle("-fx-base:#87ceeb");
    }    

    @FXML
    private void okClicked(MouseEvent event) {
         Stage stageL = (Stage) textFild.getScene().getWindow();
        stageL.close();
    }
    
}
