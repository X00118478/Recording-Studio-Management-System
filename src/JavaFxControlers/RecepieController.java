/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Equipment;
import SqlEntities.Receipt;
import SqlEntities.Service;
import SqlEntities.Technician;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class RecepieController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ListView<String> listViewRecepie;
    @FXML
    private TextArea textArea;
    @FXML
    private Button deleteSelectedButton;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField shearchTextFild;

    SqlWaiter sqlWaiter;
    protected List<String> indexList = new ArrayList<>();
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    HashMap<String,String> maping;
    private List<String> shearchList=new ArrayList<>();
    String id;
    
    public void iniLiners(){
        
        
        
        this.shearchTextFild.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newValue=newValue.toLowerCase();
                
                 if (newValue.length() > 1) {
                    shearchList.clear();
                    for (String v : listProperty) {
                      v=v.toLowerCase();
                        if (v.indexOf(newValue) != -1) {

                            shearchList.add(v);
                        }
                    }
                    listProperty.clear();
                    listProperty.addAll(shearchList);

                } else {
                    listProperty.clear();
                   loadeData();
                }
                
                
            }});
            
      this.listViewRecepie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               textArea.setText(maping.get(newValue));
               priceLabel.setText("Not Yet done");
               id =newValue;
            }
        });      
        
    }
    
    public RecepieController(){this.maping = new HashMap();
}
    
    public void setSQlWaiter(SqlWaiter updateSql) {
        this.sqlWaiter = updateSql;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         listProperty.set(FXCollections.observableArrayList(indexList));
        this.listViewRecepie.itemsProperty().bind(listProperty);
        iniLiners();
        loadeData();
    }    

    
    
    public void loadeData()
    {
        for(Receipt e:this.sqlWaiter.getAllRecepies())
        {
            this.maping.put(e.getBandId().getBandName(), madeReceit(e));
            this.listProperty.add(e.getBandId().getBandName());
        }
    }
    
    public String madeReceit(Receipt e)
    {
        String re="";
        re="\n Te Receipt for Band ; "+e.getBandId().getBandName()+"\n"
                + "Total Payed :"+e.getTotalpay()+" \n"
                + "The Manager is "+e.getManagerId().getManagerFname()+" "+e.getManagerId().getManagerLname()+""
                + "Manager Email is :"+e.getManagerId().getEmail()+"\n Contact Number :"+e.getManagerId().getContactnumber()+""
                + "Receit Calculate For the Day "+e.getIdDate().getBooked().toString()+"\n Booked By :"+e.getIdDate().getLogin()+""
                + "price Calculate For :";
        for(Equipment s:e.getEquipmentList())
        {
            re+="\n"+s.getNameEq()+" E"+s.getCost();
        }
        for(Service d:e.getServiceList())
        {
            re+="\n"+d.getTypeofService()+" E"+d.getCost();
        }
        for(Technician t:e.getTechnicianList()){
        re+="\n"+t.getTechnicianjob()+" E"+t.getCost();
        }
        re+="___________________________________"+"\n "
                + "total price ="+e.getTotalpay();
        
       
        return re;
    }
    
    
    @FXML
    private void listViewMauseClicked(MouseEvent event) {
    }

    @FXML
    private void deleteMauseClicked(MouseEvent event) {
        
         this.listProperty.remove(id);
        
    }
    
}
