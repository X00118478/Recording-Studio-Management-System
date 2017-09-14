/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StorageUnit;

import JavaFxControlers.MainRecordingStudioWindowController;
import JavaFxControlers.BookBandController;
import JavaFxControlers.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import otherClassys.SqlWaiter;

/**
 *
 * @author Daniel
 */
// class for resorcess
public class StorageManager {

    private static final String LOGIN_PATH = "Login.fxml";
    private static final String MAIN_RECORDING_STUDIO_WINDOW = "MainRecordingStudioWindow.fxml";
    private static final String LOGIN_LABEL_GIF = "gif1.gif";
    private static final String ADD_BAND = "AddBand.fxml";
    private static final String ADD_MANAGER = "addManager.fxml";
    private static final String ADD_EMPLOYE = "AddEmploye.fxml";
 
    
    private static final String MAKE_BOOKING = "BookBand.fxml";
    private static final String RIGHT = "Co.png";
    private static final String WRONG = "wr.png";

    private static final String MANAGER_WINDOW = "managerWindow.fxml";//to be done
    private static final String TECHNICIAN = "technician.fxml";
    private static final String SERVICES = "service.fxml";
    private static final String EQUIPMENT = "equipment.fxml";
    private static final String SERVICE_CHAR = "serviceChart.fxml";
    private static final String EQUIPMENT_CHART = "equipmentChart.fxml";
    private static final String TECH_CHART = "techChart.fxml";
    private static final String EMPLOYE_CHART = "employeChart.fxml";
    private static final String UPDATE_VIEW_ADD = "updateAdd.fxml";
    private static final String HELP = "help.fxml";
    private static final String RECEPIE = "recepie.fxml";
    private static final String CALENDAR = "calendar.fxml";
    private static final String DB="data.png";
    private static final String POP="popUp.fxml";
     private static final String SETINGS="setings.fxml";
     private static final String ABOUT="About.fxml";
     private static final String ABUTO="rights.jpg";
    public StorageManager() {

    }

    public Image getIcon() {
        return new Image(".\\StorageUnit\\RecordingStudioIcon.png");
    }

      
    public ImageView getAnimationLogin(double width,double heith) {
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(ABUTO)));
        image.setFitHeight(heith);
        image.setFitWidth(width);
        image.setSmooth(true);
        return image;
    }
    
    
    public ImageView getAnimationLogin() {
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(LOGIN_LABEL_GIF)));
        image.setFitHeight(94.0);
        image.setFitWidth(499.0);
        image.setSmooth(true);
        return image;
    }

    public ImageView getRiGht() {
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(RIGHT)));
        image.setFitHeight(37.0);
        image.setFitWidth(41.0);
        image.setSmooth(true);
        return image;
    }

    public ImageView getWrong() {
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(WRONG)));
        image.setFitHeight(37.0);
        image.setFitWidth(41.0);
        image.setSmooth(true);
        return image;
    }

     public ImageView getPopupWindowImage() {
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(DB)));
        image.setFitHeight(138.0);
        image.setFitWidth(562.0);
        image.setSmooth(true);
        return image;
    }
    
