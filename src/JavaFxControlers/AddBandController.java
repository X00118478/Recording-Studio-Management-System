/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Band;
import SqlEntities.Manager;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AddBandController implements Initializable {

    @FXML
    private Pane mainAddBandPanel;
    @FXML
    private Label gifLabel;
    @FXML
    private Button addBandButton;
    @FXML
    private TextField bandNameInput;
    @FXML
    private TextField numberOfPeopelInput;
    @FXML
    private TextField musicGenerInput;
    @FXML
    private Label bandNameCompliteLabel;
    @FXML
    private Label bandNumberOfPeopelCompleteLabel;
    @FXML
    private Label bandMusicGenerCompleteLabel;
    @FXML
    private Label informationText;
    @FXML
    private ListView<String> listViewOfManagers;
    @FXML
    private CheckBox managerIsInDbCheckBox;
    @FXML
    private TextField shearchManagerButton;
    @FXML
    private Button addManagerButton;
    @FXML
    private Label informationLabelWhatToDo;

    /**
     * Initializes the controller class.
     */
    private SqlWaiter sqlWaiter;
    private StorageManager storage = new StorageManager();//For Moving Resocrcess
    private boolean name, num, musGe;
    protected List<String> indexList = new ArrayList<>();
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    private Manager maneSelected;
    private int id,ID;
    boolean update;
    private Band updated;
    
    public AddBandController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBandButton.setVisible(false);
        gifLabel.setGraphic(storage.getAnimationLogin());
        bandNameCompliteLabel.setGraphic(storage.getWrong());
        bandNumberOfPeopelCompleteLabel.setGraphic(storage.getWrong());
        bandMusicGenerCompleteLabel.setGraphic(storage.getWrong());
        informationLabelWhatToDo.setVisible(false);
        addManagerButton.setVisible(true);
        listViewOfManagers.setVisible(false);
        shearchManagerButton.setVisible(false);
        informationText.setText("Please Enter A Band Name");

        listProperty.set(FXCollections.observableArrayList(indexList));
        this.listViewOfManagers.itemsProperty().bind(listProperty);

        intStyle();
        initLisinersInTextFilds();

        initListView();
        loadeManagers();
        initShearch();
        
        if(update)
        {
         
          this.bandNameInput.setText(updated.getBandName()); 
          this.musicGenerInput.setText(updated.getMusicGeners());
          this.numberOfPeopelInput.setText(updated.getMemberNumber()+"");
          this.listViewOfManagers.setVisible(true);
          addManagerButton.setVisible(false);
          this.listViewOfManagers.getSelectionModel().select(updated.getManagerId().getMaId()+" "+updated.getManagerId().getManagerFname()+" "+updated.getManagerId().getManagerLname());
            int selectedIndex = this.listViewOfManagers.getSelectionModel().getSelectedIndex();
            this.listViewOfManagers.getFocusModel().focus(selectedIndex);
            this.addBandButton.setStyle("-fx-base:  #ff8c00; -fx-text-fill:  #ffffff;");
            this.addBandButton.setText("Update Band");
        }
        
    }

    public void setSQlWaiter(SqlWaiter updateSql) {
        this.sqlWaiter = updateSql;
    }

     public void setSQlWaiter(SqlWaiter updateSql,int ID) {
        this.sqlWaiter = updateSql;
         this.updated=this.sqlWaiter.getBandById(ID);
         this.update=true;
        
    }
    
    public void initLisinersInTextFilds() {
        //Text Change on input on textFild Band Name
        bandNameInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 0) {
                    bandNameCompliteLabel.setGraphic(storage.getRiGht());
                    name = true;
                } else {
                    bandNameCompliteLabel.setGraphic(storage.getWrong());
                    name = false;
                }
                if (name == true && num == true && musGe == true) {
                    addBandButton.setVisible(true);
                } else {
                    addBandButton.setVisible(false);
                }
            }
        });
        //Text Change on input Number of Peopel
        numberOfPeopelInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                  
                
                if (newValue.length() > 0) {
                    bandNumberOfPeopelCompleteLabel.setGraphic(storage.getRiGht());
                    num = true;
                } else {
                    bandNumberOfPeopelCompleteLabel.setGraphic(storage.getWrong());
                    num = false;
                }
                if (name == true && num == true && musGe == true) {
                    addBandButton.setVisible(true);
                } else {
                    addBandButton.setVisible(false);
                }
            }
        });
        //Text Change on input Band Gener
        musicGenerInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 0) {
                    bandMusicGenerCompleteLabel.setGraphic(storage.getRiGht());
                    musGe = true;
                } else {
                    musGe = false;
                    bandMusicGenerCompleteLabel.setGraphic(storage.getWrong());
                }
                if (name == true && num == true && musGe == true) {
                    addBandButton.setVisible(true);
                } else {
                    addBandButton.setVisible(false);
                }
            }
        });
    }

    public void initListView() {
       

        listViewOfManagers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length()>1){
                StringTokenizer st = new StringTokenizer(newValue);
                while (st.hasMoreTokens()) {
                    id = Integer.parseInt(st.nextToken());
                    break;
                }
                }
                maneSelected = sqlWaiter.getManagerByID(id);
            }
        });
    }

    public void loadeManagers() {
        this.listProperty.clear();
        for (Manager mane : sqlWaiter.getAllManagers()) {
            // indexList.add(mane.getMaId() + " " + mane.getManagerFname() + " " + mane.getManagerLname());
            this.listProperty.add(mane.getMaId() + " " + mane.getManagerFname() + " " + mane.getManagerLname());
            //  System.out.println(mane.getManagerFname());
        }

    }

    public void initShearch() {
        List<String> shearchList = new ArrayList<String>();
        this.shearchManagerButton.textProperty().addListener(new ChangeListener<String>() {
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
                    loadeManagers();
                }
            }
        });
    }

    public void intStyle() {
        mainAddBandPanel.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
        addBandButton.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        addManagerButton.setStyle("-fx-base: #ff8c00;-fx-text-fill: #ffffff;");
    }

    @FXML
    private void onAddbandMauseCliked(MouseEvent event) throws IOException {
        //Main Button CLiked
          int numberPeopel = Integer.parseInt(numberOfPeopelInput.getText());
        if(update)
        {
            this.sqlWaiter.updateBand(ID,bandNameInput.getText(), numberPeopel,musicGenerInput.getText(), maneSelected);
        }else
        {
      
        this.sqlWaiter.addband(bandNameInput.getText(), numberPeopel, musicGenerInput.getText(), maneSelected);
        }
        Stage makeBookingStage = this.storage.getMakeBookingStage(sqlWaiter);
        makeBookingStage.show();
        
        Stage stageL = (Stage) addManagerButton.getScene().getWindow();
        stageL.close();
    }

    @FXML
    private void onMauseClikedManagerIsInDB(MouseEvent event) {
        if (managerIsInDbCheckBox.isSelected()) {
            addManagerButton.setVisible(false);
            listViewOfManagers.setVisible(true);
            shearchManagerButton.setVisible(true);
        } else {
            addManagerButton.setVisible(true);
            listViewOfManagers.setVisible(false);
            shearchManagerButton.setVisible(false);
        }

    }

    @FXML
    private void addManagerMauseClicked(MouseEvent event) throws IOException {
        //Here is the buton to go to Add Manager form
        Stage addManagerStage = storage.getAddManagerStage(sqlWaiter);
        addManagerStage.show();

        Stage stageL = (Stage) addManagerButton.getScene().getWindow();
        stageL.close();
    }

}
