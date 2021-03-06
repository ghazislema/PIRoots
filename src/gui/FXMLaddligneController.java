/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import appMainClasses.LigneMain;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Ligne;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceLigne;

/**
 * FXML Controller class
 *
 * @author ghazy
 */
public class FXMLaddligneController implements Initializable {
private ResourceBundle bundle;
    @FXML
    private Label OP_SUCCESS;
    @FXML
    private Label OP_SUCCESS1;
    @FXML
    private AnchorPane BOX_NOTIF;
    @FXML
    private AnchorPane BOX_NOTIF_WARNING;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button BTN_LINE_MANAGEMENT;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private JFXButton ADD_LINE_BTN;
    @FXML
    private Hyperlink PREV_LINK;
    @FXML
    private ComboBox COMBO_BOX_TRANSP;
    @FXML
    private JFXTextField LINE_NAME;
     @FXML
    private Button btnMenus1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadpages();
              //Chargement langage
 // Loadlang(LigneMain.language);
  
        COMBO_BOX_TRANSP.getItems().addAll("Bus","Train","Metro");
        
       
        
        ADD_LINE_BTN.setOnAction(e->{
            
           
            if (COMBO_BOX_TRANSP.getValue()==null  || LINE_NAME.getText()==null
                    || LINE_NAME.getText().trim().isEmpty())
                {
                     BOX_NOTIF_WARNING.setVisible(true);
                    BOX_NOTIF.setVisible(false);
                }
      
            else
                {
                
            ServiceLigne service=new ServiceLigne();
            String nom= LINE_NAME.getText();
            String transp= (String) COMBO_BOX_TRANSP.getValue();
            Ligne ligne=new Ligne();
            ligne.setNom(nom);
            ligne.setMoyentransport(transp);
            
            //here
            if (service.searchLigne(nom)){
                 Alert alert = new Alert(Alert.AlertType.WARNING);

alert.setHeaderText(null);
alert.setContentText("Line already exists !");

alert.showAndWait();
                
            }
            else
                {
                    
                
            service.insert(ligne);


  Alert alert = new Alert(Alert.AlertType.INFORMATION);

alert.setHeaderText(null);
alert.setContentText("Line has been created successfully.");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    
        Parent parent;
             try {
                 parent = FXMLLoader.load(getClass().getResource("FXMLgestionlignes.fxml"));
                  Scene scene = new Scene(parent);
        
        
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
} 
            
            
            BOX_NOTIF_WARNING.setVisible(false);
                
                }
                }
        });
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
     private void Loadlang(String lang) {
  Locale locale = new Locale(lang);
  bundle = ResourceBundle.getBundle("i18n.mybundle", locale);

  PREV_LINK.setText(bundle.getString("PREV_PAGE"));
   btnOrders.setText(bundle.getString("BTN_TRIPS_MANAGEMENT"));
    BTN_LINE_MANAGEMENT.setText(bundle.getString("BTN_LINE_MANAGEMENT"));
            btnMenus.setText(bundle.getString("BTN_STATION_MANAGEMENT"));
            btnSignout.setText(bundle.getString("BTN_SIGN_OUT"));
                    LINE_NAME.setPromptText(bundle.getString("LINE_NAME"));
                    COMBO_BOX_TRANSP.setPromptText(bundle.getString("MEANS_OF_TRANSPORt"));
                            PREV_LINK.setText(bundle.getString("PREV_PAGE"));
                                    OP_SUCCESS1.setText(bundle.getString("OP_FAIL"));
                                    OP_SUCCESS.setText(bundle.getString("OP_SUCC"));

 }

      public void loadpages()
 {
       btnMenus1.setOnAction(e->{
          Parent showligne;
             try {
                 showligne = FXMLLoader.load(getClass().getResource("affectationmoyentransport.fxml"));
                  Scene scene = new Scene(showligne);
        
        
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
       
         });
     btnOrders.setOnAction(e->{
          Parent showligne;
             try {
                 showligne = FXMLLoader.load(getClass().getResource("FXMLgestionvoyage.fxml"));
                  Scene scene = new Scene(showligne);
        
        
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
       
         });
     
     BTN_LINE_MANAGEMENT.setOnAction(e->{
         
          Parent showligne;
             try {
                 showligne = FXMLLoader.load(getClass().getResource("FXMLgestionlignes.fxml"));
                  Scene scene = new Scene(showligne);
        
        
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
         
     });
       btnOverview.setOnAction(e->{
          Parent showligne;
             try {
                 showligne = FXMLLoader.load(getClass().getResource("HOME.fxml"));
                  Scene scene = new Scene(showligne);
        
        
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
       
         });
     btnSettings.setOnAction(e->{
         Parent showligne;
             try {
                 showligne = FXMLLoader.load(getClass().getResource("RoadConstruction.fxml"));
                  Scene scene = new Scene(showligne);
        
        
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
         
     });
     btnMenus.setOnAction(e->{
         Parent showligne;
             try {
                 showligne = FXMLLoader.load(getClass().getResource("FXMLgestionstations.fxml"));
                  Scene scene = new Scene(showligne);
        
        
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
         
     });
     btnSignout.setOnAction(e->{
          Parent showligne;
             try {
                 showligne = FXMLLoader.load(getClass().getResource("FXMLlogin.fxml"));
                  Scene scene = new Scene(showligne);
        
        
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
         
     });
        
 }
    @FXML
    private void Back(ActionEvent event) throws IOException {
        
        Parent showligne = FXMLLoader.load(getClass().getResource("FXMLgestionlignes.fxml"));
        Scene scene = new Scene(showligne);
        
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
