/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Service;
import StorageUnit.StorageManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class ServiceController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label gifLabel;
    @FXML
    private Button addService;
    @FXML
    private Label serviceCheck;
    @FXML
    private TextField serviceName;
    @FXML
    private Label costCheck;
    @FXML
    private TextField cost1;

    private StorageManager storage=new StorageManager();
    private boolean servicename,servicecost;
    private boolean update=false;
    private int id =0;
    private  Service serviceByID ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        mainPane.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
        this.addService.setStyle("-fx-base:#228b22;-fx-text-fill: #ffffff;");
        this.serviceCheck.setGraphic(storage.getWrong());
        this.costCheck.setGraphic(storage.getWrong());
        this.addService.setVisible(false);
        this.servicename=false;
        this.servicecost=false;
        this.gifLabel.setGraphic(storage.getAnimationLogin());
        
        
        iniLisiners();
        
        if(this.serviceByID!=null)
        {
        this.cost1.setText(serviceByID.getCost());
        this.serviceName.setText(serviceByID.getTypeofService());
        this.addService.setText("Update Service");
        this.addService.setStyle("-fx-base:  #ff8c00; -fx-text-fill:  #ffffff;");
        }
        
        
        
        
       
        
    }    

    public void iniLisiners()
    {
        this.cost1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            
                  
                
                if(newValue.length()>0)
                {
               costCheck.setGraphic(storage.getRiGht());
               servicecost=true;
                }
               else
                {
                  costCheck.setGraphic(storage.getWrong());  
                  servicecost=false;
                }
               if(servicecost==true && servicename==true)
                {
                   addService.setVisible(true);
                }
               else
                {
                   addService.setVisible(false);
                }
               
            }});
        
        this.serviceName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length()>0)
                {
               serviceCheck.setGraphic(storage.getRiGht());
               servicename=true;
                }
               else
                {
                  serviceCheck.setGraphic(storage.getWrong());  
                  servicename=false;
                }
                
                
                if(servicecost==true && servicename==true)
                {
                   addService.setVisible(true);
                }
               else
                {
                   addService.setVisible(false);
                }
            }});
        
        
        
        
    }
    

    
    
    @FXML
    private void addServiceButonClicked(MouseEvent event) throws IOException {
        if(this.update)
        {
           this.sqlWaiter.updateService(id, this.serviceName.getText(),  this.cost1.getText());
        }
        else
        {
       this.sqlWaiter.addService(this.serviceName.getText(), this.cost1.getText());
       
        
        }
        Stage mainManagerWindow = storage.getMainManagerWindow(sqlWaiter);
        mainManagerWindow.show();
        
        Stage stageL = (Stage) addService.getScene().getWindow();
        stageL.close();
        
    }
    
    private SqlWaiter sqlWaiter;
     public void setSQlWaiter(SqlWaiter updateSql)
    {
        this.sqlWaiter=updateSql;
    }
     
     public void setSQlWaiter(SqlWaiter updateSql,int id)
    {
        this.id=id;
        this.sqlWaiter=updateSql;
        serviceByID = this.sqlWaiter.getServiceByID(id);
       this.update=true;
        
    }
     
     public ServiceController(){}
    
}
