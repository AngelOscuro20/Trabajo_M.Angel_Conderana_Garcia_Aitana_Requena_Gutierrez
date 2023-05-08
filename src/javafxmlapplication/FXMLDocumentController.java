/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.Club;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    //========================================================
    // objects defined into FXML file with fx:id 
    @FXML
    private Button buttonClick;
    @FXML
    private Label labelMessage;
    @FXML
    private Button iniciar;
    private Club greenball = null;
    
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    @FXML
    private void handleButtonAction(ActionEvent event) {
        labelMessage.setText("C");
    }
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new
FXMLLoader(getClass().getResource("FXMLiniciarSesion.fxml"));
Parent root = miCargador.load();
FXMLiniciarSesionController controladorInicioSesion = miCargador.getController();
controladorInicioSesion.initSesion(greenball);
Scene scene = new Scene(root,500,300);
Stage stage = new Stage();
stage.setScene(scene);
stage.setTitle("Vista datos persona");
stage.initModality(Modality.APPLICATION_MODAL);
//la ventana se muestra modal
stage.show();



    }
    

    
}
