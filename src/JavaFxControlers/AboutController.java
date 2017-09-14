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
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AboutController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TabPane table;
    @FXML
    private GridPane gridInfoPane;
    @FXML
    private TextArea textAreapane;
    @FXML
    private Label funnyLabel;

    /**
     * Initializes the controller class.
     */
    private StorageManager storage= new StorageManager();
    
    public AboutController(){}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.funnyLabel.setGraphic(storage.getAnimationLogin(this.funnyLabel.getPrefWidth(), (this.funnyLabel.getPrefHeight())));
      this.gridInfoPane.setStyle(null);
      this.mainPane.setStyle(null);
      
      
    }    
    
}
