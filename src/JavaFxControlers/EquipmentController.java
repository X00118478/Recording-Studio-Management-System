/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Equipment;
import StorageUnit.StorageManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class EquipmentController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label gifLabel;
    @FXML
    private Button addEquipment;
    @FXML
    private Label eqNaCheck;
    @FXML
    private TextField equipemntName;
    @FXML
    private Label conditionCheck;
    @FXML
    private TextField condition;
    @FXML
    private Label costCheck;
    @FXML
    private TextField cost1;

    /**
     * Initializes the controller class.
     */
    public EquipmentController(){}
    
    private SqlWaiter sqlWaiter;
    private StorageManager storage=new StorageManager();
    private boolean condytion,cost,name;
    private int id;
    private boolean update;
    private Equipment equipByID;
    
     public void setSQlWaiter(SqlWaiter updateSql)
    {
        this.sqlWaiter=updateSql;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.mainPane.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
       this.addEquipment.setStyle("-fx-base:#228b22;-fx-text-fill: #ffffff;");
       this.conditionCheck.setGraphic(storage.getWrong());
       this.costCheck.setGraphic(storage.getWrong());
       this.eqNaCheck.setGraphic(storage.getWrong());
       this.gifLabel.setGraphic(storage.getAnimationLogin());//
       this.addEquipment.setVisible(false);
       this.condytion=false;
       this.cost=false;
       this.name=false;
       
       iniLisiners();
       
       if(update)
       {
           this.condition.setText(this.equipByID.getStateCondition());
           this.cost1.setText(this.equipByID.getCost());
           this.equipemntName.setText(this.equipByID.getNameEq());
           this.addEquipment.setText("Update Equipment");
           this.addEquipment.setStyle("-fx-base:  #ff8c00; -fx-text-fill:  #ffffff;");
       }
    }    

    
    public void iniLisiners()
    {
        this.condition.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if(newValue.length()>0)
                {
               conditionCheck.setGraphic(storage.getRiGht());
               condytion=true;
                }
               else
                {
                  conditionCheck.setGraphic(storage.getWrong());  
                  condytion=false;
                }
               
               if(condytion==true && cost==true && name==true)
                {
                   addEquipment.setVisible(true);
                }
               else
                {
                   addEquipment.setVisible(false);
                }
               
            }});
        
        this.cost1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if(newValue.length()>0)
                {
               costCheck.setGraphic(storage.getRiGht());
               cost=true;
                }
               else
                {
                  costCheck.setGraphic(storage.getWrong());  
                  cost=false;
                }
               
               if(condytion==true && cost==true && name==true)
                {
                   addEquipment.setVisible(true);
                }
               else
                {
                   addEquipment.setVisible(false);
                }
               
            }});
        
        this.equipemntName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if(newValue.length()>0)
                {
               eqNaCheck.setGraphic(storage.getRiGht());
               name=true;
                }
               else
                {
                  eqNaCheck.setGraphic(storage.getWrong());  
                  name=false;
                }
               
               if(condytion==true && cost==true && name==true)
                {
                   addEquipment.setVisible(true);
                }
               else
                {
                   addEquipment.setVisible(false);
                }
               
            }});
        
    }
    
    @FXML
    private void addEquipmentButton(MouseEvent event) throws IOException {
        this.sqlWaiter.addEEquipment(this.equipemntName.getText(), this.condition.getText(), this.cost1.getText());
        
         Stage mainManagerWindow = storage.getMainManagerWindow(sqlWaiter);
        mainManagerWindow.show();
       
        Stage stageL = (Stage) equipemntName.getScene().getWindow();
        stageL.close();
    }
    
    
    
    public void setSQlWaiter(SqlWaiter updateSql,int id) throws IOException
    {
        this.id=id;
        this.sqlWaiter=updateSql;
        equipByID = this.sqlWaiter.getEquipByID(id);
       this.update=true;
        
    }
    
}
