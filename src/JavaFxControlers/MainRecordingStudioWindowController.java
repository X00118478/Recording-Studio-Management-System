/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Band;
import SqlEntities.CalDate;
import SqlEntities.Manager;
import SqlEntities.Receipt;
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
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import otherClassys.ReceitView;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class MainRecordingStudioWindowController implements Initializable {

    @FXML
    private Pane calendarPanel;
    @FXML
    private Button callendarButton1;
    @FXML
    private Button callendarButton2;
    @FXML
    private Button callendarButton3;
    @FXML
    private Button callendarButton4;
    @FXML
    private Button callendarButton5;
    @FXML
    private Button callendarButton6;
    @FXML
    private Button callendarButton7;
    @FXML
    private Button callendarButton8;
    @FXML
    private Button callendarButton9;
    @FXML
    private Button callendarButton10;
    @FXML
    private Button callendarButton11;
    @FXML
    private Button callendarButton12;
    @FXML
    private Button callendarButton13;
    @FXML
    private Button callendarButton14;
    @FXML
    private Button callendarButton15;
    @FXML
    private Button callendarButton16;
    @FXML
    private Button callendarButton17;
    @FXML
    private Button callendarButton18;
    @FXML
    private Button callendarButton19;
    @FXML
    private Button callendarButton20;
    @FXML
    private Button callendarButton21;
    @FXML
    private Button callendarButton22;
    @FXML
    private Button callendarButton23;
    @FXML
    private Button callendarButton24;
    @FXML
    private Button callendarButton25;
    @FXML
    private Button callendarButton26;
    @FXML
    private Button callendarButton27;
    @FXML
    private Button callendarButton28;
    @FXML
    private Button callendarButton29;
    @FXML
    private Button callendarButton30;
    @FXML
    private Button callendarButton31;
    @FXML
    private Button callendarButton32;
    @FXML
    private Button callendarButton33;
    @FXML
    private Button callendarButton34;
    @FXML
    private Button callendarButton35;
    @FXML
    private Button callendarGoLeftButton;
    @FXML
    private Button callendarGoRightButton;
    @FXML
    private Label monthDisplayLabel;
    @FXML
    private TextField messageOfDayTextArea;
    @FXML
    private Label messageOfDayLabel;
    @FXML
    private Button settingsOption;
    @FXML
    private Button helpOption;
    @FXML
    private Button logOutOption;
    @FXML
    private Button receiptsOption;
    @FXML
    private TextField shearchTextFild;
    @FXML
    private Button aboutOption;
    @FXML
    private Button callendarOption;
    @FXML
    private Button manegeRecordingStudio;
    @FXML
    private TableView<ReceitView> columnTable;
    @FXML
    private Button addBandButton;
    @FXML
    private Button updateBandButton;

    /**
     * Initializes the controller class.
     */
    private StorageManager storage = new StorageManager();//For Moving Resocrcess
    private SqlWaiter sqlManager;
    private final ObservableList<ReceitView> data = FXCollections.observableArrayList();
    private ObservableList<ReceitView> filtrData = FXCollections.observableArrayList();
    private List<ReceitView> receitView=new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.calendarPanel.setVisible(false);
//        this.messageOfDayLabel.setLayoutY(120.0);
//        this.messageOfDayTextArea.setLayoutY(100.0);
       
        this.addBandButton.setStyle("-fx-base:  #228b22; -fx-text-fill:  #ffffff;");//Green and font white
        this.updateBandButton.setStyle("-fx-base:  #ff8c00; -fx-text-fill:  #ffffff;");//Dark Orange and font
//        this.deleteBandButton.setStyle("-fx-base:  #b22222; -fx-text-fill:  #ffffff;");//Red and font white
//        this.resetDB_Butonn.setStyle("-fx-base: #2f4f4f; -fx-text-fill:  #ffffff;");//darksaltygrey and font white
        this.logOutOption.setStyle("-fx-base: #dc143c; -fx-text-fill:  #ffffff;");//Light red and white font
        this.calendarPanel.setStyle("-fx-base: #87ceeb");//salte blue
        //   this.dbMainTabelView.setStyle("-fx-base: #4682b4");//steelblue
        iniColumns();
        iniShearchLisner();
        
        //inicilise
        //Manager
        if(this.sqlManager.getCurent_user().getIdEmploye().getEmPremisions().equals("Full"))
        {
            //No restrictions
        }
       
       else  if(this.sqlManager.getCurent_user().getIdEmploye().getEmPremisions().equals("Secretary"))
        {
            this.manegeRecordingStudio.setVisible(false);
        }
         //None
        else  if(this.sqlManager.getCurent_user().getIdEmploye().getEmPremisions().equals("None"))
        {
            this.manegeRecordingStudio.setVisible(false);
            this.addBandButton.setVisible(false);
            this.updateBandButton.setVisible(false);
            this.receiptsOption.setVisible(false);
        } else
        {
             this.manegeRecordingStudio.setVisible(false);
                 this.addBandButton.setVisible(false);
            this.updateBandButton.setVisible(false);
            this.receiptsOption.setVisible(false);
        }
           
            
        
           LocalDate today = LocalDate.now();
           Instant toInstant = today.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date=Date.from(toInstant);
          this.messageOfDayTextArea.setText(this.sqlManager.checkNoteForTheDay(date));
          
    }

    public MainRecordingStudioWindowController() {
    }

    public void setSqlWaiter(SqlWaiter waiter) {
        this.sqlManager = waiter;
    }

    public void iniColumns() {
        List<Receipt> allRecepies = this.sqlManager.getAllRecepies();
        for (Receipt e : allRecepies) {
            this.receitView.add(new ReceitView(e));
            data.add(new ReceitView(e));
        }

        //Main Colmns
        TableColumn band = new TableColumn("Band");
        TableColumn manager = new TableColumn("Manager");
        TableColumn email = new TableColumn("Email");
        TableColumn payed = new TableColumn("Payed");
        TableColumn bokedDate = new TableColumn("Date");
        TableColumn price = new TableColumn("Price");

        //Inside Columns
        //Band
        TableColumn bandName = new TableColumn("Name");
        TableColumn bandNumberOfPeopel = new TableColumn("Number of Peopel");
        //Manager
        TableColumn managerName = new TableColumn("Name");
        TableColumn managerLname = new TableColumn("Surname");
        TableColumn managerContactNumber = new TableColumn("Phone Number");
    //Maping to values in Data

        bandName.setCellValueFactory(new PropertyValueFactory<>("bandName"));
        bandNumberOfPeopel.setCellValueFactory(new PropertyValueFactory<>("numberOfPeopel"));
        managerName.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        managerLname.setCellValueFactory(new PropertyValueFactory<>("managerSurname"));
        managerContactNumber.setCellValueFactory(new PropertyValueFactory<>("managerContactNumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        payed.setCellValueFactory(new PropertyValueFactory<>("payed"));
        bokedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Adding tables
        band.getColumns().add(bandName);
        band.getColumns().add(bandNumberOfPeopel);

        manager.getColumns().add(managerName);
        manager.getColumns().add(managerLname);
        manager.getColumns().add(managerContactNumber);
        //ading value 
        columnTable.setItems(data);
        columnTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);//Auto Resize policy

    //Ading columns to main table
        columnTable.getColumns().addAll(band, manager, email, payed, bokedDate, price);

    }

    public void iniShearchLisner() {
        this.shearchTextFild.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (newValue.length() > 1) {
                    filtrData.clear();
                    for (ReceitView e : receitView) {
                        if (macheData(e)) {
                            filtrData.add(e);
                        }
                    }
                    columnTable.setItems(filtrData);
                } else {
                    columnTable.setItems(data);
                }

            }
        });
    }

    @FXML
    private void callendarButtonClicked(MouseEvent event) {
    }

    @FXML
    private void callendarMonthBack(MouseEvent event) {
    }

    @FXML
    private void callendarMonthFolword(MouseEvent event) {
    }

    @FXML
    private void messageOfTheDayTextChange(InputMethodEvent event) {
    }

    @FXML
    private void onSettingsButtonClicked(MouseEvent event) throws IOException {
        Stage setingsStage = storage.getSetingsStage(sqlManager);
        setingsStage.show();
        
        Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void onHelpButtonClicked(MouseEvent event) throws IOException {
        Stage helRec = this.storage.getHelRec(sqlManager);
        helRec.show();

        Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void onLogeOutButtonClicked(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = storage.getLogin();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

        Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void onReceiptButtonClicked(MouseEvent event) throws IOException {
        Stage recWindow = this.storage.getRecWindow(sqlManager);
        recWindow.show();
        
         Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void onEnterpreasedShearch(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {

            this.filtrData.clear();
            for (ReceitView e : this.receitView) {
                if (macheData(e)) {
                    this.filtrData.add(e);
                }
            }
            this.columnTable.setItems(filtrData);

        }

    }

    @FXML
    private void onAboutButtonClicked(MouseEvent event) throws IOException {
         Stage abautStage = storage.getAbautStage(sqlManager);
            abautStage.show();
             Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void onCallendarButtonClicked(MouseEvent event) throws IOException {
        Stage callendar = this.storage.getCallendar(sqlManager);
        callendar.show();

        Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void onManageButtonClicked(MouseEvent event) throws IOException {
        Stage mainManagerWindow = this.storage.getMainManagerWindow(sqlManager);
        mainManagerWindow.show();

        Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void tableClicked(MouseEvent event) {
    }

    @FXML
    private void addBandButtonClicked(MouseEvent event) throws IOException {
        Stage addBandStage = this.storage.getMakeBookingStage(sqlManager);
        addBandStage.show();

        Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void onUpdateBand(MouseEvent event) throws IOException {
        Stage addUpdate = this.storage.getAddUpdate(sqlManager);
        addUpdate.show();

        Stage stageL = (Stage) logOutOption.getScene().getWindow();
        stageL.close();
    }

    public boolean macheData(ReceitView re) {
        String enter = this.shearchTextFild.getText().toLowerCase();
        int indexOf = re.getBandName().toLowerCase().indexOf(enter);
        if (indexOf != -1) {
            return true;
        }
        int indexOf1 = re.getDate().toLowerCase().indexOf(enter);
        if (indexOf1 != -1) {
            return true;
        }
        int indexOf2 = re.getEmail().toLowerCase().indexOf(enter);
        if (indexOf2 != -1) {
            return true;
        }
        int indexOf3 = re.getManagerContactNumber().toLowerCase().indexOf(enter);
        if (indexOf3 != -1) {
            return true;
        }
        int indexOf4 = re.getManagerName().toLowerCase().indexOf(enter);
        if (indexOf4 != -1) {
            return true;
        }
        int indexOf5 = re.getManagerSurname().toLowerCase().indexOf(enter);
        if (indexOf5 != -1) {
            return true;
        }
        int indexOf6 = re.getNumberOfPeopel().toLowerCase().indexOf(enter);
        if (indexOf6 != -1) {
            return true;
        }
        int indexOf7 = re.getPayed().toLowerCase().indexOf(enter);
        if (indexOf7 != -1) {
            return true;
        }
        int indexOf8 = re.getPrice().toLowerCase().indexOf(enter);
        if (indexOf8 != -1) {
            return true;
        }
        return false;
    }

}
