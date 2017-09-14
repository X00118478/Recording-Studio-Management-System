/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.Employe;
import StorageUnit.StorageManager;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Alex Mac Uaid X00118478
 */
public class AddEmployeController implements Initializable {

    @FXML
    private Pane mainAddManagerPanel;
    @FXML
    private Label gifLabel;
    @FXML
    private Button addemp;
    @FXML
    private TextField empFirstName;
    @FXML
    private TextField empLastName;
    @FXML
    private TextField empContactNumber;
    @FXML
    private Label emFNameCheck;
    @FXML
    private Label emLNameCheck;
    @FXML
    private Label emContactNumberCheck;
    @FXML
    private TextField empaddresLine1;
    @FXML
    private TextField empaddresLine2;
    @FXML
    private TextField empcity;
    @FXML
    private TextField empcounty;
    @FXML
    private TextField empcountry;
    @FXML
    private Label emaddresFirstLine1Check;
    @FXML
    private Label emaddresFirstLine2Check;
    @FXML
    private Label citycheck;
    @FXML
    private Label countyCheck;
    @FXML
    private Label countryCheck;
    @FXML
    private Label gifLabel1;
    @FXML
    private ComboBox<String> empComboBoxJobTime;
    @FXML
    private TextField empWage;
    @FXML
    private ComboBox<String> empPremision;
    @FXML
    private TextField empEmail;
    @FXML
    private Label userAccountTextFild;
    @FXML
    private TextField userLogin;
    @FXML
    private TextField userPassword;
    @FXML
    private Label timeJobJeckCheck;
    @FXML
    private Label wageCheck;
    @FXML
    private Label remisionCheck;
    @FXML
    private Label emailCheck;
    @FXML
    private Label loginCheck;
    @FXML
    private Label passwordCheck;

    Employe curentEmploye;
    
    /**
     * Interact with the controller class.
     */
    private StorageManager storage = new StorageManager();//For Moving Resocrces
    private Calendar c1 = new GregorianCalendar();
    //Create booleans to store
    private boolean firstName, lastName, contactNumber, addresLine1, addresLine2, city, county, country, comboBoxJobTime, wage, premision, email, login, password;
    private boolean up=false;
    SqlWaiter sqe = new SqlWaiter();

    public AddEmployeController() {
    }

    public void setSqlWaiter(SqlWaiter waiter) {
        this.sqe = waiter;
    }

    public void fillComboBox() {

        //full time or part time
        empComboBoxJobTime.getItems().clear();

        empComboBoxJobTime.getItems().addAll(
                "Full Time",
                "Part Time");

        //permissions access level
        empPremision.getItems().clear();

        empPremision.getItems().addAll(
                "Full",
                "Secretary",
                "User");

    }

