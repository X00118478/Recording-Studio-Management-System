/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Technician;
import StorageUnit.StorageManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class TechChartController implements Initializable {
    @FXML
    private AnchorPane main;
    @FXML
    private Label gifLabel;
    @FXML
    private BarChart<String,Number> barChart;
    @FXML
    private Label infoLabel;

    final CategoryAxis x = new CategoryAxis();
    final NumberAxis y = new NumberAxis();
    
    public void iniChart()
    {
  //   barChart= new BarChart<String,Number>(x,y);  
     barChart.setTitle("Technicians Jobs done");
     x.setLabel("Type Of Technician");       
     y.setLabel("Number Of Jobs");
     
      XYChart.Series values = new XYChart.Series();
      values.setName("Most Popular Technician");
     
     
     for(Technician e:this.sqlWaiter.getAllTechnicians())
     {
        values.getData().add(new XYChart.Data(e.getTechnicianjob(),e.getReceiptList().size())); 
     }
     //values.getData().add(new XYChart.Data("Test",1234)); 
     
     barChart.setAnimated(true);
    barChart.animatedProperty();
     barChart.getData().add(values);
    }
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StorageManager storage = new StorageManager();
         this.gifLabel.setGraphic(storage.getAnimationLogin());
           this.infoLabel.setText("Most Used Equipment");
      iniChart();
    }    
    
    private SqlWaiter sqlWaiter;
     public void setSQlWaiter(SqlWaiter updateSql)
    {
        this.sqlWaiter=updateSql;
    }
     
     public TechChartController(){}
    
}
