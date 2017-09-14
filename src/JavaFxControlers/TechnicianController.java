/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Employe;
import SqlEntities.Technician;
import StorageUnit.StorageManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class TechnicianController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label gifLabel;
    @FXML
    private Button addTechnician;
    @FXML
    private Label techCheck;
    @FXML
    private TextField techJob;
    @FXML
    private Label costCheck;
    @FXML
    private TextField cost;
    @FXML
    private ListView<String> employeeListView;
    @FXML
    private Label choseEmploye;

    private StorageManager storage = new StorageManager();
    private boolean techCost, techjob,employeChose;
    private Employe selectedEmp;
    protected List<String> indexList = new ArrayList<>();
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    private boolean update=false;
    private int id=0;
    private Technician technByID;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
        addTechnician.setStyle("-fx-base:#228b22;-fx-text-fill: #ffffff;");
        gifLabel.setGraphic(storage.getAnimationLogin());
        costCheck.setGraphic(storage.getWrong());
        techCheck.setGraphic(storage.getWrong());
        addTechnician.setVisible(false);
        this.techjob = false;
        this.techCost = false;
        this.employeChose=false;
        listProperty.set(FXCollections.observableArrayList(indexList));
        this.employeeListView.itemsProperty().bind(listProperty);
        
         iniLisiners();
        iniListView();
        
        if(this.update)
        {
            this.techJob.setText(this.technByID.getTechnicianjob());
            this.cost.setText(this.technByID.getCost());
            this.selectedEmp=this.technByID.getIdEmploye();
            this.employeChose=true;
            this.employeeListView.getSelectionModel().select(selectedEmp.getIdEmp()+" "+selectedEmp.getEmFirname()+" "+selectedEmp.getEmLastname());
        //    System.out.println(selectedEmp.getIdEmp()+" "+selectedEmp.getEmFirname()+" "+selectedEmp.getEmLastname());
            int selectedIndex = this.employeeListView.getSelectionModel().getSelectedIndex();
          //  this.employeeListView.getSelectionModel().select(null);
          //   System.out.println(selectedIndex);
            this.employeeListView.getFocusModel().focus(selectedIndex);
            this.addTechnician.setText("Update Technician");
            this.addTechnician.setStyle("-fx-base:  #ff8c00; -fx-text-fill:  #ffffff;");
        }
        
        
       
    }

    public void iniLisiners() {
        this.cost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               
                if (newValue.length() > 0) {
                    costCheck.setGraphic(storage.getRiGht());
                    techCost = true;
                } else {
                    costCheck.setGraphic(storage.getWrong());
                    techCost = false;
                }
                if (techCost == true && techjob == true&&employeChose==true) {
                    addTechnician.setVisible(true);
                } else {
                    addTechnician.setVisible(false);
                }

            }
        });

        this.techJob.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 0) {
                    techCheck.setGraphic(storage.getRiGht());
                    techjob = true;
                } else {
                    techCheck.setGraphic(storage.getWrong());
                    techjob = false;
                }
               if (techCost == true && techjob == true&&employeChose==true) {
                    addTechnician.setVisible(true);
                } else {
                    addTechnician.setVisible(false);
                }
            }
        });

    }

    public void iniListView() {

        
        for(Employe em :this.sqlWaiter.getAllEmplList())
        {
           listProperty.add(em.getIdEmp()+" "+em.getEmFirname()+" "+em.getEmLastname());
        }
        
        this.employeeListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                StringTokenizer st = new StringTokenizer(newValue);
                int id = 0;
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }
                selectedEmp =sqlWaiter.getEmpByID(id);
                employeChose=true;
                
                 if (techCost == true && techjob == true&&employeChose==true) {
                    addTechnician.setVisible(true);
                } else {
                    addTechnician.setVisible(false);
                }
             }
        });
    }
        
    

    @FXML
    private void addTechnicianButton(MouseEvent event) throws IOException {
        
         if(this.update)
        {
           this.sqlWaiter.updateTechnician(id,this.techJob.getText(), selectedEmp, this.cost.getText());
        }
         else{
        
        this.sqlWaiter.addTechnician(this.techJob.getText(),this.cost.getText(), selectedEmp);
         }
        Stage addManagerStage = this.storage.getMainManagerWindow(sqlWaiter);
        addManagerStage.show();
         Stage stageL = (Stage) addTechnician.getScene().getWindow();
        stageL.close();
        
    }

    public TechnicianController() {
    }

    private SqlWaiter sqlWaiter;

    public void setSQlWaiter(SqlWaiter updateSql) {
        this.sqlWaiter = updateSql;
    }

     public void setSQlWaiter(SqlWaiter updateSql,int id)
    {
        this.id=id;
        this.sqlWaiter=updateSql;
       technByID = this.sqlWaiter.getTechnByID(id);
       this.update=true;
        
    }
    
}
