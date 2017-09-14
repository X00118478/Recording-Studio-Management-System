/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Service;
import StorageUnit.StorageManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class ServiceChartController implements Initializable {
    @FXML
    private AnchorPane main;
    @FXML
    private Label gifLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private PieChart charPie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniPieChart();
         StorageManager storage = new StorageManager();
          this.gifLabel.setGraphic(storage.getAnimationLogin());
            this.infoLabel.setText("Most Used Equipment");
    }    
    
    public void iniPieChart()
    {
         ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        List<Service> allServices = this.sqlWaiter.getAllServices();
        for(Service e :allServices)
        {
            chartData.add(new PieChart.Data(e.getTypeofService(), e.getReceiptList().size()+0.0));
        }
        this.charPie.getData().addAll(chartData);
        
     //   chartData.add(new PieChart.Data("Test", 123+0.0));
        
        charPie.setTitle("Popular Services");
        charPie.setAnimated(true);
        charPie.animatedProperty();
    }
    
    public ServiceChartController(){}
    
    private SqlWaiter sqlWaiter;
     public void setSQlWaiter(SqlWaiter updateSql)
    {
        this.sqlWaiter=updateSql;
    }
    
}