//    
     public URL getSetings() throws IOException {
        return getClass().getResource(SETINGS);
    }
     
       public URL getAbout() throws IOException {
        return getClass().getResource(ABOUT);
    }
     
     
    public Parent getLogin() throws IOException {
        return FXMLLoader.load(getClass().getResource(LOGIN_PATH));
    }

    public URL getUpdateAddRecordingStudio() throws IOException {
        return getClass().getResource(UPDATE_VIEW_ADD);
    }

    public URL getPopUpWindow() throws IOException {
        return getClass().getResource(POP);
    }
    
    public URL getCalendarRecordingStudio() throws IOException {
        return getClass().getResource(CALENDAR);
    }

    public URL getRecRecordingStudio() throws IOException {
        return getClass().getResource(RECEPIE);
    }

    public URL getHelpRecordingStudio() throws IOException {
        return getClass().getResource(HELP);
    }

    public URL getMainRecordingStudio() throws IOException {
        return getClass().getResource(MAIN_RECORDING_STUDIO_WINDOW);
    }

    public URL getManagerWIndow() {
        return getClass().getResource(MANAGER_WINDOW);
    }

    
    public URL getAddBandRecordinStudioForm() throws IOException {
        URL url = getClass().getResource(ADD_BAND);
        return url;
    }

    public URL getAddEmployeRecordingStudioForm() throws IOException {
        return getClass().getResource(ADD_EMPLOYE);
    }

    public URL getBookingBandRecordingStudioForm() throws IOException {
        URL url = getClass().getResource(MAKE_BOOKING);
        return url;
    }

   
    public URL getAddManagerRecordingStdioForm() throws IOException {
        URL url = getClass().getResource(ADD_MANAGER);
        return url;
    }

  

    public URL getAddEquipmentRecordingStudioForm() throws IOException {
        URL url = getClass().getResource(EQUIPMENT);
        return url;
    }

    public URL getAddServicesRecordingStudioForm() throws IOException {
        URL url = getClass().getResource(SERVICES);
        return url;
    }

    public URL getAddTechnicianRecordingStudioForm() throws IOException {
        URL url = getClass().getResource(TECHNICIAN);
        return url;
    }

    public URL getAddEmployeChartRecordingStudioForm() throws IOException {
        URL url = getClass().getResource(EMPLOYE_CHART);
        return url;
    }

    public URL getAddEquipmentChartRecordingStudioForm() throws IOException {
        URL url = getClass().getResource(EQUIPMENT_CHART);
        return url;
    }

    public URL getAddServicesChartRecordingStudioForm() throws IOException {
        URL url = getClass().getResource(SERVICE_CHAR);
        return url;
    }

    public URL getAddTechnicianChartRecordingStudioForm() throws IOException {
        URL url = getClass().getResource(TECH_CHART);
        return url;
    }
    
    public Stage getSetingsStage(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getSetings());
        SetingsController con = new SetingsController();

        loader.setController(con);
        con.setSqlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
           try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      }); 
        stage.getIcons().add(getIcon());
         stage.setTitle("Recording Studio");
        return stage;

    }
    
     public Stage getAbautStage(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAbout());
        AboutController con = new AboutController();
        loader.setController(con);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
           try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      }); 
        stage.getIcons().add(getIcon());
         stage.setTitle("Recording Studio");
        return stage;

    }
    
    
    public Stage getHelRec(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getHelpRecordingStudio());
        HelpController con = new HelpController();

        loader.setController(con);
        //con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
           try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      }); 
        stage.getIcons().add(getIcon());
         stage.setTitle("Recording Studio");
        return stage;

    }
    
    public Stage getRecWindow(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getRecRecordingStudio());
        RecepieController con = new RecepieController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
           try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }
    
    public Stage getCallendar(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getCalendarRecordingStudio());
        CalendarController con = new CalendarController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }
    
     public Stage getAddUpdate(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getUpdateAddRecordingStudio());
        UpdateAddController con = new UpdateAddController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }
    
    public Stage getMainManagerWindow(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getManagerWIndow());
        ManagerWindowController con = new ManagerWindowController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
           try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getUpdateService(SqlWaiter sqlWaiter, int id) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddServicesRecordingStudioForm());
        ServiceController con = new ServiceController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter, id);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
           try {
              Stage mainManagerWindow = getMainManagerWindow(sqlWaiter);
              mainManagerWindow.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getAddService(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddServicesRecordingStudioForm());
        ServiceController con = new ServiceController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage mainManagerWindow = getMainManagerWindow(sqlWaiter);
              mainManagerWindow.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getAddTechnician(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddTechnicianRecordingStudioForm());

        TechnicianController con = new TechnicianController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
           try {
              Stage mainManagerWindow = getMainManagerWindow(sqlWaiter);
              mainManagerWindow.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getUpdateTechnician(SqlWaiter sqlWaiter, int id) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddTechnicianRecordingStudioForm());

        TechnicianController con = new TechnicianController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter, id);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage mainManagerWindow = getMainManagerWindow(sqlWaiter);
              mainManagerWindow.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getUpdateEquipment(SqlWaiter sqlWaiter, int id) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddEquipmentRecordingStudioForm());

        EquipmentController con = new EquipmentController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter, id);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage mainManagerWindow = getMainManagerWindow(sqlWaiter);
              mainManagerWindow.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getAddEquipment(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddEquipmentRecordingStudioForm());

        EquipmentController con = new EquipmentController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
        try {
              Stage mainManagerWindow = getMainManagerWindow(sqlWaiter);
              mainManagerWindow.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getAddEmploye(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddEmployeRecordingStudioForm());

        AddEmployeController con = new AddEmployeController();

        loader.setController(con);
        con.setSqlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
         try {
              Stage mainManagerWindow = getMainManagerWindow(sqlWaiter);
              mainManagerWindow.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getUpdateEmploye(SqlWaiter sqlWaiter, int id) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddEmployeRecordingStudioForm());

        AddEmployeController con = new AddEmployeController();

        loader.setController(con);
        con.updateSqe(sqlWaiter, id);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage mainManagerWindow = getMainManagerWindow(sqlWaiter);
              mainManagerWindow.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;

    }

    public Stage getMainRecordingStudio(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getMainRecordingStudio());
        MainRecordingStudioWindowController con = new MainRecordingStudioWindowController();

        loader.setController(con);
        con.setSqlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaximized(true);
         stage.setTitle("Recording Studio");
         stage.getIcons().add(getIcon());
        return stage;
    }

     public Stage getPopUPWIndow(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getPopUpWindow());
       PopUpController con = new PopUpController(message);

        loader.setController(con);
        
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
         stage.setTitle("Recording Studio");
         stage.getIcons().add(getIcon());
         
        return stage;
    }
    
    
    public Stage getMakeBookingStage(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getBookingBandRecordingStudioForm());
        BookBandController con = new BookBandController();

        loader.setController(con);
        con.setSqlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;
    }

    
      public Stage getUpdateBookingStage(SqlWaiter sqlWaiter,int id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getBookingBandRecordingStudioForm());
        BookBandController con = new BookBandController();

        loader.setController(con);
        con.setSqlWaiter(sqlWaiter, id);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
               Stage addUpdate = getAddUpdate(sqlWaiter);
              addUpdate.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;
    }
    
    public Stage getAddBandStage(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getAddBandRecordinStudioForm());
        AddBandController con = new AddBandController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage mainRecordingStudio = getMainRecordingStudio(sqlWaiter);
              mainRecordingStudio.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
          
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;
    }
    
     public Stage getUpdateBandStage(SqlWaiter sqlWaiter,int id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getAddBandRecordinStudioForm());
        AddBandController con = new AddBandController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter, id);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              
              Stage addUpdate = getAddUpdate(sqlWaiter);
              addUpdate.show();
              
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
          
      }
  }); 
         stage.getIcons().add(getIcon());
          stage.setTitle("Recording Studio");
        return stage;
    }
    

    public Stage getAddManagerStage(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(getAddManagerRecordingStdioForm());
        AddManagerController con = new AddManagerController();

        loader.setController(con);
        con.setSqlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
          try {
              Stage addBandStage = getAddBandStage(sqlWaiter);
              addBandStage.show();
          } catch (IOException ex) {
              Logger.getLogger(StorageManager.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }); 
        stage.setTitle("Recording Studio");
         stage.getIcons().add(getIcon());
        return stage;
    }
    
      public Stage getTechChart(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddTechnicianChartRecordingStudioForm());
        TechChartController con = new TechChartController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Recording Studio");
         stage.getIcons().add(getIcon());
        return stage;
    }
    
      public Stage getServiChart(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddServicesChartRecordingStudioForm());
        ServiceChartController con = new ServiceChartController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Recording Studio");
         stage.getIcons().add(getIcon());
        return stage;
    }  

     public Stage getEquipChart(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddEquipmentChartRecordingStudioForm());
        EquipmentChartController con = new EquipmentChartController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Recording Studio");
         stage.getIcons().add(getIcon());
        return stage;
    }  
      
      public Stage getEmpChart(SqlWaiter sqlWaiter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getAddEmployeChartRecordingStudioForm());
        EmployeChartController con = new EmployeChartController();

        loader.setController(con);
        con.setSQlWaiter(sqlWaiter);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Recording Studio");
         stage.getIcons().add(getIcon());
        return stage;
    }  
     
      
}
