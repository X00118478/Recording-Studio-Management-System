/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Band;
import SqlEntities.Manager;
import SqlEntities.Receipt;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class UpdateAddController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ListView<String> bandList;
    @FXML
    private ListView<String> managerList;
    @FXML
    private ListView<String> bookingList;
    @FXML
    private Button addBand;
    @FXML
    private Button addManager;
    @FXML
    private Button addBooking;
    @FXML
    private Button deleteSelected;

    
      protected List<String> indexListBn = new ArrayList<>();
    protected ListProperty<String> listPropertyBn = new SimpleListProperty<>();
    
      protected List<String> indexListMn = new ArrayList<>();
    protected ListProperty<String> listPropertyMn = new SimpleListProperty<>();
    
    
      protected List<String> indexListBk = new ArrayList<>();
    protected ListProperty<String> listPropertyBk = new SimpleListProperty<>();
    
     private  SqlWaiter sqlWaiter;
  private  StorageManager storage;
    private int id;
   private  Object selectedObject;
 //  private Band selectedBand;
 //  private Manager selectedManager;
 //  private Receipt selectedReceipt;
    /**
     * Initializes the controller class.
     */
    
    public void iniListView()
    {
        listPropertyBn.set(FXCollections.observableArrayList(indexListBn));
        bandList.itemsProperty().bind(listPropertyBn);
        
         listPropertyMn.set(FXCollections.observableArrayList(indexListMn));
        managerList.itemsProperty().bind(listPropertyMn);
        
         listPropertyBk.set(FXCollections.observableArrayList(indexListBk));
        bookingList.itemsProperty().bind(listPropertyBk);
    }
    
  public void lodeData()
  {
      for(Band e :this.sqlWaiter.getAllBands())
      {
          listPropertyBn.add(e.getBandId()+" "+e.getBandName()+" "+e.getManagerId().getMaId()+" "+e.getManagerId().getManagerFname());
      }
      
       for(Manager e :this.sqlWaiter.getAllManagers())
      {
          listPropertyMn.add(e.getMaId()+" "+e.getManagerFname()+" "+e.getManagerLname());
      }
       
       for(Receipt e :this.sqlWaiter.getAllRecepies())
      {
          listPropertyBk.add(e.getIdReceipt()+" "+e.getBandId().getBandName()+" "+e.getIdDate().getBooked());
      }
  }
    
   public UpdateAddController()//Constructor
   {
   this.storage=new StorageManager();
   }
    
   public void iniListLisners()
   {
       bandList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 if(newValue!=null){
                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }
               // selectedBand= 
                selectedObject =sqlWaiter.getBandById(id);
                 }
            }
        });
       
       managerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 if(newValue!=null){
                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }
             //   selectedManager=
                selectedObject = sqlWaiter.getManagerByID(id);
                 }
            }
        });
       
       bookingList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if(newValue!=null){
                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }
             //   selectedReceipt =
                selectedObject = sqlWaiter.getReceiptById(id);
                }
            }
        });
       
   }
   
   
    public void setSQlWaiter(SqlWaiter updateSql) {
        this.sqlWaiter = updateSql;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       iniListView();
       iniListLisners();
       lodeData();
       
       this.mainPane.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
       this.addBand.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
       this.addBooking.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
       this.addManager.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
       this.deleteSelected.setStyle("-fx-base:  #b22222; -fx-text-fill:  #ffffff;");
       
    }    

    @FXML
    private void bandMauseCliked(MouseEvent event) throws IOException {
        int clickCount = event.getClickCount();
       if(clickCount==2)
       {
            Stage updateBandStage = storage.getUpdateBandStage(sqlWaiter, id);
            updateBandStage.show();
           
         Stage stageL = (Stage) addBand.getScene().getWindow();
        stageL.close();
       }
    }

    @FXML
    private void managerMauseClicked(MouseEvent event) {
       int clickCount = event.getClickCount();
       if(clickCount==2)
       {
           
         Stage stageL = (Stage) addBand.getScene().getWindow();
        stageL.close();
       }
    
    }

    @FXML
    private void bookingMauseClicked(MouseEvent event) throws IOException {
        
        int clickCount = event.getClickCount();
       if(clickCount==2)
       {
            Stage updateBookingStage = storage.getUpdateBookingStage(sqlWaiter, id);
            updateBookingStage.show();
            
        Stage stageL = (Stage) addBand.getScene().getWindow();
        stageL.close();
       }
    
    }

    @FXML
    private void addBandMauseClicked(MouseEvent event) throws IOException {
        Stage addBandStage = storage.getAddBandStage(sqlWaiter);
        addBandStage.show();
        
          Stage stageL = (Stage) addBand.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void addManagerMauseClicked(MouseEvent event) throws IOException {
        Stage addManagerStage = storage.getAddManagerStage(sqlWaiter);
        addManagerStage.show();
          Stage stageL = (Stage) addBand.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void addBookingMauseClicked(MouseEvent event) throws IOException {
        Stage makeBookingStage = storage.getMakeBookingStage(sqlWaiter);
        makeBookingStage.show();
          Stage stageL = (Stage) addBand.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void deleteMauseClicked(MouseEvent event) {
        
          if (selectedObject != null) {
              
              System.out.println(selectedObject.getClass().toString());
          if(selectedObject.getClass().toString().contains("class SqlEntities.Band"))    
          {
              Band e =(Band) selectedObject;
              this.listPropertyBn.remove(e.getBandId()+" "+e.getBandName()+" "+e.getManagerId().getMaId()+" "+e.getManagerId().getManagerFname());
              this.sqlWaiter.deleteBand(id);
          }
          if(selectedObject.getClass().toString().contains("class SqlEntities.Manager"))    
          {
              Manager e =(Manager) selectedObject;
              this.listPropertyMn.remove(e.getMaId()+" "+e.getManagerFname()+" "+e.getManagerLname());
              this.sqlWaiter.deleteManager(id);
          }    
          if(selectedObject.getClass().toString().contains("class SqlEntities.Receipt"))    
          {
              Receipt e =(Receipt) selectedObject;
              this.listPropertyBk.remove(e.getIdReceipt()+" "+e.getBandId().getBandName()+" "+e.getIdDate().getBooked());
              this.sqlWaiter.deleteRecepie(id);
          }    
          }
    }
    
}
