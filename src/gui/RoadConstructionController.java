/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entities.Ligne;
import entities.Station;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ServiceLigne;
import service.ServiceStation;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RoadConstructionController implements Initializable {

 @FXML
 private Button btnOverview;
 @FXML
 private Button btnOrders;
 @FXML
 private Button BTN_LINE_MANAGEMENT;
 @FXML
 private Button btnMenus;
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
 private TextField SEARCH_BAR;
 @FXML
 private Label H1_LINE_MANAGEMENT;
 @FXML
 private VBox pnItems;
 @FXML
 private Label COL2_LIST_LIGNE;
 @FXML
 private Label COL1_LIST_LIGNE;
 @FXML
 private AnchorPane ANCHOR_UPDATE;
 @FXML
 private JFXTextField LINE_NAME;
 @FXML
 private ComboBox < ? > COMBO_BOX_TRANSP;
 @FXML
 private ImageView SAVE_ICON;
 @FXML
 private ImageView EXIT_ICON;
 @FXML
 private Label LINE_ID;
 @FXML
 private ImageView RELOAD_BTN;
 @FXML
 private ImageView RELOAD_BTN1;
 @FXML
 private Label BTN_ADD_NEW_LINE;
 @FXML
 private JFXComboBox < Label > LINE_CAMBO_BAWKS;
 @FXML
 private RadioButton radio1;
 @FXML
 private RadioButton radio2;
 @FXML
 private RadioButton radio3;
 @FXML
 private Label tx1;
 @FXML
 private Label tx2;
 @FXML
 private JFXComboBox combo_depart;
 @FXML
 private JFXComboBox combo_arrive;
 @FXML
 private Label tx3;
 @FXML
 private JFXRadioButton manual;
 @FXML
 private JFXRadioButton Automatic1;
 @FXML
 private Hyperlink next1;
 @FXML
 private Hyperlink next2;
 @FXML
 private Hyperlink prev1;
 @FXML
 private Hyperlink prev2;
 @FXML
 private AnchorPane STEP1_ANCHOR;
 @FXML
 private AnchorPane STEP2_ANCHOR;
 @FXML
 private AnchorPane STEP3_ANCHOR;
 @FXML
 private StackPane pane;
 @FXML
 private JFXChipView < String > chipView;
 @FXML
 private ImageView DISPLAY_MAP;
 @FXML
 private JFXButton SAVE_TRAJECT;


 ServiceStation serv_station = new ServiceStation();
 ServiceLigne serv_ligne = new ServiceLigne();
 /**
  * Initializes the controller class.
  */

 public void retrievedata() {

  LINE_CAMBO_BAWKS.setVisibleRowCount(4);
  final ToggleGroup group = new ToggleGroup();
  radio1.setToggleGroup(group);
  radio2.setToggleGroup(group);
  radio3.setToggleGroup(group);

  group.selectedToggleProperty().addListener(e -> {
   RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
   String str = selectedRadioButton.getText();
   
   LINE_CAMBO_BAWKS.getItems().clear();
   
   List < Ligne > list_ligne = serv_ligne.searchLineByNameTransport(str);

   LINE_CAMBO_BAWKS.setDisable(false);
   tx1.setDisable(false);

   for (Ligne l: list_ligne) {
    Label nom = new Label(l.getNom());
    LINE_CAMBO_BAWKS.getItems().add(nom);
   }

  });


  List < Station > list_station = serv_station.findAll();


  next1.setOnAction(ev2 -> {
      
      
      if (LINE_CAMBO_BAWKS.getValue()!=null)
          {
              
          
         String nom=LINE_CAMBO_BAWKS.getValue().getText();
         if (serv_ligne.checktraject(nom))
          {
              Alert alert = new Alert(AlertType.WARNING);

alert.setHeaderText(null);
alert.setContentText("Please choose another line. This one has a traject.");

alert.showAndWait();
          }
         else
          {
                STEP1_ANCHOR.setVisible(false);
   STEP2_ANCHOR.setVisible(true);
          }
         }
      else
          {
               Alert alert = new Alert(AlertType.WARNING);

alert.setHeaderText(null);
alert.setContentText("Please select a line !");
alert.showAndWait();
          }
 
   

  });
  next2.setOnAction(ev2 -> {
   STEP1_ANCHOR.setVisible(false);
   STEP2_ANCHOR.setVisible(false);
   STEP3_ANCHOR.setVisible(true);
   System.out.println("size chip view =" + chipView.getChips().size());

  

  });
  prev1.setOnAction(ev2 -> {
   STEP1_ANCHOR.setVisible(true);
   STEP2_ANCHOR.setVisible(false);
   STEP3_ANCHOR.setVisible(false);
  });
  prev2.setOnAction(ev2 -> {
   STEP1_ANCHOR.setVisible(false);
   STEP2_ANCHOR.setVisible(true);
   STEP3_ANCHOR.setVisible(false);
  });

  
  //affichage carte
DISPLAY_MAP.setOnMouseClicked(e->{
     //chip view size bech nged beha el map
   int n = chipView.getChips().size();
   List < Station > list2_stat = new ArrayList < Station > ();

   TestingTraject test = new TestingTraject();
   if (n != 0) {
    for (int i = 0; i < n; i++) {

     TestingTraject.list.add(serv_station.searchById(chipView.getChips().get(i)));

     list2_stat.add(serv_station.searchById(chipView.getChips().get(i)));
    }
   }
   Parent root;

   Stage stage = new Stage();
   test.start(stage);
});


SAVE_TRAJECT.setOnAction(ev->{
    
  String nom_l=LINE_CAMBO_BAWKS.getValue().getText()+"";
  
   int n = chipView.getChips().size();
   
   if (n != 0) {
    for (int i = 0; i < n; i++) {

     Station stat=serv_station.searchById(chipView.getChips().get(i));
     serv_ligne.MakeTraject(nom_l, stat, i);
    }
   }
   
    Alert alert = new Alert(AlertType.INFORMATION);

alert.setHeaderText(null);
alert.setContentText("Traject generated successfully.");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    
        Parent parent;
             try {
                 parent = FXMLLoader.load(getClass().getResource("RoadConstruction.fxml"));
                  Scene scene = new Scene(parent);
        
        
        Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLgestionstationsController.class.getName()).log(Level.SEVERE, null, ex);
             }
} 
  
});

  for (Station s: list_station) {
   chipView.getSuggestions().add(s.getNom());
  }

  chipView.setStyle("-fx-background-color: WHITE;");

  pane.setStyle("-fx-background-color:GRAY;");


 }
 @Override
 public void initialize(URL url, ResourceBundle rb) {
loadpages();
  retrievedata();
  List < Station > list_station = serv_station.findAll();
 
 }

 @FXML
 private void handleClicks(ActionEvent event) {}
 @FXML
 private void Back(ActionEvent event) {}
 
 
  public void loadpages()
 {
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

}