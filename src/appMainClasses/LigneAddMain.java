/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appMainClasses;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ghazy
 */
public class LigneAddMain extends Application {
    
    public static String language = "en";
    
    @Override
    public void start(Stage s) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/gui/FXMLaddligne.fxml"));
        
        Scene scene = new Scene(root);
        
        s.setScene(scene);
        
        s.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
