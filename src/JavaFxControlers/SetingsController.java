/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxControlers;

import SqlEntities.SUser;
import StorageUnit.StorageManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import otherClassys.SqlWaiter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class SetingsController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private Label gifLogo;
    @FXML
    private TextField newLoginTextFild;
    @FXML
    private PasswordField newPasswordTextFild;
    @FXML
    private PasswordField reNewPassword;
    @FXML
    private Button updateButton;
    @FXML
    private Label newLoginLabel;
    @FXML
    private Label newPasswordLabel;
    @FXML
    private Label newPassword;

    /**
     * Initializes the controller class.
     */
    private SqlWaiter waiter;
    private StorageManager storage=new StorageManager();
    SUser curent_user;
    private boolean login,pass,repass;
    public SetingsController(){}
    
    public void setSqlWaiter(SqlWaiter waiter)
    {
        this.waiter=waiter;
       SUser curent_user = waiter.getCurent_user();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       iniLisiners();
    }    

    public void iniLisiners()
    {
        newPasswordTextFild.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              login=true;
              updateIf();
            }
        });
        
        this.newLoginTextFild.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              pass=true;
              updateIf();
            }
        });
        
        this.reNewPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals(newPasswordTextFild.getText()))
                {
                    
                }
              repass=true;
              updateIf();
            }
        });
    }
    
    
    public void updateIf()
    {
        if(login==true&&repass==true&&pass==true)
        {
            this.updateButton.setVisible(true);
        }
        else
        {
              this.updateButton.setVisible(false);
        }
    }
    
    @FXML
    private void buttonaClicked(MouseEvent event) {
        
        waiter.updateUser(curent_user.getIdUser(), this.newLoginTextFild.getText(), this.newPasswordTextFild.getText(), curent_user.getIdEmploye());
    }
    
}
