/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Employe;
import SqlEntities.Equipment;
import StorageUnit.StorageManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class EmployeChartController implements Initializable {
    @FXML
    private AnchorPane main;
    @FXML
    private Label gifLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private StackedBarChart<String,Number> stackedBarChart;
   
    /**
     * Initializes the controller class.
     */
     final CategoryAxis x  = new CategoryAxis();
    final NumberAxis y  = new NumberAxis();
    
    public void iniEmploye()
    {
       // this.stackedBarChart= new StackedBarChart<String,Number>(x,y); 
         stackedBarChart.setTitle("Most Used Equipment");
         this.infoLabel.setText("Most Used Equipment");
       XYChart.Series<String,Number> value = new XYChart.Series();
        value.setName("Employes And Wages");
        
       for (Employe e:this.sqlWaiter.getAllEmplList())
       {
          value.getData().add(new XYChart.Data(e.getEmFirname()+" "+e.getEmLastname(),Integer.valueOf(e.getEmWages()))); 
       }
      // value.getData().add(new XYChart.Data("Test",12343)); 
       stackedBarChart.getData().add(value);
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      iniEmploye();
       StorageManager storage = new StorageManager();
       this.gifLabel.setGraphic(storage.getAnimationLogin());
    }    
     public EmployeChartController(){}
     private SqlWaiter sqlWaiter;
     public void setSQlWaiter(SqlWaiter updateSql)
    {
        this.sqlWaiter=updateSql;
    }
    
    
}
