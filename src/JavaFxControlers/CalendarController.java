/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.CalDate;
import StorageUnit.StorageManager;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class CalendarController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label gifLabel;
    @FXML
    private Label mainDate;
    @FXML
    private ListView<String> listViewCalendar;
    @FXML
    private Button deleteSelected;
    @FXML
    private Button exitButton;
    @FXML
    private TextArea textArea;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Button addCalendar;

    /**
     * Initializes the controller class.
     */
    
    protected List<String> indexList = new ArrayList<>();
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    private int id;
    SqlWaiter sqlWaiter;
    StorageManager storage = new StorageManager();
     CalDate caleDateByid;
    public void setSQlWaiter(SqlWaiter updateSql) {
        this.sqlWaiter = updateSql;
    }
    
   public  CalendarController(){}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniLisAll();
        this.gifLabel.setGraphic(storage.getAnimationLogin());
        this.mainPane.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
        this.deleteSelected.setStyle("-fx-base:  #b22222; -fx-text-fill:  #ffffff;");
        this.addCalendar.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        this.exitButton.setVisible(false);
        //this.mainDate.setStyle(null);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        Date date = new Date();
        this.mainDate.setText(dateFormat.format(date));
        this.datepicker.setValue(LocalDate.now());
        this.textArea.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
    }    

    @FXML
    private void deleteSelectedPreased(MouseEvent event) {
         if(caleDateByid.getReceiptList().isEmpty())
        {
        this.sqlWaiter.deleteCalendarDate(id);
        this.listProperty.remove(this.caleDateByid.getIdDate()+" "+caleDateByid.getBooked());
        }
    }

    @FXML
    private void exitPreased(MouseEvent event) {
       
        
    }

    @FXML
    private void addCalendarPreased(MouseEvent event) {
        
        LocalDate value = this.datepicker.getValue();
        Instant toInstant = value.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date=Date.from(toInstant);
        
        CalDate addCalendarDate = this.sqlWaiter.addCalendarDate(date,this.textArea.getText());
     
        if(addCalendarDate!=null)
        {
        listProperty.add(addCalendarDate.getIdDate()+" "+addCalendarDate.getBooked());
        this.textArea.setText("Added To Data Base");
        }
    }
    
    public void iniLisAll()
    {
      listProperty.set(FXCollections.observableArrayList(indexList));
        this.listViewCalendar.itemsProperty().bind(listProperty);   
        
        
        this.listViewCalendar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue!=null){
                if(newValue.length()>6){
                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                    
                }
                     caleDateByid = sqlWaiter.getCaleDateByid(id);
                textArea.setText(caleDateByid.getBooked()+"\n\n"+caleDateByid.getNote());
                }
                }
            }
        });
        
        
        for(CalDate e :this.sqlWaiter.getAllCallDate())
        {
            this.listProperty.add(e.getIdDate()+" "+e.getBooked());
        }
        
    }
    
    
    
}
