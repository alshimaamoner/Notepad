/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.WindowConstants;

/**
 *
 * @author pc
 */
public class Lab5 extends Application {
   static Stage stage;
    NotepadBase root;
    public static boolean status;
    @Override
    public void start(Stage primaryStage) {
    
        root=new NotepadBase();
        Scene scene = new Scene(root, 300, 250);
        stage=primaryStage;
        primaryStage.setTitle("NotePad");
        primaryStage.setScene(scene);
        primaryStage.show();
        //status=root.exitApplication();
        

       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
       // System.exit(0);
    }
    
}
