package JavaFxControlers;

import StorageUnit.StorageManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.event.ChangeListener;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AddManagerController implements Initializable {
    @FXML
    private Pane mainAddManagerPanel;
    @FXML
    private Label gifLabel;
    @FXML
    private Button addManager;
    @FXML
    private TextField manFirstName;
    @FXML
    private TextField managerLastName;
    @FXML
    private TextField managerContactNumber;
    @FXML
    private Label manFNameCheck;
    @FXML
    private Label manLNameCheck;
    @FXML
    private Label manContactNumberCheck;
    @FXML
    private TextField addressLine1;
    @FXML
    private TextField addressLine2;
    @FXML
    private TextField city;
    @FXML
    private TextField county;
    @FXML
    private TextField country;
    @FXML
    private Label manaddressFirstLine1Check;
    @FXML
    private Label manaddressFirstLine2Check;
    @FXML
    private Label citycheck;
    @FXML
    private Label countyCheck;
    @FXML
    private Label countryCheck;
    @FXML
    private TextField managerEmail;
    @FXML
    private Label manEmailCheck;

    /**
     * Initializes the controller class.
     */
   private SqlWaiter waiter;
   
    
   public AddManagerController(){}
   
   public void setSqlWaiter(SqlWaiter waiter)
   {
       this.waiter=waiter;
   }
    
    private StorageManager storage = new StorageManager();//For Moving Resocrcess
    SqlWaiter sql = new SqlWaiter();
    private boolean FirstName, lastName, number, email, address1, address2, cty, count, countr;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gifLabel.setGraphic(storage.getAnimationLogin());
         this.mainAddManagerPanel.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
        this.addManager.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        
        initListeners();
        
    }    

    @FXML
    private void addManagerMaseCliked(MouseEvent event) throws IOException {
      
        String address = addressLine1.getText() + "" + addressLine2.getText() + "" + city.getText()
                        + "" + county.getText() + "" + country.getText();
        sql.addManager(manFirstName.getText(), managerLastName.getText(), address, managerContactNumber.getText(), managerEmail.getText());
        
          Stage makeBookingStage = storage.getAddBandStage(this.sql);
       makeBookingStage.show();
        
        Stage stageL = (Stage) manFirstName.getScene().getWindow();
                stageL.close();
    }

    @FXML
    private void managerFNameChange(InputMethodEvent event) {
        if(manFirstName.getText() == null || manFirstName.getText().length() == 0) {
            manFNameCheck.setGraphic(storage.getWrong());
        }
        else {
            manFNameCheck.setGraphic(storage.getRiGht());
        }
    }

    @FXML
    private void managerLNameChange(InputMethodEvent event) {
        if(managerLastName.getText() == null || managerLastName.getText().length() == 0) {
            manLNameCheck.setGraphic(storage.getWrong());
        }
        else {
            manLNameCheck.setGraphic(storage.getRiGht());
        }
    }

    @FXML
    private void managerContactChange(InputMethodEvent event) {
        if(managerContactNumber.getText() == null || managerContactNumber.getText().length() == 0) {
            manContactNumberCheck.setGraphic(storage.getWrong());
        }
        else {
            manContactNumberCheck.setGraphic(storage.getRiGht());
        }
    }
    
    @FXML
    private void managerEmailChange(InputMethodEvent event) {
        if(managerEmail.getText() == null || managerEmail.getText().length() == 0) {
            manEmailCheck.setGraphic(storage.getWrong());
        }
        else {
            manEmailCheck.setGraphic(storage.getRiGht());
        }
    }

    @FXML
    private void addressLine1Change(InputMethodEvent event) {
        if(addressLine1.getText() == null || addressLine1.getText().length() == 0) {
            manaddressFirstLine1Check.setGraphic(storage.getWrong());
        }
        else {
            manaddressFirstLine1Check.setGraphic(storage.getRiGht());
        }
    }

    @FXML
    private void addressLine2Change(InputMethodEvent event) {
        if(addressLine2.getText() == null || addressLine2.getText().length() == 0) {
            manaddressFirstLine2Check.setGraphic(storage.getWrong());
        }
        else {
            manaddressFirstLine2Check.setGraphic(storage.getRiGht());
        }
    }

    @FXML
    private void cityChange(InputMethodEvent event) {
        if(city.getText() == null || city.getText().length() == 0) {
            citycheck.setGraphic(storage.getWrong());
        }
        else {
            citycheck.setGraphic(storage.getRiGht());
        }
    }

    @FXML
    private void countyChange(InputMethodEvent event) {
        if(county.getText() == null || county.getText().length() == 0) {
            countyCheck.setGraphic(storage.getWrong());
        }
        else {
            countyCheck.setGraphic(storage.getRiGht());
        }
    }

    @FXML
    private void countryChange(InputMethodEvent event) {
        if(country.getText() == null || country.getText().length() == 0) {
            countryCheck.setGraphic(storage.getWrong());
        }
        else {
            countryCheck.setGraphic(storage.getRiGht());
        }
    }
    
    public void initListeners() {
       
            this.manFirstName.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(manFirstName.getText().length() > 0) {
                    FirstName = true;
                    manFNameCheck.setGraphic(storage.getRiGht());
                }
                else {
                    FirstName = false;
                    manFNameCheck.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
        });

            this.managerLastName.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(managerLastName.getText().length() > 0) {
                    lastName = true;
                    manLNameCheck.setGraphic(storage.getRiGht());
                }
                else {
                    lastName = false;
                    manLNameCheck.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
            });
            
            this.managerContactNumber.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(managerContactNumber.getText().length() > 0) {
                    number = true;
                    manContactNumberCheck.setGraphic(storage.getRiGht());
                }
                else {
                    number = false;
                    manContactNumberCheck.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
            });
            
            
            this.managerEmail.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(managerEmail.getText().length() > 0) {
                    email = true;
                    manEmailCheck.setGraphic(storage.getRiGht());
                }
                else {
                    email = false;
                    manEmailCheck.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
            });
            
            
            //checks address1 input
          this.addressLine1.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(addressLine1.getText().length() > 0) {
                    address1 = true;
                    manaddressFirstLine1Check.setGraphic(storage.getRiGht());
                }
                else {
                    address1 = false;
                    manaddressFirstLine1Check.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
            });
          
          
          //checks address2 input
          this.addressLine2.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(addressLine2.getText().length() > 0) {
                    address2 = true;
                    manaddressFirstLine2Check.setGraphic(storage.getRiGht());
                }
                else {
                    address2 = false;
                    manaddressFirstLine2Check.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
            });
          
          this.city.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(city.getText().length() > 0) {
                    cty = true;
                    citycheck.setGraphic(storage.getRiGht());
                }
                else {
                    cty = false;
                    citycheck.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
            });
    
          this.county.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(county.getText().length() > 0) {
                    count = true;
                    countyCheck.setGraphic(storage.getRiGht());
                }
                else {
                    count = false;
                    countyCheck.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
            });
          
          this.country.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(country.getText().length() > 0) {
                    countr = true;
                    countryCheck.setGraphic(storage.getRiGht());
                }
                else {
                    countr = false;
                    countryCheck.setGraphic(storage.getWrong());
                }
                if (FirstName == true && lastName == true && number == true && 
                        email == true && address1 == true && address2 == true && cty == true && count == true && countr == true) {
                    addManager.setVisible(true);
                } else {
                    addManager.setVisible(false);
                }
            }
            });
    }
}