    public void checkForTheValues()
    {
        if(this.addresLine1=true&& this.addresLine2==true&&this.city==true&&this.comboBoxJobTime==true&&this.contactNumber==true&&this.country==true &&this.county==true&& this.email==true&& this.firstName==true&& this.lastName==true&& this.login==true&& this.password==true&& this.premision==true&& this.wage==true)
        {
           this.addemp.setVisible(true);
        }
        else
        {
            this.addemp.setVisible(false);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gifLabel.setGraphic(storage.getAnimationLogin());
        gifLabel1.setGraphic(storage.getAnimationLogin());
        addemp.setVisible(false);
        
        this.mainAddManagerPanel.setStyle("-fx-base: #708090;-fx-text-fill: #ffffff;");
        this.addemp.setStyle("-fx-base: #228b22;-fx-text-fill: #ffffff;");
        timeJobJeckCheck.setGraphic(storage.getWrong());
        remisionCheck.setGraphic(storage.getWrong());
        emFNameCheck.setGraphic(storage.getWrong());
        emLNameCheck.setGraphic(storage.getWrong());
        emContactNumberCheck.setGraphic(storage.getWrong());
          emaddresFirstLine1Check.setGraphic(storage.getWrong());
           emaddresFirstLine2Check.setGraphic(storage.getWrong());
            citycheck.setGraphic(storage.getWrong());
            countyCheck.setGraphic(storage.getWrong());
             countryCheck.setGraphic(storage.getWrong());
               wageCheck.setGraphic(storage.getWrong());
                emailCheck.setGraphic(storage.getWrong());
        //initialize the listeners 
        initListeners();
        //initialize the combo box to display it 
        fillComboBox();
        // set all the booleans to false 
        firstName = false;
        lastName = false;
        contactNumber = false;
        addresLine1 = false;
        addresLine2 = false;
        city = false;
        county = false;
        country = false;
        comboBoxJobTime = false;
        wage = false;
        premision = false;
        email = false;
        login = false;
        password = false;

        if(up==true)
        {
            System.out.println("Update Procedure is Loaded");
            this.addemp.setStyle("-fx-base:  #ff8c00; -fx-text-fill:#ffffff;");
            this.addemp.setText("Update Employe");
            this.empContactNumber.setText(curentEmploye.getEmContactnumber());
            this.empEmail.setText(curentEmploye.getEmail());
            this.empFirstName.setText(curentEmploye.getEmFirname());
            this.empLastName.setText(curentEmploye.getEmLastname());
         //   this.empPremision.setSelectionModel(null);
          //  this.empComboBoxJobTime.setSelectionModel(null);
            this.empWage.setText(curentEmploye.getEmWages());
            
            StringTokenizer st = new StringTokenizer(curentEmploye.getEmAddress(),"%");
                while (st.hasMoreTokens()) {
             this.empaddresLine1.setText(st.nextToken());
            this.empaddresLine2.setText(st.nextToken());
            this.empcity.setText(st.nextToken());
            this.empcountry.setText(st.nextToken());
            this.empcounty.setText(st.nextToken());
            break;
                }
            
           
        }
        
    }

    public void iniAutoGenerateUser() {
        //Create login and password for usser.
        String login = "";//firstname Lisiner
        String password = "";
        if(this.empFirstName.getText().length()>2&& this.empLastName.getText().length()>3&&this.empcity.getText().length()>4&&this.empaddresLine1.getText().length()>4)
        {
        login = empFirstName.getText().substring(0, 2) + empLastName.getText().substring(0, 2);
        password = empcity.getText().substring(0, 3) + empaddresLine1.getText().substring(0, 3);
        this.userLogin.setText(login);
        this.userPassword.setText(password);
        this.password=true;
        this.login=true;
        checkForTheValues();
        this.passwordCheck.setGraphic(storage.getRiGht());
        this.loginCheck.setGraphic(storage.getRiGht());
        }
    }

    @FXML
    private void addEmployeeButtonCliked(MouseEvent event) throws IOException {
        //create two variables one holds all location info the other created the date for calendar. 
        // main add button clicked 
        String fullAddress = empaddresLine1.getText() + "%" + empaddresLine2.getText() + "%" + empcity.getText() + "%" + empcounty.getText() + "%" + empcountry.getText();

        if(this.curentEmploye==null&&up!=true){
        Date date1 = this.c1.getTime();

        //add all the input data into the variables which are stored in add employee method 
        sqe.addEmploye(empFirstName.getText(), empLastName.getText(), fullAddress, empContactNumber.getText(), empComboBoxJobTime.getSelectionModel().getSelectedItem(),
                empWage.getText(), empPremision.getSelectionModel().getSelectedItem(), date1, empEmail.getText());

        //add all the input data into the variables which are stored in addSuser 
        sqe.addSUser(userLogin.getText(), userPassword.getText(), sqe.getEmployeeByName(empFirstName.getText()));
        }else
        {
            this.sqe.updateEmploye(this.curentEmploye.getIdEmp(),empFirstName.getText(), empLastName.getText(), fullAddress, empContactNumber.getText(), empComboBoxJobTime.getSelectionModel().getSelectedItem(),
                empWage.getText(), empPremision.getSelectionModel().getSelectedItem(), empEmail.getText());
        }
        Stage mainManagerWindow = storage.getMainManagerWindow(sqe);
        mainManagerWindow.show();
        
        Stage stageL = (Stage) addemp.getScene().getWindow();
        stageL.close();
    }

    public void updateSqe(SqlWaiter sql,int id)
    {
        this.sqe=sql;
        this.curentEmploye =(Employe) sqe.getEmpByID(id);
        System.out.println("Selected employe is loded"+curentEmploye);
        up=true;
    }
    
    
    @FXML
    private void emFnamechange(InputMethodEvent event) {

      
    }

    @FXML
    private void emLastnameChange(InputMethodEvent event) {


    }

    @FXML
    private void emContactNumbnerChange(InputMethodEvent event) {
       
    }

    @FXML
    private void emAddreLine1Change(InputMethodEvent event) {
       
    }

    @FXML
    private void employeAdresLine2change(InputMethodEvent event) {
      
    }

    @FXML
    private void emCityChange(InputMethodEvent event) {
      
    }

    @FXML
    private void EmCountyChange(InputMethodEvent event) {
       
    }

    @FXML
    private void emCountryChange(InputMethodEvent event) {
      
    }

    @FXML
    private void timeClikedCheck(MouseEvent event) {
       comboBoxJobTime=true;
        checkForTheValues();
        this.timeJobJeckCheck.setGraphic(storage.getRiGht());
    }

    @FXML
    private void wageTextChange(InputMethodEvent event) {
       
    }

    @FXML
    private void premisionClikedCheck(MouseEvent event) {
        this.premision=true;
         checkForTheValues();
         remisionCheck.setGraphic(storage.getRiGht());
         
    }

    @FXML
    private void emailTextChange(InputMethodEvent event) {
       
    }

    //This allows the tick to clarify if data is entered.
    public void initListeners() {
        //for first name 
        empFirstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //if statements check the textfield to see if the length of the string has changed from 0, if it has then tick apears 
                if (empFirstName.getText().length() > 0) {
                    emFNameCheck.setGraphic(storage.getRiGht());
                    iniAutoGenerateUser();
                    firstName=true;
                    //if it hasn't then x appears 
                } else {
                    emFNameCheck.setGraphic(storage.getWrong());
                    firstName=false;
                }
                 
                checkForTheValues();
               
            }
        });
        //for last name 
        empLastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (empLastName.getText().length() > 0) {
                    emLNameCheck.setGraphic(storage.getRiGht());
                    iniAutoGenerateUser();
                    lastName=true;
                } else {
                    emLNameCheck.setGraphic(storage.getWrong());
                    lastName=false;
                }
                        checkForTheValues();
            }
        });
        //for contact number
        empContactNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (empContactNumber.getText().length() > 0) {
                    emContactNumberCheck.setGraphic(storage.getRiGht());
                    contactNumber=true;
                } else {
                    emContactNumberCheck.setGraphic(storage.getWrong());
                    contactNumber=false;
                }
                checkForTheValues();
            }
        });
        //for address line 1
        empaddresLine1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (empaddresLine1.getText().length() > 0) {
                    emaddresFirstLine1Check.setGraphic(storage.getRiGht());
                    iniAutoGenerateUser();
                    addresLine1=true;
                } else {
                    emaddresFirstLine1Check.setGraphic(storage.getWrong());
                    addresLine1=false;
                }
                checkForTheValues();
            }
        });
        //for address line 2 
        empaddresLine2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (empaddresLine2.getText().length() > 0) {
                    emaddresFirstLine2Check.setGraphic(storage.getRiGht());
                    addresLine2=true;
                } else {
                    emaddresFirstLine2Check.setGraphic(storage.getWrong());
                    addresLine2=false;
                }
                checkForTheValues();
            }
        });
        //for city 
        empcity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (empcity.getText().length() > 0) {
                    citycheck.setGraphic(storage.getRiGht());
                     iniAutoGenerateUser();
                     city=true;
                } else {
                    citycheck.setGraphic(storage.getWrong());
                    city=false;
                }
               checkForTheValues();
            }
        });
        //for county
        empcounty.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (empcounty.getText().length() > 0) {
                    countyCheck.setGraphic(storage.getRiGht());
                    county=true;
                } else {
                    countyCheck.setGraphic(storage.getWrong());
                    county=false;
                }
                checkForTheValues();
            }
        });
        //for country
        empcountry.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (empcountry.getText().length() > 0) {
                    countryCheck.setGraphic(storage.getRiGht());
                    country=true;
                } else {
                    countryCheck.setGraphic(storage.getWrong());
                    country=false;
                }
                checkForTheValues();
            }
        });
        //change wage 
        empWage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
             
                
                if (empWage.getText().length() > 0) {
                    wageCheck.setGraphic(storage.getRiGht());
                    wage=true;
                } else {
                    wageCheck.setGraphic(storage.getWrong());
                    wage=false;
                }
                checkForTheValues();
            }
        });
        //change email 
        empEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (empEmail.getText().length() > 0) {
                    emailCheck.setGraphic(storage.getRiGht());
                    email=true;
                } else {
                    emailCheck.setGraphic(storage.getWrong());
                    email=false;
                }
                checkForTheValues();
            }
        });

    }

}



