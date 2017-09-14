/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Band;
import SqlEntities.CalDate;
import SqlEntities.Equipment;
import SqlEntities.Receipt;
import SqlEntities.Service;
import SqlEntities.Technician;
import StorageUnit.StorageManager;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class BookBandController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button bookBandMainButton;
    @FXML
    private Label gifLabel;
    @FXML
    private ListView<String> listViewBands;
    @FXML
    private ListView<String> listViewCheckout;
    @FXML
    private ListView<String> serviceListView;
    @FXML
    private ListView<String> listViewEquipment;
    @FXML
    private ListView<String> listViewTechnician;
    @FXML
    private CheckBox bandExistCheckBox;
    @FXML
    private Label priceLabel;
    @FXML
    private Label checkoutLabel;
    @FXML
    private Button deleteSelectedButon;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label selectTechnicianLabel;
    @FXML
    private Label selectedServiceLabel;
    @FXML
    private Label selectedBandLabel;
    @FXML
    private Label selectedEquipmentLabel;
    @FXML
    private TextField quantitiOfServiceInput;
    @FXML
    private Button addNewBandButton;
    @FXML
    private Label gifLabel1;

    /**
     * Initializes the controller class.
     */
    private StorageManager storage = new StorageManager();//For Moving Resocrcess
    private int id = 0;
    private SqlWaiter waiter;

    protected List<String> indexListBnd = new ArrayList<>();
    protected ListProperty<String> listPropertyBand = new SimpleListProperty<>();

    protected List<String> indexListEq = new ArrayList<>();
    protected ListProperty<String> listPropertyEq = new SimpleListProperty<>();

    protected List<String> indexListSer = new ArrayList<>();
    protected ListProperty<String> listPropertySer = new SimpleListProperty<>();

    protected List<String> indexListTech = new ArrayList<>();
    protected ListProperty<String> listPropertyTech = new SimpleListProperty<>();

    protected List<String> indexListCheckout = new ArrayList<>();
    protected ListProperty<String> listPropertyCheckout = new SimpleListProperty<>();

    private Band selectedBand;
    private Equipment selectedEquipment;
    private Service selectedService;
    private Technician selectedTechician;
    private int selectedCheckout;
    private double price;
    
    private List<Equipment> eqList;
    private List<Service> serList;
    private List<Technician> techList;
    
    private Date date;
    
    private int ID;
    private boolean updated;
    private Receipt re;
    
      public void setSqlWaiter(SqlWaiter waiter,int id) {
        this.waiter = waiter;
        this.ID=id;
        updated=true;
        re=waiter.getReceiptById(id);
    }
    
    public void iniListViews() {
        listPropertyCheckout.set(FXCollections.observableArrayList(indexListCheckout));
        listViewCheckout.itemsProperty().bind(listPropertyCheckout);

        listPropertyBand.set(FXCollections.observableArrayList(indexListBnd));
        listViewBands.itemsProperty().bind(listPropertyBand);

        listPropertyEq.set(FXCollections.observableArrayList(indexListEq));
        listViewEquipment.itemsProperty().bind(listPropertyEq);

        listPropertySer.set(FXCollections.observableArrayList(indexListSer));
        serviceListView.itemsProperty().bind(listPropertySer);

        listPropertyTech.set(FXCollections.observableArrayList(indexListTech));
        listViewTechnician.itemsProperty().bind(listPropertyTech);

    }

    public BookBandController() {
        eqList=new ArrayList<>();
        serList=new ArrayList<>();
        techList=new ArrayList<>();
        price=0.0;
    }

    public void setSqlWaiter(SqlWaiter waiter) {
        this.waiter = waiter;
    }
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gifLabel.setGraphic(storage.getAnimationLogin());
        this.gifLabel1.setGraphic(storage.getAnimationLogin());
        this.listViewBands.setVisible(true);
        this.addNewBandButton.setVisible(false);   
        this.datePicker.setValue(LocalDate.now());
        this.quantitiOfServiceInput.setVisible(false);
        iniListViews();
        iniLisiners();
        loadeDataToListViews();
        
        //Style
        this.mainPane.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
        this.addNewBandButton.setStyle("-fx-base: #ff8c00;-fx-text-fill: #ffffff;");
        this.deleteSelectedButon.setStyle("-fx-base:  #b22222; -fx-text-fill:  #ffffff;");
        this.bookBandMainButton.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        
        if(this.updated)
        {
            Date booked = re.getIdDate().getBooked();///get date to be done
            List<Equipment> equipmentList = re.getEquipmentList();
            List<Service> serviceList = re.getServiceList();
            List<Technician> technicianList = re.getTechnicianList();
            selectedBand=re.getBandId();
           
                this.datePicker.setValue(booked.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                this.techList=technicianList;
                this.eqList=equipmentList;
                this.serList=serviceList;
                this.price= Double.parseDouble(re.getTotalpay());
                this.priceLabel.setText(this.priceLabel.getText()+" "+price);
                for(Equipment e:equipmentList)
                {
                   listPropertyCheckout.add("Equipment "+e.getEquipId()+" "+e.getNameEq()+" "+e.getCost()+" €");
                }
                
                for(Service e:serviceList)
                {
                    listPropertyCheckout.add("Service "+e.getServiceId()+" "+e.getTypeofService()+" "+e.getCost()+" €");
                }
                for(Technician e:technicianList)
                {
                    listPropertyCheckout.add("Technician "+e.getIdTechnician()+" "+e.getTechnicianjob()+" "+e.getCost()+" €");
                }
        }
        
    }

    public void loadeDataToListViews() {

        for (Band e : this.waiter.getAllBands()) {
            this.listPropertyBand.add(e.getBandId() + " " + e.getBandName());
        }

        for (Equipment e : this.waiter.getAllEquipment()) {
            this.listPropertyEq.add(e.getEquipId() + " " + e.getNameEq() + " " + e.getCost());

        }

        for (Service e : this.waiter.getAllServices()) {
            this.listPropertySer.add(e.getServiceId() + " " + e.getTypeofService() + " " + e.getCost());
        }

        for (Technician e : this.waiter.getAllTechnicians()) {
            this.listPropertyTech.add(e.getIdTechnician() + " " + e.getTechnicianjob() + " " + e.getCost());
        }

    }

    public void iniLisiners() {
        this.listViewBands.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }

                selectedBand = waiter.getBandById(id);

            }
        });

        this.listViewCheckout.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                selectedCheckout = listViewCheckout.getSelectionModel().getSelectedIndex();

            }
        });

        this.listViewEquipment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }

                selectedEquipment = waiter.getEquipByID(id);

            }
        });

        this.listViewTechnician.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }

                selectedTechician = waiter.getTechnByID(id);

            }
        });

        this.serviceListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }

                selectedService = waiter.getServiceByID(id);

            }
        });

    }

    @FXML
    private void bookedBandMainCliked(MouseEvent event) throws IOException {
        //Band is booked
         LocalDate value = this.datePicker.getValue();
        Instant toInstant = value.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        this.date=Date.from(toInstant);
        if(!updated){
        
        CalDate addCalendarDate = this.waiter.addCalendarDate(date, "");
       if(addCalendarDate!=null){
           
        this.waiter.addRecepie(this.price+" ","No", selectedBand, addCalendarDate, selectedBand.getManagerId(), waiter.getCurent_user(), eqList, serList, techList);
         Stage mainRecordingStudio = storage.getMainRecordingStudio(waiter);
         mainRecordingStudio.show();
         Stage stageL = (Stage) priceLabel.getScene().getWindow();
         stageL.close();
       }
        else
        {
            //pop up window if band is booked
            Stage popUPWIndow = storage.getPopUPWIndow("Band Is Arledy Booked on This Day");
            popUPWIndow.show();
        }
        
        }
        else
        {
            //data pop up window
            this.waiter.updateCalendar(re.getIdDate().getIdDate(), null, date);
            this.waiter.updateRecepie(re.getIdReceipt(),this.price+" ", "No", selectedBand, selectedBand.getManagerId(), this.waiter.getCaleDateByid(re.getIdDate().getIdDate()), waiter.getCurent_user(), serList, eqList, techList);
         Stage mainRecordingStudio = storage.getMainRecordingStudio(waiter);
         mainRecordingStudio.show();
         Stage stageL = (Stage) priceLabel.getScene().getWindow();
         stageL.close();
        
        }
        
        
    }

    @FXML
    private void choseBandMauseClicked(MouseEvent event) {
         int clickCount = event.getClickCount();
       if(clickCount==2)
         {
         //don't know
           
         }
    }

    @FXML
    private void datePickerSelected(MouseEvent event) {
       
    }
    
    @FXML
    private void onMauseClickedReceit(MouseEvent event) {
        //don't know
    }

    @FXML
    private void serviceListVlicked(MouseEvent event) {
         int clickCount = event.getClickCount();
       if(clickCount==2 && serList.contains(selectedService)!=true)
         {
            this.serList.add(selectedService);
             this.price+= Double.parseDouble(this.selectedService.getCost());
             this.listPropertyCheckout.add("Service "+selectedService.getServiceId()+" "+selectedService.getTypeofService()+" "+selectedService.getCost()+" €");
       //     this.selectedService=null;
           this.priceLabel.setText("Price :"+price+"€");
            clickCount=0; 
           
         }
    }

    @FXML
    private void equipmentMauseClicked(MouseEvent event) {
         int clickCount = event.getClickCount();
       if(clickCount==2&& eqList.contains(selectedEquipment)!=true)
         {
             
            this.eqList.add(selectedEquipment);
             this.price+= Double.parseDouble(this.selectedEquipment.getCost());
             this.listPropertyCheckout.add("Equipment "+selectedEquipment.getEquipId()+" "+selectedEquipment.getNameEq()+" "+selectedEquipment.getCost()+" €");
        //    this.selectedEquipment=null;
           this.priceLabel.setText("Price :"+price+"€");
            clickCount=0; 
           
         }
    }

    @FXML
    private void technicianMauseClicked(MouseEvent event) {
         int clickCount = event.getClickCount();
       if(clickCount==2 && techList.contains(selectedTechician)!=true)
         {
           this.techList.add(selectedTechician);
             this.price+= Double.parseDouble(this.selectedTechician.getCost());
             this.listPropertyCheckout.add("Technician "+selectedTechician.getIdTechnician()+" "+selectedTechician.getTechnicianjob()+" "+selectedTechician.getCost()+" €");
         //   this.selectedTechician=null;
           this.priceLabel.setText("Price :"+price+"€");
            clickCount=0; 
           
         }
    }

    @FXML
    private void bandExistComboBox(MouseEvent event) {
        if(this.bandExistCheckBox.isSelected()){
        this.listViewBands.setVisible(true);
        this.addNewBandButton.setVisible(false);}
        else
        {
          this.listViewBands.setVisible(false);
        this.addNewBandButton.setVisible(true);   
        }
    }

    @FXML
    private void deleteCheckoutpreased(MouseEvent event) {
        String get = this.listPropertyCheckout.get(this.selectedCheckout);
         String type ;
         int iD;
         StringTokenizer st = new StringTokenizer(get);
                while (st.hasMoreTokens()) {
             type = st.nextToken(); //type
                 iD = Integer.parseInt(st.nextToken());//id
                    st.nextToken();//name
            String nextToken = st.nextToken(); //cost
                 this.price-= Double.parseDouble(nextToken.substring(0, nextToken.length()-1));
                 this.priceLabel.setText("Price :"+price+"€");
                 if(type=="Technician")
                 {
                     this.techList.remove(this.waiter.getTechnByID(iD));
                 }else if(type=="Equipment")
                 {
                     this.eqList.remove(this.waiter.getServiceByID(iD));
                 }else if(type=="Service")
                 {
                     this.serList.remove(this.waiter.getServiceByID(iD));
                 }
                     
                    break;
                }
        this.listPropertyCheckout.remove(this.selectedCheckout);
    }

    @FXML
    private void quantityTextFildPreased(KeyEvent event) {
        
        //If Key Preased Add Number of services to checkout
    }

    @FXML
    private void quantityCliked(MouseEvent event) {
         int clickCount = event.getClickCount();
       if(clickCount==2)
       {
           this.quantitiOfServiceInput.setText(null);
       }
    }

    @FXML
    private void addNewBandButonCliked(MouseEvent event) throws IOException {
        Stage makeBookingStage = storage.getAddBandStage(waiter);
        makeBookingStage.show();

        Stage stageL = (Stage) gifLabel.getScene().getWindow();
        stageL.close();

    }

}
