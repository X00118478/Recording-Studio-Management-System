/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Employe;
import SqlEntities.Equipment;
import SqlEntities.Service;
import SqlEntities.Technician;
import StorageUnit.StorageManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class ManagerWindowController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button addEmployee;
    @FXML
    private Button addEquipment;
    @FXML
    private Button addService;
    @FXML
    private Button addTechnician;
    @FXML
    private Button deleteSelectedLabel;
    @FXML
    private Button goBackToRecordingStudio;
    @FXML
    private Button logOutAndExit;
    @FXML
    private Rectangle equpmeBox;
    @FXML
    private Rectangle serviceBox;
    @FXML
    private Rectangle techBox;
    @FXML
    private Rectangle employeGraph;
    @FXML
    private Label empStatisticLabel;
    @FXML
    private Label equipStatisticLabel;
    @FXML
    private Label servStatisticLabel;
    @FXML
    private ListView<String> listViewOfEmployes;
    @FXML
    private ListView<String> listViewOfEquipment;
    @FXML
    private ListView<String> listViewOfServices;
    @FXML
    private ListView<String> listViewsOfTechnicians;
    @FXML
    private Label employeListLabel;
    @FXML
    private Label equipmentListLabel;
    @FXML
    private Label serviceListLabel;
    @FXML
    private Label techStatisticLabel;
    @FXML
    private Label technicianListLabel;
    @FXML
    private BarChart<String, Number> technicianBarChart;
    @FXML
    private StackedBarChart<String, Number> equipStacBarChart;
    @FXML
    private StackedBarChart<String, Number> employeStacBarChart;
    @FXML
    private PieChart servicesPieChart;

    protected List<String> indexListEm = new ArrayList<>();
    protected ListProperty<String> listPropertyEm = new SimpleListProperty<>();

    protected List<String> indexListEq = new ArrayList<>();
    protected ListProperty<String> listPropertyEq = new SimpleListProperty<>();

    protected List<String> indexListSer = new ArrayList<>();
    protected ListProperty<String> listPropertySer = new SimpleListProperty<>();

    protected List<String> indexListTech = new ArrayList<>();
    protected ListProperty<String> listPropertyTech = new SimpleListProperty<>();

    private StorageManager storage;
    private Object selected;
    private int id = 0;
   
    
    

    /**
     * Initializes the controller class.
     */
    
    public void iniCharts()
    {
    
    
    //Technician
    this.techStatisticLabel.setText("Technicians Jobs done");
   // this.technicianBarChart=new BarChart<String,Number>(xT,yT);  
    this.technicianBarChart.setTitle("Technicians Jobs done");
    
    
     
      XYChart.Series values = new XYChart.Series();
      values.setName("Most Popular Technician");
     
     System.out.println("Tehnician are loding");
     for(Technician e:this.sqlWaiter.getAllTechnicians())
     {
          System.out.println("Technican Added"+e.getTechnicianjob()+"|"+e.getReceiptList().size());
        values.getData().add(new XYChart.Data(e.getTechnicianjob(),e.getReceiptList().size())); 
     }
     technicianBarChart.setAnimated(true);
     technicianBarChart.animatedProperty();
     technicianBarChart.getData().add(values);
    //Services
     System.out.println("Service are loding");
     this.servStatisticLabel.setText("Popular Services");
     
     
       ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        List<Service> allServices = this.sqlWaiter.getAllServices();
        for(Service e :allServices)
        {
            System.out.println("Service are loding"+e.getTypeofService()+"|"+ e.getReceiptList().size()+0.0);
            chartData.add(new PieChart.Data(e.getTypeofService(), e.getReceiptList().size()+0.0));
        }
        this.servicesPieChart.getData().addAll(chartData);
        
        servicesPieChart.setTitle("Popular Services");
  //      servicesPieChart.setAnimated(true);
   //     servicesPieChart.animatedProperty();
        
    //Equipment
         System.out.println("Equipment are loding");
        this.equipStatisticLabel.setText("Most Used Equipment");
   //  equipStacBarChart = new StackedBarChart<String,Number>(xEq,yEq); 
       equipStacBarChart.setTitle("Most Used Equipment");
       XYChart.Series<String,Number> value1 = new XYChart.Series();
        value1.setName("Most Used Equipment");
        
       for (Equipment e:this.sqlWaiter.getAllEquipment())
       {
           
         System.out.println("Equipment are loding"+e.getNameEq()+"|"+e.getReceiptList().size());
          value1.getData().add(new XYChart.Data(e.getNameEq(),e.getReceiptList().size())); 
       }
       
       equipStacBarChart.getData().add(value1);
    ///   equipStacBarChart.setAnimated(true);
    //    equipStacBarChart.animatedProperty();
    //Employe
       System.out.println("Employe are loading");
       this.empStatisticLabel.setText("Most Used Equipment");
    //    this.employeStacBarChart= new StackedBarChart<String,Number>(xEm,yEm); 
         employeStacBarChart.setTitle("Most Used Equipment");
       XYChart.Series<String,Number> value = new XYChart.Series();
        value.setName("Employes And Wages");
        
       for (Employe e:this.sqlWaiter.getAllEmplList())
       {
            System.out.println("Most Used Equipment"+e.getEmFirname()+" "+e.getEmLastname()+"|"+Integer.valueOf(e.getEmWages()));
          value.getData().add(new XYChart.Data(e.getEmFirname()+" "+e.getEmLastname(),Integer.valueOf(e.getEmWages()))); 
       }
       
       employeStacBarChart.getData().add(value);
      // employeStacBarChart.setAnimated(true);
      // employeStacBarChart.animatedProperty();
        
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.storage = new StorageManager();
        this.addEmployee.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        this.addEquipment.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        this.addService.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        this.addTechnician.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        this.mainPane.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
        this.deleteSelectedLabel.setStyle("-fx-base:  #b22222; -fx-text-fill:  #ffffff;");
        this.goBackToRecordingStudio.setStyle("-fx-base: #ff8c00;-fx-text-fill: #ffffff;");
        this.logOutAndExit.setStyle("-fx-base: #2f4f4f; -fx-text-fill:#ffffff;");

        iniListViews();
        iniLisinersForListViews();
        lodeDataToListViews();
        iniCharts();
    }

    public void iniLisinersForListViews() {
        this.listViewOfEmployes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null) {
                    StringTokenizer st = new StringTokenizer(newValue);
                    while (st.hasMoreTokens()) {
                        id = Integer.parseInt(st.nextToken());
                        break;
                    }

                    selected = sqlWaiter.getEmpByID(id);
                }

            }
        });

        //Get selected Equipment
        this.listViewOfEquipment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    StringTokenizer st = new StringTokenizer(newValue);
                    while (st.hasMoreTokens()) {
                        id = Integer.parseInt(st.nextToken());
                        break;
                    }

                    selected = sqlWaiter.getEquipByID(id);

                }
            }
        });

        //get selected service
        this.listViewOfServices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    StringTokenizer st = new StringTokenizer(newValue);
                    while (st.hasMoreTokens()) {
                        id = Integer.parseInt(st.nextToken());
                        break;
                    }

                    selected = sqlWaiter.getServiceByID(id);

                }
            }
        });

        //Get selected technician
        this.listViewsOfTechnicians.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    StringTokenizer st = new StringTokenizer(newValue);
                    while (st.hasMoreTokens()) {
                        id = Integer.parseInt(st.nextToken());
                        break;
                    }

                    selected = sqlWaiter.getTechnByID(id);

                }
            }
        });

    }

    public void iniListViews() {
        listPropertyEm.set(FXCollections.observableArrayList(indexListEm));
        listViewOfEmployes.itemsProperty().bind(listPropertyEm);

        listPropertyEq.set(FXCollections.observableArrayList(indexListEq));
        listViewOfEquipment.itemsProperty().bind(listPropertyEq);

        listPropertySer.set(FXCollections.observableArrayList(indexListSer));
        listViewOfServices.itemsProperty().bind(listPropertySer);

        listPropertyTech.set(FXCollections.observableArrayList(indexListTech));
        listViewsOfTechnicians.itemsProperty().bind(listPropertyTech);

    }

    public ManagerWindowController() {
    }

    private SqlWaiter sqlWaiter;

    public void setSQlWaiter(SqlWaiter updateSql) {
        this.sqlWaiter = updateSql;

    }

    public void lodeDataToListViews() {

        for (Employe e : this.sqlWaiter.getAllEmplList()) {
            this.listPropertyEm.add(e.getIdEmp() + " " + e.getEmFirname() + " " + e.getEmLastname());
        }

        for (Equipment e : this.sqlWaiter.getAllEquipment()) {
            this.listPropertyEq.add(e.getEquipId() + " " + e.getNameEq());

        }

        for (Service e : this.sqlWaiter.getAllServices()) {
            this.listPropertySer.add(e.getServiceId() + " " + e.getTypeofService());
        }

        for (Technician e : this.sqlWaiter.getAllTechnicians()) {
            this.listPropertyTech.add(e.getIdTechnician() + " " + e.getTechnicianjob());
        }
    }

    @FXML
    private void selectedEmpClicked(MouseEvent event) throws IOException {
        int clickCount = event.getClickCount();
        if (clickCount == 2) {
            Stage updateEmploye = this.storage.getUpdateEmploye(sqlWaiter, id);
            updateEmploye.show();
            clickCount = 0;
            Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
            stageL.close();

        }
    }

    @FXML
    private void selectedEquipClick(MouseEvent event) throws IOException {
        int clickCount = event.getClickCount();
        if (clickCount == 2) {
            //update equipemnt
            clickCount = 0;
            Stage updateEquipment = this.storage.getUpdateEquipment(sqlWaiter, id);
            updateEquipment.show();
            Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
            stageL.close();
        }
    }

    @FXML
    private void selectedServClicked(MouseEvent event) throws IOException {
        int clickCount = event.getClickCount();
        if (clickCount == 2) {
            //update service
            clickCount = 0;
            Stage updateService = this.storage.getUpdateService(sqlWaiter, id);
            updateService.show();

            Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
            stageL.close();
        }
    }

    @FXML
    private void selectedTechClicked(MouseEvent event) throws IOException {
        int clickCount = event.getClickCount();
        if (clickCount == 2) {
            //update technician
            clickCount = 0;
            Stage updateTechnician = this.storage.getUpdateTechnician(sqlWaiter, id);
            updateTechnician.show();

            Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
            stageL.close();
        }
    }

    @FXML
    private void addEmployeCliked(MouseEvent event) throws IOException {
        Stage addEmploye = this.storage.getAddEmploye(sqlWaiter);
        addEmploye.show();
        Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void addEquipmentClicked(MouseEvent event) throws IOException {
        Stage addEquipment1 = this.storage.getAddEquipment(sqlWaiter);
        // addEquipment1.initStyle(StageStyle.TRANSPARENT);
        addEquipment1.show();
        Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void addServicesClicked(MouseEvent event) throws IOException {
        Stage addService1 = this.storage.getAddService(sqlWaiter);
        //  addService1.initStyle(StageStyle.TRANSPARENT);
        addService1.show();
        Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void addTechnicianClicked(MouseEvent event) throws IOException {
        Stage addTechnician1 = this.storage.getAddTechnician(sqlWaiter);
        //  addTechnician1.initStyle(StageStyle.TRANSPARENT);
        addTechnician1.show();
        Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void deletSelectedImecBttonClicked(MouseEvent event) {
        //here u delete
        if (this.selected != null) {

            if (this.selected.getClass().toString().equals("class SqlEntities.Employe")) {
                //delete employe
                if (this.sqlWaiter.getCurent_user().getIdEmploye().getIdEmp() != id) {
                    Employe e = (Employe) selected;
                    this.listPropertyEm.remove(e.getIdEmp() + " " + e.getEmFirname() + " " + e.getEmLastname());
                    this.sqlWaiter.deleteEmployee(id);
                }

            }
            if (this.selected.getClass().toString().equals("class SqlEntities.Equipment")) {
                //delete equipment
                Equipment e = (Equipment) selected;
                this.listPropertyEq.remove(e.getEquipId() + " " + e.getNameEq());
                this.sqlWaiter.deleteEquipment(id);
                //  this.indexListEq
            }
            if (this.selected.getClass().toString().equals("class SqlEntities.Service")) {
                Service e = (Service) selected;
                this.listPropertySer.remove(e.getServiceId() + " " + e.getTypeofService());
                this.sqlWaiter.deleteService(id);
            }
            if (this.selected.getClass().toString().equals("class SqlEntities.Technician")) {
                //delete technician
                Technician e = (Technician) selected;
                this.listPropertyTech.remove(e.getIdTechnician() + " " + e.getTechnicianjob());
                this.sqlWaiter.deleteTechnician(id);
            }

        }

    }

    @FXML
    private void goBackButtonCliked(MouseEvent event) throws IOException {
        Stage mainRecordingStudio = this.storage.getMainRecordingStudio(sqlWaiter);

        mainRecordingStudio.show();
        mainRecordingStudio.setMaximized(true);

        Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void logOutButtonClicked(MouseEvent event) {
        Stage stageL = (Stage) goBackToRecordingStudio.getScene().getWindow();
        stageL.close();

    }

    @FXML
    private void techChartClicked(MouseEvent event) throws IOException {
        //Charts
        Stage techChart = storage.getTechChart(sqlWaiter);
        techChart.show();
    }

    @FXML
    private void equipmentChart(MouseEvent event) throws IOException {
        //charts
        Stage equipChart = storage.getEquipChart(sqlWaiter);
        equipChart.show();
    }

    @FXML
    private void emplChart(MouseEvent event) throws IOException {
        // charts
        Stage empChart = storage.getEmpChart(sqlWaiter);
        empChart.show();
    }

    @FXML
    private void servicesChart(MouseEvent event) throws IOException {
        //charts
        Stage serviChart = storage.getServiChart(sqlWaiter);
        serviChart.show();
    }

}
